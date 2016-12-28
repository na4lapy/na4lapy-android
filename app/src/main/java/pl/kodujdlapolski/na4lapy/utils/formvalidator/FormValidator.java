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
package pl.kodujdlapolski.na4lapy.utils.formvalidator;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pl.kodujdlapolski.na4lapy.utils.formvalidator.rule.Rule;

public class FormValidator {

    private Context context;
    private Set<Rule> form;

    public FormValidator(Context context) {
        this.context = context;
        form = new HashSet<>();
    }

    public FormValidator addRule(Rule rule) {
        form.add(rule);
        return this;
    }

    public List<ValidationError> validateForm() {
        return Lists.newArrayList(Collections2.transform(getErrorFields(), new Function<Rule, ValidationError>() {

            @Nullable
            @Override
            public ValidationError apply(Rule validationRule) {
                validationRule.setErrorMessage(context.getString(validationRule.getErrorMessageResourceId()));
                return new ValidationError((View) validationRule.getView(), validationRule.getErrorMessageResourceId(), validationRule.getValidationCode());
            }

            @Override
            public boolean equals(Object object) {
                return false;
            }
        }));
    }

    private Set<Rule> getErrorFields() {
        return Sets.filter(form, r -> !r.validate());
    }
}
