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
    <Tabs >
        <TabPane label="TaskList" icon="logo-apple">
            <Scroll :height = '800'>
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
                <Progress vertical :percent="25" />
                <Progress vertical :percent="45" status="active" />
                <Progress vertical :percent="65" status="wrong" />
                <Progress vertical :percent="100" />
                <Progress vertical :percent="25" hide-info />
            </div>
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
        <TabPane label="Muisc" icon="logo-tux">
            <Input search placeholder="Enter something..." v-model="content" @on-search="loadMusic"/>
            <Card v-for="item,index in music" :key="index" style="width:100%">
                <div style="text-align:center">
                    <img :src="item.pic"/>
                    <h4><a :href="item.link" target="">{{item.title}}</a>--{{item.author}}</h4>
                </div>
            </Card>

        </TabPane>
    </Tabs>

</template>
<script>
export default {
  data () {
    return {
        value2: '1',
        tasks: [],
        num: 0,
        music:[],
        content: ''
    }
  },
  created() {

    this.load();
    // this.loadMusic();

  },
  computed:{


  },
  methods: {

    async load() {
        

        let nowDate = this.getDate(0);


        let { data } = await this.$axios.get(this.$apiTypes.GET_TASKS);

        
        if(data != ''){

            this.tasks = data.data;
            this.num = data.num;

        }


        console.info(data);

    },

    async loadMusic() {
        
        let postData={
            content: this.content,
            relation: '',
            token: ''
        }

        let { data } = await this.$axios.post(this.$apiTypes.GET_MUSICS, postData);

        
        if(data != ''){

            this.music = data.data.result;
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
