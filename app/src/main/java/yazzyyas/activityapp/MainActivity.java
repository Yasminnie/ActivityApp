package yazzyyas.activityapp;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import yazzyyas.activityapp.base.BaseActivity;
import yazzyyas.activityapp.databinding.ActivityMainBinding;
import yazzyyas.addActivity.AddActivity;
import yazzyyas.addActivity.AddViewModel;
import yazzyyas.detailActivity.DetailsActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements ActivityAdapter.ItemClickListener {

	private ActivityAdapter activityAdapter;
	private List<Activity> activities = new ArrayList<>();
	private AddViewModel addViewModel;

	@BindView(R.id.activityRecyclerView)
	RecyclerView activityRecyclerView;

	//Constants used when calling the update activity
	public static final String EXTRA_ACTIVITY = "Activity";
	public static final int REQUESTCODE = 1234;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addViewModel = new AddViewModel(getApplication(), getApplicationContext());
		initObservers();

		this.activityAdapter = new ActivityAdapter(activities, this);
		activityRecyclerView.setAdapter(activityAdapter);
		RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
		activityRecyclerView.setLayoutManager(layoutManager);
	}

	private void updateUI() {
		if (activityAdapter == null) {
			activityAdapter = new ActivityAdapter(activities, this);
			activityRecyclerView.setAdapter(activityAdapter);
		} else {
			activityAdapter.swapList(activities);
		}
	}

	private void initObservers() {
		addViewModel.getActivities().observe(this, onActivities -> {
			activities = onActivities;
			updateUI();
		});
	}

	public void addActivity(View view) {
		Intent intent = new Intent(getApplicationContext(), AddActivity.class);
		startActivityForResult(intent, REQUESTCODE);
	}

	private void openActivity(Activity activity) {
		Intent intent = new Intent(this, DetailsActivity.class);
		intent.putExtra(EXTRA_ACTIVITY, activity);
		startActivity(intent);
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
				Activity insertBucket = (Activity) data.getSerializableExtra(MainActivity.EXTRA_ACTIVITY);
				addViewModel.insert(insertBucket);
			}
		}
	}

	@Override
	public void onItemClick(Activity activity) {
		openActivity(activity);
	}
}
