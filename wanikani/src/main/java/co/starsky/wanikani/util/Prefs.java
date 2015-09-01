package co.starsky.wanikani.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author alliecurry
 */
public final class Prefs {
    private static final String PREF_NAME = "wkpref";
    private static final String KEY_WANIKANI_API = "wkapi";

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(PREF_NAME, 0);
    }

    public static String getWaniKaniApiKey(final Context context) {
        return getPrefs(context).getString(KEY_WANIKANI_API, null);
    }

    public static void setWaniKaniApiKey(final Context context, final String key) {
        getPrefs(context).edit().putString(KEY_WANIKANI_API, key).apply();
    }

}
