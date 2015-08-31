package co.starsky.wanikani.service;

import co.starsky.wanikani.BuildConfig;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.converter.GsonConverter;

/**
 * Service utility class for creating and retrieving access to APIs.
 * @author alliecurry
 */
public final class ApiManager {
    private static final String WANIKANI_BASE_URL = "https://www.wanikani.com/api";
    private static String WANIKANI_API_KEY;
    private static WaniKaniService WANIKANI_SERVICE;

    private static Gson getDefaultGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    /** @return The RestAdapter used to make API calls to WaniKani. */
    public static WaniKaniService getWaniKaniService() {
        if (WANIKANI_SERVICE == null) {
            WANIKANI_SERVICE = new RestAdapter.Builder()
                    .setEndpoint(WANIKANI_BASE_URL)
                    .setLog(new AndroidLog("WaniKaniApi"))
                    .setLogLevel(BuildConfig.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
                    .setConverter(new GsonConverter(getDefaultGson()))
                    .build()
                    .create(WaniKaniService.class);
        }
        return WANIKANI_SERVICE;
    }

}
