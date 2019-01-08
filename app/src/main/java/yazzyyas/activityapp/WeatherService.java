package yazzyyas.activityapp;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import yazzyyas.activityapp.models.WeatherResponse;

import static yazzyyas.activityapp.WeatherApi.API_KEY;

public interface WeatherService {

//  http://api.openweathermap.org/data/2.5/weather?lat=5.2&lon=52&units=metric&APPID=df9dd46fb4e603b4997ffd6ececa8303
//	Use metric for response temperature in Celsius instead of Kelvin, it is substracted by 273.15
	@GET("/data/2.5/weather?units=metric&APPID=" + API_KEY)
	Single<WeatherResponse> getWeatherForLocation(@Query("lat") double lat, @Query("lon") double lng);
}
