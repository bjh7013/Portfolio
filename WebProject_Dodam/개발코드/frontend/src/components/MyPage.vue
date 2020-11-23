<template>
	<v-app>
		<nav-bar></nav-bar>
		<v-container>
			<div>
				<h2>내 정보</h2>
				<v-row id="left">
					<v-col cols="10">
						<v-divider></v-divider>
						<v-card :elevation="0">
							<v-row>
								<v-col cols="2">
									<v-card :elevation="0" height="170px">
										<b>프로필 사진</b>
									</v-card>
								</v-col>
								<v-col>
									<v-card :elevation="0" height="170px">
										<!-- <div class="profile_photo">
											<img class="profile-image" :src="userImage" width="150px" height="150px">
										</div> -->
										<!-- <div :class="{ active: isActive }"> 
											<input type="file" round class="change-profile-image" @change="onFileChange" ref="fileupload"> 
											<button v-on:click="imgUpdateSubmit()">적용</button>&nbsp;
											<button v-on:click="imgUpdateCancel()">취소</button>
										</div> -->
										<!-- <div id="profile_btn" :class="{ active: !isActive }">
											<v-btn text small v-on:click="toggle"><v-icon small>mdi-pencil</v-icon>수정</v-btn>
											<v-btn text small><v-icon small>mdi-delete</v-icon>삭제</v-btn>
										</div> -->
									</v-card>
								</v-col>
							</v-row>
						</v-card>
						<v-divider></v-divider>
						<v-card :elevation="0">
							<v-row>
								<v-col cols="2">
									<v-card :elevation="0" height="30px">
										<b>별명</b>
									</v-card>
								</v-col>
								<v-col>
									<v-card :elevation="0" height="30px">
										<div :class="{ active_nickname: !isActive_nickname }">
											<p>
												<input type="text" name="nickname" v-model="userInfo.nickName">
												<v-btn text @click="toggle_nickname"><v-icon small>mdi-pencil</v-icon>수정</v-btn>
											</p>      
										</div>
										<!-- <div :class="{ active_nickname: isActive_nickname }">
											<p>
												<input type="text" name="nickname" v-model="userid" placeholder="닉네임 입력">
												<button @click="toggle_nickname_complete">수정완료</button>
												<button @click="toggle_nickname_cancel">취소</button>
											</p>      
										</div> -->
									</v-card>
								</v-col>
							</v-row>
						</v-card>
						<br><br><br><br><br><br>
					</v-col>
				</v-row>

			</div>
		</v-container>
		<foot-bar></foot-bar>
	</v-app>
</template>

<script>
import axios from "axios"
import NavBar from '@/components/NavBar.vue'
import FootBar from '@/components/FootBar.vue'

export default {
	name: 'mypage',
	components: {
		NavBar,
		FootBar,
	},
	data(){
		return{
			isActive: true,
			isActive_nickname: true,
			userid : ''
		}
	},
	computed:{
    userInfo(){
      return this.$store.getters.userInfo;
    },
  },

	methods: {
		toggle: function () {
      this.isActive = !this.isActive;
    },
    toggle_nickname() {
      this.isActive_nickname = !this.isActive_nickname;
    },
    toggle_nickname_complete(){
      axios.put('/user/nickname',{
          nickName : this.userid,
      }).then(()=>{
        alert("닉네임 변경이 완료되었습니다");
        this.isActive_nickname = !this.isActive_nickname;
        this.$router.go();
      }).catch((error)=>{
        alert(error);
      })
    },
    toggle_nickname_cancel() {
      this.isActive_nickname = !this.isActive_nickname;
      this.userid='';
    },
	}
}
</script>

<style scoped>

</style>