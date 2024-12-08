package com.coupon.model;

import java.sql.Date;
import java.util.*;

public class CouponService {

	private CouponDAO_interface dao;

	public CouponService() {
		dao = new CouponDAO();
	}

	public CouponVO addCoupon(String cop_code, Date end_date, Integer discount) {

		CouponVO couponVO = new CouponVO();

		couponVO.setCop_code(cop_code);
		couponVO.setEnd_date(end_date);
		couponVO.setDiscount(discount);
		dao.insert(couponVO);

		return couponVO;
	}

	public CouponVO updateCoupon(String cop_code, java.sql.Date end_date, Integer discount, Integer cop_id) {

		CouponVO couponVO = new CouponVO();

		couponVO.setCop_code(cop_code);
		couponVO.setEnd_date(end_date);
		couponVO.setDiscount(discount);
		couponVO.setCop_id(cop_id);
		dao.update(couponVO);

		return dao.findByPrimaryKey(cop_id);
	}

	public void deleteCoupon(Integer cop_id) {
		dao.delete(cop_id);
	}

	public CouponVO getOneCoupon(Integer cop_id) {
		return dao.findByPrimaryKey(cop_id);
	}

	public List<CouponVO> getAll() {
		return dao.getAll();
	}
}
