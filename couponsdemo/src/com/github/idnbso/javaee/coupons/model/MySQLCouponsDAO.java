package com.github.idnbso.javaee.coupons.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLCouponsDAO implements ICouponsDAO
{
    protected String driver = "com.mysql.jdbc.Driver";
    protected String protocol = "jdbc:mysql://localhost:3306/coupons";

    private Connection connection = null;
    private Statement statement = null;

    public MySQLCouponsDAO() throws CouponsPlatformException
    {
        super();
        try
        {
            Class.forName(driver);

            connection = DriverManager.getConnection(protocol, "idan", "loloftw");
            statement = connection.createStatement();
            String tableCreationStatement = "CREATE TABLE IF NOT EXISTS coupons(id INT, productName TEXT, discount DOUBLE(9, 2));";
            executeStatement(tableCreationStatement);
        }
        catch (ClassNotFoundException e)
        {
            throw new CouponsPlatformException("Error with the JDBC MySQL Driver.", e);
        }
        catch (MySQLCouponsDAOException e)
        {
            throw new CouponsPlatformException("Error creating the table of coupons.", e);
        }
        catch (SQLException e)
        {
            throw new CouponsPlatformException("Error connecting to the MySQL server.", e);
        }
    }

    @Override
    public void addCoupon(Coupon coupon) throws CouponsPlatformException
    {
        int couponId = coupon.getId();
        String couponProductName = coupon.getProductName();
        double couponDiscount = coupon.getDiscount();
        try
        {
            connection = DriverManager.getConnection(protocol, "idan", "loloftw");
            statement = connection.createStatement();
            MessageFormat messageFormat = new MessageFormat(
                    "INSERT INTO coupons VALUES ({0}, ''{1}'', {2});");
            Object[] args = { couponId, couponProductName, couponDiscount };
            String couponCreationStatement = messageFormat.format(args);
            executeStatement(couponCreationStatement);
        }
        catch (MySQLCouponsDAOException e)
        {
            throw new CouponsPlatformException(e.getMessage(), e);
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCoupon(int id) throws CouponsPlatformException
    {
        try
        {
            String couponCreationStatement;
            couponCreationStatement = String.format("DELETE FROM coupons WHERE id=%d;", id);
            executeStatement(couponCreationStatement);
        }
        catch (MySQLCouponsDAOException e)
        {
            throw new CouponsPlatformException(e.getMessage(), e);
        }
    }

    @Override
    public Coupon[] getCoupons() throws CouponsPlatformException
    {
        Coupon[] coupons = null;
        ArrayList<Coupon> couponsList = new ArrayList<Coupon>();
        String couponsStatementQuery = null;
        ResultSet resultSet = null;
        try
        {
            couponsStatementQuery = "SELECT * FROM coupons;";
            resultSet = executeStatementQuery(couponsStatementQuery);
            while (resultSet.next())
            {
                int currentCouponId = resultSet.getInt("id");
                String currentCouponProductName = resultSet.getString("productName");
                double currentCouponDiscount = resultSet.getDouble("discount");
                Coupon currentCoupon = new Coupon(currentCouponId, currentCouponProductName,
                        currentCouponDiscount);
                couponsList.add(currentCoupon);
            }

            int totalCoupons = couponsList.size();
            coupons = new Coupon[totalCoupons];

            int couponIndex = 0;
            for (Coupon coupon : couponsList)
            {
                coupons[couponIndex++] = coupon;
            }
        }
        catch (MySQLCouponsDAOException e)
        {
            throw new CouponsPlatformException(e.getMessage(), e);
        }
        catch (SQLException e)
        {
            throw new CouponsPlatformException(
                    "There was a problem retrieving data from the MySQL connection.", e);
        }

        return coupons;
    }

    @Override
    public void updateCoupon(int id, String productName, double discount)
            throws CouponsPlatformException
    {
        try
        {
            connection = DriverManager.getConnection(protocol, "idan", "loloftw");
            statement = connection.createStatement();
            MessageFormat messageFormat = new MessageFormat(
                    "UPDATE coupons SET productName=''{1}'', discount={2} WHERE id={0};");
            Object[] args = { id, productName, discount };
            String couponCreationStatement = messageFormat.format(args);
            executeStatement(couponCreationStatement);
        }
        catch (MySQLCouponsDAOException e)
        {
            throw new CouponsPlatformException(e.getMessage(), e);
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private ResultSet executeStatementQuery(String statementQuery) throws MySQLCouponsDAOException
    {
        ResultSet result = null;
        try
        {
            if (connection != null)
            {
                result = statement.executeQuery(statementQuery);
            }
        }
        catch (SQLException e)
        {
            throw new MySQLCouponsDAOException("There is no open connection to the MySQL server.",
                    e);
        }

        return result;
    }

    private void executeStatement(String statementQuery) throws MySQLCouponsDAOException
    {
        try
        {
            if (connection != null)
            {
                statement.execute(statementQuery);
            }
        }
        catch (SQLException e)
        {
            throw new MySQLCouponsDAOException("There is no open connection to the MySQL server.",
                    e);
        }
    }
}
