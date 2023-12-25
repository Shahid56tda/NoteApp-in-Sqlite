package com.example.noteappsqlit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteappsqlit.databinding.ItemViewBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SecondAdapter extends RecyclerView.Adapter<SecondAdapter.viewHolder> {
    Context context;
    ArrayList<ModelClass> list;

    public SecondAdapter(Context context, ArrayList<ModelClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false));


    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        int colourcode=getRandomColor();
        holder.binding.c1.setBackgroundColor(holder.itemView.getResources().getColor(colourcode,null));
        holder.binding.time.setText(list.get(position).getTime());
        holder.binding.title.setText(list.get(position).getTitle());
        holder.binding.content.setText(list.get(position).getContent());
        holder.binding.id.setText(list.get(position).getId());
        holder.binding.id.setVisibility(View.INVISIBLE);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class  viewHolder extends RecyclerView.ViewHolder {
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
