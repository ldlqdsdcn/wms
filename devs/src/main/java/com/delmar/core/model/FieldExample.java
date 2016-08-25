package com.delmar.core.model;

import java.util.ArrayList;
import java.util.List;

public class FieldExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FieldExample() {
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

        public Criteria andLabelIdIsNull() {
            addCriterion("label_id is null");
            return (Criteria) this;
        }

        public Criteria andLabelIdIsNotNull() {
            addCriterion("label_id is not null");
            return (Criteria) this;
        }

        public Criteria andLabelIdEqualTo(Integer value) {
            addCriterion("label_id =", value, "labelId");
            return (Criteria) this;
        }

        public Criteria andLabelIdNotEqualTo(Integer value) {
            addCriterion("label_id <>", value, "labelId");
            return (Criteria) this;
        }

        public Criteria andLabelIdGreaterThan(Integer value) {
            addCriterion("label_id >", value, "labelId");
            return (Criteria) this;
        }

        public Criteria andLabelIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("label_id >=", value, "labelId");
            return (Criteria) this;
        }

        public Criteria andLabelIdLessThan(Integer value) {
            addCriterion("label_id <", value, "labelId");
            return (Criteria) this;
        }

        public Criteria andLabelIdLessThanOrEqualTo(Integer value) {
            addCriterion("label_id <=", value, "labelId");
            return (Criteria) this;
        }

        public Criteria andLabelIdIn(List<Integer> values) {
            addCriterion("label_id in", values, "labelId");
            return (Criteria) this;
        }

        public Criteria andLabelIdNotIn(List<Integer> values) {
            addCriterion("label_id not in", values, "labelId");
            return (Criteria) this;
        }

        public Criteria andLabelIdBetween(Integer value1, Integer value2) {
            addCriterion("label_id between", value1, value2, "labelId");
            return (Criteria) this;
        }

        public Criteria andLabelIdNotBetween(Integer value1, Integer value2) {
            addCriterion("label_id not between", value1, value2, "labelId");
            return (Criteria) this;
        }

        public Criteria andIsRequiredIsNull() {
            addCriterion("is_required is null");
            return (Criteria) this;
        }

        public Criteria andIsRequiredIsNotNull() {
            addCriterion("is_required is not null");
            return (Criteria) this;
        }

        public Criteria andIsRequiredEqualTo(String value) {
            addCriterion("is_required =", value, "isRequired");
            return (Criteria) this;
        }

        public Criteria andIsRequiredNotEqualTo(String value) {
            addCriterion("is_required <>", value, "isRequired");
            return (Criteria) this;
        }

        public Criteria andIsRequiredGreaterThan(String value) {
            addCriterion("is_required >", value, "isRequired");
            return (Criteria) this;
        }

        public Criteria andIsRequiredGreaterThanOrEqualTo(String value) {
            addCriterion("is_required >=", value, "isRequired");
            return (Criteria) this;
        }

        public Criteria andIsRequiredLessThan(String value) {
            addCriterion("is_required <", value, "isRequired");
            return (Criteria) this;
        }

        public Criteria andIsRequiredLessThanOrEqualTo(String value) {
            addCriterion("is_required <=", value, "isRequired");
            return (Criteria) this;
        }

        public Criteria andIsRequiredLike(String value) {
            addCriterion("is_required like", value, "isRequired");
            return (Criteria) this;
        }

        public Criteria andIsRequiredNotLike(String value) {
            addCriterion("is_required not like", value, "isRequired");
            return (Criteria) this;
        }

        public Criteria andIsRequiredIn(List<String> values) {
            addCriterion("is_required in", values, "isRequired");
            return (Criteria) this;
        }

        public Criteria andIsRequiredNotIn(List<String> values) {
            addCriterion("is_required not in", values, "isRequired");
            return (Criteria) this;
        }

        public Criteria andIsRequiredBetween(String value1, String value2) {
            addCriterion("is_required between", value1, value2, "isRequired");
            return (Criteria) this;
        }

        public Criteria andIsRequiredNotBetween(String value1, String value2) {
            addCriterion("is_required not between", value1, value2, "isRequired");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNull() {
            addCriterion("type_id is null");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNotNull() {
            addCriterion("type_id is not null");
            return (Criteria) this;
        }

        public Criteria andTypeIdEqualTo(Integer value) {
            addCriterion("type_id =", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotEqualTo(Integer value) {
            addCriterion("type_id <>", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThan(Integer value) {
            addCriterion("type_id >", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("type_id >=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThan(Integer value) {
            addCriterion("type_id <", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("type_id <=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdIn(List<Integer> values) {
            addCriterion("type_id in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotIn(List<Integer> values) {
            addCriterion("type_id not in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("type_id between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("type_id not between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andTabIdIsNull() {
            addCriterion("tab_id is null");
            return (Criteria) this;
        }

        public Criteria andTabIdIsNotNull() {
            addCriterion("tab_id is not null");
            return (Criteria) this;
        }

        public Criteria andTabIdEqualTo(Integer value) {
            addCriterion("tab_id =", value, "tabId");
            return (Criteria) this;
        }

        public Criteria andTabIdNotEqualTo(Integer value) {
            addCriterion("tab_id <>", value, "tabId");
            return (Criteria) this;
        }

        public Criteria andTabIdGreaterThan(Integer value) {
            addCriterion("tab_id >", value, "tabId");
            return (Criteria) this;
        }

        public Criteria andTabIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("tab_id >=", value, "tabId");
            return (Criteria) this;
        }

        public Criteria andTabIdLessThan(Integer value) {
            addCriterion("tab_id <", value, "tabId");
            return (Criteria) this;
        }

        public Criteria andTabIdLessThanOrEqualTo(Integer value) {
            addCriterion("tab_id <=", value, "tabId");
            return (Criteria) this;
        }

        public Criteria andTabIdIn(List<Integer> values) {
            addCriterion("tab_id in", values, "tabId");
            return (Criteria) this;
        }

        public Criteria andTabIdNotIn(List<Integer> values) {
            addCriterion("tab_id not in", values, "tabId");
            return (Criteria) this;
        }

        public Criteria andTabIdBetween(Integer value1, Integer value2) {
            addCriterion("tab_id between", value1, value2, "tabId");
            return (Criteria) this;
        }

        public Criteria andTabIdNotBetween(Integer value1, Integer value2) {
            addCriterion("tab_id not between", value1, value2, "tabId");
            return (Criteria) this;
        }

        public Criteria andColumnIdIsNull() {
            addCriterion("column_id is null");
            return (Criteria) this;
        }

        public Criteria andColumnIdIsNotNull() {
            addCriterion("column_id is not null");
            return (Criteria) this;
        }

        public Criteria andColumnIdEqualTo(Integer value) {
            addCriterion("column_id =", value, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdNotEqualTo(Integer value) {
            addCriterion("column_id <>", value, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdGreaterThan(Integer value) {
            addCriterion("column_id >", value, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("column_id >=", value, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdLessThan(Integer value) {
            addCriterion("column_id <", value, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdLessThanOrEqualTo(Integer value) {
            addCriterion("column_id <=", value, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdIn(List<Integer> values) {
            addCriterion("column_id in", values, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdNotIn(List<Integer> values) {
            addCriterion("column_id not in", values, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdBetween(Integer value1, Integer value2) {
            addCriterion("column_id between", value1, value2, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdNotBetween(Integer value1, Integer value2) {
            addCriterion("column_id not between", value1, value2, "columnId");
            return (Criteria) this;
        }

        public Criteria andShowTypeIsNull() {
            addCriterion("show_type is null");
            return (Criteria) this;
        }

        public Criteria andShowTypeIsNotNull() {
            addCriterion("show_type is not null");
            return (Criteria) this;
        }

        public Criteria andShowTypeEqualTo(Integer value) {
            addCriterion("show_type =", value, "showType");
            return (Criteria) this;
        }

        public Criteria andShowTypeNotEqualTo(Integer value) {
            addCriterion("show_type <>", value, "showType");
            return (Criteria) this;
        }

        public Criteria andShowTypeGreaterThan(Integer value) {
            addCriterion("show_type >", value, "showType");
            return (Criteria) this;
        }

        public Criteria andShowTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("show_type >=", value, "showType");
            return (Criteria) this;
        }

        public Criteria andShowTypeLessThan(Integer value) {
            addCriterion("show_type <", value, "showType");
            return (Criteria) this;
        }

        public Criteria andShowTypeLessThanOrEqualTo(Integer value) {
            addCriterion("show_type <=", value, "showType");
            return (Criteria) this;
        }

        public Criteria andShowTypeIn(List<Integer> values) {
            addCriterion("show_type in", values, "showType");
            return (Criteria) this;
        }

        public Criteria andShowTypeNotIn(List<Integer> values) {
            addCriterion("show_type not in", values, "showType");
            return (Criteria) this;
        }

        public Criteria andShowTypeBetween(Integer value1, Integer value2) {
            addCriterion("show_type between", value1, value2, "showType");
            return (Criteria) this;
        }

        public Criteria andShowTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("show_type not between", value1, value2, "showType");
            return (Criteria) this;
        }

        public Criteria andLinkTableIdIsNull() {
            addCriterion("link_table_id is null");
            return (Criteria) this;
        }

        public Criteria andLinkTableIdIsNotNull() {
            addCriterion("link_table_id is not null");
            return (Criteria) this;
        }

        public Criteria andLinkTableIdEqualTo(Integer value) {
            addCriterion("link_table_id =", value, "linkTableId");
            return (Criteria) this;
        }

        public Criteria andLinkTableIdNotEqualTo(Integer value) {
            addCriterion("link_table_id <>", value, "linkTableId");
            return (Criteria) this;
        }

        public Criteria andLinkTableIdGreaterThan(Integer value) {
            addCriterion("link_table_id >", value, "linkTableId");
            return (Criteria) this;
        }

        public Criteria andLinkTableIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("link_table_id >=", value, "linkTableId");
            return (Criteria) this;
        }

        public Criteria andLinkTableIdLessThan(Integer value) {
            addCriterion("link_table_id <", value, "linkTableId");
            return (Criteria) this;
        }

        public Criteria andLinkTableIdLessThanOrEqualTo(Integer value) {
            addCriterion("link_table_id <=", value, "linkTableId");
            return (Criteria) this;
        }

        public Criteria andLinkTableIdIn(List<Integer> values) {
            addCriterion("link_table_id in", values, "linkTableId");
            return (Criteria) this;
        }

        public Criteria andLinkTableIdNotIn(List<Integer> values) {
            addCriterion("link_table_id not in", values, "linkTableId");
            return (Criteria) this;
        }

        public Criteria andLinkTableIdBetween(Integer value1, Integer value2) {
            addCriterion("link_table_id between", value1, value2, "linkTableId");
            return (Criteria) this;
        }

        public Criteria andLinkTableIdNotBetween(Integer value1, Integer value2) {
            addCriterion("link_table_id not between", value1, value2, "linkTableId");
            return (Criteria) this;
        }

        public Criteria andLinkValueColumnIdIsNull() {
            addCriterion("link_value_column_id is null");
            return (Criteria) this;
        }

        public Criteria andLinkValueColumnIdIsNotNull() {
            addCriterion("link_value_column_id is not null");
            return (Criteria) this;
        }

        public Criteria andLinkValueColumnIdEqualTo(Integer value) {
            addCriterion("link_value_column_id =", value, "linkValueColumnId");
            return (Criteria) this;
        }

        public Criteria andLinkValueColumnIdNotEqualTo(Integer value) {
            addCriterion("link_value_column_id <>", value, "linkValueColumnId");
            return (Criteria) this;
        }

        public Criteria andLinkValueColumnIdGreaterThan(Integer value) {
            addCriterion("link_value_column_id >", value, "linkValueColumnId");
            return (Criteria) this;
        }

        public Criteria andLinkValueColumnIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("link_value_column_id >=", value, "linkValueColumnId");
            return (Criteria) this;
        }

        public Criteria andLinkValueColumnIdLessThan(Integer value) {
            addCriterion("link_value_column_id <", value, "linkValueColumnId");
            return (Criteria) this;
        }

        public Criteria andLinkValueColumnIdLessThanOrEqualTo(Integer value) {
            addCriterion("link_value_column_id <=", value, "linkValueColumnId");
            return (Criteria) this;
        }

        public Criteria andLinkValueColumnIdIn(List<Integer> values) {
            addCriterion("link_value_column_id in", values, "linkValueColumnId");
            return (Criteria) this;
        }

        public Criteria andLinkValueColumnIdNotIn(List<Integer> values) {
            addCriterion("link_value_column_id not in", values, "linkValueColumnId");
            return (Criteria) this;
        }

        public Criteria andLinkValueColumnIdBetween(Integer value1, Integer value2) {
            addCriterion("link_value_column_id between", value1, value2, "linkValueColumnId");
            return (Criteria) this;
        }

        public Criteria andLinkValueColumnIdNotBetween(Integer value1, Integer value2) {
            addCriterion("link_value_column_id not between", value1, value2, "linkValueColumnId");
            return (Criteria) this;
        }

        public Criteria andLinkLabelColumnIdIsNull() {
            addCriterion("link_label_column_id is null");
            return (Criteria) this;
        }

        public Criteria andLinkLabelColumnIdIsNotNull() {
            addCriterion("link_label_column_id is not null");
            return (Criteria) this;
        }

        public Criteria andLinkLabelColumnIdEqualTo(Integer value) {
            addCriterion("link_label_column_id =", value, "linkLabelColumnId");
            return (Criteria) this;
        }

        public Criteria andLinkLabelColumnIdNotEqualTo(Integer value) {
            addCriterion("link_label_column_id <>", value, "linkLabelColumnId");
            return (Criteria) this;
        }

        public Criteria andLinkLabelColumnIdGreaterThan(Integer value) {
            addCriterion("link_label_column_id >", value, "linkLabelColumnId");
            return (Criteria) this;
        }

        public Criteria andLinkLabelColumnIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("link_label_column_id >=", value, "linkLabelColumnId");
            return (Criteria) this;
        }

        public Criteria andLinkLabelColumnIdLessThan(Integer value) {
            addCriterion("link_label_column_id <", value, "linkLabelColumnId");
            return (Criteria) this;
        }

        public Criteria andLinkLabelColumnIdLessThanOrEqualTo(Integer value) {
            addCriterion("link_label_column_id <=", value, "linkLabelColumnId");
            return (Criteria) this;
        }

        public Criteria andLinkLabelColumnIdIn(List<Integer> values) {
            addCriterion("link_label_column_id in", values, "linkLabelColumnId");
            return (Criteria) this;
        }

        public Criteria andLinkLabelColumnIdNotIn(List<Integer> values) {
            addCriterion("link_label_column_id not in", values, "linkLabelColumnId");
            return (Criteria) this;
        }

        public Criteria andLinkLabelColumnIdBetween(Integer value1, Integer value2) {
            addCriterion("link_label_column_id between", value1, value2, "linkLabelColumnId");
            return (Criteria) this;
        }

        public Criteria andLinkLabelColumnIdNotBetween(Integer value1, Integer value2) {
            addCriterion("link_label_column_id not between", value1, value2, "linkLabelColumnId");
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

        public Criteria andDefaultValueIsNull() {
            addCriterion("default_value is null");
            return (Criteria) this;
        }

        public Criteria andDefaultValueIsNotNull() {
            addCriterion("default_value is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultValueEqualTo(String value) {
            addCriterion("default_value =", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueNotEqualTo(String value) {
            addCriterion("default_value <>", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueGreaterThan(String value) {
            addCriterion("default_value >", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueGreaterThanOrEqualTo(String value) {
            addCriterion("default_value >=", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueLessThan(String value) {
            addCriterion("default_value <", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueLessThanOrEqualTo(String value) {
            addCriterion("default_value <=", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueLike(String value) {
            addCriterion("default_value like", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueNotLike(String value) {
            addCriterion("default_value not like", value, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueIn(List<String> values) {
            addCriterion("default_value in", values, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueNotIn(List<String> values) {
            addCriterion("default_value not in", values, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueBetween(String value1, String value2) {
            addCriterion("default_value between", value1, value2, "defaultValue");
            return (Criteria) this;
        }

        public Criteria andDefaultValueNotBetween(String value1, String value2) {
            addCriterion("default_value not between", value1, value2, "defaultValue");
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

        public Criteria andSamelineIsNull() {
            addCriterion("sameline is null");
            return (Criteria) this;
        }

        public Criteria andSamelineIsNotNull() {
            addCriterion("sameline is not null");
            return (Criteria) this;
        }

        public Criteria andSamelineEqualTo(String value) {
            addCriterion("sameline =", value, "sameline");
            return (Criteria) this;
        }

        public Criteria andSamelineNotEqualTo(String value) {
            addCriterion("sameline <>", value, "sameline");
            return (Criteria) this;
        }

        public Criteria andSamelineGreaterThan(String value) {
            addCriterion("sameline >", value, "sameline");
            return (Criteria) this;
        }

        public Criteria andSamelineGreaterThanOrEqualTo(String value) {
            addCriterion("sameline >=", value, "sameline");
            return (Criteria) this;
        }

        public Criteria andSamelineLessThan(String value) {
            addCriterion("sameline <", value, "sameline");
            return (Criteria) this;
        }

        public Criteria andSamelineLessThanOrEqualTo(String value) {
            addCriterion("sameline <=", value, "sameline");
            return (Criteria) this;
        }

        public Criteria andSamelineLike(String value) {
            addCriterion("sameline like", value, "sameline");
            return (Criteria) this;
        }

        public Criteria andSamelineNotLike(String value) {
            addCriterion("sameline not like", value, "sameline");
            return (Criteria) this;
        }

        public Criteria andSamelineIn(List<String> values) {
            addCriterion("sameline in", values, "sameline");
            return (Criteria) this;
        }

        public Criteria andSamelineNotIn(List<String> values) {
            addCriterion("sameline not in", values, "sameline");
            return (Criteria) this;
        }

        public Criteria andSamelineBetween(String value1, String value2) {
            addCriterion("sameline between", value1, value2, "sameline");
            return (Criteria) this;
        }

        public Criteria andSamelineNotBetween(String value1, String value2) {
            addCriterion("sameline not between", value1, value2, "sameline");
            return (Criteria) this;
        }

        public Criteria andLineTabIdIsNull() {
            addCriterion("line_tab_id is null");
            return (Criteria) this;
        }

        public Criteria andLineTabIdIsNotNull() {
            addCriterion("line_tab_id is not null");
            return (Criteria) this;
        }

        public Criteria andLineTabIdEqualTo(Integer value) {
            addCriterion("line_tab_id =", value, "lineTabId");
            return (Criteria) this;
        }

        public Criteria andLineTabIdNotEqualTo(Integer value) {
            addCriterion("line_tab_id <>", value, "lineTabId");
            return (Criteria) this;
        }

        public Criteria andLineTabIdGreaterThan(Integer value) {
            addCriterion("line_tab_id >", value, "lineTabId");
            return (Criteria) this;
        }

        public Criteria andLineTabIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("line_tab_id >=", value, "lineTabId");
            return (Criteria) this;
        }

        public Criteria andLineTabIdLessThan(Integer value) {
            addCriterion("line_tab_id <", value, "lineTabId");
            return (Criteria) this;
        }

        public Criteria andLineTabIdLessThanOrEqualTo(Integer value) {
            addCriterion("line_tab_id <=", value, "lineTabId");
            return (Criteria) this;
        }

        public Criteria andLineTabIdIn(List<Integer> values) {
            addCriterion("line_tab_id in", values, "lineTabId");
            return (Criteria) this;
        }

        public Criteria andLineTabIdNotIn(List<Integer> values) {
            addCriterion("line_tab_id not in", values, "lineTabId");
            return (Criteria) this;
        }

        public Criteria andLineTabIdBetween(Integer value1, Integer value2) {
            addCriterion("line_tab_id between", value1, value2, "lineTabId");
            return (Criteria) this;
        }

        public Criteria andLineTabIdNotBetween(Integer value1, Integer value2) {
            addCriterion("line_tab_id not between", value1, value2, "lineTabId");
            return (Criteria) this;
        }

        public Criteria andLinkTableFilterSqlIsNull() {
            addCriterion("link_table_filter_sql is null");
            return (Criteria) this;
        }

        public Criteria andLinkTableFilterSqlIsNotNull() {
            addCriterion("link_table_filter_sql is not null");
            return (Criteria) this;
        }

        public Criteria andLinkTableFilterSqlEqualTo(String value) {
            addCriterion("link_table_filter_sql =", value, "linkTableFilterSql");
            return (Criteria) this;
        }

        public Criteria andLinkTableFilterSqlNotEqualTo(String value) {
            addCriterion("link_table_filter_sql <>", value, "linkTableFilterSql");
            return (Criteria) this;
        }

        public Criteria andLinkTableFilterSqlGreaterThan(String value) {
            addCriterion("link_table_filter_sql >", value, "linkTableFilterSql");
            return (Criteria) this;
        }

        public Criteria andLinkTableFilterSqlGreaterThanOrEqualTo(String value) {
            addCriterion("link_table_filter_sql >=", value, "linkTableFilterSql");
            return (Criteria) this;
        }

        public Criteria andLinkTableFilterSqlLessThan(String value) {
            addCriterion("link_table_filter_sql <", value, "linkTableFilterSql");
            return (Criteria) this;
        }

        public Criteria andLinkTableFilterSqlLessThanOrEqualTo(String value) {
            addCriterion("link_table_filter_sql <=", value, "linkTableFilterSql");
            return (Criteria) this;
        }

        public Criteria andLinkTableFilterSqlLike(String value) {
            addCriterion("link_table_filter_sql like", value, "linkTableFilterSql");
            return (Criteria) this;
        }

        public Criteria andLinkTableFilterSqlNotLike(String value) {
            addCriterion("link_table_filter_sql not like", value, "linkTableFilterSql");
            return (Criteria) this;
        }

        public Criteria andLinkTableFilterSqlIn(List<String> values) {
            addCriterion("link_table_filter_sql in", values, "linkTableFilterSql");
            return (Criteria) this;
        }

        public Criteria andLinkTableFilterSqlNotIn(List<String> values) {
            addCriterion("link_table_filter_sql not in", values, "linkTableFilterSql");
            return (Criteria) this;
        }

        public Criteria andLinkTableFilterSqlBetween(String value1, String value2) {
            addCriterion("link_table_filter_sql between", value1, value2, "linkTableFilterSql");
            return (Criteria) this;
        }

        public Criteria andLinkTableFilterSqlNotBetween(String value1, String value2) {
            addCriterion("link_table_filter_sql not between", value1, value2, "linkTableFilterSql");
            return (Criteria) this;
        }

        public Criteria andValidateRoleIsNull() {
            addCriterion("validate_role is null");
            return (Criteria) this;
        }

        public Criteria andValidateRoleIsNotNull() {
            addCriterion("validate_role is not null");
            return (Criteria) this;
        }

        public Criteria andValidateRoleEqualTo(String value) {
            addCriterion("validate_role =", value, "validateRole");
            return (Criteria) this;
        }

        public Criteria andValidateRoleNotEqualTo(String value) {
            addCriterion("validate_role <>", value, "validateRole");
            return (Criteria) this;
        }

        public Criteria andValidateRoleGreaterThan(String value) {
            addCriterion("validate_role >", value, "validateRole");
            return (Criteria) this;
        }

        public Criteria andValidateRoleGreaterThanOrEqualTo(String value) {
            addCriterion("validate_role >=", value, "validateRole");
            return (Criteria) this;
        }

        public Criteria andValidateRoleLessThan(String value) {
            addCriterion("validate_role <", value, "validateRole");
            return (Criteria) this;
        }

        public Criteria andValidateRoleLessThanOrEqualTo(String value) {
            addCriterion("validate_role <=", value, "validateRole");
            return (Criteria) this;
        }

        public Criteria andValidateRoleLike(String value) {
            addCriterion("validate_role like", value, "validateRole");
            return (Criteria) this;
        }

        public Criteria andValidateRoleNotLike(String value) {
            addCriterion("validate_role not like", value, "validateRole");
            return (Criteria) this;
        }

        public Criteria andValidateRoleIn(List<String> values) {
            addCriterion("validate_role in", values, "validateRole");
            return (Criteria) this;
        }

        public Criteria andValidateRoleNotIn(List<String> values) {
            addCriterion("validate_role not in", values, "validateRole");
            return (Criteria) this;
        }

        public Criteria andValidateRoleBetween(String value1, String value2) {
            addCriterion("validate_role between", value1, value2, "validateRole");
            return (Criteria) this;
        }

        public Criteria andValidateRoleNotBetween(String value1, String value2) {
            addCriterion("validate_role not between", value1, value2, "validateRole");
            return (Criteria) this;
        }

        public Criteria andShowInListIsNull() {
            addCriterion("show_in_list is null");
            return (Criteria) this;
        }

        public Criteria andShowInListIsNotNull() {
            addCriterion("show_in_list is not null");
            return (Criteria) this;
        }

        public Criteria andShowInListEqualTo(String value) {
            addCriterion("show_in_list =", value, "showInList");
            return (Criteria) this;
        }

        public Criteria andShowInListNotEqualTo(String value) {
            addCriterion("show_in_list <>", value, "showInList");
            return (Criteria) this;
        }

        public Criteria andShowInListGreaterThan(String value) {
            addCriterion("show_in_list >", value, "showInList");
            return (Criteria) this;
        }

        public Criteria andShowInListGreaterThanOrEqualTo(String value) {
            addCriterion("show_in_list >=", value, "showInList");
            return (Criteria) this;
        }

        public Criteria andShowInListLessThan(String value) {
            addCriterion("show_in_list <", value, "showInList");
            return (Criteria) this;
        }

        public Criteria andShowInListLessThanOrEqualTo(String value) {
            addCriterion("show_in_list <=", value, "showInList");
            return (Criteria) this;
        }

        public Criteria andShowInListLike(String value) {
            addCriterion("show_in_list like", value, "showInList");
            return (Criteria) this;
        }

        public Criteria andShowInListNotLike(String value) {
            addCriterion("show_in_list not like", value, "showInList");
            return (Criteria) this;
        }

        public Criteria andShowInListIn(List<String> values) {
            addCriterion("show_in_list in", values, "showInList");
            return (Criteria) this;
        }

        public Criteria andShowInListNotIn(List<String> values) {
            addCriterion("show_in_list not in", values, "showInList");
            return (Criteria) this;
        }

        public Criteria andShowInListBetween(String value1, String value2) {
            addCriterion("show_in_list between", value1, value2, "showInList");
            return (Criteria) this;
        }

        public Criteria andShowInListNotBetween(String value1, String value2) {
            addCriterion("show_in_list not between", value1, value2, "showInList");
            return (Criteria) this;
        }

        public Criteria andShowInFormIsNull() {
            addCriterion("show_in_form is null");
            return (Criteria) this;
        }

        public Criteria andShowInFormIsNotNull() {
            addCriterion("show_in_form is not null");
            return (Criteria) this;
        }

        public Criteria andShowInFormEqualTo(String value) {
            addCriterion("show_in_form =", value, "showInForm");
            return (Criteria) this;
        }

        public Criteria andShowInFormNotEqualTo(String value) {
            addCriterion("show_in_form <>", value, "showInForm");
            return (Criteria) this;
        }

        public Criteria andShowInFormGreaterThan(String value) {
            addCriterion("show_in_form >", value, "showInForm");
            return (Criteria) this;
        }

        public Criteria andShowInFormGreaterThanOrEqualTo(String value) {
            addCriterion("show_in_form >=", value, "showInForm");
            return (Criteria) this;
        }

        public Criteria andShowInFormLessThan(String value) {
            addCriterion("show_in_form <", value, "showInForm");
            return (Criteria) this;
        }

        public Criteria andShowInFormLessThanOrEqualTo(String value) {
            addCriterion("show_in_form <=", value, "showInForm");
            return (Criteria) this;
        }

        public Criteria andShowInFormLike(String value) {
            addCriterion("show_in_form like", value, "showInForm");
            return (Criteria) this;
        }

        public Criteria andShowInFormNotLike(String value) {
            addCriterion("show_in_form not like", value, "showInForm");
            return (Criteria) this;
        }

        public Criteria andShowInFormIn(List<String> values) {
            addCriterion("show_in_form in", values, "showInForm");
            return (Criteria) this;
        }

        public Criteria andShowInFormNotIn(List<String> values) {
            addCriterion("show_in_form not in", values, "showInForm");
            return (Criteria) this;
        }

        public Criteria andShowInFormBetween(String value1, String value2) {
            addCriterion("show_in_form between", value1, value2, "showInForm");
            return (Criteria) this;
        }

        public Criteria andShowInFormNotBetween(String value1, String value2) {
            addCriterion("show_in_form not between", value1, value2, "showInForm");
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