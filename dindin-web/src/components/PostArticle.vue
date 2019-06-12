<template>
  <el-container v-loading="loading" class="post-article">
    <el-header class="header">
      <el-input
        v-model="activity.title"
        placeholder="请输入活动标题..."
        style="width: 400px;margin-left: 10px"
      ></el-input>
     
      <div class="example" >
        <label class="label">上架日期</label>
        <Calendar
            v-model="activity.actUptime"
            :range="example.range"
            :lang="example.lang"
            :firstDayOfWeek="example.firstDayOfWeek"
            :input-class="example.inputClass"
            :position="example.position"
            :disabled-start-date="example.disabledStartDate"
            :text-format="example.textFormat"
            :date-format="example.dateFormat"
            :disabled-end-date="example.disabledEndDate"/>
    </div>
    <div class="example" >
        <label class="label">下架日期</label>
        <Calendar
            v-model="activity.actUptime"
            :range="example.range"
            :lang="example.lang"
            :firstDayOfWeek="example.firstDayOfWeek"
            :input-class="example.inputClass"
            :position="example.position"
            :disabled-start-date="example.disabledStartDate"
            :text-format="example.textFormat"
            :date-format="example.dateFormat"
            :disabled-end-date="example.disabledEndDate"/>
    </div>
    </el-header>

    <el-main class="main">
      <!-- 活动内容 -->
      <div id="editor">
        <mavon-editor
          style="height: 100%;width: 100%;"
          ref="md"
          @imgAdd="imgAdd"
          @imgDel="imgDel"
          v-model="activity.mdContent"
        ></mavon-editor>
      </div>
      <div style="display: flex;align-items: center;margin-top: 15px;justify-content: flex-end">
        <!-- <el-button @click="cancelEdit" v-if="from!=undefined">放弃修改</el-button> -->
        <template v-if="from==undefined || from=='draft'">
          <!-- <el-button @click="saveBlog(0)">保存到草稿箱</el-button> -->
          <el-button type="primary" @click="saveBlog(1)">提交活动</el-button>
        </template>
        <!-- <template v-else="from==post">
          <el-button type="primary" @click="saveBlog(1)">保存修改</el-button>
        </template>-->
      </div>
    </el-main>
  </el-container>
