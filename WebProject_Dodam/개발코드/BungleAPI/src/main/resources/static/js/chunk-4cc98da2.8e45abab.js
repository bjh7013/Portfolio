(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-4cc98da2"],{1182:function(t,i,e){},2425:function(t,i,e){"use strict";var a=function(){var t=this,i=t.$createElement,e=t._self._c||i;return e("v-footer",{attrs:{id:"app-footer",padless:""}},[e("div",{staticClass:"row"},[e("div",{staticClass:"col-sm-12 col-md-6"},[e("h6",[t._v("logo")]),e("p",{staticClass:"text-justify"},[t._v("소개")])]),e("div",{staticClass:"col-xs-6 col-md-3"},[e("h6",[t._v("Categories")]),e("ul",{staticClass:"footer-links"},[e("li",[e("a",{attrs:{href:"#"}},[t._v("도담도담 이용안내")])]),e("li",[e("a",{attrs:{href:"#"}},[t._v("고객센터")])]),e("li",[e("a",{attrs:{href:"#"}},[t._v("이용약관")])])])]),e("div",{staticClass:"col-xs-6 col-md-3"},[e("h6",[t._v("Quick Links")]),e("ul",{staticClass:"footer-links"},[e("li",[e("a",{attrs:{href:"#"}},[t._v("도담도담 공지사항")])])])])]),e("div",{staticClass:"row"},[e("div",{staticClass:"col-md-8 col-sm-6 col-xs-12"},[e("p",{staticClass:"copyright-text"},[t._v("Copyright © 2020 All Rights Reserved by "),e("a",{attrs:{href:"#"}},[t._v("Bungle")])])]),e("div",{staticClass:"col-md-4 col-sm-6 col-xs-12"})])])},s=[],o={name:"footbar"},n=o,r=(e("b478"),e("2877")),c=e("6544"),l=e.n(c),u=(e("a9e3"),e("c7cd"),e("5530")),p=(e("b5b6"),e("8dd9")),h=e("fe6c"),d=e("58df");function f(t){var i=arguments.length>1&&void 0!==arguments[1]?arguments[1]:[];return Object(d["a"])(Object(h["b"])(["absolute","fixed"])).extend({name:"applicationable",props:{app:Boolean},computed:{applicationProperty:function(){return t}},watch:{app:function(t,i){i?this.removeApplication(!0):this.callUpdate()},applicationProperty:function(t,i){this.$vuetify.application.unregister(this._uid,i)}},activated:function(){this.callUpdate()},created:function(){for(var t=0,e=i.length;t<e;t++)this.$watch(i[t],this.callUpdate);this.callUpdate()},mounted:function(){this.callUpdate()},deactivated:function(){this.removeApplication()},destroyed:function(){this.removeApplication()},methods:{callUpdate:function(){this.app&&this.$vuetify.application.register(this._uid,this.applicationProperty,this.updateApplication())},removeApplication:function(){var t=arguments.length>0&&void 0!==arguments[0]&&arguments[0];(t||this.app)&&this.$vuetify.application.unregister(this._uid,this.applicationProperty)},updateApplication:function(){return 0}}})}var v=e("2b0e"),b=v["default"].extend({name:"ssr-bootable",data:function(){return{isBooted:!1}},mounted:function(){var t=this;window.requestAnimationFrame((function(){t.$el.setAttribute("data-booted","true"),t.isBooted=!0}))}}),m=e("80d2"),g=Object(d["a"])(p["a"],f("footer",["height","inset"]),b).extend({name:"v-footer",props:{height:{default:"auto",type:[Number,String]},inset:Boolean,padless:Boolean,tag:{type:String,default:"footer"}},computed:{applicationProperty:function(){return this.inset?"insetFooter":"footer"},classes:function(){return Object(u["a"])(Object(u["a"])({},p["a"].options.computed.classes.call(this)),{},{"v-footer--absolute":this.absolute,"v-footer--fixed":!this.absolute&&(this.app||this.fixed),"v-footer--padless":this.padless,"v-footer--inset":this.inset})},computedBottom:function(){if(this.isPositioned)return this.app?this.$vuetify.application.bottom:0},computedLeft:function(){if(this.isPositioned)return this.app&&this.inset?this.$vuetify.application.left:0},computedRight:function(){if(this.isPositioned)return this.app&&this.inset?this.$vuetify.application.right:0},isPositioned:function(){return Boolean(this.absolute||this.fixed||this.app)},styles:function(){var t=parseInt(this.height);return Object(u["a"])(Object(u["a"])({},p["a"].options.computed.styles.call(this)),{},{height:isNaN(t)?t:Object(m["d"])(t),left:Object(m["d"])(this.computedLeft),right:Object(m["d"])(this.computedRight),bottom:Object(m["d"])(this.computedBottom)})}},methods:{updateApplication:function(){var t=parseInt(this.height);return isNaN(t)?this.$el?this.$el.clientHeight:0:t}},render:function(t){var i=this.setBackgroundColor(this.color,{staticClass:"v-footer",class:this.classes,style:this.styles});return t(this.tag,i,this.$slots.default)}}),_=Object(r["a"])(n,a,s,!1,null,"52f96b69",null);i["a"]=_.exports;l()(_,{VFooter:g})},"300a":function(t,i,e){},"34ba":function(t,i,e){"use strict";var a=e("1182"),s=e.n(a);s.a},"9f5c":function(t,i,e){"use strict";e.r(i);var a=function(){var t=this,i=t.$createElement,e=t._self._c||i;return e("v-container",[e("nav-bar"),e("div",{staticClass:"teachermain"},[e("br"),e("br"),e("br"),e("h1",[t._v("선생님용 메인페이지입니다")])]),e("foot-bar")],1)},s=[],o=e("d000"),n=e("2425"),r={name:"teachermain",components:{NavBar:o["a"],FootBar:n["a"]}},c=r,l=e("2877"),u=e("6544"),p=e.n(u),h=e("a523"),d=Object(l["a"])(c,a,s,!1,null,null,null);i["default"]=d.exports;p()(d,{VContainer:h["a"]})},b189:function(t,i,e){t.exports=e.p+"img/logo1.78bd979b.png"},b478:function(t,i,e){"use strict";var a=e("300a"),s=e.n(a);s.a},b5b6:function(t,i,e){},d000:function(t,i,e){"use strict";var a=function(){var t=this,i=t.$createElement;t._self._c;return t._m(0)},s=[function(){var t=this,i=t.$createElement,e=t._self._c||i;return e("div",[e("section",{staticClass:"navigation"},[e("div",{staticClass:"nav-container"},[e("div",{staticClass:"brand"},[e("a",{attrs:{href:"/"}},[t._v("도담도담")])]),e("nav",[e("div",{staticClass:"nav-mobile"},[e("a",{attrs:{id:"nav-toggle",href:"#!"}},[e("span")])]),e("ul",{staticClass:"nav-list"},[e("li",[e("a",{attrs:{href:"/"}},[t._v("학습컨텐츠")])]),e("li",[e("a",{attrs:{href:"/about"}},[t._v("클래스")])]),e("li",[e("a",{attrs:{href:"/login"}},[t._v("로그인")])])])])])])])}],o={name:"navbar",data:function(){return{logo:e("b189")}}},n=o,r=(e("34ba"),e("2877")),c=Object(r["a"])(n,a,s,!1,null,null,null);i["a"]=c.exports}}]);
//# sourceMappingURL=chunk-4cc98da2.8e45abab.js.map