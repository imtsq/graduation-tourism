<template>
  <div class="wrap">

   <div class="container">
     <div class="label"><span class="required">*</span>密 码:<input type="password" v-model="password"></div>
     <div class="label"><span class="required">*</span>确 认 密 码:<input type="password" v-model="Repassword" v-on:blur="handleBlur"></div>
    <div class="register_btn" @click="handleBtn">确认</div>
   </div>
  </div>
</template>
<script>
import { UpdatePasswordByUsernmae } from '@/api/login'
export default {
  name: 'registerIndex',
  data () {
    return {
      token: '',
      password: '',
      Repassword: ''
    }
  },
  methods: {
    handleBtn () {
      if (this.password !== this.Repassword) {
        this.tip('两次密码不一样')
        return
      }
      this.token = this.$route.params.token
      this.UpdatePasswordByUsernmae()
    },
    async UpdatePasswordByUsernmae () {
      if (this.token.length < 0 || this.password !== this.Repassword) {
        return
      }
      const resultInfo = await UpdatePasswordByUsernmae(this.token, this.password)
      this.tip(resultInfo.msg)
    },
    handleBlur (event) {
      if (this.password !== this.Repassword) {
        this.tip('两次密码不一样')
      }
    },
    tip (msg) {
      this.$message(msg)
    }
  },
  created () {
  }
}
</script>

<style scoped lang="less">
@import '@/style/login/user/register.less';
</style>