</template>
<script>
import { postRequest } from "../utils/api";
import { postRequestJson } from "../utils/api";
import { putRequest } from "../utils/api";
import { deleteRequest } from "../utils/api";
import { getRequest } from "../utils/api";
import { uploadFileRequest } from "../utils/api";
// Local Registration
import { mavonEditor } from "mavon-editor";
// 可以通过 mavonEditor.markdownIt 获取解析器markdown-it对象
import "mavon-editor/dist/css/index.css";
import { isNotNullORBlank } from "../utils/utils";
import Calendar from '@/components/calendar'
export default {
  mounted: function() {
    this.getCategories();
    var from = this.$route.query.from;
    this.from = from;
    var _this = this;
    if (from != null && from != "" && from != undefined) {
      var id = this.$route.query.id;
      this.id = id;
      this.loading = true;
      getRequest("/activity/" + id).then(
        resp => {
          _this.loading = false;
          if (resp.status == 200) {
            _this.activity = resp.data;
            var tags = resp.data.tags;
            _this.activity.dynamicTags = [];
            for (var i = 0; i < tags.length; i++) {
              _this.activity.dynamicTags.push(tags[i].tagName);
            }
          } else {
            _this.$message({ type: "error", message: "页面加载失败!" });
          }
        },
        resp => {
          _this.loading = false;
          _this.$message({ type: "error", message: "页面加载失败!" });
        }
      );
    }
  },
  components: {
    mavonEditor,Calendar
  },
  methods: {
    cancelEdit() {
      this.$router.go(-1);
    },
    saveBlog(state) {
      if (!isNotNullORBlank(this.activity.title, this.activity.mdContent)) {
        this.$message({ type: "error", message: "数据不能为空!" });
        return;
      }
      var _this = this;
      _this.loading = true;
      postRequestJson("/insertActivity/", {
        actTime: "2019-06-12T03:31:04.000+0000",
        actAddress: "1",
        actName: _this.activity.title,
        actContent: _this.activity.mdContent,
        actPicture: "",
        actUptime: _this.activity.actUptime,
        actDowntime: _this.activity.actDowntime,
        actEndtime: "2019-06-11T03:31:20.000+0000",
        gmtCreate: "2019-06-11T03:31:25.000+0000",
        gmtModified: "2019-06-11T03:31:29.000+0000",
        userId: "1"
      }).then(
        resp => {
          _this.loading = false;
          if (resp.status == 200 && resp.data.status == "success") {
            _this.activity.id = resp.data.msg;
            _this.$message({
              type: "success",
              message: state == 0 ? "保存成功!" : "发布成功!"
            });
            //            if (_this.from != undefined) {
            window.bus.$emit("blogTableReload");
            //            }
            if (state == 1) {
              _this.$router.replace({ path: "/articleList" });
            }
          }
        },
        resp => {
          _this.loading = false;
          _this.$message({
            type: "error",
            message: state == 0 ? "保存草稿失败!" : "博客发布失败!"
          });
        }
      );
    },
    imgAdd(pos, $file) {
      var _this = this;
      // 第一步.将图片上传到服务器.
      var formdata = new FormData();
      formdata.append("image", $file);
      uploadFileRequest("/activity/uploadimg", formdata).then(resp => {
        var json = resp.data;
        if (json.status == "success") {
          //            _this.$refs.md.$imgUpdateByUrl(pos, json.msg)
          _this.$refs.md.$imglst2Url([[pos, json.msg]]);
        } else {
          _this.$message({ type: json.status, message: json.msg });
        }
      });
    },
    imgDel(pos) {},
    getCategories() {
      let _this = this;
      getRequest("/admin/category/all").then(resp => {
        _this.categories = resp.data;
      });
    },
    handleClose(tag) {
      this.activity.dynamicTags.splice(this.activity.dynamicTags.indexOf(tag), 1);
    },
    showInput() {
      this.tagInputVisible = true;
      this.$nextTick(_ => {
        this.$refs.saveTagInput.$refs.input.focus();
      });
    },
    handleInputConfirm() {
      let tagValue = this.tagValue;
      if (tagValue) {
        this.activity.dynamicTags.push(tagValue);
      }
      this.tagInputVisible = false;
      this.tagValue = "";
    }
  },
  data() {
    return {
      categories: [],
      tagInputVisible: false,
      tagValue: "",
      loading: false,
      from: "",
      activity: {
        id: "-1",
        dynamicTags: [],
        title: "",
        mdContent: "",
        cid: ""
      },
      //选择日历
      exampleCode: '< Calendar  <br> v-model="example.value" <br> :range="example.range" <br> :lang="example.lang" <br> :firstDayOfWeek="example.firstDayOfWeek" <br> :input-class="example.inputClass"  <br> :position="example.position" <br> :disabled-start-date="example.disabledStartDate" <br> :text-format="example.textFormat" <br> :date-format="example.dateFormat" <br> :disabled-end-date="example.disabledEndDate"/>',
      example: 
        { title: 'Single',
          inputClass: 'exampleDatePicker',
          lang: 'zh',
          position: 'bottom',
          range: false,
          value: new Date(),
          firstDayOfWeek: 'sunday'
        }
    };
  }
};
</script>
<style>
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

.post-article > .header > .el-tag + .el-tag {
  margin-left: 10px;
}

.post-article > .header > .button-new-tag {
  margin-left: 10px;
  height: 32px;
  line-height: 30px;
  padding-top: 0;
  padding-bottom: 0;
}

.post-article > .header > .input-new-tag {
  width: 90px;
  margin-left: 10px;
  vertical-align: bottom;
}

.post-article {
}


.post-article > .header > .example{
    /* display: inline-block; */
    display: flex;
    justify-content: flex-start;
    padding: 0%;
    background-color: #ececec;
    margin-top: 10px;
    margin-bottom: 10px;
    padding-left: 5px;
    /* line-height: 40px; */
    /* height: 40px; */
}
.post-article > .header > .example > .label{
    display: flex;
    justify-content: flex-start;
    padding: 0%;
    background-color: #ececec;
    margin-top: 10px;
    margin-bottom: 10px;
    padding-right: 5px;
    width: 100px;
     margin-right: 2px;
     margin-left:auto;
margin-right:auto;
}
</style>
