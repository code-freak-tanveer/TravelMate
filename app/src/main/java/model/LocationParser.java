package model;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Code Freak Tanveer on 22/08/2016.
 */
public interface LocationParser {
    @GET("nearbysearch/json")
    Call<LocationData> getLocations(@QueryMap HashMap<String,String> map);
}
