package co.starsky.wanikani;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import co.starsky.wanikani.service.ApiManager;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ApiManager.isWaniKaiApiKeyValid(this)) {
            // TODO show main page
        } else {
            showLogin();
        }
    }

    private void showLogin() {
        // TODO
    }

}
