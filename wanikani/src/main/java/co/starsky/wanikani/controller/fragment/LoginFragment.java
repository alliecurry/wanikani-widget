package co.starsky.wanikani.controller.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import co.starsky.wanikani.R;
import co.starsky.wanikani.model.User;
import co.starsky.wanikani.model.WaniKaniResponse;
import co.starsky.wanikani.service.ApiManager;
import co.starsky.wanikani.util.ViewUtility;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Fragment which prompts the user for their WaniKani API key.
 * @author alliecurry
 */
public class LoginFragment extends BaseFragment implements TextView.OnEditorActionListener {
    public static final String TAG = LoginFragment.class.getSimpleName();

    private EditText input;
    private TextView errorText;
    private ProgressDialog progress;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_login, container, false);
        errorText = (TextView) layout.findViewById(R.id.login_error);
        input = (EditText) layout.findViewById(R.id.login_input);
        input.setOnEditorActionListener(this);
        return layout;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_GO) {
            final String key = v.getText().toString().trim();
            if (key.isEmpty()) {
                showError(getString(R.string.l0ogin_error_empty));
            } else {
                doLogin(key);
            }
        }
        return false;
    }

    private void doLogin(final String key) {
        showProgress();
        ApiManager.getWaniKaniService().getUser(key, new Callback<WaniKaniResponse<User>>() {
            @Override
            public void success(WaniKaniResponse<User> waniKaniResponse, Response response) {
                if (!isReady()) {
                    return;
                }
                hideProgress();
                if (waniKaniResponse == null) {
                    showError();
                } else if (waniKaniResponse.getUserInfo() != null) {
                    if (getActivity() instanceof LoginListener) {
                        ((LoginListener) getActivity()).onLoginComplete(key);
                    } else {
                        Log.w(TAG, "Cannot handle login: LoginListener not implemented by parent Activity");
                        showError();
                    }
                } else if (waniKaniResponse.isError()) {
                    showError(waniKaniResponse.getError().getMessage());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.toString());
                if (!isReady()) {
                    return;
                }
                hideProgress();
                showError();
            }
        });
    }

    private void showProgress() {
        if (progress == null) {
            progress = ViewUtility.getDefaultProgressDialog(getActivity());
        }
        progress.show();
    }

    private void hideProgress() {
        if (progress != null) {
            progress.hide();
        }
    }

    private void showError() {
        showError(getString(R.string.default_error));
    }

    private void showError(final String error) {
        if (errorText == null) {
            return;
        }
        final AlphaAnimation fadeIn = new AlphaAnimation(0f, 1f);
        fadeIn.setDuration(500);
        fadeIn.setFillAfter(true);
        errorText.setText(error);
        errorText.startAnimation(fadeIn);
        input.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.shake));
    }

    public interface LoginListener {
        void onLoginComplete(final String key);
    }
}
