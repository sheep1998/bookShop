<template>
  <div>
    <mu-appbar style="height:65px" color="primary">
      <Icon type="ios-apps-outline" style="margin-left:20px" size="30" slot="left" @click="toggle(true)"/>
      <div class="search-box" style="margin-left:40px" slot="left">
        <div class="search-input" slot="left">
          <Input v-model="searchValue" placeholder="请输入商品名称..."></Input>
          <Button type="primary" icon="ios-search">搜索</Button>
        </div>
      </div>
      eShop
      <mu-button @click="login" v-if="!currUserFlag" style="width:70px" flat slot="right">登陆</mu-button>
      <mu-button @click="register" v-if="!currUserFlag" style="width:70px" flat slot="right">注册</mu-button>
      <mu-button v-if="currUserFlag" style="width:140px" flat slot="right">退出登录</mu-button>
    </mu-appbar>
    <router-view style="margin-top:65px"/>
    <mu-drawer style="background:#2196f3;width:200px" :open="open" :docked="docked" @close="toggle()">
      <mu-list>
        <mu-list-item style="margin-bottom:12px;height:80px;display:flex;justify-content:center;align-items:center">
          <div style="width:100%;display:flex;flex-direction:column;align-items:center;justify-content:center">
            <mu-avatar size="50">
              <img :src="user.avatar">
            </mu-avatar>
            <p style="font-size:15px;color:#ffffff">{{user.name}}</p>
          </div>
        </mu-list-item>
        <mu-list-item v-for="(item,index) in menuItem" :key="index"  @click="jumpToUrl(item.url)" button
          style="margin-bottom:12px;height:48px;background:#2196f3;">
          <mu-list-item-title style="height:48px;color:white;font-size:15px;line-height:48px;text-align:center">
            <Icon size="20" :type="item.ico" style="margin-right:10px"/>
            {{item.name}}
          </mu-list-item-title>
        </mu-list-item>
      </mu-list>
    </mu-drawer>
  </div>
</template>

<script>
import $ from 'jquery';
export default {
  name: 'App',
  data(){
    return{
      currUserFlag:false,
      searchValue:'',
      open:false,
      docked: false,
      menuItem:[
        {
          name:"寻找好书",
          url:"shop",
          ico:"ios-book-outline"
        },
        {
          name:"我的收藏",
          url:"cart",
          ico:"ios-basket-outline"
        },
        {
          name:"购买记录",
          url:"record",
          ico:"ios-bookmarks-outline"
        },
        {
          name:"个人设置",
          url:"mypage",
          ico:"ios-person-outline"
        }
      ]
    }
  },
  computed:{
    user:function(){
     return this.$store.state.user
    }
  },
  methods:{
    toggle(flag){
      this.open = !this.open
    },
    jumpToUrl(url){
      this.$router.push({ path: '/' + url })
      this.open = !this.open
    },
    login(){
      $.post("http://localhost:8080"+"/user/loggin",{"email":"admin","password":"123"},(response)=>{
        this.$store.state.user = response
        this.currUserFlag= true
        }
      )
    },
    register(){
    }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

.mu-appbar{
  text-align: center;
  position: fixed;
  top:0px;
  width:100%
}

.search-box {
    padding: 10px;
}
.search-input {
    display: flex;
    justify-content: center;
}
.search-input>div {
    max-width: 500px;
    min-width: 250px;
    padding-right: 15px;
}
</style>
