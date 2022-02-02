package com.my.gn.manage.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import com.my.gn.manage.api.service.ApiService;
import com.my.gn.manage.api.vo.ApiVO;
import com.my.gn.util.resolver.JsonView;

/**
 * 임시 API
 * @Class ApiController.java
 *	@Description ApiController
 * <pre>
 *
 *      수정일                  수정자                   수정내용
 * --------------  -------     ---------------------
 *  2020. 3. 6.          hgb             최초작성
 *
 * </pre>
 * @author hgb
 * @version 0.0  
 * @see
 *
 */
@Controller
@RequestMapping("/api/v1/")
public class ApiController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);
	
	@Autowired
	ApiService ApiSerivice;

	/**
	 * <pre>
	 * 1. 개요 : 임시 내역 조회
	 * 2. 처리내용 :
	 * </pre>
	 *	@since 2020. 3. 10.
	 * @return
	 */
	@RequestMapping("selectList")
	public View selectList(@RequestBody ApiVO vo, HttpServletRequest request, ModelMap model){
		try {
			model.addAttribute("result", "Y");
			model.addAttribute("resultList", ApiSerivice.selectList(vo));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.info(e.getMessage());
		}
		return new JsonView();
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 임시 내역 쓰기
	 * 2. 처리내용 :
	 * </pre>
	 *	@since 2020. 3. 6.
	 * @return
	 */
	@RequestMapping("write")
	public View write(@RequestBody ApiVO vo, HttpServletRequest request, ModelMap model){
		try {
			ApiSerivice.write(vo);
			model.addAttribute("result", "Y");
			model.addAttribute("msg", "저장 되었습니다.");
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			model.addAttribute("result", "N");
			model.addAttribute("msg", "잠시후 다시 시도해");
		}
		return new JsonView();
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 임시 내역 쓰기
	 * 2. 처리내용 :
	 * </pre>
	 *	@since 2020. 3. 6.
	 * @return
	 */
	@RequestMapping("update")
	public View update(@RequestBody ApiVO vo, HttpServletRequest request, ModelMap model){
		try {
			ApiSerivice.updateSpend(vo);
			model.addAttribute("result", "Y");
			model.addAttribute("msg", "저장 되었습니다.");
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			model.addAttribute("result", "N");
			model.addAttribute("msg", "잠시후 다시 시도해");
		}
		return new JsonView();
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 임시 내역 쓰기
	 * 2. 처리내용 :
	 * </pre>
	 *	@since 2020. 3. 6.
	 * @return
	 */
	@RequestMapping("delete")
	public View delete(@RequestBody ApiVO vo, HttpServletRequest request, ModelMap model){
		try {
			ApiSerivice.deleteSpend(vo);
			model.addAttribute("result", "Y");
			model.addAttribute("msg", "삭제 되었습니다.");
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			model.addAttribute("result", "N");
			model.addAttribute("msg", "잠시후 다시 시도해");
		}
		return new JsonView();
	}

}
