<template>
  <el-row v-loading="loading">
    <el-col :span="24">
      <div style="text-align: left;">
        <el-button type="text" icon="el-icon-back" @click="goBack" style="padding-bottom: 0px;">返回</el-button>
      </div>
      <div>
        <div style="width:500px;height:50px;">
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
            @click="getInsertEvent"
            style="float:left; margin-left:3px; display: inline-block;height:40px;"
          >提交</vxe-button>
          <vxe-button
            @click="getRemoveEvent"
            style="float:left; margin-left:3px; display: inline-block;height:40px;"
          >删除</vxe-button>
        </div>
        <div>
          <vxe-table
            highlight-hover-row
            height="300"
            show-all-overflow
            ref="xTable"
            border
            :data.sync="tableData"
            :edit-rules="validRules"
            :edit-config="{key: 'id', trigger: 'click', mode: 'cell'}"
          >
            <vxe-table-column type="selection" width="60"></vxe-table-column>
            <vxe-table-column prop="prizeLevel" label="奖品等级" :edit-render="{name: 'input'}"></vxe-table-column>
            <vxe-table-column prop="prizeLevelName" label="等级名称" :edit-render="{name: 'input'}"></vxe-table-column>
            <vxe-table-column prop="prizeName" label="奖品名称" :edit-render="{name: 'input'}"></vxe-table-column>
            <vxe-table-column prop="prizeTotal" label="总数量" :edit-render="{name: 'input'}"></vxe-table-column>
          </vxe-table>
        </div>
      </div>
    </el-col>
  </el-row>
</template>

<script>
import { postRequestJson } from "../utils/api";
import { getAllRequest } from "../utils/api";
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
  },
  data() {
    return {
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
      }
    };
  },
  inject: ["reload"],
  created() {},
  methods: {
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
      this.$refs.xTable.removeSelecteds();
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

