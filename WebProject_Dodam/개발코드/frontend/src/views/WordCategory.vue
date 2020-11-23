<template>
  <div>
    <img src="../assets/images/leftanimal.png" class="mimg leftimg"/>
    <img src="../assets/images/rightanimal.png" class="mimg rightimg"/>
    <section>
      <div @click="gocontents" class="back_menu"><i class="fas fa-arrow-circle-left fa-1x" style="margin:5px;"></i>학습컨텐츠<p class="nomobile">로 돌아가기</p></div>
    </section>
    <v-container>
      <v-layout row wrap>
        <v-flex xs12 sm6 md3 v-for="(wordItem,index) in categorylist" :key="index">
          <v-card style="margin:2%;">
            <div class="cards">
              <WordItems :wordItem="wordItem"/>
            </div>
          </v-card>
        </v-flex>
      </v-layout>
    </v-container>
  </div>

</template>

<script>
import WordItems from "@/components/WordItems";
import axios from "axios";

export default {
  
    data() {
        return {
            fileName: "img/class/default.jpg",
            files: "",

            userno:"1",
            categorylist: [],

        };
    },
    methods:{
      gocontents() {
        this.$router.push('/contentsmain')
      },
      userFail: function(text) {
        if (text[0] == "0") {
          text = text.substring(1, 5);
          this.userlimit = text;
        }
      },
      getCategory(){
            axios.get('/wordtest/category')
            .then( res => {
                this.categorylist = res.data;
            })
            .catch( err => {
              err
            })
        },
    },
    created(){
      this.getCategory();
    },
    components: {
        WordItems
        }
    }
</script>


<style scoped>

.mimg{
  
  width: auto;
  height: 90vh;    
  position: absolute;
  z-index: 0;
}
.leftimg{
  bottom: 0;
  left: 0;
}
.rightimg{
  
  bottom: 0;
  right: 0;
}

.back_menu {
  display: flex;
  font-family: 'Jua', sans-serif;
  font-size: 40%;
  color:#ffba00;
}
section {
  margin: 1% auto;
  width: 80%;
  font-size: 100px;
  text-align: center;
}
#classcreatemodal *{
  margin: 0;
}
#classcreatemodal{
  margin: 0 10px;
}
#classcreatemodal>div{
  padding: 6px 0;
}
#teenager,#userlimit,#classname{
  width:190px;
  margin: 6px 0;
  display: inline-block;
}
/* #userlimit{
  width: 190px;
} */
.floatleft{
  float:left;
  padding: 0 0 0 10px;
  line-height: 50px;
}
.classcreatemodaldiv2,
.classcreatemodaldiv4{
  display: flex;
  line-height: 50px;
}
#starttime,#endtime{
  border:solid 1px;
  border-color:rgb(200, 200, 200);
  border-radius: 5px;
}
#classname{
  width: 270px;
}
#classdesc{
  resize: none;
  height: 90px;
}

#photo{
  display: none;
}
.haveimg,
.img_upload,
.noimg,
.photo{
  height: 200px;
  width: 200px;
  margin:0px auto;
  line-height: 200px;
}
.noimg{
  border-style:dashed;
  background:violet;
}
.cards{
  padding: 1% 1%;
  border-radius: 50%;
}

.cimg{
  border-radius: 10%;
}
.cfont{
  font-size:1vh;
}

.cti{
  border-radius: 15%;
  background-color: rgb(196, 189, 189);
  height: 5vh;
}

@media (min-width:301px) and (max-width: 700px){
  .nomobile{
    display: none;
  }
}
</style>
