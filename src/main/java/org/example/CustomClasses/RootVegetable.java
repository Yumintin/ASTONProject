package org.example.CustomClasses;
// Класс "Корнеплод" с использованием паттерна Builder и валидацией
public class RootVegetable {
    // Поля класса
    private final String type; // Тип корнеплода
    private final double weight; // Вес корнеплода
    private final String color; // Цвет корнеплода

    // Конструктор, используемый Builder-ом
    private RootVegetable(Builder builder) {
        this.type = builder.type;
        this.weight = builder.weight;
        this.color = builder.color;
    }

    // Геттеры для получения значений полей
    public String getType() {
        return type;
    }

    public double getWeight() {
        return weight;
    }

    public String getColor() {
        return color;
    }

    // Внутренний класс Builder для пошагового создания объектов
    public static class Builder {
        private String type;
        private double weight;
        private String color;

        // Метод для установки типа корнеплода
        public Builder setType(String type) {
            if (type == null || type.isEmpty()) {
                throw new IllegalArgumentException("Тип корнеплода не может быть пустым или null.");
            }
            this.type = type;
            return this;
        }

        // Метод для установки веса корнеплода
        public Builder setWeight(double weight) {
            if (weight <= 0) {
                throw new IllegalArgumentException("Вес корнеплода должен быть положительным числом.");
            }
            this.weight = weight;
            return this;
        }

        // Метод для установки цвета корнеплода
        public Builder setColor(String color) {
            if (color == null || color.isEmpty()) {
                throw new IllegalArgumentException("Цвет корнеплода не может быть пустым или null.");
            }
            this.color = color;
            return this;
        }

        // Метод для создания объекта RootVegetable
        public RootVegetable build() {
            return new RootVegetable(this);
        }
    }

    @Override
    public String toString() {
        return "RootVegetable{" +
                "тип='" + type + '\'' +
                ", вес=" + weight +
                ", цвет='" + color + '\'' +
                '}';
    }
}
