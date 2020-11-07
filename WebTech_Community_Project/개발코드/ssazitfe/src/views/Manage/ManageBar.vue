<template>
  <div class="col-2 order" id="sticky-sidebar">
    <v-card :elevation="0" class="mx-auto bor" height="1000px" width="256">
      <v-navigation-drawer permanent>
        <v-list>
          <v-list-item v-for="item in items" :key="item.title" link :to ="item.link">
            <v-list-item-content>
              <v-list-item-title><b>{{ item.title }}</b></v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-navigation-drawer>
    <!-- <router-view></router-view> -->
    </v-card>
  </div>
</template>

<script>
export default {
  name: "managebar",
  data (){
    return {
      items: [
        { title: '임시태그관리', link:"/managepage"},
        { title: '정식태그관리', link:"/tag"},
      ],
    }
  },
  components: {

  },
};
</script>

<style scoped>
#sticky-sidebar {
  padding: 0px;
}
</style>
