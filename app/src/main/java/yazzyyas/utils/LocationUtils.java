package yazzyyas.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import java.io.IOException;
import java.util.List;

public class LocationUtils {

	public static double locationToLatitude(String location, Context context) {
		Geocoder gc = new Geocoder(context);
		List<Address> list = null;
		try {
			list = gc.getFromLocationName(location, 1);
			Address add = list.get(0);
			Log.d("telat", "location");
			return add.getLatitude();
		} catch (IOException e) {
			Log.d("telat", "locationToLatitude: ");
			e.printStackTrace();
		}
		return 0;
	}

	public static double locationToLongitude(String location, Context context) {
		Geocoder gc = new Geocoder(context);
		List<Address> list = null;
		try {
			list = gc.getFromLocationName(location, 1);
			Address add = list.get(0);
			Log.d("telong", "location");
			return add.getLongitude();
		} catch (IOException e) {
			Log.d("telong", "locationToLatitude: ");
			e.printStackTrace();
		}
		return 0;
	}
}
