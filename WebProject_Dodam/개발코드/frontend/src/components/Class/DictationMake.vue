<template>
  <div>
    <template>
    <v-row justify="center">
        <v-dialog
        v-model="dialog"
        persistent
        max-width="600px"
        >
        <template v-slot:activator="{ on, attrs }">
            <v-btn
            v-bind="attrs"
            v-on="on"
            class="btn filters__btn filters__btn--all"
            >
            받아쓰기생성
            </v-btn>
        </template>
        <v-card>
            <v-card-title>
            <span class="headline">받아쓰기</span>
            </v-card-title>
            <v-card-text>
            <v-container>
                <v-row>
                <!-- 시작 -->
                <div id="app">
                    <p><button class="btn form__submit-btn" type="submit" disabled>받아쓰기 제목</button>
                    <input class="input form__input form" v-model="name" label="문제 이름"/>
                <v-col cols="12" v-for="(problem,i) of problems" :key="i">
                    <button class="btn form__submit-btn" type="submit" disabled>문제{{i+1}}번</button>
                    <input class="input form__input form" v-model="problem.dictationProblem"/>
                    <voice-recorder :idx='i' @audio-change="audioChange" class="recorder"></voice-recorder>
                </v-col>
                  <transition-group tag="ol" name="list" class="todo-list">
                    <li
                      class="todo-list__item"
                      v-bind:class="{ complete: todo.complete }"
                      v-bind:key="index"
                      v-for="(todo, index) in filteredTodos">
                      <button
                        class="todo-list__item-content"
                        v-on:click="toggleTodo(todo)">
                        {{ todo.text }}
                      </button>
                      <button
                        class="btn todo-list__item-remove"
                        v-on:click="deleteTodo(index)">
                        <i class="fa" v-bind:class="[todo.complete ? 'fa-check' : 'fa-times']"></i>
                      </button>
                    </li>
                  </transition-group>
                  <div class="filters">
                    <button 
                      class="btn filters__btn filters__btn--all" 
                      @click="add">
                      추가하기
                    </button>
                    <button 
                      class="btn filters__btn filters__btn--complete" 
                      @click="sub">
                      제거하기
                    </button>
                    <button 
                      class="btn filters__btn filters__btn--incomplete" 
                      @click="addDictionSet">
                      제출하기
                    </button>
                    <button 
                      class="btn filters__btn filters__btn--incomplete" 
                      @click="cancel_dictation">
                      취소
                    </button>
                  </div>
                </div>
                <!-- 종료 -->
                </v-row>
            </v-container>
            </v-card-text>
            <v-card-actions>
            <v-spacer></v-spacer>
            </v-card-actions>
        </v-card>
        </v-dialog>
    </v-row>
    </template>
    <v-dialog v-model="OK" fullscreen>
      <v-container>
        <div id="containerok" style="margin:20% 30%">
          <div id="success-box">
            <div class="face">
              <div class="eye"></div>
              <div class="eye right"></div>
              <div class="mouth happy"></div>
            </div>
            <div class="shadow scale"></div>
            <div class="message">
              <h1 class="alert">성공했어요!</h1>
              <p>받아쓰기세트 만들기 성공!</p>
            </div>
            <button class="button-box green">
              <h1 @click="clear">확인</h1>
            </button>
          </div>
        </div>
      </v-container>
    </v-dialog>
  </div>
</template>

<script>
import axios from 'axios'
import VoiceRecorder from '@/components/VoiceRecord.vue'
var filters = {
  all: function (todos) {
    return todos;
  },
  complete: function (todos) {
    return todos.filter(function (todo) {
      return todo.complete;
    });
  },
  incomplete: function (todos) {
    return todos.filter(function (todo) {
      return !todo.complete;
    });
  }
};

