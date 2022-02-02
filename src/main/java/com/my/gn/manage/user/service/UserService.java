package com.my.gn.manage.user.service;

import com.my.gn.manage.user.vo.UserVO;

/**
 * @Class UserService.java
 *	@Description UserService
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
public interface UserService {

	
	public UserVO userLogin(UserVO vo) throws Exception;
	
}
