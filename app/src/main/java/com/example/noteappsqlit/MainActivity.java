package com.example.noteappsqlit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.example.noteappsqlit.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
DatabaserHelper mydb;
ModelClass modelClass;
AdapterClass adapterClass;
ArrayList<ModelClass> list;
    StaggeredGridLayoutManager staggeredGridLayoutManager;
    RecyclerView R1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        getSupportActionBar().setTitle("All Notes");
        R1=findViewById(R.id.R1);


        list=new ArrayList<>();
        mydb = new DatabaserHelper(this); // Initialize your DbHelper object
        staggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);




        binding. R1.setLayoutManager(staggeredGridLayoutManager);
        ViwData();























    }
    public void ViwData() {
        Cursor cursor = mydb.getAllData();
        list.clear();
        if (cursor.getCount() == 0) {
            showmessage("Error", "No data avalible");
            return;
        }
        while (cursor.moveToNext()) {
            modelClass = new ModelClass(cursor.getString(0), cursor.getString(1)
                    , cursor.getString(2), cursor.getString(3));

            list.add(modelClass);
            adapterClass = new AdapterClass(MainActivity.this, list);
            R1.setAdapter(adapterClass);


        }
    }
    public void showmessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}