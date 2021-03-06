package com.github.idnbso.javaee.coupons.model;

public class CouponsDemo
{

    public static void main(String[] args) throws CouponsPlatformException
    {
        try
        {
            ICouponsDAO couponDB = new MySQLCouponsDAO();
            Coupon iPadCoupon = new Coupon(1, "iPad", 100);
            Coupon iPodCoupon = new Coupon(2, "iPod", 50);
            couponDB.addCoupon(iPadCoupon);
            couponDB.addCoupon(iPodCoupon);
            // couponDB.updateCoupon(1, "iPad3", 25);
            Coupon[] coupons = couponDB.getCoupons();
            for (Coupon coupon : coupons)
            {
                System.out.println(coupon);
            }
        }
        catch (CouponsPlatformException e)
        {
            e.printStackTrace();
        }
    }

}
