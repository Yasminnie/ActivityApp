package yazzyyas.addActivity;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Patterns;

import java.util.List;

import yazzyyas.activityapp.Activity;
import yazzyyas.activityapp.ActivityRepository;
import yazzyyas.activityapp.MainActivity;
import yazzyyas.activityapp.base.BaseViewModel;
import yazzyyas.utils.LocationUtils;

public class AddViewModel extends BaseViewModel {

	private MutableLiveData<String> title = new MutableLiveData<>();
	private MutableLiveData<String> description = new MutableLiveData<>();
	private MutableLiveData<String> location = new MutableLiveData<>();
	private MutableLiveData<String> errorMessage = new MutableLiveData<>();

	private ActivityRepository activityRepository;
	private LiveData<List<Activity>> activities;

//	Extra empty constructor to create Instance in runtime
	public AddViewModel(@NonNull Application application) {
		super(application);
	}

	public AddViewModel(@NonNull Application application, Context context) {
		super(application);
		activityRepository = new ActivityRepository(context);
		activities = activityRepository.getAllActivities();
	}

//	This method checks if the input in the AddActivity is valid.
	public boolean isInputFieldsValid() {
		if (title.getValue() == null || title.getValue().isEmpty()) {
			errorMessage.setValue("Please fill in a title.");
			return false;
		}

		if (description.getValue() == null || description.getValue().isEmpty()) {
			errorMessage.setValue("Please fill in a description.");
			return false;
		}

		if (location.getValue() == null || location.getValue().isEmpty()) {
			errorMessage.setValue("Please fill in a valid location.");
			return false;
		}
		return true;
	}

	public LiveData<List<Activity>> getActivities() {
		return activities;
	}

	public void insert(Activity activity) {
		activityRepository.insert(activity);
	}

	public void update(Activity activity) {
		activityRepository.update(activity);
	}

	public void delete(Activity activity) {
		activityRepository.delete(activity);
	}

	public MutableLiveData<String> getTitle() {
		return title;
	}

	public MutableLiveData<String> getDescription() {
		return description;
	}

	public MutableLiveData<String> getLocation() {
		return location;
	}

	public MutableLiveData<String> getErrorMessage() {
		return errorMessage;
	}
}
