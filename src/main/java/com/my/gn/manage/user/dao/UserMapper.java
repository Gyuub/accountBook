package com.my.gn.manage.user.dao;

import com.my.gn.manage.user.vo.UserVO;

public interface UserMapper {

	public UserVO selectUser(UserVO vo) throws Exception;
}
