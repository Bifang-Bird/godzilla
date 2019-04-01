<template>
<div>
    <Button @click="value2 = true" shape="circle" size="large">Open</Button>
    <Drawer title="Basic Drawer" placement="left" :closable="false" v-model="value2">
        <p>Some contents...</p>
        <p>Some contents...</p>
        <p>Some contents...</p>
    </Drawer>
        <Row :style="conheight">
            <Progress :percent="contentLenght" stroke-width='15' status="active"></Progress>
            <i-input v-model="contentValue" style="padding:5px;" type="textarea" :on-change="changeContent" :autosize="{minRows: 10,maxRows: 20}" placeholder="Enter something..."></i-input>
        </Row>
        <Row :style="{height:'25px'}">
            <i-col span="4" style="padding-top:4px">&nbsp;</i-col>
            <i-col span="4" style="padding-top:4px">&nbsp;</i-col>
            <i-col span="4" style="padding-top:4px">&nbsp;</i-col>
            <i-col span="4" style="padding-top:4px">&nbsp;</i-col>
            <i-col span="4" style="padding-top:4px"><i-button @click="submit" shape="circle" size="large" icon="ios-checkmark"></i-button></i-col>
            <i-col span="4" style="padding-top:4px"><i-button size="large" @click="holdBack" shape="circle" icon="ios-arrow-back"></i-button></i-col>
        </Row>
</div>
</template>
<script>
    export default {
        data () {
            return {
                value2: false,
                contentHeight: 200,
        　　　　 conheight:{
                  height:'',
                  overflow:false
                },
                usercount: '',
                contentValue: '',
                contentLenght: 0,
            }
        },
        watch:{
          contentValue(val){
            this.contentLenght = this.contentValue.length/2;
          }
        },
        mounted() {
          this.conheight.height= window.innerHeight-110+"px";
          this.contentHeight = window.innerHeight-110;
          //window.innerHeight是浏览器可用高度，this.$refs.table.$el.offsetTop是表格距离浏览器可用高度顶部的距离
        },
        created() {
          window.addEventListener('resize', this.setHeight);
          this.setHeight();
          this.usercount = this.$route.params.usercount;
          // this.loadMusic();
        },
        methods:{
            setHeight(){
              this.conheight.height= window.innerHeight-110+"px";
              this.contentHeight = window.innerHeight-110;
            },
            holdBack(){
              this.$router.push({name:'main',params:{usercount: this.usercount}});
            },
            warning (nodesc) {
              this.$Notice.warning({
              title: 'Notification title',
                  desc: nodesc ? '' : '提交内容不能为空！ '
              });
            },
            changeContent(){
              debugger
              console.info(this.contentValue);
              this.contentLenght = this.contentValue.length;
            },
            async submit() {
                
                if(this.contentValue == ''){
                    this.warning();
                    return;
                }

                if(this.usercount == ''|| this.usercount == 0){
                    
                    this.$Modal.success({
                        title: '',
                        content: '用户无效!'
                    });
                    return;
                }
                


                let nowDate = this.getDate(0);

                let postData={
                userId : this.usercount,
                note : this.contentValue,
                content: this.contentValue,
                date: nowDate
                }

                let { data } = await this.$axios.post(this.$apiTypes.SUBMIT_CONTENT, postData);

                
                if(data == 'success'){

                    this.$Modal.success({
                        title: '',
                        content: '提交成功!'
                    });

                }
                console.info(data);

            },
            getDate(n) {

              var ss = 24 * 60 * 60 * 1000; //一天的毫秒数86400

              var timestamp = new Date().getTime(); //获取当前时间戳

              var date1 = new Date(ss * n + timestamp) //加上n天的国际标准日期

              var newTime = date1.toLocaleString(); //把日期转换成2018/6/4 下午10:45:19 格式

              var arr = newTime.split(" "); //把2018/6/4提取出来

              var arr2 = arr[0].split('/'); //把年月日数字单独提出来

              return arr2[0] + '年' + arr2[1] + '月' + arr2[2] + '日'+' '+ arr[1]; //拼接成我们需要的格式返回

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
    margin-top: 10px;
}
.ivu-progress {
    display: inline-block;
    width: 95%;
    font-size: 12px;
    position: relative;
}
</style>
