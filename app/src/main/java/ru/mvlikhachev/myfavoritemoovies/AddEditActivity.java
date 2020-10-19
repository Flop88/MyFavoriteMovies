package ru.mvlikhachev.myfavoritemoovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

public class AddEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);
    }

    public class AddEditcActivityClickHandlers {
        Context context;

        public AddEditcActivityClickHandlers(Context context) {
            this.context = context;
        }

        public void onOkButtonClicked(View view) {

        }

    }
}