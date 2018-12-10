package yazzyyas.activityapp;

import android.os.AsyncTask;

import java.util.List;

import static yazzyyas.activityapp.MainActivity.TASK_DELETE_ACTIVITY;
import static yazzyyas.activityapp.MainActivity.db;

public class ActivityAsyncTask extends AsyncTask<Activity, Void, List<Activity>> {

	public final static int TASK_UPDATE_ACTIVITY = 2;
	public final static int TASK_INSERT_ACTIVITY = 3;

	int taskcode;
	ActivityRecyclerViewAdapter adapter;

	public ActivityAsyncTask(int taskcode, ActivityRecyclerViewAdapter adapter) {
		this.taskcode = taskcode;
		this.adapter = adapter;
	}

	@Override
	protected List<Activity> doInBackground(Activity... activities) {
		switch (taskcode) {
			case TASK_INSERT_ACTIVITY:
				db.activityDao().insertActivities(activities[0]);
				break;
			case TASK_UPDATE_ACTIVITY:
				db.activityDao().updateActivities(activities[0]);
				break;
			case TASK_DELETE_ACTIVITY:
				db.activityDao().deleteActivities(activities[0]);
				break;
		}
		return db.activityDao().getAllActivities();
	}
}
