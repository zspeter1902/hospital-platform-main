<template>
  <el-upload
    accept=".xlsx, .xls"
    class="upload-demo"
    :action="uploadApi"
    :on-preview="handlePreview"
    :on-remove="handleRemove"
    :before-remove="beforeRemove"
    :on-exceed="handleExceed"
    :file-list="fileList"
    :limit="1"
    :data="upLoadParams"
    :on-success="onSuccess"
    :on-error="onError"
    :auto-upload="false"
    :on-change="handleChange"
    ref="upload"
    :headers="upLoadHeaders"
  >
    <el-button size="mini" type="primary">点击上传</el-button>
    <div slot="tip" class="el-upload__tip" :style="{color: '#f56c6c'}" v-if="imgList.length == 0">请上传Excel文件</div>
  </el-upload>
</template>
<script>
export default {
  props: {
    uploadApi: {
      required: true,
    },
    fileList: {
      type: Array,
      required: false,
      default: [],
    },
    upLoadParams: {
      type: Object,
      required: false,
      default: {},
    },
  },
  data() {
    return {
      type: 1, //没用的
      imgList: []
    };
  },
  computed: {
      upLoadHeaders() {
         const token = localStorage.getItem('token') || '';
          return {
            token: token,
          }
      }
  },
  methods: {
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
      console.log(this.fileList);
    },
    handleExceed(files, fileList) {
       console.log(files);
      this.$message.warning("只能上传一个文件");
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },
    onError(err, file, fileList) {
      this.$message.warning(err.message);
    },
    onSuccess(response, file, fileList) {
       this.$emit('subBack', response);
    },
    submitUpload() {
        this.$refs.upload.submit();
    },
    handleChange(file, fileList) {
       this.imgList = fileList;
       console.log(this.imgList)
    }
  },
};
</script>
