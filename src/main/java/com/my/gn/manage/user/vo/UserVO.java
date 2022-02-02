package com.my.gn.manage.user.vo;

/**
 * 사용자 정보
 * @Class UserVO.java
 *	@Description UserVO
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
public class UserVO {
	
	private String user_seq;
	private String user_id;
	private String user_pw;
	private String user_nm;
	private String user_phone;
	private String cre_date;
	private String cre_user_id;
	private String mod_date;
	private String mod_user_id;
	private String del_yn;
	
	
	
	
	
	
	
	
	
	
	public String getUser_seq() {
		return user_seq;
	}
	public void setUser_seq(String user_seq) {
		this.user_seq = user_seq;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getUser_nm() {
		return user_nm;
	}
	public void setUser_nm(String user_nm) {
		this.user_nm = user_nm;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getCre_date() {
		return cre_date;
	}
	public void setCre_date(String cre_date) {
		this.cre_date = cre_date;
	}
	public String getCre_user_id() {
		return cre_user_id;
	}
	public void setCre_user_id(String cre_user_id) {
		this.cre_user_id = cre_user_id;
	}
	public String getMod_date() {
		return mod_date;
	}
	public void setMod_date(String mod_date) {
		this.mod_date = mod_date;
	}
	public String getMod_user_id() {
		return mod_user_id;
	}
	public void setMod_user_id(String mod_user_id) {
		this.mod_user_id = mod_user_id;
	}
	public String getDel_yn() {
		return del_yn;
	}
	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}
	
}
