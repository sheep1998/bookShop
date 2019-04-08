<template>
  <div>
    <Row>
      <Col  :sm="{ span: 16, offset: 4 }" :md="{ span: 12, offset: 6 }" :lg="{ span: 12, offset: 6 }">
        <Form  :label-width="100">
          <FormItem label="商品图片">
            <swiper :options="swiperOption">
              <swiper-slide  style="height:400px" v-for="(item,index) in book.imgs" :key="index" >
                <img style="width:100%;height:100%" :src="item">
              </swiper-slide>
            </swiper>
          </FormItem>
          <FormItem label="商品名称">
            {{book.name}}
          </FormItem>
          <FormItem label="商品价格">
            {{book.price}}
          </FormItem>
          <FormItem label="商品库存">
            {{book.storage}}
          </FormItem>
          <FormItem label="月销量">
            {{book.monthsale}}
          </FormItem>
        </Form>
      </Col>
      <div style="width:100%;display:flex;flex-direction:row;align-items:center;justify-content:center">
        <Button @click="returnToList" ghost type="primary">返回列表</Button>
        <Button  ghost type="warning" style="margin-left: 20px">加入收藏</Button>
      </div>
    </Row>
  </div>
</template>

<script>
import $ from 'jquery';
import 'swiper/dist/css/swiper.css'
import { swiper, swiperSlide } from 'vue-awesome-swiper'
export default {
  name: 'orderDetail',
  data () {
    return {
      coding:"",
      book:{},
      swiperOption:{
        autoplay:true,
        speed:400,
        grabCursor: true,
        centeredSlides: true,
        loop:true,
        observer:true,//修改swiper自己或子元素时，自动初始化swiper
        observeParents:true,//修改swiper的父元素时，自动初始化swiper
      }
    }
  },
  components:{
    swiper,
    swiperSlide
  },
  mounted(){
    this.coding = this.$route.query.coding
    $.post("http://localhost:8080"+"/book/single",{"coding":this.coding},(response)=>{
      var str = response.imgs
      var arr = str.split('|')
      response.imgs = arr
      this.book = response
    })
  },
  methods:{
    returnToList(){
      this.$router.go(-1)
    },
  }
}
</script>

<style scoped>

</style>
