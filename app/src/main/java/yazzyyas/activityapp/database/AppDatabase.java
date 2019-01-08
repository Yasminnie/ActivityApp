package yazzyyas.activityapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import yazzyyas.activityapp.Activity;

@Database(entities = {Activity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

	public abstract ActivityDao activityDao();

	private final static String NAME_DATABASE = "ActivitiesDbTestj";
	private static AppDatabase sInstance;

	public static AppDatabase getInstance(Context context) {
		if (sInstance == null) {
			sInstance = Room.databaseBuilder(context, AppDatabase.class, NAME_DATABASE).allowMainThreadQueries().build();
		}
		return sInstance;
	}
}
