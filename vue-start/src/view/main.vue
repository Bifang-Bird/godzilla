<style scoped>
    .layout{
        border: 1px solid #d7dde4;
        background: #f5f7f9;
        position: relative;
        border-radius: 4px;
        overflow: hidden;
    }
    .layout-nav{
        width: 420px;
        margin: 0 auto;
        margin-right: 20px;
    }
    .layout-footer-center{
        text-align: center;
    }
    .time{
        font-size: 14px;
        font-weight: bold;
    }
    
    .layout-content{
        padding-top: 1px;
        width: 100%;
        height: 100%;
        border-radius: 3px;
        font-style: Arial, Helvetica, sans-serif;
    }
</style>
<template>
    <div>
        <Row>
            <i-col span="24">
                <div class="layout">
                    <Layout>
                        <Content class="layout-content">
                            <Card :style="conheight">
                                <p slot="title">
                                    <Icon type="ios-navigate"></Icon>lists
                                </p>
                                <p slot="extra">
                                    <Button @click="load" shape="circle" size="large" icon="ios-film-outline">加载</Button>
                                </p>
                                <Scroll :height = '600'>
                                    <Row style="width:100%;text-align:left">
                                        <Timeline>
                                            <TimelineItem v-for="item,index in notes" :key="index">
                                                <p class="time">{{item.date}}</p>
                                                <p class="content">{{item.content}}</p>
                                            </TimelineItem>
                                        </Timeline>
                                    </Row>
                                </Scroll>
                            </Card>
                        </Content>
                    </Layout>
                </div>
            </i-col>
        </Row>
        <Row :style="{height:'25px'}">
            <i-col span="8" style="padding-top:4px"><i-button size="large" @click="holdBack" shape="circle" icon="ios-arrow-back"></i-button></i-col>
            <i-col span="8" style="padding-top:4px"><i-button size="large" @click="holdMusic" shape="circle" icon="ios-analytics"></i-button></i-col>
            <i-col span="8" style="padding-top:4px"><i-button size="large" @click="holdTask" shape="circle" icon="ios-bookmarks-outline"></i-button></i-col>
        </Row>
    </div>
</template>
<script>
export default {
  data () {
    return {
        username: '',
        contentValue: '',
        userId: '',
        notes: [],
        dispalyMenu: false,
　　　　 conheight:{
            height:''
        }
    }
  },

  mounted() {
    this.conheight.height=window.innerHeight-80+'px';
    //window.innerHeight是浏览器可用高度，this.$refs.table.$el.offsetTop是表格距离浏览器可用高度顶部的距离
  },
  created() {
    this.username = this.$route.params.name;
    this.userId = this.$route.params.usercount;
    console.info(this.$route.params.name+ '  '+ this.userId );
    this.load();
    window.addEventListener('resize', this.setHeight);
    this.setHeight();
  },
  computed:{
  },
  methods: {
    setHeight(){
        this.conheight.height = window.innerHeight-80+"px";
    },
    holdBack(){
        this.$router.push({path:'index'});
    },
    holdTask(){
        this.$router.push({name:'taskmain',params:{usercount: this.userId}});
    },
    holdMusic(){
        this.$router.push({name:'music',params:{usercount: this.userId}});
    },
    //
    async submit() {
        
        if(this.contentValue == ''){
            this.warning();
            return;
        }

        if(this.userId == ''|| this.userId == 0){
            
            this.$Modal.success({
                title: '',
                content: '用户无效!'
            });
            return;
        }
        


        let nowDate = this.getDate(0);

        let postData={
        userId : this.userId,
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
        this.load();

    },

    async load() {
        
        if(this.userId == ''|| this.userId == 0){
            
            this.$Modal.success({
                title: '',
                content: '用户无效!'
            });
            return;
        }
        


        let nowDate = this.getDate(0);


        let { data } = await this.$axios.get(this.$apiTypes.GET_NOTES+this.userId);

        
        if(data != ''){

            this.notes = data;

        }


        console.info(data);

    },

    warning (nodesc) {
        this.$Notice.warning({
        title: 'Notification title',
            desc: nodesc ? '' : '提交内容不能为空！ '
        });
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
