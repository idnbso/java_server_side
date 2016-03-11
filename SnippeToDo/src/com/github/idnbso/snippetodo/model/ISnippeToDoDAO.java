/**
 * 
 */
package com.github.idnbso.snippetodo.model;

import com.github.idnbso.snippetodo.model.data.*;

/**
 * TODO
 * 
 * @author Idan Busso
 * @author Shani Kahila
 * @see Item
 * @see User
 */
public interface ISnippeToDoDAO
{
    /**
     * Add a new ToDo item to be added to the database's items table.
     * 
     * @param item The ToDo item to be added to the database items table
     * @see ISnippeToDoDAO#addItem(Item)
     * @throws SnippeToDoPlatformException
     */
    public void addItem(Item item) throws SnippeToDoPlatformException;

    /**
     * Add a new registered user to be added to the database's users table.
     * 
     * @param user The registered user to be added to the database users table
     * @see ISnippeToDoDAO#addUser(User)
     * @throws SnippeToDoPlatformException
     */
    public void addUser(User user) throws SnippeToDoPlatformException;

    /**
     * Delete a new ToDo item from the database items table.
     * 
     * @param id The id number of the item to be deleted from the database
     * @see ISnippeToDoDAO#deleteItem(Item)
     * @throws SnippeToDoPlatformException
     */
    public void deleteItem(int id) throws SnippeToDoPlatformException;

    /**
     * Delete a registered user from the database users table.
     * 
     * @param id The id number of the user to be deleted from the database
     * @see ISnippeToDoDAO#deleteUser(User)
     * @throws SnippeToDoPlatformException
     */
    public void deleteUser(int id) throws SnippeToDoPlatformException;

    /**
     * Get an array of all of the ToDo items from the database items table.
     * 
     * @see ISnippeToDoDAO#getItems()
     * @throws SnippeToDoPlatformException
     * @return the array of all of the ToDo items from the database items table
     */
    public Item[] getItems() throws SnippeToDoPlatformException;

    /**
     * Get an array of all of the registered users from the database users
     * table.
     * 
     * @see ISnippeToDoDAO#getUsers()
     * @throws SnippeToDoPlatformException
     * @return the array of all of the registered users from the database
     */
    public User[] getUsers() throws SnippeToDoPlatformException;

    /**
     * Update an existing ToDo item from the database items table.
     * 
     * @param id The id number of the item to be updated from the database
     * @param userId The user id of the user the item belongs to
     * @param description The description of the ToDo item message
     * @see ISnippeToDoDAO#updateItem(int, int, String)
     * @throws SnippeToDoPlatformException
     */
    public void updateItem(int id, int userId, String description)
            throws SnippeToDoPlatformException;

    /**
     * Update an existing registered user from the database users table.
     * 
     * @param id The id number of the user to be updated in the database
     * @param name The name of the registered user
     * @see ISnippeToDoDAO#updateUser(int, String)
     * @throws SnippeToDoPlatformException
     */
    public void updateUser(int id, String name) throws SnippeToDoPlatformException;
}