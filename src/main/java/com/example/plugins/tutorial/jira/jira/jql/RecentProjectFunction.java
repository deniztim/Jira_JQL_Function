package com.example.plugins.tutorial.jira.jira.jql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.atlassian.jira.JiraDataType;
import com.atlassian.jira.JiraDataTypes;
import com.atlassian.jira.jql.operand.QueryLiteral;
import com.atlassian.jira.jql.query.QueryCreationContext;
import com.atlassian.jira.plugin.jql.function.AbstractJqlFunction;
import com.atlassian.jira.util.MessageSet;
import com.atlassian.jira.util.NotNull;
import com.atlassian.query.clause.TerminalClause;
import com.atlassian.query.operand.FunctionOperand;
import com.google.common.collect.Iterables;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.jira.user.UserHistoryItem;
import com.atlassian.jira.user.UserProjectHistoryManager;
import java.util.LinkedList;

import java.util.Collections;
import java.util.List;

/**
 * Echoes the the string passed in as an argument.
 */
@Scanned
public class RecentProjectFunction extends AbstractJqlFunction
{
    private static final Logger log = LoggerFactory.getLogger(RecentProjectFunction.class);

    public MessageSet validate(ApplicationUser searcher, FunctionOperand operand, TerminalClause terminalClause) {
        return validateNumberOfArgs(operand, 1);
    }

    public List<QueryLiteral> getValues(QueryCreationContext queryCreationContext, FunctionOperand operand, TerminalClause terminalClause)
    {
        return Collections.singletonList(new QueryLiteral(operand, Iterables.get(operand.getArgs(), 0)));
    }

    public int getMinimumNumberOfExpectedArguments()
    {
        return 1;
    }

    public JiraDataType getDataType()
    {
        return JiraDataTypes.TEXT;
    }
}