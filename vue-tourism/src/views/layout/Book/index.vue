<template>
  <div  class="main">
      <div class="title"><h1>XXX购票系统</h1></div>
      <div class="container">
        <div class="wrap" v-for="item in tableDate" :key="item.id">
          <router-link :to="`/BookDetail/${item.id}`">
          <img :src="item.url" class="pic">
          <div class="context">
            <div class="context_name">{{item.name}}</div>
            <div class="time">时间: {{item.starttime}} ~ {{item.endtime}}</div>
            <div class="context_bottom">
              <div class="context_num">人数:{{item.num}}</div>
              <div class="context_price">¥{{item.price}}</div>
            </div>
          </div>
          </router-link>
        </div>
      </div>
  </div>
</template>

<script>
import { SelectAllTicketsByorderKey } from '@/api/tickets'
export default {
  name: 'IndexBook',
  data () {
    return {
      pickerOptions: {
        disabledDate (time) {
          console.log(time.getTime())
          return time.getTime() < Date.now()
        }
      },
      value1: '',
      tableDate: [],
      starttime: '',
      endtime: ''
    }
  },
  methods: {
    getTimeFunction (time) {
      const date = new Date(time)

      // 对于这种格式的日期字符串，可以直接转换为Date对象
      // 但为了兼容性，可以使用Date.parse()配合new Date()
      // var date = new Date(Date.parse(dateStr.replace(/ GMT.*$/, '')));

      const formattedTime = date.getFullYear() + '-' +
                   ('0' + (date.getMonth() + 1)).slice(-2) + '-' +
                   ('0' + date.getDate()).slice(-2)
      //  ('0' + date.getHours()).slice(-2) + ':' +
      //  ('0' + date.getMinutes()).slice(-2) + ':' +
      //  ('0' + date.getSeconds()).slice(-2)

      return formattedTime
    }
  },

  async mounted () {
    const resultInfo = await SelectAllTicketsByorderKey(1)
    console.log(resultInfo.data)
    resultInfo.data.forEach(item => {
      const time = item.date.split('@')
      this.starttime = this.getTimeFunction(time[0])
      this.endtime = this.getTimeFunction(time[1])

      item.starttime = this.starttime
      item.endtime = this.endtime
      console.log(item)
      this.tableDate.push(item)
    })
  }

}
</script>

<style scoped lang="less">
@import '@/style/layout/Book/index.less';
</style>
