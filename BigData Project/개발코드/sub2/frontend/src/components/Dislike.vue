<template>
  <v-container>
    <v-simple-table>
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left">이름</th>
          <th class="text-left">사유</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in desserts" :key="item.name">
          <td>{{ item.name }}</td>
          <td>{{ item.reason }}</td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
    <v-row align="center">
      <div class="my-2">
          <v-btn small>추가</v-btn>
      </div>
      <div class="my-2">
          <v-btn small>삭제</v-btn>
      </div>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name : 'dislike',
  data(){
    return {
      desserts : [
        {
          name : '토마토',
          reason : '물컹함'
        },
        {
          name : '바나나',
          reason : '텁텁함'
        }
      ]
    }
  }
}
</script>

<style>

</style>