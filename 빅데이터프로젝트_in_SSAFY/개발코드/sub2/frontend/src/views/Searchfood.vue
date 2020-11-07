<template>
  <div>
    <!-- 1. 그래프 추가 -->
    <v-container>
      <v-simple-table id="result_table">
        <template v-slot:default>
          <tbody>
            <v-expansion-panels v-if="searchResult.length" style="background-color:#f5f5f5;">
              <v-expansion-panel
                v-for="item in searchResult"
                :key="item.food_no"
              >
                <v-expansion-panel-header style="font-size:20px;">{{ item.food_name }}</v-expansion-panel-header>
                <v-expansion-panel-content>
                  <v-col style="padding:0px;">
                    칼로리 : {{ item.cal }} Kcal
                  </v-col>
                  <v-row style="padding:0px 10px 20px;">
                    <span v-for="n in item.nutrient" :key="n.nutrient" style="margin:0px 5px;">
                      <span v-if="n.nutrient == 'carb'">
                        탄수화물 : {{ n.content }} g
                      </span>
                      <span v-else-if="n.nutrient == 'pro'">
                        단백질 : {{ n.content }} g
                      </span>
                      <span v-else-if="n.nutrient == 'fat'">
                        지방 : {{ n.content }} g
                      </span>
                      <span v-else-if="n.nutrient == 'na'">
                        나트륨 : {{ n.content }} mg
                      </span>
                      <span v-else-if="n.nutrient == 'chol'">
                        콜레스테롤 : {{ n.content }} mg
                      </span>
                      <span v-else-if="n.nutrient == 'sug'">
                        당분 : {{ n.content }} g
                      </span>
                    </span>
                  </v-row>
                  <!-- <div class="percent-bar">
                    <div class="carb percent-bar--part">0%</div>
                    <div class="prot percent-bar--part">0%</div>
                    <div class="fat percent-bar--part">0%</div>
                  </div>
                  <v-row style="margin:10px 20px 0px 0px">
                    <span style="font-weight:bolder; font-size:50px; line-height:10px; margin:8px 10px 0px; color:#BF4C3D;">·</span>
                    <span>탄수화물</span>
                    <span style="font-weight:bolder; font-size:50px; line-height:10px; margin:8px 10px 0px; color:green;">·</span>
                    <span>단백질</span>
                    <span style="font-weight:bolder; font-size:50px; line-height:10px; margin:8px 10px 0px; color:#1A7ABA;">·</span>
                    <span>지방</span>
                  </v-row> -->
                  <v-row style="margin:0px auto; padding:0px auto;">
                    <v-btn 
                      v-if="isLogin"
                      text
                      color="pink"
                      :class="{ incomplist : item.inComplist }"
                      style="border-right:solid 5px white; border-left:solid 5px white; width:50%; min-width:320px; box-shadow:none; color:black; font-weight:bold; margin-top:10px;"
                      @click="compList(item)"
                    >
                      비교해보기  
                      <span v-if="item.inComplist" style="font-weight:bold;">   OK</span>
                    </v-btn>
                    <v-btn text color="green" style="border-right:solid 5px white; border-left:solid 5px white; width:50%; min-width:320px; box-shadow:none; color:black; font-weight:bold; margin-top:10px;" @click="detail(item.food_no)">
                      자세히보기
                    </v-btn>
                  </v-row>
                </v-expansion-panel-content>
              </v-expansion-panel>
            </v-expansion-panels>
            <v-expansion-panels v-else style="height:230px; margin-top:150px;">
              검색결과가 없습니다.
            </v-expansion-panels>
          </tbody>
        </template>
      </v-simple-table>
      <br>
      <div>
        <v-row>
          <v-btn 
            text
            style="width:90%; margin:0px auto;" 
            @click="showMore()" 
          >
            상세 품목 더보기
            <v-icon>mdi-chevron-down</v-icon>
          </v-btn>
        </v-row>
        <br>
        <v-simple-table v-show="more">
          <template v-slot:default>
            <tbody>
              <v-expansion-panels v-if="subFoodList.length" style="background-color:#f5f5f5;">
                <v-expansion-panel
                  v-for="item in subFoodList"
                  :key="item.food_no"
                >
                  <v-expansion-panel-header style="font-weight:bold; font-size:16px;">{{ item.food_name }}</v-expansion-panel-header>
                  <v-expansion-panel-content>
                    <v-col class="nutrient" style="font-size:15px;">
                      칼로리 : {{ item.cal }} Kcal
                      <!-- 평점 <v-rating v-model="item.score" /> -->
                    </v-col>
                    <v-row style="margin:0px auto;">
                      <div v-for="n in item.nutrient" :key="n.nutrient" class="nutrient ml-4 mr-3">
                        <span style="font-size:15px;">
                          <span v-if="n.nutrient == 'carb'">
                            탄수화물 : {{ n.content }} g
                          </span>
                          <span v-else-if="n.nutrient == 'pro'">
                            단백질 : {{ n.content }} g
                          </span>
                          <span v-else-if="n.nutrient == 'fat'">
                            지방 : {{ n.content }} g
                          </span>
                          <span v-else-if="n.nutrient == 'na'">
                            나트륨 : {{ n.content }} mg
                          </span>
                          <span v-else-if="n.nutrient == 'chol'">
                            콜레스테롤 : {{ n.content }} mg
                          </span>
                          <span v-else-if="n.nutrient == 'sug'">
                            당분 : {{ n.content }} g
                          </span>
                        </span>
                      </div>
                    </v-row>
                    <v-btn 
                      v-if="isLogin"
                      text
                      color="pink"
                      :class="{ incomplist : item.inComplist }"
                      style="width:98%; min-width:320px; margin:10px auto;"
                      @click="compList(item)"
                    >
                      비교해보기
                      <span v-if="item.inComplist" style="font-weight:bold;">  OK</span>
                    </v-btn>
                  </v-expansion-panel-content>
                </v-expansion-panel>
              </v-expansion-panels>
              <v-expansion-panels v-else style="height:230px; margin-top:150px;">
                상세 품목이 없습니다.
              </v-expansion-panels>
            </tbody>
          </template>
        </v-simple-table>
      </div>
      <!-- </v-parallax> -->
    </v-container>
  </div>
