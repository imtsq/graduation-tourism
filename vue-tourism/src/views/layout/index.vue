<template>
  <div class="warp">
    <div class="narbar">
      <NavBar></NavBar>
    </div>
    <div class="main" >
      <router-view/>
    </div>
    <div class="footer">
      <Footer></Footer>
    </div>
  </div>
</template>

<script>
import NavBar from '@/components/layout/Narbar.vue'
import Footer from '@/components/layout/footer.vue'
export default {
  name: 'LayoutIndex',
  components: {
    NavBar,
    Footer
  },
  methods: {

  }
}
</script>

<style lang="less" scoped>
.main{
  margin-top:88px;
}
</style>
