package yazzyyas.activityapp.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import yazzyyas.activityapp.Activity;

@Dao
public interface ActivityDao {
	@Query("Select * FROM activity")
	List<Activity> getAllActivities();

	@Insert
	void insertActivities(Activity activities);


	@Delete
	void deleteActivities(Activity activities);


	@Update
	void updateActivities(Activity activities);
}
