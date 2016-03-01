package pl.kodujdlapolski.na4lapy.utils;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.model.Shelter;

/**
 * Klasa generuje plik konfiguracyjny dla biblioteki OrmLite
 * Przy uruchomieniu należy ustawić working directiory na: /project/module/src/main
 */
public class DatabaseConfigUtil extends OrmLiteConfigUtil {
    private static final Class<?>[] classes = new Class[] {
            Animal.class, Shelter.class
    };
    public static void main(String[] args) throws Exception {
        writeConfigFile("ormlite_config.txt", classes);
    }
}
