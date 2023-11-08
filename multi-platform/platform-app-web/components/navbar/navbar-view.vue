<template>
	<view class="navbar-view">
		<view class="navbar-item-view">
			<view class="left-icon-view"
				@click="leftClick"
				v-if="leftIcon">
				<u-icon 
					:name="leftIcon" 
					:color="iconColor" 
					size="28">
				</u-icon>
			</view>
			<view class="navbar-item-text" v-if="centerTitle" :style="'color:'+titleColor">
				{{centerTitle}}
			</view>
			<view class="right-icon-view" v-if="rightIcon">
				<u-icon 
					:name="rightIcon" 
					:color="iconColor" 
					size="28">
				</u-icon>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		name: 'navbarView',
		props: {
			//标题颜色
			titleColor : {
				type:String,
				required:false,
				default: () => {
					return ''
				}
			},
			//导航栏图标颜色
			iconColor : {
				type:String,
				required:false,
				default: () => {
					return ''
				}
			},
			//右边的图标
			rightIcon : {
				type:String,
				required:false,
				default: () => {
					return ''
				}
			},
			//左边的图标
			leftIcon : {
				type:String,
				required:false,
				default: () => {
					return ''
				}
			},
			//中间的名字
			centerTitle : {
				type:String,
				required:false,
				default: () => {
					return '页面导航'
				}
			}
		},
		components:{
			
		},
		data() {
			return {
				
			}
		},
		methods: {
			//左边图标点击
			leftClick(){
				this.$emit('leftClick');
			}
		}
	}
</script>

<style lang="scss" scoped>
	.navbar-view{
		width: 100%;
		display: flex;
		flex-direction: column;
		justify-content: flex-start;
		align-items: center;
		align-content:center;
		background-image: linear-gradient(to right, #93dadc, #f9d2c3);
		box-shadow: 0 0 15px 10px #fff; 
		border-radius: 0px 0px 5px 5px ;
		.navbar-item-view{
			// display: flex;
			// flex-direction: row;
			// justify-content: flex-start;
			// align-items: center;
			// align-content:center;
			width: 100%;
			margin: 15rpx 0rpx;
			.left-icon-view{
				float: left;
			}
			.right-icon-view{
				float: right;
			}
			.navbar-item-text{
				text-align: center;
			}
		}
	}
</style>