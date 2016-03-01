package pl.kodujdlapolski.na4lapy.repository.database;

import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

public class DatabaseServiceImpl implements DatabaseService {

    private DatabaseHelper mDatabaseHelper;

    @Inject
    public DatabaseServiceImpl(DatabaseHelper databaseHelper) {
        mDatabaseHelper = databaseHelper;
    }

    @Override
    public <T> T findOneById(Long id, Class clazz) throws SQLException {
        ConnectionSource connectionSource = new AndroidConnectionSource(mDatabaseHelper);
        Dao<T, Long> dao = getDao(clazz, connectionSource);
        T entity = dao.queryForId(id);
        connectionSource.close();
        return entity;
    }

    @Override
    public <T> List<T> findAll(Class clazz) throws SQLException {
        ConnectionSource connectionSource = new AndroidConnectionSource(mDatabaseHelper);
        Dao<T, Long> dao = getDao(clazz, connectionSource);
        List<T> list = dao.queryForAll();
        connectionSource.close();
        return list;
    }

    @Override
    public <T> int save(T entity) throws SQLException {
        ConnectionSource connectionSource = new AndroidConnectionSource(mDatabaseHelper);
        Dao<T, Long> dao = getDao(entity.getClass(), connectionSource);
        return dao.createOrUpdate(entity).getNumLinesChanged();
    }

    @Override
    public <T> int saveAll(final List<T> entities) throws Exception {
        if (entities.isEmpty()) {
            return 0;
        }
        ConnectionSource connectionSource = new AndroidConnectionSource(mDatabaseHelper);
        final Dao<T, Long> dao = getDao(entities.get(0).getClass(), connectionSource);
        final int[] count = {0};
        dao.callBatchTasks(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                for (T entity : entities) {
                    count[0] += dao.createOrUpdate(entity).getNumLinesChanged();
                }
                return null;
            }
        });
        return count[0];
    }

    private <T> Dao<T, Long> getDao(Class clazz, ConnectionSource connectionSource) throws SQLException {
        return DaoManager.createDao(connectionSource, clazz);
    }
}
