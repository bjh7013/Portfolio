<template>
    <v-card style="height:100%">
        <div class="title-content">
        <p style="margin-top:10%; padding:5% 0%;">{{ developer.team }} {{ developer.name }}</p><hr/>
        </div>
        <v-hover v-slot="{ hover }">
        <v-card>
            <div style="width:100%; height:100%">
            <v-img :src="developer.profile" style="min-height:260px; max-height:680px;">
                <v-expand-transition>
                <div
                    v-if="hover"
                    class="d-flex transition-fast-in-fast-out grey darken-2 v-card--reveal display-5 white--text"
                    style="height: 100%; font-size:105%"
                >
                    {{ developer.review1 }}<br>
                    {{ developer.review2 }}<br>
                    {{ developer.review3 }}
                </div>
                </v-expand-transition>
            </v-img>
            </div>
        </v-card>
        </v-hover>
        <v-card elevation="0" style="padding:1%;">
        {{ developer.role }} <br>
        {{ developer.role2 }}
        </v-card>
    </v-card>
</template>

<script>
export default {
    props: ["developer"],
}
</script>


<style scoped>
.header {
  font-family: 'Jua', sans-serif;
  margin: 4%;  
}

.col {
  padding: 1%;
}

* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}

.title-content {
  text-align: center;
  margin: 0px 0 0 0;
  color:darkseagreen;
  /* position: absolute; */
  z-index: 20;
  width: 100%;
  top: 0;
  left: 0;
}

html, body {
  height: 100%;
}
hr {
  width: 50px;
  height: 4px;
  margin: 20px auto;
  border: 0;
  background: #f3cc0b;
}
.v-card--reveal {
  font-family: 'Jua', sans-serif;
  align-items: center;
  bottom: 0;
  justify-content: center;
  opacity: .7;
  position: absolute;
  width: 100%;
}
</style>