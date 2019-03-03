package lesson3;

import lesson3.creator.ExcelWriter;
import lesson3.creator.PdfWriter;
import lesson3.domain.Human;
import lesson3.util.DataGenerator;

import java.util.List;

public class HomeWork3 {
    private static final int MAX_PEOPLE_COUNT = 30;

    public static void main(String[] args) {
        // Определяем количество людей для генерации
        int peopleCount = DataGenerator.getRandomNumberInRange(1, MAX_PEOPLE_COUNT);

        DataGenerator dataGenerator = new DataGenerator();
        List<Human> humans = dataGenerator.generateHumans(peopleCount);

        new ExcelWriter().write(humans);
        new PdfWriter().write(humans);
    }

}