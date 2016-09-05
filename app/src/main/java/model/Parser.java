package model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Code Freak Tanveer on 14/08/2016.
 */
public interface Parser  {

@GET("{lat},{longitude}?daily")
Call<WeatherApi> getWeatherCondition(@Path("lat") double lat,@Path("longitude") double longitude);
}
