(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-b20b8656"],{"29f6":function(t,s,i){},"3de3":function(t,s,i){"use strict";i.r(s);var e=function(){var t=this,s=t.$createElement,i=t._self._c||s;return i("div",[i("div",{attrs:{id:"mant"}},[i("div",[i("h1",[t._v("나의 점수는? "+t._s(t.correctcount/t.questions.length*100)+"점")])])]),t.correctcount!=t.questions.length?i("div",[t._m(0),i("div",t._l(t.ansdata,(function(s,e){return i("div",{key:e},[s.correct?t._e():i("div",{staticClass:"display-flex_width-100 item-one"},[i("div",{staticClass:"submit-item-left"},[t._v(" "+t._s(e+1)+"번 ")]),i("div",{staticClass:"display-flex_width-90"},[i("div",{staticClass:"submit-item-mid"},[i("div",{staticClass:"display-flex_width-100",staticStyle:{"background-color":"#F7AA97"}},[i("div",{staticClass:"display-flex_width-10"},[t._v("정답")]),i("div",{staticClass:"display-flex_width-90 padding-4"},[t._v(" "+t._s(t.questions[e].dictationSentence)+" ")])]),i("div",{staticClass:"display-flex_width-100",staticStyle:{"background-color":"peachpuff"}},[i("div",{staticClass:"display-flex_width-15"},[t._v("내가 쓴 답")]),""==s.submitOCR?i("div",{staticClass:"display-flex_width-85 padding-4"},[t._v(" "+t._s(s.submitAnswer)+" ")]):t._e(),""!=s.submitOCR?i("div",{staticClass:"display-flex_width-85 padding-4",staticStyle:{"justify-content":"space-between"}},[i("div",[t._v(" "+t._s(s.submitOCR)+" ")])]):t._e()]),""!=s.submitOCR?i("div",{staticStyle:{"background-color":"peachpuff"}},[i("div",{staticClass:"ocrimg",attrs:{id:"ocr"+e}},[i("img",{staticClass:"ocrimgs",attrs:{src:s.submitImgData}})])]):t._e()]),""!=s.submitOCR?i("div",{staticClass:"submit-item-right"},[i("div",{staticClass:"btn-showresultimg",on:{click:function(s){return t.ckresults(e)}}},[t._v(" 내가 쓴 글씨 보기 "),i("i",{staticClass:"fas fa-angle-down"})])]):t._e()])])])})),0)]):t._e()])},a=[function(){var t=this,s=t.$createElement,i=t._self._c||s;return i("div",{staticClass:"sub-mant"},[i("div",[t._v(" 오답 노트를 볼까요? ")])])}],n={name:"dictationresult",props:["ansdata","questions","correctcount"],data:function(){return{}},created:function(){},mounted:function(){this.getdisplay()},methods:{getdisplay:function(){for(var t=0;t<this.questions.length;t++)this.ansdata[t].correct||""==this.ansdata[t].submitOCR||(document.getElementById("ocr"+t).style.display="none")},ckresults:function(t){"none"!=document.getElementById("ocr"+t).style.display?document.getElementById("ocr"+t).style.display="none":document.getElementById("ocr"+t).style.display="block"}}},c=n,d=(i("c2a4"),i("2877")),l=Object(d["a"])(c,e,a,!1,null,null,null);s["default"]=l.exports},c2a4:function(t,s,i){"use strict";var e=i("29f6"),a=i.n(e);a.a}}]);
//# sourceMappingURL=chunk-b20b8656.52c2fdcb.js.map