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

public class ActivityRecyclerViewAdapter extends RecyclerView.Adapter<ActivityRecyclerViewAdapter.ViewHolder> {

	private List<Activity> mData;
	private LayoutInflater mInflater;
	private ItemClickListener mClickListener;

	public ActivityRecyclerViewAdapter(Context context, List<Activity> mData, ItemClickListener mClickListener) {
		this.mInflater = LayoutInflater.from(context);
		this.mData = mData;
		this.mClickListener = mClickListener;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
		View view = mInflater.inflate(R.layout.activity_item, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		Activity activity = mData.get(position);

		holder.title.setText(activity.getTitle());
		holder.location.setText(activity.getLocation());
	}

	@Override
	public int getItemCount() {
		return mData.size();
	}

	public void setmData(List<Activity> mData) {
		this.mData = mData;
		notifyDataSetChanged(); // zegt tegen UI dat je items kan updaten in recyclerview
	}

	class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		TextView title;
		TextView date;
		TextView location;

		ViewHolder(View itemView) {
			super(itemView);

			title = itemView.findViewById(R.id.Title);
			date = itemView.findViewById(R.id.date);
			location = itemView.findViewById(R.id.location);
			itemView.setOnClickListener(this);
		}

		@Override
		public void onClick(View view) {
			try {
				mClickListener.onItemClick(view, getAdapterPosition());
			} catch (ExecutionException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// parent activity will implement this method to respond to click events
	public interface ItemClickListener {
		void onItemClick(View view, int position) throws ExecutionException, InterruptedException;
	}
}
