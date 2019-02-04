package ut.com.example.plugins.tutorial.jira.jira.jql;

import com.atlassian.jira.plugin.jql.function.JqlFunctionModuleDescriptor;
import com.atlassian.jira.web.bean.I18nBean;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.mockito.Mockito;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.atlassian.query.operand.SingleValueOperand;
import com.atlassian.jira.jql.operand.QueryLiteral;
import com.atlassian.jira.util.MessageSet;
import com.atlassian.jira.JiraDataTypes;
import com.atlassian.query.clause.TerminalClauseImpl;
import com.atlassian.query.operand.FunctionOperand;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.example.plugins.tutorial.jira.jira.jql.RecentProjectFunction;


public class RecentProjectFunctionTest
{
    private static final String FUNC_NAME = "funcName";
    protected RecentProjectFunction function;

    @Before
    public void setup() {
        JqlFunctionModuleDescriptor descriptor = mock(JqlFunctionModuleDescriptor.class);
        I18nBean i18nBean = mock(I18nBean.class);

        when(i18nBean.getText(anyString(), anyString(), anyString(), anyString())).thenReturn("Function 'funcName' expected '1' arguments but received '0'.");
        when(descriptor.getI18nBean()).thenReturn(i18nBean);

        function = new RecentProjectFunction();
        function.init(descriptor);
    }

    @Test
    public void testDataType() throws Exception
    {
        assertEquals(JiraDataTypes.TEXT, function.getDataType());
    }

    @Test
    public void testValidateEmptyArguments() throws Exception
    {
        final FunctionOperand functionOperand = new FunctionOperand(FUNC_NAME);
        final MessageSet messageSet = function.validate(null, functionOperand, null);
        assertTrue(messageSet.hasAnyErrors());
        assertEquals("Function 'funcName' expected '1' arguments but received '0'.", messageSet.getErrorMessages().iterator().next());
    }

    @Test
    public void testValidateArguments() throws Exception
    {
        final FunctionOperand functionOperand = new FunctionOperand(FUNC_NAME,"one");
        final MessageSet messageSet = function.validate(null, functionOperand, null);
        assertFalse(messageSet.hasAnyErrors());
    }

    @Test
    public void testGetValues()
    {
        List<QueryLiteral> actualList;

        actualList = function.getValues(null, new FunctionOperand(FUNC_NAME, "one"), null);
        assertEquals(Collections.singletonList(createLiteral("one")), actualList);
    }

    @Test
    public void testGetMinimumNumberOfExpectedArguments()
    {
        assertEquals(1, function.getMinimumNumberOfExpectedArguments());
    }

    private QueryLiteral createLiteral(String value)
    {
        return new QueryLiteral(new SingleValueOperand(value), value);
    }

}
