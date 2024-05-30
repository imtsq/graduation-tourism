<template>
  <div>
    <ServerTitle>门票管理</ServerTitle>
    <div class="container">
        <div>
           <el-button @click="$router.go(-1)"><i class="el-icon-back"></i></el-button>
           <el-button type="primary" @click="dialogVisible = true">添加</el-button>
        </div>
        <!-- 表 -->
        <div>
           <el-table
    :data="tableData"
    style="width: 100%">
    <el-table-column
      label="ID"
      width="200">
      <template slot-scope="scope">
        <span style="margin-left: 10px">{{ scope.row.id }}</span>
      </template>
    </el-table-column>
    <el-table-column
      label="图片"
      width="200"
     >
      <template slot-scope="scope">
        <span style="margin-left: 10px"><img :src="scope.row.url"  class="my_img"></span>
      </template>
    </el-table-column>
    <el-table-column
      label="名字"
      width="200">
      <template slot-scope="scope">
        <span>{{ scope.row.name }}</span>
      </template>
    </el-table-column>
    <el-table-column label="操作">
      <template slot-scope="scope">
        <el-button
          size="mini"
          @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)">删除</el-button>
          <el-button size="mini" type="primary"
          @click="handleUpload(scope.$index, scope.row)">图片</el-button>
         <el-upload
          v-show="false"
          class="upload-demo"
          action="#"
          :http-request="uploadFile"
          :show-file-list="false"
        >
  <button size="mini" type="primary" ref="upload">图片</button>
      </el-upload>
      </template>
    </el-table-column>
  </el-table>
        </div>
    </div>
     <el-dialog :visible.sync="dialogVisible" title="添加门票" @close="closeDialog">
      <el-input v-model="name" placeholder="名称" />
<el-date-picker
      v-model="date"
      type="daterange"
      range-separator="至"
      start-placeholder="开始日期"
      end-placeholder="结束日期">
    </el-date-picker>

    <el-input v-model="price" placeholder="价格" />
      <el-input v-model="num" placeholder="门票数量" />
      <el-button @click="handleClose">关闭弹窗</el-button>
      <el-button @click="handleChoose" type="primary">确认</el-button>
    </el-dialog>
    <!-- 修改 -->
         <el-dialog :visible.sync="dialogVisible1" title="添加门票" @close="closeDialog">
      <el-input v-model="name" placeholder="名称" />
<el-date-picker
      v-model="date"
      type="daterange"
      range-separator="至"
      start-placeholder="开始日期"
      end-placeholder="结束日期">
    </el-date-picker>

    <el-input v-model="price" placeholder="价格" />
      <el-input v-model="num" placeholder="门票数量" />
      <el-button @click="handleClose">关闭弹窗</el-button>
      <el-button @click="handleChoose1" type="primary">确认</el-button>
    </el-dialog>

  </div>
</template>

<script>
import { InsertNewTickets, SelectAllTicketsByorderKey, UpdateTicketsImg, DeleteById, updateTickets } from '@/api/tickets'
export default {
  name: 'TicketIndex',
  data () {
    return {
      name: '',
      price: '',
      date: '',
      num: '',
      tableData: [
        { id: 1, name: 'a', url: '#' }
      ],
      dialogVisible: false,
      dialogVisible1: false,
      // 临时保存行数
      rowid: '',
      rowurl: ''
    }
  },
  methods: {
    async handleChoose1 () {
      this.date = this.date[0] + '@' + this.date[1]
      const resultInfo = await updateTickets({
        id: this.rowid,
        name: this.name,
        date: this.date,
        price: this.price,
        num: this.num,
        url: this.rowurl,
        orderkey: 1
      })
      this.tip(resultInfo.msg)
      this.dialogVisible1 = false
      this.SelectAllTicketsByorderKey()
    },
    async handleChoose () {
      this.date = this.date[0] + '@' + this.date[1]
      console.log({
        name: this.name,
        date: this.date,
        price: this.price,
        num: this.num,
        orderkey: 1
      })
      await this.InsertNewTickets({
        name: this.name,
        date: this.date,
        price: this.price,
        num: this.num,
        orderkey: 1
      })
      this.SelectAllTicketsByorderKey()
    },
    async InsertNewTickets (data) {
      console.log(data)
      const resultInfo = await InsertNewTickets(data)
      this.tip(resultInfo.msg)
    },
    // 查询
    async SelectAllTicketsByorderKey () {
      const resultInfo = await SelectAllTicketsByorderKey(1)
      this.tableData = resultInfo.data
    },
    handleClose () {
      this.dialogVisible = false
      this.dialogVisible1 = false
    },
    closeDialog () {
      this.dialogVisible = false
      this.dialogVisible1 = false
    },
    async uploadFile (params) {
      const data = new FormData()
      data.append('file', params.file)
      data.append('id', this.rowid)
      await this.UpdateTicketsImg(data)
      await this.SelectAllTicketsByorderKey()
    },
    async UpdateTicketsImg (data) {
      const resultInfo = await UpdateTicketsImg(data)
      this.tip(resultInfo.msg)
    },
    handleUpload (index, row) {
      this.rowid = row.id
      this.$refs.upload.click()
    },
    async handleDelete  (index, row) {
      const data = new FormData()
      data.append('id', row.id)
      data.append('url', row.url)
      // { id: parseInt(row.id), url: row.url }
      const resultInfo = await DeleteById(data)
      this.SelectAllTicketsByorderKey()
      this.tip(resultInfo.msg)
    },
    async updateTickets () {
      await updateTickets()
    },
    handleEdit  (index, row) {
      this.dialogVisible1 = true
      this.name = row.name
      const str = row.date.split('@')
      this.rowurl = row.url
      this.rowid = row.id
      this.date = str
      this.price = row.price
      this.num = row.num
      console.log(this.date)
    },
    tip (msg) {
      this.$message(msg)
    }
  },
  mounted () {
    this.SelectAllTicketsByorderKey()
  }
}
</script>

<style scoped lang="less">
.my_img{
  max-height: 100px;
  max-width: 200px;
}
</style>
