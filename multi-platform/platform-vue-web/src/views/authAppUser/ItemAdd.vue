<template>
    <el-dialog title="新增APP认证用户" :center="true" :visible.sync="addVisible">
        <div class="add-body">
            <el-form
                ref="submitForm"
                :model="submitData"
                :rules="validatorRules"
                label-width="130px"
                class="add-body-form"
            >
                <div class="add-body-div">
                    <div class="add-item-view">
                        <el-form-item label="用户名" prop="userName">
                            <el-input
                                v-model="submitData.userName"
                                placeholder="请填写-用户名"
                                maxlength="30"
                                show-word-limit>
                            </el-input>
                        </el-form-item>
                    </div>
                    <div class="add-item-view">
                        <el-form-item label="用户编号" prop="userNumber">
                            <el-input
                                v-model="submitData.userNumber"
                                placeholder="请填写-用户编号"
                                maxlength="30"
                                show-word-limit>
                            </el-input>
                        </el-form-item>
                    </div>
                    <div class="add-item-view">
                        <el-form-item label="手机号码" prop="phoneNumber">
                            <el-input
                                v-model="submitData.phoneNumber"
                                placeholder="请填写-手机号码"
                                maxlength="30"
                                show-word-limit>
                            </el-input>
                        </el-form-item>
                    </div>
                </div>
                <div class="add-body-div">
                    <div class="add-item-view">
                        <el-form-item label="昵称" prop="nickName">
                            <el-input
                                v-model="submitData.nickName"
                                placeholder="请填写-昵称"
                                maxlength="30"
                                show-word-limit>
                            </el-input>
                        </el-form-item>
                    </div>
                    <div class="add-item-view">
                        <el-form-item label="真实姓名" prop="realName">
                            <el-input
                                v-model="submitData.realName"
                                placeholder="请填写-真实姓名"
                                maxlength="30"
                                show-word-limit>
                            </el-input>
                        </el-form-item>
                    </div>
                    <div class="add-item-view">
                        <el-form-item label="性别" prop="gender">
                            <el-select
                                v-model="submitData.gender"
                                :clearable="true"
                                placeholder="请选择性别">
                                <el-option
                                    v-for="item in authUserGenderOptions"
                                    :key="item.value"
                                    :label="item.text"
                                    :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </div>
                </div>
                <div class="add-body-div">
                    <div class="add-item-view">
                        <el-form-item label="明文密码" prop="decryptionPassword">
                            <el-input
                                v-model="submitData.decryptionPassword"
                                placeholder="请填写-明文密码"
                                maxlength="10"
                                show-word-limit>
                            </el-input>
                        </el-form-item>
                    </div>
                    <div class="add-item-view">
                        <el-form-item label="用户状态" prop="authStatus">
                            <el-select
                                v-model="submitData.authStatus"
                                :clearable="true"
                                placeholder="请选择项目状态">
                                <el-option
                                    v-for="item in yesOrNoOptions"
                                    :key="item.value"
                                    :label="item.text"
                                    :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </div>
                </div>
                <div class="add-body-div">
                    <div class="add-item-view">
                        <el-form-item label="OpenID" prop="openId">
                            <el-input
                                type="textarea"
                                rows="5"
                                v-model="submitData.openId"
                                placeholder="请填写-绑定三方平台的OpenID"
                                maxlength="50"
                                show-word-limit>
                            </el-input>
                        </el-form-item>
                    </div>
                </div>
                <el-divider content-position="center">上传头像</el-divider>
                <div class="add-body-div">
                    <div class="add-item-view">
                        <el-form-item label="用户头像图:">
                            <div class="upload-item-view">
                                <UploadImg
                                    class="upload-item"
                                    ref="uploadAvatarUrl"
                                    :uploadApi="uploadUrl"
                                    :fileList="submitData.avatarUrlList"
                                    @subBack="uploadAvatarImageSuccess"
                                    @handleFileDelete="uploadAvatarImageDelete"
                                    :limit="1"
                                    :headers="uploadHeaders"
                                />
                            </div>
                        </el-form-item>
                    </div>
                </div>
            </el-form>
        </div>
        <span slot="footer" class="dialog-footer">
            <el-button
                @click="handleCancel"
                size="mini">
                取 消
            </el-button>
            <el-button
                type="primary"
                @click="handleSubmit"
                size="mini">
                提 交
            </el-button>
        </span>
    </el-dialog>
</template>
<script>
import Api from "@/services";
import baseUtils from '@/utils/baseUtils'
import UploadImg from "@/components/UploadImg";

