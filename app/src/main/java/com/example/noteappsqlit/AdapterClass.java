package com.example.noteappsqlit;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteappsqlit.databinding.ItemViewBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.viewHolder> {
    Context context;
    ArrayList<ModelClass>list;
    DatabaserHelper mydb;

    public AdapterClass(Context context, ArrayList<ModelClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {
        int colourcode=getRandomColor();
        holder.binding.c1.setBackgroundColor(holder.itemView.getResources().getColor(colourcode,null));
        holder.binding.time.setText(list.get(position).getTime());
        holder.binding.title.setText(list.get(position).getTitle());
        holder.binding.content.setText(list.get(position).getContent());
        holder.binding.id.setText(list.get(position).getId());
        holder.binding.id.setVisibility(View.INVISIBLE);

        holder.itemView.setOnClickListener(v -> {

                Intent intent = new Intent(context, uppDataActivity.class);
                intent.putExtra("id", list.get(position).getId());
                intent.putExtra("title", list.get(position).getTitle());
                intent.putExtra("content", list.get(position).getContent());
                intent.putExtra("time", list.get(position).getTime());
                context.startActivity(intent);
                ((Activity) context).finish();

        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext())

                        .setTitle("delete contact").setMessage("Are you sure you want to delete this item")
                        .setIcon(R.drawable.ic_launcher_background)
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mydb=new DatabaserHelper(context);
                                mydb.getWritableDatabase();
                                Integer deleteRow=mydb.deleteData(list.get(position).getId());
                                if(deleteRow>0)
                                {
                                    Toast.makeText(context, "Data Deleted", Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(context,MainActivity.class);
                                    context.startActivity(intent);
                                    ((Activity)context).finish();
                                }else
                                {
                                    Toast.makeText(context, "Data not deleted", Toast.LENGTH_SHORT).show();
                                }
                                notifyItemRemoved(position);





                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.show();

                return true;
            }
        });





    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ItemViewBinding binding;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding=ItemViewBinding.bind(itemView);
        }
    }
    private  int getRandomColor(){
        List<Integer> colorcode=new ArrayList<>();
        colorcode.add(R.color.gray);
        colorcode.add(R.color.green);
        colorcode.add(R.color.lightgreen);
        colorcode.add(R.color.pink);
        colorcode.add(R.color.color1);
        colorcode.add(R.color.color2);
        colorcode.add(R.color.color3);
        colorcode.add(R.color.color4);
        colorcode.add(R.color.color5);
        Random random=new Random();
        int number=random.nextInt(colorcode.size());
        return colorcode.get(number);


    }

}