var STORAGE_KEY = "vue-js-todo-P7oZi9sL";
var todoStorage = {
  fetch: function () {
    var todos = JSON.parse(localStorage.getItem(STORAGE_KEY) || "[]");
    return todos;
  },
  save: function (todos) {
    localStorage.setItem(STORAGE_KEY, JSON.stringify(todos));
  }
};
export default {
    components:{VoiceRecorder: VoiceRecorder},
    data(){
        return{
            dialog : false,
            loading: false,
            search: null,
            problems: [{dictationProblem:'', audioData: null}],
            name: "",
            count : 1,
            inputVal: "",
            todos: todoStorage.fetch(),
            visibility: "all",
            OK: false
          
        }
    },
    watch: {
      search (val) {
        val && val !== this.problems && this.querySelections(val)
      },
      todos: {
        handler: function (todos) {
          todoStorage.save(todos);
        }
      }
    },
    methods: {
      clear(){
        this.$router.go()
      },
      cancel_dictation(){
        this.dialog = false
        this.problems = []
      },
      audioChange(i,base64Data){
        this.problems[i].audioData = base64Data
      },
      querySelections (v) {
        this.loading = true
        // Simulated ajax query
        setTimeout(() => {
          this.items = this.states.filter(e => {
            return (e || '').toLowerCase().indexOf((v || '').toLowerCase()) > -1
          })
          this.loading = false
        }, 500)
      },
      addDictionSet(){
        if(this.problems.length!=this.count){

            alert('모두 입력해주세요1')
            return;
        }
        for(let i=0;i<this.problems.length;i++){
            
            if(this.problems[i]==null){
                 alert('모두 입력해주세요2')
                 return;
            }
        }
        this.dialog = false
        let data = {
          dictationsetName : this.name,
          dictationSetContentList : this.problems
        }
        
        axios.post('dictation/createproblem',data)
        .then((Response)=>{
            Response
            this.OK=true
        })
      },
      add(){
        this.count = this.count+1;
        this.problems.push({dictationProblem:'', audioData: null})
      },
      sub(){
        if(this.count==1){
          return;
        }
        this.problems.pop()
        this.count = this.count-1;
      },
      addTodo: function (e) {
        e.preventDefault();
        if (this.inputVal) {
          this.todos.push({
            text: this.inputVal,
            complete: false
          });
        }
        this.inputVal = "";
      },
      toggleTodo: function (todo) {
        todo.complete = !todo.complete;
      },
      filterTodos: function (filter) {
        this.visibility = filter;
      },
      deleteTodo: function (index) {
        this.todos.splice(index, 1);
      }
    },
    computed: {
      filteredTodos: function () {
        return filters[this.visibility](this.todos);
      }
    },
}
</script>

<style scoped>
*, *:before, *:after {
  box-sizing: border-box;
}

html {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  font-feature-settings: "liga", "kern";
  overflow-y: scroll;
  overflow-x: hidden;
  height: 100%;
  background: linear-gradient(210deg, #9adbbe, #4fc08d);
}

body {
  overflow: hidden;
  height: 100%;
  display: -webkit-box;
  display: flex;
  -webkit-box-align: center;
          align-items: center;
  -webkit-box-pack: center;
          justify-content: center;
}

button {
  background: none;
  border: none;
  color: inherit;
  font-size: inherit;
  font-family: inherit;
  font-weight: inherit;
}
button:focus {
  outline: none;
}
button:hover {
  cursor: pointer;
}

.app {
  min-height: 50vh;
  display: -webkit-box;
  display: flex;
  -webkit-box-orient: vertical;
  -webkit-box-direction: normal;
          flex-direction: column;
  -webkit-box-align: center;
          align-items: center;
  -webkit-box-pack: justify;
          justify-content: space-between;
  border-radius: 1em;
  background: #fff;
  overflow: hidden;
  box-shadow: 0 0 5px rgba(25, 25, 25, 0.25);
}

#app {
  font-family: 'TmoneyRoundWindExtraBold'; 
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: left;
  color: #2c3e50;
}

.btn {
  font-size: 14px;
  margin: 0 .5em;
  border-radius: 2em;
  padding: 0.75em 1.5em;
  cursor: pointer;
  background: none;
  color: #2d7c58;
  border: 1px solid;
  letter-spacing: 1px;
  color: #4fc08d;
  border: #4fc08d 1px solid;
  -webkit-transition: 250ms ease-out;
  transition: 250ms ease-out;
}
.btn:hover, .btn:focus {
  color: #fff;
  background: #4fc08d;
}

