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