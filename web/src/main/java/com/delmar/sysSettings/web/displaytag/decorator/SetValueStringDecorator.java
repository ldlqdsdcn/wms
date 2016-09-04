package com.delmar.sysSettings.web.displaytag.decorator;


import com.delmar.core.web.bean.SystemContextHelper;
import com.delmar.core.web.bean.UserResource;
import com.delmar.sysSettings.enums.InputTypeEnum;
import com.delmar.sysSettings.model.SysSettings;
import com.delmar.sysSettings.model.SysSettingsItem;
import com.delmar.sysSettings.model.SysSettingsItemValue;
import com.delmar.sysSettings.model.SysSettingsValues;
import com.delmar.sysSettings.service.SysSettingsItemValueService;
import com.delmar.sysSettings.service.SysSettingsService;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

import javax.servlet.jsp.PageContext;
import java.util.*;

public class SetValueStringDecorator implements DisplaytagColumnDecorator {

	private SysSettingsService sysSettingsService = SystemContextHelper.getBean("SysSettingsService", SysSettingsService.class);
	private SysSettingsItemValueService sysSettingsItemValueService = SystemContextHelper.getBean("SysSettingsItemValueService", SysSettingsItemValueService.class);
	
	/* (non-Javadoc)
	 * @see org.displaytag.decorator.DisplaytagColumnDecorator#decorate(java.lang.Object, javax.servlet.jsp.PageContext, org.displaytag.properties.MediaTypeEnum)
	 */
	@Override
	public Object decorate(Object arg0, PageContext arg1, MediaTypeEnum arg2)
			throws DecoratorException {
		if (arg0==null) {
			return "";
		}
		if (arg0 instanceof Integer) {
			
			Integer carrierId = (Integer)arg0;
			
			SysSettings sysSettings = sysSettingsService.selectByPrimaryKey(carrierId);
			SysSettingsItem item = sysSettings.getSysSettingsItem();
			Integer setType = item.getSetType();
			
			//如果输入类型为文本输入或者数字输入，做过个性化的话会将个性化值存储在setValueString中 否则setValueString为空
			StringBuffer returnMsg = new StringBuffer();
			if (InputTypeEnum.NumInput.getValue().equals(setType) || InputTypeEnum.TestInput.getValue().equals(setType) ) {
				
				if (StringUtils.isNotEmpty(sysSettings.getSetValueString())) {
					returnMsg.append("<input name=\"setValueString_").append(carrierId + "\"").append(" id=\"setValueString_").append(carrierId  + "\"");
					returnMsg.append(" value=\"").append(sysSettings.getSetValueString()).append("\"");
					returnMsg.append(" style=\"width:180px\">");
					returnMsg.append("</input>");
					return returnMsg.toString();
				} else {
					
					Set<SysSettingsValues> set = sysSettings.getSysSettingsValues();
					UserResource ur = (UserResource)ServletActionContext.getRequest().getSession().getAttribute("resource");
					String language = ur.getLocale().toString();
					
					String setValueString = sysSettingsItemValueService.getItemValueName(set.iterator().next().getSysSettingsItemValue(), language);
					returnMsg.append("<input name=\"setValueString_").append(carrierId + "\"").append(" id=\"setValueString_").append(carrierId  + "\"");
					returnMsg.append(" value=\"").append(setValueString).append("\"");
					returnMsg.append(" style=\"width:180px\">");
					returnMsg.append("</input>");
					return returnMsg.toString();
				}
			}
			
			//如果输入类型为单项选择，做过个性化的话会将个性化值替换sys_settings_values表中数据
			if (InputTypeEnum.SingelSelect.getValue().equals(setType)) {
				Set<SysSettingsValues> sysSettingsValues = sysSettings.getSysSettingsValues();
				Set<SysSettingsItemValue> sysSettingsItemValues = sysSettings.getSysSettingsItem().getSysSettingsItemValues();
				List<SysSettingsItemValue> itemValuesList = new ArrayList<SysSettingsItemValue>();
				itemValuesList.addAll(sysSettingsItemValues);
				 Collections.sort(itemValuesList, new Comparator<SysSettingsItemValue>() {
			            public int compare(SysSettingsItemValue arg0, SysSettingsItemValue arg1) {
			                return arg0.getId().compareTo(arg1.getId());
			            }

			        });
				UserResource ur = (UserResource)ServletActionContext.getRequest().getSession().getAttribute("resource");
				String language = ur.getLocale().toString();
				SysSettingsValues values = sysSettingsValues.iterator().next();
				returnMsg.append("<select id=\"sysSettingsValuesList_").append(carrierId  + "\"").append(" onchange=\"changeSingelSelect(" + carrierId +")\"").append(" style=\"width:180px\">");
				Integer id = 0;
				for (SysSettingsItemValue value : itemValuesList) {
					returnMsg.append("<option value=\"" + value.getId() + "\""); 
					if (values.getItemvalueId().equals(value.getId())) {
						returnMsg.append(" selected=\"selected\"");
						id = value.getId();
					}
					returnMsg.append(">");
					String name = sysSettingsItemValueService.getItemValueName(value, language);
					returnMsg.append(name);
					returnMsg.append("</option>");
				}
				returnMsg.append("</select>");
				
				returnMsg.append("<input name=\"itemValueIds_").append(carrierId  + "\"").append("  id=\"itemValueIds_").append(carrierId  + "\" value=\"" + id + "\" type=\"hidden\"/>");
				return returnMsg.toString();
			}
			
			//如果输入类型为多项选择，做过个性化的话会将个性化值替换sys_settings_values表中数据
			if (InputTypeEnum.Multiselect.getValue().equals(setType)) {
				Set<SysSettingsValues> sysSettingsValues = sysSettings.getSysSettingsValues();
				
				Set<SysSettingsItemValue> sysSettingsItemValues = sysSettings.getSysSettingsItem().getSysSettingsItemValues();
				List<SysSettingsItemValue> itemValuesList = new ArrayList<SysSettingsItemValue>();
				itemValuesList.addAll(sysSettingsItemValues);
				 Collections.sort(itemValuesList, new Comparator<SysSettingsItemValue>() {
			            public int compare(SysSettingsItemValue arg0, SysSettingsItemValue arg1) {
			                return arg0.getId().compareTo(arg1.getId());
			            }

			        });
				UserResource ur = (UserResource)ServletActionContext.getRequest().getSession().getAttribute("resource");
				
				String language = ur.getLocale().toString();
				Map<Integer, SysSettingsValues> map = new HashMap<Integer, SysSettingsValues>();
				for (SysSettingsValues values : sysSettingsValues) {
					map.put(values.getItemvalueId(), values);
				}
				
				returnMsg.append("<select id=\"sysSettingsValuesList_").append(carrierId  + "\"").append(" multiple=\"multiple\"").
				append(" onchange=\"changeMultiselect(" + carrierId +")\"").append(" style=\"width:120px\">");
				String ids = "";
				for (SysSettingsItemValue value : itemValuesList) {
					returnMsg.append("<option value=\"" + value.getId() + "\""); 
					if (map.containsKey(value.getId())) {
						ids += value.getId() + ",";
						returnMsg.append(" selected=\"selected\"");
					}
					returnMsg.append(">");
					String name = sysSettingsItemValueService.getItemValueName(value, language);
					returnMsg.append(name);
					returnMsg.append("</option>");
				}
				returnMsg.append("</select>");
				
				ids = ids.substring(0, ids.lastIndexOf(","));
				returnMsg.append("<input name=\"itemValueIds_").append(carrierId  + "\"").append("  id=\"itemValueIds_").append(carrierId  + "\" value=\"" + ids + "\" type=\"hidden\"/>");
				
				returnMsg.append("<script type=\"text/javascript\">");
				returnMsg.append("jQuery(\"#sysSettingsValuesList_" + carrierId + "\").multiselect({");
				returnMsg.append("initValue:$(\"#itemValueIds_" + carrierId + "\").val(),");
				returnMsg.append("minWidth:180");
				returnMsg.append("});");
				returnMsg.append("var varray_" + carrierId + "=$(\"#itemValueIds_" + carrierId + "\").val().split(\",\");");
				returnMsg.append("for (var i in varray_" + carrierId + "){");
				returnMsg.append("$(\"#sysSettingsValuesList_" + carrierId + "\").find(\"option[value='\"+varray_" + carrierId + "[i]+\"']\").attr(\"selected\",true);");
				returnMsg.append("}");
				
				returnMsg.append("</script>");
				return returnMsg.toString();
			}
		}
		
		return "";
	}

}

