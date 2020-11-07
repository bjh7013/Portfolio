<template>
  <v-container>
    <div class="searchInput">
      <input v-model="inputText" type="text" style="border: 1px solid #000000;" @keypress="onSearchRecipe">
      <v-btn class="searchpoint" @click="onSearchRecipe"><v-icon>mdi-magnify</v-icon></v-btn>
    </div>
  </v-container>
</template>

<script>
export default {
  name: 'YoutubeSearch',
  props: ['food'],
  data () {
    return {
      inputText: null,
      subFoodList : [],
    }
  },
  watch:{
    food(){
      this.inputText = this.food.food_name + ' 레시피'      
    }
  }
  ,
  methods: {
    onSearchRecipe () {
      if (this.inputText) {
        this.$emit('search-recipe', this.inputText)
      }
    },
  },  
}
</script>

<style scoped>
.searchInput {
  display: flex;
  justify-content: right;
  align-items: right;
}

.searchInput>input {
  width: 100%;
  line-height: 2;
}

.searchpoint:hover {
  cursor: pointer;
}
</style>