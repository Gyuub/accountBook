package com.my.gn.manage.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.gn.manage.user.dao.UserMapper;
import com.my.gn.manage.user.service.UserService;
import com.my.gn.manage.user.vo.UserVO;

/**
 * @Class UserServiceImpl.java
 *	@Description UserServiceImpl
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
@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	UserMapper userMapper;
	
	@Override
	public UserVO userLogin(UserVO vo) throws Exception {
		vo = userMapper.selectUser(vo);
		return vo;
	}


}
