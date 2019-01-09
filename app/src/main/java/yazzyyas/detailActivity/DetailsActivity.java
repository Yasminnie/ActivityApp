package yazzyyas.detailActivity;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import yazzyyas.activityapp.Activity;
import yazzyyas.activityapp.R;
import yazzyyas.activityapp.base.BaseActivity;
import yazzyyas.activityapp.databinding.ActivityDetailBinding;

import static yazzyyas.activityapp.MainActivity.EXTRA_ACTIVITY;

public class DetailsActivity extends BaseActivity<ActivityDetailBinding, DetailsViewModel> implements OnMapReadyCallback {

	public static final int DEFAULTZOOM = 15;
	public static final String GRADEN = " °C";
	public static final String WINDSPEED = "Windspeed ";
	Activity activity;

	@BindView(R.id.weatherImage)
	ImageView weatherIcon;

	@BindView(R.id.weatherDescription)
	TextView weatherDescription;

	@BindView(R.id.weatherTemp)
	TextView weatherTemp;

	@BindView(R.id.weatherWindSpeed)
	TextView windSpeed;

	@BindView(R.id.detailTitle)
	TextView title;

	@BindView(R.id.detailDescription)
	TextView description;

	@BindView(R.id.detailLocation)
	TextView location;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getIntent().getExtras().getSerializable(EXTRA_ACTIVITY) != null) {
			activity = (Activity) getIntent().getExtras().getSerializable(EXTRA_ACTIVITY);
			initObservers();

			SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map);
			mapFragment.getMapAsync(this);

			setTitle(activity.getDate());
			title.setText(activity.getTitle());
			description.setText(activity.getDescription());
			location.setText(activity.getLocation());

			viewModel.getWeatherData(activity.getLatitude(), activity.getLongitude());
		}
	}

	@Override
	public void onMapReady(GoogleMap googleMap) {
		LatLng location = new LatLng(activity.getLatitude(), activity.getLongitude());
		googleMap.addMarker(new MarkerOptions().position(location)
				.title(activity.getTitle() + " - " + activity.getLocation()));
		googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, DEFAULTZOOM));
	}

	@SuppressLint("SetTextI18n")
	private void initObservers() {
		viewModel.getIconValue().observe(this, iconKey -> Glide.with(getApplicationContext())
				.load("http://openweathermap.org/img/w/" + iconKey + ".png")
				.into(weatherIcon));

		viewModel.getWeatherDescription().observe(this, s -> weatherDescription.setText(s));
		viewModel.getWeatherTemp().observe(this, temp -> weatherTemp.setText(temp + GRADEN));
		viewModel.getWindSpeed().observe(this, speed -> windSpeed.setText(WINDSPEED + speed));
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
