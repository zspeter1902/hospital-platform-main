import authUtils from '@/utils/authUtils.js'
import baseUtils from '@/utils/baseUtils.js'
import bizConstants from '@/utils/bizConstants.js'
export const imageHandler = {
	//拿到图片地址
	handleImageUrlSync(url){
	    if(baseUtils.isNull(url)){
	      return '';
	    }
		let handledUrl = bizConstants.OSS_URL+url
	    return handledUrl
	},
	//拿到图片地址
	async handleImageUrl(url){
	    if(baseUtils.isNull(url)){
	      return '';
	    }
		let handledUrl = bizConstants.OSS_URL+url
	    return handledUrl
	},
	urlToBase64(url){//网络上的URL资源转换程base64格式
		return new Promise((reslove,reject) => {
			uni.request({
				method: 'GET',
				url: apiRequest.OSS_BASE_URL + url,
				responseType: "arraybuffer",
				success: (res) => {
					if(!res.statusCode == 200){
						uni.showToast({
						    title: '请求异常',
						    duration: 2000
						});
							reject(res.data);
					}
						if(res.data){
							let data = res.data
							let base64 = uni.arrayBufferToBase64(data); //把arraybuffer转成base64
							let toBase64Url = 'data:image/jpeg;base64,' + base64; //不加上这串字符，在页面无法显示
							//console.log('toBase64Url : '+toBase64Url)
							reslove(toBase64Url)
						}
					reslove(res.data)
				},
				fail: (err) => { //调用接口失败的函数
					console.log(err)
					uni.showToast({
					    title: '数据请求失败',
					    duration: 2000
					});
					reject(res.data);
				}
			})
		})
	},
	//拿到图片的信息(宽度等)
	returnImageMetaData(path){
		return new Promise((reslove,reject) => {
			uni.getImageInfo({
			     src: path,
			     success: (res) => {
			         //console.log(image.width);
			         //console.log(image.height);
					 reslove(res)
			     },
				 fail:(err)=>{
				 	console.log('拿到图片信息结果失败 : '+err)
				 	uni.showToast({
				 	    title: '拿到图片信息结果失败',
				 	    duration: 2000
				 	});
				 	reject(err)
				 }
			});
		})
	},
	//通过图片原始名称拿到图片的信息(宽度等)
	returnImageMetaByOriginal(path){
		return new Promise((reslove,reject) => {
			uni.getImageInfo({
			     src: apiRequest.OSS_BASE_URL+path,
			     success: (res) => {
			         //console.log(image.width);
			         //console.log(image.height);
					 reslove(res)
			     },
				 fail:(err)=>{
				 	console.log('拿到图片信息结果失败 : '+err)
				 	uni.showToast({
				 	    title: '拿到图片信息结果失败',
				 	    duration: 2000
				 	});
				 	reject(err)
				 }
			});
		})
	},
	//保存图片到本地
	saveImageToPhotosAlbum(localPath){
		return new Promise((reslove,reject) => {
			uni.saveImageToPhotosAlbum({
					filePath: localPath,
					success:(res)=>{
						console.log('保存图片成功结果 : '+JSON.stringify(res))
						reslove(res)
					}, 
					fail:(err)=>{
						console.log('保存图片失败结果 : '+err)
						uni.showToast({
						    title: '保存图片失败',
						    duration: 2000
						});
						reject(err)
				}
			});
		})
	},
}
export default imageHandler;