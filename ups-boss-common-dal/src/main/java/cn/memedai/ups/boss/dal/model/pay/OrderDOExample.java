package cn.memedai.ups.boss.dal.model.pay;

import java.util.ArrayList;
import java.util.List;

public class OrderDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderDOExample() {
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

        public Criteria andOrderIdIsNull() {
            addCriterion("ORDER_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("ORDER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(Long value) {
            addCriterion("ORDER_ID =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(Long value) {
            addCriterion("ORDER_ID <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(Long value) {
            addCriterion("ORDER_ID >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ORDER_ID >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(Long value) {
            addCriterion("ORDER_ID <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(Long value) {
            addCriterion("ORDER_ID <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<Long> values) {
            addCriterion("ORDER_ID in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<Long> values) {
            addCriterion("ORDER_ID not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(Long value1, Long value2) {
            addCriterion("ORDER_ID between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(Long value1, Long value2) {
            addCriterion("ORDER_ID not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andUpsTransNumIsNull() {
            addCriterion("UPS_TRANS_NUM is null");
            return (Criteria) this;
        }

        public Criteria andUpsTransNumIsNotNull() {
            addCriterion("UPS_TRANS_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andUpsTransNumEqualTo(String value) {
            addCriterion("UPS_TRANS_NUM =", value, "upsTransNum");
            return (Criteria) this;
        }

        public Criteria andUpsTransNumNotEqualTo(String value) {
            addCriterion("UPS_TRANS_NUM <>", value, "upsTransNum");
            return (Criteria) this;
        }

        public Criteria andUpsTransNumGreaterThan(String value) {
            addCriterion("UPS_TRANS_NUM >", value, "upsTransNum");
            return (Criteria) this;
        }

        public Criteria andUpsTransNumGreaterThanOrEqualTo(String value) {
            addCriterion("UPS_TRANS_NUM >=", value, "upsTransNum");
            return (Criteria) this;
        }

        public Criteria andUpsTransNumLessThan(String value) {
            addCriterion("UPS_TRANS_NUM <", value, "upsTransNum");
            return (Criteria) this;
        }

        public Criteria andUpsTransNumLessThanOrEqualTo(String value) {
            addCriterion("UPS_TRANS_NUM <=", value, "upsTransNum");
            return (Criteria) this;
        }

        public Criteria andUpsTransNumLike(String value) {
            addCriterion("UPS_TRANS_NUM like", value, "upsTransNum");
            return (Criteria) this;
        }

        public Criteria andUpsTransNumNotLike(String value) {
            addCriterion("UPS_TRANS_NUM not like", value, "upsTransNum");
            return (Criteria) this;
        }

        public Criteria andUpsTransNumIn(List<String> values) {
            addCriterion("UPS_TRANS_NUM in", values, "upsTransNum");
            return (Criteria) this;
        }

        public Criteria andUpsTransNumNotIn(List<String> values) {
            addCriterion("UPS_TRANS_NUM not in", values, "upsTransNum");
            return (Criteria) this;
        }

        public Criteria andUpsTransNumBetween(String value1, String value2) {
            addCriterion("UPS_TRANS_NUM between", value1, value2, "upsTransNum");
            return (Criteria) this;
        }

        public Criteria andUpsTransNumNotBetween(String value1, String value2) {
            addCriterion("UPS_TRANS_NUM not between", value1, value2, "upsTransNum");
            return (Criteria) this;
        }

        public Criteria andTradeAmountIsNull() {
            addCriterion("TRADE_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andTradeAmountIsNotNull() {
            addCriterion("TRADE_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andTradeAmountEqualTo(Long value) {
            addCriterion("TRADE_AMOUNT =", value, "tradeAmount");
            return (Criteria) this;
        }

        public Criteria andTradeAmountNotEqualTo(Long value) {
            addCriterion("TRADE_AMOUNT <>", value, "tradeAmount");
            return (Criteria) this;
        }

        public Criteria andTradeAmountGreaterThan(Long value) {
            addCriterion("TRADE_AMOUNT >", value, "tradeAmount");
            return (Criteria) this;
        }

        public Criteria andTradeAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("TRADE_AMOUNT >=", value, "tradeAmount");
            return (Criteria) this;
        }

        public Criteria andTradeAmountLessThan(Long value) {
            addCriterion("TRADE_AMOUNT <", value, "tradeAmount");
            return (Criteria) this;
        }

        public Criteria andTradeAmountLessThanOrEqualTo(Long value) {
            addCriterion("TRADE_AMOUNT <=", value, "tradeAmount");
            return (Criteria) this;
        }

        public Criteria andTradeAmountIn(List<Long> values) {
            addCriterion("TRADE_AMOUNT in", values, "tradeAmount");
            return (Criteria) this;
        }

        public Criteria andTradeAmountNotIn(List<Long> values) {
            addCriterion("TRADE_AMOUNT not in", values, "tradeAmount");
            return (Criteria) this;
        }

        public Criteria andTradeAmountBetween(Long value1, Long value2) {
            addCriterion("TRADE_AMOUNT between", value1, value2, "tradeAmount");
            return (Criteria) this;
        }

        public Criteria andTradeAmountNotBetween(Long value1, Long value2) {
            addCriterion("TRADE_AMOUNT not between", value1, value2, "tradeAmount");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeIsNull() {
            addCriterion("MERCHANT_CODE is null");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeIsNotNull() {
            addCriterion("MERCHANT_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeEqualTo(String value) {
            addCriterion("MERCHANT_CODE =", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeNotEqualTo(String value) {
            addCriterion("MERCHANT_CODE <>", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeGreaterThan(String value) {
            addCriterion("MERCHANT_CODE >", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeGreaterThanOrEqualTo(String value) {
            addCriterion("MERCHANT_CODE >=", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeLessThan(String value) {
            addCriterion("MERCHANT_CODE <", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeLessThanOrEqualTo(String value) {
            addCriterion("MERCHANT_CODE <=", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeLike(String value) {
            addCriterion("MERCHANT_CODE like", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeNotLike(String value) {
            addCriterion("MERCHANT_CODE not like", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeIn(List<String> values) {
            addCriterion("MERCHANT_CODE in", values, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeNotIn(List<String> values) {
            addCriterion("MERCHANT_CODE not in", values, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeBetween(String value1, String value2) {
            addCriterion("MERCHANT_CODE between", value1, value2, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeNotBetween(String value1, String value2) {
            addCriterion("MERCHANT_CODE not between", value1, value2, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantTradeCodeIsNull() {
            addCriterion("MERCHANT_TRADE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andMerchantTradeCodeIsNotNull() {
            addCriterion("MERCHANT_TRADE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantTradeCodeEqualTo(String value) {
            addCriterion("MERCHANT_TRADE_CODE =", value, "merchantTradeCode");
            return (Criteria) this;
        }

        public Criteria andMerchantTradeCodeNotEqualTo(String value) {
            addCriterion("MERCHANT_TRADE_CODE <>", value, "merchantTradeCode");
            return (Criteria) this;
        }

        public Criteria andMerchantTradeCodeGreaterThan(String value) {
            addCriterion("MERCHANT_TRADE_CODE >", value, "merchantTradeCode");
            return (Criteria) this;
        }

        public Criteria andMerchantTradeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("MERCHANT_TRADE_CODE >=", value, "merchantTradeCode");
            return (Criteria) this;
        }

        public Criteria andMerchantTradeCodeLessThan(String value) {
            addCriterion("MERCHANT_TRADE_CODE <", value, "merchantTradeCode");
            return (Criteria) this;
        }

        public Criteria andMerchantTradeCodeLessThanOrEqualTo(String value) {
            addCriterion("MERCHANT_TRADE_CODE <=", value, "merchantTradeCode");
            return (Criteria) this;
        }

        public Criteria andMerchantTradeCodeLike(String value) {
            addCriterion("MERCHANT_TRADE_CODE like", value, "merchantTradeCode");
            return (Criteria) this;
        }

        public Criteria andMerchantTradeCodeNotLike(String value) {
            addCriterion("MERCHANT_TRADE_CODE not like", value, "merchantTradeCode");
            return (Criteria) this;
        }

        public Criteria andMerchantTradeCodeIn(List<String> values) {
            addCriterion("MERCHANT_TRADE_CODE in", values, "merchantTradeCode");
            return (Criteria) this;
        }

        public Criteria andMerchantTradeCodeNotIn(List<String> values) {
            addCriterion("MERCHANT_TRADE_CODE not in", values, "merchantTradeCode");
            return (Criteria) this;
        }

        public Criteria andMerchantTradeCodeBetween(String value1, String value2) {
            addCriterion("MERCHANT_TRADE_CODE between", value1, value2, "merchantTradeCode");
            return (Criteria) this;
        }

        public Criteria andMerchantTradeCodeNotBetween(String value1, String value2) {
            addCriterion("MERCHANT_TRADE_CODE not between", value1, value2, "merchantTradeCode");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNull() {
            addCriterion("PRODUCT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNotNull() {
            addCriterion("PRODUCT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andProductNameEqualTo(String value) {
            addCriterion("PRODUCT_NAME =", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotEqualTo(String value) {
            addCriterion("PRODUCT_NAME <>", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThan(String value) {
            addCriterion("PRODUCT_NAME >", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThanOrEqualTo(String value) {
            addCriterion("PRODUCT_NAME >=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThan(String value) {
            addCriterion("PRODUCT_NAME <", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThanOrEqualTo(String value) {
            addCriterion("PRODUCT_NAME <=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLike(String value) {
            addCriterion("PRODUCT_NAME like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotLike(String value) {
            addCriterion("PRODUCT_NAME not like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameIn(List<String> values) {
            addCriterion("PRODUCT_NAME in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotIn(List<String> values) {
            addCriterion("PRODUCT_NAME not in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameBetween(String value1, String value2) {
            addCriterion("PRODUCT_NAME between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotBetween(String value1, String value2) {
            addCriterion("PRODUCT_NAME not between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductDescIsNull() {
            addCriterion("PRODUCT_DESC is null");
            return (Criteria) this;
        }

        public Criteria andProductDescIsNotNull() {
            addCriterion("PRODUCT_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andProductDescEqualTo(String value) {
            addCriterion("PRODUCT_DESC =", value, "productDesc");
            return (Criteria) this;
        }

        public Criteria andProductDescNotEqualTo(String value) {
            addCriterion("PRODUCT_DESC <>", value, "productDesc");
            return (Criteria) this;
        }

        public Criteria andProductDescGreaterThan(String value) {
            addCriterion("PRODUCT_DESC >", value, "productDesc");
            return (Criteria) this;
        }

        public Criteria andProductDescGreaterThanOrEqualTo(String value) {
            addCriterion("PRODUCT_DESC >=", value, "productDesc");
            return (Criteria) this;
        }

        public Criteria andProductDescLessThan(String value) {
            addCriterion("PRODUCT_DESC <", value, "productDesc");
            return (Criteria) this;
        }

        public Criteria andProductDescLessThanOrEqualTo(String value) {
            addCriterion("PRODUCT_DESC <=", value, "productDesc");
            return (Criteria) this;
        }

        public Criteria andProductDescLike(String value) {
            addCriterion("PRODUCT_DESC like", value, "productDesc");
            return (Criteria) this;
        }

        public Criteria andProductDescNotLike(String value) {
            addCriterion("PRODUCT_DESC not like", value, "productDesc");
            return (Criteria) this;
        }

        public Criteria andProductDescIn(List<String> values) {
            addCriterion("PRODUCT_DESC in", values, "productDesc");
            return (Criteria) this;
        }

        public Criteria andProductDescNotIn(List<String> values) {
            addCriterion("PRODUCT_DESC not in", values, "productDesc");
            return (Criteria) this;
        }

        public Criteria andProductDescBetween(String value1, String value2) {
            addCriterion("PRODUCT_DESC between", value1, value2, "productDesc");
            return (Criteria) this;
        }

        public Criteria andProductDescNotBetween(String value1, String value2) {
            addCriterion("PRODUCT_DESC not between", value1, value2, "productDesc");
            return (Criteria) this;
        }

        public Criteria andMerchantTransDateIsNull() {
            addCriterion("MERCHANT_TRANS_DATE is null");
            return (Criteria) this;
        }

        public Criteria andMerchantTransDateIsNotNull() {
            addCriterion("MERCHANT_TRANS_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantTransDateEqualTo(String value) {
            addCriterion("MERCHANT_TRANS_DATE =", value, "merchantTransDate");
            return (Criteria) this;
        }

        public Criteria andMerchantTransDateNotEqualTo(String value) {
            addCriterion("MERCHANT_TRANS_DATE <>", value, "merchantTransDate");
            return (Criteria) this;
        }

        public Criteria andMerchantTransDateGreaterThan(String value) {
            addCriterion("MERCHANT_TRANS_DATE >", value, "merchantTransDate");
            return (Criteria) this;
        }

        public Criteria andMerchantTransDateGreaterThanOrEqualTo(String value) {
            addCriterion("MERCHANT_TRANS_DATE >=", value, "merchantTransDate");
            return (Criteria) this;
        }

        public Criteria andMerchantTransDateLessThan(String value) {
            addCriterion("MERCHANT_TRANS_DATE <", value, "merchantTransDate");
            return (Criteria) this;
        }

        public Criteria andMerchantTransDateLessThanOrEqualTo(String value) {
            addCriterion("MERCHANT_TRANS_DATE <=", value, "merchantTransDate");
            return (Criteria) this;
        }

        public Criteria andMerchantTransDateLike(String value) {
            addCriterion("MERCHANT_TRANS_DATE like", value, "merchantTransDate");
            return (Criteria) this;
        }

        public Criteria andMerchantTransDateNotLike(String value) {
            addCriterion("MERCHANT_TRANS_DATE not like", value, "merchantTransDate");
            return (Criteria) this;
        }

        public Criteria andMerchantTransDateIn(List<String> values) {
            addCriterion("MERCHANT_TRANS_DATE in", values, "merchantTransDate");
            return (Criteria) this;
        }

        public Criteria andMerchantTransDateNotIn(List<String> values) {
            addCriterion("MERCHANT_TRANS_DATE not in", values, "merchantTransDate");
            return (Criteria) this;
        }

        public Criteria andMerchantTransDateBetween(String value1, String value2) {
            addCriterion("MERCHANT_TRANS_DATE between", value1, value2, "merchantTransDate");
            return (Criteria) this;
        }

        public Criteria andMerchantTransDateNotBetween(String value1, String value2) {
            addCriterion("MERCHANT_TRANS_DATE not between", value1, value2, "merchantTransDate");
            return (Criteria) this;
        }

        public Criteria andMerchantTransTimeIsNull() {
            addCriterion("MERCHANT_TRANS_TIME is null");
            return (Criteria) this;
        }

        public Criteria andMerchantTransTimeIsNotNull() {
            addCriterion("MERCHANT_TRANS_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantTransTimeEqualTo(String value) {
            addCriterion("MERCHANT_TRANS_TIME =", value, "merchantTransTime");
            return (Criteria) this;
        }

        public Criteria andMerchantTransTimeNotEqualTo(String value) {
            addCriterion("MERCHANT_TRANS_TIME <>", value, "merchantTransTime");
            return (Criteria) this;
        }

        public Criteria andMerchantTransTimeGreaterThan(String value) {
            addCriterion("MERCHANT_TRANS_TIME >", value, "merchantTransTime");
            return (Criteria) this;
        }

        public Criteria andMerchantTransTimeGreaterThanOrEqualTo(String value) {
            addCriterion("MERCHANT_TRANS_TIME >=", value, "merchantTransTime");
            return (Criteria) this;
        }

        public Criteria andMerchantTransTimeLessThan(String value) {
            addCriterion("MERCHANT_TRANS_TIME <", value, "merchantTransTime");
            return (Criteria) this;
        }

        public Criteria andMerchantTransTimeLessThanOrEqualTo(String value) {
            addCriterion("MERCHANT_TRANS_TIME <=", value, "merchantTransTime");
            return (Criteria) this;
        }

        public Criteria andMerchantTransTimeLike(String value) {
            addCriterion("MERCHANT_TRANS_TIME like", value, "merchantTransTime");
            return (Criteria) this;
        }

        public Criteria andMerchantTransTimeNotLike(String value) {
            addCriterion("MERCHANT_TRANS_TIME not like", value, "merchantTransTime");
            return (Criteria) this;
        }

        public Criteria andMerchantTransTimeIn(List<String> values) {
            addCriterion("MERCHANT_TRANS_TIME in", values, "merchantTransTime");
            return (Criteria) this;
        }

        public Criteria andMerchantTransTimeNotIn(List<String> values) {
            addCriterion("MERCHANT_TRANS_TIME not in", values, "merchantTransTime");
            return (Criteria) this;
        }

        public Criteria andMerchantTransTimeBetween(String value1, String value2) {
            addCriterion("MERCHANT_TRANS_TIME between", value1, value2, "merchantTransTime");
            return (Criteria) this;
        }

        public Criteria andMerchantTransTimeNotBetween(String value1, String value2) {
            addCriterion("MERCHANT_TRANS_TIME not between", value1, value2, "merchantTransTime");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNull() {
            addCriterion("MEMBER_ID is null");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNotNull() {
            addCriterion("MEMBER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMemberIdEqualTo(String value) {
            addCriterion("MEMBER_ID =", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotEqualTo(String value) {
            addCriterion("MEMBER_ID <>", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThan(String value) {
            addCriterion("MEMBER_ID >", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThanOrEqualTo(String value) {
            addCriterion("MEMBER_ID >=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThan(String value) {
            addCriterion("MEMBER_ID <", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThanOrEqualTo(String value) {
            addCriterion("MEMBER_ID <=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLike(String value) {
            addCriterion("MEMBER_ID like", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotLike(String value) {
            addCriterion("MEMBER_ID not like", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdIn(List<String> values) {
            addCriterion("MEMBER_ID in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotIn(List<String> values) {
            addCriterion("MEMBER_ID not in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdBetween(String value1, String value2) {
            addCriterion("MEMBER_ID between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotBetween(String value1, String value2) {
            addCriterion("MEMBER_ID not between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andBgUrlIsNull() {
            addCriterion("BG_URL is null");
            return (Criteria) this;
        }

        public Criteria andBgUrlIsNotNull() {
            addCriterion("BG_URL is not null");
            return (Criteria) this;
        }

        public Criteria andBgUrlEqualTo(String value) {
            addCriterion("BG_URL =", value, "bgUrl");
            return (Criteria) this;
        }

        public Criteria andBgUrlNotEqualTo(String value) {
            addCriterion("BG_URL <>", value, "bgUrl");
            return (Criteria) this;
        }

        public Criteria andBgUrlGreaterThan(String value) {
            addCriterion("BG_URL >", value, "bgUrl");
            return (Criteria) this;
        }

        public Criteria andBgUrlGreaterThanOrEqualTo(String value) {
            addCriterion("BG_URL >=", value, "bgUrl");
            return (Criteria) this;
        }

        public Criteria andBgUrlLessThan(String value) {
            addCriterion("BG_URL <", value, "bgUrl");
            return (Criteria) this;
        }

        public Criteria andBgUrlLessThanOrEqualTo(String value) {
            addCriterion("BG_URL <=", value, "bgUrl");
            return (Criteria) this;
        }

        public Criteria andBgUrlLike(String value) {
            addCriterion("BG_URL like", value, "bgUrl");
            return (Criteria) this;
        }

        public Criteria andBgUrlNotLike(String value) {
            addCriterion("BG_URL not like", value, "bgUrl");
            return (Criteria) this;
        }

        public Criteria andBgUrlIn(List<String> values) {
            addCriterion("BG_URL in", values, "bgUrl");
            return (Criteria) this;
        }

        public Criteria andBgUrlNotIn(List<String> values) {
            addCriterion("BG_URL not in", values, "bgUrl");
            return (Criteria) this;
        }

        public Criteria andBgUrlBetween(String value1, String value2) {
            addCriterion("BG_URL between", value1, value2, "bgUrl");
            return (Criteria) this;
        }

        public Criteria andBgUrlNotBetween(String value1, String value2) {
            addCriterion("BG_URL not between", value1, value2, "bgUrl");
            return (Criteria) this;
        }

        public Criteria andAccountIdIsNull() {
            addCriterion("ACCOUNT_ID is null");
            return (Criteria) this;
        }

        public Criteria andAccountIdIsNotNull() {
            addCriterion("ACCOUNT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAccountIdEqualTo(Long value) {
            addCriterion("ACCOUNT_ID =", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotEqualTo(Long value) {
            addCriterion("ACCOUNT_ID <>", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdGreaterThan(Long value) {
            addCriterion("ACCOUNT_ID >", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ACCOUNT_ID >=", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLessThan(Long value) {
            addCriterion("ACCOUNT_ID <", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLessThanOrEqualTo(Long value) {
            addCriterion("ACCOUNT_ID <=", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdIn(List<Long> values) {
            addCriterion("ACCOUNT_ID in", values, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotIn(List<Long> values) {
            addCriterion("ACCOUNT_ID not in", values, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdBetween(Long value1, Long value2) {
            addCriterion("ACCOUNT_ID between", value1, value2, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotBetween(Long value1, Long value2) {
            addCriterion("ACCOUNT_ID not between", value1, value2, "accountId");
            return (Criteria) this;
        }

        public Criteria andUpsTransDateIsNull() {
            addCriterion("UPS_TRANS_DATE is null");
            return (Criteria) this;
        }

        public Criteria andUpsTransDateIsNotNull() {
            addCriterion("UPS_TRANS_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andUpsTransDateEqualTo(String value) {
            addCriterion("UPS_TRANS_DATE =", value, "upsTransDate");
            return (Criteria) this;
        }

        public Criteria andUpsTransDateNotEqualTo(String value) {
            addCriterion("UPS_TRANS_DATE <>", value, "upsTransDate");
            return (Criteria) this;
        }

        public Criteria andUpsTransDateGreaterThan(String value) {
            addCriterion("UPS_TRANS_DATE >", value, "upsTransDate");
            return (Criteria) this;
        }

        public Criteria andUpsTransDateGreaterThanOrEqualTo(String value) {
            addCriterion("UPS_TRANS_DATE >=", value, "upsTransDate");
            return (Criteria) this;
        }

        public Criteria andUpsTransDateLessThan(String value) {
            addCriterion("UPS_TRANS_DATE <", value, "upsTransDate");
            return (Criteria) this;
        }

        public Criteria andUpsTransDateLessThanOrEqualTo(String value) {
            addCriterion("UPS_TRANS_DATE <=", value, "upsTransDate");
            return (Criteria) this;
        }

        public Criteria andUpsTransDateLike(String value) {
            addCriterion("UPS_TRANS_DATE like", value, "upsTransDate");
            return (Criteria) this;
        }

        public Criteria andUpsTransDateNotLike(String value) {
            addCriterion("UPS_TRANS_DATE not like", value, "upsTransDate");
            return (Criteria) this;
        }

        public Criteria andUpsTransDateIn(List<String> values) {
            addCriterion("UPS_TRANS_DATE in", values, "upsTransDate");
            return (Criteria) this;
        }

        public Criteria andUpsTransDateNotIn(List<String> values) {
            addCriterion("UPS_TRANS_DATE not in", values, "upsTransDate");
            return (Criteria) this;
        }

        public Criteria andUpsTransDateBetween(String value1, String value2) {
            addCriterion("UPS_TRANS_DATE between", value1, value2, "upsTransDate");
            return (Criteria) this;
        }

        public Criteria andUpsTransDateNotBetween(String value1, String value2) {
            addCriterion("UPS_TRANS_DATE not between", value1, value2, "upsTransDate");
            return (Criteria) this;
        }

        public Criteria andUpsTransTimeIsNull() {
            addCriterion("UPS_TRANS_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpsTransTimeIsNotNull() {
            addCriterion("UPS_TRANS_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpsTransTimeEqualTo(String value) {
            addCriterion("UPS_TRANS_TIME =", value, "upsTransTime");
            return (Criteria) this;
        }

        public Criteria andUpsTransTimeNotEqualTo(String value) {
            addCriterion("UPS_TRANS_TIME <>", value, "upsTransTime");
            return (Criteria) this;
        }

        public Criteria andUpsTransTimeGreaterThan(String value) {
            addCriterion("UPS_TRANS_TIME >", value, "upsTransTime");
            return (Criteria) this;
        }

        public Criteria andUpsTransTimeGreaterThanOrEqualTo(String value) {
            addCriterion("UPS_TRANS_TIME >=", value, "upsTransTime");
            return (Criteria) this;
        }

        public Criteria andUpsTransTimeLessThan(String value) {
            addCriterion("UPS_TRANS_TIME <", value, "upsTransTime");
            return (Criteria) this;
        }

        public Criteria andUpsTransTimeLessThanOrEqualTo(String value) {
            addCriterion("UPS_TRANS_TIME <=", value, "upsTransTime");
            return (Criteria) this;
        }

        public Criteria andUpsTransTimeLike(String value) {
            addCriterion("UPS_TRANS_TIME like", value, "upsTransTime");
            return (Criteria) this;
        }

        public Criteria andUpsTransTimeNotLike(String value) {
            addCriterion("UPS_TRANS_TIME not like", value, "upsTransTime");
            return (Criteria) this;
        }

        public Criteria andUpsTransTimeIn(List<String> values) {
            addCriterion("UPS_TRANS_TIME in", values, "upsTransTime");
            return (Criteria) this;
        }

        public Criteria andUpsTransTimeNotIn(List<String> values) {
            addCriterion("UPS_TRANS_TIME not in", values, "upsTransTime");
            return (Criteria) this;
        }

        public Criteria andUpsTransTimeBetween(String value1, String value2) {
            addCriterion("UPS_TRANS_TIME between", value1, value2, "upsTransTime");
            return (Criteria) this;
        }

        public Criteria andUpsTransTimeNotBetween(String value1, String value2) {
            addCriterion("UPS_TRANS_TIME not between", value1, value2, "upsTransTime");
            return (Criteria) this;
        }

        public Criteria andRespCodeIsNull() {
            addCriterion("RESP_CODE is null");
            return (Criteria) this;
        }

        public Criteria andRespCodeIsNotNull() {
            addCriterion("RESP_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andRespCodeEqualTo(String value) {
            addCriterion("RESP_CODE =", value, "respCode");
            return (Criteria) this;
        }

        public Criteria andRespCodeNotEqualTo(String value) {
            addCriterion("RESP_CODE <>", value, "respCode");
            return (Criteria) this;
        }

        public Criteria andRespCodeGreaterThan(String value) {
            addCriterion("RESP_CODE >", value, "respCode");
            return (Criteria) this;
        }

        public Criteria andRespCodeGreaterThanOrEqualTo(String value) {
            addCriterion("RESP_CODE >=", value, "respCode");
            return (Criteria) this;
        }

        public Criteria andRespCodeLessThan(String value) {
            addCriterion("RESP_CODE <", value, "respCode");
            return (Criteria) this;
        }

        public Criteria andRespCodeLessThanOrEqualTo(String value) {
            addCriterion("RESP_CODE <=", value, "respCode");
            return (Criteria) this;
        }

        public Criteria andRespCodeLike(String value) {
            addCriterion("RESP_CODE like", value, "respCode");
            return (Criteria) this;
        }

        public Criteria andRespCodeNotLike(String value) {
            addCriterion("RESP_CODE not like", value, "respCode");
            return (Criteria) this;
        }

        public Criteria andRespCodeIn(List<String> values) {
            addCriterion("RESP_CODE in", values, "respCode");
            return (Criteria) this;
        }

        public Criteria andRespCodeNotIn(List<String> values) {
            addCriterion("RESP_CODE not in", values, "respCode");
            return (Criteria) this;
        }

        public Criteria andRespCodeBetween(String value1, String value2) {
            addCriterion("RESP_CODE between", value1, value2, "respCode");
            return (Criteria) this;
        }

        public Criteria andRespCodeNotBetween(String value1, String value2) {
            addCriterion("RESP_CODE not between", value1, value2, "respCode");
            return (Criteria) this;
        }

        public Criteria andCodeMsgIsNull() {
            addCriterion("CODE_MSG is null");
            return (Criteria) this;
        }

        public Criteria andCodeMsgIsNotNull() {
            addCriterion("CODE_MSG is not null");
            return (Criteria) this;
        }

        public Criteria andCodeMsgEqualTo(String value) {
            addCriterion("CODE_MSG =", value, "codeMsg");
            return (Criteria) this;
        }

        public Criteria andCodeMsgNotEqualTo(String value) {
            addCriterion("CODE_MSG <>", value, "codeMsg");
            return (Criteria) this;
        }

        public Criteria andCodeMsgGreaterThan(String value) {
            addCriterion("CODE_MSG >", value, "codeMsg");
            return (Criteria) this;
        }

        public Criteria andCodeMsgGreaterThanOrEqualTo(String value) {
            addCriterion("CODE_MSG >=", value, "codeMsg");
            return (Criteria) this;
        }

        public Criteria andCodeMsgLessThan(String value) {
            addCriterion("CODE_MSG <", value, "codeMsg");
            return (Criteria) this;
        }

        public Criteria andCodeMsgLessThanOrEqualTo(String value) {
            addCriterion("CODE_MSG <=", value, "codeMsg");
            return (Criteria) this;
        }

        public Criteria andCodeMsgLike(String value) {
            addCriterion("CODE_MSG like", value, "codeMsg");
            return (Criteria) this;
        }

        public Criteria andCodeMsgNotLike(String value) {
            addCriterion("CODE_MSG not like", value, "codeMsg");
            return (Criteria) this;
        }

        public Criteria andCodeMsgIn(List<String> values) {
            addCriterion("CODE_MSG in", values, "codeMsg");
            return (Criteria) this;
        }

        public Criteria andCodeMsgNotIn(List<String> values) {
            addCriterion("CODE_MSG not in", values, "codeMsg");
            return (Criteria) this;
        }

        public Criteria andCodeMsgBetween(String value1, String value2) {
            addCriterion("CODE_MSG between", value1, value2, "codeMsg");
            return (Criteria) this;
        }

        public Criteria andCodeMsgNotBetween(String value1, String value2) {
            addCriterion("CODE_MSG not between", value1, value2, "codeMsg");
            return (Criteria) this;
        }

        public Criteria andBankTransDateIsNull() {
            addCriterion("BANK_TRANS_DATE is null");
            return (Criteria) this;
        }

        public Criteria andBankTransDateIsNotNull() {
            addCriterion("BANK_TRANS_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andBankTransDateEqualTo(String value) {
            addCriterion("BANK_TRANS_DATE =", value, "bankTransDate");
            return (Criteria) this;
        }

        public Criteria andBankTransDateNotEqualTo(String value) {
            addCriterion("BANK_TRANS_DATE <>", value, "bankTransDate");
            return (Criteria) this;
        }

        public Criteria andBankTransDateGreaterThan(String value) {
            addCriterion("BANK_TRANS_DATE >", value, "bankTransDate");
            return (Criteria) this;
        }

        public Criteria andBankTransDateGreaterThanOrEqualTo(String value) {
            addCriterion("BANK_TRANS_DATE >=", value, "bankTransDate");
            return (Criteria) this;
        }

        public Criteria andBankTransDateLessThan(String value) {
            addCriterion("BANK_TRANS_DATE <", value, "bankTransDate");
            return (Criteria) this;
        }

        public Criteria andBankTransDateLessThanOrEqualTo(String value) {
            addCriterion("BANK_TRANS_DATE <=", value, "bankTransDate");
            return (Criteria) this;
        }

        public Criteria andBankTransDateLike(String value) {
            addCriterion("BANK_TRANS_DATE like", value, "bankTransDate");
            return (Criteria) this;
        }

        public Criteria andBankTransDateNotLike(String value) {
            addCriterion("BANK_TRANS_DATE not like", value, "bankTransDate");
            return (Criteria) this;
        }

        public Criteria andBankTransDateIn(List<String> values) {
            addCriterion("BANK_TRANS_DATE in", values, "bankTransDate");
            return (Criteria) this;
        }

        public Criteria andBankTransDateNotIn(List<String> values) {
            addCriterion("BANK_TRANS_DATE not in", values, "bankTransDate");
            return (Criteria) this;
        }

        public Criteria andBankTransDateBetween(String value1, String value2) {
            addCriterion("BANK_TRANS_DATE between", value1, value2, "bankTransDate");
            return (Criteria) this;
        }

        public Criteria andBankTransDateNotBetween(String value1, String value2) {
            addCriterion("BANK_TRANS_DATE not between", value1, value2, "bankTransDate");
            return (Criteria) this;
        }

        public Criteria andBankTransTimeIsNull() {
            addCriterion("BANK_TRANS_TIME is null");
            return (Criteria) this;
        }

        public Criteria andBankTransTimeIsNotNull() {
            addCriterion("BANK_TRANS_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andBankTransTimeEqualTo(String value) {
            addCriterion("BANK_TRANS_TIME =", value, "bankTransTime");
            return (Criteria) this;
        }

        public Criteria andBankTransTimeNotEqualTo(String value) {
            addCriterion("BANK_TRANS_TIME <>", value, "bankTransTime");
            return (Criteria) this;
        }

        public Criteria andBankTransTimeGreaterThan(String value) {
            addCriterion("BANK_TRANS_TIME >", value, "bankTransTime");
            return (Criteria) this;
        }

        public Criteria andBankTransTimeGreaterThanOrEqualTo(String value) {
            addCriterion("BANK_TRANS_TIME >=", value, "bankTransTime");
            return (Criteria) this;
        }

        public Criteria andBankTransTimeLessThan(String value) {
            addCriterion("BANK_TRANS_TIME <", value, "bankTransTime");
            return (Criteria) this;
        }

        public Criteria andBankTransTimeLessThanOrEqualTo(String value) {
            addCriterion("BANK_TRANS_TIME <=", value, "bankTransTime");
            return (Criteria) this;
        }

        public Criteria andBankTransTimeLike(String value) {
            addCriterion("BANK_TRANS_TIME like", value, "bankTransTime");
            return (Criteria) this;
        }

        public Criteria andBankTransTimeNotLike(String value) {
            addCriterion("BANK_TRANS_TIME not like", value, "bankTransTime");
            return (Criteria) this;
        }

        public Criteria andBankTransTimeIn(List<String> values) {
            addCriterion("BANK_TRANS_TIME in", values, "bankTransTime");
            return (Criteria) this;
        }

        public Criteria andBankTransTimeNotIn(List<String> values) {
            addCriterion("BANK_TRANS_TIME not in", values, "bankTransTime");
            return (Criteria) this;
        }

        public Criteria andBankTransTimeBetween(String value1, String value2) {
            addCriterion("BANK_TRANS_TIME between", value1, value2, "bankTransTime");
            return (Criteria) this;
        }

        public Criteria andBankTransTimeNotBetween(String value1, String value2) {
            addCriterion("BANK_TRANS_TIME not between", value1, value2, "bankTransTime");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andIsAgreeShortcutIsNull() {
            addCriterion("IS_AGREE_SHORTCUT is null");
            return (Criteria) this;
        }

        public Criteria andIsAgreeShortcutIsNotNull() {
            addCriterion("IS_AGREE_SHORTCUT is not null");
            return (Criteria) this;
        }

        public Criteria andIsAgreeShortcutEqualTo(String value) {
            addCriterion("IS_AGREE_SHORTCUT =", value, "isAgreeShortcut");
            return (Criteria) this;
        }

        public Criteria andIsAgreeShortcutNotEqualTo(String value) {
            addCriterion("IS_AGREE_SHORTCUT <>", value, "isAgreeShortcut");
            return (Criteria) this;
        }

        public Criteria andIsAgreeShortcutGreaterThan(String value) {
            addCriterion("IS_AGREE_SHORTCUT >", value, "isAgreeShortcut");
            return (Criteria) this;
        }

        public Criteria andIsAgreeShortcutGreaterThanOrEqualTo(String value) {
            addCriterion("IS_AGREE_SHORTCUT >=", value, "isAgreeShortcut");
            return (Criteria) this;
        }

        public Criteria andIsAgreeShortcutLessThan(String value) {
            addCriterion("IS_AGREE_SHORTCUT <", value, "isAgreeShortcut");
            return (Criteria) this;
        }

        public Criteria andIsAgreeShortcutLessThanOrEqualTo(String value) {
            addCriterion("IS_AGREE_SHORTCUT <=", value, "isAgreeShortcut");
            return (Criteria) this;
        }

        public Criteria andIsAgreeShortcutLike(String value) {
            addCriterion("IS_AGREE_SHORTCUT like", value, "isAgreeShortcut");
            return (Criteria) this;
        }

        public Criteria andIsAgreeShortcutNotLike(String value) {
            addCriterion("IS_AGREE_SHORTCUT not like", value, "isAgreeShortcut");
            return (Criteria) this;
        }

        public Criteria andIsAgreeShortcutIn(List<String> values) {
            addCriterion("IS_AGREE_SHORTCUT in", values, "isAgreeShortcut");
            return (Criteria) this;
        }

        public Criteria andIsAgreeShortcutNotIn(List<String> values) {
            addCriterion("IS_AGREE_SHORTCUT not in", values, "isAgreeShortcut");
            return (Criteria) this;
        }

        public Criteria andIsAgreeShortcutBetween(String value1, String value2) {
            addCriterion("IS_AGREE_SHORTCUT between", value1, value2, "isAgreeShortcut");
            return (Criteria) this;
        }

        public Criteria andIsAgreeShortcutNotBetween(String value1, String value2) {
            addCriterion("IS_AGREE_SHORTCUT not between", value1, value2, "isAgreeShortcut");
            return (Criteria) this;
        }

        public Criteria andPayGatewayIsNull() {
            addCriterion("PAY_GATEWAY is null");
            return (Criteria) this;
        }

        public Criteria andPayGatewayIsNotNull() {
            addCriterion("PAY_GATEWAY is not null");
            return (Criteria) this;
        }

        public Criteria andPayGatewayEqualTo(String value) {
            addCriterion("PAY_GATEWAY =", value, "payGateway");
            return (Criteria) this;
        }

        public Criteria andPayGatewayNotEqualTo(String value) {
            addCriterion("PAY_GATEWAY <>", value, "payGateway");
            return (Criteria) this;
        }

        public Criteria andPayGatewayGreaterThan(String value) {
            addCriterion("PAY_GATEWAY >", value, "payGateway");
            return (Criteria) this;
        }

        public Criteria andPayGatewayGreaterThanOrEqualTo(String value) {
            addCriterion("PAY_GATEWAY >=", value, "payGateway");
            return (Criteria) this;
        }

        public Criteria andPayGatewayLessThan(String value) {
            addCriterion("PAY_GATEWAY <", value, "payGateway");
            return (Criteria) this;
        }

        public Criteria andPayGatewayLessThanOrEqualTo(String value) {
            addCriterion("PAY_GATEWAY <=", value, "payGateway");
            return (Criteria) this;
        }

        public Criteria andPayGatewayLike(String value) {
            addCriterion("PAY_GATEWAY like", value, "payGateway");
            return (Criteria) this;
        }

        public Criteria andPayGatewayNotLike(String value) {
            addCriterion("PAY_GATEWAY not like", value, "payGateway");
            return (Criteria) this;
        }

        public Criteria andPayGatewayIn(List<String> values) {
            addCriterion("PAY_GATEWAY in", values, "payGateway");
            return (Criteria) this;
        }

        public Criteria andPayGatewayNotIn(List<String> values) {
            addCriterion("PAY_GATEWAY not in", values, "payGateway");
            return (Criteria) this;
        }

        public Criteria andPayGatewayBetween(String value1, String value2) {
            addCriterion("PAY_GATEWAY between", value1, value2, "payGateway");
            return (Criteria) this;
        }

        public Criteria andPayGatewayNotBetween(String value1, String value2) {
            addCriterion("PAY_GATEWAY not between", value1, value2, "payGateway");
            return (Criteria) this;
        }

        public Criteria andCreateTeimIsNull() {
            addCriterion("CREATE_TEIM is null");
            return (Criteria) this;
        }

        public Criteria andCreateTeimIsNotNull() {
            addCriterion("CREATE_TEIM is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTeimEqualTo(String value) {
            addCriterion("CREATE_TEIM =", value, "createTeim");
            return (Criteria) this;
        }

        public Criteria andCreateTeimNotEqualTo(String value) {
            addCriterion("CREATE_TEIM <>", value, "createTeim");
            return (Criteria) this;
        }

        public Criteria andCreateTeimGreaterThan(String value) {
            addCriterion("CREATE_TEIM >", value, "createTeim");
            return (Criteria) this;
        }

        public Criteria andCreateTeimGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_TEIM >=", value, "createTeim");
            return (Criteria) this;
        }

        public Criteria andCreateTeimLessThan(String value) {
            addCriterion("CREATE_TEIM <", value, "createTeim");
            return (Criteria) this;
        }

        public Criteria andCreateTeimLessThanOrEqualTo(String value) {
            addCriterion("CREATE_TEIM <=", value, "createTeim");
            return (Criteria) this;
        }

        public Criteria andCreateTeimLike(String value) {
            addCriterion("CREATE_TEIM like", value, "createTeim");
            return (Criteria) this;
        }

        public Criteria andCreateTeimNotLike(String value) {
            addCriterion("CREATE_TEIM not like", value, "createTeim");
            return (Criteria) this;
        }

        public Criteria andCreateTeimIn(List<String> values) {
            addCriterion("CREATE_TEIM in", values, "createTeim");
            return (Criteria) this;
        }

        public Criteria andCreateTeimNotIn(List<String> values) {
            addCriterion("CREATE_TEIM not in", values, "createTeim");
            return (Criteria) this;
        }

        public Criteria andCreateTeimBetween(String value1, String value2) {
            addCriterion("CREATE_TEIM between", value1, value2, "createTeim");
            return (Criteria) this;
        }

        public Criteria andCreateTeimNotBetween(String value1, String value2) {
            addCriterion("CREATE_TEIM not between", value1, value2, "createTeim");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeIsNull() {
            addCriterion("LAST_UPDATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeIsNotNull() {
            addCriterion("LAST_UPDATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeEqualTo(String value) {
            addCriterion("LAST_UPDATE_TIME =", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeNotEqualTo(String value) {
            addCriterion("LAST_UPDATE_TIME <>", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeGreaterThan(String value) {
            addCriterion("LAST_UPDATE_TIME >", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("LAST_UPDATE_TIME >=", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeLessThan(String value) {
            addCriterion("LAST_UPDATE_TIME <", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeLessThanOrEqualTo(String value) {
            addCriterion("LAST_UPDATE_TIME <=", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeLike(String value) {
            addCriterion("LAST_UPDATE_TIME like", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeNotLike(String value) {
            addCriterion("LAST_UPDATE_TIME not like", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeIn(List<String> values) {
            addCriterion("LAST_UPDATE_TIME in", values, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeNotIn(List<String> values) {
            addCriterion("LAST_UPDATE_TIME not in", values, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeBetween(String value1, String value2) {
            addCriterion("LAST_UPDATE_TIME between", value1, value2, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeNotBetween(String value1, String value2) {
            addCriterion("LAST_UPDATE_TIME not between", value1, value2, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNull() {
            addCriterion("PAY_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNotNull() {
            addCriterion("PAY_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPayTypeEqualTo(String value) {
            addCriterion("PAY_TYPE =", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotEqualTo(String value) {
            addCriterion("PAY_TYPE <>", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThan(String value) {
            addCriterion("PAY_TYPE >", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PAY_TYPE >=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThan(String value) {
            addCriterion("PAY_TYPE <", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThanOrEqualTo(String value) {
            addCriterion("PAY_TYPE <=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLike(String value) {
            addCriterion("PAY_TYPE like", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotLike(String value) {
            addCriterion("PAY_TYPE not like", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeIn(List<String> values) {
            addCriterion("PAY_TYPE in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotIn(List<String> values) {
            addCriterion("PAY_TYPE not in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeBetween(String value1, String value2) {
            addCriterion("PAY_TYPE between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotBetween(String value1, String value2) {
            addCriterion("PAY_TYPE not between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andBankAccountIsNull() {
            addCriterion("BANK_ACCOUNT is null");
            return (Criteria) this;
        }

        public Criteria andBankAccountIsNotNull() {
            addCriterion("BANK_ACCOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andBankAccountEqualTo(String value) {
            addCriterion("BANK_ACCOUNT =", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountNotEqualTo(String value) {
            addCriterion("BANK_ACCOUNT <>", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountGreaterThan(String value) {
            addCriterion("BANK_ACCOUNT >", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountGreaterThanOrEqualTo(String value) {
            addCriterion("BANK_ACCOUNT >=", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountLessThan(String value) {
            addCriterion("BANK_ACCOUNT <", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountLessThanOrEqualTo(String value) {
            addCriterion("BANK_ACCOUNT <=", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountLike(String value) {
            addCriterion("BANK_ACCOUNT like", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountNotLike(String value) {
            addCriterion("BANK_ACCOUNT not like", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountIn(List<String> values) {
            addCriterion("BANK_ACCOUNT in", values, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountNotIn(List<String> values) {
            addCriterion("BANK_ACCOUNT not in", values, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountBetween(String value1, String value2) {
            addCriterion("BANK_ACCOUNT between", value1, value2, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountNotBetween(String value1, String value2) {
            addCriterion("BANK_ACCOUNT not between", value1, value2, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameIsNull() {
            addCriterion("BANK_ACCOUNT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameIsNotNull() {
            addCriterion("BANK_ACCOUNT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameEqualTo(String value) {
            addCriterion("BANK_ACCOUNT_NAME =", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameNotEqualTo(String value) {
            addCriterion("BANK_ACCOUNT_NAME <>", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameGreaterThan(String value) {
            addCriterion("BANK_ACCOUNT_NAME >", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameGreaterThanOrEqualTo(String value) {
            addCriterion("BANK_ACCOUNT_NAME >=", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameLessThan(String value) {
            addCriterion("BANK_ACCOUNT_NAME <", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameLessThanOrEqualTo(String value) {
            addCriterion("BANK_ACCOUNT_NAME <=", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameLike(String value) {
            addCriterion("BANK_ACCOUNT_NAME like", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameNotLike(String value) {
            addCriterion("BANK_ACCOUNT_NAME not like", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameIn(List<String> values) {
            addCriterion("BANK_ACCOUNT_NAME in", values, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameNotIn(List<String> values) {
            addCriterion("BANK_ACCOUNT_NAME not in", values, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameBetween(String value1, String value2) {
            addCriterion("BANK_ACCOUNT_NAME between", value1, value2, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameNotBetween(String value1, String value2) {
            addCriterion("BANK_ACCOUNT_NAME not between", value1, value2, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankCodeIsNull() {
            addCriterion("BANK_CODE is null");
            return (Criteria) this;
        }

        public Criteria andBankCodeIsNotNull() {
            addCriterion("BANK_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andBankCodeEqualTo(String value) {
            addCriterion("BANK_CODE =", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeNotEqualTo(String value) {
            addCriterion("BANK_CODE <>", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeGreaterThan(String value) {
            addCriterion("BANK_CODE >", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeGreaterThanOrEqualTo(String value) {
            addCriterion("BANK_CODE >=", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeLessThan(String value) {
            addCriterion("BANK_CODE <", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeLessThanOrEqualTo(String value) {
            addCriterion("BANK_CODE <=", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeLike(String value) {
            addCriterion("BANK_CODE like", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeNotLike(String value) {
            addCriterion("BANK_CODE not like", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeIn(List<String> values) {
            addCriterion("BANK_CODE in", values, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeNotIn(List<String> values) {
            addCriterion("BANK_CODE not in", values, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeBetween(String value1, String value2) {
            addCriterion("BANK_CODE between", value1, value2, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeNotBetween(String value1, String value2) {
            addCriterion("BANK_CODE not between", value1, value2, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNull() {
            addCriterion("BANK_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNotNull() {
            addCriterion("BANK_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBankNameEqualTo(String value) {
            addCriterion("BANK_NAME =", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotEqualTo(String value) {
            addCriterion("BANK_NAME <>", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThan(String value) {
            addCriterion("BANK_NAME >", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThanOrEqualTo(String value) {
            addCriterion("BANK_NAME >=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThan(String value) {
            addCriterion("BANK_NAME <", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThanOrEqualTo(String value) {
            addCriterion("BANK_NAME <=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLike(String value) {
            addCriterion("BANK_NAME like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotLike(String value) {
            addCriterion("BANK_NAME not like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameIn(List<String> values) {
            addCriterion("BANK_NAME in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotIn(List<String> values) {
            addCriterion("BANK_NAME not in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameBetween(String value1, String value2) {
            addCriterion("BANK_NAME between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotBetween(String value1, String value2) {
            addCriterion("BANK_NAME not between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andIdCardIsNull() {
            addCriterion("ID_CARD is null");
            return (Criteria) this;
        }

        public Criteria andIdCardIsNotNull() {
            addCriterion("ID_CARD is not null");
            return (Criteria) this;
        }

        public Criteria andIdCardEqualTo(String value) {
            addCriterion("ID_CARD =", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotEqualTo(String value) {
            addCriterion("ID_CARD <>", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardGreaterThan(String value) {
            addCriterion("ID_CARD >", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardGreaterThanOrEqualTo(String value) {
            addCriterion("ID_CARD >=", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLessThan(String value) {
            addCriterion("ID_CARD <", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLessThanOrEqualTo(String value) {
            addCriterion("ID_CARD <=", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLike(String value) {
            addCriterion("ID_CARD like", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotLike(String value) {
            addCriterion("ID_CARD not like", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardIn(List<String> values) {
            addCriterion("ID_CARD in", values, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotIn(List<String> values) {
            addCriterion("ID_CARD not in", values, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardBetween(String value1, String value2) {
            addCriterion("ID_CARD between", value1, value2, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotBetween(String value1, String value2) {
            addCriterion("ID_CARD not between", value1, value2, "idCard");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneIsNull() {
            addCriterion("MOBILE_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneIsNotNull() {
            addCriterion("MOBILE_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneEqualTo(String value) {
            addCriterion("MOBILE_PHONE =", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneNotEqualTo(String value) {
            addCriterion("MOBILE_PHONE <>", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneGreaterThan(String value) {
            addCriterion("MOBILE_PHONE >", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneGreaterThanOrEqualTo(String value) {
            addCriterion("MOBILE_PHONE >=", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneLessThan(String value) {
            addCriterion("MOBILE_PHONE <", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneLessThanOrEqualTo(String value) {
            addCriterion("MOBILE_PHONE <=", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneLike(String value) {
            addCriterion("MOBILE_PHONE like", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneNotLike(String value) {
            addCriterion("MOBILE_PHONE not like", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneIn(List<String> values) {
            addCriterion("MOBILE_PHONE in", values, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneNotIn(List<String> values) {
            addCriterion("MOBILE_PHONE not in", values, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneBetween(String value1, String value2) {
            addCriterion("MOBILE_PHONE between", value1, value2, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneNotBetween(String value1, String value2) {
            addCriterion("MOBILE_PHONE not between", value1, value2, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andStorablePanIsNull() {
            addCriterion("STORABLE_PAN is null");
            return (Criteria) this;
        }

        public Criteria andStorablePanIsNotNull() {
            addCriterion("STORABLE_PAN is not null");
            return (Criteria) this;
        }

        public Criteria andStorablePanEqualTo(String value) {
            addCriterion("STORABLE_PAN =", value, "storablePan");
            return (Criteria) this;
        }

        public Criteria andStorablePanNotEqualTo(String value) {
            addCriterion("STORABLE_PAN <>", value, "storablePan");
            return (Criteria) this;
        }

        public Criteria andStorablePanGreaterThan(String value) {
            addCriterion("STORABLE_PAN >", value, "storablePan");
            return (Criteria) this;
        }

        public Criteria andStorablePanGreaterThanOrEqualTo(String value) {
            addCriterion("STORABLE_PAN >=", value, "storablePan");
            return (Criteria) this;
        }

        public Criteria andStorablePanLessThan(String value) {
            addCriterion("STORABLE_PAN <", value, "storablePan");
            return (Criteria) this;
        }

        public Criteria andStorablePanLessThanOrEqualTo(String value) {
            addCriterion("STORABLE_PAN <=", value, "storablePan");
            return (Criteria) this;
        }

        public Criteria andStorablePanLike(String value) {
            addCriterion("STORABLE_PAN like", value, "storablePan");
            return (Criteria) this;
        }

        public Criteria andStorablePanNotLike(String value) {
            addCriterion("STORABLE_PAN not like", value, "storablePan");
            return (Criteria) this;
        }

        public Criteria andStorablePanIn(List<String> values) {
            addCriterion("STORABLE_PAN in", values, "storablePan");
            return (Criteria) this;
        }

        public Criteria andStorablePanNotIn(List<String> values) {
            addCriterion("STORABLE_PAN not in", values, "storablePan");
            return (Criteria) this;
        }

        public Criteria andStorablePanBetween(String value1, String value2) {
            addCriterion("STORABLE_PAN between", value1, value2, "storablePan");
            return (Criteria) this;
        }

        public Criteria andStorablePanNotBetween(String value1, String value2) {
            addCriterion("STORABLE_PAN not between", value1, value2, "storablePan");
            return (Criteria) this;
        }

        public Criteria andValidCodeIsNull() {
            addCriterion("VALID_CODE is null");
            return (Criteria) this;
        }

        public Criteria andValidCodeIsNotNull() {
            addCriterion("VALID_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andValidCodeEqualTo(String value) {
            addCriterion("VALID_CODE =", value, "validCode");
            return (Criteria) this;
        }

        public Criteria andValidCodeNotEqualTo(String value) {
            addCriterion("VALID_CODE <>", value, "validCode");
            return (Criteria) this;
        }

        public Criteria andValidCodeGreaterThan(String value) {
            addCriterion("VALID_CODE >", value, "validCode");
            return (Criteria) this;
        }

        public Criteria andValidCodeGreaterThanOrEqualTo(String value) {
            addCriterion("VALID_CODE >=", value, "validCode");
            return (Criteria) this;
        }

        public Criteria andValidCodeLessThan(String value) {
            addCriterion("VALID_CODE <", value, "validCode");
            return (Criteria) this;
        }

        public Criteria andValidCodeLessThanOrEqualTo(String value) {
            addCriterion("VALID_CODE <=", value, "validCode");
            return (Criteria) this;
        }

        public Criteria andValidCodeLike(String value) {
            addCriterion("VALID_CODE like", value, "validCode");
            return (Criteria) this;
        }

        public Criteria andValidCodeNotLike(String value) {
            addCriterion("VALID_CODE not like", value, "validCode");
            return (Criteria) this;
        }

        public Criteria andValidCodeIn(List<String> values) {
            addCriterion("VALID_CODE in", values, "validCode");
            return (Criteria) this;
        }

        public Criteria andValidCodeNotIn(List<String> values) {
            addCriterion("VALID_CODE not in", values, "validCode");
            return (Criteria) this;
        }

        public Criteria andValidCodeBetween(String value1, String value2) {
            addCriterion("VALID_CODE between", value1, value2, "validCode");
            return (Criteria) this;
        }

        public Criteria andValidCodeNotBetween(String value1, String value2) {
            addCriterion("VALID_CODE not between", value1, value2, "validCode");
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