package com.my.gn.manage.api.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.gn.manage.api.dao.ApiMapper;
import com.my.gn.manage.api.service.ApiService;
import com.my.gn.manage.api.vo.ApiVO;
import com.my.gn.util.CustomUtil;

/**
 * @Class ApiServiceImpl.java
 *	@Description ApiServiceImpl
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
@Service("apiService")
public class ApiServiceImpl implements ApiService{
	private static final Logger LOGGER = LoggerFactory.getLogger(ApiServiceImpl.class);

	@Autowired
	ApiMapper apiMapper;
	
	/**
	 * 월별 가게부 내역 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<ApiVO> selectList(ApiVO vo) throws Exception {
		return apiMapper.selectList(vo);
	}
	
	
	/**
	 * 가계부 등록
	 * @param vo
	 * @throws Exception
	 */
	@Override
	public void write(ApiVO vo) throws Exception {
		vo.setIncom_ymd(CustomUtil.isRegx(vo.getIncom_ymd()));
		vo.setIncom_amount(CustomUtil.isRegx(vo.getIncom_amount()));
		apiMapper.write(vo);
	}


	/**
	 * 가계부 수정
	 * @param vo
	 * @throws Exception
	 */
	@Override
	public void updateSpend(ApiVO vo) throws Exception {
		vo.setIncom_ymd(CustomUtil.isRegx(vo.getIncom_ymd()));
		vo.setIncom_amount(CustomUtil.isRegx(vo.getIncom_amount()));
		
		apiMapper.updateSpend(vo);
	}


	/**
	 * 가계부 삭제
	 * @param vo
	 * @throws Exception
	 */
	@Override
	public void deleteSpend(ApiVO vo) throws Exception {
		apiMapper.deleteSpend(vo);
	}

	

}
