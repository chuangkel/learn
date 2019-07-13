<template>
  <div>
    <el-container v-loading="loading" class="post-article">
      <el-header class="header">
        <h1 class="base-info__title">活动简介</h1>
      </el-header>
      <el-main class="main">
        <div style="background:#ECECEC; padding:30px;  ">
          <div style="width: 400px; display: inline-block; float:left;">
            <img
              :alt="activity.actPicture"
              :src="activity.actPicture"
              slot="cover"
              style="width: 100%;height: 100%;"
            >
          </div>
          <!-- <a-divider type="vertical"/> -->
          <div style="width: 400px;display:inline-block; float:left;">
            <a-card
              align="left"
              :title="activity.actName"
              :bordered="false"
              style="background:#ECECEC; width: 100%;"
            >
              <a-card-meta :title="activity.actAddress" style="float:left; padding:0px">
                <template slot="description">
                  <div align="left">
                    <p>举办时间：{{activity.actTime | formatDateTime}}-{{activity.actEndtime | formatDateTime}}</p>
                    <p>限额人数：{{activity.actPeople}}</p>
                    <p>活动亮点：{{activity.actStar}}</p>
                    <p>活动标签：{{activity.actTags}}</p>
                  </div>
                </template>
              </a-card-meta>
            </a-card>
          </div>
          <div style="width: 200px; display: inline-block; float:left;">
            <img
              :alt="activityCodeUrl"
              :src="activityCodeUrl"
              slot="cover"
              style="width: 100%;height: 100%;"
            >
            <br>
            <p @click="downloadCode">下 载</p>
          </div>
        </div>
      </el-main>
    </el-container>
    <a-divider></a-divider>
    <el-container v-loading="loading" class="post-article">
      <el-header class="header">
        <h1 class="base-info__title">活动内容</h1>
      </el-header>
      <el-main class="main">
        <div id="editor">
          <mavon-editor
            class="md"
            :placeholder="placeholder"
            :value="activity.actContent"
            :subfield="subfield"
            :defaultOpen="defaultOpen"
            :toolbarsFlag="toolbarsFlag"
            :editable="editable"
            :scrollStyle="scrollStyle"
            :boxShadow="boxShadow"
          ></mavon-editor>
        </div>
      </el-main>
    </el-container>
  </div>
</template>
<script>
// Local Registration
import { mavonEditor } from "mavon-editor";
// 可以通过 mavonEditor.markdownIt 获取解析器markdown-it对象
import "mavon-editor/dist/css/index.css";
import { getAllRequest } from "../utils/api";
import marked from "marked";
import { debuglog } from "util";
import global_ from '../utils/Global'
let base = global_.base; 
export default {
  components: {
    mavonEditor
  },
  //页面初始化时请求数据
  mounted: function() {
    let _this = this;
    _this.actId = _this.$route.query.selected;
    getAllRequest("/getActivity/" + _this.actId).then(resq => {
      if (resq.status == 200 && resq.data.result != null) {
        _this.activity = resq.data.result.activity;
        _this.activityCodeUrl = base+ "/image/" + resq.data.result.fileName;
      } else {
        this.$message({ type: "error", message: "查询不到活动详情" });
      }
    });
  },
  methods: {
     downloadIamge (imgsrc, name) { // 下载图片地址和图片名
      var image = new Image()
      // 解决跨域 Canvas 污染问题
      image.setAttribute('crossOrigin', 'anonymous')
      image.onload = function () {
        var canvas = document.createElement('canvas')
        canvas.width = image.width
        canvas.height = image.height
        var context = canvas.getContext('2d')
        context.drawImage(image, 0, 0, image.width, image.height)
        var url = canvas.toDataURL('image/png') // 得到图片的base64编码数据
        var a = document.createElement('a') // 生成一个a元素
        var event = new MouseEvent('click') // 创建一个单击事件
        a.download = name || 'photo' // 设置图片名称
        a.href = url // 将生成的URL设置为a.href属性
        a.dispatchEvent(event) // 触发a的单击事件
      }
      image.src = imgsrc
    },
    downloadCode(){
    this.downloadIamge(this.activityCodeUrl,this.activity.actName)
    }
  },
  data() {
    return {
      activityCodeUrl:"",
      actId: "",
      activity: "",
      placeholder: "", //输入框为空时默认提示文本
      subfield: false, // 单双栏模式
      defaultOpen: "preview", //edit： 默认展示编辑区域 ， preview： 默认展示预览区域
      editable: false,
      toolbarsFlag: false,
      scrollStyle: true,
      loading: false,
      articleDetail: "",
      boxShadow: true
    };
  }
};
</script>
<style>
.base-info__title {
  line-height: 60px;
  padding-left: 30px;
  border-bottom: 1px solid white;
  font-size: 18px;
  color: #20a0ff;
  font-weight: bold;
  margin-right: -30px;
}
h1 {
  margin: 0;
  padding: 0;
}
.post-article > .main > #editor {
  width: 100%;
  height: 450px;
  text-align: left;
}

.post-article > .header {
  background-color: #ececec;
  margin-top: 10px;
  padding-left: 5px;
  display: flex;
  justify-content: flex-start;
}

.post-article > .main {
  /*justify-content: flex-start;*/
  display: flex;
  flex-direction: column;
  padding-left: 5px;
  background-color: #ececec;
  padding-top: 0px;
}
</style>

