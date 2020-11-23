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
            단어생성
            </v-btn>
        </template>
        <v-card>
            <v-card-title>
            <span class="headline">단어</span>
            </v-card-title>
            <v-card-text>
            <v-container>
                <v-row>
                    <button class="btn form__submit-btn" type="submit" disabled>단어 문제 이름</button>
                    <input class="input form__input" v-model="name" maxlength="8"/>
                    <div id="app">
                        <v-col cols="12" v-for="(i) in count" :key="i">
                            <button class="btn form__submit-btn" type="submit" disabled>문제{{i}}번</button>
                            <input class="input form__input" v-model="select[i]" maxlength="8"/>
                            <div class="noimg">
                                <label for="photo" id="file-drag-not_img" ref="dropbox">
                                <h4>{{i}}번 단어 이미지 등록</h4>
                                <input type="file" id="photo" accept="image/*" @change="onFileChange" @click="index=i"/>
                                </label>
                            </div>
                        </v-col>
                    </div>
                        <div class="filters">
                            <button 
                            class="btn filters__btn filters__btn--all" 
                            @click="add">
                            추가하기
                            </button>
                            <button 
                            class="btn filters__btn filters__btn--complete" 
                            @click="subtraction">
                            제거하기
                            </button>
                            <button 
                            class="btn filters__btn filters__btn--incomplete" 
                            @click="addWords">
                            제출하기
                            </button>
                            <button 
                            class="btn filters__btn filters__btn--incomplete" 
                            @click="cancel_word">
                            취소
                            </button>
                        </div>
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
        <div id="container" style="margin:20% 30%">
          <div id="success-box">
            <div class="face">
              <div class="eye"></div>
              <div class="eye right"></div>
              <div class="mouth happy"></div>
            </div>
            <div class="shadow scale"></div>
            <div class="message">
              <h1 class="alert">성공했어요!</h1>
              <p>낱말세트 만들기 성공!</p>
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
    data(){
        return {
            dialog : false,
            select: [null],
            count : 1,
            name : null,
            files: [null],
            fileName:[null],
            index : 1,
            inputVal: "",
            todos: todoStorage.fetch(),
            visibility: "all",
            OK : false
        }
    },
    methods : {
        clear(){
          this.$router.go()
        },
        cancel_word(){
            this.dialog = false
            this.select = [null]
            this.files = [null]
            this.fileName = [null]
            this.name = null
            this.count=1
        },
        add(){
            this.count=this.count+1;
            this.select.push(null)
            this.files.push(null)
            this.fileName.push(null)
        },
        subtraction(){
            if(this.count==1){
                return;
            }
            this.files.pop()
            this.select.pop()
            this.fileName.pop()
            this.count = this.count-1;
        },
        addWords(){
            if(this.select.length!=this.count+1){
                alert('모두 입력해주세요')
                return;
            }
            if(this.files.length!=this.count+1){
                alert('모두 입력해주세요')
                return;
            }
            for(let i=1;i<this.count+1;i++){
                if(this.select[i]==null){
                    alert('모두 입력해주세요')
                    return;
                }
            }
            for(let i=1;i<this.count+1;i++){
                if(this.files[i]==null){
                    alert('모두 입력해주세요2')
                    return;
                }
            }
            this.dialog = false
            if(this.name==null){
                alert('제목을 입력하세요')
                return;
            }

            axios.post('wordtest/userword',{
                wordsetTitle : this.name,
                wordImgData: this.files,
                problemName : this.select
            }) //이미지업데이트
            .then((data) =>{
                data
                this.OK = true
            }).catch((error)=>{
              error
            })
        },
        onFileChange(e) {
            //이미지 첨부
            let files = e.target.files || e.dataTransfer.files;
            if (!files.length) return;
            this.createImage(files[0],this.index);
        },
        createImage(file,index) {
            //이미지 가져오기
            this.fileName.splice(index,1,file.name);
            let reader = new FileReader();
            let vm = this;
            reader.onload = (e) => {
                vm.classimg = e.target.result;
                this.files.splice(this.index,1,e.target.result);
            };
            reader.readAsDataURL(file);

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
    watch: {
        todos: {
        handler: function (todos) {
            todoStorage.save(todos);
        }
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
  font-family: "Source Sans Pro", sans-serif;
}
#app {
  font-family: 'TmoneyRoundWindExtraBold';
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: left;
  color: #2c3e50;
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
  font-family: "Source Sans Pro", sans-serif;
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
  font-family: "Source Sans Pro", sans-serif;
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