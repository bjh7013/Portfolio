<template>
  <div>
    <router-view />
  </div>
</template>

<script>
export default {
  // 마이페이지입니다.
  created() {
    // 로그인하고 오시오.
    if (!this.$cookies.isKey('jwt')) {
      return this.$router.push('/login')
    }
  }
}
</script>

<style>

</style>