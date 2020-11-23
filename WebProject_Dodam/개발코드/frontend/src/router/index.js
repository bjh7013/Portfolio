import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/sharedraw',
    component: () => import('@/components/ShareCanvas.vue')
  },
  {
    path: '/webrtc',
    component: () => import('@/components/WebRTC.vue')
  },
  {
    path: '/loading',
    component: () => import('../components/LoadingIcon.vue')
  },
  {
    path: '/record',
    component : () => import('../components/VoiceRecord.vue')
  },
  {
    path:'/pointmaker',
    component: () => import('../components/PointMaker.vue')
  },
  // views
  {
    path: '/',
    name: 'main',
    component: () => import('../views/Main.vue')
  },
  {
    path: '/dodam',
    name: 'dodam',
    component: () => import('../views/Dodam.vue')
  },
  {
    path: '/contentsmain',
    name: 'contentsmain',
    component: () => import('../views/ContentsMain.vue')
  },
  {
    path: '/classsearch',
    name: 'Classsearch',
    component: () => import('../views/ClassSearch.vue')
  },
  {
    path: '/join',
    name: 'join',
    component: () => import('../components/Join.vue')
  },
  {
    path: '/mypage',
    name: 'mypage',
    component: () => import('../components/MyPage.vue')
  },
  {
    path: '/draw',
    name : 'Draw',
    component: () => import('../components/Draw.vue')
  },
  {
    path : '/alphabetwriting',
    component: () => import('../components/AlphabetWriting.vue')
  },
  {
    path: '/studentmain',
    name: 'StudentMain',
    component: () => import('../views/StudentMain.vue')
  },
  {
    path: '/teachermain',
    name: 'TeacherMain',
    component: () => import('../views/TeacherMain.vue')
  },
  {
    path: '/class',
    name: 'class',
    component: () => import('../views/Class.vue'),
  },
  {
    path: '/class/modify/:classNo',
    name: 'classmodify',
    component : () => import('../components/Class/ClassModify.vue')
  },
  {
    path: '/wordtest/:categoryNo:categoryName',
    name: 'WordTest',
    props:true,
    component: () => import('../views/WordTest.vue')
  },
  {
    path: '/wordtesthomework/:homeworkNo',
    name: 'WordTestHomework',
    props:true,
    component: () => import('../views/WordTest.vue')
  },
  {
    path: '/drawingword',
    name: 'drawingword',
    props:true,
    component: () => import('../views/DrawingWord.vue')
  },
  {
    path: '/wordCategory',
    name: 'WordCategory',
    component: () => import('../views/WordCategory.vue')
  },
  
  //components
  {
    path: '/bungle',
    name: 'bungle',
    component: () => import('../components/Bungle.vue')
  },
  {
    path: '/logo',
    name: 'logo',
    component: () => import('../components/Logo.vue')
  },
  // 글자공부
  {
    path: '/alphabet',
    name: 'alphabet',
    component: () => import('../components/Alphabet/Alphabet.vue')
  },
  {
    path: '/koreanconsonant/',
    name: 'koreanconsonant',
    component: () => import('../components/Alphabet/KoreanConsonant.vue')
  },
  {
    path: '/koreandoubleconsonant/',
    name: 'koreandoubleconsonant',
    component: () => import('../components/Alphabet/KoreanDoubleConsonant.vue')
  },
  {
    path: '/koreanvowel/',
    name: 'koreanvowel',
    component: () => import('../components/Alphabet/KoreanVowel.vue')
  },
  {
    path: '/koreandoublevowel/',
    name: 'koreandoublevowel',
    component: () => import('../components/Alphabet/KoreanDoubleVowel.vue')
  },
  {
    path: '/koreanletter/',
    name: 'koreanletter',
    component: () => import('../components/Alphabet/KoreanLetter.vue')
  },
  {
    path: '/koreandetail/:koreanno',
    name: 'koreandetail',
    component: () => import('../components/Alphabet/KoreanDetail.vue')
  },
  {
    path: '/koreantable',
    name: 'koreantable',
    component: () => import('../components/Alphabet/KoreanTable.vue')
  },
  // 받아쓰기
  {
    path: '/dictation',
    name: 'dictation',
    component: () => import('../components/Dictation/Dictation.vue')
  },  
  {
    path: '/dictationresult',
    name: 'dictationresult',
    props:true,
    component: () => import('../components/Dictation/DictationResult.vue')
  },
  {
    path: '/dictationtest/:questionsno',
    name: 'dictationtest',
    props:true,
    component: () => import('../components/Dictation/DictationTest.vue')
  },
  {
    path: '/dictationhomework/:homeworkno',
    name: 'dictationhomework',
    props:true,
    component: () => import('../components/Dictation/DictationTest.vue')
  },

  // 클래스
  {
    path: '/classdetail/:classno',
    name: 'classdetail',
    component: () => import('../components/Class/ClassDetail.vue'),
    children : [
      {
        path: '/',
        name: 'ClassCurriculum',
        component: () => import('../components/Class/ClassCurriculum.vue'),
      },
      {
        path: '/curriculum/:classno',
        name: 'ClassCurriculum',
        component: () => import('../components/Class/ClassCurriculum.vue'),
      },
      {
        path: '/timetable/:classno',
        name: 'ClassTimeTable',
        component: () => import('../components/Class/ClassTimeTable.vue'),
      },
      {
        path: '/homework/:classno',
        name: 'ClassHomework',
        component: () => import('../components/Class/ClassHomework.vue'),
      },
      {
        path: '/classhwcreate/:classno',
        name: 'classhwcreate',
        component: () => import('../components/Class/ClassHwCreate.vue'),
        children : [
          {
            path: '/calldictation/:classno',
            name: 'calldictation',
            component: () => import('../components/Class/ClassCallDictation.vue'),
          },
          {
            path: '/callword/:classno',
            name: 'callword',
            component: () => import('../components/Class/ClassCallWord.vue'),
          }
        ]
      },
      {
        path: '/notice/:classno',
        name: 'ClassNotice',
        component: () => import('../components/Class/ClassNotice.vue'),
      },
      {
        path: '/member/:classno',
        name: 'ClassStudents',
        component: () => import('../components/Class/ClassStudents.vue'),
      },
      {
        path: '/joinwantlist/:classno',
        name: 'ClassJoinRequire',
        component: () => import('../components/Class/ClassJoinRequire.vue'),
      },
      {
        path: '/classmember/:userno',
        name: 'ClassUserDetail',
        component: () => import('../components/Class/ClassUserDetail.vue'),
      },
      {
        path: '/createproblem/:classno',
        name: 'CreateProblem',
        component: () => import('../components/Class/CreateProblem.vue'),
      },
    ]
  },

  {
    path: '/mychildren',
    name: 'mychildren',
    component: () => import('../components/MyChildren.vue'),
  },
  {
    path: '/childrendetail',
    name: 'childrendetail',
    // props:true,
    component: () => import('../components/ChildrenDetail.vue'),
  },{
    path: '/childdetail/:userno',
    name: 'childrendetail',
    // props:true,
    component: () => import('../components/ChildrenDetail.vue'),
  },
  {
    path: '/bgm',
    name: 'BGM',
    component: () => import('../components/BackGroundMusic.vue')
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
