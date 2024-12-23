import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args)  {
        List<MyClass1> objects = Arrays.asList(
                new MyClass1("Лада", 22, 1000),
                new MyClass1("Держинский", 23, 23),
                new MyClass1("apple",16, 53)
        );
        List<MyClass2> objects2 = Arrays.asList(
                new MyClass2(765, "Ппупупу", "упуп"),
                new MyClass2(4563, "вФЫв", "Куыфыв"),
                new MyClass2(23423,"Дабдаб", "Есес")
        );


        SortContext<MyClass1> context = new SortContext<>();
        context.setStrategy(new MergeSort<>());
        SortContext<MyClass2> context2 = new SortContext<>();
        context2.setStrategy(new MergeSort<>());

        System.out.println("Сортировка по первому полю:");
        context.executeSort(objects, "field1");
        System.out.println(objects);


        System.out.println("Сортировка по второму полю:");
        context2.executeSort(objects2, "field2");
        System.out.println(objects2);


        System.out.println("\nСортировка по третьему полю:");
        context.executeSort(objects, "field3");
        System.out.println(objects);
    }
}