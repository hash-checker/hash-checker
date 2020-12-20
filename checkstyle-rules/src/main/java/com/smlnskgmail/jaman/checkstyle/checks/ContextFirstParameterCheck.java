package com.smlnskgmail.jaman.checkstyle.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class ContextFirstParameterCheck extends AbstractCheck {

    private static final String MESSAGE_KEY = "ContextFirstParameterCheck";

    @Override
    public void visitToken(DetailAST ast) {
        final DetailAST parameters = ast.findFirstToken(TokenTypes.PARAMETERS);
        if (parameters.getChildCount(TokenTypes.PARAMETER_DEF) > 0) {
            boolean hasContext = false;
            int position = 0;
            for (DetailAST detail = parameters.getFirstChild(); detail != null; detail = detail.getNextSibling()) {
                if (detail.getType() == TokenTypes.PARAMETER_DEF) {
                    final DetailAST type = detail.findFirstToken(TokenTypes.TYPE);
                    final DetailAST element = type.findFirstToken(TokenTypes.IDENT);
                    if (element != null && element.getText().equals("Context")) {
                        hasContext = true;
                        break;
                    } else {
                        position++;
                    }
                }
            }
            if (hasContext && position != 0) {
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
                TokenTypes.CTOR_DEF,
                TokenTypes.METHOD_DEF
        };
    }

}
