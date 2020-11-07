<template>
  <v-container style="margin-bottom:10px;">
    <div class="youtube-item">
      <div class="details">
        <h4 style="text-align:center; font-weight:bold; margin:20px 20px 0px;">
          {{ youtube.snippet.title }}
        </h4>
        <hr>
      </div>
      <div class="embed-responsive embed-responsive-16by9" style="width:98%; margin:10px auto;">
        <iframe :src="youtubeUrl" frameborder="0" class="embed-responsive-item" />
      </div>
    </div>
  </v-container>
</template>

<script>
export default {
  name: 'YoutubeItemDetail',
  props: {
    youtube: Object,
  },
  computed: {
    youtubeUrl () {
      const youtubeId = this.youtube.id.videoId
      return `https://www.youtube.com/embed/${youtubeId}`
    }
  }
}
</script>

<style>

</style>