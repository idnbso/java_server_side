package com.github.idnbso.javaee.hibernate.coupons;

public class HibernateCouponsDemo
{

    public static void main(String[] args) throws CouponsPlatformException
    {
        ICouponsDAO couponDB = new MySQLCouponsDAO();
        Coupon iPadCoupon = new Coupon(1, "iPad", 100);
        Coupon iPodCoupon = new Coupon(2, "iPod", 50);
//        couponDB.addCoupon(iPadCoupon);
//        couponDB.addCoupon(iPodCoupon);
        Coupon[] coupons = couponDB.getCoupons();
        printCoupons(coupons);

//        couponDB.updateCoupon(10, "iPad5", 35);
//        coupons = couponDB.getCoupons();
//        printCoupons(coupons);
    }

    public static void printCoupons(Coupon[] coupons)
    {
        for (Coupon coupon : coupons)
        {
            System.out.println(coupon);
        }
    }
}
