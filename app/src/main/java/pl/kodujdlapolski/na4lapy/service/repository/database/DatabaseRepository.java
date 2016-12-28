/*
 *	Copyright 2017 Stowarzyszenie Na4Łapy
 *
 *	Licensed under the Apache License, Version 2.0 (the "License");
 *	you may not use this file except in compliance with the License.
 *	You may obtain a copy of the License at
 *
 *	http://www.apache.org/licenses/LICENSE-2.0
 *
 *	Unless required by applicable law or agreed to in writing, software
 *	distributed under the License is distributed on an "AS IS" BASIS,
 *	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *	See the License for the specific language governing permissions and
 *	limitations under the License.
 */
package pl.kodujdlapolski.na4lapy.service.repository.database;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface DatabaseRepository {
    <T> T findOneById(Long id, Class clazz) throws SQLException;
    <T> List<T> findAll(Class clazz) throws SQLException;
    <T1, T2> List<T1> findAllByForeignId(Long id, Class sourceClass, Class foreignClass) throws SQLException;
    <T> List<T> findAllByFields(Map<String, Object> fieldValues, Class clazz) throws SQLException;
    <T> List<T> findAllByIdList(List<Long> idList, Class clazz) throws SQLException;
    <T> int save(T entity) throws SQLException;
    <T> int saveAll(List<T> entities) throws Exception;
}
