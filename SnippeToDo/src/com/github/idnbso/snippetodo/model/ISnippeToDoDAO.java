/**
 * 
 */
package com.github.idnbso.snippetodo.model;

import java.util.List;

/**
 * ISnippeToDoDAO interface provides all of the CRUD operations to be used in
 * the SnippeToDo database by the users.
 *  
 * @author Idan Busso
 * @author Shani Kahila
 */
public interface ISnippeToDoDAO<T>
{
    /**
     * Create a new entity to be added to the database.
     * 
     * @param entity The entity to be added to the database
     * @see ISnippeToDoDAO#create(T)
     * @throws SnippeToDoPlatformExceptions
     */
    public void create(final T entity) throws SnippeToDoPlatformException;

    /**
     * Delete an existing entity from the database by its id number.
     * 
     * @param id The id number of the entity to be deleted from the database
     * @see ISnippeToDoDAO#deleteById(int)
     * @throws SnippeToDoPlatformException
     */
    public void deleteById(final int id) throws SnippeToDoPlatformException;

    /**
     * Delete an existing entity from the database.
     * 
     * @param entity The entity to be deleted from the database
     * @see ISnippeToDoDAO#delete(T)
     * @throws SnippeToDoPlatformException
     */
    public void delete(final T entity) throws SnippeToDoPlatformException;

    /**
     * Get an array of all of the entities from the database.
     * 
     * @see ISnippeToDoDAO#getAll()
     * @throws SnippeToDoPlatformException
     * @return the array of all of the entities from the database
     */
    public List<T> getAll() throws SnippeToDoPlatformException;

    /**
     * Get a specific entity reference from the database.
     * 
     * @param id The id number of the entity to be retrieved from the database
     * @see ISnippeToDoDAO#get()
     * @throws SnippeToDoPlatformException
     * @return the specific entity reference from the database
     */
    public T get(final int id) throws SnippeToDoPlatformException;

    /**
     * Update an existing entity from the database.
     * 
     * @param entity The entity to be updated in the database
     * @see ISnippeToDoDAO#update(T)
     * @throws SnippeToDoPlatformException
     * @return the updated entity reference from the database
     */
    public T update(final T entity) throws SnippeToDoPlatformException;
}
