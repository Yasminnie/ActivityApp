package yazzyyas.detailActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import yazzyyas.activityapp.Activity;
import yazzyyas.activityapp.R;
import yazzyyas.activityapp.base.BaseActivity;
import yazzyyas.activityapp.databinding.ActivityDetailBinding;

import static yazzyyas.activityapp.MainActivity.EXTRA_ACTIVITY;

public class DetailsActivity extends BaseActivity<ActivityDetailBinding, DetailsViewModel> implements OnMapReadyCallback {

	public static final int DEFAULTZOOM = 12;
	Activity activity;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

//		if (getIntent().getExtras().getParcelable(EXTRA_ACTIVITY) != null) {
		if (getIntent().getExtras().getSerializable(EXTRA_ACTIVITY) != null) {
//			activity = getIntent().getExtras().getParcelable(EXTRA_ACTIVITY);
			activity = (Activity) getIntent().getExtras().getSerializable(EXTRA_ACTIVITY);
			SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map);
			mapFragment.getMapAsync(this);
			viewModel.getWeatherData(activity.getLatitude(), activity.getLongitude());
			initObservers();
		}
	}

	@Override
	public void onMapReady(GoogleMap googleMap) {
		// Add a marker in Sydney, Australia,
		// and move the map's camera to the same location.

		Log.d("yastest", "onMapReady: lat " + activity.getLatitude() + " long " + activity.getLongitude());

		LatLng location = new LatLng(activity.getLatitude(), activity.getLongitude());
		googleMap.addMarker(new MarkerOptions().position(location)
				.title(activity.getTitle()));
		googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, DEFAULTZOOM));
	}

	private void initObservers() {

	}

	@Override
	protected Integer getLayoutId() {
		return R.layout.activity_detail;
	}

	@Override
	protected void initViewModelBinding() {
		binding.setViewModel(viewModel);
	}

	@Override
	protected Class<DetailsViewModel> getVMClass() {
		return DetailsViewModel.class;
	}
}
