<template>
  <div class=" markdown-body">
    <div style="background:#ECECEC; padding:30px">
  <a-card title="Card title" :bordered="false" style="width: 300px">
    <p>Card content</p>
    <p>Card content</p>
    <p>Card content</p>
  </a-card>
</div>
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
import marked from 'marked'
import { debuglog } from 'util';
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
        _this.activity = resq.data.result;
      }else{
        this.$message({ type: "error", message: "查询不到活动详情" });
      }
    });
  },
  methods:{
//     getMardownFileContent(data) {
// 		//处理markdown数据，data为markdown文件读出的字符串
// 		this.readmeContent = marked(articleDetail || '<h>aaaa</h>', {
// 		  sanitize: true
// 		}); 
//   }
  },
  data(){
      return{
        actId:"",
        activity: "",
        placeholder:"",//输入框为空时默认提示文本
        subfield: false, // 单双栏模式
        defaultOpen: "preview" , //edit： 默认展示编辑区域 ， preview： 默认展示预览区域
        editable: false,
        toolbarsFlag: false,
        scrollStyle: true,
        loading:false,
        articleDetail:"",
        boxShadow:true,
      }
  },
  
};
</script>
<style>

</style>

