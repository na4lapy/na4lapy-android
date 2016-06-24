package pl.kodujdlapolski.na4lapy.service.repository.database;

import android.app.Application;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class DatabaseModuleTest {

    @Mock
    private DatabaseHelper databaseHelper;

    @Mock
    private Application application;

    private DatabaseModule databaseModule;

    @Before
    public void setUp() throws Exception {
        databaseModule = new DatabaseModule();
    }

    @Test
    public void testProvideDatabaseRepository() throws Exception {
        // when
        DatabaseRepository result = databaseModule.provideDatabaseRepository(databaseHelper);

        // then
        assertNotNull(result);
    }

    @Test
    public void testProvideDatabaseHelper() throws Exception {
    }
}