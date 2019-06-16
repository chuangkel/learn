<template>
  <el-row v-loading="loading">
    <el-col :span="24">
      <div style="text-align: left;">
        <el-button type="text" icon="el-icon-back" @click="goBack" style="padding-bottom: 0px;">返回</el-button>
      </div>
      <div>
        <div class="button_div">
          <el-select v-model="value" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
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
            <vxe-table-column prop="actId" label="所属活动" :edit-render="{name: 'input'}" width="300"></vxe-table-column>
            <vxe-table-column
              prop="prizeLevel"
              label="奖品等级"
              :edit-render="{name: 'input'}"
              width="300"
            ></vxe-table-column>
            <vxe-table-column
              prop="prizeName"
              label="奖品名称"
              :edit-render="{name: 'input'}"
              width="300"
            ></vxe-table-column>
            <vxe-table-column
              prop="prizeTotal"
              label="总数量"
              :edit-render="{name: 'input'}"
              width="300"
            ></vxe-table-column>
          </vxe-table>
        </div>
      </div>
    </el-col>
  </el-row>
</template>

<script>
import { postRequestJson } from "../utils/api";
export default {
  mounted: function() {
    var _this = this;
    // this.loading = true;
  },
  data() {
    return {
      loading: false,
      options: [
        {
          value: "选项1",
          label: "黄金糕"
        },
        {
          value: "选项2",
          label: "双皮奶"
        },
        {
          value: "选项3",
          label: "蚵仔煎"
        },
        {
          value: "选项4",
          label: "龙须面"
        },
        {
          value: "选项5",
          label: "北京烤鸭"
        }
      ],
      value: "",
      tableData: [],
      validRules: {
        actName: [
          { required: true, message: "活动名称必须填写" }
          // { min: 3, max: 50, message: '名称长度在 3 到 50 个字符' }
        ],
        prize_level: [{ required: true, message: "奖品等级必须填写" }]
      }
    };
  },
  created() {
    // this.tableData = {name:"aaa"};
  },
  methods: {
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
      // alert(insertRecords.length);
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
