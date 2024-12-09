package com.coupon.model;

import java.sql.Date;

public class CouponVO {
	private Integer copid;
	private String copcode;
	private Date crtdate;
	private Date enddate;
	private Integer discount;
	
	public Integer getCopid() {
		return copid;
	}
	public void setCopid(Integer copid) {
		this.copid = copid;
	}
	public String getCopcode() {
		return copcode;
	}
	public void setCopcode(String copcode) {
		this.copcode = copcode;
	}
	public Date getCrtdate() {
		return crtdate;
	}
	public void setCrtdate(Date crtdate) {
		this.crtdate = crtdate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public Integer getDiscount() {
		return discount;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
}
