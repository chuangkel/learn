<template>
  <el-row v-loading="loading">
    <el-col :span="24">
      <div style="text-align: left;">
        <el-button type="text" icon="el-icon-back" @click="goBack" style="padding-bottom: 0px;">返回</el-button>
      </div>
      <div>
        <div style="width:700px;height:50px;">
          <label style="float:left; margin-left:3px;margin-top:10px;padding-bottom: 10px; ">当前活动：</label>
          <el-select
            v-model="selectLabel"
            placeholder="请选择"
            @change="deliverValue"
            style="float:left; margin-left:3px; margin-bottom:3px; display: inline-block; "
          >
            <el-option
              v-for="item in selectedAct"
              :key="item.actId"
              :label="item.actName"
              :value="item.actId"
            ></el-option>
          </el-select>
          <vxe-button
            @click="$refs.xTable.insertAt({actName: ''}, -1)"
            style="float:left;margin-left:3px; display: inline-block;height:40px; "
          >新增</vxe-button>
          <vxe-button
            @click="getRemoveEvent"
            style="float:left; margin-left:3px; display: inline-block;height:40px;"
          >删除</vxe-button>
          <vxe-button
            @click="getInsertEvent"
            style="float:left; margin-left:3px; display: inline-block;height:40px;"
          >提交</vxe-button>
          <a-upload
          style="float:left; margin-left:3px; display: inline-block;"
          :showUploadList= "false"
          action="https://www.mocky.io/v2/5cc8019d300000980a055e76" @change="handleChange">
            <a-button style="height:40px;">
              <a-icon type="upload" /> 上传图片
            </a-button>
          </a-upload>
        </div>
        <div>
          <vxe-table
            highlight-hover-row
            height="300"
            show-all-overflow
            ref="xTable"
            border
            @select-change="selectchange"
            :data.sync="tableData"
            :edit-rules="validRules"
            :edit-config="{key: 'id', trigger: 'click', mode: 'cell'}"
          >
            <vxe-table-column type="radio" width="60"></vxe-table-column>
            <vxe-table-column prop="prizeLevel" label="奖品等级" :edit-render="{name: 'input'}"></vxe-table-column>
            <vxe-table-column prop="prizeLevelName" label="等级名称" :edit-render="{name: 'input'}"></vxe-table-column>
            <vxe-table-column prop="prizeName" label="奖品名称" :edit-render="{name: 'input'}"></vxe-table-column>
            <vxe-table-column prop="prizeTotal" label="总数量" :edit-render="{name: 'input'}"></vxe-table-column>
            <vxe-table-column prop="prizePic" label="奖品图片" :edit-render="{name: 'input'}" ></vxe-table-column>
          </vxe-table>
        </div>
      </div>
    </el-col>
  </el-row>
</template>

