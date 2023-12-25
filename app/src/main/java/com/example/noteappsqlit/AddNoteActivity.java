package com.example.noteappsqlit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.noteappsqlit.databinding.ActivityAddNoteBinding;

import java.text.DateFormat;
import java.util.Calendar;

public class AddNoteActivity extends AppCompatActivity {
    DatabaserHelper mydb;
    MainActivity mainActivity;


ActivityAddNoteBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAddNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mydb=new DatabaserHelper(this);
        mydb.getWritableDatabase();





        binding.savebtn.setOnClickListener(v -> {

            boolean isInserted=mydb.insertData(
                    binding.titlEditText.getText().toString(),binding.contentEditText.getText().toString());

            if (isInserted==true)

            {
                Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                finishAffinity();




            }
            else
            {
                Toast.makeText(this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
            }
        });



    }


}