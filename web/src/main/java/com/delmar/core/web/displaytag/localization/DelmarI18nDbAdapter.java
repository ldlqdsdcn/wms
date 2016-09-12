package com.delmar.core.web.displaytag.localization;

import com.delmar.core.web.bean.UserResource;
import org.apache.log4j.Logger;
import org.apache.taglibs.standard.tag.common.fmt.BundleSupport;
import org.displaytag.Messages;
import org.displaytag.localization.I18nResourceProvider;
import org.displaytag.localization.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.jstl.core.Config;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.Locale;
import java.util.MissingResourceException;

/**
 * Created by 刘大磊 on 2016/9/10 15:23.
 *
 * display tag国际化从数据库中读取
 */
public class DelmarI18nDbAdapter implements I18nResourceProvider, LocaleResolver {
    /**
     * prefix/suffix for missing entries.
     */
    public static final String UNDEFINED_KEY = "???"; //$NON-NLS-1$

    /**
     * logger.
     */
    private static Logger log = Logger.getLogger(DelmarI18nDbAdapter.class);

    /**
     * @see LocaleResolver#resolveLocale(HttpServletRequest)
     */
    public Locale resolveLocale(HttpServletRequest request) {
        Locale locale = (Locale) Config.get(request.getSession(), Config.FMT_LOCALE);
        if (locale == null) {
            locale = request.getLocale();
        }
        return locale;
    }

    @Override
    public String getResource(String resourceKey, String defaultValue, Tag tag, PageContext pageContext) {

        UserResource bundle = (UserResource) pageContext.getSession().getAttribute("resource");
        // if titleKey isn't defined either, use property
        String key = (resourceKey != null) ? resourceKey : defaultValue;
        String title = null;

        // jakarta jstl implementation, there is no other way to get the bundle from the parent fmt:bundle tag
        Tag bundleTag = TagSupport.findAncestorWithClass(tag, BundleSupport.class);
        if (bundleTag != null) {
            BundleSupport parent = (BundleSupport) bundleTag;
            if (key != null) {
                String prefix = parent.getPrefix();
                if (prefix != null) {
                    key = prefix + key;
                }
            }

        }
        if (bundle != null) {
            try {
                title = bundle.get(key);
            } catch (MissingResourceException e) {
                log.debug(Messages.getString("Localization.missingkey", key)); //$NON-NLS-1$

                // if user explicitely added a titleKey we guess this is an error
                if (resourceKey != null) {
                    title = UNDEFINED_KEY + resourceKey + UNDEFINED_KEY;
                }
            }
        }

        return title;
    }
}
