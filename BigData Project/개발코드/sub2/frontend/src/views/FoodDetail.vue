<template>
  <!-- 4. 상세보기 없는 경우 -->
  <v-container>
    <v-row>
      <v-spacer />
      <div style="margin:15px auto;">
        <h2 style="vertical-align:middle; font-size:27px; margin-top:3px;">
          {{ food_info.food_name }}
        </h2>
      </div>
      <v-spacer />
    </v-row>
    <v-card>
      <v-card-title>칼로리 : {{ food_info.cal || '-' }}  Kcal</v-card-title>
      <v-row style="padding:0px 30px 10px;">
        <span v-for="n in food_info.nutrient" :key="n.nutrient" style="margin:0px 5px;">
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
          v-if="isLogin"
          text
          color="pink"
          :class="{ incomplist : food_info.inComplist }"
          style="width:98%; min-width:320px; margin:10px auto;"
          @click="compList(food_info)"
        >
          비교해보기 
          <span v-if="food_info.inComplist" style="font-weight:bold;">   OK</span>
        </v-btn>
      </v-card-actions>
    </v-card>
    <br>
    <!-- 탭 -->
    <b-tabs style="background-color:white;">
      <!-- 주변 음식점 지도 -->
      <b-tab title="주변 음식점" active>
        <div class="map_wrap" style="margin:30px auto; height:570px;">
          <div id="map" style="width:100%; height:98%; position:relative; overflow:hidden;" />
        </div>
      </b-tab>
      <!-- 상세 품목 리스트 -->
      <b-tab title="상세 품목">
        <v-simple-table class="subfood_table scroll-box" style="overflow:auto; height:600px;">
          <template v-slot:default>
            <tbody>
              <v-expansion-panels v-if="subFoodList.length">
                <v-expansion-panel
                  v-for="item in subFoodList"
                  :key="item.food_no"
                >
                  <v-expansion-panel-header style="font-weight:bold;">
                    [{{ item.manufacturer }}]  {{ item.food_name }}
                  </v-expansion-panel-header>
                  <v-expansion-panel-content>
                    <v-col class="nutrient" style="padding:0px;">
                      칼로리 : {{ item.cal }} Kcal
                    </v-col>
                    <v-row style="margin:0px auto 20px;">
                      <div v-for="n in item.nutrient" :key="n.nutrient" class="nutrient">
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
                      <span v-if="item.inComplist" style="font-weight:bold;">   OK</span>
                    </v-btn>
                  </v-expansion-panel-content>
                </v-expansion-panel>
              </v-expansion-panels>
              <v-expansion-panels v-else style="margin-top:20px;">
                상세 품목이 없습니다.
              </v-expansion-panels>
            </tbody>
          </template>
        </v-simple-table>
      </b-tab>
      <!-- 레시피 동영상 -->
      <b-tab title="레시피">
        <v-card style="box-shadow:none;">
          <div style="margin:10px;">
            <!-- <YoutubeSearch :food="food_info" @search-recipe="onSearchedRecipe" /> -->
            <YoutubeItem :youtubes="searchedRecipe" />
          </div>
          <!-- <router-link to="/youtubes"></router-link> -->
        </v-card>
      </b-tab>
    </b-tabs>
    <!-- 추천 메뉴 -->
    <v-card class="mb-5">
      <v-col cols="12">
        <v-card-text>
          <v-row class="px-5">
            <div>
              <h4 class="pt-1">이 메뉴는 어떤가요?</h4>
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
              v-for="food in food_reco"
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
                  style="width: 180px; margin: 0px auto; text-align: center;"    
                  @click="saveReco(food)"
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
                      color="green" 
                      style="width:98%; min-width:320px; box-shadow:none; color:black; font-weight:bold; margin-top:10px auto;" 
                      @click="detail(reco_info.food_no)"
                    >
                      자세히보기
                    </v-btn>
                  </v-card-actions>
                </div>
              </v-row>
            </v-sheet>
          </v-expand-transition>
        </v-card-text>
      </v-col>
    </v-card>
  </v-container>
</template>

<script>
import Axios from 'axios'
// import YoutubeSearch from '@/components/youtubes/YoutubeSearch'
import YoutubeItem from '@/components/youtubes/YoutubeItem'
// import { AspectPlugin } from 'bootstrap-vue'

// const API_KEY = process.env.VUE_APP_YOUTUBE_API_KEY
// const API_URL = 'https://www.googleapis.com/youtube/v3/search'

