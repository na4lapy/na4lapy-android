package pl.kodujdlapolski.na4lapy.service.api;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

import org.joda.time.LocalDate;

import java.lang.reflect.Type;
import java.sql.SQLException;

import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.model.Photo;
import pl.kodujdlapolski.na4lapy.service.repository.database.DatabaseHelper;

/**
 * Created by Natalia Wróblewska on 2016-05-09.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class AnimalDeserializer implements JsonDeserializer {
    private Context ctx;

    public AnimalDeserializer(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateDeserializer()).create();
        Animal animal = gson.fromJson(json, Animal.class);
        try {
            ConnectionSource connectionSource = new AndroidConnectionSource(new DatabaseHelper(ctx));
            Dao<Animal, Long> dao = DaoManager.createDao(connectionSource, Animal.class);
            dao.assignEmptyForeignCollection(animal, "photos");

            if (animal.getPhotos() != null) {
                for (JsonElement singleJsonPhoto : json.getAsJsonObject().get("photos").getAsJsonArray()) {
                    Photo photo = gson.fromJson(singleJsonPhoto, Photo.class);
                    animal.getPhotos().add(photo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return animal;
    }
}
