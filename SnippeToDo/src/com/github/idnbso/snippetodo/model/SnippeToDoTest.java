/**
 * 
 */
package com.github.idnbso.snippetodo.model;

import com.github.idnbso.snippetodo.model.data.*;

/**
 * @author Idan Busso
 * @author Shani Kahila
 *
 */
public class SnippeToDoTest
{
    public static void main(String[] args)
    {
        try
        {
            ISnippeToDoDAO snippeToDoDB = HibernateSnippeToDoDAO.getInstance();
            User snippeToDoUserIdan = new User(1, "Idan");
            Item snippeToDoItemFirst = new Item(1, 1, "test1");
            Item snippeToDoItemSecond = new Item(2, 1, "test2");
            
            snippeToDoDB.addUser(snippeToDoUserIdan);
            printObjects(snippeToDoDB.getUsers());
            
            snippeToDoDB.addItem(snippeToDoItemFirst);
            snippeToDoDB.addItem(snippeToDoItemSecond);
            printObjects(snippeToDoDB.getItems());
            
            snippeToDoDB.updateItem(1, 1, "test3");
            printObjects(snippeToDoDB.getItems());
        }
        catch (SnippeToDoPlatformException e)
        {
            e.printStackTrace();
        }
    }
    
    public static void printObjects(Object[] objects)
    {
        for (Object object : objects)
        {
            System.out.println(object);
        }
    }
}
