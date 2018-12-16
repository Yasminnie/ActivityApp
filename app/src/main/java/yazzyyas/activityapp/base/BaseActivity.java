package yazzyyas.activityapp.base;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import yazzyyas.activityapp.R;

public abstract class BaseActivity<VDB extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity {

	protected VDB binding;
	protected VM viewModel;
//	protected SharedPreferencesManager sharedPrefmanager;

	@BindView(R.id.progress_circle)
	@Nullable
	ProgressBar progressBar;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = DataBindingUtil.setContentView(this, getLayoutId());
		viewModel = ViewModelProviders.of(this).get(getVMClass());
		initViewModelBinding();
		ButterKnife.bind(this);
//		sharedPrefmanager = new SharedPreferencesManager(this);
		initProgressBar();
	}

	private void initProgressBar() {
		if (progressBar != null) {
			progressBar.getIndeterminateDrawable()
					.setColorFilter(Color.RED, android.graphics.PorterDuff.Mode.MULTIPLY);
		}

		viewModel.isLoading().observe(this, this::showLoading);
	}

	public void showLoading(boolean visibility) {
		if (progressBar != null) progressBar.setVisibility(visibility ? View.VISIBLE : View.GONE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (getMenuLayoutId() != -1) getMenuInflater().inflate(getMenuLayoutId(), menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_logout: {
				if (new SharedPreferencesManager(this).logout()) {
					Intent intent = new Intent(this, LoginActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
					startActivity(intent);
				}
				return true;
			}
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * @return Integer the res id of the activity layout.
	 */
	@LayoutRes
	protected abstract Integer getLayoutId();

	/**
	 * Set the viewModel in the ActivityBinding.
	 */
	protected abstract void initViewModelBinding();

	/**
	 * @return Class of the ViewModel.
	 */
	protected abstract Class<VM> getVMClass();

	/**
	 * Override this method with a valid menu id or -1.
	 * @return the menu resource id to be used.
	 */
	@MenuRes
	protected int getMenuLayoutId() {
		return R.menu.menu_main;
	}

}
