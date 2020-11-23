<template>
	<v-container>
		<div class="timetable">
      
      <div class="wrapper">
        <table>
          <caption>Timetable</caption>
          <tr>
            <th>Time</th>
            <th>Monday</th>
            <th>Tuesday</th>
            <th>Wednesday</th>
            <th>Thursday</th>
            <th>Friday</th>
            <th>Saturday</th>
            <th>Sunday</th>
          </tr>
          <tr>
            <td>9:00 </td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
          </tr>
          <tr>
            <td>10:00 </td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
          </tr>
          <tr>
            <td>11:00 </td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
          </tr>
          <tr>
            <td>12:00 </td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
          </tr>
          <tr>
            <td>13:00 </td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
          </tr>
          <tr>
            <td>14:00 </td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
          </tr>
          <tr>
            <td>15:00 </td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
          </tr>
          <tr>
            <td>16:00 </td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
          </tr>
          <tr>
            <td>17:00 </td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
            <td contenteditable=""></td>
          </tr>
        </table>
      </div>
		</div>
	</v-container>
</template>

<script>
export default {
	name: 'timetable',
}
</script>

<style scoped>
body {
  background: #ECF0F1;
}

.wrapper {
  margin: 50px auto;
  width: 1000px;
}

table {
  table-layout: fixed;
  width: 100%;
  font-size: 18px;
  font-weight: 300;
}
table caption {
  text-align: center;
  font-size: 1.5em;
  padding: 0.35em;
}
table tr {
  height: 1.85em;
}
table td, table th {
  text-align: center;
  background: #FAFAFA;
}
table th {
  font-weight: 400;
}

</style>