<template>
    <div v-if="mobile">
        <el-button size="mini" type="primary" @click="callUser(mobile, 1)">{{mobile}}</el-button>
        <el-button size="mini" type="primary" @click="callUser(mobile, 0)">外地电话</el-button>
    </div>
</template>

<script>
    import Api from "@/services";
    import axios from 'axios';

	export default {
	  name: 'callUserByMobile',
      props: {
	      mobile: String
      },
	  data() {
	    return {
	    //   mobile: this.props.mobile
        };
      },
      methods: {
        callUser: function(_mobile, _local) {
          this.$confirm("是否呼出电话?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
            })
            .then(() => {
              Api.telToUserByMobile({ mobile: _mobile, local: _local })
                .then(rep => {
                  if(!rep.data) {
                    this.$message({
                      message: "操作失败!",
                      type: "error"
                    });
                    return;
                  }
                  // axios.get(rep.data.telUrl)
                  window.open(rep.data.telUrl)
                  // .then(rep => {})
                  // .catch(error => {});

                  this.$message({
                    message: "操作成功!",
                    type: "success"
                  });
                })
                .catch(error => {
                  this.loading = false;
                  this.$message({
                    message: error.info,
                     type: "error"
                  });
                });
            })
            .catch(() => {});
        }
      }
	};
</script>
