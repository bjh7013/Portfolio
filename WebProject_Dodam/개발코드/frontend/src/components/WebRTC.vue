<template>
    <div style="display:fixed;width:100vw;height:100vh;">
        <div id="video-section">
            <video :id="`localVideo0`" autoplay playsinline controls="false"/>
        </div>


        
    <img src="../assets/images/st.png" class="vmimg vmright"/>
    <img src="../assets/images/teacher.png" class="vmimg vmleft"/>
    <img src="../assets/images/chilpan.png" class="bkimg"/>
    
        <!-- <video id="remoteVideo" autoplay playsinline controls="false"/> -->
    </div>
    
</template>

<script>
import Stomp from 'webstomp-client';
import SockJS from 'sockjs-client';

export default {
    data(){
        return{
            configuration : { 
                iceServers: [{'urls': 'stun:stun.l.google.com:19302'}]
            },
            localStream : null,
            localVideo: null,

            peerConnections : {},
            remoteVideos: {},
            remoteStreams : {},
            dataChannel : null,
            message : null,
            stompClient: null,
            classNo : 60
        }
    },
    mounted(){
        let socket = new SockJS(this.$store.getters.WS_URL + `/ws-stomp`)
        let stompClient = this.stompClient =  Stomp.over(socket);
        
        stompClient.connect(
            {},
            async (frame) => {
                // connected = true;
                console.log('소켓 연결 성공', frame);
                              
                stompClient.subscribe(`/sub/webrtc/${this.classNo}`, async (res) => {
                    let body = JSON.parse(res.body);
                    let type = body.type 
                    let data = body.data
                    let userNo = body.userNo;
                    let toUser = body.toUser;

                    if(!userNo || userNo == await this.$store.getters.userInfo.userNo){
                        return
                    }

                    if(type == 'join'){
                        await this.makeConnection(userNo)
                        await this.offer(userNo)
                    }

                    if(type == 'candidate'){
                        if(this.peerConnections[userNo])
                            this.peerConnections[userNo].addIceCandidate(new RTCIceCandidate(data))
                    }

                    if(this.peerConnections[userNo] && (this.peerConnections[userNo].connectionState == 'connected')){
                        return;
                    }
                               
                    if(type == 'offer'){
                        await this.makeConnection(userNo)
                        await this.peerConnections[userNo].setRemoteDescription(new RTCSessionDescription(data));
                                            
                        this.peerConnections[userNo].createAnswer(async (answer) => {
                            await this.peerConnections[userNo].setLocalDescription(answer)
                            this.send({type: 'answer', data : answer, userNo: this.$store.getters.userInfo.userNo, toUser:userNo,classNo : this.classNo})
                        }, (error) => {
                            console.log('error in create answer', error)
                        })

                    }else if(type == 'answer'){
                        if(toUser != this.$store.getters.userInfo.userNo)
                            return;

                        // console.dir(this.peerConnection)
                        if(this.peerConnections[userNo].signalingState == 'stable')
                            return
                        await this.peerConnections[userNo].setRemoteDescription(new RTCSessionDescription(data))
                    
                    }
                });
                    
                stompClient.ws.onclose = function(){
                    // this.connected = false;
                    // context.commit('sockConnected', false);
                    // setInterval( () => {
                        // if(context.getters.userInfo && !context.getters.sockConnected){
                            // context.dispatch("connectWS", context.getters.userInfo.userNo)
                    // } 
                    // }, 1000)
                    console.log('close------------------------')
                }

                await this.setLocalStream()
                
                this.send({
                    type : "join",
                    userNo : this.$store.getters.userInfo.userNo,
                    classNo : this.classNo
                })

            },
            error => {
                // 소켓 연결 실패
                console.log('소켓 연결 실패', error);
                // this.connected = false;
            }
        );
        
    },
    methods:{
        async getUserMedia(){
            const constraints = {'video': { facingMode: { exact: "user" }}, 'audio': true}
            return await navigator.mediaDevices.getUserMedia(constraints);
        },
        playVideoFromCamera() {
            try {
                this.localVideo = document.querySelector('#localVideo0');
                this.localVideo.srcObject = this.localStream;
            } catch(error) {
                console.error('Error opening video camera.', error);
            }
        },
        send(msg){  
            // msg.test = 1
            // console.dir(msg)
            console.log(this.$store.getters.userInfo.userNo)
            console.dir(msg)
            console.dir(msg)
            msg['userNo'] = this.$store.getters.userInfo.userNo
            this.stompClient.send(`/pub/establish`, JSON.stringify(msg), {});
        },

        async setLocalStream(){
            console.log('setlocalstream==============================')      
            this.localStream = await this.getUserMedia();
            this.playVideoFromCamera()
            console.log('setlocalstream==============================')      
        },

        async makeConnection(userNo){
            this.peerConnections[userNo] = await new RTCPeerConnection(this.configuration);
            console.dir(this.peerConnections[userNo])
            
            this.peerConnections[userNo].onicecandidate = (event) => {
                if (event.candidate) {
                    this.send({
                        type : "candidate",
                        data : event.candidate,
                        userNo : this.$store.getters.userInfo.userNo,
                        classNo : this.classNo
                    });
                }
            }

            //local
            this.localStream.getTracks().forEach(track => {
                this.peerConnections[userNo].addTrack(track, this.localStream);
            });
            

            //remote
            this.remoteStreams[userNo] = new MediaStream();
            let remoteVideo = document.createElement('video')
            remoteVideo.id = "user"+userNo;
            remoteVideo.autoplay = true
            remoteVideo.playsInline = true
            remoteVideo.srcObject = this.remoteStreams[userNo]
            // var delvideo =document.getElementsByTagName("video");
            // for(var vid =0; vid<delvideo.length-1;vid++){
            //     var del = delvideo[vid];
            //     if(del.id =="" || del.id =="user"+userNo){
            //         del.style.display="none";
            //     }
            // }
            document.getElementById('video-section').appendChild(remoteVideo)
            this.remoteVideos[userNo] = remoteVideo

            this.peerConnections[userNo].addEventListener('track', async (event) => {
                this.remoteStreams[userNo].addTrack(event.track, this.remoteStreams[userNo]);
            });

  
            this.peerConnections[userNo].addEventListener('connectionstatechange', () => {
                // if (this.peerConnections[userNo].connectionState === 'connected') {
                //     // Peers connected!
                    
                // }
                if(this.peerConnections[userNo].connectionState == 'disconnected'){
                    this.peerConnections[userNo].close()
                    // console.dir(this.remoteStreams[userNo])
                    this.remoteStreams[userNo].getTracks().forEach((track) => {
                        track.stop();
                        console.log('stop')
                    });
                    // var dellv = document.getElementById("video"+userNo);
                    // var par = document.getElementById("video-section");
                    // var trow = par.removeChild(dellv);
                    // trow;
                    this.remoteVideos[userNo].parentElement.removeChild(this.remoteVideos[userNo])
                }
                // else if(this.peerConnections[userNo].connectionState == 'closed'){
                //     alert('closed')
                // }
            });
        },
        offer(userNo){
            this.peerConnections[userNo].createOffer(async (offer) => {
                
                console.log(this)
                
                await this.peerConnections[userNo].setLocalDescription(offer);
                this.send({
                    type : "offer",
                    data : this.peerConnections[userNo].localDescription,
                    userNo: this.$store.getters.userInfo.userNo,
                    classNo : this.classNo
                });
                console.dir(offer)
                
            }, function(error) {
                // Handle error here
                console.log('error in offer', error)
            });
        },
    }
}
</script>

<style>
.bkimg{
  height: 100%;
  width: 100%;
  position: absolute;
  bottom: 0;
  right: 0;
  z-index: 0;
} 
.vmimg{
    max-height: 40%;
  max-width: 50%;
  position: absolute;
  bottom: 0;
  right: 0;
  z-index: 1;
} 
.vmleft{
    
  left: 0;
}
#video-section{
    display: flex;
    justify-content: center; 
    align-content: flex-start;
    flex-wrap: wrap;
    width:100vw;
    height: 80vh;
    margin: 10vh 0;
}
video{
    width: 28%;
    z-index: 2;
}
</style>