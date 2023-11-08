<script>
	export default {
		onLaunch: function() {
		},
		onShow: function() {
			console.log(uni.$u.config.v);
		},
		onHide: function() {
			
		}
	}
</script>

<style lang="scss">
	/*每个页面公共css */
	@import "@/components/uview-ui/index.scss";
	::-webkit-scrollbar {  
	    display: none;  
	    width: 0 !important;  
	    height: 0 !important;  
	    -webkit-appearance: none;  
	    background: transparent;  
	}
</style>
