(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-f2f4a18c","chunk-d56cccb8"],{"0212":function(e,t,n){},2241:function(e,t,n){"use strict";n.r(t);var s=function(){var e=this,t=e.$createElement;e._self._c;return e._m(0)},r=[function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"wrapper"},[n("div",{staticClass:"content"},[n("div",[n("div",[n("span",[e._v("도")]),n("span",[e._v("담")]),n("span",[e._v("도")]),n("span",[e._v("담")])])])])])}],a={},o=a,i=(n("8ed2"),n("2877")),u=Object(i["a"])(o,s,r,!1,null,"1dfbe496",null);t["default"]=u.exports},"2af3":function(e,t,n){"use strict";var s=n("5a57"),r=n.n(s);r.a},"5a57":function(e,t,n){},"8ed2":function(e,t,n){"use strict";var s=n("0212"),r=n.n(s);r.a},cd56:function(e,t,n){"use strict";n.r(t);var s=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",[n("div",{staticClass:"main-header"},[n("router-link",{attrs:{to:"/#"}},[n("logo",{staticClass:"logo"})],1)],1),n("div",{staticClass:"contact-us"},[e._m(0),n("input",{directives:[{name:"model",rawName:"v-model",value:e.userId,expression:"userId"}],attrs:{placeholder:"아이디",required:"",type:"text"},domProps:{value:e.userId},on:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.login(t)},input:function(t){t.target.composing||(e.userId=t.target.value)}}}),n("input",{directives:[{name:"model",rawName:"v-model",value:e.userPw,expression:"userPw"}],attrs:{name:"password",placeholder:"비밀번호",type:"password"},domProps:{value:e.userPw},on:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.login(t)},input:function(t){t.target.composing||(e.userPw=t.target.value)}}}),n("div",{staticClass:"login-btns"},[n("button",{on:{click:e.login}},[e._v("들어가기")]),n("br"),n("p",{staticClass:"login-ment"},[e._v("아직 회원이 아니신가요?")]),n("button",{on:{click:e.join}},[e._v("회원가입")])])])])},r=[function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"guid-link"},[n("p",{staticClass:"guid-ment"},[e._v("도담도담이 궁금하다면? ")]),e._v(" "),n("a",{attrs:{href:"/dodam"}},[e._v("알아보기")])])}],a=n("2241"),o=n("bc3a"),i=n.n(o),u={name:"main",components:{Logo:a["default"]},data:function(){return{includeFiles:!0,enabled:!1,userId:"",userPw:""}},methods:{logout:function(){this.$session.destroy(),this.$store.dispatch("LOGOUT"),this.$router.go()},login:function(){var e=this,t={userId:this.userId,userPw:this.userPw},n={header:{"Content-Type":"application/json"}};i.a.post("/user/login",t,n).then((function(t){200===t.status&&t.headers.authorization?(e.$session.start(),e.$session.set("jwt",t.headers.authorization),i.a.defaults.headers.common["Authorization"]=t.headers.authorization,e.$store.dispatch("LOGIN",t.data),"teacher"==e.$store.getters.userInfo.userType?e.$router.push("/teachermain"):e.$router.push("/contentsmain")):alert("아이디와 비밀번호를 확인해주세요")})).catch((function(e){alert("아이디와 비밀번호를 확인해주세요System Error")}))},checkLogin:function(){var e=this;null!=this.$session.get("jwt")&&i.a.get("user/info").then((function(t){"teacher"==t.data.userType?e.$router.push("/teachermain"):e.$router.push("/contentsmain")}))},join:function(){this.$router.push("/join")}},computed:{userInfo:function(){return this.$store.getters.userInfo},userType:function(){return this.$store.getters.userInfo.userType}},created:function(){this.checkLogin()}},c=u,l=(n("2af3"),n("2877")),d=Object(l["a"])(c,s,r,!1,null,"f5a04f7c",null);t["default"]=d.exports}}]);
//# sourceMappingURL=chunk-f2f4a18c.5b8d3263.js.map