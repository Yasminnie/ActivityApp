package yazzyyas.activityapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import yazzyyas.activityapp.database.AppDatabase;

public class MainActivity extends AppCompatActivity implements ActivityRecyclerViewAdapter.ItemClickListener {

	static AppDatabase db;
	public final static int TASK_GET_ALL_ACTIVITES = 0;
	public final static int TASK_DELETE_ACTIVITY = 1;
	public final static int TASK_UPDATE_ACTIVITY = 2;
	public final static int TASK_INSERT_ACTIVITY = 3;

	RecyclerView activityRecyclerView;
	private ActivityRecyclerViewAdapter activityAdapter;

	FloatingActionButton fabAddActivity;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		fabAddActivity = findViewById(R.id.fabAddActivity);
		fabAddActivity.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), AddActivity.class);
				startActivityForResult(intent, TASK_INSERT_ACTIVITY);
			}
		});

		db = AppDatabase.getInstance(this);
		this.activityAdapter = new ActivityRecyclerViewAdapter(this, new ArrayList<Activity>(), this);
		new ActivityAsyncTask(TASK_GET_ALL_ACTIVITES, activityAdapter).execute();
		activityRecyclerView = findViewById(R.id.activityRecyclerView);
		activityRecyclerView.setAdapter(activityAdapter);

		RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
		activityRecyclerView.setLayoutManager(layoutManager);
		activityRecyclerView.setHasFixedSize(true);

		ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
				new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
					@Override
					public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder
							target) {
						return false;
					}

					@Override
					public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
						int position = (viewHolder.getAdapterPosition());

						try {
							Activity activityPosition = new ActivityAsyncTask(TASK_GET_ALL_ACTIVITES, activityAdapter).execute().get().get(position);
							new ActivityAsyncTask(TASK_DELETE_ACTIVITY, activityAdapter).execute(activityPosition);
						} catch (InterruptedException e) {
							e.printStackTrace();
						} catch (ExecutionException e) {
							e.printStackTrace();
						}
						activityAdapter.notifyItemRemoved(position);
					}
				};

		ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
		itemTouchHelper.attachToRecyclerView(activityRecyclerView);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		if (resultCode == android.app.Activity.RESULT_OK) {
			switch (requestCode) {
				case TASK_INSERT_ACTIVITY: // data in intent omzetten naar een game object
//					Activity insertActivity = new Activity(null, data.getStringExtra("title"), data.getStringExtra("platform"), data.getStringExtra("notes"), new Date());
					new ActivityAsyncTask(TASK_INSERT_ACTIVITY, activityAdapter).execute(insertActivity);
					break;
				case TASK_UPDATE_ACTIVITY:
//					Activity updateActivity = new Activity(data.getLongExtra("id", 0), data.getStringExtra("title"), data.getStringExtra("platform"), data.getStringExtra("notes"), new Date());
					new ActivityAsyncTask(TASK_UPDATE_ACTIVITY, activityAdapter).execute(updateActivity);
					break;
			}
		}
	}

	@Override
	public void onItemClick(View view, int position) throws ExecutionException, InterruptedException {
		try {
			Activity activityPosition = new ActivityAsyncTask(TASK_GET_ALL_ACTIVITES, activityAdapter).execute().get().get(position);
			Intent intent = new Intent(MainActivity.this, AddActivity.class);

			intent.putExtra("id", activityPosition.getId());
			intent.putExtra("title", activityPosition.getTitle());
			intent.putExtra("location", activityPosition.getLocation());

			startActivityForResult(intent, TASK_UPDATE_ACTIVITY);
		} catch (ExecutionException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
