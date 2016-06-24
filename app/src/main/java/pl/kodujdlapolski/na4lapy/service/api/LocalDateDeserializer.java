package pl.kodujdlapolski.na4lapy.service.api;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

import java.lang.reflect.Type;

public class LocalDateDeserializer implements JsonDeserializer<LocalDate> {
    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return DateTimeFormat.forPattern("yyyy-MM-dd").parseLocalDate(json.getAsString());
    }
}
