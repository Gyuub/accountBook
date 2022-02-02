package com.my.gn.manage.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import com.my.gn.manage.user.service.UserService;
import com.my.gn.manage.user.vo.UserVO;
import com.my.gn.util.resolver.JsonView;

/**
 * @Class UserController.java
 *	@Description UserController
 * <pre>
 *
 *      수정일                  수정자                   수정내용
 * --------------  -------     ---------------------
 *  2020. 3. 4.          hgb             최초작성
 *
 * </pre>
 * @author hgb
 * @version 0.0  
 * @see
 *
 */
@Controller
@RequestMapping("/api/user/")
public class UserController {

	@Autowired(required=true)
	UserService userService; 
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 로그인
	 * 2. 처리내용 :
	 * </pre>
	 *	@since 2020. 3. 4.
	 * @param vo
	 * @param model
	 * @return
	 */
	@RequestMapping("login")
	public View userLogin(@RequestBody UserVO vo, ModelMap model){
		try {
			model.addAttribute("user", userService.userLogin(vo));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return new JsonView();
	}
}
