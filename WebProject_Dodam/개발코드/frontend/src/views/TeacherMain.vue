<template>
  <v-app>
    <nav-bar></nav-bar>
      <div>
        <v-container>
          <div class="teachermain">
            <class-search></class-search>
          </div>
        </v-container>
      </div>
    <foot-bar></foot-bar>
  <Loading />
  </v-app>
</template>

<script>
import NavBar from '@/components/NavBar.vue'
import ClassSearch from '@/views/ClassSearch.vue'
import FootBar from '@/components/FootBar.vue'
import Loading from "@/components/Loading"

export default {
  name: 'teachermain',
  components: {
    NavBar,
    ClassSearch,
    FootBar,
    Loading,
  },
  created(){
    this.check_teacher()
  },
  methods : {
    check_teacher(){
      if(this.$session.get('jwt')==null){
        // alert('잘못된 접근입니다.')
        this.$router.go(-1)
      }
      if(!this.userInfo || this.userType!='teacher'){
        // alert('잘못된 접근입니다.')
        this.$router.push('/')
      }
    }
  },
  computed : {
    userInfo(){
      return this.$store.getters.userInfo
    },
    userType(){
      return this.$store.getters.userInfo.userType
    }
  }
}
</script>
