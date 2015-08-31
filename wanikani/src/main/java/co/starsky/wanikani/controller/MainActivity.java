package co.starsky.wanikani.controller;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import co.starsky.wanikani.BuildConfig;
import co.starsky.wanikani.R;
import co.starsky.wanikani.controller.fragment.LoginFragment;
import co.starsky.wanikani.service.ApiManager;


public class MainActivity extends AppCompatActivity implements LoginFragment.LoginListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setStatusBarColor(getResources().getColor(R.color.royal_blue_status));

        if (ApiManager.isWaniKaiApiKeyValid(this)) {
            showMainPage();
        } else {
            showLogin();
        }
    }

    private void showLogin() {
        clearBackStack(); // Users should not be able to navigate back if they must login.
        replaceFragment(new LoginFragment(), LoginFragment.TAG);
    }

    private void showMainPage() {
        // TODO
    }

    @Override
    public void onLoginComplete(final String key) {
        ApiManager.setWanikaniApiKey(this, key);
        if (BuildConfig.DEBUG) {
            Toast.makeText(this, key, Toast.LENGTH_SHORT).show();
        }
        showMainPage();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            super.onBackPressed();
        } else { // No more fragments...
            finish();
        }
    }

    /** Clears the fragment back stack. */
    private void clearBackStack() {
       final  FragmentManager manager = getSupportFragmentManager();
        if (manager.getBackStackEntryCount() > 0) {
            final FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
            manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    /** Safe call (handles IllegalStateException) to attempt to replace the Fragment in the main container. */
    private void replaceFragment(final Fragment fragment, final String tag) {
        try {
            replaceFragment(fragment, R.id.container, tag);
        } catch (IllegalStateException e) {
            Log.e(TAG, e.toString());
        }
    }

    /** Replaces the fragment in the given container. */
    private void replaceFragment(final Fragment fragment, final int containerId, final String tag) {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containerId, fragment, tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColor(final int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            final Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(color);
        }
    }

}
