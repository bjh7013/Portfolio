<template>
	<div>
		<div>
			<v-row style="margin:auto 10px;">
				<!-- <v-col cols="4" v-for="(wishjoin,index) in wishjoinlist" :key="index"> -->
				<v-flex xs12 sm6 md3 v-for="(wishjoin,index) in wishjoinlist" :key="index" style="margin:3px">
					<v-card>
						<div style="display:flex;">
							<div style="margin:auto;">
								<v-img
							:src="url + wishjoin.profileImg"
							height="100px"
							width="100px"
							></v-img>
							</div>
							<v-col cols="7">
								<p>{{wishjoin.userName}}({{wishjoin.userId}})</p>
								<p>{{wishjoin.birthDate | birth_sort}}</p>
								<v-btn @click="acceptjoin(wishjoin.studentNo,index)">승인</v-btn>
								<v-btn @click="rejectjoin(wishjoin.studentNo,index)">거절</v-btn>
							</v-col>
						</div>
					</v-card>
				<!-- </v-col> -->
				</v-flex>
			</v-row>	
		</div>
	</div>
</template>

<script>
import axios from 'axios'
export default {
	data(){
		return{
			classInfo : [],
			wishjoinlist : []
		}
	},
	created() {
		
		this.get_wantjoin()
	},
	methods : {
		get_wantjoin() {
			axios.get(`class/oneclass/`+this.$route.params.classno)
        .then((Response)=>{
			this.classInfo = Response.data
			if(this.classInfo.userNo == this.userInfo.userNo){ //관리자일때
                axios.get(`class/callwaitclass/`+this.$route.params.classno)
                .then((res)=>{
                    this.wishjoinlist = res.data
                })
                .catch((err)=>{
                    err
                })
            }
        })
        .catch((error)=>{
            error
        })
		},
		acceptjoin(userno,index) {
            let data = {
                classNo : this.$route.params.classno,
                studentNo : userno,
                isApproved : 1
            }
            axios.put('class/classjoin',data)
            .then((Response)=>{
                Response
				alert("가입 수락 완료.");
				this.wishjoinlist.splice(index,1)
            })
        },
        rejectjoin(userno,index){
			let data = {
				classNo : this.$route.params.classno,
                studentNo : userno,
                isApproved : 0
            }
            axios.put('class/classjoin',data)
            .then((Response)=>{
				Response
                alert("가입 거절 완료.");
				this.wishjoinlist.splice(index,1)
            })
        }
	},
	computed : {
		userInfo(){
			return this.$store.getters.userInfo
		},
		url(){
			return this.$store.getters.BACKEND_URL
		}
	},
	filters : {
		birth_sort(date){
			date = date.toString()
			return date.substring(0,10)
		}
	}
}
</script>

<style scoped>
</style>