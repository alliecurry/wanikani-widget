package co.starsky.wanikani.controller.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import co.starsky.wanikani.R;
import co.starsky.wanikani.model.User;
import co.starsky.wanikani.model.WaniKaniResponse;
import co.starsky.wanikani.service.ApiManager;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Fragment which prompts the user for their WaniKani API key.
 * @author alliecurry
 */
public class LoginFragment extends BaseFragment implements TextView.OnEditorActionListener {
    public static final String TAG = LoginFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_login, container, false);
        final EditText input = (EditText) layout.findViewById(R.id.login_input);
        input.setOnEditorActionListener(this);
        return layout;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_GO) {
            doLogin(v.getText().toString().trim());
        }
        return false;
    }

    private void doLogin(final String key) {
        // TODO show progress dialog
        ApiManager.getWaniKaniService().getUser(key, new Callback<WaniKaniResponse<User>>() {
            @Override
            public void success(WaniKaniResponse<User> waniKaniResponse, Response response) {
                if (waniKaniResponse != null && waniKaniResponse.getUserInfo() != null) {
                    if (getActivity() instanceof LoginListener) {
                        ((LoginListener) getActivity()).onLoginComplete(key);
                        return;
                    } else {
                        Log.w(TAG, "Cannot handle login: LoginListener not implemented by parent Activity");
                    }
                }

                // TODO hide progress dialog
                // TODO show error
            }

            @Override
            public void failure(RetrofitError error) {
                // TODO show error
            }
        });
    }

    public interface LoginListener {
        void onLoginComplete(final String key);
    }
}
