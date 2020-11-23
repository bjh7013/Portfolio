<template>
  <div>
    <template>
			<v-row justify="center">
				<v-dialog v-model="dialog" persistent	max-width="600px">
					<!-- 버튼 -->
					<template v-slot:activator="{ on, attrs }">
						<v-btn color="primary" dark v-bind="attrs" v-on="on" style="">부모님 등록</v-btn>
					</template>
					<!-- 모달 -->
					<v-card>
						<v-card-title>
							<span class="headline">부모님 검색</span>
						</v-card-title>

						<v-card-text>
							<v-container>
								<v-row>
								<v-col cols="12" sm="6" md="4">
									<v-text-field	label="아이디입력" v-model="parentId" required></v-text-field>
								</v-col>
								</v-row>
							</v-container>
						</v-card-text>

						<v-card-actions>
						<v-spacer></v-spacer>
						<v-btn color="blue darken-1" text @click="dialog = false">닫기</v-btn>
						<v-btn color="blue darken-1" text @click="addParent">등록</v-btn>
						</v-card-actions>
					</v-card>

				</v-dialog>
			</v-row>
    </template>
  </div>
</template>

<script>
import axios from 'axios'
export default {
    data(){
        return {
            dialog: false,
            parentId : ""
        }
    },
    methods : {
        addParent() {
            this.dialog = false
            axios.get(`user/info/${this.parentId}`)
            .then((Response)=>{
                axios.get('user/makeparent/'+Response.data.userNo)
                .then((response)=>{
                    response
                    alert("부모 등록 신청 완료.")
                    this.$router.go()
                })
                .catch((error)=>{
                    error
                    alert("이미 등록되어 있습니다.")
                })
            })
        }
    }
}
</script>

<style scoped>

</style>