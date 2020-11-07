<template>
  <div
    v-infinite-scroll="loadMore"
    infinite-scroll-disabled="loading"
    infinite-scroll-distance="10"
  >
    <v-container fill-height fluid grid-list-xl>
      <v-layout justify-center wrap mt-5>
        <v-flex xs12 md8>
          <card title="음식 검색">

            <v-container py-0>
              <v-layout wrap>
                <v-flex xs12 md12>
                  <v-text-field v-model="foodName" label="음식 이름" @keyup.enter="onSubmit" />
                </v-flex>
                <v-flex xs12 text-center>
                  <v-btn
                    large
                    class="indigo white--text ma-5"
                    rounded
                    color="blue lighten-1"
                    @click="onSubmit"
                  >GO!</v-btn>
                </v-flex>
              </v-layout>
            </v-container>
 
          </card>
          <v-divider class="mx-4" />
        </v-flex>
        
      </v-layout>
    </v-container>
    <router-view />
  </div>
</template>

<script>
import Card from "@/components/Card";
// import axios from 'axios'
export default {
  components: {
    Card,
  },
  data(){
    return {
      foodName: "",
      loading: true,
      shops:[]
    }
  },
  computed: {
    
  },
  methods: {
    async onSubmit(){
      this.$router.push({name:'searchfood', params: {key:this.foodName ,name:this.foodName}});
      // shops:[
      //   {
      //     storeno : '1',
      //     storename : '최고막국수',
      //     branch_name : '막국수',
      //     area : '부산',
      //     tel_no : '010-3131-3131',
      //     address : '부산광역시 진구',
      //   },
      //   {
      //     storeno : '2',
      //     storename : '누고족발',
      //     branch_name : '족발',
      //     area : '부산',
      //     tel_no : '010-3131-3131',
      //     address : '부산광역시 연제구',
      //   },
      // ]
      //  await axios.get('/restaurant')
      // .then((Response)=>{
      //   this.shops = []
      //   this.shops = Response.data;
      // })
    }
  }
};
</script>
