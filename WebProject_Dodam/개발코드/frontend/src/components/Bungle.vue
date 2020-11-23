<template>
	<v-app> 
		<div class="header">
      <h1><a href="/contentsmain" style="color:orange">도담도담</a>의 개발자들을 소개합니다.</h1>
      <h4 style="color:grey">
        도담도담은 '아이가 별 탈 없이 자라는 모습'이라는 뜻을 가진 순우리말입니다.<br>
        아이들이 글자를 배우는 처음 과정부터 올바르게 배웠으면 하는 마음에서 도담도담을 개발하였습니다. <br>
        도담도담을 통해 아이들이 즐겁게 공부할 수 있었으면 좋겠습니다.
      </h4>
      
		</div>

    <div>
      <div class="bungles-form">
        <div class="bungles" :class="`bungle`+i" v-for="(developer, i) in developers" :key="i">
          <div class="bungle">
            <BungleItem :developer="developer" />
          </div>
        </div>
      </div>
    </div>
    <div class="footer">
      <div class="text-center">
        <v-dialog
          v-model="dialog"
          width="50%">
          <template v-slot:activator>
            <v-btn
              color="orange lighten-2"
              dark
              style="font-size:120%"
              @click="gomain"
            >
              감사합니다
            </v-btn>
          </template>
          <v-card class="justify-center">
            <h3 style="padding:5%">즐거운 프로젝트였습니다. <br>SSAFY 화이팅</h3>
          </v-card>
        </v-dialog>
      </div>
		</div>
  <Loading />
	</v-app>
</template>

<script>
import Loading from "@/components/Loading";
import BungleItem from "@/components/BungleItem";

export default {
  methods:{
    gomain() {
      this.$router.push('/class')
    },
  },
  data() {
    return {
      dialog: false,
      developers: [
        { 
          team: '팀원', 
          name: '진승화', 
          role: 'Frontend', 
          role2: 'FE_Git Master', 
          review1: '보여주고 싶은건 많았지만', 
          review2: '지금 당장은 시간이 부족해서',
          review3: '마무리가 아쉬웠습니다.',  
          profile: require("@/assets/images/sh.jpg")
        },
        { 
          team: '팀원', 
          name: '박지은',
          role: 'Frontend', 
          role2: 'Designer', 
          review1: '애정가는 도담도담 서비스,', 
          review2: '즐겁게 개발할 수 있어서',
          review3: '좋았던 시간이었습니다.',
          profile: require("@/assets/images/je.jpg")
        },
        { 
          team: '팀장', 
          name: '백정훈', 
          role: 'Frontend', 
          role2: 'Backend', 
          review1: '싸피 마지막 프로젝트', 
          review2: '다들 수고 많으셨습니다',
          review3: '',
          profile: require("@/assets/images/jh.jpg")
        },
        { 
          team: '팀원', 
          name: '이용수', 
          role: 'Frontend', 
          role2: 'Backend', 
          review1: '빡빡한 일정이었지만', 
          review2: '프로젝트를 끝낼 수 있어서',
          review3: '다행입니다.', 
          profile: require("@/assets/images/ys.jpg")
        },
        { 
          team: '팀원', 
          name: '이원우', 
          role: 'Backend', 
          role2: 'BE_Git Master', 
          review1: '시간이 짧아서', 
          review2: '너무 아쉬운',
          review3: '프로젝트였습니다.', 
          profile: require("@/assets/images/ww.jpg")
        },
      ]
    }
  },
  components: {
    BungleItem,
  Loading
  }
}
</script>

<style scoped>
.header {
  font-family: 'Jua', sans-serif;
  margin: 4%;  
}
.footer{
  margin: 2%;  

}
.col {
  padding: 1%;
}

* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}

.title-content {
  text-align: center;
  margin: 0px 0 0 0;
  color:darkseagreen;
  /* position: absolute; */
  z-index: 20;
  width: 100%;
  top: 0;
  left: 0;
}

html, body {
  height: 100%;
}
hr {
  width: 50px;
  height: 4px;
  margin: 20px auto;
  border: 0;
  background: #f3cc0b;
}
.v-card--reveal {
  font-family: 'Jua', sans-serif;
  align-items: center;
  bottom: 0;
  justify-content: center;
  opacity: .7;
  position: absolute;
  width: 100%;
}
.bungles{
  
    /* flex-basis: 0;
    flex-grow: 1; */
    max-width: 90%;
    width: 19%;
}
.bungle{
  flex: auto;
}
.bungles-form{
  display:flex;
  height:90%;    
  justify-content: space-around;
}
@media (min-width:301px) and (max-width: 700px){
  .bungles{
    width: 90%;
    margin: 0 auto;
  }
    
  .bungles-form{
    flex-direction: row;
    flex-wrap:wrap;
    height:100%;
  }
  .bungle0{
    order:1;
  }
  .bungle1{
    order:2;
  }

  .bungle2{
    order:0;
  }

  .bungle3{
    order:3;
  }

  .bungle4{
    order:4;
  }
}
</style>