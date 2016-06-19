package pl.kodujdlapolski.na4lapy.ui.compliance_level;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

import pl.kodujdlapolski.na4lapy.R;
import pl.kodujdlapolski.na4lapy.ui.preferences.PreferencesActivity;

/**
 * Created by Natalia Wróblewska on 2016-06-19.
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
public class ComplianceLevelDialog {

    public static void showComplianceLevelInfoDialog(Context ctx) {
        AlertDialog.Builder d = new AlertDialog.Builder(ctx);
        AlertDialog dialog = d.create();
        dialog.setButton(AlertDialog.BUTTON_POSITIVE,
                ctx.getString(R.string.ok), (dialog1, which) -> {
                    dialog.dismiss();
                });
        dialog.setButton(AlertDialog.BUTTON_NEUTRAL, ctx.getString(R.string.go_to_preferences), (dialog1, which) -> {
            ctx.startActivity(new Intent(ctx, PreferencesActivity.class));
        });
        dialog.setTitle(R.string.compliance_level_dialog_title);
        dialog.setMessage(ctx.getString(R.string.compliance_level_dialog_message));
        dialog.setCancelable(true);
        dialog.show();
    }

    public static void showNoComplianceLevelYetDialog(Context ctx) {
        AlertDialog.Builder d = new AlertDialog.Builder(ctx);
        AlertDialog dialog = d.create();
        dialog.setButton(AlertDialog.BUTTON_POSITIVE,
                ctx.getString(R.string.yes), (dialog1, which) -> {
                    ctx.startActivity(new Intent(ctx, PreferencesActivity.class));
                });
        dialog.setButton(AlertDialog.BUTTON_NEUTRAL, ctx.getString(R.string.later), (dialog1, which) -> {
            dialog.dismiss();
        });
        dialog.setTitle(R.string.no_compliance_level_dialog_title);
        dialog.setMessage(ctx.getString(R.string.no_compliance_level_dialog_message));
        dialog.setCancelable(true);
        dialog.show();
    }
}
