(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-665725ff"],{1182:function(t,e,a){},2425:function(t,e,a){"use strict";var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("v-footer",{attrs:{id:"app-footer",padless:""}},[a("div",{staticClass:"row"},[a("div",{staticClass:"col-sm-12 col-md-6"},[a("h6",[t._v("logo")]),a("p",{staticClass:"text-justify"},[t._v("소개")])]),a("div",{staticClass:"col-xs-6 col-md-3"},[a("h6",[t._v("Categories")]),a("ul",{staticClass:"footer-links"},[a("li",[a("a",{attrs:{href:"#"}},[t._v("도담도담 이용안내")])]),a("li",[a("a",{attrs:{href:"#"}},[t._v("고객센터")])]),a("li",[a("a",{attrs:{href:"#"}},[t._v("이용약관")])])])]),a("div",{staticClass:"col-xs-6 col-md-3"},[a("h6",[t._v("Quick Links")]),a("ul",{staticClass:"footer-links"},[a("li",[a("a",{attrs:{href:"#"}},[t._v("도담도담 공지사항")])])])])]),a("div",{staticClass:"row"},[a("div",{staticClass:"col-md-8 col-sm-6 col-xs-12"},[a("p",{staticClass:"copyright-text"},[t._v("Copyright © 2020 All Rights Reserved by "),a("a",{attrs:{href:"#"}},[t._v("Bungle")])])]),a("div",{staticClass:"col-md-4 col-sm-6 col-xs-12"})])])},i=[],s={name:"footbar"},o=s,d=(a("b478"),a("2877")),r=a("6544"),c=a.n(r),l=(a("a9e3"),a("c7cd"),a("5530")),u=(a("b5b6"),a("8dd9")),b=a("fe6c"),p=a("58df");function h(t){var e=arguments.length>1&&void 0!==arguments[1]?arguments[1]:[];return Object(p["a"])(Object(b["b"])(["absolute","fixed"])).extend({name:"applicationable",props:{app:Boolean},computed:{applicationProperty:function(){return t}},watch:{app:function(t,e){e?this.removeApplication(!0):this.callUpdate()},applicationProperty:function(t,e){this.$vuetify.application.unregister(this._uid,e)}},activated:function(){this.callUpdate()},created:function(){for(var t=0,a=e.length;t<a;t++)this.$watch(e[t],this.callUpdate);this.callUpdate()},mounted:function(){this.callUpdate()},deactivated:function(){this.removeApplication()},destroyed:function(){this.removeApplication()},methods:{callUpdate:function(){this.app&&this.$vuetify.application.register(this._uid,this.applicationProperty,this.updateApplication())},removeApplication:function(){var t=arguments.length>0&&void 0!==arguments[0]&&arguments[0];(t||this.app)&&this.$vuetify.application.unregister(this._uid,this.applicationProperty)},updateApplication:function(){return 0}}})}var f=a("2b0e"),v=f["default"].extend({name:"ssr-bootable",data:function(){return{isBooted:!1}},mounted:function(){var t=this;window.requestAnimationFrame((function(){t.$el.setAttribute("data-booted","true"),t.isBooted=!0}))}}),m=a("80d2"),_=Object(p["a"])(u["a"],h("footer",["height","inset"]),v).extend({name:"v-footer",props:{height:{default:"auto",type:[Number,String]},inset:Boolean,padless:Boolean,tag:{type:String,default:"footer"}},computed:{applicationProperty:function(){return this.inset?"insetFooter":"footer"},classes:function(){return Object(l["a"])(Object(l["a"])({},u["a"].options.computed.classes.call(this)),{},{"v-footer--absolute":this.absolute,"v-footer--fixed":!this.absolute&&(this.app||this.fixed),"v-footer--padless":this.padless,"v-footer--inset":this.inset})},computedBottom:function(){if(this.isPositioned)return this.app?this.$vuetify.application.bottom:0},computedLeft:function(){if(this.isPositioned)return this.app&&this.inset?this.$vuetify.application.left:0},computedRight:function(){if(this.isPositioned)return this.app&&this.inset?this.$vuetify.application.right:0},isPositioned:function(){return Boolean(this.absolute||this.fixed||this.app)},styles:function(){var t=parseInt(this.height);return Object(l["a"])(Object(l["a"])({},u["a"].options.computed.styles.call(this)),{},{height:isNaN(t)?t:Object(m["d"])(t),left:Object(m["d"])(this.computedLeft),right:Object(m["d"])(this.computedRight),bottom:Object(m["d"])(this.computedBottom)})}},methods:{updateApplication:function(){var t=parseInt(this.height);return isNaN(t)?this.$el?this.$el.clientHeight:0:t}},render:function(t){var e=this.setBackgroundColor(this.color,{staticClass:"v-footer",class:this.classes,style:this.styles});return t(this.tag,e,this.$slots.default)}}),g=Object(d["a"])(o,n,i,!1,null,"52f96b69",null);e["a"]=g.exports;c()(g,{VFooter:_})},"300a":function(t,e,a){},"34ba":function(t,e,a){"use strict";var n=a("1182"),i=a.n(n);i.a},7027:function(t,e,a){"use strict";a.r(e);var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("v-container",[a("nav-bar"),a("time-table"),a("foot-bar")],1)},i=[],s=a("d000"),o=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("v-container",[a("div",{staticClass:"timetable"},[a("div",{staticClass:"wrapper"},[a("table",[a("caption",[t._v("Timetable")]),a("tr",[a("th",[t._v("Time")]),a("th",[t._v("Monday")]),a("th",[t._v("Tuesday")]),a("th",[t._v("Wednesday")]),a("th",[t._v("Thursday")]),a("th",[t._v("Friday")]),a("th",[t._v("Saturday")]),a("th",[t._v("Sunday")])]),a("tr",[a("td",[t._v("9:00 ")]),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}})]),a("tr",[a("td",[t._v("10:00 ")]),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}})]),a("tr",[a("td",[t._v("11:00 ")]),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}})]),a("tr",[a("td",[t._v("12:00 ")]),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}})]),a("tr",[a("td",[t._v("13:00 ")]),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}})]),a("tr",[a("td",[t._v("14:00 ")]),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}})]),a("tr",[a("td",[t._v("15:00 ")]),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}})]),a("tr",[a("td",[t._v("16:00 ")]),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}})]),a("tr",[a("td",[t._v("17:00 ")]),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}}),a("td",{attrs:{contenteditable:""}})])])])])])},d=[],r={name:"timetable"},c=r,l=(a("db06"),a("2877")),u=a("6544"),b=a.n(u),p=a("a523"),h=Object(l["a"])(c,o,d,!1,null,"2601fec7",null),f=h.exports;b()(h,{VContainer:p["a"]});var v=a("2425"),m={name:"studentmain",components:{NavBar:s["a"],TimeTable:f,FootBar:v["a"]}},_=m,g=Object(l["a"])(_,n,i,!1,null,"4cc02b3a",null);e["default"]=g.exports;b()(g,{VContainer:p["a"]})},b189:function(t,e,a){t.exports=a.p+"img/logo1.78bd979b.png"},b478:function(t,e,a){"use strict";var n=a("300a"),i=a.n(n);i.a},b5b6:function(t,e,a){},d000:function(t,e,a){"use strict";var n=function(){var t=this,e=t.$createElement;t._self._c;return t._m(0)},i=[function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("section",{staticClass:"navigation"},[a("div",{staticClass:"nav-container"},[a("div",{staticClass:"brand"},[a("a",{attrs:{href:"/"}},[t._v("도담도담")])]),a("nav",[a("div",{staticClass:"nav-mobile"},[a("a",{attrs:{id:"nav-toggle",href:"#!"}},[a("span")])]),a("ul",{staticClass:"nav-list"},[a("li",[a("a",{attrs:{href:"/"}},[t._v("학습컨텐츠")])]),a("li",[a("a",{attrs:{href:"/about"}},[t._v("클래스")])]),a("li",[a("a",{attrs:{href:"/login"}},[t._v("로그인")])])])])])])])}],s={name:"navbar",data:function(){return{logo:a("b189")}}},o=s,d=(a("34ba"),a("2877")),r=Object(d["a"])(o,n,i,!1,null,null,null);e["a"]=r.exports},d62d:function(t,e,a){},db06:function(t,e,a){"use strict";var n=a("d62d"),i=a.n(n);i.a}}]);
//# sourceMappingURL=chunk-665725ff.70493901.js.map