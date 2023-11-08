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
    :headers="headers"
    ref="excelUploader"
  >
    <el-button type="primary" size="mini">{{buttonText}}</el-button>
  </el-upload>
</template>
<script>
export default {
  name:"UploadExcel",
  props: {
    buttonText:{
      type: String,
      required: true,
      default: '上传Excel',
    },
    headers:{
      token:''
    },
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
    };
  },
  methods: {
    clearFiles(){
      console.log('清除图片')
      this.$refs.excelUploader.clearFiles()
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
      this.$emit('handleRemove', file);
    },
    handlePreview(file) {
      console.log(file);
      console.log(this.fileList);
    },
    handleExceed(files, fileList) {
      this.$message.warning("只能上传一个文件");
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },
    onError(err, file, fileList) {
      this.$message.warning(err.message);
    },
    onSuccess(response, file, fileList) {
      let success = response.success
      let message = response.message
      if(success === true){
        this.$message.success("导入"+file.name+"成功");
      }else{
        this.$message.error("导入"+file.name+"失败:"+message);
      }
      console.log('导入Excel完成:'+JSON.stringify(file))
      this.$emit('onSuccess', response);
    },
  },
};
</script>
