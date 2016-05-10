package pl.kodujdlapolski.na4lapy.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import pl.kodujdlapolski.na4lapy.R;

/**
 * Created by Natalia Wróblewska on 2016-05-10.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class NotLoggedDialog {
    public static AlertDialog get(Context ctx) {
        AlertDialog dialog = new AlertDialog.Builder(ctx).create();

        dialog.setMessage(ctx.getString(R.string.not_logged_in_message));
        dialog.setTitle(R.string.not_logged_in_title);
        dialog.setButton(AlertDialog.BUTTON_POSITIVE,
                ctx.getString(R.string.yes), (dialog1, which) -> {
                    // TODO start login activity
                });
        dialog.setButton(AlertDialog.BUTTON_NEGATIVE,
                ctx.getString(R.string.no), (dialog1, which) -> {
                    dialog.dismiss();
                });
        dialog.setCancelable(true);
        return dialog;
    }
}
