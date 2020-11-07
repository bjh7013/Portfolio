<template>
  <div>
    <!-- 약관 동의 -->
    <v-checkbox
      v-model="agreeCheck"
      color="green"
    >
      <template v-slot:label>
        <div @click.stop="">
          <a href="javascript:;" @click="agreeView = true">약관 내용</a>
          을 확인하였으며, 모두 동의합니다. (필수)
        </div>
      </template>
    </v-checkbox>
    <!-- 약관 상세 보기 -->
    <v-dialog
      v-model="agreeView"
      fullscreen
      hide-overlay
      transition="dialog-bottom-transition"
      scrollable
    >
      <v-card tile>
        <v-toolbar
          flat
          dark
          color="primary"
        >
          <v-btn
            icon
            dark
            @click="agreeView = false"
          >
            <v-icon>mdi-close</v-icon>
          </v-btn>
          <v-toolbar-title>이용약관</v-toolbar-title>
          <v-spacer />
        </v-toolbar>
        <v-card-text>
          <agree-detail />
        </v-card-text>

      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import AgreeDetail from "@/components/AgreeDetail";

export default {
  components: {
    AgreeDetail
  },
  data() {
    return {
      agreeCheck: false,  //이용약관 동의 여부
      agreeView: false,   //이용약관 보기
    }
  },
  watch: {
    agreeCheck() {        // 동의 여부를 store에 저장
      this.$store.dispatch('UserAgree', this.agreeCheck);
    }
  },
}
</script>

<style>

</style>