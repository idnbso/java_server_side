package com.github.idnbso.javaee.hibernate.coupons;

public class MySQLCouponsDAOException extends Exception
{

    /**
     * default UID
     */
    private static final long serialVersionUID = 1L;
    
    public MySQLCouponsDAOException(String message, Throwable cause)
    {
        super(message, cause);
    }
    
    public MySQLCouponsDAOException(String message)
    {
        super(message);
    }
}
