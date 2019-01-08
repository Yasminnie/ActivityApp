package yazzyyas.addActivity;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import yazzyyas.activityapp.Activity;
import yazzyyas.activityapp.ActivityRepository;
import yazzyyas.activityapp.base.BaseViewModel;

public class AddViewModel extends BaseViewModel {

	private ActivityRepository activityRepository;
	private LiveData<List<Activity>> activities;

	public AddViewModel(@NonNull Application application) {
		super(application);
	}

	public AddViewModel(@NonNull Application application, Context context) {
		super(application);
		activityRepository = new ActivityRepository(context);
		activities = activityRepository.getAllActivities();
	}

	public LiveData<List<Activity>> getActivities() {
		return activities;
	}

	public void insert (Activity activity) {
		activityRepository.insert(activity);
	}


	public void update (Activity activity) {
		activityRepository.update(activity);
	}

	public void delete (Activity activity) {
		activityRepository.delete(activity);
	}

}
