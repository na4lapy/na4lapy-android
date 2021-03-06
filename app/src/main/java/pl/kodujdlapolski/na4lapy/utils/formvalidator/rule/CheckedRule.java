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

import android.support.annotation.NonNull;
import android.widget.CheckBox;

import pl.kodujdlapolski.na4lapy.R;

public class CheckedRule extends Rule<CheckBox> {

    public CheckedRule(@NonNull CheckBox checkBox, int validationCode) {
        super(checkBox, R.string.validation_error_checked, validationCode);
    }

    @Override
    public boolean validate() {
        return Boolean.TRUE.equals(getView().isChecked());
    }

    @Override
    public void setErrorMessage(String message) {
    }
}
