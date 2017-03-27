package pl.kodujdlapolski.na4lapy.ui;

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

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import pl.kodujdlapolski.na4lapy.R;

public class CommonUI {

    public static void onServerError(Throwable t, Activity activity) {
        Toast.makeText(activity, R.string.error_data_cannot_be_loaded, Toast.LENGTH_LONG).show();
        if (t != null && t.getMessage() != null) {
            Log.d(activity.getClass().toString(), t.getMessage());
        }
    }
}