</template>

<script>
import Axios from 'axios'
export default {
  name : 'Searchfood',
  data() {
    return {
      result : [],
      more: false,
      subFoodList : [],
      updateNeeded: true,
      carbPercent: 0,
      protPercent: 0,
      fatPercent: 0,
      openCarb: 0,
      openProt: 0,
      openFat: 0,
      openTotal: 0,
    }
  },
  computed:{
    searchResult(){
      return this.$store.getters.searchResult;
    },
    isLogin(){
      return this.$store.getters.isLogin
    }
  },
  watch:{
    searchResult : function(){
      this.more = false;
      this.subFoodList = [];
      this.updateNeeded = true;
    }
  },
  mounted(){
    this.$store.dispatch('searchFood', this.$route.params.name)
  },
  methods: {
    compList(food_info){
      Axios.post(`/apis/compitem`, {
        food_no : food_info.food_no
      })
      .then(({data}) =>{
        if(data == 'delete'){
          food_info.inComplist = false;
        }else{
          food_info.inComplist = true;
          
        }
      })
    },
    detail(food_no){
      
      this.$router.push({name:'fooddetail', params: {key:food_no , food_no:food_no}});
    },
    showMore(){
      this.more = !this.more
      if(this.more && this.updateNeeded){
        Axios.get(`/apis/searchsubfood/${this.$route.params.name}`)
        .then( ({data}) => {
          this.subFoodList = data;
        })
        .catch( () => {
          alert('error')  
        })
      }

      this.updateNeeded = false;
    },
    
    // setPercentBar(carb, prot, fat, target){
    //   // var $carb = document.getElementsByClassName('carb')[0]
    //   // var $prot = document.getElementsByClassName('prot')[0]
    //   // var $fat  = document.getElementsByClassName('fat')[0]

    //   // console.dir(target)
    //   let el = target.nextElementSibling
    //   //  console.dir(el)
    //   if(el){
    //     // $carb.html(carb + '%').css({ width : carb + '%'});
    //       // $prot.html(prot + '%').css({ width : prot + '%'});
    //       // $fat.html(fat + '%').css({ width : fat + '%'});
    //       // console.log(typeof(el))
    //       var $carb = el.getElementsByClassName('carb')[0]
    //       var $prot = el.getElementsByClassName('prot')[0]
    //       var $fat = el.getElementsByClassName('fat')[0]
          
    //       if($carb && $prot && $fat){
    //         $carb.style.width = carb + '%';
    //         $carb.innerHTML = carb + '%';
    
    //         $prot.style.width = prot + '%';
    //         $prot.innerHTML = prot + '%';
    
    //         $fat.style.width = fat + '%'
    //         $fat.innerHTML = fat + '%'; 
    //       }
          
    //     }else{
    //       setTimeout(() => {
    //         this.setPercentBar(carb, prot, fat, target)
    //       }, 200)
    //     }
    //   },
    //   openFood(data) {
    //     let target = event.target;


    //     this.openCarb = 0
    //     this.openProt = 0
    //     this.openFat = 0
    //     this.openTotal = 0
    //     this.carbPercent = 0
    //     this.protPercent = 0
    //     this.fatPercent = 0
        
    //     for (var n of data) {
    //       if (n.nutrient == 'pro') {
    //         this.openProt = n.content * 4
    //       } else if (n.nutrient == 'carb') {
    //         this.openCarb = n.content * 4
    //       } else if (n.nutrient == 'fat') {
    //         this.openFat = n.content * 9
    //       }
    //     }
    //     this.openTotal = this.openCarb + this.openProt + this.openFat
        
    //     if (this.openTotal) {
    //       this.carbPercent = (this.openCarb / this.openTotal) * 100
    //       this.protPercent = (this.openProt / this.openTotal) * 100
    //       this.fatPercent = (this.openFat / this.openTotal) * 100
    //     }

    //     var carb = Math.round(this.carbPercent);
    //     var prot = Math.round(this.protPercent);
    //     var fat = Math.round(this.fatPercent);
        
    //     this.setPercentBar(carb, prot, fat, target)
    //   },
  },

}
</script>

<style>
/* .v-parallax{
  height:1000px !important;
} */
.incomplist{
  border: solid 3px pink !important;
}
.percent-bar {
  width: 100%;
  height: 20px;
  border-radius: 4px;
  overflow: hidden;
}

.percent-bar--part {
  float: left;
  overflow: hidden;
  color: #fff;
  text-align: center;
  font-family: Arial;
  font-size: .9em;
  height: 100%;
  padding-top: 2px;
}

.carb {
  background-color: #BF4C3D;
  width: 0%;
  border-right: 1px solid #fff;
  box-sizing: border-box;
}

.prot {
  background-color: green;
  width: 0%;
  border-right: 1px solid #fff;
}
.fat {
  background-color: #1A7ABA;
  width: 0%;
}
</style>