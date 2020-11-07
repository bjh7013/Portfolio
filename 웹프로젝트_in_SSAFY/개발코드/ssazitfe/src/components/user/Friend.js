import axios from 'axios';
export default{
    name: "friend",
    data() {
    return {
      rows: 100,
      follower : [],
      followercheck : [],
      following : [],
      follow : [],
      blocklist : [],
      search : '',
      select : 1,
      userImagefollow : [],
      userImagefollower : [],
      userImagefollowing : [],
      userImageblock:[],
      blog_follow : [],
      blog_follower : [],
      blog_following : [],
      blog_block:[],
      // page: 1,
    };
  },
    methods : {
       get_following(){
           axios.get('/user/following')
           .then((Response)=>{
           // console.log(Response);
           this.following = Response.data;
           for(let i=0;i<Response.data.length;i++){
           Response.data[i].userId
           console.log(Response.data[i].userId+"이웃 아이디")
           axios.post('/user/list',{
           searchKey : "userId",
           searchValue : Response.data[i].userId
           }).then((Res)=>{
           console.log(Res.data);  
           console.log("Hi위는데이또");
   
           for(let j=0;j<Res.data.length;j++){
             if(Res.data[j].userId==Response.data[i].userId){
               this.blogInfo = Res.data[j]; // 해당 블로그 정보
               this.userImagefollowing.splice(i,1,this.serverURL + Res.data[j].profileImg);
               this.blog_following[i]=Res.data[j].userId;
               break;
             }
           }
           // this.blogInfo = Response.data[0];
           // this.userImagefollowing.push(this.serverURL + this.blogInfo.profileImg);
           console.log(this.blogInfo.userId+"이거");
           console.log(this.serverURL+"이거");
           console.log(this.blogInfo.profileImg+"이거");
       })
         }
       })
    },
    get_follower(){
        axios.get('/user/follower')
        .then((Response)=>{
          // console.log(Response);
          this.follower = Response.data;
          for(let i=0;i<Response.data.length;i++){
            Response.data[i].userId
            console.log(Response.data[i].userId+"이웃 아이디")
            axios.get('/user/following')
            .then((Res)=>{
              for(let j=0;j<Res.data.length;j++){
                if(Response.data[i].follower==Res.data[j].following){
                  this.followercheck.splice(i,1,false);
                  break;
                }
                if(j==Res.data.length-1){
                  this.followercheck.splice(i,1,true);
                }
              }
            })
            axios.post('/user/list',{
            searchKey : "userId",
            searchValue : Response.data[i].userId
            }).then((Res)=>{
            console.log(Res.data);  
            console.log("Hi위는데이또");
    
            for(let j=0;j<Res.data.length;j++){
              if(Res.data[j].userId==Response.data[i].userId){
                this.blogInfo = Res.data[j]; // 해당 블로그 정보
                this.userImagefollower.splice(i,1,this.serverURL + Res.data[j].profileImg);
                this.blog_follower[i]=Res.data[j].userId;
                break;
              }
            }
            // this.blogInfo = Response.data[0];
            // this.userImagefollower.push(this.serverURL + this.blogInfo.profileImg);
            console.log(this.blogInfo.userId+"이거");
            console.log(this.serverURL+"이거");
            console.log(this.blogInfo.profileImg+"이거");
        })
          }
        })
    },
    get_f4f(){
        axios.get('/user/f4f')
        .then((Response)=>{
          // console.log(Response);
          this.follow = Response.data;
          for(let i=0;i<Response.data.length;i++){
            Response.data[i].userId
            console.log(Response.data[i].userId+"이웃 아이디")
            axios.post('/user/list',{
            searchKey : "userId",
            searchValue : Response.data[i].userId
            }).then((Res)=>{
            console.log(Res.data);  
            console.log("Hi위는데이또");
    
            for(let j=0;j<Res.data.length;j++){
              if(Res.data[j].userId==Response.data[i].userId){
                this.blogInfo = Res.data[j]; // 해당 블로그 정보
                this.userImagefollow.splice(i,1,this.serverURL + Res.data[j].profileImg);
                this.blog_follow[i]=Res.data[j].userId;
                break;
              }
            }
            // this.blogInfo = Response.data[0];
            // this.userImagefollow.push(this.serverURL + this.blogInfo.profileImg);
            console.log(this.blogInfo.userId+"이거");
            console.log(this.serverURL+"이거");
            console.log(this.blogInfo.profileImg+"이거");
            console.log(i+"다")
        })
          }
        })
    },
    get_block(){
        axios.get('/user/block')
        .then((Response)=>{
          console.log(Response);
          this.blocklist = Response.data;
          for(let i=0;i<Response.data.length;i++){
            Response.data[i].userId
            console.log(Response.data[i].userId+"이웃 아이디")
            axios.post('/user/list',{
            searchKey : "userId",
            searchValue : Response.data[i].userId
            }).then((Res)=>{
            console.log(Res.data);  
            console.log("Hi위는데이또");
    
            for(let j=0;j<Res.data.length;j++){
              if(Res.data[j].userId==Response.data[i].userId){
                this.userImageblock.splice(i,1,this.serverURL + Res.data[j].profileImg);
                this.blog_block[i]=Res.data[j].userId;
                break;
              }
            }
            console.log(this.blogInfo.userId+"이거");
            console.log(this.serverURL+"이거");
            console.log(this.blogInfo.profileImg+"이거");
        })
          }
        })
    }
    }

}