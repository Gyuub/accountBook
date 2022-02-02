package com.my.gn.manage.api.dao;

import java.util.List;

import com.my.gn.manage.api.vo.ApiVO;

/**
 * @Class ApiMapper.java
 *	@Description ApiMapper
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
public interface ApiMapper {

	public List<ApiVO> selectList(ApiVO vo) throws Exception;
	
	public int write(ApiVO vo) throws Exception;
	
	public int updateSpend(ApiVO vo) throws Exception;
	
	public int deleteSpend(ApiVO vo) throws Exception;
}
