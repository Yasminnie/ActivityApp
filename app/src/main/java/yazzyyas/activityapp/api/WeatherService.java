package yazzyyas.activityapp.api;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import yazzyyas.activityapp.models.WeatherResponse;

import static yazzyyas.activityapp.api.WeatherApi.API_KEY;

public interface WeatherService {

//	Use metric for response temperature in Celsius instead of Kelvin, it is substracted by 273.15
	@GET("/data/2.5/weather?units=metric&APPID=" + API_KEY)
	Single<WeatherResponse> getWeatherForLocation(@Query("lat") double lat, @Query("lon") double lng);
}
