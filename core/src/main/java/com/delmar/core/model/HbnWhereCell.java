package com.delmar.core.model;

import java.math.BigDecimal;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;

import org.hibernate.Query;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月19日 下午4:23:04 
 * 类说明 
 */
public class HbnWhereCell {
	
	private String sqlName;//字段名
	private String sqlValue; //值
	private int sqlType;//字段类型
	private int relation=0;//连接条件 and or
	private int link=0; //条件 >= <= = > < like
	
	

	public HbnWhereCell() {
	
	}
	
	public HbnWhereCell(String sqlName,
			             String sqlValue,
			             int sqlType,
			             int link,
			             int relation) {
		this.sqlName=sqlName;
		this.sqlValue=sqlValue;
		this.sqlType=sqlType;
		this.link=link;
		this.relation=relation;
	}

	public String getSqlName() {
		return sqlName;
	}

	public void setSqlName(String sqlName) {
		this.sqlName = sqlName;
	}

	public String getSqlValue() {
		return sqlValue;
	}

	public void setSqlValue(String sqlValue) {
		this.sqlValue = sqlValue;
	}

	public int getSqlType() {
		return sqlType;
	}

	public void setSqlType(int sqlType) {
		this.sqlType = sqlType;
	}

	public int getRelation() {
		return relation;
	}

	public void setRelation(int relation) {
		this.relation = relation;
	}

	public int getLink() {
		return link;
	}

	public void setLink(int link) {
		this.link = link;
	}	
	
	
	  private String createRel(){
	        switch(relation){
	        	case 0: 
	        	    return " AND ";
	        	case 1: 
	        	    return " OR ";
	        	default: 
	        	    return " AND ";
	        }
	    }
	    
	    private String createLink(){
	        switch(link){
	    	case 0: 
	    	    return " = ";
	    	case 1: 
	    	    return " < ";
	    	case 2: 
	    	    return " > ";
	    	case 3: 
	    	    return " IN ";
	    	case 4: 
	    	case 5:
	    	case 6:
	    	    return " LIKE ";    	    
	       	case 7:
	    	    return " <= ";    	    
	       	case 8:
	    	    return " >= ";  
	       	case 9:
	    	    return " <> ";       	    
	     	default: 
	    	    return " = ";
	        } 
	    }
	    
		public String createWhere()
		{
			StringBuilder sb=new StringBuilder(" ");
			sb.append(createRel());
			sb.append(this.sqlName).append(" ");
			sb.append(createLink()).append(" ");
			sb.append(createValueFlag()).append(" ");			
			return sb.toString();
		}
		
	    /**
	     * 设置
	     * @param index
	     * @param stmt
	     * @return
	     */
	    public int setQValue(int index, Query query)
	    {
	        int currentIndex = index;
	        switch(link){
	    	case 0: 
	    	case 1: 
	    	case 2: 
	    	case 7:
	    	case 8:
	    	case 9:    		
	    	    currentIndex = setOneValue(index,sqlValue,query); 
	    	    break;
	    	case 4: 
	    	    currentIndex = setOneValue(index,"%" + sqlValue + "%",query);
	    	    break;
	    	case 5: 
	    	    currentIndex = setOneValue(index,"%" + sqlValue,query);
	    	    break;
	    	case 6:     	
	    	    currentIndex = setOneValue(index,sqlValue + "%" ,query);
	    	    break;
	    	case 3: 
	    	    currentIndex = setInValue(index,query);
	    	    break;
	    	default: 
	        } 
	        return currentIndex;
	    }
	    
	    
	    private int setInValue(int index, Query query)
	    	    {
	    	        int currentIndex = index;
	    	        StringTokenizer token = new StringTokenizer(sqlValue,",");
	    	        while(token.hasMoreElements()){
	    	            String cellValue = (String)token.nextElement();
	    	            currentIndex = setOneValue(currentIndex,cellValue,query);
	    	        }
	    	        return currentIndex;
	    	    }
	    
	    
	    
	    private int setOneValue(int index, String value,Query query)
	    	    {
	    	        int currentIndex = index;
	    	        switch(sqlType){
	    	    	case 0: 
	    	    		query.setString(index,value);
	    	    	    break;
	    	    	case 1:   
	    	    		query.setInteger(index,Integer.parseInt(value));
	    	    	    break;
	    	    	case 2:   
	    	    		query.setBigDecimal(index,new BigDecimal(value));
	    	    	    break;	
	    	    	case 3:   
	    	    		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
	    	    		ParsePosition pos1 = new ParsePosition(0);
	    	    		query.setDate(index, formatter.parse(value, pos1));
	    	    	    break;		    	    	    
	    	    	default: 
	    	        } 
	    	        currentIndex += 1;
	    	        return currentIndex;
	    	    }
	    	    
	    
		
	
	    private String createValueFlag(){
	        switch(link){
	    	case 0: 
	    	case 1: 
	    	case 2: 
	    	case 4:
	    	case 5: 
	    	case 6: 	    		
	    	case 7:
	    	case 8:
	    	case 9:	    		
	    	    return "?"; 
	    	case 3: 
	    	    return createInCellFlag();    	    
	    	default: 
	    	    return "";
	        } 
	    }
	    
	    private String createInCellFlag(){
	        String result = "(";
	        StringTokenizer token = new StringTokenizer(sqlValue,",");
	        int index = 0;
	        while(token.hasMoreElements()){
	            if (index > 0)
	                result += ",";
	            result += "?";
	            index ++;
	            token.nextElement();
	        }
	        result += ")";
	        return result;
	    }	    

}
