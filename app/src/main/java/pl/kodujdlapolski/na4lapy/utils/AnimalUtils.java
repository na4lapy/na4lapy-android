package pl.kodujdlapolski.na4lapy.utils;

import android.content.Context;
import android.content.Intent;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.model.type.ActivityAnimal;
import pl.kodujdlapolski.na4lapy.model.type.Gender;

/**
 * Created by Natalia Wróblewska on 2016-03-13.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
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
            return String.format(ctx.getString(R.string.months), months);
        } else {
            int years = days / oneYearInDays;
            return String.format(ctx.getString(R.string.years), years);
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

    public static int getMatchingLvlImage(Animal animal) {
        switch (animal.getMatchLevel()) {
            case 0:
                return R.drawable.vector_drawable_procent_0;
            case 1:
                return R.drawable.vector_drawable_procent_20;
            case 2:
                return R.drawable.vector_drawable_procent_40;
            case 3:
                return R.drawable.vector_drawable_procent_60;
            case 4:
                return R.drawable.vector_drawable_procent_80;
            case 5:
                return R.drawable.vector_drawable_procent_100;
            default:
                return -1;
        }
    }

    public static int getAddToFavFabImage(Animal animal) {
        return animal.isFavourite() ? R.drawable.ic_favorite_white : R.drawable.ic_favorite_border_white;
    }

    public static int getSizeImage(Animal animal) {
        switch (animal.getSize()) {
            case SMALL:
                return R.drawable.vector_drawable_przegladanie_maly;
            case MEDIUM:
                return R.drawable.vector_drawable_przegladanie_sredni;
            case LARGE:
                return R.drawable.vector_drawable_przegladanie_duzy;
        }
        return -1;
    }

    public static int getGenderImage(Animal animal) {
        return animal.getGender() == Gender.FEMALE ? R.drawable.vector_drawable_przegladanie_suczka : R.drawable.vector_drawable_przegladanie_samiec;
    }

    public static int getActivityImage(Animal animal) {
        return animal.getActivity() == ActivityAnimal.HIGH ? R.drawable.vector_drawable_przegladanie_aktywny : R.drawable.vector_drawable_przegladanie_domator;
    }

    public static Intent getShareIntent(Animal animal) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, animal.getName());
        return sharingIntent;
    }
}
