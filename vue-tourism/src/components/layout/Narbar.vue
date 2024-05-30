<template>
  <div class="wrap">
    <div class="top">
      <div class="loginBt fr" style="margin: 0 30px 0 10px">
        <span @click="$router.push('/UserLogin')">登录</span>
        <span style="margin: 0 10px 0 10px">|</span>
        <span @click="$router.push('/UserRegister')">注册</span>
      </div>

    </div>
    <ul class="main">
        <li class="list" v-for="item in DataList" :key="item.id">
            <router-link :to="item.url">{{item.name}}</router-link>
        </li>
    </ul>

  </div>

</template>

<script>
export default {
  name: 'NarbarIndex',
  data () {
    return {
      DataList: [
        { id: 1, name: '首页', url: '/index' },
        { id: 2, name: '购票', url: '/Book' },
        { id: 4, name: '出票', url: '#' }
      ]
    }
  }
}
</script>

<style lang="less" scoped>
.loginBt{
  span{
  cursor: pointer;
  }
}
.list  {
    height: 100%;
    width: 128px;
    a{
        height: 100%;
        width: 100%;
        // line-height: 50px;
        font-size:20px;
        display: flex;
        justify-content: center;
        align-items: center;
        color: #0061ae;
        font-weight: bold;
    }
}
.top{
    background-color: #05c5b6;
    width: 100%;
    height: 25px;

}
.main {
    background-color: #a0bded;
    width: 100%;
    height: 60px;
    display: flex;
    justify-content: center;
    align-items: center;

}
.wrap{
    box-shadow: 10px 10px 10px rgba(0, 0, 0, 0.2);

    position:fixed;
    left: 0;
    right: 0;
    top:0;
    z-index: 101;

}

</style>
