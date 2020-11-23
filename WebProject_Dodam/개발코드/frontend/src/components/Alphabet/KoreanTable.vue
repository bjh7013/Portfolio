<template>
  <v-container>
    <!-- <section>
      <div @click="gocontents" class="back_menu"><i class="fas fa-arrow-circle-left fa-1x" style="margin:5px;"></i>한글공부</div>
    </section> -->
    <section class="ss-icon">
      <!-- <a href="/koreandetail/53"><article><span>하</span></article></a> -->
			<v-card>
				<v-row>
					<v-card>
						<v-col>
							<article class="k_table"><span>ㄱ</span></article>
						</v-col>
						<v-col>
							<article class="k_table"><span>가</span></article>
							<article class="k_table"><span>갸</span></article>
							<article class="k_table"><span>거</span></article>
							<article class="k_table"><span>겨</span></article>
							<article class="k_table"><span>고</span></article>
							<article class="k_table"><span>교</span></article>
							<article class="k_table"><span>구</span></article>
							<article class="k_table"><span>규</span></article>
							<article class="k_table"><span>그</span></article>
							<article class="k_table"><span>기</span></article>
						</v-col>
					</v-card>
				</v-row>
				<v-row>
					<v-card>d</v-card>
				</v-row>
			</v-card>
    </section>
  </v-container>
</template>

<script>
export default {
  name: 'koreandoubleconsonant',
  methods: {
    gocontents() {
      this.$router.push('/alphabet')
    }
  }
}
</script>

<style scoped>
.item-one{
  margin: 5px 10px;
  width: 176px;
  height: 176px;
}
.back_menu {
  display: flex;
  font-family: 'Jua', sans-serif;
  font-size: 250%;
  color:#ffba00;
}

.ss-icon {
  display: flex;
  margin: 10px auto;
  width: 95%;
  font-size: 900%;
  text-align: center;
  
  flex-wrap: wrap;
  justify-content: center;
}

.k_table {
  font-family: 'Hi Melody', cursive;
  display: inline-block;
  position: relative;
  float: left;
  width: 70px;
  height: 70px;
  margin: 10px;
}

article {
  cursor: pointer;
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  width: 100%;
  height: 100%;
  background-color: orange;
  box-shadow: 0 0 6px -2px red, 0 0 45px -20px red;
  color: #fff;
  transform: perspective(300px) rotateY(0deg);
  transition: transform 0.4s linear, background-color 0s linear 0.2s, color 0s linear 0.2s;
}
a:hover article {
  transform: perspective(300px) rotateY(180deg);
  color: #eee;
  transition: transform 0.2s linear, background-color 0s linear 0.1s, color 0s linear 0.1s;
}
a:nth-child(1):hover article {
  background-color: greenyellow;
}
a:nth-child(2):hover article {
  background-color: #00ACED;
}
a:nth-child(3):hover article {
  background-color: #FF0000;
}
a:nth-child(4):hover article {
  background-color: #faf61c;
}
a:nth-child(5):hover article {
  background-color: #47C83E;
}
a:nth-child(6):hover article {
  background-color: #4183C4;
}
a:nth-child(7):hover article {
  background-color: #3399CC;
}
a:nth-child(8):hover article {
  background-color: #EA4C89;
}
a:nth-child(9):hover article {
  background-color: #3F729B;
}
a:nth-child(10):hover article {
  background-color: #FF0084;
}
a:nth-child(11):hover article {
  background-color: #F26300;
}
a:nth-child(12):hover article {
  background-color: #445566;
}
a:nth-child(13):hover article {
  background-color: greenyellow;
}
a:nth-child(14):hover article {
  background-color: #00ACED;
}

span {
  height: 120%;
  transition: transform 0s linear 0.2s, text-shadow 0s linear 0.2s;
  -webkit-font-smoothing: subpixel-antialiased;
  font-smoothing: antialiased;
}
a:hover span {
  transition: transform 0s linear 0.1s, text-shadow 0s linear 0.1s;
  transform: scale(-1, 1);
  text-shadow: 0 0 50px rgba(255, 255, 255, 0.5);
}
</style>