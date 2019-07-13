<template>
  <div>
    <div style="width:500px;height:50px;">
      <div>
        <vxe-button
          @click="getInsertEvent"
          style="float:left;  margin-top:10px;display: inline-block;height:32px;"
        >提交</vxe-button>
      </div>
    </div>

    <vxe-table
      resizable
      show-overflow
      :tree-config="{key: 'id', children: 'children'}"
      :edit-config="{trigger: 'click', mode: 'row'}"
      :data.sync="tableData"
      @select-change="selectChangeEvent"
    >
      <!-- <vxe-table-column type="selection" tree-node></vxe-table-column> -->
      <vxe-table-column prop="combinationActName" label="活动组合/活动" tree-node ></vxe-table-column>
      <vxe-table-column prop="prizeLevelName" label="奖品等级"></vxe-table-column>
      <vxe-table-column prop="prizeName" label="奖品名称"></vxe-table-column>
      <vxe-table-column prop="prizeTotal" label="奖品数量"></vxe-table-column>
      <vxe-table-column prop="drawWay" label="抽奖方式" :edit-render="{autofocus: '.custom-input'}">
        <template v-slot:edit="{ row }">
          <select v-model="row.drawWay" placeholder="请选择" @change="deliverValue(row)">
            <option
              v-for="item in selectedAct"
              v-bind:key="item.drawId"
              v-bind:value="item.drawWay"
            >{{item.drawWay}}
            </option>
          </select>
        </template>
      </vxe-table-column>
    </vxe-table>
  </div>
</template>

<script>
import { getAllRequest } from "../utils/api";
import { postRequestJson } from "../utils/api";
export default {
    mounted: function() {
    let _this = this;
    var userId = localStorage.getItem("userId");
    getAllRequest("/getComActPrizes/" + userId).then(resq => {
      if (resq.status == 200 && resq.data.result != null) {
          
        _this.tableData = resq.data.result;
      } else {
        this.$message({ type: "error", message: "查询不到活动详情" });
      }
    });
  }
    ,
  data() {
    return {
      selectedAct: [
        { drawId: 1, drawWay: "1-签到有奖" },
        { drawId: 2, drawWay: "2-幸运抽奖" }
      ],
      drawId: "", //下拉框选择的id
      selectLabel: "", //下拉框显示的值
      tableData:"",
    //    [
    //     {
    //       combinationActName: "name1",
    //       prizeLevelName: " ",
    //       prizeName: " ",
    //       prizeTotal: "",
    //       drawWay: " ",
    //       children: [
    //         {
    //           combinationActName: "name11",
    //           prizeLevelName: 1,
    //           prizeName: "type1",
    //           prizeTotal: 1,
    //           drawWay: "2",
    //         }
    //       ]
    //     }
    //   ]
    };
  },
  inject: ["reload"],
  methods: {
      getInsertEvent() {
      var _this = this;
      var arr =  [];
      var index = 0;
      for(var i = 0; i < _this.tableData.length;i++){
          for(var j = 0; j < _this.tableData[i].children.length;j++){
                arr[index ++] = {id:_this.tableData[i].children[j].id,
                drawWay:_this.tableData[i].children[j].drawWay };
          }
      }
        postRequestJson("/updateDrawWay", arr).then(resq => {
            if (resq.status == 200) {
              if (resq.data.result != undefined && resq.data.result > 0) {
                this.$message.info("提交成功");
              } else {
                this.$message.info("提交失败");
              }
              //刷新 重新请求数据
              this.reload();
            } else {
              alert("系统异常,联系管理员");
            }
          });
    },
    selectChangeEvent({ selection }) {
      console.info(`勾选${selection.length}个树形节点`, selection);
    },
    deliverValue(row) {
    
    }
  },
  
};
</script>

