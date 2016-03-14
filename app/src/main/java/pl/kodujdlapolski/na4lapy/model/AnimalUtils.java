package pl.kodujdlapolski.na4lapy.model;

import android.content.Context;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import pl.kodujdlapolski.na4lapy.R;

/**
 * Created by Natalia on 2016-03-13.
 */
public class AnimalUtils {

    public static String getAnimalAgeFormatted(Context ctx, Animal animal) {
        GregorianCalendar calendarBirthDate = new GregorianCalendar();
        calendarBirthDate.setTime(new Date(animal.getBirthDate()));

        GregorianCalendar current = new GregorianCalendar();
        current.setTime(new Date(System.currentTimeMillis()));
        int days = daysBetween(current, calendarBirthDate);
        int oneYearInDays = 365;
        if (days < oneYearInDays) {
            int months = days / 30;
            return ctx.getResources().getQuantityString(R.plurals.animal_age_months, months, months);
        } else {
            int years = days / oneYearInDays;
            return ctx.getResources().getQuantityString(R.plurals.animal_age_years, years, years);
        }
    }


    public static int daysBetween(Calendar cal1, Calendar cal2) {
        Calendar cal1a = (Calendar) cal1.clone();
        Calendar cal2a = (Calendar) cal2.clone();
        if (cal1a.get(Calendar.YEAR) == cal2a.get(Calendar.YEAR)) {
            return Math.abs(cal1a.get(Calendar.DAY_OF_YEAR)
                    - cal2a.get(Calendar.DAY_OF_YEAR));
        } else {
            if (cal2a.get(Calendar.YEAR) > cal1a.get(Calendar.YEAR)) {
                // swap them
                Calendar temp = cal1a;
                cal1a = cal2a;
                cal2a = temp;
            }
            int extraDays = 0;

            int dayOneOriginalYearDays = cal1a.get(Calendar.DAY_OF_YEAR);

            while (cal1a.get(Calendar.YEAR) > cal2a.get(Calendar.YEAR)) {
                cal1a.add(Calendar.YEAR, -1);
                // getActualMaximum() important for leap years
                extraDays += cal1a.getActualMaximum(Calendar.DAY_OF_YEAR);
            }

            return extraDays - cal2a.get(Calendar.DAY_OF_YEAR)
                    + dayOneOriginalYearDays;
        }
    }
}
