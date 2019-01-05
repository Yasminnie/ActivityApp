package yazzyyas.activityapp;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import yazzyyas.activityapp.base.BaseActivity;

public class AddActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

	private TextInputEditText titleView;
	private TextInputEditText descriptionView;
	private TextInputEditText location;
	private TextView date;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);

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

				Activity newActivity = new Activity(title, description, locationString, dateString);

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

	/**
	 * This callback method, call DatePickerFragment class,
	 * DatePickerFragment class returns calendar view.
	 * @param view
	 */
	public void datePicker(View view){
		DatePickerFragment fragment = new DatePickerFragment();
		fragment.show(getSupportFragmentManager(), "date");
	}

	/**
	 * To set date on TextView
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
