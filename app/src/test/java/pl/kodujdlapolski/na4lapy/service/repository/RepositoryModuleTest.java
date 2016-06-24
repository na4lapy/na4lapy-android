package pl.kodujdlapolski.na4lapy.service.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.kodujdlapolski.na4lapy.service.api.ApiService;
import pl.kodujdlapolski.na4lapy.service.preferences.PreferencesService;
import pl.kodujdlapolski.na4lapy.service.repository.database.DatabaseRepository;
import pl.kodujdlapolski.na4lapy.service.user.UserService;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class RepositoryModuleTest {

    @Mock
    private ApiService apiService;

    @Mock
    private DatabaseRepository databaseRepository;

    @Mock
    private UserService userService;

    @Mock
    private PreferencesService preferencesService;

    private RepositoryModule repositoryModule;

    @Before
    public void setUp() throws Exception {
        repositoryModule = new RepositoryModule();
    }

    @Test
    public void testProvideRepositoryService() throws Exception {
        // when
        RepositoryService result = repositoryModule.provideRepositoryService(apiService, databaseRepository, preferencesService, userService);

        // then
        assertNotNull(result);
    }
}