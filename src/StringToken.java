public class StringToken extends Token {
    private String value;

    public StringToken(Sym s, String v) {
        super(s);
        value = v;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return "Symbol : " + symbol + " | valeur : " + value;
    }
}