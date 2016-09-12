package com.delmar.core.i18n;

import com.delmar.core.bo.LabelBo;
import com.delmar.core.service.LabelService;
import com.delmar.core.util.SpringContextUtil;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by 刘大磊 on 2016/9/12 8:35.
 */
public final class DbResourceBundle {
    private static LabelService labelService;
    private Map<String, LabelBo> labelResourceMap;


    public synchronized static DbResourceBundle getResourceBundle(Locale locale) {
        if (labelService == null) {
            labelService = SpringContextUtil.getBean(LabelService.class);
        }
        return new DbResourceBundle(locale);
    }


    private DbResourceBundle(Locale locale) {
        List<LabelBo> labelBoList = labelService.selectLocaleLabel(locale.toString());
        labelResourceMap = new HashMap<>();
        for (LabelBo labelBo : labelBoList) {
            labelResourceMap.put(labelBo.getKey(), labelBo);
        }

    }

    public String getLabel(String key) {
        LabelBo labelBo = labelResourceMap.get(key);
        if (labelBo == null) {
            return null;
        } else return labelBo.getValue();
    }
    //TODO Need to implements it.
    public String getMessage(String key)
    {
        return null;
    }

}
