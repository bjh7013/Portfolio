<template>
  <v-container>
    <div v-if="this.$route.params.name==='막국수'">
        <div v-for="shop in shops" :key="shop.storeno"> 
            <div v-if="shop.storeno==1">
                <v-card class="d-inline-block mx-auto">
                <br>{{shop.storename}}
                {{shop.address}}<br>
                {{shop.tel_no}}<br>
                    <v-container>
                    <v-row justify="space-between">
                        <v-col cols="auto">
                        <v-img
                            height="200"
                            width="200"
                            src="https://cdn.vuetifyjs.com/images/cards/store.jpg"
                        ></v-img>
                        </v-col>

                        <v-col
                        cols="auto"
                        class="text-center pl-0"
                        >
                        <v-row
                            class="flex-column ma-0 fill-height"
                            justify="center"
                        >
                            <v-col class="px-0">
                            <v-btn icon>
                                <v-icon>mdi-heart</v-icon>
                            </v-btn>
                            </v-col>

                            <v-col class="px-0">
                            <v-btn icon>
                                <v-icon>mdi-bookmark</v-icon>
                            </v-btn>
                            </v-col>

                            <v-col class="px-0">
                            <v-btn icon>
                                <v-icon>mdi-share-variant</v-icon>
                            </v-btn>
                            </v-col>
                        </v-row>
                        </v-col>
                    </v-row>
                    </v-container>
                </v-card>
            </div>    
        </div>
    </div>
    <div v-else>
        <div v-for="shop in shops" :key="shop.storeno"> 
            <div v-if="shop.storeno==2">
                {
                <v-card class="d-inline-block mx-auto">
                <br>{{shop.storename}}
                {{shop.address}}<br>
                {{shop.tel_no}}<br>
                    <v-container>
                    <v-row justify="space-between">
                        <v-col cols="auto">
                        <v-img
                            height="200"
                            width="200"
                            src="https://cdn.vuetifyjs.com/images/cards/store.jpg"
                        ></v-img>
                        </v-col>

                        <v-col
                        cols="auto"
                        class="text-center pl-0"
                        >
                        <v-row
                            class="flex-column ma-0 fill-height"
                            justify="center"
                        >
                            <v-col class="px-0">
                            <v-btn icon>
                                <v-icon>mdi-heart</v-icon>
                            </v-btn>
                            </v-col>

                            <v-col class="px-0">
                            <v-btn icon>
                                <v-icon>mdi-bookmark</v-icon>
                            </v-btn>
                            </v-col>

                            <v-col class="px-0">
                            <v-btn icon>
                                <v-icon>mdi-share-variant</v-icon>
                            </v-btn>
                            </v-col>
                        </v-row>
                        </v-col>
                    </v-row>
                    </v-container>
                </v-card>
            </div>    
        </div>
    </div>
  </v-container>
</template>

<script>
export default {
    name : 'searchshop',
    data(){
        return {
            shopname : this.$route.params.name,
            shops:[
            {
            storeno : '1',
            storename : '최고막국수',
            branch_name : '막국수',
            area : '부산',
            tel_no : '010-3131-3131',
            address : '부산광역시 진구',
            },
            {
            storeno : '2',
            storename : '누고족발',
            branch_name : '족발',
            area : '부산',
            tel_no : '010-3131-3131',
            address : '부산광역시 연제구',
            },
            ]
        }
    }
}
</script>

<style>

</style>