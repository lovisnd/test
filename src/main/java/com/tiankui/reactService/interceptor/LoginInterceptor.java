package com.tiankui.reactService.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.tiankui.reactService.entity.User;
import com.tiankui.reactService.util.LoginUserCache;
import com.tiankui.reactService.util.ObjectUtil;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	private static Logger logger = Logger.getLogger(LoginInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		String url = request.getServletPath();
		if(!"/api/token".equals(url)){
			String token = request.getParameter("token");
			if(ObjectUtil.isNull(token)){
				logger.error("前端未登录！请求被拦截。");
				return false;
			}
			User user = LoginUserCache.get(token);
			if(user == null){
				response.setStatus(403);
				return false;
			}
		}
		
		return true;
	}
}