package com.github.idnbso.javaee.hibernate.coupons;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class MySQLCouponsDAO implements ICouponsDAO
{
    public SessionFactory factory;

    public MySQLCouponsDAO() throws CouponsPlatformException
    {
        // creating factory for getting sessions
        factory = new AnnotationConfiguration().configure().buildSessionFactory();
    }

    @Override
    public void addCoupon(Coupon coupon) throws CouponsPlatformException
    {
        Session session = null;

        try
        {
            // creating a new session for adding coupons
            session = factory.openSession();
            session.beginTransaction();
            session.save(coupon);
            session.getTransaction().commit();
        }
        catch (HibernateException e)
        {
            session.getTransaction().rollback();
            throw new CouponsPlatformException(e.getMessage(), e);
        }
        finally
        {
            if (session != null)
            {
                session.close();
            }
        }
    }

    @Override
    public void deleteCoupon(int id) throws CouponsPlatformException
    {
        Session session = null;

        try
        {
            // creating a new session for deleting coupons
            session = factory.openSession();
            session.beginTransaction();
            Coupon coupon = (Coupon) session.get(Coupon.class, id);
            session.delete(coupon);
            session.getTransaction().commit();
        }
        catch (HibernateException e)
        {
            session.getTransaction().rollback();
            throw new CouponsPlatformException(e.getMessage(), e);
        }
        finally
        {
            if (session != null)
            {
                session.close();
            }
        }
    }

    @Override
    public Coupon[] getCoupons() throws CouponsPlatformException
    {
        Session session = null;
        Coupon[] coupons = null;
        try
        {
            // creating a new session for getting all coupons
            session = factory.openSession();
            session.beginTransaction();
            List<?> couponsList = session.createQuery("from Coupon").list();
            session.getTransaction().commit();

            int totalCoupons = couponsList.size();
            coupons = new Coupon[totalCoupons];

            int couponIndex = 0;
            for (Object coupon : couponsList)
            {
                coupons[couponIndex++] = (Coupon) coupon;
            }
        }
        catch (HibernateException e)
        {
            session.getTransaction().rollback();
            throw new CouponsPlatformException(e.getMessage(), e);
        }
        finally
        {
            if (session != null)
            {
                session.close();
            }
        }

        return coupons;
    }

    @Override
    public void updateCoupon(int id, String productName, double discount)
            throws CouponsPlatformException
    {
        Session session = null;

        try
        {
            // creating a new session for updating coupons
            session = factory.openSession();
            session.beginTransaction();
            Coupon coupon = (Coupon) session.get(Coupon.class, id);
            coupon.setProductName(productName);
            coupon.setDiscount(discount);
            session.update(coupon);
            session.getTransaction().commit();
        }
        catch (HibernateException e)
        {
            session.getTransaction().rollback();
            throw new CouponsPlatformException(e.getMessage(), e);
        }
        finally
        {
            if (session != null)
            {
                session.close();
            }
        }
    }
}
