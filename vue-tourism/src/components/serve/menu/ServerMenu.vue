<template>
  <div>
  <el-menu
      default-active="2"
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
      router>
      <el-menu-item>
        <span slot="title">景区后台管理</span>
      </el-menu-item>
      <div v-for="item in menuList" :key="item.id">
      <el-submenu :index="item.id" v-if='item.children && item.children.length != 0'>
        <template slot="title">
          <i :class="item.icon"></i>
          <span>{{item.name}}</span>
        </template>
          <el-menu-item
          v-for="sub in item.children"
          :key="sub.id"
          :index="sub.url">{{sub.name}}</el-menu-item>
      </el-submenu>
        <el-menu-item :index="item.url" v-else>
          <i :class="item.icon"></i>{{item.name}}
          </el-menu-item>
      </div>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: 'ServerMenuIndex',
  methods: {
    handleOpen (key, keyPath) {
      // console.log(key, keyPath)
    },
    handleClose (key, keyPath) {
      // console.log(key, keyPath)
    }
  },
  data () {
    return {
      menuList: [
        {
          id: '1',
          name: '门票管理',
          url: '',
          icon: 'el-icon-coffee',
          children: [
            {
              id: '1-1',
              name: '添加门票',
              url: '/serve/Ticket',
              icon: ''
            }
          ]
        },
        {
          id: '2',
          name: '用户管理',
          url: '',
          icon: 'el-icon-user-solid',
          children: [
            {
              id: '2-1',
              name: '客户编辑',
              url: '/server/SerUser',
              icon: 'el-icon-coffee'
            }
          ]
        },
        {
          id: '4',
          name: '订单管理',
          url: '/server/modifyPassword',
          icon: 'el-icon-setting',
          children: [
            {
              id: '4-1',
              name: '订单',
              url: '/server/SerOrder',
              icon: 'el-icon-coffee'
            }
          ]
        },
        {
          id: '3',
          name: '账号设置',
          url: '/server/modifyPassword',
          icon: 'el-icon-setting'
        }
      ]
    }
  }
}
</script>

<style lang="less" scoped>
.el-menu {
  border-right:0;
}
.el-submenu .el-menu-item{
  min-width: 0;
}
// 高亮
/deep/ .is-active {
  background-color: black ! important;
  color: #fff !important;
}
</style>
