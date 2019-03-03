package lesson3.creator;

import lesson3.domain.Sex;
import org.joda.time.DateTime;
import org.joda.time.Period;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractWriter {

    private final static String[] HEADERS = {
            "Имя",
            "Фамилия",
            "Отчество",
            "Возраст",
            "Пол",
            "Дата рождения",
            "ИНН",
            "Почтовый индекс",
            "Страна",
            "Область",
            "Город",
            "Улица",
            "Дом",
            "Кв"
    };

    protected List<String> getHeaders() {
        return Arrays.asList(HEADERS);
    }

    protected static int calculateAge(DateTime dateTime) {
        return new Period(dateTime.toLocalDate(), DateTime.now().toLocalDate()).getYears();
    }

    protected static String format(Sex sex) {
        switch (sex) {
            case MALE:
                return "М";
            case FEMALE:
                return "Ж";
            default:
                throw new RuntimeException("Unknown sex: " + sex);
        }
    }

    protected static String format(DateTime dateTime) {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return df.format(dateTime.toDate());
    }
}
