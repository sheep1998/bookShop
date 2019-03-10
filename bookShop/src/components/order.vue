<template>
  <div>
    <Button style="margin-top:10px;margin-bottom:10px;margin-left:30px" type="success" ghost @click="orderMsg">查询物流</Button>
    <Table
       :columns="orderItemStyle"
       :data="orderItemList"
    ></Table>
    <Modal
      v-model="modal"
      title="物流信息">
      <p>快递单号：{{msg.id}}</p>
      <p>{{msg.position}}</p>
      <p>{{msg.restTime}}</p>
    </Modal>

  </div>
</template>

<script>
import $ from 'jquery';
export default {
  name: 'cart',
  data () {
    return {
      msg:{
        id:"",
        position:"",
        restTime:""
      },
      modal:false,
      orderId:"",
      orderItemList:[
        {
          orderitemId:0,
          bookId:"1",
          name:"213",
          img:"https://cbu01.alicdn.com/img/ibank/2012/008/444/662444800_995212887.jpg",
          number:3,
          tprice:200,
        }
      ],
      orderItemStyle:[
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
          title:'单价',
          key:'tprice',
          width:'300px',
          align:'center'
        },
        {
          title:'数量',
          key:'number',
          width:'300px',
          align:'center',
          sortable:true
        },
        {
          title:'操作',
          align: 'center',
          width: '300px',
          render:(h,params)=>{
            return h('div',[
              h('Button',{
                props:{
                  size:'small',
                  icon:'ios-paper-outline',
                },
                on:{
                  click:()=>{
                    this.$router.push({ path: '/detail',query:{ coding:params.row.bookId } })
                  }
                }
              },'详情')
            ])
          }
        }
      ],
    }
  },
  methods:{
    orderMsg(){
      $.post("http://localhost:8080"+"/order/msg",{"orderId":this.orderId},(response)=>{
        this.msg = response
        this.modal = true
      })
    }
  },
  mounted(){
    this.orderId = this.$route.query.orderId;
    $.post("http://localhost:8080"+"/order/detail",{"orderId":this.orderId},(response)=>{
      for(let i=0;i<response.length;i++){
        var str = response[i].img
        var arr = str.split('|')
        response[i].img = arr[0]
      }
      this.orderItemList = response
    })
  },
}
</script>

<style>
.ivu-table-cell{
  padding-left:0px;
  padding-right:0px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: normal;
  word-break: break-all;
  box-sizing: border-box
}
</style>
