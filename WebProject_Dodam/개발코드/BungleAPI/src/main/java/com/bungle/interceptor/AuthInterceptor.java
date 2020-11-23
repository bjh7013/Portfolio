package com.bungle.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bungle.dto.User;
import com.bungle.util.JWTUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	JWTUtil jwtUtil;

	@Override // 전처리
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		System.out.println("AuthInterceptor 진입===============================");
		System.out.println(request);
		if (!request.getMethod().equals("OPTIONS")) {//preflight요청이 아닌 경우만 토큰 겁증
			System.out.println(request.getHeader("Authorization"));
			Jws<Claims> jws;
			try {
				String jwsString = request.getHeader("Authorization").substring("Bearer ".length());
				System.out.println(jwsString);
				
				jws = Jwts.parserBuilder() // (1)
						.setSigningKey(jwtUtil.getKey()) // (2)
						.build() // (3)
						.parseClaimsJws(jwsString); // (4)

				// we can safely trust the JWT
				System.out.println("===========token vaildated===============");
				System.out.println(jws);
				
				Claims claims = jws.getBody();
				
				int userNo = (Integer) claims.get("userNo");
				String userId = (String) claims.get("userId");
				String userType = (String) claims.get("userType");
//				boolean isAdmin = (boolean) claims.get("isAdmin");

				User user = new User();
				user.setUserNo(userNo);
				user.setUserId(userId);
				user.setUserType(userType);
//				user.setAdmin(isAdmin);;

				request.setAttribute("userinfo", user);
			} catch (JwtException ex) { // (5)
				// we *cannot* use the JWT as intended by its creator
				System.out.println("유효하지 않은 토큰...");
//				response.sendRedirect("http://127.0.0.1:9999/happyhouse/logout");
				return false;
			} catch(NullPointerException ne) {
				System.out.println("토큰이 없습니다...");
				
				if(request.getServletPath().equals("/article/articles")) {
					return true;
				}
				if(request.getServletPath().contains("/book/book/")) {
					return true;
				}
				
				response.getWriter().print("request must have a token");
				return false;
			}
		}

		return super.preHandle(request, response, handler);
	}

	@Override // 후처리
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		System.out.println("###후처리###");
	}
}
