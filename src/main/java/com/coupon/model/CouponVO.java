package com.coupon.model;

import java.sql.Date;

public class CouponVO {
	private Integer cop_id;
	private String cop_code;
	private Date crt_date;
	private Date end_date;
	private Integer discount;
	
	public Integer getCop_id() {
		return cop_id;
	}
	public void setCop_id(Integer cop_id) {
		this.cop_id = cop_id;
	}
	public String getCop_code() {
		return cop_code;
	}
	public void setCop_code(String cop_code) {
		this.cop_code = cop_code;
	}
	public Date getCrt_date() {
		return crt_date;
	}
	public void setCrt_date(Date crt_date) {
		this.crt_date = crt_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public Integer getDiscount() {
		return discount;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
}
