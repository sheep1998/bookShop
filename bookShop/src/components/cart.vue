<template>
  <div>
    <Table
        ref="selection"
       :columns="orderStyle"
       :data="cart"
       @on-selection-change="handleSelectionChange"
    ></Table>
    <div style="margin-top:20px;width:100%;display:flex;flex-direction:row;align-items:center;justify-content:center">
      <span style="color:#fe5400">总价格：￥{{totalPrice}}</span>
      <Button style="margin-left:20px" ghost type="success">购买</Button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'cart',
  data () {
    return {
      mSelection:[],
      cart:[],
      orderStyle:[
        {
          type:"selection",
          width:60,
          align:'center'
        },
        {
          title:"商品图片",
          width:'150px',
          align:'center',
          render:(h,params)=>{
            return h('div',
              {
                attrs:{
                  style:"height:100px;width:150px;display:flex;align-items:center;justify-content:center"
                }
              },[
              h('img',{
              attrs:{
                src: "https://cbu01.alicdn.com/img/ibank/2012/008/444/662444800_995212887.jpg", style: 'height:70px;width: 70px;border-radius: 2px;'
              }
              })
            ])
          }
        },
        {
          title:'商品名称',
          key:'name',
          align:'center',
          render:(h,params)=>{
            return h('div',{
              attrs:{
                style:"height:100px;width:100%;display:flex;align-items:center;justify-content:center"
                }
              },[
                h('Tooltip',{
                  props:{
                    placement:"bottom"
                  }
                },[
                  h('span',{
                    style:{
                      display:'inline-block',
                      width: params.column._width*0.9+'px',
                      overflow: 'hidden',
                      textOverflow: 'ellipsis',
                      whiteSpace: 'nowrap',
                    }
                  },params.row.name),
                  h('span',{
                    slot:'content',
                    style: { whiteSpace: 'normal', wordBreak: 'break-all' }
                  },params.row.name)
                ])
              ])
          }
        },
        {
          title:'数量',
          key:'number',
          width:'200px',
          align:'center',
          render:(h,params)=>{
            return h('div',[
              h('InputNumber',{
                props:{
                  value:this.cart[params.index].number,
                  min:0
                },
                on:{
                  'on-change':(event)=>{
                    params.row.number = event
                    this.cart[params.index] = params.row
                    for(let i=0; i<this.mSelection.length; i++){
                      if(this.mSelection[i].coding === params.row.coding){
                        this.mSelection[i].number = event
                      }
                    }
                    this.$cookies.set("cart",{cart:this.cart},60*60*12)
                  }
                }
              },'')
            ])
          }
        },
        {
          title:'单价',
          key:'price',
          width:'200px',
          align:'center'
        },
        {
          title:'操作',
          align: 'center',
          width: '400px',
          render:(h,params)=>{
            return h('div',[
              h('Button',{
                props:{
                  size:'small',
                  icon:'ios-paper-outline',
                },
                on:{
                  click:()=>{
                    this.$router.push({ path: '/detail',query:{ coding:params.row.coding } })
                  }
                }
              },'详情')
            ])
          }
        }
      ],
    }
  },
  mounted(){
    this.cart = this.$cookies.get("cart").cart
  },
  methods:{
    handleSelectionChange(val){
      this.mSelection = val
    }
  },
  computed:{
    totalPrice:function(){
      let totalPrice = 0
      for(let i=0;i<this.mSelection.length;i++){
        totalPrice += this.mSelection[i].number * this.mSelection[i].price
      }
      return totalPrice
    }
  },
}
</script>

<style scoped>

</style>
