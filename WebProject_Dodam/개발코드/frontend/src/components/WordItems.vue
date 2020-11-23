<template>
	<router-link :headName="wordItem.categoryName" :to="{name: 'WordTest',params:{categoryNo: categoryItem.categoryNo ,categoryName: categoryItem.categoryName}}">
	<v-card flat class="ma-1 text-xs-center">
		<div class="ctitle">
		<!-- <v-img :src="url+wordItem.categoryImg" aspect-ratio="1" class="cimg"> -->
				
		<v-img :src="$store.getters.BACKEND_URL + wordItem.categoryImg" aspect-ratio="1" class="cimg">
		</v-img>
			<div class="cti">
				<v-card-title primary-title style="display:inline-block">
					<h3 class="cfont">{{wordItem.categoryName}}</h3>
				<!-- <div>{{productItem.description}}</div> -->
				</v-card-title>
			</div>
		</div>   
	</v-card>
	</router-link>
</template>

<script>
    export default {
      props: ["wordItem"],
      data() {
        return {
            categoryItem:this.wordItem,
            url : ""
        };
      },
      created(){	
          this.url = this.$store.getters.BACKEND_URL;
      }
    };
</script>

<style>
.cimg{
	border-radius: 10% 10% 0 0;
}
.cfont{
	font-family: 'Jua', sans-serif;	
	font-size: 120%;
	margin: 0px;	
}
.cti{    
	background-color: #FDD692;
	height: 80%;
}
</style>