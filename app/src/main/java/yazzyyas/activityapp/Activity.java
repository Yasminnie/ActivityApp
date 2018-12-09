package yazzyyas.activityapp;

import android.arch.persistence.room.PrimaryKey;

import java.sql.Time;
import java.util.Date;

public class Activity {

	@PrimaryKey(autoGenerate = true)
	private Long id;

	private String title;
	private String description;
	private Time time;
	private String location;
	private Date createdDate;

	public Activity(Long id, String title, String description, Time time, String location, Date createdDate) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.time = time;
		this.location = location;
		this.createdDate = createdDate;
	}

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

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}
