package pl.kodujdlapolski.na4lapy.utils.formvalidator;

import android.support.annotation.StringRes;
import android.view.View;

import lombok.Data;

@Data
public class ValidationError {

    private final View view;

    @StringRes
    private final int errorStringResId;

    private final int validationCode;

    public ValidationError(View view, int errorStringResId, int validationCode) {
        this.view = view;
        this.errorStringResId = errorStringResId;
        this.validationCode = validationCode;
    }
}
