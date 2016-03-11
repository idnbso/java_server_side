package com.github.idnbso.snippetodo.model.data;

/**
 * @author Idan Busso
 * @author Shani Kahila
 *
 */
public class Item
{
    private int id;
    private int userId;
    private String description;
        
    /**
     * 
     */
    public Item()
    {
        super();
    }

    /**
     * @param id
     * @param userId
     * @param description
     */
    public Item(int id, int userId, String description)
    {
        this.setId(id);
        this.setDescription(description);
        this.setUserId(userId);
    }



    /**
     * @return the id
     */
    public int getId()
    {
        return id;
    }

    /**
     * @return the description
     */
    public String getDescription()
    {
        return description;
    }
   
    /**
     * @return the userId
     */
    public int getUserId()
    {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return String.format("Item [id=%s, description=%s]", id, description);
    }    
}
