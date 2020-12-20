package com.smlnskgmail.jaman.checkstyle.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.utils.TokenUtil;

public class UtilityClassPrivateConstructorCheck extends AbstractCheck {

    private static final String MESSAGE_KEY = "UtilityClassPrivateConstructorCheck";

    @Override
    public void visitToken(DetailAST ast) {
        if (ast.findFirstToken(TokenTypes.IDENT).getText().endsWith("Utils")) {
            final DetailAST objBlock = ast.findFirstToken(TokenTypes.OBJBLOCK);
            if (objBlock.getChildCount(TokenTypes.CTOR_DEF) == 0) {
                log(ast);
            } else {
                TokenUtil.forEachChild(
                        objBlock,
                        TokenTypes.CTOR_DEF,
                        detailAST -> {
                            TokenUtil.forEachChild(
                                    detailAST,
                                    TokenTypes.MODIFIERS,
                                    modifiers -> {
                                        if (!modifiers.branchContains(TokenTypes.LITERAL_PRIVATE)) {
                                            log(ast);
                                        }
                                    }
                            );
                        }
                );
            }
        }
    }

    private void log(DetailAST ast) {
        log(ast.getLineNo(), MESSAGE_KEY);
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
                TokenTypes.CLASS_DEF,
        };
    }

}
