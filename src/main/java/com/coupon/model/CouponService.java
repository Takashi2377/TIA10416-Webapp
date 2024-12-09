package com.coupon.model;

import java.sql.Date;
import java.util.*;

public class CouponService {

	private CouponDAO_interface dao;

	public CouponService() {
		dao = new CouponDAO();
	}

	public CouponVO addCoupon(String copcode, Date enddate, Integer discount) {

		CouponVO couponVO = new CouponVO();

		couponVO.setCopcode(copcode);
		couponVO.setEnddate(enddate);
		couponVO.setDiscount(discount);
		dao.insert(couponVO);

		return couponVO;
	}

	public CouponVO updateCoupon(String copcode, java.sql.Date enddate, Integer discount, Integer copid) {

		CouponVO couponVO = new CouponVO();

		couponVO.setCopcode(copcode);
		couponVO.setEnddate(enddate);
		couponVO.setDiscount(discount);
		couponVO.setCopid(copid);
		dao.update(couponVO);

		return dao.findByPrimaryKey(copid);
	}

	public void deleteCoupon(Integer copid) {
		dao.delete(copid);
	}

	public CouponVO getOneCoupon(Integer copid) {
		return dao.findByPrimaryKey(copid);
	}

	public List<CouponVO> getAll() {
		return dao.getAll();
	}
}
