package com.github.idnbso.javaee.coupons.model;

public interface ICouponsDAO
{
    public void addCoupon(Coupon coupon) throws CouponsPlatformException;

    public void deleteCoupon(int id) throws CouponsPlatformException;

    public Coupon[] getCoupons() throws CouponsPlatformException;

    public void updateCoupon(int id, String productName, double discount) throws CouponsPlatformException;
}
