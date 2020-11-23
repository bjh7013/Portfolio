<template>
  <div>
    <div style="width:7vw; z-index: 1; margin:1vh 1vw; position: fixed; left: 0; bottom:0;">
      <v-btn id="a" class="box" @click="play(1)" v-if="music_num==7"><i class="fas fa-music fa-lg"></i></v-btn>
      <v-btn id="b" class="box" @click="play(2)" v-if="music_num==1"><i class="fas fa-music fa-lg"></i></v-btn>
      <v-btn id="cc" class="box" @click="play(3)" v-if="music_num==2"><i class="fas fa-music fa-lg"></i></v-btn>
      <v-btn id="d" class="box" @click="play(4)" v-if="music_num==3"><i class="fas fa-music fa-lg"></i></v-btn>
      <v-btn id="e" class="box" @click="play(5)" v-if="music_num==4"><i class="fas fa-music fa-lg"></i></v-btn>
      <v-btn id="f" class="box" @click="play(6)" v-if="music_num==5"><i class="fas fa-music fa-lg"></i></v-btn>
      <v-btn id="g" class="box" @click="play(7)" v-if="music_num==6"><i class="fas fa-music fa-lg"></i></v-btn>
    </div>
    <div style="width:7vw; z-index: 1; margin:1vh 1vw; position: fixed; left: 65px; bottom:0;">
      <v-btn id="cancel_music" class="box" @click="stop"><i class="fas fa-stop fa-lg"></i></v-btn>
    </div>
    <audio id="1Audio" loop>
      <source src="../assets/musics/BGM_1.mp3" type="audio/mp3">
    </audio>
    <audio id="2Audio" loop>
      <source src="../assets/musics/BGM_2.mp3" type="audio/mp3">
    </audio>
    <audio id="3Audio" loop>
      <source src="../assets/musics/BGM_3.mp3" type="audio/mp3">
    </audio>
    <audio id="4Audio" loop>
      <source src="../assets/musics/BGM_4.mp3" type="audio/mp3">
    </audio>
    <audio id="5Audio" loop>
      <source src="../assets/musics/BGM_5.mp3" type="audio/mp3">
    </audio>
    <audio id="6Audio" loop>
      <source src="../assets/musics/BGM_6.mp3" type="audio/mp3">
    </audio>
    <audio id="7Audio" loop>
      <source src="../assets/musics/BGM_7.mp3" type="audio/mp3">
    </audio>

  </div>
</template>

<script>
export default {
    name:'BGM',
    data(){
        return{
            audio : null,
            status : false,
            music_num : 2,
        }
    },
    beforeUpdate(){
        
    },
    methods: {
        play(k) {
            document.getElementById(this.music_num+'Audio').pause();
            document.getElementById(k+'Audio').play();
            document.getElementById(k+'Audio').currentTime = 0;
            document.getElementById(k+'Audio').volume = 0.1;
            this.music_num = k
        },
        stop(){
            document.getElementById(this.music_num+'Audio').pause();
            if(this.music_num==1){
                this.music_num=7
            }else{
                this.music_num=this.music_num-1
            }
        }
    },
};
</script>

<style scoped>
body{
  background-color: #22313F;
}

#keys{
    height: 116px;
    position: absolute;
    top: 0; left: 0; bottom: 0; right: 0;
}


#a {
    background-color: #E040FB;
    box-shadow: 0px 6px 0px #AA00FF;
    height: 50px;
    width: 50px;
} 

#b {
    background-color: #7C4DFF;
    box-shadow: 0px 6px 0px #6200EA;
    height: 50px;
    width: 50px;
}

#cc {
    background-color: #448AFF;
    box-shadow: 0px 6px 0px #2962FF;
    height: 50px;
    width: 50px;
}

#d {
    background-color: #9CCC65;
    box-shadow: 0px 6px 0px #2E7D32;
    height: 50px;
    width: 50px;
}

#e {
    background-color: #FFF176;
    box-shadow: 0px 6px 0px #FFD600;
    height: 50px;
    width: 50px;
}

#f {
    background-color: #FFB74D;
    box-shadow: 0px 6px 0px #FF9100;
    height: 50px;
    width: 50px;
}

#g {
    background-color: #FF7043;
    box-shadow: 0px 6px 0px #F4511E;
    height: 50px;
    width: 50px;
}

#cancel_music {
    background-color:white;
    box-shadow: 0px 6px 0px #EEEEEE;
    height: 50px;
    width: 50px;
}
.box.active {
  box-shadow: none;
  margin-top: 5px;
}

#a.active {
  background-color: #AA00FF;
}

#b.active {
  background-color: #6200EA;
}

#cc.active {
  background-color: #2962FF;
}

#d.active {
  background-color: #2E7D32;
}

#e.active {
  background-color: #FFD600;
}

#f.active {
  background-color: #FF9100;
}

#g.active {
  background-color: #F4511E;
}
</style>