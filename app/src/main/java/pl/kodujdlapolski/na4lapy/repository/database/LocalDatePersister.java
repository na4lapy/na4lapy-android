package pl.kodujdlapolski.na4lapy.repository.database;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.field.types.DateStringType;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class LocalDatePersister extends DateStringType {

    private static final LocalDatePersister singleton = new LocalDatePersister();
    private static final DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    public static LocalDatePersister getSingleton() {
        return singleton;
    }

    protected LocalDatePersister() {
        super(SqlType.STRING, new Class<?>[] { LocalDate.class });
    }

    @Override
    public Object javaToSqlArg(FieldType fieldType, Object javaObject) {
        LocalDate localDate = (LocalDate)javaObject;
        if (localDate == null) {
            return null;
        } else {
            return localDate.toString(formatter);
        }
    }

    @Override
    public Object sqlArgToJava(FieldType fieldType, Object sqlArg, int columnPos) {
        return LocalDate.parse((String)sqlArg, formatter);
    }
}
