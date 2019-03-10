<template>
  <div>
    <Row>
      <Col v-for="(goods,index) in goodsList" :key="index" :xs="8" :sm="6" :md="4" :lg="4">
        <Card class="goods" :padding="0">
          <div class="goods-box">
            <div class="img-box">
              <img :src="goods.imgs[0]" />
            </div>
            <p class="goods-name">{{ goods.name }}</p>
            <div class="goods-message">
              <p>￥&nbsp;<span>{{ goods.price }}</span></p>
              <p>库存&nbsp;<span>{{ goods.storage }}</span></p>
            </div>
          </div>
          <div  class="add-btn">
            <Icon size="50" @click="toDetail(goods.coding)" type="ios-paper-outline" title="商品详情"/>
            <Icon size="50" @click="addToCart(goods)" style="margin-left:30px" type="ios-add-circle" title="加入收藏"/>
          </div>
        </Card>
      </Col>
    </Row>
    <p class="no-goods" v-if="!allGoodsList.length">当前没有商品，敬请期待</p>
    <Page @on-change="changePage" v-if="pageFlag" :current="currPage" :total="pageTotal" :page-size="24" show-elevator class="page"></Page>
  </div>
</template>

<script>
import $ from 'jquery';
export default {
  name: 'shop',
  data () {
    return {
      currPage: 1,
      cart:[]
    }
  },
  methods:{
    changePage (num) {
      this.currPage = num;
    },
    toDetail(id){
      this.$router.push({ path: '/detail',query:{ coding:id } })
    },
    addToCart(goods){
      console.log(goods.coding)
      for(let i=0;i<this.cart.length;i++){
        if(goods.coding===this.cart[i].coding){
          this.cart[i].number+=1
          this.$cookies.set("cart",{cart:this.cart},60*60*12)
          return
        }
      }
      this.cart.push({
        coding:goods.coding,
        name:goods.name,
        img:goods.imgs[0],
        price:goods.price,
        number:1
      })
      this.$cookies.set("cart",{cart:this.cart},60*60*12)
    }
  },
  mounted(){
    $.get("http://localhost:8080"+"/book/all",(response)=>{
      for(let i=0;i<response.length;i++){
        var str = response[i].imgs
        var arr = str.split('|')
        response[i].imgs = arr
      }
      this.$store.state.goodsList = response
    })
    this.cart = this.$cookies.get("cart").cart
  },
  computed:{
    allGoodsList: function () {
      return this.$store.state.goodsList;
    },
    pageFlag: function () {
      if (this.allGoodsList.length > 24) {
        return true;
      }
      else {
        return false;
      }
    },
    goodsList: function () {
      let goodsList = [];
      let i = (this.currPage - 1) * 24;
      while (i < this.currPage * 24) {
        if (this.allGoodsList[i]) {
          goodsList.push(this.allGoodsList[i]);
        }
        i++;
      }
      return goodsList;
    },
    pageTotal: function () {
      return this.allGoodsList.length;
    }
  }
}
</script>

<style scoped>
.no-goods {
    text-align: center;
    font-size: 1.2em;
}
.page {
    margin: 10px 0;
    display: flex;
    justify-content: center;
}
.goods {
    cursor: pointer;
    color: #2b85e4;
    margin: 5px;
}

.goods-box {
    padding: 10px;
}
.goods-box .img-box {
    width: 100%;
    height: 0px;
    padding-bottom: 100%;
    overflow: hidden;
    margin: 0;
    position: relative;
}
.goods-box .img-box img {
    width: 100%;
    height: 250px;
}
.goods-name {
    height: 42px;
    overflow: hidden;
}
.goods-message {
    display: flex;
}
.goods-message>p {
    width: 50%;
}
.goods-message p span {
    font-weight: bold;
}
.goods-message>p:first-child {
    text-align: left;
}
.goods-message>p:last-child {
    text-align: right;
}
.goods .add-btn {
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    background-color: #dddee1;
    opacity: 0.6;
    z-index: 9;
    display:flex;
    flex-direction:row;
    justify-content: center;
    align-items: center;
    display: none;
}
.goods:hover .add-btn {
    display: flex;
}
</style>
