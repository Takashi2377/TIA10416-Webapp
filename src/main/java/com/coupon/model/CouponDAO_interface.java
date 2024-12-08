package com.coupon.model;

import java.util.*;

public interface CouponDAO_interface {
	public void insert(CouponVO couponVO);
    public void update(CouponVO couponVO);
    public void delete(Integer cop_id);
    public CouponVO findByPrimaryKey(Integer cop_id);
    public List<CouponVO> getAll();
}