<script>
import { postRequestJson } from "../utils/api";
import { getAllRequest } from "../utils/api";
import { uploadFileRequest } from "../utils/api";
import global_ from '../utils/Global'
let base = global_.base; 
function getBase64(img, callback) {
  const reader = new FileReader();
  reader.addEventListener("load", () => callback(reader.result));
  reader.readAsDataURL(img);
}
export default {
  mounted: function() {
    var _this = this;
    var userId = localStorage.getItem("userId");
    getAllRequest("/getPrizes/" + userId).then(resq => {
      if (resq.status == 200) {
        _this.actPrizes = resq.data.result.prizesList;
        _this.selectedAct = resq.data.result.selectOptions;
        //刷新的时候 恢复刷新前的下拉选择
        let selectedActId = localStorage.getItem("selectedActId");
        if (selectedActId != undefined && selectedActId != null && selectedActId != "") {
          //先把表格恢复 需之前下拉框的value 而不是label
          this.deliverValue(selectedActId);
        }
      }
    });
    if(this.tableData != null && this.tableData.length > 0){
      this.$refs.xTable.setCurrentRow(tableData[0]);
    }
  },
  data() {
    return {
      current:"",//当前row
      currentRow:"",//当前row id
      previewVisible: false,
      previewImage: '',
      loading: false,
      actPrizes: "",
      selectedAct: "",
      value: "", //下拉框选择的id
      selectLabel: "", //下拉框显示的值
      tableData: "",
      validRules: {
        prizeLevel: [{ required: true, message: "奖品等级必须填写" }],
        prizeLevelName: [{ min: 3, max: 6, message: "奖品等级名称3-6个字符" }],
        prizeName: [{ min: 3, max: 15, message: "奖品名称3-15个字符" }],
        prizeTotal: [{ required: true, message: "奖品总数量必须填写" }]
      },
       fileList:
       [{
        uid: '-1',
        name: 'xxx.png',
        status: 'done',
        url: 'http://www.baidu.com/xxx.png',
      }]
      ,
    };
  },
  inject: ["reload"],
  created() {},
  methods: {
    beforeUpload(file) {
      const isJPG = file.type === "image/jpeg";
      if (!isJPG) {
        this.$message.error("只能上传jpg文件");
      }
      const isLt2M = file.size / 1024 / 1024 < 4;
      if (!isLt2M) {
        this.$message.error("图片需要小于4MB!");
      }
      var _this = this;
      // 第一步.将图片上传到服务器.
      var formdata = new FormData();
      formdata.append("image", file);
      uploadFileRequest("/activity/uploadimg", formdata).then(resp => {
        var json = resp.data;
        var fileName = file.name;
        if (resp.status == 200 && json.result == "success") {
          _this.fileList[0].url =
            localStorage.getItem("base") + "/image/" + fileName;
          this.$message.success(`${file.name} 上传成功`);
        } else {
          this.$message.error(`${file.name} 上传失败`);
        }
      });
      return isJPG && isLt2M;
    },
    
    selectchange(row){
      this.currentRow = row.row.id;
      this.current = row.row;
    },
    handleChange({ fileList }) {
      if(this.currentRow == undefined || this.currentRow == ""){
        this.$message({type: 'error', message: '请选择一行!'});
        return;
      }
      this.fileList = fileList.slice(-1);
      for(var i = 0;i < this.tableData.length;i ++){
        if(this.tableData[i].id == this.currentRow){
          this.tableData[i].prizePic = base + "/image/"+ this.fileList[0].name;
          break;
        }
      }
    },
    fullValidEvent() {
      var _this = this;
      this.$refs.xTable.fullValidate((valid, errMap) => {
        if (valid) {
          // this.$XMsg.alert("校验成功！");
          let updateRecords = this.$refs.xTable.getUpdateRecords();
          let insertRecords = this.$refs.xTable.getInsertRecords();
          if (updateRecords.length != 0 || insertRecords.length != 0) {
          } else {
            this.$message.info("没有修改数据喔");
            return;
          }
          //数据库生成id 将id置null
          for (var i = 0; i < insertRecords.length; i++) {
            insertRecords[i].id = null;
            insertRecords[i].actId = _this.value;
            // insertRecords[i].prizeLef
          }
          //合并 更新和插入的数据
          let arr = updateRecords.concat(insertRecords);
          postRequestJson("/insertPrizes", arr).then(resq => {
            if (resq.status == 200) {
              if (resq.data.result != undefined && resq.data.result > 0) {
                this.$message.info("提交成功");
              } else {
                this.$message.info("提交失败");
              }
              //刷新 重新请求数据
              localStorage.setItem("selectedActId", _this.value);
              this.reload();
            } else {
              alert("系统异常,联系管理员");
            }
          });
        } else {
          let msgList = [];
          Object.values(errMap).forEach(errList => {
            errList.forEach(params => {
              let { rowIndex, column, rules } = params;
              rules.forEach(rule => {
                msgList.push(
                  `第 ${rowIndex} 行 ${column.label} 校验错误：${rule.message}`
                );
              });
            });
          });
          this.$XMsg.alert({
            message: () => {
              return [
                <div class="red" style="max-height: 400px;overflow: auto;">
                  {msgList.map(msg => {
                    return <p>{msg}</p>;
                  })}
                </div>
              ];
            }
          });
        }
      });
    },
    deliverValue(vId) {
      this.value = vId;
      localStorage.setItem("selectedActId", this.value);
      //恢复label为actName
      for (var i = 0; i < this.selectedAct.length; i++) {
        if (this.selectedAct[i].actId == this.value) {
          this.selectLabel = this.selectedAct[i].actName;
        }
      }
      var arr = new Array();
      for (var i = 0; i < this.actPrizes.length; i++) {
        if (this.actPrizes[i].actId == this.value) {
          arr.push(this.actPrizes[i]);
        }
      }
      //未新增过奖品 置""
      if (arr.length == 0) {
        this.tableData = "";
      } else {
        this.tableData = arr;
      }
    },
    goBack() {
      this.$router.go(-1);
    },
    insertEvent() {
      let record = {
        name: Date.now()
      };

      this.$refs.xTable
        .insertAt(record, this.tableData[2])
        .then(({ row }) => this.$refs.xTable.setActiveCell(row, "sex"));
    },
    //奖励新增
    getInsertEvent() {
      this.fullValidEvent();
    },
    //删除选择
    getRemoveEvent() {
      //执行删除
      // this.$refs.xTable.removeSelecteds();
      this.$refs.xTable.remove(this.current)
      //获取删除数据
      let removeRecords = this.$refs.xTable.getRemoveRecords();
      var _this = this;
      postRequestJson("/deletePrizes", removeRecords).then(resq => {
        if (resq.status == 200) {
          if (resq.data.result != undefined && resq.data.result > 0) {
            this.$message.info("删除成功");
          } else {
            this.$message.info("删除失败");
          }
          //刷新 重新请求数据
          localStorage.setItem("selectedActId", _this.value);
          this.reload();
        } else {
          alert("系统异常,联系管理员");
        }
      });
    }
  }
};
</script>

<style>
  /* you can make up upload button and sample style by using stylesheets */
  .ant-upload-select-picture-card i {
    margin-left:3px; display: inline-block;
    font-size: 32px;
    color: #999;
  }

  .ant-upload-select-picture-card .ant-upload-text {
     margin-left:3px; display: inline-block;
    margin-top: 8px;
    color:red;
  }
</style>