package org.example.files;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.CustomClasses.Book;
import org.example.CustomClasses.Car;
import org.example.CustomClasses.RootVegetable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XlsFileToClassObject {

    public static List<Car> excelDataToListOfObjets_Car(String fileLocation)
            throws IOException {
        FileInputStream file = new FileInputStream(new File(fileLocation));
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);
        List<Car> carData = new ArrayList<Car>();
        DataFormatter dataFormatter = new DataFormatter();
        for (int n = 1; n < sheet.getPhysicalNumberOfRows(); n++) {
            Row row = sheet.getRow(n);

            int i = row.getFirstCellNum();

            Car car = new Car.Builder()
                            .setPower(Integer.parseInt(dataFormatter.formatCellValue(row.getCell(i))))
                            .setModel(dataFormatter.formatCellValue(row.getCell(++i)))
                            .setYear(Integer.parseInt(dataFormatter.formatCellValue(row.getCell(++i))))
                            .build();

            carData.add(car);
        }
        return carData;
    }

    public static List<Book> excelDataToListOfObjets_Book(String fileLocation)
            throws IOException {
        FileInputStream file = new FileInputStream(new File(fileLocation));
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);
        List<Book> bookData = new ArrayList<Book>();
        DataFormatter dataFormatter = new DataFormatter();
        for (int n = 1; n < sheet.getPhysicalNumberOfRows(); n++) {
            Row row = sheet.getRow(n);

            int i = row.getFirstCellNum();

            Book book = new Book.Builder()
                    .setAuthor(dataFormatter.formatCellValue(row.getCell(i)))
                    .setTitle(dataFormatter.formatCellValue(row.getCell(++i)))
                    .setPages(Integer.parseInt(dataFormatter.formatCellValue(row.getCell(++i))))
                    .build();

            bookData.add(book);
        }
        return bookData;
    }

    public static List<RootVegetable> excelDataToListOfObjets_RootVegitables(String fileLocation)
            throws IOException {
        FileInputStream file = new FileInputStream(new File(fileLocation));
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);
        List<RootVegetable> rootVegitablesData = new ArrayList<RootVegetable>();
        DataFormatter dataFormatter = new DataFormatter();
        for (int n = 1; n < sheet.getPhysicalNumberOfRows(); n++) {
            Row row = sheet.getRow(n);

            int i = row.getFirstCellNum();

            RootVegetable veggi = new RootVegetable.Builder()
                    .setType(dataFormatter.formatCellValue(row.getCell(i)))
                    .setWeight(Integer.parseInt(dataFormatter.formatCellValue(row.getCell(++i))))
                    .setColor(dataFormatter.formatCellValue(row.getCell(++i)))
                    .build();

            rootVegitablesData.add(veggi);
        }
        return rootVegitablesData;
    }

}
