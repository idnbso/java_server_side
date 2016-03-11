/**
 * 
 */
package com.github.idnbso.snippetodo.model;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.github.idnbso.snippetodo.model.data.*;

/**
 * The HibernateSnippeToDoDAO class implements the Data Access Object design
 * pattern, using Hibernate 3 with the MySQL database, for the use of CRUD
 * operations regarding ToDo items and registered users of the SnippeToDo
 * application.
 * 
 * @author Idan Busso
 * @author Shani Kahila
 * @see ISnippeToDoDAO
 * @see Item
 * @see User
 */
public class HibernateSnippeToDoDAO implements ISnippeToDoDAO
{
    private SessionFactory factory;

    /**
     * the instance reference is set to null if there was no instantiation
     * beforehand.
     */
    private static HibernateSnippeToDoDAO snippeToDoInstance = null;

    /**
     * Get the single instance object HibernateSnippeToDoDAO class according to
     * the implementation of the Singleton design pattern.
     *
     * @return the single instance object of the HibernateSnippeToDoDAO class.
     * @throws SnippeToDoPlatformException
     */
    public static HibernateSnippeToDoDAO getInstance() throws SnippeToDoPlatformException
    {
        if (snippeToDoInstance == null)
        {
            snippeToDoInstance = new HibernateSnippeToDoDAO();
        }
        return snippeToDoInstance;
    }

    /**
     * HibernateSnippeToDoDAO private constructor for the Singleton design
     * pattern implementation.
     * 
     * @throws SnippeToDoPlatformException
     */
    private HibernateSnippeToDoDAO() throws SnippeToDoPlatformException
    {
        try
        {
            factory = new Configuration().configure().buildSessionFactory();
        }
        catch (HibernateException ex)
        {
            throw new SnippeToDoPlatformException(
                    "Initial SessionFactory creation failed: " + ex.getMessage(), ex);
        }
    }

    /**
     * Add a new ToDo item to be added to the database's items table.
     * 
     * @param item The ToDo item to be added to the database items table
     * @see ISnippeToDoDAO#addItem(Item)
     * @throws SnippeToDoPlatformException
     */
    @Override
    public void addItem(Item item) throws SnippeToDoPlatformException
    {
        Session session = null;
        Transaction transaction = null;

        try
        {
            // creating a new session for adding items
            session = factory.openSession();
            transaction = session.beginTransaction();
            // save the new item in the database items table
            session.save(item);
            // commit the changes to the database
            transaction.commit();
        }
        catch (HibernateException e)
        {
            if (transaction != null)
            {
                session.getTransaction().rollback();
            }
            throw new SnippeToDoPlatformException(
                    "Failed to add a new item to the database: " + e.getMessage(), e);
        }
        finally
        {
            session.close();
        }
    }

    /**
     * Add a new registered user to be added to the database's users table.
     * 
     * @param user The registered user to be added to the database users table
     * @see ISnippeToDoDAO#addUser(User)
     * @throws SnippeToDoPlatformException
     */
    @Override
    public void addUser(User user) throws SnippeToDoPlatformException
    {
        Session session = null;
        Transaction transaction = null;

        try
        {
            // creating a new session for adding users
            session = factory.openSession();
            transaction = session.beginTransaction();
            // save the new item in the database users table
            session.save(user);
            // commit the changes to the database
            transaction.commit();
        }
        catch (HibernateException e)
        {
            if (transaction != null)
            {
                transaction.rollback();
            }
            throw new SnippeToDoPlatformException(
                    "Failed to add a new user to the database: " + e.getMessage(), e);
        }
        finally
        {
            session.close();
        }
    }

    /**
     * Delete a new ToDo item from the database items table.
     * 
     * @param id The id number of the item to be deleted from the database
     * @see ISnippeToDoDAO#deleteItem(Item)
     * @throws SnippeToDoPlatformException
     */
    @Override
    public void deleteItem(int id) throws SnippeToDoPlatformException
    {
        Session session = null;
        Transaction transaction = null;

        try
        {
            // creating a new session for deleting items
            session = factory.openSession();
            transaction = session.beginTransaction();
            // get the specific item from the database that match the id
            Item item = (Item) session.get(Item.class, id);
            // delete the item from the database
            session.delete(item);
            // commit the changes to the database
            transaction.commit();
        }
        catch (HibernateException e)
        {
            if (transaction != null)
            {
                transaction.rollback();
            }
            throw new SnippeToDoPlatformException(
                    "Failed to delete an item from the database: " + e.getMessage(), e);
        }
        finally
        {
            session.close();
        }
    }

    /**
     * Delete a registered user from the database users table.
     * 
     * @param id The id number of the user to be deleted from the database users
     * @see ISnippeToDoDAO#deleteUser(User)
     * @throws SnippeToDoPlatformException
     */
    @Override
    public void deleteUser(int id) throws SnippeToDoPlatformException
    {
        Session session = null;
        Transaction transaction = null;

        try
        {
            // creating a new session for deleting users
            session = factory.openSession();
            transaction = session.beginTransaction();
            // get the specific user from the database that match the id
            User user = (User) session.get(User.class, id);
            // delete the user from the database
            session.delete(user);
            // commit the changes to the database
            transaction.commit();
        }
        catch (HibernateException e)
        {
            if (transaction != null)
            {
                transaction.rollback();
            }
            throw new SnippeToDoPlatformException(
                    "Failed to delete a user from the database: " + e.getMessage(), e);
        }
        finally
        {
            session.close();
        }
    }

