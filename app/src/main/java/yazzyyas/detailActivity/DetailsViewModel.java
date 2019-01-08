package yazzyyas.detailActivity;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import yazzyyas.activityapp.WeatherApi;
import yazzyyas.activityapp.WeatherService;
import yazzyyas.activityapp.base.BaseViewModel;
import yazzyyas.activityapp.models.WeatherResponse;

public class DetailsViewModel extends BaseViewModel {

	private MutableLiveData<WeatherResponse> weatherData = new MutableLiveData<>();
	private WeatherService weatherService;


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

					}

					@Override
					public void onSuccess(WeatherResponse weatherResponse) {
						weatherData.setValue(weatherResponse);
					}

					@Override
					public void onError(Throwable e) {
						e.printStackTrace();
					}
				});
		return weatherData;
	}
}
