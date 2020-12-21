package com.smlnskgmail.jaman.checkstyle.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import java.util.Arrays;
import java.util.List;

public class MethodObjectReturnAnnotationCheck extends AbstractCheck {

    private static final String MESSAGE_KEY = "MethodObjectReturnAnnotationCheck";

    private static final List<Integer> EXCLUDED_TYPES = Arrays.asList(
            TokenTypes.LITERAL_BOOLEAN,
            TokenTypes.LITERAL_CHAR,
            TokenTypes.LITERAL_BYTE,
            TokenTypes.LITERAL_SHORT,
            TokenTypes.LITERAL_INT,
            TokenTypes.LITERAL_LONG,
            TokenTypes.LITERAL_DOUBLE,
            TokenTypes.LITERAL_FLOAT,
            TokenTypes.LITERAL_VOID
    );

    @Override
    public void visitToken(DetailAST ast) {
        final DetailAST returnType = ast.findFirstToken(TokenTypes.TYPE);
        if (!EXCLUDED_TYPES.contains(returnType.getFirstChild().getType())) {
            // TODO: add annotations check
            final DetailAST modifiers = ast.findFirstToken(TokenTypes.MODIFIERS);
            if (modifiers.getChildCount(TokenTypes.ANNOTATION) == 0) {
                log(ast.getLineNo(), MESSAGE_KEY);
            }
        }
    }

    @Override
    public int[] getDefaultTokens() {
        return getRequiredTokens();
    }

    @Override
    public int[] getAcceptableTokens() {
        return getRequiredTokens();
    }

    @Override
    public int[] getRequiredTokens() {
        return new int[] {
                TokenTypes.METHOD_DEF,
        };
    }

}
