package com.example.noteappsqlit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.noteappsqlit.databinding.ActivityUppDataBinding;

public class uppDataActivity extends AppCompatActivity {
    DatabaserHelper mydb;

ActivityUppDataBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityUppDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mydb = new DatabaserHelper(this);
        mydb.getWritableDatabase();


       // binding.id.setEnabled(false);
        binding.id.setEnabled(false);
        binding.id.setVisibility(View.INVISIBLE);

        binding.id.setText(getIntent().getStringExtra("id"));
        binding.title.setText(getIntent().getStringExtra("title"));
        binding.content.setText(getIntent().getStringExtra("content"));



        binding.savebtn.setOnClickListener(v -> {
            boolean isUpdate=mydb.UpdateData(binding.id.getText().toString(),binding.title.getText().toString()
                    ,binding.content.getText().toString());




            if(isUpdate==true)
            {
                Toast.makeText(this, "Data Updated", Toast.LENGTH_SHORT).show();

                binding.id.setText(" ");
                binding.title.setText("");

                binding.content.setText("");


                Intent intent=new Intent(uppDataActivity.this,MainActivity.class);
                startActivity(intent);
                finishAffinity();





            }
            else {
                Toast.makeText(this, "Data is not updated", Toast.LENGTH_SHORT).show();

            }
        });












    }


}