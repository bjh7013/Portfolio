<template>
  <v-footer id="app-footer" padless>
    <v-row
      justify="center"
      no-gutters
    >
      <v-btn
        v-for="link in links"
        :key="link"
        color="grey"
        text
        rounded
        class="my-2"
      >
        {{ link }}
      </v-btn>
    </v-row>
    <v-col
      class="lighten-2 py-4 text-center grey--text"
      cols="12"
    >
      {{ new Date().getFullYear() }} — <strong>Vuetify</strong>
    </v-col>
  </v-footer>
</template>

<script>
export default {
  data() {
    return {
      responsive: false,
      links: [
        'Home',
        'About Us',
        'Team',
        'Services',
        'Blog',
        'Contact Us',
      ],
    }
  },
}
</script>

<style>

</style>