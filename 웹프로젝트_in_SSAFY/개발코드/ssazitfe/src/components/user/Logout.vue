<template>
  <div>
  </div>
</template>

<script>
export default {
    name: 'Logout',
    created(){
        // console.log('로그아웃');
        this.$session.destroy();
        this.$store.dispatch('LOGOUT');
        // console.dir(this.$store);
        this.$router.push('/main');
    }
}
</script>

<style>

</style>>