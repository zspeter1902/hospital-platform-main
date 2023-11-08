<template>
    <el-dialog title="大图浏览" :visible.sync="show">
        <div class="dialog-body">
            <div class="img-outer" id="bigImgOuter" :style="{height: params.outerHeight + 'px'}">
                <img class="big-img" :src="url" :style="{
                transform: 'rotate(' + deg + 'deg)',
                'margin-top': params.mt + 'px',
                'margin-left': params.ml,
                'max-width': params.maxWidth,
            }"/>
            </div>
        </div>
        <span slot="footer" class="dialog-footer">
            <el-button type="primary" @click="rotateImg(90)">顺时针旋转</el-button>
            <el-button type="primary" @click="rotateImg(-90)">逆时针旋转</el-button>
            <el-button @click="show = false">关 闭</el-button>
        </span>
    </el-dialog>
</template>

<script>
    export default {
        components: {},
        data() {
            return {
                name: '',
                show: false,
                url: '',
                deg: 0,
                naturalHeight: null,
                naturalWidth: null,
                r: 1,
                maxWidth: null
            }
        },
        computed: {
            params() {
                const data = {};
                if (this.deg % 180 === 0) {
                    data.mt = 0;
                    data.ml = null;
                    data.maxWidth = '100%';
                    data.outerHeight = this.naturalHeight * this.r;
                } else {
                    const maxWidth = (100 * this.naturalWidth / this.naturalHeight).toFixed(2);
                    data.maxWidth = maxWidth + '%';
                    const mt = (this.naturalWidth - this.naturalHeight) * this.r / 2;
                    data.mt = this.naturalWidth < this.naturalHeight ? mt * maxWidth / 100 : mt;
                    data.ml = this.naturalWidth < this.naturalHeight ? (100 - maxWidth) / 2 + '%' : null;
                    const outerHeight = this.naturalWidth * this.r
                    data.outerHeight = this.naturalWidth < this.naturalHeight ? outerHeight * maxWidth / 100 : outerHeight;
                }
                return data;
            },
            mt() {
                if (this.deg % 180 === 0) {
                    return 0
                } else {
                    return (this.naturalWidth - this.naturalHeight) * this.r / 2
                }
            },
            ml() {
                if (this.deg % 180 === 0) {
                    this.maxWidth = '100%';
                    return null
                } else if (this.naturalWidth < this.naturalHeight) {
                    const maxWidth = (100 * this.naturalWidth / this.naturalHeight).toFixed(2);
                    this.maxWidth = maxWidth + '%';
                    return (100 - maxWidth) / 2 + '%';
                }
                return null
            },
            outerHeight() {
                if (this.deg % 180 === 0) {
                    this.maxWidth = '100%';
                    return this.naturalHeight * this.r
                } else {
                    const maxWidth = (100 * this.naturalWidth / this.naturalHeight).toFixed(2);
                    this.maxWidth = maxWidth + '%';
                    return this.naturalWidth * this.r
                }
            }
        },
        methods: {
            rotateImg(a) {
                let deg = this.deg + a;
                if (deg >= 360) {
                    deg -= 360;
                } else if (deg < 0) {
                    deg += 360;
                }
                this.deg = deg;
            },
            showImgDialog(url) {
                this.deg = 0;
                this.mt = 0;
                this.url = url;
                this.r = 1;
                this.show = true;

                const img = new Image();
                img.src = url;
                const that = this;
                img.onload = function() {
                    that.naturalHeight = img.naturalHeight;
                    that.naturalWidth = img.naturalWidth;

                    console.log('natural', img.naturalWidth, img.naturalHeight)

                    const div = document.getElementById('bigImgOuter');
                    let r = 1;
                    if (div) {
                        r = div.offsetWidth / this.naturalWidth;
                    }
                    that.r = r;
                    console.log('r', that.r)
                }
            }
        }
    }
</script>

<style scoped lang="scss">
    .el-dialog{
        margin-bottom: 10%;
    }
    .img-outer {
        position: relative;
        width: 100%;
    }

    .big-img {
        width: 100%;
    }
</style>