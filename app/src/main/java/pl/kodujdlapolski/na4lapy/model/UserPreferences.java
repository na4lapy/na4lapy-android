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
package pl.kodujdlapolski.na4lapy.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPreferences {

    private boolean typeDog;
    private boolean typeCat;
    private boolean typeOther;
    private boolean genderWoman;
    private boolean genderMan;
    private int ageMin;
    private int ageMax;
    private boolean sizeSmall;
    private boolean sizeMedium;
    private boolean sizeLarge;
    private boolean activityLow;
    private boolean activityHigh;

    public UserPreferences() {
        typeDog = false;
        typeCat = false;
        typeOther = false;
        genderWoman = false;
        genderMan = false;
        ageMin = 0;
        ageMax = 20;
        sizeSmall = false;
        sizeMedium = false;
        sizeLarge = false;
        activityLow = false;
        activityHigh = false;
    }
}
