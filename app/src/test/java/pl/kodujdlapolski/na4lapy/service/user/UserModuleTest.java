package pl.kodujdlapolski.na4lapy.service.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.kodujdlapolski.na4lapy.service.preferences.PreferencesService;
import pl.kodujdlapolski.na4lapy.user.UserModule;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class UserModuleTest {

    @Mock
    private PreferencesService preferencesService;

    private UserModule userModule;

    @Before
    public void setUp() throws Exception {
        userModule = new UserModule();
    }

    @Test
    public void testProvideRepositoryService() throws Exception {
        // when
        UserService result = userModule.provideUserService(preferencesService);

        // then
        assertNotNull(result);
    }
}