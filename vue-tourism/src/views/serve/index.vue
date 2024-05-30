<template>
  <div class="layout clearfix">
    <div class="menu ">
      <Menu></Menu>
    </div>
     <!-- 操作区 -->
     <div class="context">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import Menu from '@/components/serve/menu/ServerMenu.vue'
export default {
  name: 'serverIndex',
  methods: {
  },
  components: {
    Menu
  }

}
</script>

<style scoped lang="less">
.layout {

    width:100%;
    height: 100%;

    .menu {
      width:15%;
      background-color: rgb(84, 92, 100);
      position:fixed;
      left:0;
      top:0;
      bottom:0;
    }

    .context {
    padding-left:15%;
    width:85%;
    background-color: white;

}
}
</style>
