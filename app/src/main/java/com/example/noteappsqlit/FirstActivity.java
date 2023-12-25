package com.example.noteappsqlit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.noteappsqlit.databinding.ActivityFirstBinding;

public class FirstActivity extends AppCompatActivity {
ActivityFirstBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityFirstBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.view.setOnClickListener(v -> {
            Intent intent=new Intent(FirstActivity.this,SecondActivity.class);
            startActivity(intent);

        });
        binding.addnotes.setOnClickListener(v -> {
            Intent intent=new Intent(FirstActivity.this,MainActivity.class);
            startActivity(intent);

        });
    }
}