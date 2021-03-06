package org.wso2.siddhi.core.table.record;


import org.wso2.siddhi.query.api.definition.Attribute;
import org.wso2.siddhi.query.api.expression.AttributeFunction;
import org.wso2.siddhi.query.api.expression.Expression;
import org.wso2.siddhi.query.api.expression.Variable;
import org.wso2.siddhi.query.api.expression.condition.*;
import org.wso2.siddhi.query.api.expression.constant.Constant;

public class BaseConditionVisitor implements ConditionVisitor {


    @Override
    public void beginVisitAnd() {

    }

    @Override
    public void endVisitAnd() {

    }

    @Override
    public void beginVisitAndLeftOperand() {

    }

    @Override
    public void endVisitAndLeftOperand() {

    }

    @Override
    public void beginVisitAndRightOperand() {

    }

    @Override
    public void endVisitAndRightOperand() {

    }

    @Override
    public void beginVisitOr() {

    }

    @Override
    public void endVisitOr() {

    }

    @Override
    public void beginVisitOrLeftOperand() {

    }

    @Override
    public void endVisitOrLeftOperand() {

    }

    @Override
    public void beginVisitOrRightOperand() {

    }

    @Override
    public void endVisitOrRightOperand() {

    }

    @Override
    public void beginVisitNot() {

    }

    @Override
    public void endVisitNot() {

    }

    @Override
    public void beginVisitCompare(Compare.Operator operator) {

    }

    @Override
    public void endVisitCompare(Compare.Operator operator) {

    }

    @Override
    public void beginVisitCompareLeftOperand(Compare.Operator operator) {

    }

    @Override
    public void endVisitCompareLeftOperand(Compare.Operator operator) {

    }

    @Override
    public void beginVisitCompareRightOperand(Compare.Operator operator) {

    }

    @Override
    public void endVisitCompareRightOperand(Compare.Operator operator) {

    }

    @Override
    public void beginVisitIsNull(String streamId) {

    }

    @Override
    public void endVisitIsNull(String streamId) {

    }

    @Override
    public void beginVisitIn(String storeId) {

    }

    @Override
    public void endVisitIn(String storeId) {

    }

    @Override
    public void beginVisitConstant(Object value, Attribute.Type type) {

    }

    @Override
    public void endVisitConstant(Object value, Attribute.Type type) {

    }

    @Override
    public void beginVisitMath(MathOperator mathOperator) {

    }

    @Override
    public void endVisitMath(MathOperator mathOperator) {

    }

    @Override
    public void beginVisitMathLeftOperand(MathOperator mathOperator) {

    }

    @Override
    public void endVisitMathLeftOperand(MathOperator mathOperator) {

    }

    @Override
    public void beginVisitMathRightOperand(MathOperator mathOperator) {

    }

    @Override
    public void endVisitMathRightOperand(MathOperator mathOperator) {

    }

    @Override
    public void beginVisitAttributeFunction(String namespace, String functionName) {

    }

    @Override
    public void endVisitAttributeFunction(String namespace, String functionName) {

    }

    @Override
    public void beginVisitParameterAttributeFunction(int index) {

    }

    @Override
    public void endVisitParameterAttributeFunction(int index) {

    }

    @Override
    public void beginVisitStreamVariable(String id, String streamId, String attributeName, Attribute.Type type) {

    }

    @Override
    public void endVisitStreamVariable(String id, String streamId, String attributeName, Attribute.Type type) {

    }

    @Override
    public void beginVisitStoreVariable(String StoreId, String attributeName, Attribute.Type type) {

    }

    @Override
    public void endVisitStoreVariable(String StoreId, String attributeName, Attribute.Type type) {

    }
}
