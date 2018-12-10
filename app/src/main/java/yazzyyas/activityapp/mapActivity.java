package yazzyyas.activityapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class mapActivity extends AppCompatActivity implements OnMapReadyCallback {

	private BottomNavigationView bottomNavigationView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		bottomNavigationView = findViewById(R.id.bottom_navigation);

		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map);
		mapFragment.getMapAsync(this);

		bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem item) {
				switch (item.getItemId()) {
					case R.id.action_recents:
						Toast.makeText(mapActivity.this, "Recents", Toast.LENGTH_SHORT).show();
						break;
					case R.id.action_favorites:
						Toast.makeText(mapActivity.this, "Favorites", Toast.LENGTH_SHORT).show();
						break;
					case R.id.action_nearby:
						Toast.makeText(mapActivity.this, "Nearby", Toast.LENGTH_SHORT).show();
						break;
				}
				return true;
			}
		});
	}

	@Override
	public void onMapReady(GoogleMap googleMap) {
		// Add a marker in Sydney, Australia,
		// and move the map's camera to the same location.
		LatLng sydney = new LatLng(-33.852, 151.211);
		googleMap.addMarker(new MarkerOptions().position(sydney)
				.title("Marker in Sydney"));
		googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
	}

	@Override
	public void onPointerCaptureChanged(boolean hasCapture) {

	}
}

