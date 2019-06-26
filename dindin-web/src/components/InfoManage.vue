<template>
  <div>
    <vxe-toolbar>
      <template v-slot:buttons>
        <vxe-button @click="exportCsvEvent">默认导出</vxe-button>
      </template>
    </vxe-toolbar>

    <vxe-table ref="xTable1" highlight-hover-row height="300" :data.sync="tableData">
      <vxe-table-column type="index" width="60"></vxe-table-column>
      <vxe-table-column prop="name" label="Name"></vxe-table-column>
      <vxe-table-column prop="sex" label="Sex"></vxe-table-column>
      <vxe-table-column prop="age" label="Age" sortable></vxe-table-column>
      <vxe-table-column prop="address" label="Address" show-overflow></vxe-table-column>
    </vxe-table>
  </div>
</template>
<script>
export default {
    mounted:{

    },
  data() {
    return {
      tableData: [{name:"name",sex:"gril",age:26,address:"江南大道"}]
    };
  },
  created() {
    // this.tableData = {name:"name",sex:"gril",age:26,address:"江南大道"};
  },
  methods: {
    exportCsvEvent() {
      this.$refs.xTable1.exportCsv();
    }
  }
};
</script>
