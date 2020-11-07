<template>
  <div>
    <!-- 추천 메뉴 -->
    <v-card class="mb-5">
      <v-col cols="12">
        <v-card-text>
          <v-row class="px-5">
            <div>
              <h2 class="pt-1">추천 메뉴</h2>
            </div>
            <v-spacer />
          </v-row>
          <hr>

          <v-slide-group
            v-model="selectReco"
            class="pa-0"
            show-arrows
          >
            <v-slide-item
              v-for="food in food_list"
              :key="food.food_no"
              v-slot:default="{ active, toggle }"
            >
              <v-card
                :color="active ? 'grey lighten-1' : 'white'"
                class="ma-4"
                style="width:200px; height:100px; box-shadow:none;"
                @click="toggle"
              >
                <v-row
                  class="fill-height"
                  align="center"
                  justify="center"
                  @click="reco_info = food"
                >
                  <p style="font-size:22px; margin:0px;">{{ food.food_name }}</p>   
                </v-row>
              </v-card>
            </v-slide-item>
          </v-slide-group>

          <v-expand-transition>
            <v-sheet
              v-if="selectReco != null"
              color="grey lighten-4"
              height="200"
              tile
            >
              <v-row
                class="fill-height"
                align="center"
                justify="center"
              >
                <div class="col-12">
                  <!-- 데이터 넘어오면 이거 쓰면 댐 -->

                  <v-card-title>{{ reco_info.food_name }}</v-card-title>
                  <p style="margin:0px 20px;">칼로리 : {{ reco_info.cal || '-' }}  Kcal</p>
                  <v-row style="padding:0px 30px 10px;">
                    <span v-for="n in reco_info.nutrient" :key="n.nutrient" style="margin:0px 5px;">
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
                  <v-card-actions>
                    <v-btn 
                      text
                      color="pink"
                      :class="{ incomplist : reco_info.inComplist }"
                      style="width:98%; min-width:320px; margin:10px auto;"
                      @click="compList(reco_info)"
                    >
                      비교해보기 
                      <span v-if="reco_info.inComplist" style="font-weight:bold;">   OK</span>
                    </v-btn>
                  </v-card-actions>
                </div>
              </v-row>
            </v-sheet>
          </v-expand-transition>
        </v-card-text>
      </v-col>
    </v-card>
  </div>
</template>

<script>
import Axios from 'axios'
export default {
  data() {
    return {
      selectReco: null,
      food_list : [],
      reco_info : ''
    }
  },
  computed : {
    
  },
  created(){
    Axios.get('/apis/recommend')
    .then(({data}) =>{
      this.food_list = data
    })   
  },
  methods:{
    compList(reco_info){
      Axios.post(`/apis/compitem`,{
        food_no : reco_info.food_no
      })
      .then(({data}) =>{
        if(data == 'delete'){
          // alert('비교리스트에서 제거되었습니다.')
          reco_info.inComplist = false;
        }else{
          // alert('비교리스트에 추가되었습니다.')
          reco_info.inComplist = true;
          
        }
      })
      .catch(() =>{
        alert('error')
      })
    }
  }
}
</script>

<style>
  .v-slide-group__prev {
    min-width: 30px !important;
  }
.incomplist{
  border: solid 3px pink !important;
}
.subfood_table{
  margin: 10px auto;
  width:95%;
}
.nav-tabs{
  background-color:#f5f5f5 !important;
}
.v-application a{
  color:black !important;
}
</style>