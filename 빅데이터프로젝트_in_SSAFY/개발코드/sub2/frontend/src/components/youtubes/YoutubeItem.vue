<template>
  <div class="scroll-box" style="overflow:auto; height:600px;">
    <YoutubeItemDetail v-for="youtube in youtubes" :key="youtube.etag" :youtube="youtube" />
  </div>
</template>

<script>
import YoutubeItemDetail from '@/components/youtubes/YoutubeItemDetail.vue'

export default {
  name: 'YoutubeItem',
  components: {
    YoutubeItemDetail,
  },  
  props: {
    youtubes: Array,
  }
}
</script>

<style scoped>
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