export default {
  name : 'Fooddetail',
  components: {
    // YoutubeSearch,
    YoutubeItem
  },
  data() {
    return {
      selectReco: null,
      API_KEY : process.env.VUE_APP_YOUTUBE_API_KEY,
      API_URL : 'https://www.googleapis.com/youtube/v3/search',
      searchedRecipe: [],
      cards: ['Good', 'Best', 'Finest'],
      center : {
        lat : 37.500131499999995,
        lng : 127.03242579999998
      },
      markers : {
        lat : 37.500131499999995,
        lng : 127.03242579999998
      },
      food_info: [],
      subFoodList : [],
      food_reco: [],
      reco_info: [],
    }
  },
  computed:{
    isLogin(){
      return this.$store.getters.isLogin;
    }
  },
  mounted(){
    this.current_loc()
  },
  methods : {
    detail(food_no){
      this.$router.push({name:'fooddetail', params: {key:food_no , food_no:food_no}});
      this.current_loc()
    },
    saveReco(food) {
      this.reco_info = food
    },
    tab_fooddetails() {
      this.$router.push({ name:'FoodDetails'})
    },
    tab_youtuberecipe() {
      this.$router.push({ name:'YoutubeRecipe'})
    },
    tab_restaurant() {
      this.$router.push({ name:'Restaurant'})
    },
    onSearchedRecipe (inputText) {
      Axios.get(this.API_URL, {
        params: {
          key: this.API_KEY,
          part: 'snippet',
          type: 'youtube',
          q: inputText
        }
      }).then(res=> {
        // console.log(res.data.items)
        this.searchedRecipe = res.data.items
      }).catch(() => {
        // console.error(err)
      })
    },
    compList(food_info){
      Axios.post(`/apis/compitem`, {
        food_no : food_info.food_no
      })
      .then(({data}) =>{
        if(data == 'delete'){
          // alert('비교리스트에서 제거되었습니다.')
  
          food_info.inComplist = false;
        }else{
          // alert('비교리스트에 추가되었습니다.')

          food_info.inComplist = true;
          
        }
      })
    },
    getImage () {
      const min = 550
      const max = 560

      return Math.floor(Math.random() * (max - min + 1)) + min
    },
    async current_loc(){
      await navigator.geolocation.getCurrentPosition(this.setMap, this.setMap);

    },
    setMap(pos) {
        
        let lat, lng;
        if(pos.coords){
          lat =  pos.coords.latitude
          lng =  pos.coords.longitude
        }else{
          lat = 37.566826
          lng = 126.9786567
          alert('위치 정보 접근을 허용해주세요')
        }

        var real_loc = {
          lat : lat,
          lng : lng
        }

        this.center = real_loc
        this.markers = real_loc
        // var infowindow = new kakao.maps.InfoWindow({zIndex:1});

        var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
            mapOption = {
                center: new kakao.maps.LatLng(this.center.lat, this.center.lng), // 지도의 중심좌표
                level: 3 // 지도의 확대 레벨
            };  

        // 지도를 생성합니다    
        var map = new kakao.maps.Map(mapContainer, mapOption); 

        // 장소 검색 객체를 생성합니다
        var ps = new kakao.maps.services.Places(); 
        Axios.get(`/apis/fooddetail/${this.$route.params.food_no}`)
        .then( ({data}) => {
          this.food_info = data
          this.onSearchedRecipe(this.food_info.food_name + ' 레시피')

          ps.keywordSearch(this.food_info.food_name, placesSearchCB,
          {
            location: new kakao.maps.LatLng(this.center.lat, this.center.lng),
            radius: 10000
          }); 
          Axios.get(`/apis/relatedfood/${this.food_info.food_no}`)
          .then((response) => {
            this.food_reco = response.data;
          })
          .catch(() => {
            alert('error')
          })
        })
        .catch(() => {
          alert('error')
        })

        Axios.get(`/apis/subfoodlist/${this.$route.params.food_no}`)
        .then( ({data}) => {
          this.subFoodList = data
        })
        .catch(() =>{
          alert('subfoodlist 조회 중 에러 발생')
        })
        // 키워드로 장소를 검색합니다

        // 키워드 검색 완료 시 호출되는 콜백함수 입니다
        function placesSearchCB (data, status) {
          if (status === kakao.maps.services.Status.OK) {

            // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
            // LatLngBounds 객체에 좌표를 추가합니다
            var bounds = new kakao.maps.LatLngBounds();

            for (var i=0; i<data.length; i++) {
              displayMarker(data[i]);    
              bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
            }       

            // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
            map.setBounds(bounds);
          } 
        }

        
        var placeOverlay = new kakao.maps.CustomOverlay({zIndex:1}), 
        contentNode = document.createElement('div'); // 커스텀 오버레이의 컨텐츠 엘리먼트 입니다
        
        // 커스텀 오버레이의 컨텐츠 노드에 css class를 추가합니다 
        contentNode.className = 'placeinfo_wrap';

        // 커스텀 오버레이의 컨텐츠 노드에 mousedown, touchstart 이벤트가 발생했을때
        // 지도 객체에 이벤트가 전달되지 않도록 이벤트 핸들러로 kakao.maps.event.preventMap 메소드를 등록합니다 
        addEventHandle(contentNode, 'mousedown', kakao.maps.event.preventMap);
        addEventHandle(contentNode, 'touchstart', kakao.maps.event.preventMap);

        // 커스텀 오버레이 컨텐츠를 설정합니다
        placeOverlay.setContent(contentNode);  
        // 엘리먼트에 이벤트 핸들러를 등록하는 함수입니다
        function addEventHandle(target, type, callback) {
            if (target.addEventListener) {
                target.addEventListener(type, callback);
            } else {
                target.attachEvent('on' + type, callback);
            }
        }
        // 지도에 마커를 표시하는 함수입니다
        function displayMarker(place) {
            
            // 마커를 생성하고 지도에 표시합니다
            var marker = new kakao.maps.Marker({
                map: map,
                position: new kakao.maps.LatLng(place.y, place.x) 
            });

            // 마커에 클릭이벤트를 등록합니다
            kakao.maps.event.addListener(marker, 'click', function() {
                // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
                
                displayPlaceInfo(place)
                // infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
                // infowindow.open(map, marker);
            });
        }

        // 클릭한 마커에 대한 장소 상세정보를 커스텀 오버레이로 표시하는 함수입니다
        function displayPlaceInfo(place) {
            var content = '<div class="placeinfo">' +
                            '   <a class="title" href="' + place.place_url + '" target="_blank" title="' + place.place_name + '" style="font-weight:normal;">' + place.place_name + '</a>';   

            if (place.road_address_name) {
                content += '    <span title="' + place.road_address_name + '">' + place.road_address_name + '</span>' +
                            '  <span class="jibun" title="' + place.address_name + '">(지번 : ' + place.address_name + ')</span>';
            }  else {
                content += '    <span title="' + place.address_name + '">' + place.address_name + '</span>';
            }                
          
            content += '    <span class="tel">' + place.phone + '</span>' + 
                        '</div>' + 
                        '<div class="after"></div>';

            contentNode.innerHTML = content;
            placeOverlay.setPosition(new kakao.maps.LatLng(place.y, place.x));
            placeOverlay.setMap(map);  
        }
        // alert(pos.coords.latitude)
      }
  },
}
</script>

