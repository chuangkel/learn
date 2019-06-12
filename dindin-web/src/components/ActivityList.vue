<template>
  <el-table :data="tableData">
    <el-table-column
      prop="actTime"
      label="活动时间"
      format="yyyy-MM-dd">
    </el-table-column>
    <el-table-column
      prop="actName"
      label="活动简介">
    </el-table-column>
    <el-table-column
      prop="actContent"
      label="活动内容">
    </el-table-column>
    <el-table-column
      prop="actUptime"
      label="上架时间"
      format="yyyy-MM-dd">
    </el-table-column>
    <el-table-column
      prop="actDowntime"
      label="下架时间"
      format="yyyy-MM-dd">
    </el-table-column>
  </el-table>
</template>

<script>
import { getAllRequest } from '../utils/api';
export default {
  data () {
      return {
        tableData: []
      }
  },
  //页面初始化时请求数据
   mounted: function () {
      let _this = this;
      var userId = 0;
      getAllRequest('/getActivities/'+userId).then(resq=>{
        if (resq.status == 200) {
            _this.tableData = resq.data.data;
        }
      })
    },
  metheds:{
  },
}
</script>