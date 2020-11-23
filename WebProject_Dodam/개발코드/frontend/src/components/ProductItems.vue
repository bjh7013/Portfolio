<template>
	<v-card flat class="ma-1 text-xs-center">
		<v-card>
      <v-card-title style="float:right; padding:4%auto;">
        <div v-if="this.$store.getters.userInfo.userNo==productItem.userNo">
          <!-- <i @click="deleteclass(productItem.classNo)" class="fas fa-trash-alt"></i> -->
          <v-row justify="center">
          <i @click="modifyclass(productItem.classNo)" class="fas fa-edit"></i>
            <v-dialog
              v-model="dialog"
              persistent
              max-width="290"
            >
              <template v-slot:activator="{ on, attrs }">
              <!-- <v-btn dark v-bind="attrs" v-on="on"> -->
                <i v-bind="attrs" v-on="on" class="fas fa-trash-alt"></i>
              <!-- </v-btn> -->
              </template>
              <v-card>
              <v-card-title>
                정말로 삭제하시겠습니까?
              </v-card-title>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="green darken-1" text @click="dialog = false">취소</v-btn>
                <v-btn color="green darken-1" text @click="deleteclass(productItem.classNo)">확인</v-btn>
              </v-card-actions>
              </v-card>
            </v-dialog>
          </v-row>
        </div>
      </v-card-title>
			<v-img style="clear:both;" :src="url + productItem.classImg" aspect-ratio="1" class="cimg" @click="moveclass(productItem.classNo)"/>
			<div class="cti">
				<v-card-title primary-title>
					<h3 class="justify-center" style="font-size: 22px;"> 
						{{productItem.className}}
					</h3>
				</v-card-title>
			</div>
		</v-card>
	</v-card>
</template>

<script>
import axios from 'axios'
	export default {
		props: ["productItem"],
		data() {
			return {
        url: this.$store.getters.BACKEND_URL,
        dialog: false,
			};
		},
		methods : {
			moveclass(classno) {
				this.$router.push(`/classdetail/${classno}`);
			},
			modifyclass(classno) {
				classno
				this.$router.push(`/class/modify/${classno}`);
			},
			deleteclass(classno) {
				axios.delete(`class/oneclass/${classno}`)
				.then((response)=>{
					response
					this.$router.go();
				})
				.catch((err)=>{
					err
				})
			}
		}
	};
</script>

<style scoped>

</style>