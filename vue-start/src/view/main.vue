<style scoped>
    .layout{
        border: 1px solid #d7dde4;
        background: #f5f7f9;
        position: relative;
        border-radius: 4px;
        overflow: hidden;
    }
    .layout-logo{
        width: 450px;
        height: 38px;
        background: #444850;
        border-radius: 3px;
        float: left;
        position: relative;
        font-style: Arial, Helvetica, sans-serif;
        top: 14px;
        left: 10px;
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
    .content{
        padding-left: 1px;
    }
</style>
<template>
    <div class="layout">
        <Layout>
            <Header :style="{position: 'fixed', width: '100%'}">
                <Menu mode="horizontal" theme="dark" active-name="1">
                    <div class="layout-logo" style="color: white">【知识图谱系统,大数据推荐,web展示服务,内容标注系统,辅助诊断系统】</div>
                    <div class="layout-nav">
                        <MenuItem name="1">
                            <Icon type="ios-navigate"></Icon>
                            item1
                        </MenuItem>
                         <MenuItem name="2">
                            <Icon type="ios-navigate"></Icon>
                            item2
                        </MenuItem>
                        <MenuItem name="4">
                            <Avatar src="https://i.loli.net/2017/08/21/599a521472424.jpg" />
                            {{this.username}}
                        </MenuItem>

                    </div>
                </Menu>
            </Header>
            <Content :style="{margin: '75px 20px 0', background: '#fff', minHeight: '700px'}">
                <Card style="width:100%">
                    <p slot="title">
                        <Icon type="ios-navigate"></Icon>
                         note++
                    </p>
                    <p slot="extra">
                        <Button @click="submit" shape="circle" icon=ios-checkmark>提交</Button>
                    </p>
                    <p>
                        <Input v-model="contentValue" type="textarea" :autosize="{minRows: 3,maxRows: 5}" placeholder="Enter something..." />
                    </p>

                </Card>
                <br>

                <Card style="width:100%;height='400px'">
                    <p slot="title">
                        <Icon type="ios-navigate"></Icon>
                         lists
                    </p>
                    <p slot="extra">
                        <Button @click="load" shape="circle" icon=ios-film-outline>加载</Button>
                    </p>
                    <Scroll :height = '410' :on-reach-bottom='handleRearchBottom'>
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
            <Footer class="layout-footer-center">2017-2018 &copy; godzillaKiller</Footer>
        </Layout>
    </div>
</template>
<script>
export default {
  data () {
    return {
        username: '',
        contentValue: '',
        userId: '',
        notes: []
    }
  },
  created() {
    this.username = this.$route.params.name;
    this.userId = this.$route.params.usercount;
    console.info(this.$route.params.name+ '  '+ this.userId );
    this.load();

  },
  computed:{


  },
  methods: {
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
