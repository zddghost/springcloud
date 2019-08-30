package com.example.web.system.entity;

import java.util.ArrayList;
import java.util.List;

public class SysUserRefSysPermissionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysUserRefSysPermissionExample() {
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

        public Criteria andSysyUserIdIsNull() {
            addCriterion("SYSY_USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andSysyUserIdIsNotNull() {
            addCriterion("SYSY_USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSysyUserIdEqualTo(String value) {
            addCriterion("SYSY_USER_ID =", value, "sysyUserId");
            return (Criteria) this;
        }

        public Criteria andSysyUserIdNotEqualTo(String value) {
            addCriterion("SYSY_USER_ID <>", value, "sysyUserId");
            return (Criteria) this;
        }

        public Criteria andSysyUserIdGreaterThan(String value) {
            addCriterion("SYSY_USER_ID >", value, "sysyUserId");
            return (Criteria) this;
        }

        public Criteria andSysyUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("SYSY_USER_ID >=", value, "sysyUserId");
            return (Criteria) this;
        }

        public Criteria andSysyUserIdLessThan(String value) {
            addCriterion("SYSY_USER_ID <", value, "sysyUserId");
            return (Criteria) this;
        }

        public Criteria andSysyUserIdLessThanOrEqualTo(String value) {
            addCriterion("SYSY_USER_ID <=", value, "sysyUserId");
            return (Criteria) this;
        }

        public Criteria andSysyUserIdLike(String value) {
            addCriterion("SYSY_USER_ID like", value, "sysyUserId");
            return (Criteria) this;
        }

        public Criteria andSysyUserIdNotLike(String value) {
            addCriterion("SYSY_USER_ID not like", value, "sysyUserId");
            return (Criteria) this;
        }

        public Criteria andSysyUserIdIn(List<String> values) {
            addCriterion("SYSY_USER_ID in", values, "sysyUserId");
            return (Criteria) this;
        }

        public Criteria andSysyUserIdNotIn(List<String> values) {
            addCriterion("SYSY_USER_ID not in", values, "sysyUserId");
            return (Criteria) this;
        }

        public Criteria andSysyUserIdBetween(String value1, String value2) {
            addCriterion("SYSY_USER_ID between", value1, value2, "sysyUserId");
            return (Criteria) this;
        }

        public Criteria andSysyUserIdNotBetween(String value1, String value2) {
            addCriterion("SYSY_USER_ID not between", value1, value2, "sysyUserId");
            return (Criteria) this;
        }

        public Criteria andSysPermissionIdIsNull() {
            addCriterion("SYS_PERMISSION_ID is null");
            return (Criteria) this;
        }

        public Criteria andSysPermissionIdIsNotNull() {
            addCriterion("SYS_PERMISSION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSysPermissionIdEqualTo(String value) {
            addCriterion("SYS_PERMISSION_ID =", value, "sysPermissionId");
            return (Criteria) this;
        }

        public Criteria andSysPermissionIdNotEqualTo(String value) {
            addCriterion("SYS_PERMISSION_ID <>", value, "sysPermissionId");
            return (Criteria) this;
        }

        public Criteria andSysPermissionIdGreaterThan(String value) {
            addCriterion("SYS_PERMISSION_ID >", value, "sysPermissionId");
            return (Criteria) this;
        }

        public Criteria andSysPermissionIdGreaterThanOrEqualTo(String value) {
            addCriterion("SYS_PERMISSION_ID >=", value, "sysPermissionId");
            return (Criteria) this;
        }

        public Criteria andSysPermissionIdLessThan(String value) {
            addCriterion("SYS_PERMISSION_ID <", value, "sysPermissionId");
            return (Criteria) this;
        }

        public Criteria andSysPermissionIdLessThanOrEqualTo(String value) {
            addCriterion("SYS_PERMISSION_ID <=", value, "sysPermissionId");
            return (Criteria) this;
        }

        public Criteria andSysPermissionIdLike(String value) {
            addCriterion("SYS_PERMISSION_ID like", value, "sysPermissionId");
            return (Criteria) this;
        }

        public Criteria andSysPermissionIdNotLike(String value) {
            addCriterion("SYS_PERMISSION_ID not like", value, "sysPermissionId");
            return (Criteria) this;
        }

        public Criteria andSysPermissionIdIn(List<String> values) {
            addCriterion("SYS_PERMISSION_ID in", values, "sysPermissionId");
            return (Criteria) this;
        }

        public Criteria andSysPermissionIdNotIn(List<String> values) {
            addCriterion("SYS_PERMISSION_ID not in", values, "sysPermissionId");
            return (Criteria) this;
        }

        public Criteria andSysPermissionIdBetween(String value1, String value2) {
            addCriterion("SYS_PERMISSION_ID between", value1, value2, "sysPermissionId");
            return (Criteria) this;
        }

        public Criteria andSysPermissionIdNotBetween(String value1, String value2) {
            addCriterion("SYS_PERMISSION_ID not between", value1, value2, "sysPermissionId");
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