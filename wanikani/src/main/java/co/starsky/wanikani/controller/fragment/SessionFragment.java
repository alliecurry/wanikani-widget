package co.starsky.wanikani.controller.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import co.starsky.wanikani.R;

/**
 * The main fragment that displays when the user is in session.
 * @author alliecurry
 */
public class SessionFragment extends BaseFragment {
    public static final String TAG = SessionFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_session, container, false);
    }
}
