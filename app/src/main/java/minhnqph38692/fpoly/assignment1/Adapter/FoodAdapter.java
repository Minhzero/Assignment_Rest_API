package minhnqph38692.fpoly.assignment1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import minhnqph38692.fpoly.assignment1.Activity.ShowDetailActivity;
import minhnqph38692.fpoly.assignment1.DTO.CategoryDTO;
import minhnqph38692.fpoly.assignment1.DTO.FoodDTO;
import minhnqph38692.fpoly.assignment1.Interface.Item_Food_Handle;
import minhnqph38692.fpoly.assignment1.R;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {
Context context;
ArrayList<FoodDTO> foodDTOS;
Item_Food_Handle handle;

    public FoodAdapter(Context context, ArrayList<FoodDTO> foodDTOS, Item_Food_Handle handle) {
        this.context = context;
        this.foodDTOS = foodDTOS;
        this.handle = handle;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_food, parent, false);
        return new ViewHolder(inView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodDTO foodDTO = foodDTOS.get(position);
        holder.title.setText(foodDTOS.get(position).getTitle());
        holder.fee.setText(String.valueOf(foodDTOS.get(position).getPrice()));
        Glide.with(context)
                .load(foodDTO.getImage().get(0))
                .thumbnail(Glide.with(context).load(foodDTO.getImage().get(0)))
                .into(holder.pic);

//        int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(foodDTOS.get(position).getPic(),"drawable",holder.itemView.getContext().getPackageName());
//
//        Glide.with(holder.itemView.getContext())
//                .load(drawableResourceId)
//                .into(holder.pic);
        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
                intent.putExtra("object", foodDTOS.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
        holder.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
                intent.putExtra("object", foodDTOS.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return foodDTOS.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,fee,addBtn;
        ImageView pic;
        ConstraintLayout next;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title= itemView.findViewById(R.id.title);
            fee= itemView.findViewById(R.id.fee);
            pic= itemView.findViewById(R.id.pic);
            addBtn= itemView.findViewById(R.id.addBtn);
            next=itemView.findViewById(R.id.next);


        }
    }
}
