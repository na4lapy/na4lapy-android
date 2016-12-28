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
import android.support.annotation.StringRes;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class Rule<T> {

    private final T view;

    @StringRes
    private final int errorMessageResId;

    private final int validationCode;

    public Rule(@NonNull T view, int errorMessageResId, int validationCode) {
        this.view = checkNotNull(view);
        this.errorMessageResId = errorMessageResId;
        this.validationCode = validationCode;
    }

    public abstract boolean validate();

    public abstract void setErrorMessage(String message);

    public T getView() {
        return view;
    }

    @StringRes
    public int getErrorMessageResourceId() {
        return errorMessageResId;
    }

    public int getValidationCode() {
        return validationCode;
    }
}
