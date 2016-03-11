/**
 * 
 */
package com.github.idnbso.snippetodo.model.data;

/**
 * @author Idan Busso
 * @author Shani Kahila
 *
 */
public class User
{
    private int id;
    private String name;
        
    /**
     * 
     */
    public User()
    {
        super();
    }
        
    /**
     * @param id
     * @param name
     */
    public User(int id, String name)
    {
        this.setId(id);
        this.setName(name);
    }

    /**
     * @return the id
     */
    public int getId()
    {
        return id;
    }
    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }
    /**
     * @param id the id to set
     */
    public void setId(int id)
    {
        this.id = id;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return String.format("User [id=%s, name=%s]", id, name);
    }
}
