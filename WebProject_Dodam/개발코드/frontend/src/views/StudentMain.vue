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
  </v-app>
</template>

<script>
import NavBar from '@/components/NavBar.vue'
import ClassSearch from '@/views/ClassSearch.vue'
import FootBar from '@/components/FootBar.vue'

export default {
  name: 'studentmain',
  components: {
    NavBar,
    ClassSearch,
    FootBar
  }
}
</script>

<style scoped>

</style>
