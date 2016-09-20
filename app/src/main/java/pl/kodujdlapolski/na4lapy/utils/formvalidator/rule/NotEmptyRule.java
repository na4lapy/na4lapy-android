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

package pl.kodujdlapolski.na4lapy.utils.formvalidator.rule;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;

import pl.kodujdlapolski.na4lapy.R;

/**
 * @author Marek Wojtuszkiewicz
 */
public class NotEmptyRule extends Rule<TextInputEditText> {

    private TextInputLayout textInputLayout;

    public NotEmptyRule(@NonNull TextInputEditText textInputEditText, @Nullable TextInputLayout textInputLayout, int validationCode) {
        super(textInputEditText, R.string.validation_error_empty, validationCode);
        this.textInputLayout = textInputLayout;
    }

    @Override
    public boolean validate() {
        String input = getView().getText().toString();
        return input.trim().length() > 0;
    }

    @Override
    public void setErrorMessage(String message) {
        if (textInputLayout != null) {
            textInputLayout.setError(message);
        } else {
            getView().setError(message);
        }
    }
}
