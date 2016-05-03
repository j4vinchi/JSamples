package mx.caltec.jsamples.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import mx.caltec.jsamples.R;

public class MainActivity extends AppCompatActivity {
    private Button buttonIndexListView, buttonContactosEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUI();
    }

    private void setUI() {
        setContentView(R.layout.activity_main);
        buttonIndexListView = (Button) findViewById(R.id.buttonIndexListView);
        buttonContactosEmail = (Button) findViewById(R.id.buttonContactosEmail);
        setOnclickListeners();
    }

    private void setOnclickListeners() {
        //listview con index alfabetico
        buttonIndexListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchListViewIndexActivity();
            }
        });

        buttonContactosEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchListViewContactosEmail();
            }
        });

    }

    private void launchListViewIndexActivity() {
        startActivity(new Intent(MainActivity.this, ListViewIndexActivity.class));
        //finish();
    }

    private void launchListViewContactosEmail() {
        startActivity(new Intent(MainActivity.this, ContactosEmailActivity.class));
    }
}
