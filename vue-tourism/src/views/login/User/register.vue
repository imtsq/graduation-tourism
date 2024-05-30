<template>
  <div class="wrap">

   <div class="container">
    <div class="title">用户注册</div>
     <div class="label"><span class="required">*</span>用 户 名:<input type="text" v-model="username"></div>
     <div class="label"><span class="required">*</span>密 码:<input type="password" v-model="password"></div>
     <div class="label"><span class="required">*</span>确 认 密 码:<input type="password" v-model="Repassword" v-on:blur="handleBlur"></div>
    <div class="label"><span class="required">*</span>姓 名:<input type="text" v-model="name"></div>
    <div class="label"><span class="required">*</span>身 份 证:<input type="text" v-model="identity_card" v-on:blur="handleBlur1"></div>
    <div class="label"><span class="required">*</span>邮 箱:<input type="email" v-model="email" v-on:blur="handleBlur2"></div>
    <div class="register_btn" @click="handleBtn">注册</div>
   </div>
  </div>
</template>

<script>
import { insertNewUser } from '@/api/login'
export default {
  name: 'registerIndex',
  data () {
    return {
      username: '',
      password: '',
      Repassword: '',
      name: '',
      identity_card: '',
      email: ''
    }
  },
  methods: {
    handleBtn () {
      if (this.password !== this.Repassword) {
        this.tip('两次密码不一样')
        return
      }
      console.log({
        username: this.username,
        password: this.pasword,
        name: this.name,
        identityCard: this.identity_card,
        email: this.email
      })
      this.insertNewUser()
    },
    async insertNewUser () {
      const resultInfo = await insertNewUser({
        username: this.username,
        pasword: this.pasword,
        name: this.name,
        identityCard: this.identity_card,
        email: this.email
      })
      this.tip(resultInfo.msg)
    },
    handleBlur (event) {
      if (this.password !== this.Repassword) {
        this.tip('两次密码不一样')
      }
    },
    handleBlur1 () {
      if (this.identity_card.length !== 18) {
        this.tip('身份证长度错误')
      }
    },
    handleBlur2 () {
      if (!this.email.includes('@') || !this.email.includes('.')) {
        this.tip('邮箱错误')
      }
    },
    tip (msg) {
      this.$message(msg)
    }
  }
}
</script>

<style scoped lang="less">
@import '@/style/login/user/register.less';
</style>
