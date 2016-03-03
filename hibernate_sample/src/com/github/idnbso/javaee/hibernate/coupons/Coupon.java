package com.github.idnbso.javaee.hibernate.coupons;

public class Coupon
{
    private double discount;
    private String productName;
    private int id;

    public Coupon()
    {
        super();
    }

    public Coupon(int id, String productName, double price)
    {
        this.id = id;
        this.discount = price;
        this.productName = productName;
    }

    public double getDiscount()
    {
        return discount;
    }

    public void setDiscount(double discount)
    {
        this.discount = discount;
    }

    public String getProductName()
    {
        return productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "[" + id + ", " + productName + ", " + discount + "]";
    }
}