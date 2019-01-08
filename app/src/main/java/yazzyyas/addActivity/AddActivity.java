package yazzyyas.addActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import yazzyyas.activityapp.Activity;
import yazzyyas.activityapp.MainActivity;
import yazzyyas.activityapp.R;
import yazzyyas.activityapp.base.BaseActivity;
import yazzyyas.activityapp.databinding.ActivityAddBinding;
import yazzyyas.utils.LocationUtils;

public class AddActivity extends BaseActivity<ActivityAddBinding, AddViewModel> implements DatePickerDialog.OnDateSetListener {

	private TextInputEditText titleView;
	private TextInputEditText descriptionView;
	private TextInputEditText location;
	private TextView date;

	TextView latLongTV;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		titleView = findViewById(R.id.title);
		descriptionView = findViewById(R.id.description);
		location = findViewById(R.id.location);
		date = findViewById(R.id.showDate);

		FloatingActionButton save = findViewById(R.id.fabSaveActivity);
		save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String title = titleView.getText().toString();
				String description = descriptionView.getText().toString();
				String locationString = location.getText().toString();
				String dateString = date.getText().toString();

				double lat = LocationUtils.locationToLatitude(locationString, getApplicationContext());
				double lng = LocationUtils.locationToLongitude(locationString, getApplicationContext());
				Activity newActivity = new Activity(title, description, locationString, dateString, lat, lng);

				if (!TextUtils.isEmpty(title) | !TextUtils.isEmpty(description) | !TextUtils.isEmpty(locationString)) {
					//Prepare the return parameter and return
					Intent resultIntent = new Intent();
					resultIntent.putExtra(MainActivity.EXTRA_ACTIVITY, newActivity);
					setResult(android.app.Activity.RESULT_OK, resultIntent);
					finish();
				} else {
					Toast.makeText(AddActivity.this, "Enter some text", Toast.LENGTH_LONG).show();
				}
			}
		});
	}


	@Override
	protected Integer getLayoutId() {
		return R.layout.activity_add;
	}

	@Override
	protected void initViewModelBinding() {
		binding.setViewModel(viewModel);
	}

	@Override
	protected Class<AddViewModel> getVMClass() {
		return AddViewModel.class;
	}

	/**
	 * This callback method, call DatePickerFragment class,
	 * DatePickerFragment class returns calendar view.
	 *
	 * @param view
	 */
	public void datePicker(View view) {
		DatePickerFragment fragment = new DatePickerFragment();
		fragment.show(getSupportFragmentManager(), "date");
	}

	/**
	 * To set date on TextView
	 *
	 * @param calendar
	 */
	private void setDate(final Calendar calendar) {
		final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
		date.setText(dateFormat.format(calendar.getTime()));
	}

	@Override
	public void onDateSet(DatePicker view, int year, int month, int day) {
		Calendar cal = new GregorianCalendar(year, month, day);
		setDate(cal);
	}

//	public class GeoCoderHandler extends Handler {
//		@Override
//		public void handleMessage(Message message) {
//			String locationAddress;
//			switch (message.what) {
//				case 1:
//					Bundle bundle = message.getData();
//					locationAddress = bundle.getString("address");
//					break;
//				default:
//					locationAddress = null;
//			}
//			latLongTV.setText(locationAddress);
//		}
//	}
}
