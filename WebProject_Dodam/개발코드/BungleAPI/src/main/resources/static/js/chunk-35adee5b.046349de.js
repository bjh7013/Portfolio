(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-35adee5b"],{"0ab9":function(t,a,e){"use strict";var r=e("f229"),s=e.n(r);s.a},"0e8f":function(t,a,e){"use strict";e("20f6");var r=e("e8f2");a["a"]=Object(r["a"])("flex")},"2a0d":function(t,a,e){"use strict";e.r(a);var r=function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",[e("h1",{staticClass:"products"},[t._v("단어연습")]),e("v-container",{staticClass:"my-5"},[e("v-layout",{attrs:{row:"",wrap:""}},t._l(t.categorylist,(function(t,a){return e("v-flex",{key:a,attrs:{xs12:"",sm6:"",md3:""}},[e("div",{staticClass:"cards"},[e("WordItems",{attrs:{wordItem:t}})],1)])})),1)],1)],1)},s=[],n=function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("router-link",{attrs:{to:{name:"WordTest",params:{categoryno:t.categoryItem.categoryNo}}}},[e("v-card",{staticClass:"ma-1 text-xs-center",attrs:{flat:""}},[e("div",{staticClass:"ctitle"},[e("v-img",{staticClass:"cimg",attrs:{src:t.wordItem.categoryImg,"aspect-ratio":"1"}}),e("div",{staticClass:"cti"},[e("v-card-title",{attrs:{"primary-title":""}},[e("h3",{staticClass:"cfont headline pink--text text--accent-2 text-truncate"},[t._v(" "+t._s(t.wordItem.categoryName))])])],1)],1)])],1)},c=[],o={props:["wordItem"],data:function(){return{categoryItem:this.wordItem}},created:function(){}},i=o,l=(e("0ab9"),e("2877")),u=e("6544"),d=e.n(u),f=e("b0af"),m=e("99d9"),g=e("adda"),v=Object(l["a"])(i,n,c,!1,null,null,null),p=v.exports;d()(v,{VCard:f["a"],VCardTitle:m["a"],VImg:g["a"]});var y=e("bc3a"),h=e.n(y),w={data:function(){return{userno:"1",categorylist:[]}},methods:{userFail:function(t){"0"==t[0]&&(t=t.substring(1,5),this.userlimit=t)},getCategory:function(){var t=this;h.a.get("http://192.168.100.70:9999/wordtest/category").then((function(a){t.categorylist=a.data,console.log(a.data)})).catch((function(t){console.log(t)}))}},created:function(){this.getCategory()},components:{WordItems:p}},b=w,C=(e("8a1b"),e("a523")),x=e("0e8f"),I=e("a722"),_=Object(l["a"])(b,r,s,!1,null,"4ada6d54",null);a["default"]=_.exports;d()(_,{VContainer:C["a"],VFlex:x["a"],VLayout:I["a"]})},"68ed":function(t,a,e){},"8a1b":function(t,a,e){"use strict";var r=e("68ed"),s=e.n(r);s.a},f229:function(t,a,e){}}]);
//# sourceMappingURL=chunk-35adee5b.046349de.js.map