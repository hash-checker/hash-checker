package com.smlnskgmail.jaman.checkstyle.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class AndroidViewFieldNameCheck extends AbstractCheck {

    private static final String MESSAGE_KEY = "AndroidViewFieldNameCheck";

    @Override
    public void visitToken(DetailAST ast) {
        DetailAST identifier = ast.findFirstToken(TokenTypes.TYPE).findFirstToken(TokenTypes.IDENT);
        if (identifier != null) {
            String fieldClassName = identifier.getText();
            switch (fieldClassName) {
                case "TextView":
                    if (!ast.findFirstToken(TokenTypes.IDENT).getText().startsWith("tv")) {
                        log(ast);
                    }
                    break;
                case "ImageView":
                    if (!ast.findFirstToken(TokenTypes.IDENT).getText().startsWith("iv")) {
                        log(ast);
                    }
                    break;
                case "EditText":
                    if (!ast.findFirstToken(TokenTypes.IDENT).getText().startsWith("et")) {
                        log(ast);
                    }
                    break;
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
                TokenTypes.VARIABLE_DEF,
        };
    }

}
