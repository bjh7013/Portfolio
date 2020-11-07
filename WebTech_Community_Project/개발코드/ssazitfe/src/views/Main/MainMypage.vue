<template>
  <div class="mainmypage">
    <nav-header></nav-header>
    <div class="row pb-3">
      <my-pagebar class="col-3"></my-pagebar>
      <router-view class="col-9"></router-view>
    </div>
    <nav-footer></nav-footer>
  </div>
</template>

<script>
import MyPagebar from "@/views/Main/MyPagebar.vue";
import NavHeader from "@/views/Main/NavHeader.vue";
import NavFooter from "@/views/Main/NavFooter.vue";
export default {
  name: "MainMypage",
  components: {
    MyPagebar,
    NavHeader,
    NavFooter
  }
};
</script>

<style scoped>
li {
  display: inline-block;
  margin: 0 10px;
}
#container-fluid {
  height: 800px;
}
</style>
