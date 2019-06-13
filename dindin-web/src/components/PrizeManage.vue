<template>
<div>
  <div>
            <!-- <vxe-button @click="$refs.xTable.insert({name: Date.now()})">在第1行插入</vxe-button>
            <vxe-button @click="insertEvent">在第3行插入并激活 Sex 单元格</vxe-button>-->
            <vxe-button @click="$refs.xTable.insertAt({actName: ''}, -1)">新增</vxe-button> 
            
            <vxe-button @click="getInsertEvent">提交</vxe-button>
  </div>
  <div>
  <vxe-table
    ref="xTable"
    border
    show-all-overflow
    :data.sync="tableData"
    :edit-rules="validRules"
    :edit-config="{key: 'id', trigger: 'click', mode: 'cell'}"
  >
    <vxe-table-column  type="selection" width="60"></vxe-table-column>
    <vxe-table-column prop="actId" label="所属活动" :edit-render="{name: 'input'}"></vxe-table-column>
    <vxe-table-column prop="prizeLevel" label="奖品等级" :edit-render="{name: 'input'}"></vxe-table-column>
    <vxe-table-column prop="prizeName" label="奖品名称" :edit-render="{name: 'input'}"></vxe-table-column>
    <vxe-table-column prop="prizeTotal" label="总数量" :edit-render="{name: 'input'}"></vxe-table-column>
  </vxe-table>
</div>
</div>
 
</template>

<script>
import { postRequestJson } from "../utils/api";
export default {
  data() {
    return {
      tableData: [],
      validRules: {
                actName: [
                  { required: true, message: '名称必须填写' },
                  { min: 3, max: 50, message: '名称长度在 3 到 50 个字符' }
                ],
                prize_level: [
                  { required: true, message: '性别必须填写' }
                ]
              }
    };
  },
  created() {
    // this.tableData = {name:"aaa"};
  },
  methods: {
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
      postRequestJson("/insertPrizes",insertRecords).then(resq=>{
        if(resq.status == 200){
          if(resq.data.data == 'success'){
            // alert("保存成功");
          }else{
            // alert("保存失败")
          }
        }else{
          alert("系统异常,联系管理员")
        }
      });

    }
  }
};
</script>
