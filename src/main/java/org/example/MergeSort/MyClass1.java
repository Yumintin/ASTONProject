package org.example.MergeSort;

public class MyClass1 implements Sortable {
    private String name;
    private double value;
    private int age;  // Третье поле, по которому нужно сортировать

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public MyClass1(String name, double value, int age) {
        this.name = name;
        this.value = value;
        this.age = age;
    }

    @Override
    public Integer getSortField() {
        return age;  // Возвращаем значение возраста для сортировки
    }

    @Override
    public String toString() {
        return "MyClass1{name='" + name + "', value=" + value + ", age=" + age + '}';
    }
}
