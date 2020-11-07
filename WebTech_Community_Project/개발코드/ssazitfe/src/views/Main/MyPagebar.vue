<template>
  <div class="col-3 order" id="sticky-sidebar" v-if="userInfo!=null && userInfo.userNo>-1">
    <v-card class="mx-auto" height="1000px" width="256">
      <v-navigation-drawer permanent>
        <v-list>
          <v-list-item v-for="item in items" :key="item.title" link :to ="item.link">
            <v-list-item-content>
              <v-list-item-title>{{ item.title }}</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-navigation-drawer>
    </v-card>
  </div>
  <div v-else>
    
  </div>
</template>

<script>
export default {
  name: "mypagebar",
  data (){
    return {
      items: [
        { title: '계정정보', link:"/accounts"},
        { title: '개인정보', link:"/privacy"},
        { title: '쪽지함', link:"/messages"},
        { title: '이웃목록', link:"/friends"},
        { title: '즐겨찾기', link:"/bookmark"},
        { title: '내가쓴답변', link:"/mycomments"},
      ],
    }
  },
  components: {

  },
  computed: {
    userInfo() {
      return this.$store.getters.userInfo;
    }
  }
};
</script>

<style>
#sticky-sidebar {
  padding: 12px 40px 12px 120px; 
}
</style>
