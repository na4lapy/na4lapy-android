package pl.kodujdlapolski.na4lapy.service.repository.database;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import pl.kodujdlapolski.na4lapy.model.Animal;
import pl.kodujdlapolski.na4lapy.model.BaseEntity;
import pl.kodujdlapolski.na4lapy.model.Shelter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DaoManager.class})
public class DatabaseRepositoryImplTest {

    @Mock
    private DatabaseHelper databaseHelper;

    @Mock
    private Dao<Object, Long> dao;

    @Mock
    private QueryBuilder<Object, Long> queryBuilder;

    @Mock
    private Where<Object, Long> where;

    @Mock
    private ConnectionSource connectionSource;

    @Captor
    private ArgumentCaptor<Callable<Void>> argumentCaptor;

    private DatabaseRepositoryImpl databaseRepository;

    @Before
    public void setUp() throws Exception {
        databaseRepository = new DatabaseRepositoryImpl(databaseHelper);
        mockStatic(DaoManager.class);
    }

    @Test
    public void testFindOneById() throws Exception {
        // given
        Long id = 1L;
        Animal animal = new Animal();
        animal.setId(id);

        DatabaseRepositoryImpl spied = spy(databaseRepository);
        doReturn(dao).when(spied).getDao(any(Class.class), any(ConnectionSource.class));
        when(dao.queryForId(id)).thenReturn(animal);

        // when
        BaseEntity result = spied.findOneById(id, animal.getClass());

        // then
        verify(dao).queryForId(id);
        assertNotNull(result);
        assertEquals(animal.getClass(), result.getClass());
        assertEquals(id, animal.getId());
    }

    @Test
    public void testFindAll() throws Exception {
        // given
        Class clazz = Animal.class;
        List list = Lists.newArrayList(new Animal());

        DatabaseRepositoryImpl spied = spy(databaseRepository);
        doReturn(dao).when(spied).getDao(any(Class.class), any(ConnectionSource.class));
        when(dao.queryForAll()).thenReturn(list);

        // when
        List<? extends BaseEntity> result = spied.findAll(clazz);

        // then
        verify(dao).queryForAll();
        assertNotNull(result);
        assertEquals(list.size(), result.size());
        assertEquals(clazz, result.get(0).getClass());
    }

    @Test
    public void testFindAllByForeignId() throws Exception {
        // given
        Class sourceClass = Animal.class;
        Class foreignClass = Shelter.class;
        Long foreignId = 123L;
        List list = Lists.newArrayList(new Animal());

        DatabaseRepositoryImpl spied = spy(databaseRepository);
        doReturn(dao).when(spied).getDao(any(Class.class), any(ConnectionSource.class));
        when(dao.queryBuilder()).thenReturn(queryBuilder);
        when(queryBuilder.where()).thenReturn(where);
        when(queryBuilder.join(any(QueryBuilder.class))).thenReturn(queryBuilder);
        when(queryBuilder.query()).thenReturn(list);

        // when
        List<? extends BaseEntity> result = spied.findAllByForeignId(foreignId, sourceClass, foreignClass);

        // then
        verify(dao, times(2)).queryBuilder();
        assertNotNull(result);
        assertEquals(list.size(), result.size());
        assertEquals(sourceClass, result.get(0).getClass());
    }

    @Test
    public void testFindAllByFields() throws Exception {
        // given
        List list = Lists.newArrayList(new Animal());
        Map<String, Object> map = Maps.newHashMap();

        DatabaseRepositoryImpl spied = spy(databaseRepository);
        doReturn(dao).when(spied).getDao(any(Class.class), any(ConnectionSource.class));
        when(dao.queryForFieldValues(map)).thenReturn(list);

        // when
        List<? extends BaseEntity> result = spied.findAllByFields(map, Animal.class);

        // then
        verify(dao).queryForFieldValues(map);
        assertNotNull(result);
        assertEquals(list.size(), result.size());
    }

    @Test
    public void testSave() throws Exception {
        // given
        Animal animal = new Animal();
        Dao.CreateOrUpdateStatus status = new Dao.CreateOrUpdateStatus(true, false, 1);

        DatabaseRepositoryImpl spied = spy(databaseRepository);
        doReturn(dao).when(spied).getDao(any(Class.class), any(ConnectionSource.class));
        when(dao.createOrUpdate(animal)).thenReturn(status);

        // when
        int result =  spied.save(animal);

        // then
        verify(dao).createOrUpdate(animal);
        assertEquals(1, result);
    }

    @Test
    public void testSaveAll() throws Exception {
        // given
        List<Animal> animals = Lists.newArrayList(new Animal(), new Animal(), new Animal());
        Dao.CreateOrUpdateStatus status = new Dao.CreateOrUpdateStatus(true, false, 1);

        DatabaseRepositoryImpl spied = spy(databaseRepository);
        doReturn(dao).when(spied).getDao(any(Class.class), any(ConnectionSource.class));
        when(dao.createOrUpdate(any(Animal.class))).thenReturn(status);
        when(dao.callBatchTasks(any(Callable.class))).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((Callable<Void>) invocation.getArguments()[0]).call();
                return null;
            }
        });

        // when
        int result = spied.saveAll(animals);

        // then
        verify(dao, times(animals.size())).createOrUpdate(any(Animal.class));
        assertEquals(animals.size(), result);
        verify(dao).callBatchTasks(argumentCaptor.capture());
    }

    @Test
    public void testSaveAllShouldReturnZero() throws Exception {
        // given
        List<Animal> list = Lists.newArrayList();
        DatabaseRepositoryImpl spied = spy(databaseRepository);
        doReturn(dao).when(spied).getDao(any(Class.class), any(ConnectionSource.class));

        // when
        int result = spied.saveAll(list);

        // then
        assertEquals(list.size(), result);
        verifyZeroInteractions(dao);
    }

    @Test
    public void testGetDao() throws Exception {
        // given
        Class clazz = Animal.class;
        given(DaoManager.createDao(connectionSource, clazz)).willReturn(dao);

        // when
        Dao<Object, Long> result = databaseRepository.getDao(clazz, connectionSource);

        // then
        verifyStatic();
        assertNotNull(result);
    }
}