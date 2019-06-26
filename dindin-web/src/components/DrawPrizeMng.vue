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
      :tree-config="{key: 'id', children: 'children'}"
      :edit-config="{trigger: 'click', mode: 'row'}"
      :data.sync="tableData"
      @select-change="selectChangeEvent"
    >
      <!-- <vxe-table-column type="selection" tree-node></vxe-table-column> -->
      <vxe-table-column prop="combinationActName" label="活动组合" tree-node></vxe-table-column>
      <vxe-table-column prop="prizeLevelName" label="奖品等级"></vxe-table-column>
      <vxe-table-column prop="prizeName" label="奖品名称"></vxe-table-column>
      <vxe-table-column prop="prizeTotal" label="奖品数量"></vxe-table-column>
      <vxe-table-column prop="drawWay" label="抽奖方式" :edit-render="{autofocus: '.custom-input'}">
        <template v-slot:edit="{ row }">
          <select v-model="row.drawWay" placeholder="请选择" @change="deliverValue(row)">
            <option
              v-for="item in selectedAct"
              :key="item.drawId"
              :label="item.drawName"
              v-bind:value="item.drawName"
            ></option>
          </select>
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
      selectedAct: [
        { drawId: 1, drawName: "aa" },
        { drawId: 2, drawName: "aaaaa" }
      ],
      drawId: "", //下拉框选择的id
      selectLabel: "", //下拉框显示的值
      tableData: [
        {
          combinationActName: "name1",
          prizeLevelName: " ",
          prizeName: " ",
          prizeTotal: "",
          drawWay: " ",
          children: [
            {
              combinationActName: "name11",
              prizeLevelName: 1,
              prizeName: "type1",
              prizeTotal: 1,
              drawWay: "2"
            }
          ]
        }
      ]
    };
  },
  created() {
    this.tableData = window.MOCK_TREE_DATA_LIST.slice(0);
  },
  methods: {
    selectChangeEvent({ selection }) {
      console.info(`勾选${selection.length}个树形节点`, selection);
    },
    deliverValue(row) {
      debugger;
      //   this.drawId = vId;
      //   this.selectLabel = this.selectedAct[i].actName;
    }
  },
  mounted: function() {
    debugger;
    let _this = this;
    var userId = localStorage.getItem("userId");
    getAllRequest("/getComActPrizes/" + actId).then(resq => {
      if (resq.status == 200 && resq.data.result != null) {
        _this.tableData = resq.data.result;
      } else {
        this.$message({ type: "error", message: "查询不到活动详情" });
      }
    });
  }
};
</script>

