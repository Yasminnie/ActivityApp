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
		List<Address> list;
		try {
			list = gc.getFromLocationName(location, 1);
			Address add = list.get(0);
			return add.getLatitude();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static double locationToLongitude(String location, Context context) {
		Geocoder gc = new Geocoder(context);
		List<Address> list;
		try {
			list = gc.getFromLocationName(location, 1);
			Address add = list.get(0);
			return add.getLongitude();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
