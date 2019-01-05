package yazzyyas.activityapp;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import yazzyyas.activityapp.database.ActivityDao;
import yazzyyas.activityapp.database.AppDatabase;

public class ActivityRepository {

	private AppDatabase mAppDatabase;
	private ActivityDao activityDao;
	private LiveData<List<Activity>> activities;

	private Executor mExecutor = Executors.newSingleThreadExecutor();

	public ActivityRepository(Context context) {
		mAppDatabase = AppDatabase.getInstance(context);
		activityDao = mAppDatabase.activityDao();
		activities = activityDao.getAllActivities();
	}

	public LiveData<List<Activity>> getAllActivities() {
		return activities;
	}

	public void insert(final Activity activity) {
		mExecutor.execute(new Runnable() {
			@Override
			public void run() {
				activityDao.insertActivities(activity);
			}
		});
	}

	public void update(final Activity activity) {
		mExecutor.execute(new Runnable() {
			@Override
			public void run() {
				activityDao.updateActivities(activity);
			}
		});
	}

	public void delete(final Activity activity) {
		mExecutor.execute(new Runnable() {
			@Override
			public void run() {
				activityDao.deleteActivities(activity);
			}
		});
	}
}
