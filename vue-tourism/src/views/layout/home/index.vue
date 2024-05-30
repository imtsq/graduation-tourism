<template>
<div>
    <ul class="list">
      <!-- 轮播 -->
        <li class="roll"></li>
        <!-- 购票链接 -->
        <li class="box" >

        </li>
        <!-- 个人中心 -->
        <li class="box" >
          <img src="@/assets/roll/small082256Blckc1693441376.jpg">
        </li>
        <!-- 打印 -->
        <li class="box" ></li>
        <!-- 关于 -->
        <li class="box" ></li>
    </ul>
</div>
</template>

<script>
export default {
  name: 'HomeIndex',
  data () {
    return {}
  },
  methods: {
    handleScroll () {
      console.log('页面正在滚动')
      // // 获取页面滚动距离
      // const scrollTop = document.documentElement.scrollTop || document.body.scrollTop
      // // 根据滚动距离执行相应逻辑...
      // console.log(scrollTop)
      const listItems = document.querySelectorAll('.box')
      const triggerBottom = window.innerHeight / 5 * 3
      listItems.forEach((item, index) => {
        const boxTop = item.getBoundingClientRect().top
        console.log(boxTop)
        if (boxTop < triggerBottom) {
          // box.classList.add('active')
          console.log(`第${index + 1}个li元素的上边框已进入屏幕下方可视范围`)
          item.classList.add('active')
        } else {
          // box.classList.remove('active')
          item.classList.remove('active')
        }
      })
    }

  },
  mounted () {
    window.addEventListener('scroll', this.handleScroll)
    this.handleScroll()
  },
  destroyed () {
    window.removeEventListener('scroll', this.handleScroll)
  }
}
</script>

<style scoped lang="less">
@import '@/style/layout/home/index.less';
</style>
