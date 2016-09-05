package model;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Code Freak Tanveer on 26/08/2016.
 */
public interface CoordinateApi {
    @GET("json")
    Call<MainLocationData> getlocation(@QueryMap HashMap<String,String> place);
}
