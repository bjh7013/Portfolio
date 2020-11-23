<template>
  <div>
    <dictation-make></dictation-make>
		<word-make></word-make>
  </div>
</template>

<script>
import DictationMake from '@/components/Class/DictationMake.vue'
import WordMake from '@/components/Class/WordMake.vue'
export default {
    components : {
		DictationMake,
		WordMake
  },
  data(){
    return{

    }
  },
  created(){

  },
  computed: {
    userInfo(){
			return this.$store.getters.userInfo
		},
  }
}
</script>

<style>

</style>