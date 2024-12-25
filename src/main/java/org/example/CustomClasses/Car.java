package org.example.CustomClasses;

import java.util.Comparator;

// Класс "Автомобиль" с использованием паттерна Builder и валидацией
public class Car {
    // Поля класса
    private final int power; // Мощность автомобиля
    private final String model; // Модель автомобиля
    private final int year; // Год производства

    // Конструктор, используемый Builder-ом
    private Car(Builder builder) {
        this.power = builder.power;
        this.model = builder.model;
        this.year = builder.year;
    }

    // Геттеры для получения значений полей
    public int getPower() {
        return power;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    // Внутренний класс Builder для пошагового создания объектов
    public static class Builder {
        private int power;
        private String model;
        private int year;

        // Метод для установки мощности
        public Builder setPower(int power) {
            if (power <= 0) {
                throw new IllegalArgumentException("Мощность должна быть положительным числом.");
            }
            this.power = power;
            return this;
        }

        // Метод для установки модели
        public Builder setModel(String model) {
            if (model == null || model.isEmpty()) {
                throw new IllegalArgumentException("Модель не может быть пустой или null.");
            }
            this.model = model;
            return this;
        }

        // Метод для установки года производства
        public Builder setYear(int year) {
            if (year < 1886 || year > java.time.Year.now().getValue()) {
                throw new IllegalArgumentException("Год производства должен быть в пределах от 1886 до текущего года.");
            }
            this.year = year;
            return this;
        }

        // Метод для создания объекта Car
        public Car build() {
            return new Car(this);
        }
    }

    @Override
    public String toString() {
        return "Car{" +
                "мощность=" + power +
                ", модель='" + model + '\'' +
                ", годПроизводства=" + year +
                '}';
    }
    class CarComparator implements Comparator<Car> {
        @Override
        public int compare(Car c1, Car c2) {
            int yearComparison = Integer.compare(c1.getYear(), c2.getYear());
            if (yearComparison != 0) return yearComparison;

            int modelComparison = c1.getModel().compareTo(c2.getModel());
            if (modelComparison != 0) return modelComparison;

            return Integer.compare(c1.getPower(), c2.getPower());
        }
    }
}