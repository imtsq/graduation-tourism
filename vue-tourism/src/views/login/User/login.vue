<template>
  <div class="main">
    <div class="box" v-show="isshow">
      <h4>用户登录</h4>
                <div class="login_input">
            <i class="el-icon-user"></i>
            <input type="text" v-model="username"><br/>
             <i class="el-icon-lock"></i>
            <input type="password" v-model="password"><br/>
             <el-button type="warning" round @click="handleLogin">登录</el-button>
             <div class="login_bottom">
              <span class="turn_bottom" @click="isshow = false">忘记密码? </span>
              <span @click="$router.push('/UserRegister')" class="turn_bottom">注册</span>
             </div>
          </div>
    </div>
        <div class="box" v-show="!isshow">
      <h4>找回密码</h4>
                <div class="login_input">
            <i class="el-icon-lock"></i>
            <input type="text" v-model="username" placeholder="账号"><br/>
            <i class="el-icon-user"></i>
            <input type="email" v-model="email" placeholder="邮箱"><br/>
             <el-button type="warning" round @click="handleSend">发送邮箱</el-button>
             <div class="login_bottom">
              <span class="turn_bottom" @click="isshow = true">登录 </span>
              <span @click="$router.push('/UserRegister')" class="turn_bottom">注册</span>
             </div>
          </div>
    </div>
  </div>
</template>

<script>
import { SendEmail } from '@/api/login'
export default {
  name: 'LoginIndex',
  data () {
    return {
      isshow: true,
      email: '',
      username: '',
      password: ''
    }
  },
  methods: {
    async handleSend () {
      const resultInfo = await SendEmail(this.email, this.username)
      console.log('发送')
      this.tip(resultInfo.msg)
    },
    async handleLogin () {

    },
    tip (msg) {
      this.$message(msg)
    }
  }
}
</script>

<style scoped lang="less">
@import '@/style/login/user/login.less';
</style>
