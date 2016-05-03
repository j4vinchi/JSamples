package mx.caltec.jsamples.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class JSamplesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (true) launchMain();
    }

    private void launchMain() {
        startActivity(new Intent(JSamplesActivity.this, MainActivity.class));
        finish();
    }
}
