package com.delmar.web.model;
/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年7月1日 上午11:58:08 
 * 用于在前端的代码选择，非字典类的
 */
public class ObjSelect {
	private Integer id;
	private String value;
	private String name;
	
	public ObjSelect()
	{
		
	}
	public ObjSelect(Integer id,String value,String name)
	{
		this.id=id;
		this.value=value;
		this.name=name;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
