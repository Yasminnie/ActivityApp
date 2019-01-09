package yazzyyas.activityapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ViewHolder> {

	private List<Activity> activityData;
	private ItemClickListener mClickListener;

	ActivityAdapter(List<Activity> activityData, ItemClickListener mClickListener) {
		this.activityData = activityData;
		this.mClickListener = mClickListener;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
		Context context = parent.getContext();
		View view = LayoutInflater.from(context).inflate(R.layout.activity_item, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		Activity activity = activityData.get(position);

		holder.title.setText(activity.getTitle());
		holder.location.setText(activity.getLocation());
		holder.date.setText(activity.getDate());
	}

	@Override
	public int getItemCount() {
		return activityData.size();
	}

	class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		TextView title;
		TextView date;
		TextView location;

		ViewHolder(View itemView) {
			super(itemView);

			title = itemView.findViewById(R.id.Title);
			date = itemView.findViewById(R.id.datum);
			location = itemView.findViewById(R.id.location);
			itemView.setOnClickListener(this);
		}

		@Override
		public void onClick(View view) {
			mClickListener.onItemClick(activityData.get(getAdapterPosition()));
		}
	}

	public void swapList(List<Activity> newList) {
		activityData = newList;
		if (newList != null) {
			this.notifyDataSetChanged();
		}
	}

	// parent activity will implement this method to respond to click events
	public interface ItemClickListener {
		void onItemClick(Activity activity);
	}
}
