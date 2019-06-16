<template>
<div>
  <vxe-table
    ref="xTable"
    border
    resizable
    show-all-overflow
    :data.sync="tableData"
    :edit-config="{key: 'id', trigger: 'manual', mode: 'row'}"
  >
    <vxe-table-column type="index" width="60"></vxe-table-column>
    <vxe-table-column prop="name" label="Name" :edit-render="{name: 'input'}"></vxe-table-column>
    <vxe-table-column prop="sex" label="Sex" :edit-render="{name: 'input'}"></vxe-table-column>
    <vxe-table-column prop="date" label="Date" :edit-render="{name: 'input'}"></vxe-table-column>
    <vxe-table-column prop="address" label="Address" :edit-render="{name: 'input'}"></vxe-table-column>
    <vxe-table-column label="操作">
      <template v-slot="{ row }">
        <template>
          <vxe-button @click="editRowEvent(row)">编辑</vxe-button>
        </template>
      </template>
    </vxe-table-column>
    
  </vxe-table>
</div>
</template>

<script>
import { getAllRequest } from "../utils/api";
export default {
  data() {
    return {
      tableData: []
    };
  },
  //页面初始化时请求数据
  mounted: function() {
    let _this = this;
    var userId = _this.GLOBAL.userId;
    getAllRequest("/getActivities/" + userId).then(resq => {
      if (resq.status == 200) {
        _this.tableData = resq.data.result;
      }
    });
  },
  created() {
    this.tableData = [{ sex: "性别", name: "姓名" }];
  },
  metheds: {
    editRowEvent(row) {
      this.$refs.xTable.setActiveRow(row);
    },
    saveRowEvent(row) {
      console.log("success");
      this.cancelRowEvent();
    },
    cancelRowEvent(row) {
      this.$refs.xTable.clearActived();
    }
  }
};
</script>