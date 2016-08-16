package com.delmar.cargo.service;

import java.util.List;
import java.util.Map;

import com.delmar.cargo.model.CustomHead;

public interface CustomHeadService {

	public List<CustomHead> getCustomHeadByMap(Map<String, Object> map);

	public String getBusinessBillCode(Map<String, Object> map);

	public boolean checkBusinessBalance(String trustFileCode);

}
