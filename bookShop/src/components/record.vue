<template>
  <div>
    <Table
       :columns="orderStyle"
       :data="orderList"
    ></Table>
  </div>
</template>

<script>
import $ from 'jquery';
export default {
  name: 'cart',
  data () {
    return {
      orderStyle:[
        {
          title:'订单号',
          key:'orderId',
          align:'center'
        },
        {
          title:'总金额',
          key:'totalPrice',
          width:'300px',
          align:'center'
        },
        {
          title:'日期',
          key:'startDate',
          width:'300px',
          align:'center',
          sortable:true
        },
        {
          title:"订单状态",
          key:'orderStatus',
          align:'center',
          width:'300px',
          tooltip:true
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
                    this.$router.push({ path: '/order',query:{ orderId:params.row.orderId } })
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
    /*$.get("http://localhost:8080"+"/order/all",(response)=>{
      this.$store.state.orderList = response
    });*/
    var _this = this;
    $.ajax({
      type:"GET",
      dataType:"json",
      async:"false",
      url:"http://localhost:8080/order/all",
      xhrFields:{
        withCredentials: true
      },
      crossDomain:true,
      success:function(response){
        _this.$store.state.orderList = response
      }
    })
  },
  computed:{
    orderList:function(){
      return this.$store.state.orderList;
    },
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
