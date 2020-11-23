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
          <v-btn  v-if="check_owner"
            color="primary"
            dark
            v-bind="attrs"
            v-on="on"
          >
            시간표 추가
          </v-btn>
          <v-btn v-else>
          시간표
          </v-btn>
        </template>
        <v-card>
          <v-card-title>
            <span class="headline">시간표 정보</span>
          </v-card-title>
          <v-card-text>
            <v-container>
              <v-row>
                <v-col
                  cols="12"
                  sm="6"
                  md="4"
                >
                  <v-text-field
                    label="클래스 요일(0~6)"
                    hint="일요일(0)~토요일(6)"
                    v-model="day"
                    required
                  ></v-text-field>
                </v-col>
                <v-col
                  cols="12"
                  sm="6"
                  md="4"
                >
                  <v-text-field
                    label="시작 시작(HHMM)"
                    v-model="start"
                    hint="ex)0900"
                  ></v-text-field>
                </v-col>
                <v-col
                  cols="12"
                  sm="6"
                  md="4"
                >
                  <v-text-field
                    label="종료 시간(HHMM)"
                    hint="ex)1800"
                    v-model="end"
                    persistent-hint
                    required
                  ></v-text-field>
                </v-col>
                <v-col cols="12">
                  <v-text-field
                    label="시간표이름"
                    v-model="classtimename"
                    required
                  ></v-text-field>
                </v-col>
              </v-row>
            </v-container>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              color="blue darken-1"
              text
              @click="dialog = false"
            >
              Close
            </v-btn>
            <v-btn
              color="blue darken-1"
              text
              @click="addClassTime"
            >
              Save
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
  </template>
    <v-row>
        <v-col>
        <v-sheet height="100%">
            <v-calendar
            ref="calendar"
            :now="today"
            :value="today"
            :events="events"
            first-time="08:00"
            color="primary"
            type="week"
            @click:event="(event)=>delClassTime(event)"
            ></v-calendar>
        </v-sheet>
        </v-col>
    </v-row>
    <template>
      <v-row justify="center">
        <v-dialog
          v-model="deldialog"
          persistent
          max-width="290"
        >
          <v-card>
            <v-card-title class="headline">
              시간표를 삭제하시겠습니까?
            </v-card-title>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn
                color="green darken-1"
                text
                @click="deldialog = false"
              >
                취소
              </v-btn>
              <v-btn
                color="green darken-1"
                text
                @click="delClassTimereal()"
              >
                확인
              </v-btn>
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
          today : '',
          dialog: false,
          events: [],
          day : null,
          start : null,
          end : null,
          classtimename : null,
          deldialog: false,
          delno : null,
          check_owner:null
        }
    },
    created(){
      this.get_SessionTime()
    },
    methods : {
      get_SessionTime() {
        let today = new Date();
        let year = today.getFullYear(); // 년도
        let month = today.getMonth() + 1;  // 월
        let date = today.getDate();  // 날짜
        let day = today.getDay();  // 요일
        this.today = year + '-' + month +'-' + date
        axios.get('classtime/searchclasstime/'+this.$route.params.classno)
        .then((response)=>{
          this.check_owner = response.data[0].checkOwner
          for(let i=0;i<response.data.length;i++){
            let val = response.data[i].classDay - parseInt(day)
            let tempday = this.$moment(new Date()).add(val,'days').format('YYYY-MM-DD')
            let data = {
              timeno : response.data[i].timeNo,
              name : response.data[i].timeName,
              start : tempday + ' ' +response.data[i].startTime.substring(0,2)+':00',
              end : tempday + ' ' +response.data[i].endTime.substring(0,2)+':00'
            }
            this.events.push(data)
          }
        })
      },
      addClassTime(){
        this.dialog = false
        let data = {
          classDay : this.day,
          startTime : this.start,
          endTime : this.end,
          classNo : this.$route.params.classno,
          timeName : this.classtimename
        }
        axios.post('classtime/createclasstime',data)
        .then((Response)=>{
          Response
          alert('시간표 추가 완료')
          this.$router.go()
        })
        .catch((error)=>{
          error
        })
      },
      delClassTime(event){
        this.delno = event.event.timeno
        this.deldialog=true;
      },
      delClassTimereal(){
        axios.delete('classtime/deleteoneclasstime/'+this.delno)
        .then((Response)=>{
          Response
          alert('삭제되었습니다.')
          this.$router.go()
        })
        .catch((error)=>{
          error
        })
      }
    }

}
</script>

<style scoped>
.my-event {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  border-radius: 2px;
  background-color: #1867c0;
  color: #ffffff;
  border: 1px solid #1867c0;
  font-size: 12px;
  padding: 3px;
  cursor: pointer;
  margin-bottom: 1px;
  left: 4px;
  margin-right: 8px;
  position: relative;
}

.my-event.with-time {
  position: absolute;
  right: 4px;
  margin-right: 0px;
}
</style>