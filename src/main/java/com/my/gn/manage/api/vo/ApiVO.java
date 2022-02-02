package com.my.gn.manage.api.vo;

/**
 * @Class ApiVO.java
 *	@Description ApiVO
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
public class ApiVO {

	private String incom_seq; //seq
	private String incom_ymd; // 등록일
	private String incom_amount; // 가격
	private String incom_title; // 제목 
	private String incom_content; // 내용
	private String incom_cd; // 상세코드
	private String cre_user_id; // 작성자
	private String cre_date; // 작설일
	private String mod_user_id; // 수정자
	private String mod_date; //수정일
	private String del_yn; // 삭제여부
	private String spend_cd; // 수입,지출코드
	private String spend_nm; // 수입,지출코드
	
	
	
	
	
	
	
	
	public String getSpend_nm() {
		return spend_nm;
	}
	public void setSpend_nm(String spend_nm) {
		this.spend_nm = spend_nm;
	}
	public String getIncom_seq() {
		return incom_seq;
	}
	public void setIncom_seq(String incom_seq) {
		this.incom_seq = incom_seq;
	}
	public String getIncom_ymd() {
		return incom_ymd;
	}
	public void setIncom_ymd(String incom_ymd) {
		this.incom_ymd = incom_ymd;
	}
	public String getIncom_amount() {
		return incom_amount;
	}
	public void setIncom_amount(String incom_amount) {
		this.incom_amount = incom_amount;
	}
	public String getIncom_title() {
		return incom_title;
	}
	public void setIncom_title(String incom_title) {
		this.incom_title = incom_title;
	}
	public String getIncom_content() {
		return incom_content;
	}
	public void setIncom_content(String incom_content) {
		this.incom_content = incom_content;
	}
	public String getIncom_cd() {
		return incom_cd;
	}
	public void setIncom_cd(String incom_cd) {
		this.incom_cd = incom_cd;
	}
	public String getCre_user_id() {
		return cre_user_id;
	}
	public void setCre_user_id(String cre_user_id) {
		this.cre_user_id = cre_user_id;
	}
	public String getCre_date() {
		return cre_date;
	}
	public void setCre_date(String cre_date) {
		this.cre_date = cre_date;
	}
	public String getMod_user_id() {
		return mod_user_id;
	}
	public void setMod_user_id(String mod_user_id) {
		this.mod_user_id = mod_user_id;
	}
	public String getMod_date() {
		return mod_date;
	}
	public void setMod_date(String mod_date) {
		this.mod_date = mod_date;
	}
	public String getDel_yn() {
		return del_yn;
	}
	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}
	public String getSpend_cd() {
		return spend_cd;
	}
	public void setSpend_cd(String spend_cd) {
		this.spend_cd = spend_cd;
	}
	
}

