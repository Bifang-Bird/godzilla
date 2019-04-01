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
    .layout-content{
        text-align: center;
        width: 100%;
        padding-bottom: 60%;
    }
</style>
<template>
    <div class="layout">
        <Card :style="conheight">
            <Layout>
                <Carousel :height = '200'>
                    <Carousel-item>
                        <div class="demo-carousel"><img :src="imgSrc" /></div>
                    </Carousel-item>
                    <Carousel-item>
                        <div class="demo-carousel">2</div>
                    </Carousel-item>
                    <Carousel-item>
                        <div class="demo-carousel">3</div>
                    </Carousel-item>
                    <Carousel-item>
                        <div class="demo-carousel">4</div>
                    </Carousel-item>
                </Carousel>
                <Content class="layout-content">  
                    <br>
                    <p>用户名：<Input v-model="username" prefix="ios-contact" size="large" placeholder="Enter something..." style="width: 300px" /></p>
                    <p></p>
                    <br>
                    <br>
                    <p>密---码：<Input v-model="password" prefix="ios-alert" size="large" placeholder="Enter something..." style="width: 300px" /></p>
                    <br>
                    <br>
                    <p><i-button @click="sign" icon="ios-person" shape="circle" size="large">登 录</i-button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<Button size="large" @click="cancel" icon="ios-trash" shape="circle">重 置</Button></p>
                </Content>
                <Footer class="layout-footer-center">2019-2020 &copy; junwang</Footer>
            </Layout>
        </Card>
    </div>
</template>
<script>
export default {
  data () {
    return {
        username: '',
        password: '',
        conheight:{
            height:"",
        },
        imgSrc: require("../assets/logo.png")
    }
  },
  created() {
    window.addEventListener('resize', this.setHeight);
    this.setHeight();
  },
  mounted() {
    this.conheight.height=window.innerHeight-40+"px";
    //window.innerHeight是浏览器可用高度，this.$refs.table.$el.offsetTop是表格距离浏览器可用高度顶部的距离
  },
  methods: {
    setHeight(){
        this.conheight.height = window.innerHeight-40+"px";
    },
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

          this.$router.push({name: 'main',params:{ name: data.name, usercount: data.id}});

        }


        console.info(data);

    },
    warning (nodesc) {
        this.$Notice.error({
        top: 150,
        duration: 2,
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
