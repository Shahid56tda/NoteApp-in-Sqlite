package com.example.noteappsqlit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.database.Cursor;
import android.os.Bundle;

import com.example.noteappsqlit.databinding.ActivitySecondBinding;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
ActivitySecondBinding binding;
MainActivity mainActivity;
    DatabaserHelper mydb;
    ModelClass modelClass;
    SecondAdapter adapterClass;
    ArrayList<ModelClass> list;

    RecyclerView R2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        R2=findViewById(R.id.R2);

        list=new ArrayList<>();
        mydb = new DatabaserHelper(this); // Initialize your DbHelper object
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        binding. R2.setLayoutManager(layoutManager);
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
            adapterClass = new SecondAdapter(SecondActivity.this, list);
            R2.setAdapter(adapterClass);


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