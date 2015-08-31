package co.starsky.wanikani.service;

import co.starsky.wanikani.model.StudyQueue;
import co.starsky.wanikani.model.User;
import co.starsky.wanikani.model.WaniKaniResponse;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Interface which defines calls to the WaniKani API.
 * @author alliecurry
 */
public interface WaniKaniService {

    @GET("/user/{api_key}/user-information")
    void getUser(@Path("api_key") final String apiKey, final Callback<WaniKaniResponse<User>> cb);

    @GET("/user/{api_key}/study-queue")
    void getStudyQueue(@Path("api_key") final String api_key, final Callback<WaniKaniResponse<StudyQueue>> cb);
}
