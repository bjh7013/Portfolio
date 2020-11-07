<template>
  <div>
    <nutri-pre />
    <br>
    <food-history />
    <food-reco />
    <footbar />
  </div>
</template>

<script>
import NutriPre from "@/components/NutriPre";
import FoodHistory from "@/components/FoodHistory";
import FoodReco from "@/components/FoodReco";
import Footbar from "@/components/Footbar";

export default {
  components: {
    NutriPre,
    FoodHistory,
    FoodReco,
    Footbar,
  },
  mounted() {
    // 로그인 안한 상태이면 Login으로 리다이렉트
    if (!this.$cookies.isKey('jwt')) {
      return this.$router.push('/login')
    }
  },
};
</script>
<style>
  .main-cards {
    background-color: #ebebeb;
    border-radius: 5px;
  }
  .main-btn {
    box-shadow: none;
    background-color: white !important;
  }
</style>