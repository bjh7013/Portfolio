<template>
  <div class="container">
    <div>
      <ul>
        <li v-for="(food, i) in foodimg" :key="i">
          <img :src="food.img">
          {{ food.img }}
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    imgfoods: { type: Array, default: () => []}
  }
}
</script>

<style>

</style>