<style>
#myMap {
  height:300px;
  width: 100%;
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
#map{
  width:95% !important;
  margin: 10px auto 30px;
}
.map_wrap, .map_wrap * {margin:0; padding:0;font-family:'Malgun Gothic',dotum,'돋움',sans-serif;font-size:12px;}
.map_wrap {position:relative;width:100%;height:550px;}
.placeinfo_wrap {position:absolute;bottom:28px;left:-150px;width:300px;}
.placeinfo {position:relative;width:100%;border-radius:6px;border: 1px solid #ccc;border-bottom:2px solid #ddd;padding-bottom: 10px;background: #fff;}
.placeinfo:nth-of-type(n) {border:0; box-shadow:0px 1px 2px #888;}
.placeinfo_wrap .after {content:'';position:relative;margin-left:-12px;left:50%;width:22px;height:12px;background:url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')}
.placeinfo a, .placeinfo a:hover, .placeinfo a:active{color:#fff;text-decoration: none;}
.placeinfo a, .placeinfo span {display: block;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;}
.placeinfo span {margin:5px 5px 0 5px;cursor: default;font-size:13px;}
.placeinfo .title {font-weight: bold; font-size:14px;border-radius: 6px 6px 0 0;margin: -1px -1px 0 -1px;padding:10px; color: #fff;background: #d95050;background: #d95050 url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png) no-repeat right 14px center;}
.placeinfo .tel {color:#0f7833;}
.placeinfo .jibun {color:#999;font-size:11px;margin-top:0;}
.scroll-box::-webkit-scrollbar {
  width: 10px;
}
.scroll-box::-webkit-scrollbar-thumb {
  background-color: #2f3542;
  border-radius: 10px;
}
.scroll-box::-webkit-scrollbar-track {
  background-color: grey;
  border-radius: 10px;
}
</style>