package com.my.gn.manage.main.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import com.my.gn.manage.main.service.MainService;
import com.my.gn.util.resolver.JsonView;

/**
 * @Class MainController.java
 *	@Description MainController
 * <pre>
 *
 *      수정일                  수정자                   수정내용
 * --------------  -------     ---------------------
 *  2019. 8. 8.          hgb             최초작성
 *
 * </pre>
 * @author hgb
 * @version 0.0  
 * @see
 *
 */
@Controller
@RequestMapping("/gn/")
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired(required=true)
	MainService mainService;
		
	
	@RequestMapping("main")
	public String mainView(Locale locale, Model model) {
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG
																					, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		
		return "main/main";
	}
	
	@RequestMapping("getData")
	public View callbackUrl(HttpServletRequest req, ModelMap model) {
		Enumeration params = req.getParameterNames();
		System.out.println("----------------------------");
		while (params.hasMoreElements()){
			String name = (String)params.nextElement();
			System.out.println(name + " : " +req.getParameter(name));
		}
		System.out.println("----------------------------");
	      
		model.addAttribute("test", mainService.test());
		return new JsonView();
	}
	
}
