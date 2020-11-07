package com.ssazit.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssazit.dto.Block;
import com.ssazit.dto.Follow;
import com.ssazit.dto.Message;
import com.ssazit.dto.Noti;
import com.ssazit.dto.User;
import com.ssazit.service.UserService;
import com.ssazit.util.JWTUtil;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService service;
	@Autowired
	JWTUtil jwtUtil;

	
	@GetMapping("/list")
	public List<User> getListTest() {
		User user = new User();
		List<User> result = service.selectUserList(user);
		return result;
	}//테스트용
	 
	@GetMapping("/listone/{userNo}")
	public User getListOne(@PathVariable int userNo) {
		User user = new User();
		user.setUserNo(userNo);
		User result = service.selectUserOne(user);
		return result;
	}//테스트용
	
	@GetMapping("/searchlist/{searchValue}/{limitStart}")
	public List<User> getSearchListTest(@PathVariable String searchValue,@PathVariable int limitStart) {
		User user = new User();
		System.out.println(searchValue+"값");
		System.out.println(limitStart+"값");
		user.setSearchValue(searchValue);
		user.setLimitStart(limitStart);
		List<User> result = service.selectUserList(user);	
		return result;
	}
	
	@GetMapping("/searchlistcount/{searchValue}")
	public int getSearchListCount(@PathVariable String searchValue) {
		User user = new User();
		user.setSearchValue(searchValue);
		int result = service.selectUserListCount(user);	
		return result;
	}
	
	@PostMapping("/list")
	public List<User> getList(@RequestBody User user){
		System.out.println(user);
		List<User> result = service.selectUserList(user);
		return result;
	}//테스트용
	
	@GetMapping("/mypage")//패스워드 입력없이 볼 수 있는 기본정보 조회
	public ResponseEntity<User> myPage(HttpServletRequest request){
		User userinfo = (User) request.getAttribute("userinfo");
		User result = service.myInfo(userinfo);
		System.out.println(userinfo);
		if(result != null) {
			return new ResponseEntity<User>(result, HttpStatus.OK);			
		}else {
			return new ResponseEntity<User>(result, HttpStatus.UNAUTHORIZED);			
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody User user) {
		User loginUser = service.login(user);
		System.out.println("asdasdasdasdasd");
		if(loginUser != null) {//로그인 성공
			HttpHeaders headers = new HttpHeaders();
			List<String> exposeHeaders = new ArrayList<>();
			
//			jwt토큰 발행
			headers.setBearerAuth(jwtUtil.createToken(loginUser));
			
			exposeHeaders.add("Authorization");
			
			headers.setAccessControlExposeHeaders(exposeHeaders);
//			headers.setAccessControlAllowCredentials(true);

//			result.setStatus("success");
//			result.setUser(loginUser);
			System.out.println("success!!!!!!!!!!!!!");
			return new ResponseEntity<User>(loginUser, headers, HttpStatus.OK);
		}else {//로그인 실패
			return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping("/idcheck")//
	public boolean idCheck(@RequestBody User user) {
		return service.idCheck(user);//이미 존재하는 아이디이면 true, 존재하지 않는 아이디면 false
	}
	
	@PostMapping("/pwcheck")
	public ResponseEntity<User> pwCheck(@RequestBody User user, HttpServletRequest request) {
		user.setUserNo(((User) request.getAttribute("userinfo")).getUserNo());
		
		try {
			User result = service.pwCheck(user);
			if(result != null) {
				return new ResponseEntity<User>(result, HttpStatus.OK);				
			}else {
				return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/modify")
	public ResponseEntity<User> modifyUser(@RequestBody User user, HttpServletRequest request){
		user.setUserNo(((User) request.getAttribute("userinfo")).getUserNo());
		
		try {
			User result = service.updateUser(user);
			
			return new ResponseEntity<User>(result, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PutMapping("/setpass")
	public ResponseEntity<User> setPass(@RequestBody User user){
		try {
			service.updateUser(user);
			return new ResponseEntity<User>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/nickname")
	public ResponseEntity<User> modifyNickName(@RequestBody User user, HttpServletRequest request){
		user.setUserNo(((User) request.getAttribute("userinfo")).getUserNo());
		
		try {
			return new ResponseEntity<User>(service.modifyNickName(user), HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/join")
	public String join(@RequestBody User user) throws SQLException {
		String result = null;	
		if(service.insertUser(user) == 1) {
			result = "success";
		}else {
			result = "fail";
		}
		
		return result;
	}
	
	@DeleteMapping("/withdrawal")
	public ResponseEntity<User> withdrawal(HttpServletRequest request){
		try {
			service.withdrawal((User)request.getAttribute("userinfo"));
//			response.sendRedirect("http://localhost:8080/logout");
			
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	/**
	 * FOLLOW
	 */
	@GetMapping("/following")
	public ResponseEntity<List<Follow>> getFollowing(HttpServletRequest request, String searchValue){
		try {
			User user = (User) request.getAttribute("userinfo");
			if(searchValue == null) {
				user.setSearchValue("");			
			} else {
				user.setSearchValue(searchValue);
			}
			return new ResponseEntity<>(service.selectFollowingList(user), HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/follower")
	public ResponseEntity<List<Follow>> getFollower(HttpServletRequest request, String searchValue){
		User user = (User) request.getAttribute("userinfo");
		if(searchValue == null) {
			user.setSearchValue("");			
		} else {
			user.setSearchValue(searchValue);
		}
		
		try {
			return new ResponseEntity<>(service.selectFollowerList(user), HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/f4f")
	public ResponseEntity<List<Follow>> getF4F(HttpServletRequest request, String searchValue){
		User user = (User) request.getAttribute("userinfo");
		
		if(searchValue == null) {
			user.setSearchValue("");			
		} else {
			user.setSearchValue(searchValue);
		}
		try {
			return new ResponseEntity<>(service.selectF4FList(user), HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/follow/{userNo}")
	public ResponseEntity<User> insertFollow(HttpServletRequest request, @PathVariable("userNo") String userNo){
		
		try {
			
			if(service.insertFollow(((User)request.getAttribute("userinfo")).getUserNo(), Integer.parseInt(userNo))) {
				return new ResponseEntity<>(HttpStatus.OK);				
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	@DeleteMapping("/follow/{userNo}")
	public ResponseEntity<User> deleteFollow(HttpServletRequest request, @PathVariable("userNo") int userNo){
		try {			
			if(service.deleteFollow(((User)request.getAttribute("userinfo")).getUserNo(), userNo)) {
				return new ResponseEntity<>(HttpStatus.OK);	
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	
	
	/**
	 * MESSAGE
	 */
	@GetMapping("/sendmsg/{page}")
	public ResponseEntity<List<Message>> selectSendMessageList(HttpServletRequest request, String searchValue, @PathVariable int page){
		Message message = new Message();
		if(searchValue == null) {
			message.setSearchValue("");			
		} else {
			message.setSearchValue(searchValue);
		}
		message.setPage((page-1)*9);
		System.out.println(searchValue);
		try {
			message.setMessageFrom(((User)request.getAttribute("userinfo")).getUserNo());
			
			return new ResponseEntity<List<Message>>(service.selectSendMessageList(message), HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);		
	}
	
	@GetMapping("/sendmsgcount")
	public ResponseEntity<Integer> selectSendMessageCount(HttpServletRequest request, String searchValue){
		Message message = new Message();
		if(searchValue == null) {
			message.setSearchValue("");			
		} else {
			message.setSearchValue(searchValue);
		}
		System.out.println(searchValue);
		try {
			message.setMessageFrom(((User)request.getAttribute("userinfo")).getUserNo());
			return new ResponseEntity<Integer>(service.selectSendMessageCount(message), HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);		
	}
	
	@GetMapping("/rcvmsgcount")
	public ResponseEntity<Integer> selectReceiveMessageCount(HttpServletRequest request, String searchValue){
		Message message = new Message();
		if(searchValue == null) {
			message.setSearchValue("");			
		} else {
			message.setSearchValue(searchValue);
		}
		System.out.println(searchValue);
		try {
			message.setMessageTo(((User)request.getAttribute("userinfo")).getUserNo());
			return new ResponseEntity<Integer>(service.selectReceiveMessageCount(message), HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);				
	}
	
	@GetMapping("/rcvmsg/{page}")
	public ResponseEntity<List<Message>> selectReceiveMessageList(HttpServletRequest request, String searchValue, @PathVariable int page){
		Message message = new Message();
		if(searchValue == null) {
			message.setSearchValue("");			
		} else {
			message.setSearchValue(searchValue);
		}
		message.setPage((page-1)*9);
		System.out.println(searchValue);
		try {
			message.setMessageTo(((User)request.getAttribute("userinfo")).getUserNo());
			return new ResponseEntity<List<Message>>(service.selectReceiveMessageList(message), HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);				
	}

	
	
	@PostMapping("/sendmsg")
	public ResponseEntity<Message> insertMessage(HttpServletRequest request, @RequestBody Message message){
		message.setMessageFrom(((User) request.getAttribute("userinfo")).getUserNo());
		try {
			if(service.insertMessage(message))
				return new ResponseEntity<Message>(HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<Message>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/readmsg/{msgNo}")
	public ResponseEntity<Message> readMessage(HttpServletRequest request, @PathVariable int msgNo){
		try {
			if(service.readMessage(msgNo))
				return new ResponseEntity<Message>(HttpStatus.OK);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Message>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/sendmsg/{msgNo}")
	public ResponseEntity<Message> sendDelete(HttpServletRequest request, @PathVariable int msgNo){
		try {
			if(service.sendDelete(msgNo, ((User)request.getAttribute("userinfo")).getUserNo()))
				return new ResponseEntity<Message>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Message>(HttpStatus.BAD_REQUEST);
	}
	@DeleteMapping("/rcvmsg/{msgNo}")
	public ResponseEntity<Message> receiveDelete(HttpServletRequest request, @PathVariable int msgNo){
		try {
			if(service.receiveDelete(msgNo, ((User)request.getAttribute("userinfo")).getUserNo()))
				return new ResponseEntity<Message>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<Message>(HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * block
	 */
	
	@GetMapping("/block")
	public ResponseEntity<List<Block>> selectBlockList(HttpServletRequest request, String searchValue){
		User user = (User) request.getAttribute("userinfo");
		if(searchValue == null) {
			user.setSearchValue("");			
		} else {
			user.setSearchValue(searchValue);
		}
		
		try {
			return new ResponseEntity<>(service.selectBlockList(user), HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return new ResponseEntity<List<Block>>(HttpStatus.BAD_REQUEST);
	}
	@PostMapping("/block/{blocking}")
	public ResponseEntity<List<Block>> insertBlock(HttpServletRequest request, @PathVariable int blocking){
		try {
			if(service.insertBlock(((User)request.getAttribute("userinfo")).getUserNo(), blocking))
				return new ResponseEntity<List<Block>>(HttpStatus.OK);						
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<List<Block>>(HttpStatus.BAD_REQUEST);
	}
	@DeleteMapping("/block/{blocking}")
	public ResponseEntity<List<Block>> deleteBlock(HttpServletRequest request, @PathVariable int blocking){		
		try {
			if(service.deleteBlock(((User)request.getAttribute("userinfo")).getUserNo(), blocking))
				return new ResponseEntity<List<Block>>(HttpStatus.OK);						
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<List<Block>>(HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/upload")
    public ResponseEntity<User> updateProfile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {

		User userinfo = (User) request.getAttribute("userinfo");
		int userNo = userinfo.getUserNo();

		System.out.println("전송자 : " + userNo);
		System.out.println("파일 이름(원본) : " + file.getOriginalFilename());
		System.out.println("파일 형식(원본) : " + file.getContentType());
		System.out.println("파일 크기 : " + file.getSize());

		String[] tmp = file.getContentType().split("/");
		System.out.println(Arrays.toString(tmp));
		String filename = Integer.toString(userNo) + "_profile." + tmp[tmp.length-1].toUpperCase();
		System.out.println("파일 이름(저장) : " + filename);
		String path = "./src/main/resources/static/resource/user_img/";
		String dbdata = "resource/user_img/" + filename;
		System.out.println(dbdata);
 
        try (
                // 맥일 경우
                // FileOutputStream fos = new FileOutputStream("/tmp/" +file.getOriginalFilename());
                // 윈도우일 경우
                // FileOutputStream fos = new FileOutputStream("./src/main/resources/static/resource/user_img/" + file.getOriginalFilename());
//        		FileOutputStream fos = new FileOutputStream("./src/main/resources/static/resource/user_img/" + filename);
        		FileOutputStream fos = new FileOutputStream("./classes/static/resource/user_img/" + filename);
                // 파일 저장할 경로 + 파일명을 파라미터로 넣고 fileOutputStream 객체 생성하고
                InputStream is = file.getInputStream();) {
                // file로 부터 inputStream을 가져온다.
            System.out.println("1");
            int readCount = 0;
            byte[] buffer = new byte[1024];
            // 파일을 읽을 크기 만큼의 buffer를 생성하고
            // ( 보통 1024, 2048, 4096, 8192 와 같이 배수 형식으로 버퍼의 크기를 잡는 것이 일반적이다.)
            
            while ((readCount = is.read(buffer)) != -1) {
                //  파일에서 가져온 fileInputStream을 설정한 크기 (1024byte) 만큼 읽고
                
            	System.out.println("2");
                fos.write(buffer, 0, readCount);
                // 위에서 생성한 fileOutputStream 객체에 출력하기를 반복한다
            }
            
            service.updateProfile(userNo,dbdata);
            

    		User result = service.myInfo(userinfo);
    		System.out.println(userinfo);
    		
    		if(result != null) {
    			return new ResponseEntity<User>(result, HttpStatus.OK);			
    		}else {
    			return new ResponseEntity<User>(result, HttpStatus.UNAUTHORIZED);			
    		}
            
        } catch (Exception ex) {
            throw new RuntimeException("file Save Error");
        }
 
    }
	
	@GetMapping("/mynoti")
	public ResponseEntity<List<Noti>> getMyNoti(HttpServletRequest request){
		
		Noti noti = new Noti();
		
		noti.setReceiver(((User)request.getAttribute("userinfo")).getUserNo());
		
		return new ResponseEntity<>(service.selectMyNoti(noti),HttpStatus.OK);
	}
	
	@DeleteMapping("/mynoti/{no}")
	public ResponseEntity<Noti> deleteMyNoti(HttpServletRequest request, @PathVariable int no){
		Noti noti = new Noti();
		
		noti.setReceiver(((User)request.getAttribute("userinfo")).getUserNo());
		noti.setNotiNo(no);
		if(service.deleteNoti(noti)) {
			return new ResponseEntity<Noti>(HttpStatus.OK);			
		}
		return new ResponseEntity<Noti>(HttpStatus.BAD_REQUEST);
	}
	
}