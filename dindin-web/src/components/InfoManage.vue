<template>
  <div>
    <div style="width:500px ; margin-top:5px;  margin-left:0px;  ">
      <vxe-toolbar>
        <template v-slot:buttons>
          <el-select
            v-model="selectLabel"
            placeholder="请选择"
            @change="deliverValue"
            style="float:left; margin-left:0px; padding-left:0px;margin-bottom:3px; display: inline-block; "
          >
            <el-option
              v-for="item in selectedAct"
              :key="item.actId"
              :label="item.actName"
              :value="item.actId"
            ></el-option>
          </el-select>

          <vxe-button
            @click="exportCsvEvent"
            style="float:left;  margin:0px; margin-left:10px; display: inline-block;height:40px;"
          >默认导出</vxe-button>
        </template>
      </vxe-toolbar>
    </div>
    <div>
      <vxe-table ref="xTable1" highlight-hover-row height="300" :data.sync="tableData" show-overflow>
        <vxe-table-column type="index" width="60"></vxe-table-column>
        <vxe-table-column prop="actName" label="活动名称"></vxe-table-column>
        <vxe-table-column prop="userId" label="用户Id"></vxe-table-column>
        <vxe-table-column prop="prizeLevelName" label="奖品等级" sortable></vxe-table-column>
        <vxe-table-column prop="prizeName" label="奖品名称" show-overflow></vxe-table-column>
        <vxe-table-column prop="gmtCreate" label="抽奖时间"></vxe-table-column>
      </vxe-table>
    </div>
  </div>
</template>
<script>
import { getAllRequest } from "../utils/api";
export default {
  mounted: function() {
    var _this = this;
    var userId = localStorage.getItem("userId");
    getAllRequest("/getPrizes/" + userId).then(resq => {
      if (resq.status == 200) {
        _this.actPrizes = resq.data.result.prizesList;
        _this.selectedAct = resq.data.result.selectOptions;
      }
    });
  },
  data() {
    return {
      selectLabel: "",
      value: "",
      actPrizes: "",
      selectedAct: "",
      tableData: ""
      //   [{ name: "name", sex: "gril", age: 26, address: "江南大道" }]
    };
  },
  created() {},
  methods: {
    deliverValue(vId) {
      this.value = vId;
      let _this = this;
      var actId = this.value;
      for (var i = 0; i < this.selectedAct.length; i++) {
        if (this.selectedAct[i].actId == this.value) {
          this.selectLabel = this.selectedAct[i].actName;
        }
      }
      getAllRequest("/getActUsersPrizes/" + actId).then(resq => {
        if (resq.status == 200) {
          _this.tableData = resq.data.result;
        }
      });
    },
    exportCsvEvent() {
      this.$refs.xTable1.exportCsv();
    }
  }
};
</script>
