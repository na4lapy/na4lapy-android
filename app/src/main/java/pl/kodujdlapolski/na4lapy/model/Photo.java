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

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import pl.kodujdlapolski.na4lapy.BuildConfig;

@Getter
@Setter
public class Photo implements Serializable {
    private Long id;
    private String fileName;
    private Boolean profil;

    private Animal animal;

    public String getFullFileName(){
        return BuildConfig.BASE_FILES_URL + fileName;
    }

}
