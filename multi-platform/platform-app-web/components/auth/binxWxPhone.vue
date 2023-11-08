<template>
	<view v-if="show">
		<view class="mask"></view>
		<!--绑定微信手机号-->
		<view>
			<view class="wxbox">
				<view class="wx-logo"><image src="/static/icon/wx-bind.png"></image></view>
				<view class="tips">为了您的账号安全，请绑定微信手机号，绑定后可快捷登录</view>
				<button  
					type="primary" 
					class="bind-we-chat-btn" 
					open-type="getPhoneNumber" @getphonenumber="getPhoneNumber" >
					立即绑定微信手机号
				</button>
				<view class="close-view" @click="close"><icon type="clear" size="26"/></view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		name: 'bindWxPhone',
		props: {
			show: {
				type: Boolean,
				default: false
			},
		},
		components:{
			
		},
		data() {
			return {
				
			};
		},
		methods:{
			//关闭
			close(){
				this.$emit('close');
			},
			//拿到用户的手机号
			async getPhoneNumber(e){
				let code = e.detail.code
				if(!code){
					uni.$u.toast('拿不到微信平台绑定手机号的Code,请联系管理员');
					this.close();
					return;
				}
				console.log('开始绑定用户微信手机号的code:'+code)
				let data = {
					//getPhoneCode是传递到后台的数据
					getPhoneCode:e.detail.code,
				}
				this.$emit('handleBindPhone',data);
			}
		}
	}
</script>

<style lang="scss">
	.mask{
		position: fixed;
		top: 0;
		left:0;
		width: 100%;
		height: 100%;
		z-index:888;
		background-color: rgba(0, 0, 0, 0.6);
	}
	.btn{
		width:70%;
		margin:20rpx auto;
		margin-top:40rpx;
		padding:20rpx 0px;
		text-align:center;
		background:#4884FF;
		border-radius: 34px;
		font-size: 30rpx;
		color:#fff;
	}
	//微信弹窗
	.wxbox{
		position: fixed; //让元素跟随滚动
		top:20%;
		z-index: 998;
		width:80%;
		left:10%;
		background-color: #fff;
		border-radius:30rpx;
		padding:30rpx 0rpx;
		.close-view{
			position: absolute;
			top:5px;
			right:10px;
			z-index: 999;
			text-align: center;
		}
		.wx-logo{
			margin:20px 0 0 0px;
			text-align: center;
			image{
				width:100px;
				height:100px;
			}
		}
		.tips{
			padding:10rpx 20rpx;
			text-align:center;
			font-size:35rpx;
			color:#333;
		}
		.bind-we-chat-btn{
			width:370rpx;
			height: 80rpx;
			margin:30rpx auto;
			padding-bottom: 30rpx;
			padding-top: 8rpx;
			text-align:center;
			background:#02bb29;
			border-radius: 15px;
			font-size: 28rpx;
			color:#fff;
		}
	}
</style>
