<template>
  <el-form
    :rules="rules"
    class="login-container"
    label-position="left"
    label-width="0px"
    v-loading="loading"
  >
    <div>
      <a-tabs defaultActiveKey="1" @change="callback">
        <a-tab-pane tab="登录" key="1">
          <br />
          <el-form-item prop="account">
            <el-input  ref="loginUsername" type="text" v-model="loginForm.username" auto-complete="off" placeholder="账号"></el-input>
          </el-form-item>
          <el-form-item prop="checkPass">
            <el-input
              type="password"
              v-model="loginForm.password"
              auto-complete="off"
              placeholder="密码"
            ></el-input>
          </el-form-item>
          <el-checkbox class="login_remember" v-model="checked" label-position="left">记住密码</el-checkbox>
          <el-form-item style="width: 100%">
            <el-button type="primary" @click.native.prevent="submitClick(0)" style="width: 100%">登录</el-button>
          </el-form-item>
        </a-tab-pane>
        <a-tab-pane tab="注册" key="3">
          <br />
          <el-form-item prop="registerAccount">
            <el-input
              type="text"
              v-model="registerForm.username"
              auto-complete="off"
              placeholder="账号"
              @blur="checkUsername"
            ></el-input>
          </el-form-item>
          <el-form-item prop="registerCheckPass">
            <el-input
              type="password"
              v-model="registerForm.password"
              auto-complete="off"
              placeholder="密码"
            ></el-input>
          </el-form-item>
          <el-checkbox class="login_remember" v-model="checked" label-position="left">记住密码</el-checkbox>
          <el-form-item style="width: 100%">
            <el-button type="primary" @click.native.prevent="submitClick(1)" style="width: 100%">注册</el-button>
          </el-form-item>
        </a-tab-pane>
      </a-tabs>
    </div>
  </el-form>
</template>
<script>
import { postRequest } from "../utils/api";
import { putRequest } from "../utils/api";
export default {
  mounted:function(){
    this.$refs.loginUsername.focus();
  },
  data() {
    return {
      rules: {
        account: [{ required: false, message: "请输入用户名", trigger: "blur" }],
        checkPass: [{ required: false, message: "请输入密码", trigger: "blur" }],
        registerAccount: [
          { required: false, message: "请输入用户名", trigger: "blur" }
        ],
        registerCheckPass: [
          { required: false, message: "请输入密码", trigger: "blur" }
        ]
      },
      defaultActiveKey:"1",
      checked: true,
      loginForm: {
        username: "",
        password: ""
      },
      registerForm: {
        username: "",
        password: ""
      },
      loading: false
    };
  },
  inject: ["reload"],
  methods: {
    submitClick: function(flag) {
      var _this = this;
      _this.loading = true;
      var path = "";
      var param = {};
      if (flag == 0) {
        //登录
        path = "/login";
        param = {
          username: this.loginForm.username,
          password: this.loginForm.password
        };
      } else {
        //注册
        path = "/register";
        param = {
          username: this.registerForm.username,
          password: this.registerForm.password
        };
      }

      postRequest(path, param).then(
        resp => {
          _this.loading = false;
          if (resp.status == 200) {
            debugger
            //成功
            if (resp.data.status == "success") {
              if(flag == 0){
                debugger
                  _this.GLOBAL.userId = resp.data.userId;
                  // _this.GLOBAL.Authorization = resp.data.Authorization;
                  localStorage.setItem("userId", resp.data.userId);
                  localStorage.setItem("Authorization","Bearer "+resp.data.token)
                  _this.$router.replace({ path: "/home" });
              }else{
                  //注册成功
                _this.defaultActiveKey = "1";
                _this.reload();
                _this.$message({
                  type: "success",
                  message: "注册完成!"
                });
              }
            } else if(resp.data.result.status == "used"){
              _this.$alert("用户名已被占用!", "失败!");
            }else{
                _this.$alert("登录失败!", "失败!");
            }
          } else {
            //失败
            _this.$alert("登录失败!", "失败!");
          }
        },
        resp => {
          _this.loading = false;
          _this.$alert("找不到服务器⊙﹏⊙∥!", "失败!");
        }
      );
    },
    callback(key) {},
    checkUsername(){
      var _this = this;
      var param = {username:this.registerForm.username};
      postRequest("/queryUserName", param).then(
        resp => {
          if (resp.status == 200) {
              if (resp.data.result == 0) {
                _this.rules.registerAccount[0].required= false;
              } else {
                _this.rules.registerAccount[0].message= _this.registerForm.username +"已存在";
                _this.rules.registerAccount[0].required= true;
              }
          } else {
            //失败
            _this.$alert("服务器异常", "失败!");
          }
        },
        resp => {
          _this.loading = false;
          _this.$alert("找不到服务器⊙﹏⊙∥!", "失败!");
        }
      );
    }
  }
};
</script>
<style>
.login-container {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 180px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

.login_title {
  margin: 0px auto 40px auto;
  text-align: center;
  color: #505458;
}

.login_remember {
  margin: 0px 0px 35px 0px;
  text-align: left;
}
</style>
