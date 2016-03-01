package pl.kodujdlapolski.na4lapy.repository.database;

import javax.inject.Inject;

public class DatabaseServiceImpl implements DatabaseService {

    @Inject
    public DatabaseServiceImpl(DatabaseHelper databaseHelper) {
    }
}