.form {
  width: 100%;
  padding: 1.5rem 1rem 0 1rem;
  display: -webkit-box;
  display: flex;
}
.form__input {
  width: 100%;
  font-size: 14px;
  margin: 0 .5em;
  border-radius: 2em;
  padding: 0.75em 1.5em;
  background: none;
  border: #e3e3e3 1px solid;
  -webkit-transition: border 250ms ease-out;
  transition: border 250ms ease-out;
}
.form__input:focus {
  border: #4fc08d 1px solid;
  outline: none;
}

.todo-list {
  width: 100%;
  padding: 0 1rem;
  -webkit-box-flex: 1;
          flex: 1;
}
.todo-list__item {
  display: -webkit-box;
  display: flex;
  -webkit-box-pack: justify;
          justify-content: space-between;
  -webkit-box-align: center;
          align-items: center;
  padding: .5em;
  margin-bottom: .5em;
  border-radius: 3px;
  -webkit-transition: 200ms;
  transition: 200ms;
  color: #4fc08d;
}
.todo-list__item:last-child {
  margin-bottom: 0;
}
.todo-list__item.complete {
  color: lightgreen;
}
.todo-list__item.complete .todo-list__item-content:after {
  background: lightgreen;
}
.todo-list__item-content {
  position: relative;
}
.todo-list__item-content:after {
  content: "";
  position: absolute;
  top: 100%;
  left: 0;
  right: 0;
  height: 1px;
  background: #4fc08d;
  -webkit-transition: 250ms ease-out;
  transition: 250ms ease-out;
  -webkit-transform-origin: center;
          transform-origin: center;
  -webkit-transform: scalex(0);
          transform: scalex(0);
}
.todo-list__item-content:hover:after, .todo-list__item-content:focus:after {
  -webkit-transform: scalex(1);
          transform: scalex(1);
}
.todo-list__item-remove {
  margin-left: .5em;
  background: none;
  border: 1px solid;
  color: inherit;
  padding: 0;
  line-height: 1;
  width: 2em;
  height: 2em;
  display: -webkit-box;
  display: flex;
  -webkit-box-align: center;
          align-items: center;
  -webkit-box-pack: center;
          justify-content: center;
  border-radius: 50%;
  font-size: 80%;
}

.filters {
  width: 100%;
  display: -webkit-box;
  display: flex;
  justify-content: space-around;
  padding: 0 1rem 1.5rem 1rem;
}

.list-move,
.list-leave-active,
.list-enter-active {
  -webkit-transition: 500ms cubic-bezier(0.87, -0.41, 0.19, 1.44);
  transition: 500ms cubic-bezier(0.87, -0.41, 0.19, 1.44);
}

.list-enter,
.list-leave-active {
  -webkit-transform: translate(100%, 0);
          transform: translate(100%, 0);
  opacity: 0;
}

@import url("https://fonts.googleapis.com/css?family=Lato:400,700");
html {
  display: grid;
  min-height: 100%;
}

body {
  display: grid;
  overflow: hidden;
  font-family: "Lato", sans-serif;
  text-transform: uppercase;
  text-align: center;
}

#container {
  position: relative;
  margin: auto;
  overflow: hidden;
  width: 700px;
  height: 250px;
}
#containerok {
  position: relative;
  margin: auto;
  overflow: hidden;
  width: 700px;
  height: 250px;
}

h1 {
  font-size: 0.9em;
  font-weight: 100;
  letter-spacing: 3px;
  padding-top: 5px;
  color: #FCFCFC;
  padding-bottom: 5px;
  text-transform: uppercase;
}

.green {
  color: #4ec07d;
}

.red {
  color: #e96075;
}

.alert {
  font-weight: 700;
  letter-spacing: 5px;
}

p {
  margin-top: -5px;
  font-size: 0.5em;
  font-weight: 100;
  color: #5e5e5e;
  letter-spacing: 1px;
}

