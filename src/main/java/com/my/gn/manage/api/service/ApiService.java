package com.my.gn.manage.api.service;

import java.util.List;

import com.my.gn.manage.api.vo.ApiVO;

/**
 * @Class ApiService.java
 *	@Description ApiService
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
public interface ApiService {

	public List<ApiVO> selectList(ApiVO vo) throws Exception;
	public void write(ApiVO vo) throws Exception;
	public void updateSpend(ApiVO vo) throws Exception;
	public void deleteSpend(ApiVO vo) throws Exception;
	
}
