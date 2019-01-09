package yazzyyas.detailActivity;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import yazzyyas.activityapp.api.WeatherApi;
import yazzyyas.activityapp.api.WeatherService;
import yazzyyas.activityapp.base.BaseViewModel;
import yazzyyas.activityapp.models.Weather;
import yazzyyas.activityapp.models.WeatherResponse;

public class DetailsViewModel extends BaseViewModel {

	private MutableLiveData<WeatherResponse> weatherData = new MutableLiveData<>();
	private WeatherService weatherService;
	private MutableLiveData<String> weatherDescription = new MutableLiveData();
	private MutableLiveData<Float> weatherTemp = new MutableLiveData();
	private MutableLiveData<Float> windSpeed = new MutableLiveData();

	private MutableLiveData<String> iconValue = new MutableLiveData<>();

	public DetailsViewModel(@NonNull Application application) {
		super(application);
		weatherService = WeatherApi.create();
	}

	public LiveData<WeatherResponse> getWeatherData(double latitude, double longitude) {
		weatherService.getWeatherForLocation(latitude, longitude)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(new SingleObserver<WeatherResponse>() {
					@Override
					public void onSubscribe(Disposable d) {
						startLoading();
					}

					@Override
					public void onSuccess(WeatherResponse weatherResponse) {
						List<Weather> weather = weatherResponse.getWeather();

						weatherData.setValue(weatherResponse);
						iconValue.setValue(weather.get(0).getIcon());
						weatherDescription.setValue(weather.get(0).getDescription());
						weatherTemp.setValue(weatherResponse.getMain().getTemp());
						windSpeed.setValue(weatherResponse.getWind().getSpeed());
						stopLoading();
					}

					@Override
					public void onError(Throwable e) {
						startLoading();
						e.printStackTrace();
					}
				});
		return weatherData;
	}

	public MutableLiveData<String> getIconValue() {
		return iconValue;
	}

	public MutableLiveData<String> getWeatherDescription() {
		return weatherDescription;
	}

	public MutableLiveData<Float> getWeatherTemp() {
		return weatherTemp;
	}

	public MutableLiveData<Float> getWindSpeed() {
		return windSpeed;
	}
}
