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