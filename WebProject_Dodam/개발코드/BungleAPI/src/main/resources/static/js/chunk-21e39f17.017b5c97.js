(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-21e39f17"],{"1a96":function(t,e,i){t.exports=i.p+"img/doubleconsonant.7a7efeea.jpg"},"24b2":function(t,e,i){"use strict";i("a9e3");var s=i("80d2"),n=i("2b0e");e["a"]=n["default"].extend({name:"measurable",props:{height:[Number,String],maxHeight:[Number,String],maxWidth:[Number,String],minHeight:[Number,String],minWidth:[Number,String],width:[Number,String]},computed:{measurableStyles:function(){var t={},e=Object(s["g"])(this.height),i=Object(s["g"])(this.minHeight),n=Object(s["g"])(this.minWidth),r=Object(s["g"])(this.maxHeight),a=Object(s["g"])(this.maxWidth),o=Object(s["g"])(this.width);return e&&(t.height=e),i&&(t.minHeight=i),n&&(t.minWidth=n),r&&(t.maxHeight=r),a&&(t.maxWidth=a),o&&(t.width=o),t}}})},"36a7":function(t,e,i){},4261:function(t,e,i){},"45f1":function(t,e,i){"use strict";var s=i("4261"),n=i.n(s);n.a},6206:function(t,e,i){t.exports=i.p+"img/consonant.d8475d09.jpg"},"6adf":function(t,e,i){t.exports=i.p+"img/vowel.c52d7844.jpg"},"8efc":function(t,e,i){},"90a2":function(t,e,i){"use strict";i("7db0");var s=i("53ca");function n(t,e){var i=e.modifiers||{},n=e.value,a="object"===Object(s["a"])(n)?n:{handler:n,options:{}},o=a.handler,c=a.options,h=new IntersectionObserver((function(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:[],s=arguments.length>1?arguments[1]:void 0;if(t._observe){if(o&&(!i.quiet||t._observe.init)){var n=Boolean(e.find((function(t){return t.isIntersecting})));o(e,s,n)}t._observe.init&&i.once?r(t):t._observe.init=!0}}),c);t._observe={init:!1,observer:h},h.observe(t)}function r(t){t._observe&&(t._observe.observer.unobserve(t),delete t._observe)}var a={inserted:n,unbind:r};e["a"]=a},"94d7":function(t,e,i){"use strict";i.r(e);var s=function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("v-container",[s("section",[s("div",{staticClass:"back_menu",on:{click:t.gocontents}},[s("i",{staticClass:"fas fa-arrow-circle-left fa-1x",staticStyle:{margin:"5px"}}),t._v("학습컨텐츠로 돌아가기")])]),s("div",{staticStyle:{clear:"both"}},[s("h1",[s("b",[t._v("[한글공부]")])]),s("router-link",{attrs:{to:"/koreanconsonant"}},[s("v-img",{staticClass:"aimg",attrs:{src:i("6206"),"aspect-ratio":"1"}})],1),s("router-link",{attrs:{to:"/koreandoubleconsonant"}},[s("v-img",{staticClass:"aimg",attrs:{src:i("1a96"),"aspect-ratio":"1"}})],1),s("router-link",{attrs:{to:"/koreanvowel"}},[s("v-img",{staticClass:"aimg",attrs:{src:i("6adf"),"aspect-ratio":"1"}})],1),s("router-link",{attrs:{to:"/koreandoublevowel"}},[s("v-img",{staticClass:"aimg",attrs:{src:i("cd14"),"aspect-ratio":"1"}})],1),s("router-link",{attrs:{to:"/koreanletter"}},[s("v-img",{staticClass:"aimg",attrs:{src:i("f03a"),"aspect-ratio":"1"}})],1)],1)])},n=[],r={name:"alphabet",methods:{gocontents:function(){this.$router.push("/contentsmain")}}},a=r,o=(i("45f1"),i("2877")),c=i("6544"),h=i.n(c),d=i("a523"),l=i("adda"),u=Object(o["a"])(a,s,n,!1,null,"47bde5d4",null);e["default"]=u.exports;h()(u,{VContainer:d["a"],VImg:l["a"]})},adda:function(t,e,i){"use strict";i("a15b"),i("a9e3");var s=i("53ca"),n=(i("8efc"),i("90a2")),r=(i("36a7"),i("24b2")),a=i("58df"),o=Object(a["a"])(r["a"]).extend({name:"v-responsive",props:{aspectRatio:[String,Number]},computed:{computedAspectRatio:function(){return Number(this.aspectRatio)},aspectStyle:function(){return this.computedAspectRatio?{paddingBottom:1/this.computedAspectRatio*100+"%"}:void 0},__cachedSizer:function(){return this.aspectStyle?this.$createElement("div",{style:this.aspectStyle,staticClass:"v-responsive__sizer"}):[]}},methods:{genContent:function(){return this.$createElement("div",{staticClass:"v-responsive__content"},this.$slots.default)}},render:function(t){return t("div",{staticClass:"v-responsive",style:this.measurableStyles,on:this.$listeners},[this.__cachedSizer,this.genContent()])}}),c=o,h=i("7560"),d=i("d9f7"),l=i("d9bd"),u="undefined"!==typeof window&&"IntersectionObserver"in window;e["a"]=Object(a["a"])(c,h["a"]).extend({name:"v-img",directives:{intersect:n["a"]},props:{alt:String,contain:Boolean,eager:Boolean,gradient:String,lazySrc:String,options:{type:Object,default:function(){return{root:void 0,rootMargin:void 0,threshold:void 0}}},position:{type:String,default:"center center"},sizes:String,src:{type:[String,Object],default:""},srcset:String,transition:{type:[Boolean,String],default:"fade-transition"}},data:function(){return{currentSrc:"",image:null,isLoading:!0,calculatedAspectRatio:void 0,naturalWidth:void 0,hasError:!1}},computed:{computedAspectRatio:function(){return Number(this.normalisedSrc.aspect||this.calculatedAspectRatio)},normalisedSrc:function(){return this.src&&"object"===Object(s["a"])(this.src)?{src:this.src.src,srcset:this.srcset||this.src.srcset,lazySrc:this.lazySrc||this.src.lazySrc,aspect:Number(this.aspectRatio||this.src.aspect)}:{src:this.src,srcset:this.srcset,lazySrc:this.lazySrc,aspect:Number(this.aspectRatio||0)}},__cachedImage:function(){if(!(this.normalisedSrc.src||this.normalisedSrc.lazySrc||this.gradient))return[];var t=[],e=this.isLoading?this.normalisedSrc.lazySrc:this.currentSrc;this.gradient&&t.push("linear-gradient(".concat(this.gradient,")")),e&&t.push('url("'.concat(e,'")'));var i=this.$createElement("div",{staticClass:"v-image__image",class:{"v-image__image--preload":this.isLoading,"v-image__image--contain":this.contain,"v-image__image--cover":!this.contain},style:{backgroundImage:t.join(", "),backgroundPosition:this.position},key:+this.isLoading});return this.transition?this.$createElement("transition",{attrs:{name:this.transition,mode:"in-out"}},[i]):i}},watch:{src:function(){this.isLoading?this.loadImage():this.init(void 0,void 0,!0)},"$vuetify.breakpoint.width":"getSrc"},mounted:function(){this.init()},methods:{init:function(t,e,i){if(!u||i||this.eager){if(this.normalisedSrc.lazySrc){var s=new Image;s.src=this.normalisedSrc.lazySrc,this.pollForSize(s,null)}this.normalisedSrc.src&&this.loadImage()}},onLoad:function(){this.getSrc(),this.isLoading=!1,this.$emit("load",this.src)},onError:function(){this.hasError=!0,this.$emit("error",this.src)},getSrc:function(){this.image&&(this.currentSrc=this.image.currentSrc||this.image.src)},loadImage:function(){var t=this,e=new Image;this.image=e,e.onload=function(){e.decode?e.decode().catch((function(e){Object(l["c"])("Failed to decode image, trying to render anyway\n\n"+"src: ".concat(t.normalisedSrc.src)+(e.message?"\nOriginal error: ".concat(e.message):""),t)})).then(t.onLoad):t.onLoad()},e.onerror=this.onError,this.hasError=!1,e.src=this.normalisedSrc.src,this.sizes&&(e.sizes=this.sizes),this.normalisedSrc.srcset&&(e.srcset=this.normalisedSrc.srcset),this.aspectRatio||this.pollForSize(e),this.getSrc()},pollForSize:function(t){var e=this,i=arguments.length>1&&void 0!==arguments[1]?arguments[1]:100,s=function s(){var n=t.naturalHeight,r=t.naturalWidth;n||r?(e.naturalWidth=r,e.calculatedAspectRatio=r/n):null!=i&&!e.hasError&&setTimeout(s,i)};s()},genContent:function(){var t=c.options.methods.genContent.call(this);return this.naturalWidth&&this._b(t.data,"div",{style:{width:"".concat(this.naturalWidth,"px")}}),t},__genPlaceholder:function(){if(this.$slots.placeholder){var t=this.isLoading?[this.$createElement("div",{staticClass:"v-image__placeholder"},this.$slots.placeholder)]:[];return this.transition?this.$createElement("transition",{props:{appear:!0,name:this.transition}},t):t[0]}}},render:function(t){var e=c.options.render.call(this,t),i=Object(d["a"])(e.data,{staticClass:"v-image",attrs:{"aria-label":this.alt,role:this.alt?"img":void 0},class:this.themeClasses,directives:u?[{name:"intersect",modifiers:{once:!0},value:{handler:this.init,options:this.options}}]:void 0});return e.children=[this.__cachedSizer,this.__cachedImage,this.__genPlaceholder(),this.genContent()],t(e.tag,i,e.children)}})},cd14:function(t,e,i){t.exports=i.p+"img/doublevowel.1f959ba3.png"},f03a:function(t,e,i){t.exports=i.p+"img/letter.9e561f91.jpg"}}]);
//# sourceMappingURL=chunk-21e39f17.017b5c97.js.map