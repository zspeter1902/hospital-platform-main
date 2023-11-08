<template>
	<view class="upload-view">
		<view class="upload-img-view">
			<view class="img-show-view" 
				@click="handleUploadImg"
				v-if="!imgUrl">
				<u--image
					radius="20"
					width="200rpx" 
					height="200rpx"
					:src="uploadIcon" 
					mode="aspectFit">
					<template v-slot:loading>
						<u-loading-icon color="red"></u-loading-icon>
					</template>
				</u--image>
			</view>
			<view 
				v-else
				class="img-show-view" 
				@click="handleUploadImg">
				<u--image
					radius="20"
					width="200rpx" 
					height="200rpx"
					:src="handleImageUrl(imgUrl)" 
					mode="aspectFit">
					<template v-slot:loading>
						<u-loading-icon color="red"></u-loading-icon>
					</template>
				</u--image>
			</view>
			<view class="img-upload-text">
				{{uploadText}}
			</view>
		</view>
	</view>
</template>
<script>
	import baseUtils from '@/utils/baseUtils';
	import moment from 'moment'
	export default {
		props: {
			//上传图片的位置
			uploadText: {
				type: String,
				default: () => {
					return '上传图片'
				}
			},
		},
		components:{
			
		},
		data() {
			return {
				//当前展示的图片
				imgUrl:'',
				//上传图片的地址
				uploadUrl:this.$bizConstants.UPLOAD_FILE_URL,
				//上传图片的图标
				uploadIcon: '/static/icon/upload-icon.png',
				commonGreen:this.$commonGreen,
				commonRed:this.$commonRed,
				mainColor:this.$mainColor,
				sketchColor:this.$sketchColor,
				commonBg:this.$commonBg,
				commonDark:this.$commonDark,
				commonWhite:this.$commonWhite,
			}
		},
		async onShow() {
			//验证登陆Token
			//await this.$authUtils.verifyLogin();
		},
		async onLoad(options){
			
		},
		methods: {
			//设置当前的图片
			setImgUrl(imgUrl){
				this.imgUrl = imgUrl;
			},
			//处理图片
			handleImageUrl(imgUrl){
				let url = this.$imageHandler.handleImageUrlSync(imgUrl);
				console.log('url:'+url);
				return url;
			},
			//选择图片
			async handleUploadImg(){
				console.log('this.choosedImage:'+this.choosedImage)
				let imgUrl = this.imgUrl;
				//如果存在图片，则需要删除
				if(imgUrl){
					let params = {
						loading:true,
						data: {
							name:imgUrl
						},
						urlData:{
							name:imgUrl
						},
						...this.$apiConstants.DELETE_FILE
					}
					let result = await this.$deleteRequest(params)
					console.log('删除图片结果:'+JSON.stringify(result))
					//上传成功
					this.$emit('delete',imgUrl);
					await this.clearAll();
				}
				uni.chooseImage({
					count: 1, //默认9
					sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
					sourceType: ['album'], //从相册选择
					success: async (res) =>  {
						uni.showLoading({
						    title: '处理中',
							mask: true
						});
						let tempFilePaths = res.tempFilePaths;
						if(!tempFilePaths){
							uni.hideLoading();
						}
						this.choosedImage = tempFilePaths[0];
						console.log('this.choosedImage:'+this.choosedImage);
						console.log('this.uploadUrl:'+this.uploadUrl);
						//执行上传图片
						await uni.uploadFile({
							url: this.uploadUrl,
							filePath: tempFilePaths[0],
							name: 'file',
							formData: {},
							success: async (res) => {
								let uploadData = res.data;
								console.log('uploadData:'+uploadData)
								if(!uploadData){
									uni.hideLoading();
									return;
								}
								let uploadResp = JSON.parse(uploadData).data;
								console.log('uploadResp:'+JSON.stringify(uploadResp))
								if(!uploadResp){
									uni.hideLoading();
									return;
								}
								let fileName = uploadResp.fileName;
								console.log('fileName:'+fileName)
								if(!fileName){
									uni.hideLoading();
									return;
								}
								if(!fileName){
									uni.hideLoading();
									return;
								}
								// let handledUrl = await this.$imageHandler.handleImageUrl(fileName);
								// this.imgUrl = handledUrl;
								this.imgUrl = fileName;
								//console.log('this.imgUrl:'+this.imgUrl)
								uni.hideLoading();
								//上传成功
								this.$emit('success',fileName);
							}
						});
					}
				});
			},
			//清除所有
			async clearAll(){
				this.imgUrl = '';
			},
			//路由跳转
			async routeTo(url){
				this.$u.route({
					url: url,
					params: {
						
					}
				})
			},
		}
	}
</script>

<style lang="scss" scoped>
	.upload-view {
		display: flex;
		flex-direction: column;
		justify-content: flex-start;
		align-items: center;
		background-color: $pageViewBgColor;
		//border: 2px solid #FA3534;
		.upload-img-view{
			border-radius: 10rpx;
			display: flex;
			flex-direction: column;
			justify-content: center;
			align-items: center;
			background-color: $common-white;
			.img-show-view{
				margin: 10rpx 10rpx;
				border-radius: 10rpx;
			}
			.img-upload-text{
				margin: 10rpx 10rpx;
				border-radius: 10rpx;
			}
		}
	}
</style>
