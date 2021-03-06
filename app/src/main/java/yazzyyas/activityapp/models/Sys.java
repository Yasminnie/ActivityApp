package yazzyyas.activityapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sys {

		@SerializedName("message")
		@Expose
		private Float message;
		@SerializedName("sunrise")
		@Expose
		private Integer sunrise;
		@SerializedName("sunset")
		@Expose
		private Integer sunset;

		public Float getMessage() {
			return message;
		}

		public void setMessage(Float message) {
			this.message = message;
		}

		public Integer getSunrise() {
			return sunrise;
		}

		public void setSunrise(Integer sunrise) {
			this.sunrise = sunrise;
		}

		public Integer getSunset() {
			return sunset;
		}

		public void setSunset(Integer sunset) {
			this.sunset = sunset;
		}

	}
