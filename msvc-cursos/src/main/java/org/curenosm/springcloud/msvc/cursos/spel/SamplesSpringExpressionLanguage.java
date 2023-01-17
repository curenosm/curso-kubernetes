package org.curenosm.springcloud.msvc.cursos.spel;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;

import java.util.ArrayList;
import java.util.List;


// I'm on section 4.1.2 Parser Configuration of Spring Framework documentation
public class SamplesSpringExpressionLanguage {
    public static void test() {
        ExpressionParser parser = new SpelExpressionParser();

        Expression exp = parser.parseExpression("'Hello World'");
        String message = (String) exp.getValue();

        Expression exp1 = parser.parseExpression("'Hello World'.concat('!')");
        String message1 = (String) exp1.getValue();

        // invokes 'getBytes()'
        Expression exp2 = parser.parseExpression("'Hello World'.bytes");
        byte[] bytes = (byte[]) exp2.getValue();

        // invokes 'getBytes().length'
        Expression exp3 = parser.parseExpression("'Hello World'.bytes.length");
        int length = (Integer) exp3.getValue();

        Expression exp4 = parser.parseExpression("new String('hello world').toUpperCase()");
        String message4 = exp4.getValue(String.class);

        Simple simple = new Simple();
        simple.booleanList.add(true);

        EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();

        // "false" is passed in here as a String. SpEL and the conversion service
        // will recognize that it needs to be a Boolean and convert it accordingly.
        parser.parseExpression("booleanList[0]").setValue(context, simple, "false");

        // b is false
        Boolean b = simple.booleanList.get(0);

    }

    static class Simple {
        public List<Boolean> booleanList = new ArrayList<Boolean>();
    }
}
