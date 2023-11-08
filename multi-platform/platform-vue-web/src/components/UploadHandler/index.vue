<template>
  <div class="upload-view">
    <el-upload
      class="uploader-item-view"
      :accept="accept"
      :action="uploadApi"
      :on-remove="handleRemove"
      :before-remove="beforeRemove"
      :on-exceed="handleExceed"
      :file-list="fileList"
      :limit="limit"
      :on-success="onSuccess"
      :on-error="onError"
      :headers="headers"
      ref="uploader"
      drag
    >
      <div class="icon-view">
        <i class="el-icon-upload"></i>
      </div>
      <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
    </el-upload>
  </div>
</template>
<script>
export default {
  props: {
    uploadApi: {
      required: true,
    },
    headers:{
      token:''
    },
    accept:{
      type: String,
      required: false,
      default: () => {
        return '';
      },
    },
    fileList: {
      type: Array,
      required: false,
      default: () => [],
    },
    limit: {
      type: Number,
      required: false,
      default: 1,
    },
  },
  data() {
    return {

    };
  },
  methods: {
    clearFiles(){
      console.log('清除图片')
      this.$refs.uploader.clearFiles()
    },
    handleRemove(file, fileList) {
      console.log('开始文件删除:'+JSON.stringify(file))
      if(!this.$isNull(file)){
        let name = file.name
        this.$emit("handleFileDelete", name);
      }
      return;
    },
    onSuccess(response, file, fileList) {
      if(!this.$isNull(response)) {
        let data = response.data
        if(!this.$isNull(data)){
          this.$emit("subBack", data.fileName,file);
        }
      }
      return;
    },
    handleExceed(files, fileList) {
      this.$message.error("最多能上传" + this.limit + "文件");
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },
    onError(err, file, fileList) {
      this.$message.error(err.message);
    },
  },
};
</script>
<style scoped lang="scss">
  .upload-view{
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-content: center;
    align-items: center;
    .uploader-item-view{
      //border: 2px solid #d70a11;
      display: flex;
      flex-direction: row;
      justify-content: flex-start;
      align-content: center;
      align-items: flex-start;
      .icon-view{
        //border: 2px solid #5cd053;
      }
    }
  }
</style>
