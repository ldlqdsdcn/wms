package com.delmar.core.model;

import lombok.Data;
 /**
  * table name core_search_column
  * Date:2016-08-29 16:03:22
  **/
@Data
public class SearchColumn extends CoreModel {

private Integer id;
private Integer searchId;
private String columnName;
private Integer dataType;
private Integer showType;
private String relOper;
private String fkTable;
private String fkKeyColumn;
private String fkLabelColumn;
private String coditions;
private String remark;
private String columnLabel;
private String newline;
private Integer seqNo;
private String[] relOperList;
private boolean newLineBool;
  public String[] getRelOperList()
  {
      System.out.println("---------------getRelOperList:"+relOper);
    if(relOper==null) return null;
   relOperList=relOper.split(",");
   return relOperList;
  }
  public void setRelOperList(String[] operList)
  {
   this.relOperList=operList;
    if(operList!=null&&operList.length>0)
    {
      StringBuilder sb=new StringBuilder();
     for(int i=0;i<operList.length;i++)
     {
      if(i>0)
      {
       sb.append(",");
      }
      sb.append(operList[i]);
     }
     relOper=sb.toString();
    }
    else
    {
     relOper=null;
    }
      System.out.println("---------------setRelOperList:"+relOper);
  }

}