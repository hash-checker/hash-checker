package com.smlnskgmail.jaman.checkstyle.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.utils.TokenUtil;

import java.util.Arrays;
import java.util.List;

public class MethodParametersAnnotationCheck extends AbstractCheck {

    private static final List<Integer> EXCLUDED_TYPES = Arrays.asList(
            TokenTypes.LITERAL_BOOLEAN,
            TokenTypes.LITERAL_CHAR,
            TokenTypes.LITERAL_BYTE,
            TokenTypes.LITERAL_SHORT,
            TokenTypes.LITERAL_INT,
            TokenTypes.LITERAL_LONG,
            TokenTypes.LITERAL_DOUBLE,
            TokenTypes.LITERAL_FLOAT
    );

    private static final String MESSAGE_KEY = "MethodParameterAnnotationCheck";

    @Override
    public void visitToken(DetailAST ast) {
        final DetailAST parameters = ast.findFirstToken(TokenTypes.PARAMETERS);
        final int parametersCount = parameters.getChildCount(TokenTypes.PARAMETER_DEF);
        if (parametersCount != 0) {
            TokenUtil.forEachChild(
                    parameters,
                    TokenTypes.PARAMETER_DEF,
                    detailAST -> {
                        final DetailAST modifiers = detailAST.findFirstToken(TokenTypes.MODIFIERS);
                        if (isInvalidParameter(detailAST)) {
                            log(modifiers.getLineNo(), MESSAGE_KEY);
                        }
                    }
            );
        }
    }

    private boolean isInvalidParameter(DetailAST parameterAST) {
        final DetailAST modifiers = parameterAST.findFirstToken(TokenTypes.MODIFIERS);
        // TODO: add annotations check
        return modifiers != null
                && !EXCLUDED_TYPES.contains(parameterAST.findFirstToken(TokenTypes.TYPE).getFirstChild().getType())
                && modifiers.findFirstToken(TokenTypes.ANNOTATION) == null;
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
        return new int[]{
                TokenTypes.METHOD_DEF,
        };
    }

}
