package com.example.roomwordsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NewWordActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY =
            "com.example.roomwordsample.REPLY";

    private EditText mEditWordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);

        mEditWordView = findViewById(R.id.edit_word);

        Button button = findViewById(R.id.button_save);

        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();

            if (mEditWordView.getText().toString().trim().isEmpty()) {
                setResult(Activity.RESULT_CANCELED, replyIntent);
            } else {
                String word = mEditWordView.getText().toString();
                replyIntent.putExtra(EXTRA_REPLY, word);
                setResult(Activity.RESULT_OK, replyIntent);
            }

            finish();
        });
    }
}
