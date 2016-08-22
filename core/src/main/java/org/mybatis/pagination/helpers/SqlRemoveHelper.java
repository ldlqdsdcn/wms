package org.mybatis.pagination.helpers;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Preconditions;
/**
 * Created by admin on 2016/8/22.
 */
public class SqlRemoveHelper {
    /** Order by 正则表达式 */
    public static final String ORDER_BY_REGEX = "order\\s*by[\\w|\\W|\\s|\\S]*";
    /** Xsql Order by 正则表达式 */
    public static final String XSQL_ORDER_BY_REGEX = "/~.*order\\s*by[\\w|\\W|\\s|\\S]*~/";
    /** From正则表达式 */
    public static final String FROM_REGEX = "\\sfrom\\s";

    /** sql contains whre regex. */
    public static final String WHERE_REGEX          = "\\s+where\\s+";
    /** sql contains <code>order by </code> regex. */
    public static final String ORDER_REGEX          = "order\\s+by";
    public static boolean containOrder(String sql) {
        return containRegex(sql, ORDER_REGEX);
    }

    public static boolean containWhere(String sql) {
        return containRegex(sql, WHERE_REGEX);
    }



    public static boolean containRegex(String sql, String regex) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(sql);
        return matcher.find();
    }

    private static int indexOfByRegex(String input, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        if (m.find()) {
            return m.start();
        }
        return -1;
    }

    /**
     * 去除select 子句，未考虑union的情况
     *
     * @param sql sql
     * @return 删除掉的selcet的子句
     */
    public static String removeSelect(String sql) {
        Preconditions.checkNotNull(sql);
        int beginPos = indexOfByRegex(sql.toLowerCase(), FROM_REGEX);
        Preconditions.checkArgument(beginPos != -1, " sql : " + sql + " must has a keyword 'from'");
        return sql.substring(beginPos);
    }

    /**
     * 去除orderby 子句
     *
     * @param sql sql
     * @return 去掉order by sql
     */
    public static String removeOrders(String sql) {
        Preconditions.checkNotNull(sql);
        Pattern p = Pattern.compile(ORDER_BY_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(sql);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "");
        }
        m.appendTail(sb);
        return sb.toString();
    }

    public static String removeFetchKeyword(String sql) {
        return sql.replaceAll("(?i)fetch", "");
    }

    public static String removeXsqlBuilderOrders(String string) {
        Preconditions.checkNotNull(string);
        Pattern p = Pattern.compile(XSQL_ORDER_BY_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(string);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "");
        }
        m.appendTail(sb);
        return removeOrders(sb.toString());
    }
}
