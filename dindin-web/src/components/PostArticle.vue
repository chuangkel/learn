<template>
  <div>
    <div>
      <el-container v-loading="loading" class="post-article">
        <el-header class="header">
          <h1 class="base-info__title">基本信息</h1>
        </el-header>
        <div id="editor">
          <a-form class="a-form-style" :form="form">
            <a-form-item
              :label-col="labelCol"
              :wrapper-col="wrapperCol"
              label="活动标题"
              validate-status="error"
              help="不少于5个字,最多50个字"
            >
              <a-input
                id="error"
                placeholder="输入活动标题"
                v-decorator="['actName', { rules: [{ required: true, message: '活动标题必填' }],}]"
              />
            </a-form-item>

            <a-form-item
              label="举办时间"
              :label-col="labelCol"
              :wrapper-col="wrapperCol"
              style="margin-bottom:0;"
              validate-status="error"
            >
              <a-form-item
                validate-status="error"
                :style="{ display: 'inline-block', width: 'calc(50% - 12px)' }"
              >
                <a-date-picker
                  :disabledDate="disabledStartDate"
                  showTime
                  format="YYYY-MM-DD HH:mm:ss"
                  v-model="startValue"
                  placeholder="开始时间"
                  @openChange="handleStartOpenChange"
                  v-decorator="['actTime', { rules: [{ required: true, message: '开始时间必填' }],}]"
                />
              </a-form-item>

              <a-form-item :style="{ display: 'inline-block', width: 'calc(50% - 12px)' }">
                <a-date-picker
                  :disabledDate="disabledEndDate"
                  showTime
                  format="YYYY-MM-DD HH:mm:ss"
                  placeholder="结束时间"
                  v-model="endValue"
                  :open="endOpen"
                  @openChange="handleEndOpenChange"
                  v-decorator="['actEndTime', { rules: [{ required: true, message: '结束时间必填' }],}]"
                />
              </a-form-item>
            </a-form-item>

            <a-form-item
              :label-col="labelCol"
              :wrapper-col="wrapperCol"
              label="活动地址"
              validate-status="error"
              help="不少于5个字,最多50个字"
            >
              <a-input
                id="error"
                placeholder="输入活动地址"
                v-decorator="['actAddress', { rules: [{ required: true, message: '活动地址必填' }],}]"
              />
            </a-form-item>

            <a-form-item :label-col="labelCol" :wrapper-col="wrapperCol" label="活动海报" has-feedback>
              <div class="clearfix">
                <a-upload
                  action="https://www.mocky.io/v2/5cc8019d300000980a055e76"
                  listType="picture-card"
                  :fileList="fileList"
                  :beforeUpload="beforeUpload"
                  @preview="handlePreview"
                  @change="handleChange"
                >
                  <div v-if="fileList.length < 1">
                    <a-icon type="plus"/>
                    <div class="ant-upload-text">Upload</div>
                  </div>
                </a-upload>
                <a-modal :visible="previewVisible" :footer="null" @cancel="handleCancel">
                  <img alt="example" style="width: 100%" :src="previewImage">
                </a-modal>
                <div class="create-box-poster-tips">
                  <p class="create-box-poster-tips__title">温馨提示：</p>
                  <p>1、图片尺寸 1080*640，.jpg 或 .png格式，不超过4M</p>
                  <p>2、精美海报有助于增加报名量，并有机会获得强力推荐！</p>
                </div>
              </div>
            </a-form-item>

            <a-form-item
              :label-col="labelCol"
              :wrapper-col="wrapperCol"
              label="活动类型"
              has-feedback
              validate-status="success"
            >
              <a-select
                default-value="1"
                v-decorator="['actType', { rules: [{ required: true, message: '活动类型必填' }],}]"
              >
                <a-select-option value="1">Option 1</a-select-option>
                <a-select-option value="2">Option 2</a-select-option>
                <a-select-option value="3">Option 3</a-select-option>
              </a-select>
            </a-form-item>

            <a-form-item
              :label-col="labelCol"
              :wrapper-col="wrapperCol"
              label="活动标签"
              validate-status="success"
            >
              <a-input
                id="warning"
                placeholder="活动标签必填"
                v-decorator="['actTags', { rules: [{ required: true, message: '活动标签必填' }],}]"
              />
            </a-form-item>

            <a-form-item
              :label-col="labelCol"
              :wrapper-col="wrapperCol"
              label="活动亮点"
              has-feedback
              validate-status="success"
            >
              <a-input
                id="validating"
                placeholder="增加活动亮点更具有吸引力!"
                v-decorator="['actStar', { rules: [{ required: true, message: '增加活动亮点更具有吸引力!' }],}]"
              />
            </a-form-item>

            <a-form-item
              :label-col="labelCol"
              :wrapper-col="wrapperCol"
              label="活动人数"
              has-feedback
              validate-status="error"
            >
              <a-input
                id="success"
                placeholder="活动人数上限"
                v-decorator="['actPeople', { rules: [{ required: true, message: '设置活动人数上限' }],}]"
              />
            </a-form-item>

            <a-form-item :label-col="labelCol" :wrapper-col="wrapperCol" label="是否公开" has-feedback>
              <a-radio-group
                @change="onChange"
                v-model="value"
                v-decorator="['actIsOpen', { rules: [{ required: true, message: '是否公开活动' }],}]"
              >
                <a-radio :style="radioStyle" :value="1">公开发布</a-radio>
                <a-radio :style="radioStyle" :value="2">私密活动，仅能通过您的分享链接报名</a-radio>
              </a-radio-group>
            </a-form-item>
          </a-form>
        </div>
      </el-container>
    </div>

    <div>
      <el-container v-loading="loading" class="post-article">
        <el-header class="header">
          <h1 class="base-info__title">活动内容</h1>
        </el-header>
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
function getBase64 (img, callback) {
  const reader = new FileReader()
  reader.addEventListener('load', () => callback(reader.result))
  reader.readAsDataURL(img)
}
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
    mavonEditor
  },
  created() {
    this.form = this.$form.createForm(this, {
      onFieldsChange: (_, changedFields) => {
        this.$emit("change", changedFields);
      },
      mapPropsToFields: () => {
        return {
          username: this.$form.createFormField({
            value: this.username
          })
        };
      },
      onValuesChange: (_, values) => {
        console.log(values);
        // Synchronize to vuex store in real time
        // this.$store.commit('update', values)
      }
    });
  },
  methods: {
     handleChange (info) {
      if (info.file.status === 'uploading') {
        this.loading = true
        return
      }
      if (info.file.status === 'done') {
        // Get this url from response in real world.
        getBase64(info.file.originFileObj, (imageUrl) => {
          this.imageUrl = imageUrl
          this.loading = false
        })
      }
    },
    beforeUpload (file) {
      const isJPG = file.type === 'image/jpeg'
      if (!isJPG) {
        this.$message.error('只能上传jpg文件')
      }
      const isLt2M = file.size / 1024 / 1024 < 4
      if (!isLt2M) {
        this.$message.error('图片需要小于4MB!')
      }
      var _this = this;
      // 第一步.将图片上传到服务器.
      var formdata = new FormData();
      formdata.append("image", file);
      uploadFileRequest("/activity/uploadimg", formdata).then(resp => {
        var json = resp.data;
        var fileName = file.name;
        if (resp.status == 200 && json.result == "success") {
            _this.fileList[0].url = localStorage.getItem("base")+"/image/" + fileName;
            this.$message.success(`${file.name} file uploaded successfully`);
        } else {
            this.$message.error(`${file.name} file upload failed.`);
        }
      });
      return isJPG && isLt2M
    },
    handchangeVailte(e){
      if(e.data.length > 5){
        this.checkActName = false;
      }
      this.checkActName = true;
    },
    disabledStartDate(startValue) {
      const endValue = this.endValue;
      if (!startValue || !endValue) {
        return false;
      }
      return startValue.valueOf() > endValue.valueOf();
    },
    disabledEndDate(endValue) {
      const startValue = this.startValue;
      if (!endValue || !startValue) {
        return false;
      }
      return startValue.valueOf() >= endValue.valueOf();
    },
    handleStartOpenChange(open) {
      if (!open) {
        this.endOpen = true;
      }
    },
    handleEndOpenChange(open) {
      this.endOpen = open;
    },
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
      
      this.form.validateFields((err, values) => {
        var actTime = this.startValue._d;
        var actEndtime = this.endValue._d;
        var actPicture = this.fileList[0].url;
        var actContent = this.activity.mdContent;

        if (!err) {
          if (!isNotNullORBlank(this.activity.mdContent)) {
            this.$message({ type: "error", message: "数据不能为空!" });
            return;
      }
          var _this = this;
          _this.loading = true;
          postRequestJson("/insertActivity/", {
            id:"",
            userId: localStorage.getItem("userId"),
            actName: values.actName,
            actContent: actContent,
            actAddress: values.actAddress,
            actTags:values.actTags,
            actTime: actTime,
            actEndtime: actEndtime,           
            actPicture: actPicture,
            actPeople:values.actPeople,
            actType:values.actType,
            actStar:values.actStar,
            actIsOpen:values.actIsOpen,
            actUptime:"",
            actDowntime:"",
            gmtCreate:"",
            gmtModified:""
          }).then(
            resp => {
              _this.loading = false;
              debugger
              if (resp.status == 200 && resp.data.result) {
                _this.activity.id = resp.data.msg;
                var actId = resp.data.result;
                _this.$message({
                  type: "success",
                  message: "发布成功!"
                });
                this.$router.push({path: '/ActivityDetail',query: {selected: actId}})

                //            if (_this.from != undefined) {
                // window.bus.$emit("blogTableReload");
                //            }
                // if (state == 1) {
                //   _this.$router.replace({ path: "/articleList" });
                // }
              }
            },
            resp => {
              _this.loading = false;
              _this.$message({
                type: "error",
                message:"活动发布失败!"
              });
            }
          );
        }
      });
    },
    imgAdd(pos, $file) {
      var _this = this;
      // 第一步.将图片上传到服务器.
      var formdata = new FormData();
      formdata.append("image", $file);
      uploadFileRequest("/activity/uploadimg", formdata).then(resp => {
        var json = resp.data;
        var url = localStorage.getItem("base")+"/image/"+$file.name;
        if (json.status == 200 && json.result == "success") {
          // _this.$refs.md.$imglst2Url([[pos, url]]);
          _this.$refs.md.$img2Url(pos, url);
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
  watch: {
    startValue(val) {
      console.log("startValue", val);
    },
    endValue(val) {
      console.log("endValue", val);
    }
  },
  data() {
    return {
      loading: false,
      imageUrl: '',
      checkActName:true,
      form: this.$form.createForm(this),
      startValue: null,
      endValue: null,
      endOpen: false,
      value: 1,
      radioStyle: {
        display: "block",
        height: "30px",
        lineHeight: "30px"
      },
      previewVisible: false,
      previewImage: "",
      fileList: [
        // {
        //   uid: "-1",
        //   name: "xxx.png",
        //   status: "done",
        //   url:""
        // }
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

  .avatar-uploader > .ant-upload {
    width: 128px;
    height: 128px;
  }
  .ant-upload-select-picture-card i {
    font-size: 32px;
    color: #999;
  }

  .ant-upload-select-picture-card .ant-upload-text {
    margin-top: 8px;
    color: #666;
  }
</style>
