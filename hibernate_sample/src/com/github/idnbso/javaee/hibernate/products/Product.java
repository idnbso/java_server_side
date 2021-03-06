package com.github.idnbso.javaee.hibernate.products;

public class Product
{
    private double price;
    private String name;
    private int id;

    public Product()
    {
        super();
    }

    public Product(int id, double price, String name)
    {
        this.id = id;
        this.price = price;
        this.name = name;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
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
        return "[" + id + ", " + name + ", " + "price" + "]";
    }
}