    /**
     * Get an array of all of the ToDo items from the database items table.
     * 
     * @see ISnippeToDoDAO#getItems()
     * @throws SnippeToDoPlatformException
     * @return the array of all of the ToDo items from the database items table
     */
    @Override
    public Item[] getItems() throws SnippeToDoPlatformException
    {
        Session session = null;
        Transaction transaction = null;
        Item[] items = null;

        try
        {
            // creating a new session for getting list of all items
            session = factory.openSession();
            transaction = session.beginTransaction();
            // create a query to get all of the items from database items table
            List<?> itemsList = session.createQuery("from Item").list();
            // commit the query to the database
            transaction.commit();
            // initialize the items array according to the created items list
            int totalItems = itemsList.size();
            items = new Item[totalItems];
            int itemIndex = 0;
            // copy the items list to the items array to be returned
            for (Object item : itemsList)
            {
                items[itemIndex++] = (Item) item;
            }
        }
        catch (HibernateException e)
        {
            if (transaction != null)
            {
                transaction.rollback();
            }
            throw new SnippeToDoPlatformException(
                    "Failed to get items from the database: " + e.getMessage(), e);
        }
        finally
        {
            session.close();
        }

        return items;
    }

    /**
     * Get an array of all of the registered users from the database users
     * table.
     * 
     * @see ISnippeToDoDAO#getUsers()
     * @throws SnippeToDoPlatformException
     * @return the array of all of the registered users from the database users
     *         table
     */
    @Override
    public User[] getUsers() throws SnippeToDoPlatformException
    {
        Session session = null;
        Transaction transaction = null;
        User[] users = null;

        try
        {
            // creating a new session for getting list of all users
            session = factory.openSession();
            transaction = session.beginTransaction();

            // create a query to get all of the users from database users table
            List<?> usersList = session.createQuery("from User").list();
            // commit the query to the database
            transaction.commit();

            // initialize the users array according to the created users list
            int totalUsers = usersList.size();
            users = new User[totalUsers];

            int userIndex = 0;
            // copy the users list to the users array to be returned
            for (Object item : usersList)
            {
                users[userIndex++] = (User) item;
            }
        }
        catch (HibernateException e)
        {
            if (transaction != null)
            {
                transaction.rollback();
            }
            throw new SnippeToDoPlatformException(
                    "Failed to get users from the database: " + e.getMessage(), e);
        }
        finally
        {
            session.close();
        }

        return users;
    }

    /**
     * Update an existing ToDo item from the database items table.
     * 
     * @param id The id number of the item to be updated from the database
     * @param userId The user id of the user the item belongs to
     * @param description The description of the ToDo item message
     * @see ISnippeToDoDAO#updateItem(int, int, String)
     * @throws SnippeToDoPlatformException
     */
    @Override
    public void updateItem(int id, int userId, String description)
            throws SnippeToDoPlatformException
    {
        Session session = null;
        Transaction transaction = null;

        try
        {
            // creating a new session for updating items
            session = factory.openSession();
            transaction = session.beginTransaction();
            // get the specific item from the database that match the id
            Item item = (Item) session.get(Item.class, id);
            // set the new values of the item
            item.setUserId(userId);
            item.setDescription(description);
            // update the item from the database
            session.update(item);
            // commit the changes to the database
            transaction.commit();
        }
        catch (HibernateException e)
        {
            if (transaction != null)
            {
                transaction.rollback();
            }
            throw new SnippeToDoPlatformException(
                    "Failed to update an item from the database: " + e.getMessage(), e);
        }
        finally
        {
            session.close();
        }
    }

    /**
     * Update an existing registered user from the database users table.
     * 
     * @param id The id number of the user to be updated in the database
     * @param name The name of the registered user
     * @see ISnippeToDoDAO#updateUser(int, String)
     * @throws SnippeToDoPlatformException
     */
    @Override
    public void updateUser(int id, String name) throws SnippeToDoPlatformException
    {
        Session session = null;
        Transaction transaction = null;

        try
        {
            // creating a new session for updating users
            session = factory.openSession();
            transaction = session.beginTransaction();
            // get the specific user from the database that match the id
            User user = (User) session.get(User.class, id);
            // set the new values of the user
            user.setName(name);
            // update the user from the database
            session.update(user);
            // commit the changes to the database
            transaction.commit();
        }
        catch (HibernateException e)
        {
            if (transaction != null)
            {
                transaction.rollback();
            }
            throw new SnippeToDoPlatformException(
                    "Failed to update a user from the database: " + e.getMessage(), e);
        }
        finally
        {
            session.close();
        }
    }

}
