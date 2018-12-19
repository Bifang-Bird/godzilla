<style scoped>
    .layout{
        border: 1px solid #d7dde4;
        background: #f5f7f9;
        position: relative;
        border-radius: 4px;
        overflow: hidden;
    }
    .layout-logo{
        width: 100px;
        height: 30px;
        background: #5b6270;
        border-radius: 3px;
        float: left;
        position: relative;
        top: 15px;
        left: 20px;
    }
    .layout-nav{
        width: 420px;
        margin: 0 auto;
        margin-right: 20px;
    }
    .layout-footer-center{
        text-align: center;
    }
</style>
<template>
    <div class="layout">
        <Layout>
            <Header :style="{position: 'fixed', width: '100%'}">
                <Menu mode="horizontal" theme="dark" active-name="1">
                    <div class="layout-logo"></div>
                    <div class="layout-nav">
                    </div>
                </Menu>
            </Header>
            <Content :style="{margin: '110px 20px 0', background: '#fff', minHeight: '700px'}">
                <br>
                <br>
                <br>
                <br>
                <p :style="{margin: '50px 20px 0'}">用-户-名：<Input v-model="username" placeholder="Enter something..." style="width: 300px" /></p>
                <p></p>
                <br>
                <br>
                <p>密----码：<Input v-model="password" placeholder="Enter something..." style="width: 300px" /></p>
                <br>
                <br>
                <p><Button @click="sign">登 录</Button>&nbsp;&nbsp;&nbsp;&nbsp;<Button @click="cancel">重 置</Button></p>
                
            </Content>
            <Footer class="layout-footer-center">2011-2016 &copy; TalkingData</Footer>
        </Layout>
    </div>
</template>
<script>
export default {
  data () {
    return {
        username: '',
        password: ''
    }
  },
  created() {
  },
  computed:{

  },
  methods: {
    //获取目录
    async sign() {
        
        if(this.username == '' || this.password == ''){
            this.warning();
            return;
        }


        let postData={
        count : this.username,
        password : this.password,
        }

        let { data } = await this.$axios.post(this.$apiTypes.CHECKUSERS, postData);

        console.info(data.count);

        if(data.name != ''){

          this.$router.push({name: 'main',params:{ name: data.name, usercount: data.count}});

        }


        console.info(data);

    },
    warning (nodesc) {
        this.$Notice.warning({
        title: 'Notification title',
            desc: nodesc ? '' : '用户名或者密码不能为空！ '
        });
    },
    cancel(){
        this.username = '';
        this.password = '';

    }


  }
}
</script>
