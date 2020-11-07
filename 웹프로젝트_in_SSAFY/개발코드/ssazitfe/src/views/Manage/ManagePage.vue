<template>
  <div class="managepage">
    <manage-header></manage-header>
    <div class="row">
      <manage-bar></manage-bar>
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import ManageHeader from "@/views/Manage/ManageHeader.vue";
import ManageBar from "@/views/Manage/ManageBar.vue";
export default {
  name: "Managepage",
  components: {
    ManageHeader,
    ManageBar,
  }
};
</script>

<style scoped>
/* li {
  display: inline-block;
  margin: 0 10px;
}
#container-fluid {
  height: 800px;
} */
</style>