export default {
    components: {
        UploadImg: UploadImg
    },
    name: "ItemAdd",
    data() {
        return {
            //--------------------------------
            pickerOptions: this.$commonOptions.pickerOptions,
            yesOrNoOptions: this.$commonConstants.yesOrNoOptions,
            timeFormat: 'yyyy-MM-dd HH:mm:ss', //时间格式
            authUserGenderOptions: this.$commonConstants.authUserGenderOptions,
            uploadUrl: process.env.BASE_API + `/api/platformFile/uploadFile`,
            deleteFileUrl: process.env.BASE_API + `/api/platformFile/deleteFileByFileName`,
            uploadHeaders: {
                token: localStorage.getItem('token') || ''
            },
            //--------------------------------
            title: "新增",
            addVisible: false,
            submitData: {
                userName: '',
                userNumber: '',
                phoneNumber: '',
                nickName: '',
                avatarUrl: '',
                realName: '',
                gender: '',
                openId: '',
                decryptionPassword: '',
                password: '',
                authStatus: '',
                avatarUrlList: [],
            },
            validatorRules: {
                userName: [
                    {
                        required: true,
                        message: '请规范填写用户名',
                        trigger: 'blur'
                    }
                ],
                userNumber: [
                    {
                        required: false,
                        message: '请规范填写用户编号',
                        trigger: 'blur'
                    }
                ],
                phoneNumber: [
                    {
                        required: false,
                        message: '请规范填写手机号码',
                        pattern: new RegExp(baseUtils.numberPattern(), "g"),
                        trigger: 'blur'
                    }
                ],
                nickName: [
                    {
                        required: false,
                        message: '请规范填写昵称',
                        trigger: 'blur'
                    }
                ],
                avatarUrl: [
                    {
                        required: false,
                        message: '请规范填写头像',
                        trigger: 'blur'
                    }
                ],
                realName: [
                    {
                        required: false,
                        message: '请规范填写真实姓名',
                        trigger: 'blur'
                    }
                ],
                gender: [
                    {
                        required: false,
                        message: '请选择性别',
                        trigger: 'change'
                    }
                ],
                openId: [
                    {
                        required: false,
                        message: '请规范填写绑定三方平台的OpenID',
                        trigger: 'blur'
                    }
                ],
                decryptionPassword: [
                    {
                        required: false,
                        message: '请规范填写明文密码',
                        trigger: 'blur'
                    }
                ],
                password: [
                    {
                        required: false,
                        message: '请规范填写密码(MD5)',
                        trigger: 'blur'
                    }
                ],
                authStatus: [
                    {
                        required: false,
                        message: '请选择用户状态',
                        trigger: 'change'
                    }
                ],
            }
        };
    },
    methods: {
        // 上传图片回调
        uploadAvatarImageSuccess(fileName, file) {
            if (this.$isNull(fileName)) {
                return
            }
            if (fileName.length === 0) {
                return;
            }
            if (this.$isNull(file)) {
                return
            }
            //随机数
            let uuId = baseUtils.randomString();
            //文件名称
            let fileNameItem = {
                status: 'success',
                name: fileName,
                percentage: 100,
                uid: uuId,
                raw: {
                    uid: uuId
                },
                url: this.handleImageUrl(fileName)
            }
            this.submitData.avatarUrlList.push(fileNameItem);
            this.submitData.avatarUrl = fileName
            console.log('上传成功后的文件集合:' + JSON.stringify(this.submitData.avatarUrlList))
        },
        async uploadAvatarImageDelete(fileName) {
            console.log('删除文件fileName:' + fileName);
            if (this.$isNull(fileName)) {
                return
            }
            const index = this.submitData.avatarUrlList.findIndex((item) => {
                return item.name == fileName
            })
            this.submitData.avatarUrlList.splice(index, 1);
            const res = await Api.deleteFileApi({
                name: fileName
            });
            console.log('删除文件结果:' + JSON.stringify(res))
            if (res.success) {
                console.log('删除成功')
            }
            console.log('删除后的文件集合:' + this.submitData.avatarUrlList);
            this.submitData.avatarUrl = '';
        },
        //处理展示
        showAdd(data) {
            console.log('data:' + JSON.stringify(data))
            if (data) {

            }
            this.init();
            this.addVisible = true;
        },
        async setOtherData() {

        },
        //处理初始化
        async init() {
            await this.setOtherData();
            const loading = this.$loading({
                lock: true,
                text: "正在请求。。。",
                spinner: 'el-icon-loading',
                background: 'rgba(0, 0, 0, 0.8)'
            });
            loading.close();
        },
        clearAll() {
            console.log('触发清除所有')
            this.submitData = {
                userName: '',
                userNumber: '',
                phoneNumber: '',
                nickName: '',
                avatarUrl: '',
                realName: '',
                gender: '',
                openId: '',
                decryptionPassword: '',
                password: '',
                authStatus: '',
                avatarUrlList: [],
            }
            //清除图片
            if (this.$baseUtils.isNull(this.$refs.uploadAvatarUrl)) {
                this.$refs.uploadAvatarUrl.clearFiles()
            }
        },
        handleCancel() {
            this.addVisible = false
            this.clearAll();
        },
        //处理提交
        handleSubmit() {
            this.$refs.submitForm.validate((valid) => {
                const params = {};
                if (valid) {
                    Object.assign(params, this.submitData);
                    console.log("params", params);
                } else {
                    this.$message.error('验证异常');
                    return;
                }
                const loading = this.$loading({
                    lock: true,
                    text: "正在提交...",
                });
                try {
                    Api.addAuthAppUserItem(params).then((res) => {
                        if (res.success) {
                            this.addVisible = false;
                            this.$emit("handleSuccess");
                            this.$message({
                                message: "操作成功!",
                                type: "success",
                            });
                            this.clearAll()
                        } else {
                            this.$message.error('服务器异常');
                        }
                    });
                    loading.close();
                } catch (error) {
                    this.clearAll()
                    loading.close();
                    this.$message.error(error.message || error.msg || "服务器异常");
                }
            });
        },
    },
};
</script>
<style scoped lang="scss">
.add-body {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-content: center;
  align-items: center;

  .add-body-form {
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-content: center;
    align-items: flex-start;

    .text-area-view {
      width: 100%;

      .add-item-view {

      }
    }

    .add-body-div {
      display: flex;
      flex-direction: row;
      justify-content: flex-start;
      align-content: center;
      align-items: center;
      width: auto;

      .add-item-view {

      }

      .upload-item-view {
        .upload-item {

        }
      }
    }
  }
}
</style>
