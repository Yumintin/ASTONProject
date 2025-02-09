package org.example.CustomClasses;

import org.example.Comparators.BookComparator;
import org.example.Comparators.CarComparator;
import org.example.MergeSort.Sortable;

import java.util.Comparator;

//Класс "Книга" с использованием паттерна Builder и валидацией
public class Book implements Sortable {
    // Поля класса
    private final String author; // Автор книги
    private final String title; // Название книги
    private final int pages; // Количество страниц

    // Конструктор, используемый Builder-ом
    private Book(Builder builder) {
        this.author = builder.author;
        this.title = builder.title;
        this.pages = builder.pages;
    }

    // Геттеры для получения значений полей
    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public int getPages() {
        return pages;
    }

    // Внутренний класс Builder для пошагового создания объектов
    public static class Builder {
        private String author;
        private String title;
        private int pages;

        // Метод для установки автора
        public Builder setAuthor(String author) {
            if (author == null || author.isEmpty()) {
                throw new IllegalArgumentException("Автор не может быть пустым или null.");
            }
            this.author = author;
            return this;
        }

        // Метод для установки названия книги
        public Builder setTitle(String title) {
            if (title == null || title.isEmpty()) {
                throw new IllegalArgumentException("Название книги не может быть пустым или null.");
            }
            this.title = title;
            return this;
        }

        // Метод для установки количества страниц
        public Builder setPages(int pages) {
            if (pages <= 0 || pages >= 2000) {
                throw new IllegalArgumentException("Количество страниц должно быть положительным числом и в допустимом пределе.");
            }
            this.pages = pages;
            return this;
        }

        // Метод для создания объекта Book
        public Book build() {
            return new Book(this);
        }
    }
    @Override
    public Integer getSortField() {
        return pages;  // Возвращаем значение возраста для сортировки
    }

	@Override
	public String toString() {
		return "Book {"					+								"\n"	+
				"\tавтор = '"			+	author		+	'\''	+	"\n"	+
				"\tназвание = '"		+	title		+	'\''	+	"\n"	+
				"\tстраницы = "			+	pages		+				"\n"	+
				'}';
	}
	
	public static Comparator<Book> getComparator() {
		return new BookComparator();
	}
}