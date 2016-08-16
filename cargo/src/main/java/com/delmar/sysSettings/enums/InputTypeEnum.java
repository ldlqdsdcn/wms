package com.delmar.sysSettings.enums;

public enum InputTypeEnum {

	NumInput("数字输入",0),
	TestInput("文本输入",1),
	SingelSelect("单项选择",2),
	Multiselect("多项选择",3);
	
	private String name;
    private Integer value;
 
    private InputTypeEnum(String name, Integer value) {
        this.setName(name);
        this.setValue(value);
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
}
