/*
 * Copyright (C) 2016 Stowarzyszenie Na4Łapy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pl.kodujdlapolski.na4lapy;

import android.app.Application;

public class Na4LapyApp extends Application {

    private Na4LapyComponent na4LapyComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        na4LapyComponent = DaggerNa4LapyComponent.builder()
                .na4LapyModule(new Na4LapyModule(this))
                .build();
    }

    public Na4LapyComponent getComponent() {
        return na4LapyComponent;
    }
}
