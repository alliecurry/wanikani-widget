package co.starsky.wanikani.controller.fragment;

import android.support.v4.app.Fragment;

/**
 * Base Class for all Fragments used in this application.
 * @author alliecurry
 */
public abstract class BaseFragment extends Fragment {

    /** Use to determine whether or not you can manipulate the fragment UI.
     *  @return true when this fragment is in the foreground. */
    public boolean isReady() {
        return (isAdded() && !isRemoving());
    }

}
