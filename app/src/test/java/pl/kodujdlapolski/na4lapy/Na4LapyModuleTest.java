package pl.kodujdlapolski.na4lapy;

import android.app.Application;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class Na4LapyModuleTest {

    @Mock
    private Application application;

    private Na4LapyModule na4LapyModule;

    @Before
    public void setUp() throws Exception {
        na4LapyModule = new Na4LapyModule(application);
    }

    @Test
    public void testProvideApplication() throws Exception {
        // when
        Application result = na4LapyModule.provideApplication();

        // then
        assertNotNull(result);
    }
}