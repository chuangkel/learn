<template>

  <div style="display: flex;justify-content: space-around;flex-wrap: wrap">
    <a-card
      style="width:350px;margin-top: 10px;"
      v-for="(item) in articleDetail"
      :key="item"
      hoverable
      @click="cardClick(item)"
    >
      <img
        :alt='item.actPicture'
        :src='item.actPicture'
        slot="cover"
      >
      <a-card-meta :title="item.actName">
        <template slot="description">举办时间:{{item.actTime | formatDateTime}}-{{item.actEndtime | formatDateTime}}</template>
      </a-card-meta>
    </a-card>
  </div>
</template>
<script>
import { getAllRequest } from "../utils/api";
export default {
  //页面初始化时请求数据
  mounted: function() {
    let _this = this;
    var userId = localStorage.getItem("userId");
    getAllRequest("/getActivities/" + userId).then(resq => {
      if (resq.status == 200) {
        _this.articleDetail = resq.data.result;
      }
    });
  },
  methods: {
    cardClick(item){
        this.$router.push({path: '/ActivityDetail', params: { activity:item.id } ,query: {selected: item.id}})
      }
  },
  data() {
    return {
      actPicture:"https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png",
      articleDetail: []
    };
  }
};
</script>
