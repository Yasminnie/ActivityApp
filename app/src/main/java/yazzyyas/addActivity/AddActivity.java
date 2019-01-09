package yazzyyas.addActivity;

import android.app.DatePickerDialog;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import butterknife.BindView;
import yazzyyas.activityapp.Activity;
import yazzyyas.activityapp.MainActivity;
import yazzyyas.activityapp.R;
import yazzyyas.activityapp.base.BaseActivity;
import yazzyyas.activityapp.databinding.ActivityAddBinding;
import yazzyyas.utils.LocationUtils;

public class AddActivity extends BaseActivity<ActivityAddBinding, AddViewModel> implements DatePickerDialog.OnDateSetListener {

	@BindView(R.id.title)
	TextInputEditText titleView;

	@BindView(R.id.description)
	TextInputEditText descriptionView;

	@BindView(R.id.location)
	TextInputEditText location;

	@BindView(R.id.showDate)
	TextView date;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initObservers();

		FloatingActionButton save = findViewById(R.id.fabSaveActivity);
		save.setOnClickListener(v -> {
			String title = titleView.getText().toString();
			String description = descriptionView.getText().toString();
			String locationString = location.getText().toString();
			String dateString = date.getText().toString();

			double lat = LocationUtils.locationToLatitude(locationString, getApplicationContext());
			double lng = LocationUtils.locationToLongitude(locationString, getApplicationContext());
			Activity newActivity = new Activity(title, description, locationString, dateString, lat, lng);

			if (viewModel.isInputFieldsValid()) {
				Intent resultIntent = new Intent();
				resultIntent.putExtra(MainActivity.EXTRA_ACTIVITY, newActivity);
				setResult(android.app.Activity.RESULT_OK, resultIntent);
				finish();
			}
		});
	}

	private void initObservers() {
		viewModel.getErrorMessage().observe(this, s -> Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show());
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
}
