package yazzyyas.activityapp;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import yazzyyas.activityapp.base.BaseActivity;
import yazzyyas.activityapp.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements ActivityAdapter.ItemClickListener {

	FloatingActionButton fabAddActivity;
	private ActivityAdapter activityAdapter;
	private List<Activity> activities = new ArrayList<>();
	private AddActivityViewModel addActivityViewModel;

	@BindView(R.id.activityRecyclerView)
	RecyclerView activityRecyclerView;

	//Constants used when calling the update activity
	public static final String EXTRA_ACTIVITY = "Activity";
	public static final int REQUESTCODE = 1234;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.activityAdapter = new ActivityAdapter(activities, this);
		activityRecyclerView.setAdapter(activityAdapter);
		RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
		activityRecyclerView.setLayoutManager(layoutManager);

		addActivityViewModel = new AddActivityViewModel(getApplication(), getApplicationContext());
		addActivityViewModel.getActivities().observe(this, new Observer<List<Activity>>() {
			@Override
			public void onChanged(@Nullable List<Activity> onActivities) {
				activities = onActivities;
				updateUI();
			}
		});

//		viewmodel gebruiken met observer voor dit
		fabAddActivity = findViewById(R.id.fabAddActivity);
		fabAddActivity.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), AddActivity.class);
				startActivityForResult(intent, REQUESTCODE);
			}
		});
	}

	private void updateUI() {
		if (activityAdapter == null) {
			activityAdapter = new ActivityAdapter(activities, this);
			activityRecyclerView.setAdapter(activityAdapter);
		} else {
			activityAdapter.swapList(activities);
		}
	}

	@Override
	protected Integer getLayoutId() {
		return R.layout.activity_main;
	}

	@Override
	protected void initViewModelBinding() {
		binding.setViewModel(viewModel);
	}

	@Override
	protected Class<MainViewModel> getVMClass() {
		return MainViewModel.class;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		if (requestCode == REQUESTCODE) {
			if (resultCode == RESULT_OK) {
				Activity insertBucket = data.getParcelableExtra(MainActivity.EXTRA_ACTIVITY);
				addActivityViewModel.insert(insertBucket);
			}
		}
	}

	@Override
	public void onItemClick(View view, int position) throws ExecutionException, InterruptedException {
		Log.d("yasjoe", "onItemClick: item van activiteit");
	}
}
