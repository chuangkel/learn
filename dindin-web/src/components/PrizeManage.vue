<template>
  <el-row v-loading="loading">
    <el-col :span="24">
      <div style="text-align: left;">
        <el-button type="text" icon="el-icon-back" @click="goBack" style="padding-bottom: 0px;">返回</el-button>
      </div>
      <div>
        <div class="button_div">
          <el-select v-model="value" placeholder="请选择" @change="deliverValue">
            <el-option
              v-for="item in actPrizes"
              :key="item.actId"
              :label="item.actName"
              :value="item.actId"
            ></el-option>
          </el-select>

          <vxe-button @click="$refs.xTable.insertAt({actName: ''}, -1)">新增</vxe-button>
          <vxe-button @click="getInsertEvent">提交</vxe-button>
        </div>
        <div>
          <vxe-table
            highlight-hover-row
            height="300"
            resize
            show-all-overflow
            ref="xTable"
            border
            :data.sync="tableData"
            :edit-rules="validRules"
            :edit-config="{key: 'id', trigger: 'click', mode: 'cell'}"
          >
            <vxe-table-column type="selection" width="60"></vxe-table-column>
            <vxe-table-column prop="id" label="活动id" :edit-render="{name: 'input'}" width="100"></vxe-table-column>
            <vxe-table-column prop="actId" label="所属活动" :edit-render="{name: 'input'}" width="100"></vxe-table-column>
            <vxe-table-column
              prop="actName"
              label="活动名称"
              :edit-render="{name: 'input'}"
              width="100"
            ></vxe-table-column>
            <vxe-table-column
              prop="prizeLevel"
              label="奖品等级"
              :edit-render="{name: 'input'}"
              width="100"
            ></vxe-table-column>
            <vxe-table-column
              prop="prizeLevelName"
              label="等级名称"
              :edit-render="{name: 'input'}"
              width="100"
            ></vxe-table-column>
            <vxe-table-column
              prop="prizeName"
              label="奖品名称"
              :edit-render="{name: 'input'}"
              width="100"
            ></vxe-table-column>
            <vxe-table-column
              prop="prizeTotal"
              label="总数量"
              :edit-render="{name: 'input'}"
              width="100"
            ></vxe-table-column>
            <vxe-table-column
              prop="prizeLeft"
              label="奖品剩余"
              :edit-render="{name: 'input'}"
              width="100"
            ></vxe-table-column>
            <vxe-table-column
              prop="gmtCreate"
              label="创建时间"
              :edit-render="{name: 'input'}"
              width="100"
            ></vxe-table-column>
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
      }
    });
  },
  data() {
    return {
      loading: false,
      actPrizes:"",
      selectedAct:"",
      value: "",
      tableData: [[]],
      validRules: {
        actName: [
          { required: true, message: "活动名称必须填写" }
        ],
        prize_level: [{ required: true, message: "奖品等级必须填写" }]
      }
    };
  },
  created() {
  },
  methods: {
    deliverValue(){
      var arr = new Array();
      for(var i = 0; i < this.actPrizes.length; i++){
        if(this.value == this.actPrizes[i].actId){
            arr.push(this.actPrizes[i]);
        }
      }
      this.tableData = arr;
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
    getInsertEvent() {
      let insertRecords = this.$refs.xTable.getInsertRecords();
      postRequestJson("/insertPrizes", insertRecords).then(resq => {
        if (resq.status == 200) {
          if (resq.data.data == "success") {
            // alert("保存成功");
          } else {
            // alert("保存失败")
          }
        } else {
          alert("系统异常,联系管理员");
        }
      });
    }
  }
};
</script>
<style>
.button_div {
  left: 0%;
}
</style>
