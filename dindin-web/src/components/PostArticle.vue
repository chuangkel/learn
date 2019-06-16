<template>
  <div>
    <div>
      <el-container v-loading="loading" class="post-article">
        <el-header class="header">
          <h1 class="base-info__title">基本信息</h1>
        </el-header>
        <!-- <el-main class="main"> -->
        <div id="editor">
          <a-form class="a-form-style">
            <a-form-item
              :label-col="labelCol"
              :wrapper-col="wrapperCol"
              label="活动标题"
              validate-status="error"
              help="不少于5个字,最多50个字"
            >
              <a-input id="error" placeholder="unavailable choice"/>
            </a-form-item>

            <a-form-item
              label="举办时间"
              :label-col="labelCol"
              :wrapper-col="wrapperCol"
              style="margin-bottom:0;"
            >
              <a-form-item
                validate-status="error"
                help="请选择时间"
                :style="{ display: 'inline-block', width: 'calc(50% - 12px)' }"
              >
                <a-date-picker style="width: 100%"/>
              </a-form-item>
              <span :style="{ display: 'inline-block', width: '24px', textAlign: 'center' }">-</span>
              <a-form-item :style="{ display: 'inline-block', width: 'calc(50% - 12px)' }">
                <a-date-picker style="width: 100%"/>
              </a-form-item>
            </a-form-item>

            <a-form-item
              :label-col="labelCol"
              :wrapper-col="wrapperCol"
              label="活动地址"
              has-feedback
              validate-status="error"
            >
              <a-select default-value="1">
                <a-select-option value="1">Option 1</a-select-option>
                <a-select-option value="2">Option 2</a-select-option>
                <a-select-option value="3">Option 3</a-select-option>
              </a-select>
            </a-form-item>

            <a-form-item :label-col="labelCol" :wrapper-col="wrapperCol" label="活动海报" has-feedback>
              <div class="clearfix">
                <a-upload
                  action="https://www.mocky.io/v2/5cc8019d300000980a055e76"
                  listType="picture-card"
                  :fileList="fileList"
                  @preview="handlePreview"
                  @change="handleChange"
                >
                  <div v-if="fileList.length < 3">
                    <a-icon type="plus"/>
                    <div class="ant-upload-text">Upload</div>
                  </div>
                </a-upload>
                <a-modal :visible="previewVisible" :footer="null" @cancel="handleCancel">
                  <img alt="example" style="width: 100%" :src="previewImage">
                </a-modal>
              </div>
            </a-form-item>

            <a-form-item
              :label-col="labelCol"
              :wrapper-col="wrapperCol"
              label="活动类型"
              has-feedback
              validate-status="error"
            >
              <a-select default-value="1">
                <a-select-option value="1">Option 1</a-select-option>
                <a-select-option value="2">Option 2</a-select-option>
                <a-select-option value="3">Option 3</a-select-option>
              </a-select>
            </a-form-item>

            <a-form-item
              :label-col="labelCol"
              :wrapper-col="wrapperCol"
              label="活动标签"
              validate-status="warning"
            >
              <a-input id="warning" placeholder="Warning"/>
            </a-form-item>

            <a-form-item
              :label-col="labelCol"
              :wrapper-col="wrapperCol"
              label="活动亮点"
              has-feedback
              validate-status="validating"
              help="The information is being validated..."
            >
              <a-input id="validating" placeholder="I'm the content is being validated"/>
            </a-form-item>

            <a-form-item
              :label-col="labelCol"
              :wrapper-col="wrapperCol"
              label="活动人数"
              has-feedback
              validate-status="success"
            >
              <a-input id="success" placeholder="I'm the content"/>
            </a-form-item>

            <a-form-item :label-col="labelCol" :wrapper-col="wrapperCol" label="是否公开" has-feedback>
              <a-radio-group @change="onChange" v-model="value">
                <a-radio :style="radioStyle" :value="1">公开发布</a-radio>
                <a-radio :style="radioStyle" :value="2">私密活动，仅能通过您的分享链接报名</a-radio>
              </a-radio-group>
            </a-form-item>
          </a-form>
        </div>
        <!-- </el-main>-->
      </el-container> 
    </div>

    <div>
      <el-container v-loading="loading" class="post-article">
        <el-header class="header">
          <h1 class="base-info__title">活动内容</h1>
        </el-header>

        <!-- 活动内容 -->
        <el-main class="main">
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
            <template v-if="from==undefined || from=='draft'">
              <el-button type="primary" @click="saveBlog(1)">提交活动</el-button>
            </template>
          </div>
        </el-main>
      </el-container>
    </div>
  </div>
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
import Calendar from "@/components/calendar";
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
    mavonEditor,
    Calendar
  },
  methods: {
    onChange(e) {
      console.log("radio checked", e.target.value);
    },
    handleCancel() {
      this.previewVisible = false;
    },
    handlePreview(file) {
      this.previewImage = file.url || file.thumbUrl;
      this.previewVisible = true;
    },
    handleChange({ fileList }) {
      this.fileList = fileList;
    },
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
        userId: _this.userId
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
      this.activity.dynamicTags.splice(
        this.activity.dynamicTags.indexOf(tag),
        1
      );
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
      value: 1,
      radioStyle: {
        display: "block",
        height: "30px",
        lineHeight: "30px"
      },
      previewVisible: false,
      previewImage: "",
      fileList: [
        {
          uid: "-1",
          name: "xxx.png",
          status: "done",
          url:
            "https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png"
        }
      ],
      labelCol: {
        xs: { span: 24 },
        sm: { span: 5 }
      },
      wrapperCol: {
        xs: { span: 24 },
        sm: { span: 12 }
      },
      userId: this.GLOBAL.userId,
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
        cid: "",
        actUptime: new Date(),
        actDowntime: new Date()
      },
      //选择日历
      exampleCode:
        '< Calendar  <br> v-model="example.value" <br> :range="example.range" <br> :lang="example.lang" <br> :firstDayOfWeek="example.firstDayOfWeek" <br> :input-class="example.inputClass"  <br> :position="example.position" <br> :disabled-start-date="example.disabledStartDate" <br> :text-format="example.textFormat" <br> :date-format="example.dateFormat" <br> :disabled-end-date="example.disabledEndDate"/>',
      example: {
        title: "Single",
        inputClass: "exampleDatePicker",
        lang: "zh",
        position: "bottom",
        range: false,
        value: new Date(),
        firstDayOfWeek: "sunday"
      }
    };
  }
};
</script>
<style>
.a-form-style {
  text-align: left;
  display: inline;
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

.post-article > .header > .example {
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
.post-article > .header > .example > .label {
  display: flex;
  justify-content: flex-start;
  padding: 0%;
  background-color: #ececec;
  margin-top: 10px;
  margin-bottom: 10px;
  padding-right: 5px;
  width: 100px;
  margin-right: 2px;
  margin-left: auto;
  margin-right: auto;
}

.create-event-v2 .base-info-block.base-info-block--base {
  padding-bottom: 40px;
}

.create-event-v2 .base-info-block {
  display: block;
  border-radius: 12px;
  background: #fff;
  padding-right: 30px;
  margin-top: 20px;
  padding-bottom: 20px;
}
* {
  margin: 0;
  padding: 0;
  font-family: "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei",
    "WenQuanYi Micro Hei", "Helvetica Neue", Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
}
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei",
    "WenQuanYi Micro Hei", "Helvetica Neue", Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
}
* {
  box-sizing: border-box;
}
* {
  margin: 0;
  padding: 0;
  -webkit-font-smoothing: antialiased;
}
user agent stylesheet div {
  display: block;
}
.create-event-v2 {
  line-height: 1;
  padding-bottom: 40px;
}
.ant-upload-select-picture-card i {
  font-size: 32px;
  color: #999;
}

.ant-upload-select-picture-card .ant-upload-text {
  margin-top: 8px;
  color: #666;
}
.base-info__title {
    line-height: 60px;
    padding-left: 30px;
    border-bottom: 1px solid black;
    font-size: 18px;
    color: black;
    font-weight: bold;
    margin-right: -30px;
}
h1 {
    margin: 0;
    padding: 0;
}
</style>
