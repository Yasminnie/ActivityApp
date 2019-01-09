package yazzyyas.activityapp.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherApi {

	private final static String BASE_URL = "http://api.openweathermap.org/";
	final static String API_KEY = "df9dd46fb4e603b4997ffd6ececa8303";

	public static WeatherService create() {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(BASE_URL)
				.client(createOkHttpClient())
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.build();
		return retrofit.create(WeatherService.class);
	}

	private static OkHttpClient createOkHttpClient() {
		return new OkHttpClient.Builder()
				.addInterceptor(new HttpLoggingInterceptor()
						.setLevel(HttpLoggingInterceptor.Level.BODY))
				.build();
	}

}
