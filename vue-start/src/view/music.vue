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
    <div style="padding: 1px 0;">
            <div style="padding:1px 0;">
                <aplayer autoplay showlrc='true' :music="musicList">
                </aplayer>
            </div>
            <br>
            <Input search placeholder="Enter something..." v-model="content" @on-search="loadMusic"/>
            <Card v-for="item,index in music" :key="index" style="width:100%">
                <div style="text-align:center">
                    <img :src="item.pic"/>
                    <br>
                    <Button type="primary" ghost @click="player(item.url,item.lrc,item.title,item.author)">{{item.title}}--{{item.author}}</Button>
                    <!-- <router-link  :to="{path:'/music',query: {music:item.url,lrc: item.lrc, title: item.title,author: item.author}}"  :data-types="item.types" class="link-des" :id='item.id'>{{item.title}}--{{item.author}}</router-link> -->
                </div>
            </Card>
        <br>
        <br>

        <!-- <ButtonGroup shape="circle">
            <Button type="primary" @click="$router.back(-1)">
                <Icon type="ios-arrow-back"></Icon>
                Backward
            </Button>
            <Button type="primary">
                Forward
                <Icon type="ios-arrow-forward"></Icon>
            </Button>
        </ButtonGroup> -->

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
  data() {
    return {
      musicList: {
        title: "youkao100",
        author: "youkao100",
        url: 'http://localhost:1122/static/ceshi.mp3',
        pic: "",
        lrc: ""
      },
      lrc: '',
      url: '',
      music: [],
    //   title: '',
      author: '',
    };
  },
  
  created() {
    this.url = this.$route.query.music;
    this.lrc = this.$route.query.lrc;
    // this.title = this.$route.query.title;
    this.author = this.$route.query.author;

  },

  computed: {
      title(){
          return this.$route.query.title;
      }

  },

  mounted() {
    this.musicList = {
      title: this.title,
      author: this.author,
      url: this.url,
      pic: "",
      lrc: this.lrc,
    };
  },

  methods: {

    player(url,lrc,title,author){
      this.musicList = {
        title: title,
        author: author,
        url: url,
        pic: "",
        lrc: lrc,
      }; 

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


  }
};
</script>
