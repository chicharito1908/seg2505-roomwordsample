package com.example.roomwordsample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import android.app.Activity;
public class MainActivity extends AppCompatActivity {

    private WordViewModel mWordViewModel;
    private final ActivityResultLauncher<Intent> newWordLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == Activity.RESULT_OK
                                && result.getData() != null) {

                            String word = result.getData().getStringExtra(
                                    NewWordActivity.EXTRA_REPLY);

                            if (word != null && !word.trim().isEmpty()) {
                                mWordViewModel.insert(new Word(word));
                            }
                        }
                    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        RecyclerView recyclerView =
                findViewById(R.id.recyclerview);

        final WordListAdapter adapter =
                new WordListAdapter(this);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));

        mWordViewModel = new ViewModelProvider(this)
                .get(WordViewModel.class);

        mWordViewModel.getAllWords().observe(
                this, words -> {
                    adapter.setWords(words);
                });
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, NewWordActivity.class);
            newWordLauncher.launch(intent);
        });
    }
}
