package pl.kodujdlapolski.na4lapy.repository.database;

import java.sql.SQLException;
import java.util.List;

public interface DatabaseService {
    <T> T findOneById(Long id, Class clazz) throws SQLException;
    <T> List<T> findAll(Class clazz) throws SQLException;
    <T> int save(T entity) throws SQLException;
    <T> int saveAll(List<T> entities) throws Exception;
}
