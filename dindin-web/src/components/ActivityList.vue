<template>
  <div>
    <div>
      <vxe-table
        ref="xTable"
        border
        resizable
        show-overflow
        :data.sync="tableData"
        :edit-config="{key: 'id', trigger: 'manual', mode: 'row' ,autoClear: false}"
      >
         <vxe-table-column type="index" width="60"></vxe-table-column>
        <vxe-table-column prop="actName" label="活动名称" :edit-render="{name: 'input'}"></vxe-table-column>
        <vxe-table-column prop="actAddress" label="活动地址" :edit-render="{name: 'input'}"></vxe-table-column>
        <vxe-table-column prop="actTime" label="开始时间" :edit-render="{name: 'input'}"></vxe-table-column>
        <vxe-table-column prop="actEndtime" label="结束时间" :edit-render="{name: 'input'}"></vxe-table-column>
        <vxe-table-column label="操作">
          <template v-slot="{ row }">
            <template v-if="$refs.xTable.hasActiveRow(row)">
              <vxe-button @click="saveRowEvent(row)">删除</vxe-button>
              <vxe-button @click="cancelRowEvent(row)">编辑</vxe-button>
            </template>
            <template v-else>
              <vxe-button @click=editRowEvent(row)>展开</vxe-button>
            </template>
          </template>
        </vxe-table-column>
      </vxe-table>
    </div>
  </div>
</template>

<script>
import { getAllRequest } from "../utils/api";
import hljs from 'highlight.js'

export default {
  data() {
    return {
      tableData: []
    };
  },
  //页面初始化时请求数据
  mounted: function() {
    let _this = this;
    var userId = localStorage.getItem("userId");
    getAllRequest("/getActivities/" + userId).then(resq => {
      if (resq.status == 200) {
        _this.tableData = resq.data.result;
      }
    });
  },
  created() {},
  metheds: {
    editRowEvent:function(row) {
      debugger;
      this.$refs.xTable.setActiveRow(row);
    },
    saveRowEvent(row) {
      debugger;
      this.$XMsg.alert("success");
      this.cancelRowEvent();
    },
    cancelRowEvent(row) {
      this.$refs.xTable.clearActived();
    }
  }
};
</script>