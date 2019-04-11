<style lang="less">
    .demo-Circle-custom{
        & h1{
            color: #3f414d;
            font-size: 28px;
            font-weight: normal;
        }
        & p{
            color: #657180;
            font-size: 14px;
            margin: 10px 0 15px;
        }
        & span{
            display: block;
            padding-top: 15px;
            color: #657180;
            font-size: 14px;
            &:before{
                content: '';
                display: block;
                width: 50px;
                height: 1px;
                margin: 0 auto;
                background: #e0e3e6;
                position: relative;
                top: -15px;
            };
        }
        & span i{
            font-style: normal;
            color: #3f414d;
        }
    }
</style>
<style scoped>
    .contents{
        text-align: left 
    }

</style>
<template>
    <div>
        <Tabs value="TaskList">
            <TabPane label="TaskList" name="TaskList" icon="logo-apple">
                <Scroll :height="contentHeight">
                <Collapse class="contents" v-model="value2" accordion>
                    <Panel v-for="item,index in tasks" :key="index"  style="white-space: wrap;font-size:15px;overflow: hidden;text-overflow: ellipsis"> 
                        <Badge status="processing" />{{item.title}}
                        <br>
                        <p slot="content" style="white-space: wrap;">{{item.description}}
                        <br> 
                        <br> 
                        <Badge status="warning" />{{item.createTime}}
                        </p>
                    </Panel>
                </Collapse>
                </Scroll>
            </TabPane>
            <TabPane label="TaskGraph" icon="logo-windows">
                <div style="height: 100px;">
                    <i-circle
                        :size="250"
                        :trail-width="4"
                        :stroke-width="5"
                        :percent="75"
                        stroke-linecap="square"
                        stroke-color="#43a3fb">
                        <div class="demo-Circle-custom">
                            <h1>{{this.num}}</h1>
                            <p>任务总数</p>
                            <span>
                                总占人数
                                <i>{{this.num}}</i>
                            </span>
                        </div>
                    </i-circle>
                </div>
            </TabPane>
        </Tabs>
        <Row :style="{height:'25px'}">
            <i-col span="8" style="padding-top:4px">&nbsp;</i-col>
            <i-col span="8" style="padding-top:4px">&nbsp;</i-col>
            <i-col span="8" style="padding-top:10px"><i-button size="large" @click="holdBack" shape="circle" icon="ios-arrow-back"></i-button></i-col>
        </Row>
    </div>
</template>
<script>
import aplayer from "vue-aplayer";
export default {
  name: "Aplayer",
  props: ["pdfurl"],
  components: {
    //别忘了引入组件
    aplayer: aplayer
  },
  data () {
    return {
        value2: '1',
        tasks: [],
        num: 0,
        content: '',
        usercount: '',
　　　　 conheight:{
            height:'',
            overflow:'true'
        },
        contentHeight: 200
    }
  },
mounted() {
    this.conheight.height=window.innerHeight-130+"px";
    this.contentHeight = window.innerHeight-130;
    //window.innerHeight是浏览器可用高度，this.$refs.table.$el.offsetTop是表格距离浏览器可用高度顶部的距离
  },
  created() {
    this.usercount = this.$route.params.usercount;
    this.load();
    window.addEventListener('resize', this.setHeight);
    this.setHeight();
    // this.loadMusic();
  },

  methods: {
    setHeight(){
        this.conheight.height= window.innerHeight-130+"px";
        this.contentHeight = window.innerHeight-130;
    },

    async load() {
        
        let nowDate = this.getDate(0);

        let { data } = await this.$axios.get(this.$apiTypes.GET_TASKS);
        
        if(data != ''){

            this.tasks = data.data;
            this.num = data.num;

        }

        console.info(data);

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
