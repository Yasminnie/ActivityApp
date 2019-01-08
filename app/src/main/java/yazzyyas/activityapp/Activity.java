package yazzyyas.activityapp;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

@Entity(tableName = "activity")
public class Activity implements Serializable {

	@PrimaryKey(autoGenerate = true)
	private Long id;

	@ColumnInfo(name = "activityTitle")
	private String title;
	@ColumnInfo(name = "activityDescription")
	private String description;
	@ColumnInfo(name = "activityLocation")
	private String location;
	private String date;
	private double latitude;
	private double longitude;

	public Activity(String title, String description, String location, String date, double latitude, double longitude) {
		this.title = title;
		this.description = description;
		this.location = location;
		this.date = date;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	protected Activity(Parcel in) {
		if (in.readByte() == 0) {
			id = null;
		} else {
			id = in.readLong();
		}
		title = in.readString();
		description = in.readString();
		location = in.readString();
		date = in.readString();
	}

//	public static final Creator<Activity> CREATOR = new Creator<Activity>() {
//		@Override
//		public Activity createFromParcel(Parcel in) {
//			return new Activity(in);
//		}
//
//		@Override
//		public Activity[] newArray(int size) {
//			return new Activity[size];
//		}
//	};

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

//	@Override
//	public int describeContents() {
//		return 0;
//	}
//
//	@Override
//	public void writeToParcel(Parcel dest, int flags) {
//		if (id == null) {
//			dest.writeByte((byte) 0);
//		} else {
//			dest.writeByte((byte) 1);
//			dest.writeLong(id);
//		}
//		dest.writeString(title);
//		dest.writeString(description);
//		dest.writeString(location);
//		dest.writeString(date);
//		dest.writeDouble(latitude);
//		dest.writeDouble(longitude);
//	}
}
