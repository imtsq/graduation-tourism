<template>
  <div class="wrap">
    <div class="pic">
      <img :src="data.url" class="myimg">
    </div>
    <div class="other">
      <div class="title">{{data.name}}</div>
      <div class="time">
        时间
        <el-date-picker
      v-model="value1"
      type="date"
      placeholder="选择时间"
      :picker-options="pickerOptions"
      >
    </el-date-picker>
      </div>

      <div class="price">价格:￥{{data.price}}</div>
      <div class="num"><el-input-number v-model="num" @change="handleChange" :min="1"  label="描述文字"></el-input-number>
      </div>
      <div class="bt">购买</div>
    </div>
  </div>
</template>

<script>
import { SelectTicketById } from '@/api/tickets'
export default {
  data () {
    return {
      data: '',
      value1: '',
      startTime: '',
      endTime: '',
      num: 1,
      pickerOptions: {

      }
    }
  },
  methods: {
    getTime (time) {
      const dataObject = new Date(time)
      return dataObject.getTime()
    },
    setPickerOptions () {
      this.pickerOptions.disabledDate = (time) => {
        return time.getTime() < this.startTime || time.getTime() > this.endTime
      }
    }
  },
  async  created () {
    const resultInfo = await SelectTicketById(this.$route.params.id)
    this.data = resultInfo.data
    console.log(this.data)
    const time = resultInfo.data.date.split('@')
    this.startTime = this.getTime(time[0])
    this.endTime = this.getTime(time[1])
    console.log(this.endTime)
    this.setPickerOptions()
  }
}
</script>

<style scoped lang="less">
@import '@/style/layout/Book/BookDetail.less';
</style>
