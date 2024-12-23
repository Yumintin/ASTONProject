class MyClass1 {
    String field1;
    int field2;
    int field3;

    public MyClass1(String field1, int field2, int field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

    @Override
    public String toString() {
        return "MyClass{" +
                "field1=" + field1 +
                ", field2=" + field2 +
                ", field3=" + field3 +
                '}';
    }
}