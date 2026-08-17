package br.com.cmarinho.models;

public enum CashEnum {
    ten(10, "Ten"),
    twenty(20, "Twenty"),
    fifty(50, "Fifty"),
    one_hundred(100, "One Hundred");

    private final int value;
    private final String text;

    CashEnum(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public int value() {
        return this.value;
    }

    public String toText() {
        return this.text;
    }
}
