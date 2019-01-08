package yazzyyas.activityapp;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import yazzyyas.activityapp.base.BaseViewModel;
import yazzyyas.activityapp.models.WeatherResponse;

public class MainViewModel extends BaseViewModel {

	public MainViewModel(@NonNull Application application) {
		super(application);
	}
}
