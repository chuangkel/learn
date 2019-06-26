<template>
  <el-row v-loading="loading">
    <el-col :span="24">
      <div style="text-align: left; width:500px;height:50px; ">
        <el-button
          type="text"
          icon="el-icon-back"
          @click="goBack"
          style="padding-bottom: 0px; float:left; margin-left:3px; display: inline-block;height:40px;"
        >返回</el-button>
        <vxe-button
          @click="getRemoveEvent"
          style="float:left; margin-left:10px; margin-top:10px; display: inline-block;height:32px;"
        >选择删除</vxe-button>
      </div>
      <div>
        <!-- <div style="width:500px;height:50px;">
          
        </div>-->
        <div>
          <vxe-table
            highlight-hover-row
            height="300"
            show-all-overflow
            ref="xTable"
            border
            :data.sync="tableData"
            :edit-config="{key: 'id', trigger: 'manual', mode: 'row'}"
          >
            <vxe-table-column type="selection" width="60"></vxe-table-column>
            <vxe-table-column prop="actName" label="活动名称" :edit-render="{name: 'input'}"></vxe-table-column>
            <vxe-table-column prop="actAddress" label="活动地址" :edit-render="{name: 'input'}"></vxe-table-column>
            <vxe-table-column prop="actTime" label="开始时间" :edit-render="{name: 'input'}"></vxe-table-column>
            <vxe-table-column prop="actEndtime" label="结束时间" :edit-render="{name: 'input'}"></vxe-table-column>
            <vxe-table-column label="操作">
              <template v-slot="{ row }">
                <vxe-button @click="editRowEvent(row)">活动编辑</vxe-button>
              </template>
            </vxe-table-column>
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
  data() {
    return {
      loading: false,
      tableData: ""
    };
  },
  inject: ["reload"],
  created() {},
  methods: {
    goBack() {
      this.$router.go(-1);
    },
    editRowEvent(row) {
      // this.$refs.xTable.setActiveRow(row);
      this.$router.push({path: '/PostArticle', params: { activity:row } ,query: {selected: row}})
    },
    //删除选择
    getRemoveEvent() {
      //执行删除
      this.$refs.xTable.removeSelecteds();
      //获取删除数据
      let removeRecords = this.$refs.xTable.getRemoveRecords();
      var _this = this;
      postRequestJson("/delectActivity", removeRecords).then(resq => {
        if (resq.status == 200) {
          if (resq.data.result != undefined && resq.data.result > 0) {
            this.$message.info("删除成功");
          } else {
            this.$message.info("删除失败");
          }
          this.reload();
        } else {
          alert("系统异常,联系管理员");
        }
      });
    }
  }
};
</script>

