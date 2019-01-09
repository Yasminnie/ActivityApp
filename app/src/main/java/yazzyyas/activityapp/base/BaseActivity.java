package yazzyyas.activityapp.base;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import yazzyyas.activityapp.R;

public abstract class BaseActivity<VDB extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity {

	protected VDB binding;
	protected VM viewModel;

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

}
