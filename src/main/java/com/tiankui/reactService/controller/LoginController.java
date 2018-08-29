package com.tiankui.reactService.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tiankui.reactService.entity.Result;
import com.tiankui.reactService.entity.TMenu;
import com.tiankui.reactService.entity.User;
import com.tiankui.reactService.service.ITMenuService;
import com.tiankui.reactService.service.IUserService;
import com.tiankui.reactService.util.IPUtil;
import com.tiankui.reactService.util.LoginUserCache;

@RestController
@RequestMapping(value = "/api")
public class LoginController {

	@Autowired
	private IUserService userService;
	@Autowired
	private ITMenuService menuService;

	@RequestMapping(value = "/token", method = RequestMethod.POST, consumes = "application/json")
	public Result<Object> login(@RequestBody Map<String,Object> userparam, HttpServletRequest request, HttpServletResponse response) {
		String uid = userparam.get("uid").toString();
		String pwd = userparam.get("pwd").toString() ;
		User user = userService.userLogin(uid, pwd, IPUtil.getIpAdrress(request));
		Result<Object> result = new Result<Object>();
		if (user != null) {
			if (user.getUid().equals("NotEnabled")) {
				result.setData(null);
				result.setStatus(0);
				result.setMessage("用户已被锁定，请联系管理员解锁！");
				return result;
			}
			List<TMenu> menus = menuService.getRoleRight(user.getRoles());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user", user);
			map.put("menus", menus);
			map.put("token", user.getToken());
			result.setData(map);
			result.setStatus(1);
			result.setMessage("登陆成功！");
			LoginUserCache.set(user.getToken(), user);
		} else {
			result.setData(null);
			result.setStatus(0);
			result.setMessage("用户名或密码错误！");
		}
		return result;
	}

	@RequestMapping(value = "/token/user")
	public Result<Object> loginStrus(@RequestParam String token) {
		User user = userService.getTokenUser(token);
		Result<Object> result = new Result<Object>();
		if (user != null) {
			List<TMenu> menus = menuService.getRoleRight(user.getRoles());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user", user);
			map.put("menus", menus);
			map.put("token", user.getToken());
			result.setData(map);
			result.setStatus(1);
			LoginUserCache.set(user.getToken(), user);
		} else {
			result.setMessage("请先登录！");
		}
		return result;
	}

	@RequestMapping(value = "/token/clear")
	public Result<Integer> loginout(@RequestParam String token) {
		int resultNum = userService.clearToken(token);
		LoginUserCache.remove(token);
		Result<Integer> result = new Result<Integer>();
		result.setData(resultNum);
		return result;
	}
	
}
