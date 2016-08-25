package com.delmar.core.model;

import java.util.ArrayList;
import java.util.List;

public class PageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PageExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("`name` is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("`name` is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("`name` =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("`name` <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("`name` >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("`name` >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("`name` <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("`name` <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("`name` like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("`name` not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("`name` in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("`name` not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("`name` between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("`name` not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andDescrIsNull() {
            addCriterion("descr is null");
            return (Criteria) this;
        }

        public Criteria andDescrIsNotNull() {
            addCriterion("descr is not null");
            return (Criteria) this;
        }

        public Criteria andDescrEqualTo(String value) {
            addCriterion("descr =", value, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrNotEqualTo(String value) {
            addCriterion("descr <>", value, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrGreaterThan(String value) {
            addCriterion("descr >", value, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrGreaterThanOrEqualTo(String value) {
            addCriterion("descr >=", value, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrLessThan(String value) {
            addCriterion("descr <", value, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrLessThanOrEqualTo(String value) {
            addCriterion("descr <=", value, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrLike(String value) {
            addCriterion("descr like", value, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrNotLike(String value) {
            addCriterion("descr not like", value, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrIn(List<String> values) {
            addCriterion("descr in", values, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrNotIn(List<String> values) {
            addCriterion("descr not in", values, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrBetween(String value1, String value2) {
            addCriterion("descr between", value1, value2, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrNotBetween(String value1, String value2) {
            addCriterion("descr not between", value1, value2, "descr");
            return (Criteria) this;
        }

        public Criteria andHelpIsNull() {
            addCriterion("help is null");
            return (Criteria) this;
        }

        public Criteria andHelpIsNotNull() {
            addCriterion("help is not null");
            return (Criteria) this;
        }

        public Criteria andHelpEqualTo(String value) {
            addCriterion("help =", value, "help");
            return (Criteria) this;
        }

        public Criteria andHelpNotEqualTo(String value) {
            addCriterion("help <>", value, "help");
            return (Criteria) this;
        }

        public Criteria andHelpGreaterThan(String value) {
            addCriterion("help >", value, "help");
            return (Criteria) this;
        }

        public Criteria andHelpGreaterThanOrEqualTo(String value) {
            addCriterion("help >=", value, "help");
            return (Criteria) this;
        }

        public Criteria andHelpLessThan(String value) {
            addCriterion("help <", value, "help");
            return (Criteria) this;
        }

        public Criteria andHelpLessThanOrEqualTo(String value) {
            addCriterion("help <=", value, "help");
            return (Criteria) this;
        }

        public Criteria andHelpLike(String value) {
            addCriterion("help like", value, "help");
            return (Criteria) this;
        }

        public Criteria andHelpNotLike(String value) {
            addCriterion("help not like", value, "help");
            return (Criteria) this;
        }

        public Criteria andHelpIn(List<String> values) {
            addCriterion("help in", values, "help");
            return (Criteria) this;
        }

        public Criteria andHelpNotIn(List<String> values) {
            addCriterion("help not in", values, "help");
            return (Criteria) this;
        }

        public Criteria andHelpBetween(String value1, String value2) {
            addCriterion("help between", value1, value2, "help");
            return (Criteria) this;
        }

        public Criteria andHelpNotBetween(String value1, String value2) {
            addCriterion("help not between", value1, value2, "help");
            return (Criteria) this;
        }

        public Criteria andWindowIdIsNull() {
            addCriterion("window_id is null");
            return (Criteria) this;
        }

        public Criteria andWindowIdIsNotNull() {
            addCriterion("window_id is not null");
            return (Criteria) this;
        }

        public Criteria andWindowIdEqualTo(Integer value) {
            addCriterion("window_id =", value, "windowId");
            return (Criteria) this;
        }

        public Criteria andWindowIdNotEqualTo(Integer value) {
            addCriterion("window_id <>", value, "windowId");
            return (Criteria) this;
        }

        public Criteria andWindowIdGreaterThan(Integer value) {
            addCriterion("window_id >", value, "windowId");
            return (Criteria) this;
        }

        public Criteria andWindowIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("window_id >=", value, "windowId");
            return (Criteria) this;
        }

        public Criteria andWindowIdLessThan(Integer value) {
            addCriterion("window_id <", value, "windowId");
            return (Criteria) this;
        }

        public Criteria andWindowIdLessThanOrEqualTo(Integer value) {
            addCriterion("window_id <=", value, "windowId");
            return (Criteria) this;
        }

        public Criteria andWindowIdIn(List<Integer> values) {
            addCriterion("window_id in", values, "windowId");
            return (Criteria) this;
        }

        public Criteria andWindowIdNotIn(List<Integer> values) {
            addCriterion("window_id not in", values, "windowId");
            return (Criteria) this;
        }

        public Criteria andWindowIdBetween(Integer value1, Integer value2) {
            addCriterion("window_id between", value1, value2, "windowId");
            return (Criteria) this;
        }

        public Criteria andWindowIdNotBetween(Integer value1, Integer value2) {
            addCriterion("window_id not between", value1, value2, "windowId");
            return (Criteria) this;
        }

        public Criteria andTableIdIsNull() {
            addCriterion("table_id is null");
            return (Criteria) this;
        }

        public Criteria andTableIdIsNotNull() {
            addCriterion("table_id is not null");
            return (Criteria) this;
        }

        public Criteria andTableIdEqualTo(Integer value) {
            addCriterion("table_id =", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdNotEqualTo(Integer value) {
            addCriterion("table_id <>", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdGreaterThan(Integer value) {
            addCriterion("table_id >", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("table_id >=", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdLessThan(Integer value) {
            addCriterion("table_id <", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdLessThanOrEqualTo(Integer value) {
            addCriterion("table_id <=", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdIn(List<Integer> values) {
            addCriterion("table_id in", values, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdNotIn(List<Integer> values) {
            addCriterion("table_id not in", values, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdBetween(Integer value1, Integer value2) {
            addCriterion("table_id between", value1, value2, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdNotBetween(Integer value1, Integer value2) {
            addCriterion("table_id not between", value1, value2, "tableId");
            return (Criteria) this;
        }

        public Criteria andFilterColumnIdIsNull() {
            addCriterion("filter_column_id is null");
            return (Criteria) this;
        }

        public Criteria andFilterColumnIdIsNotNull() {
            addCriterion("filter_column_id is not null");
            return (Criteria) this;
        }

        public Criteria andFilterColumnIdEqualTo(Integer value) {
            addCriterion("filter_column_id =", value, "filterColumnId");
            return (Criteria) this;
        }

        public Criteria andFilterColumnIdNotEqualTo(Integer value) {
            addCriterion("filter_column_id <>", value, "filterColumnId");
            return (Criteria) this;
        }

        public Criteria andFilterColumnIdGreaterThan(Integer value) {
            addCriterion("filter_column_id >", value, "filterColumnId");
            return (Criteria) this;
        }

        public Criteria andFilterColumnIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("filter_column_id >=", value, "filterColumnId");
            return (Criteria) this;
        }

        public Criteria andFilterColumnIdLessThan(Integer value) {
            addCriterion("filter_column_id <", value, "filterColumnId");
            return (Criteria) this;
        }

        public Criteria andFilterColumnIdLessThanOrEqualTo(Integer value) {
            addCriterion("filter_column_id <=", value, "filterColumnId");
            return (Criteria) this;
        }

        public Criteria andFilterColumnIdIn(List<Integer> values) {
            addCriterion("filter_column_id in", values, "filterColumnId");
            return (Criteria) this;
        }

        public Criteria andFilterColumnIdNotIn(List<Integer> values) {
            addCriterion("filter_column_id not in", values, "filterColumnId");
            return (Criteria) this;
        }

        public Criteria andFilterColumnIdBetween(Integer value1, Integer value2) {
            addCriterion("filter_column_id between", value1, value2, "filterColumnId");
            return (Criteria) this;
        }

        public Criteria andFilterColumnIdNotBetween(Integer value1, Integer value2) {
            addCriterion("filter_column_id not between", value1, value2, "filterColumnId");
            return (Criteria) this;
        }

        public Criteria andIsactiveIsNull() {
            addCriterion("isactive is null");
            return (Criteria) this;
        }

        public Criteria andIsactiveIsNotNull() {
            addCriterion("isactive is not null");
            return (Criteria) this;
        }

        public Criteria andIsactiveEqualTo(String value) {
            addCriterion("isactive =", value, "isactive");
            return (Criteria) this;
        }

        public Criteria andIsactiveNotEqualTo(String value) {
            addCriterion("isactive <>", value, "isactive");
            return (Criteria) this;
        }

        public Criteria andIsactiveGreaterThan(String value) {
            addCriterion("isactive >", value, "isactive");
            return (Criteria) this;
        }

        public Criteria andIsactiveGreaterThanOrEqualTo(String value) {
            addCriterion("isactive >=", value, "isactive");
            return (Criteria) this;
        }

        public Criteria andIsactiveLessThan(String value) {
            addCriterion("isactive <", value, "isactive");
            return (Criteria) this;
        }

        public Criteria andIsactiveLessThanOrEqualTo(String value) {
            addCriterion("isactive <=", value, "isactive");
            return (Criteria) this;
        }

        public Criteria andIsactiveLike(String value) {
            addCriterion("isactive like", value, "isactive");
            return (Criteria) this;
        }

        public Criteria andIsactiveNotLike(String value) {
            addCriterion("isactive not like", value, "isactive");
            return (Criteria) this;
        }

        public Criteria andIsactiveIn(List<String> values) {
            addCriterion("isactive in", values, "isactive");
            return (Criteria) this;
        }

        public Criteria andIsactiveNotIn(List<String> values) {
            addCriterion("isactive not in", values, "isactive");
            return (Criteria) this;
        }

        public Criteria andIsactiveBetween(String value1, String value2) {
            addCriterion("isactive between", value1, value2, "isactive");
            return (Criteria) this;
        }

        public Criteria andIsactiveNotBetween(String value1, String value2) {
            addCriterion("isactive not between", value1, value2, "isactive");
            return (Criteria) this;
        }

        public Criteria andShowInTabIsNull() {
            addCriterion("show_in_tab is null");
            return (Criteria) this;
        }

        public Criteria andShowInTabIsNotNull() {
            addCriterion("show_in_tab is not null");
            return (Criteria) this;
        }

        public Criteria andShowInTabEqualTo(String value) {
            addCriterion("show_in_tab =", value, "showInTab");
            return (Criteria) this;
        }

        public Criteria andShowInTabNotEqualTo(String value) {
            addCriterion("show_in_tab <>", value, "showInTab");
            return (Criteria) this;
        }

        public Criteria andShowInTabGreaterThan(String value) {
            addCriterion("show_in_tab >", value, "showInTab");
            return (Criteria) this;
        }

        public Criteria andShowInTabGreaterThanOrEqualTo(String value) {
            addCriterion("show_in_tab >=", value, "showInTab");
            return (Criteria) this;
        }

        public Criteria andShowInTabLessThan(String value) {
            addCriterion("show_in_tab <", value, "showInTab");
            return (Criteria) this;
        }

        public Criteria andShowInTabLessThanOrEqualTo(String value) {
            addCriterion("show_in_tab <=", value, "showInTab");
            return (Criteria) this;
        }

        public Criteria andShowInTabLike(String value) {
            addCriterion("show_in_tab like", value, "showInTab");
            return (Criteria) this;
        }

        public Criteria andShowInTabNotLike(String value) {
            addCriterion("show_in_tab not like", value, "showInTab");
            return (Criteria) this;
        }

        public Criteria andShowInTabIn(List<String> values) {
            addCriterion("show_in_tab in", values, "showInTab");
            return (Criteria) this;
        }

        public Criteria andShowInTabNotIn(List<String> values) {
            addCriterion("show_in_tab not in", values, "showInTab");
            return (Criteria) this;
        }

        public Criteria andShowInTabBetween(String value1, String value2) {
            addCriterion("show_in_tab between", value1, value2, "showInTab");
            return (Criteria) this;
        }

        public Criteria andShowInTabNotBetween(String value1, String value2) {
            addCriterion("show_in_tab not between", value1, value2, "showInTab");
            return (Criteria) this;
        }

        public Criteria andSeqNoIsNull() {
            addCriterion("seq_no is null");
            return (Criteria) this;
        }

        public Criteria andSeqNoIsNotNull() {
            addCriterion("seq_no is not null");
            return (Criteria) this;
        }

        public Criteria andSeqNoEqualTo(Integer value) {
            addCriterion("seq_no =", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoNotEqualTo(Integer value) {
            addCriterion("seq_no <>", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoGreaterThan(Integer value) {
            addCriterion("seq_no >", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("seq_no >=", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoLessThan(Integer value) {
            addCriterion("seq_no <", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoLessThanOrEqualTo(Integer value) {
            addCriterion("seq_no <=", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoIn(List<Integer> values) {
            addCriterion("seq_no in", values, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoNotIn(List<Integer> values) {
            addCriterion("seq_no not in", values, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoBetween(Integer value1, Integer value2) {
            addCriterion("seq_no between", value1, value2, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoNotBetween(Integer value1, Integer value2) {
            addCriterion("seq_no not between", value1, value2, "seqNo");
            return (Criteria) this;
        }

        public Criteria andFilterSqlIsNull() {
            addCriterion("filter_sql is null");
            return (Criteria) this;
        }

        public Criteria andFilterSqlIsNotNull() {
            addCriterion("filter_sql is not null");
            return (Criteria) this;
        }

        public Criteria andFilterSqlEqualTo(String value) {
            addCriterion("filter_sql =", value, "filterSql");
            return (Criteria) this;
        }

        public Criteria andFilterSqlNotEqualTo(String value) {
            addCriterion("filter_sql <>", value, "filterSql");
            return (Criteria) this;
        }

        public Criteria andFilterSqlGreaterThan(String value) {
            addCriterion("filter_sql >", value, "filterSql");
            return (Criteria) this;
        }

        public Criteria andFilterSqlGreaterThanOrEqualTo(String value) {
            addCriterion("filter_sql >=", value, "filterSql");
            return (Criteria) this;
        }

        public Criteria andFilterSqlLessThan(String value) {
            addCriterion("filter_sql <", value, "filterSql");
            return (Criteria) this;
        }

        public Criteria andFilterSqlLessThanOrEqualTo(String value) {
            addCriterion("filter_sql <=", value, "filterSql");
            return (Criteria) this;
        }

        public Criteria andFilterSqlLike(String value) {
            addCriterion("filter_sql like", value, "filterSql");
            return (Criteria) this;
        }

        public Criteria andFilterSqlNotLike(String value) {
            addCriterion("filter_sql not like", value, "filterSql");
            return (Criteria) this;
        }

        public Criteria andFilterSqlIn(List<String> values) {
            addCriterion("filter_sql in", values, "filterSql");
            return (Criteria) this;
        }

        public Criteria andFilterSqlNotIn(List<String> values) {
            addCriterion("filter_sql not in", values, "filterSql");
            return (Criteria) this;
        }

        public Criteria andFilterSqlBetween(String value1, String value2) {
            addCriterion("filter_sql between", value1, value2, "filterSql");
            return (Criteria) this;
        }

        public Criteria andFilterSqlNotBetween(String value1, String value2) {
            addCriterion("filter_sql not between", value1, value2, "filterSql");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}