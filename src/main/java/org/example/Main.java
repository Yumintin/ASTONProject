package org.example;

public class Main {
    public static void main(String[] args) {
        {
            /* /////////////////////КАК ЧИТАТЬ С ФАЙЛА
            Car[] cars = (Car[]) DataLoader.loadFromFile("car"); // Загрузка данных для Car
            for (Car car : cars) {
                System.out.println(car); // Печатаем данные для проверки
            }
            Book[] books = (Book[]) DataLoader.loadFromFile("book"); // Загрузка данных для Book
            for (Book book : books) {
                System.out.println(book); // Печатаем данные для проверки
            }

            RootVegetable[] rootCrops = (RootVegetable[]) DataLoader.loadFromFile("rootvegetable"); // Загрузка данных для RootVegetable
            for (RootVegetable rootCrop : rootCrops) {
                System.out.println(rootCrop); // Печатаем данные для проверки
            }
             */

            /* ///////////////////////РАНДОМНОЕ ЗАПОЛНЕНИЕ
            Car[] cars = RandomDataGenerator.generateRandomCars(5);
            for (Car car : cars) {
                System.out.println(car); // Печатаем данные для проверки
            }*/
            /* //////////////////////////СОРТИРОВКА
            Car[] cars = {   //создаем массив
                    new Car.Builder().setModel("Ford").setPower(120).setYear(2020).build(),
                    new Car.Builder().setModel("Toyota").setPower(240).setYear(2010).build(),
                    new Car.Builder().setModel("BMW").setPower(300).setYear(2014).build()
            };
         //выбираем компаратор для сравнения
            Comparator<Car> carComparator = Car.getComparator();

            // Создание контекста сортировки и задания стратегии
            SortContext<Car> sortContext = new SortContext<>();
            sortContext.setStrategy(new MergeSort<>());

            // Вывод массива до сортировки
            System.out.println("До сортировки:");
            for (Car car : cars) {
                System.out.println(car); // Печатаем данные для проверки
            }
            // Выполнение сортировки
            sortContext.executeSort(cars, carComparator);

            // Вывод массива после сортировки
            System.out.println("\nПосле сортировки:");
            for (Car car : cars) {
                System.out.println(car); // Печатаем данные для проверки
            }
            /* ////////////////////////////ПОИСК БИНАРНЫЙ
            // Создаем и сортируем массив автомобилей
            Car[] cars = {
                    new Car.Builder().setModel("Ford").setPower(120).setYear(2020).build(),
                    new Car.Builder().setModel("Toyota").setPower(240).setYear(2010).build(),
                    new Car.Builder().setModel("BMW").setPower(300).setYear(2014).build()
            };

            // Компаратор для сравнения объектов Car. В каждом классе лежит гет для своего компоратора
            Comparator<Car> carComparator =Car.getComparator();

            // Поиск автомобиля с точным значением
            Car searchKeyCar = new Car.Builder().setModel("Toyota").setPower(240).setYear(2010).build();
            int carIndex = BinarySearch.binarySearch(cars, searchKeyCar, carComparator);

            System.out.println("Индекс найденного автомобиля: " + carIndex); // Ожидаемый вывод: "1"*/

        }
    }
}
