package pl.kodujdlapolski.na4lapy.utils.formvalidator.rule;

import android.widget.CheckBox;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * @author Marek Wojtuszkiewicz
 */
@RunWith(MockitoJUnitRunner.class)
public class CheckedRuleTest {

    @Mock
    private CheckBox checkBox;

    private CheckedRule checkedRule;

    @Before
    public void setUp() throws Exception {
        checkedRule = new CheckedRule(checkBox, 0);
    }

    @Test
    public void testValidateSuccess() throws Exception {
        // given
        when(checkBox.isChecked()).thenReturn(true);

        // when
        boolean result = checkedRule.validate();

        // then
        assertTrue(result);
    }

    @Test
    public void testValidateFail() throws Exception {
        // given
        when(checkBox.isChecked()).thenReturn(false);

        // when
        boolean result = checkedRule.validate();

        // then
        assertFalse(result);
    }
}