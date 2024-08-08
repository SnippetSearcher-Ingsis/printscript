package printScreen.models.token;

import org.jetbrains.annotations.NotNull;

public record NonValuedToken(TokenType type, Integer column, Integer line) implements Token {

    @NotNull
    @Override
    public TokenType getType() {
        return type;
    }

    @Override
    public String getValue() {
        return null;
    }

    @Override
    public int getColumn() {
        return column;
    }

    @Override
    public int getLine() {
        return line;
    }
}
