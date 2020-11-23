<template>
  <div>
		<v-data-table
			:headers="headers"
			:items="desserts"
			class="elevation-1"
		></v-data-table>
  </div>
</template>

<script>
export default {
	name: 'classhomework',
	data() {
		return {
			headers: [
				{
					text: '번호',
					align: 'start',
					value: 'noti_num',
				},
				{ text: '제목', value: 'noti_name' },
				{ text: '날짜', value: 'createday' },
			],
			desserts: [
				{
					noti_num: 1,
					noti_name: '공지사항 1',
					createday: '11/07',
				},
				{
					noti_num: 2,
					noti_name: '공지사항 2',
					createday: '11/08',
				},
			]
		}
	}
}
</script>

<style>

</style>