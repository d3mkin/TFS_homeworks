package lesson3.util;

import lesson3.domain.Address;
import lesson3.domain.FemaleHuman;
import lesson3.domain.Human;
import lesson3.domain.MaleHuman;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DataGenerator {
    private static final String COUNTRIES_FILE_NAME = "Countries.txt";
    private static final String AREAS_FILE_NAME = "Areas.txt";
    private static final String CITIES_FILE_NAME = "Cities.txt";
    private static final String STREETS_FILE_NAME = "Streets.txt";
    private static final String MALE_NAMES_FILE_NAME = "MaleNames.txt";
    private static final String MALE_SURNAMES_FILE_NAME = "MaleSurNames.txt";
    private static final String MALE_PATRONYMICS_FILE_NAME = "MalePatronymics.txt";
    private static final String FEMALE_NAMES_FILE_NAME = "FemaleNames.txt";
    private static final String FEMALE_SURNAMES_FILE_NAME = "FemaleSurNames.txt";
    private static final String FEMALE_PATRONYMICS_FILE_NAME = "FemalePatronymics.txt";
    private static final DateTime MIN_BIRTH_DATE = new DateTime().withDate(1970, 1, 1);
    private static final DateTime MAX_BIRTH_DATE = new DateTime().withDate(2000, 12, 31);
    private static final int MAX_HOUSE_NUMBER = 50;
    private static final int MAX_APARTMENT_NUMBER = 100;
    private static final int MIN_ZIP_CODE_RANGE = 100000;
    private static final int MAX_ZIP_CODE_RANGE = 200000;

    private List<String> countries;
    private List<String> areas;
    private List<String> cities;
    private List<String> streets;
    private List<String> maleNames;
    private List<String> maleSurnames;
    private List<String> malePatronymics;
    private List<String> femaleNames;
    private List<String> femaleSurnames;
    private List<String> femalePatronymics;

    public DataGenerator() {
        this.countries = FilesUtil.readLinesFromFile(COUNTRIES_FILE_NAME);
        this.areas = FilesUtil.readLinesFromFile(AREAS_FILE_NAME);
        this.cities = FilesUtil.readLinesFromFile(CITIES_FILE_NAME);
        this.streets = FilesUtil.readLinesFromFile(STREETS_FILE_NAME);
        this.maleNames = FilesUtil.readLinesFromFile(MALE_NAMES_FILE_NAME);
        this.maleSurnames = FilesUtil.readLinesFromFile(MALE_SURNAMES_FILE_NAME);
        this.malePatronymics = FilesUtil.readLinesFromFile(MALE_PATRONYMICS_FILE_NAME);
        this.femaleNames = FilesUtil.readLinesFromFile(FEMALE_NAMES_FILE_NAME);
        this.femaleSurnames = FilesUtil.readLinesFromFile(FEMALE_SURNAMES_FILE_NAME);
        this.femalePatronymics = FilesUtil.readLinesFromFile(FEMALE_PATRONYMICS_FILE_NAME);
    }

    public List<Human> generateHumans(int count) {
        List<Human> humans = new ArrayList<>();
        int maleCount = getRandomNumberInRange(0, count);
        int femaleCount = count - maleCount;

        for (int i = 0; i < maleCount; i++) {
            String name = getRandomValueFrom(maleNames);
            String surname = getRandomValueFrom(maleSurnames);
            String patronymic = getRandomValueFrom(malePatronymics);
            DateTime birthDate = generateBirthDate();
            String inn = generateInn();
            Address address = new Address(
                    getZipCode(),
                    getRandomValueFrom(countries),
                    getRandomValueFrom(areas),
                    getRandomValueFrom(cities),
                    getRandomValueFrom(streets),
                    getRandomNumberInRange(1, MAX_HOUSE_NUMBER),
                    getRandomNumberInRange(1, MAX_APARTMENT_NUMBER)
            );
            humans.add(new MaleHuman(name, surname, patronymic, birthDate, inn, address));
        }

        for (int i = 0; i < femaleCount; i++) {
            String name = getRandomValueFrom(femaleNames);
            String surname = getRandomValueFrom(femaleSurnames);
            String patronymic = getRandomValueFrom(femalePatronymics);
            DateTime birthDate = generateBirthDate();
            String inn = generateInn();
            Address address = new Address(
                    getZipCode(),
                    getRandomValueFrom(countries),
                    getRandomValueFrom(areas),
                    getRandomValueFrom(cities),
                    getRandomValueFrom(streets),
                    getRandomNumberInRange(1, MAX_HOUSE_NUMBER),
                    getRandomNumberInRange(1, MAX_APARTMENT_NUMBER)
            );
            humans.add(new FemaleHuman(name, surname, patronymic, birthDate, inn, address));
        }

        Collections.shuffle(humans);
        return humans;
    }

    public static int getRandomNumberInRange(int min, int max) {
        Random randomNumber = new Random();
        return randomNumber.nextInt((max - min) + 1) + min;
    }

    private static String getRandomValueFrom(List<String> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("List size must not be empty or null");
        }

        return list.get(getRandomNumberInRange(0, list.size() - 1));
    }

    private static String generateInn() {
        String regionCode = "77";
        String localTaxCode = Integer.toString(getRandomNumberInRange(0, 5)) + Integer.toString(getRandomNumberInRange(0, 9));
        String payerTaxCode = new String();
        for (int i = 0; i < 6; i++) {
            payerTaxCode += Integer.toString(getRandomNumberInRange(0, 9));
        }
        String halfInn = regionCode + localTaxCode + payerTaxCode;

        int[] numbers = new int[halfInn.length()];
        for (int i = 0; i < halfInn.length(); i++) {
            numbers[i] = Character.digit(halfInn.charAt(i), 10);
        }

        return halfInn + calculateTwoCheckDigits(numbers);
    }

    private static String calculateTwoCheckDigits(int[] n) {
        if (n.length < 10) {
            throw new IllegalArgumentException("Digits amount must be equals 10");
        }

        int firstCheckDigit = ((7 * n[0] + 2 * n[1] + 4 * n[2] + 10 * n[3] + 3 * n[4] + 5 * n[5] + 9 * n[6] + 4 * n[7] + 6 * n[8] + 8 * n[9]) % 11) % 10;
        int secondCheckDigit = ((3 * n[0] + 7 * n[1] + 2 * n[2] + 4 * n[3] + 10 * n[4] + 3 * n[5] + 5 * n[6] + 9 * n[7] + 4 * n[8] + 6 * n[9] + 8 * firstCheckDigit) % 11) % 10;
        return firstCheckDigit + "" + secondCheckDigit;
    }

    private static int getZipCode() {
        return getRandomNumberInRange(MIN_ZIP_CODE_RANGE, MAX_ZIP_CODE_RANGE);
    }

    private DateTime generateBirthDate() {
        long diff = MAX_BIRTH_DATE.getMillis() - MIN_BIRTH_DATE.getMillis() + 1;
        return new DateTime(MIN_BIRTH_DATE.getMillis() + (long) (Math.random() * diff));
    }
}
