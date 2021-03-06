/*
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.siddhi.core.util.parser;

import org.wso2.siddhi.core.config.ExecutionPlanContext;
import org.wso2.siddhi.core.event.ComplexEventChunk;
import org.wso2.siddhi.core.event.stream.MetaStreamEvent;
import org.wso2.siddhi.core.exception.OperationNotSupportedException;
import org.wso2.siddhi.core.executor.ExpressionExecutor;
import org.wso2.siddhi.core.executor.VariableExpressionExecutor;
import org.wso2.siddhi.core.table.EventTable;
import org.wso2.siddhi.core.table.holder.IndexedEventHolder;
import org.wso2.siddhi.core.util.collection.executor.CollectionExecutor;
import org.wso2.siddhi.core.util.collection.expression.CollectionExpression;
import org.wso2.siddhi.core.util.collection.operator.*;
import org.wso2.siddhi.query.api.expression.Expression;
import org.wso2.siddhi.query.api.expression.Variable;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class OperatorParser {

    public static Operator constructOperator(Object storeEvents, Expression expression,
                                             MatchingMetaInfoHolder matchingMetaInfoHolder,
                                             ExecutionPlanContext executionPlanContext,
                                             List<VariableExpressionExecutor> variableExpressionExecutors,
                                             Map<String, EventTable> eventTableMap, String queryName) {
        if (storeEvents instanceof IndexedEventHolder) {
            CollectionExpression collectionExpression = CollectionExpressionParser.parseCollectionExpression(expression,
                    matchingMetaInfoHolder, (IndexedEventHolder) storeEvents);
            CollectionExecutor collectionExecutor = CollectionExpressionParser.buildCollectionExecutor(collectionExpression,
                    matchingMetaInfoHolder, variableExpressionExecutors, eventTableMap, executionPlanContext, true, queryName);
            return new IndexOperator(collectionExecutor);
        } else if (storeEvents instanceof ComplexEventChunk) {
            ExpressionExecutor expressionExecutor = ExpressionParser.parseExpression(expression,
                    matchingMetaInfoHolder.getMetaStateEvent(), matchingMetaInfoHolder.getCurrentState(), eventTableMap, variableExpressionExecutors, executionPlanContext, false, 0, queryName);
            return new EventChunkOperator(expressionExecutor, matchingMetaInfoHolder.getStoreEventIndex());
        } else if (storeEvents instanceof Map) {
            ExpressionExecutor expressionExecutor = ExpressionParser.parseExpression(expression,
                    matchingMetaInfoHolder.getMetaStateEvent(), matchingMetaInfoHolder.getCurrentState(), eventTableMap, variableExpressionExecutors, executionPlanContext, false, 0, queryName);
            return new MapOperator(expressionExecutor, matchingMetaInfoHolder.getStoreEventIndex());
        } else if (storeEvents instanceof Collection) {
            ExpressionExecutor expressionExecutor = ExpressionParser.parseExpression(expression,
                    matchingMetaInfoHolder.getMetaStateEvent(), matchingMetaInfoHolder.getCurrentState(), eventTableMap, variableExpressionExecutors, executionPlanContext, false, 0, queryName);
            return new CollectionOperator(expressionExecutor, matchingMetaInfoHolder.getStoreEventIndex());
        } else {
            throw new OperationNotSupportedException(storeEvents.getClass() + " is not supported by OperatorParser!");
        }
    }

    private static boolean isTableIndexVariable(MatchingMetaInfoHolder matchingMetaInfoHolder, Expression expression, String indexAttribute) {
        if (expression instanceof Variable) {
            Variable variable = (Variable) expression;
            if (variable.getStreamId() != null) {
                MetaStreamEvent tableStreamEvent = matchingMetaInfoHolder.getMetaStateEvent().getMetaStreamEvent(matchingMetaInfoHolder.getStoreEventIndex());
                if (tableStreamEvent != null) {
                    if ((tableStreamEvent.getInputReferenceId() != null && variable.getStreamId().equals(tableStreamEvent.getInputReferenceId())) ||
                            (tableStreamEvent.getLastInputDefinition().getId().equals(variable.getStreamId()))) {
                        if (Arrays.asList(tableStreamEvent.getLastInputDefinition().getAttributeNameArray()).contains(indexAttribute)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
