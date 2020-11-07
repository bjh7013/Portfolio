<template>
  <div>
    <nutri-pre /> 
    <br>
    <food-history />
  </div>
</template>

<script>
import NutriPre from "@/components/NutriPre";
import FoodHistory from "@/components/FoodHistory";

export default {
  name: 'NutriPreDetail',
  components: {
    NutriPre,
    FoodHistory,
  }
}
</script>

<style>

</style>