(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-6f09f1ba"],{1182:function(t,e,i){},2425:function(t,e,i){"use strict";var s=function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",[i("v-footer",{staticClass:"font-weight-medium"},[i("v-col",{staticClass:"text-center",attrs:{cols:"12"}},[i("ul",{staticClass:"footer-links"},[i("li",{staticStyle:{color:"white"}},[t._v("도담도담 "),i("a",{attrs:{href:"/dodam"}},[t._v("이용안내")])])]),i("p",{staticClass:"copyright-text"},[t._v("Copyright © 2020 All Rights Reserved by "),i("a",{staticStyle:{color:"white"},attrs:{href:"/bungle"}},[t._v("Bungle")])])])],1)],1)},a=[],n={name:"footbar"},o=n,r=(i("fa8c"),i("2877")),c=i("6544"),u=i.n(c),l=i("62ad"),h=(i("a9e3"),i("c7cd"),i("5530")),f=(i("b5b6"),i("8dd9")),p=i("fe6c"),d=i("58df");function v(t){var e=arguments.length>1&&void 0!==arguments[1]?arguments[1]:[];return Object(d["a"])(Object(p["b"])(["absolute","fixed"])).extend({name:"applicationable",props:{app:Boolean},computed:{applicationProperty:function(){return t}},watch:{app:function(t,e){e?this.removeApplication(!0):this.callUpdate()},applicationProperty:function(t,e){this.$vuetify.application.unregister(this._uid,e)}},activated:function(){this.callUpdate()},created:function(){for(var t=0,i=e.length;t<i;t++)this.$watch(e[t],this.callUpdate);this.callUpdate()},mounted:function(){this.callUpdate()},deactivated:function(){this.removeApplication()},destroyed:function(){this.removeApplication()},methods:{callUpdate:function(){this.app&&this.$vuetify.application.register(this._uid,this.applicationProperty,this.updateApplication())},removeApplication:function(){var t=arguments.length>0&&void 0!==arguments[0]&&arguments[0];(t||this.app)&&this.$vuetify.application.unregister(this._uid,this.applicationProperty)},updateApplication:function(){return 0}}})}var m=i("d10f"),b=i("80d2"),g=Object(d["a"])(f["a"],v("footer",["height","inset"]),m["a"]).extend({name:"v-footer",props:{height:{default:"auto",type:[Number,String]},inset:Boolean,padless:Boolean,tag:{type:String,default:"footer"}},computed:{applicationProperty:function(){return this.inset?"insetFooter":"footer"},classes:function(){return Object(h["a"])(Object(h["a"])({},f["a"].options.computed.classes.call(this)),{},{"v-footer--absolute":this.absolute,"v-footer--fixed":!this.absolute&&(this.app||this.fixed),"v-footer--padless":this.padless,"v-footer--inset":this.inset})},computedBottom:function(){if(this.isPositioned)return this.app?this.$vuetify.application.bottom:0},computedLeft:function(){if(this.isPositioned)return this.app&&this.inset?this.$vuetify.application.left:0},computedRight:function(){if(this.isPositioned)return this.app&&this.inset?this.$vuetify.application.right:0},isPositioned:function(){return Boolean(this.absolute||this.fixed||this.app)},styles:function(){var t=parseInt(this.height);return Object(h["a"])(Object(h["a"])({},f["a"].options.computed.styles.call(this)),{},{height:isNaN(t)?t:Object(b["g"])(t),left:Object(b["g"])(this.computedLeft),right:Object(b["g"])(this.computedRight),bottom:Object(b["g"])(this.computedBottom)})}},methods:{updateApplication:function(){var t=parseInt(this.height);return isNaN(t)?this.$el?this.$el.clientHeight:0:t}},render:function(t){var e=this.setBackgroundColor(this.color,{staticClass:"v-footer",class:this.classes,style:this.styles});return t(this.tag,e,this.$slots.default)}}),_=Object(r["a"])(o,s,a,!1,null,"1c2d85d1",null);e["a"]=_.exports;u()(_,{VCol:l["a"],VFooter:g})},"34ba":function(t,e,i){"use strict";var s=i("1182"),a=i.n(s);a.a},"9f5c":function(t,e,i){"use strict";i.r(e);var s=function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("v-app",[i("nav-bar"),i("div",[i("v-container",[i("div",{staticClass:"teachermain"},[i("class-search")],1)])],1),i("foot-bar")],1)},a=[],n=i("d000"),o=i("3922"),r=i("2425"),c={name:"teachermain",components:{NavBar:n["a"],ClassSearch:o["default"],FootBar:r["a"]},created:function(){this.check_teacher()},methods:{check_teacher:function(){null==this.$session.get("jwt")&&this.$router.go(-1),this.userInfo&&"teacher"==this.userType||this.$router.push("/")}},computed:{userInfo:function(){return this.$store.getters.userInfo},userType:function(){return this.$store.getters.userInfo.userType}}},u=c,l=i("2877"),h=i("6544"),f=i.n(h),p=i("7496"),d=i("a523"),v=Object(l["a"])(u,s,a,!1,null,null,null);e["default"]=v.exports;f()(v,{VApp:p["a"],VContainer:d["a"]})},b189:function(t,e,i){t.exports=i.p+"img/logo1.78bd979b.png"},b5b6:function(t,e,i){},cfaf:function(t,e,i){},d000:function(t,e,i){"use strict";var s=function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",[i("section",{staticClass:"navigation"},[i("div",{staticClass:"nav-container"},[t._m(0),i("nav",[t._m(1),t.userInfo?i("ul",{staticClass:"nav-list"},[t._m(2),"teacher"===t.userInfo.userType?i("li",[i("a",{attrs:{href:"/teachermain"}},[t._v("모든 교실")])]):t._e(),"student"===t.userInfo.userType?i("li",[i("a",{attrs:{href:"/studentmain"}},[t._v("모든 교실")])]):t._e(),"teacher"===t.userInfo.userType?i("li",[i("a",{attrs:{href:"/class"}},[t._v("우리반")])]):t._e(),"student"===t.userInfo.userType?i("li",[i("a",{attrs:{href:"/class"}},[t._v("우리반")])]):t._e(),"teacher"===t.userInfo.userType?i("li",[i("a",{attrs:{href:"/mychildren"}},[t._v("내 아이관리")])]):t._e(),"student"===t.userInfo.userType?i("li",[i("a",{attrs:{href:"/childrendetail"}},[t._v("공부현황")])]):t._e(),i("li",[i("a",{attrs:{href:""}},[i("button",{on:{click:t.logout}},[t._v("나가기")])])])]):t._e()])])]),i("div",{staticClass:"container",staticStyle:{"text-align":"right"}},[t._v(t._s(t.userInfo.nickName)+"("+t._s(t.userInfo.userName)+")님")])])},a=[function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"brand"},[i("a",{attrs:{href:"/"}},[t._v("도담도담")])])},function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"nav-mobile"},[i("a",{attrs:{id:"nav-toggle",href:"#!"}},[i("span")])])},function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("li",[i("a",{attrs:{href:"/contentsmain"}},[t._v("혼자 공부방")])])}],n={name:"navbar",data:function(){return{logo:i("b189")}},methods:{logout:function(){this.$session.destroy(),this.$store.dispatch("LOGOUT"),this.$router.push("/")}},computed:{userInfo:function(){return this.$store.getters.userInfo}}},o=n,r=(i("34ba"),i("2877")),c=Object(r["a"])(o,s,a,!1,null,null,null);e["a"]=c.exports},d10f:function(t,e,i){"use strict";var s=i("2b0e");e["a"]=s["default"].extend({name:"ssr-bootable",data:function(){return{isBooted:!1}},mounted:function(){var t=this;window.requestAnimationFrame((function(){t.$el.setAttribute("data-booted","true"),t.isBooted=!0}))}})},fa8c:function(t,e,i){"use strict";var s=i("cfaf"),a=i.n(s);a.a}}]);
//# sourceMappingURL=chunk-6f09f1ba.1c2e92b1.js.map