button, .dot {
  cursor: pointer;
}

#success-box {
  position: absolute;
  width: 35%;
  height: 100%;
  left: 12%;
  background: linear-gradient(to bottom right, #B0DB7D 40%, #99DBB4 100%);
  border-radius: 20px;
  box-shadow: 5px 5px 20px #cbcdd3;
  perspective: 40px;
}

#error-box {
  position: absolute;
  width: 35%;
  height: 100%;
  right: 12%;
  background: linear-gradient(to bottom left, #EF8D9C 40%, #FFC39E 100%);
  border-radius: 20px;
  box-shadow: 5px 5px 20px #cbcdd3;
}

.dot {
  width: 8px;
  height: 8px;
  background: #FCFCFC;
  border-radius: 50%;
  position: absolute;
  top: 4%;
  right: 6%;
}
.dot:hover {
  background: #c9c9c9;
}

.two {
  right: 12%;
  opacity: .5;
}

.face {
  position: absolute;
  width: 22%;
  height: 22%;
  background: #FCFCFC;
  border-radius: 50%;
  border: 1px solid #777777;
  top: 21%;
  left: 37.5%;
  z-index: 2;
  animation: bounce 1s ease-in infinite;
}

.face2 {
  position: absolute;
  width: 22%;
  height: 22%;
  background: #FCFCFC;
  border-radius: 50%;
  border: 1px solid #777777;
  top: 21%;
  left: 37.5%;
  z-index: 2;
  animation: roll 3s ease-in-out infinite;
}

.eye {
  position: absolute;
  width: 5px;
  height: 5px;
  background: #777777;
  border-radius: 50%;
  top: 40%;
  left: 20%;
}

.right {
  left: 68%;
}

.mouth {
  position: absolute;
  top: 43%;
  left: 41%;
  width: 7px;
  height: 7px;
  border-radius: 50%;
}

.happy {
  border: 2px solid;
  border-color: transparent #777777 #777777 transparent;
  transform: rotate(45deg);
}

.sad {
  top: 49%;
  border: 2px solid;
  border-color: #777777 transparent transparent #777777;
  transform: rotate(45deg);
}

.shadow {
  position: absolute;
  width: 21%;
  height: 3%;
  opacity: .5;
  background: #777777;
  left: 40%;
  top: 43%;
  border-radius: 50%;
  z-index: 1;
}

.scale {
  animation: scale 1s ease-in infinite;
}

.move {
  animation: move 3s ease-in-out infinite;
}

.message {
  position: absolute;
  width: 100%;
  text-align: center;
  height: 40%;
  top: 47%;
}

.button-box {
  position: absolute;
  background: #FCFCFC;
  width: 50%;
  height: 15%;
  border-radius: 20px;
  top: 73%;
  left: 25%;
  outline: 0;
  border: none;
  box-shadow: 2px 2px 10px rgba(119, 119, 119, 0.5);
  transition: all .5s ease-in-out;
}
.button-box:hover {
  background: #efefef;
  transform: scale(1.05);
  transition: all .3s ease-in-out;
}

@keyframes bounce {
  50% {
    transform: translateY(-10px);
  }
}
@keyframes scale {
  50% {
    transform: scale(0.9);
  }
}
@keyframes roll {
  0% {
    transform: rotate(0deg);
    left: 25%;
  }
  50% {
    left: 60%;
    transform: rotate(168deg);
  }
  100% {
    transform: rotate(0deg);
    left: 25%;
  }
}
@keyframes move {
  0% {
    left: 25%;
  }
  50% {
    left: 60%;
  }
  100% {
    left: 25%;
  }
}
footer {
  position: absolute;
  bottom: 0;
  right: 0;
  text-align: center;
  font-size: 1em;
  text-transform: uppercase;
  padding: 10px;
  font-family: "Lato", sans-serif;
}
footer p {
  color: #EF8D9C;
  letter-spacing: 2px;
}
footer a {
  color: #B0DB7D;
  text-decoration: none;
}
footer a:hover {
  color: #FFC39E;
}

</style>