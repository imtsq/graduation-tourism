<template>
  <div>
    <div class="server-container-title">
      <div class="title fl"><slot></slot></div>
      <div class="title-icon fr">
        <i class="el-icon-switch-button"></i>
      </div>
     </div>
  </div>
</template>

<script>
export default {
  name: 'ServerTitleIndex'
}
</script>

<style scoped lang="less">
.server-container-title{
    display: block;

    width: 100%;
    height:50px;
    border: 1px solid #ddd;
    .title {
      font-weight: 20px;
      font-size: 17px;
      line-height: 50px;
      margin-left:20px;
    }
    .title-icon{
      font-size: 25px;
      margin-right:25px;
      line-height: 50px;
    }
  }

</style>
