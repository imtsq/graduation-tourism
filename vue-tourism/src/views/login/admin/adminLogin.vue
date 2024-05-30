<template>
  <div class="wrap">

   <div class="container">
    <div class="title">管理员登录</div>
     <div class="label"><span class="required">*</span>账 号:<input type="text" v-model="username"></div>
     <div class="label"><span class="required">*</span>密 码:<input type="password" v-model="password"></div>
    <div class="register_btn" @click="handleBtn">登录</div>
   </div>
  </div>
</template>

<script>
// import { insertNewUser } from '@/api/login'
export default {
  name: 'registerIndex',
  data () {
    return {
      username: '',
      password: ''
    }
  },
  methods: {
    handleBtn () {

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
