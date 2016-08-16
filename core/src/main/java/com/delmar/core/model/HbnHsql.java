package com.delmar.core.model;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import org.hibernate.Query;

/**
 *@ClassName:   HbnWhere.java 
 *@Description:  TODO
 *
 * @author Charles Luo 
 * @Date: 2015年5月19日 下午4:43:52
 * @version V2.0
 */
public class HbnHsql {
    
    public static int VALUE_TYPE_STR = 0;
    public static int VALUE_TYPE_INT = 1;
    public static int VALUE_TYPE_FLOAT = 2;
    public static int VALUE_TYPE_DATE = 3;
    
    public static int LK_TYPE_EQ   = 0;    
    public static int LK_TYPE_LESS = 1;
    public static int LK_TYPE_MORE = 2;
    public static int LK_TYPE_IN   = 3;
    public static int LK_TYPE_LIKE = 4;
    public static int LK_TYPE_LIKE_L = 5;
    public static int LK_TYPE_LIKE_R = 6;
    public static int LK_TYPE_LESS_EQ = 7;
    public static int LK_TYPE_MORE_EQ = 8;
    public static int LK_TYPE_NOTEQ   = 9;    

    public static int REL_TYPE_AND = 0;
    public static int REL_TYPE_OR = 1;

    private Vector whereCellArray = new Vector(11);
    
	private Class className;
	
    
    /**
     * default constructor
     */
    private String simplewhere="";
    private String simpleColumns="";
    
    public HbnHsql(){   
    }
    
    public String getSimplewhere() {
    	if (simplewhere==null)
    		simplewhere="";	
		return simplewhere;
	}

	public void setSimplewhere(String simplewhere) {
		simplewhere = simplewhere;
	}
	
	
	
	public String getSimpleColumns() {
    	if (simpleColumns==null)
    		simpleColumns="";			
		return simpleColumns;
	}

	public void setSimpleColumns(String simpleColumns) {
		simpleColumns = simpleColumns;
	}

	public Class getClassName() {
		return className;
	}

	public void setClassName(Class className) {
		this.className = className;
	}	

	/**
     * 增加条件单元
     * @param sqlName
     * @param sqlValue
     * @param sqlType
     * @param relation
     */
    public void addWhereCell(String sqlName,
							String sqlValue,
							int sqlType,
							int link,
							int relation){
        
        HbnWhereCell cell = new HbnWhereCell(sqlName,
                				sqlValue,sqlType,link,relation);
        whereCellArray.add(cell);
    }
    

    
    /**
     * 判断是否可以生成条件语句
     * @return
     */
    public boolean hasWhereCell(){
        if (whereCellArray.size() > 0) 
            return true;
        else 
            return false;
    }

        
    
    /**
     * 得到查询条件语句
     * @return
     */
    public String getSqlWhere(){
    	//第一优先级为Simplewhere
    	if (simplewhere.equals(""))
    	{
            if (hasWhereCell()){
                StringBuffer result =new StringBuffer();
                Iterator list = whereCellArray.iterator();
                while(list.hasNext()){
                	HbnWhereCell cell = (HbnWhereCell)list.next();
                    result.append(cell.createWhere());
                }
                return result.toString();
            }
            else
                return "";    		
    	}
    	else
    	{
    		return simplewhere;
    	}

    }
    
    /**
     * 为查询语句设置数据
     * @param stmt
     * @throws SQLException
     */
    public int setQueryValue(Query query) 
    {
        int index = 0;
        if (hasWhereCell()){
            Iterator list = whereCellArray.iterator();
            while(list.hasNext()){
            	HbnWhereCell cell = (HbnWhereCell)list.next();
                index = cell.setQValue(index,query);
            }            
        }
        return index-1;
    }
    

    


}
