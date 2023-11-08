<template>
    <div id="app">
        <router-view></router-view>
    </div>
</template>

<script>
    import MixinWx from '@/mixin/authUser'
    import moment from "moment";
    import watermark from "watermark-dom";
    import store from '@/store'
    export default{
      name: 'APP',
      mounted(){

      },
      mixins: [ MixinWx ],
    }
</script>

<style lang="scss">
    @import './styles/index.scss'; // 全局自定义的css样式

    .area-select.medium {
        width: 142px;
        margin-left: 0px !important;
        line-height: 32px;
    }
    .area-select .area-selected-trigger{
        padding-top: 0px !important;
    }
</style>
