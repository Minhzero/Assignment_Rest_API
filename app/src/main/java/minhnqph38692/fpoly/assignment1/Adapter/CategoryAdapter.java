package minhnqph38692.fpoly.assignment1.Adapter;

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

import minhnqph38692.fpoly.assignment1.DTO.CategoryDTO;
import minhnqph38692.fpoly.assignment1.R;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
ArrayList<CategoryDTO> categoryDTOS;

    public CategoryAdapter(ArrayList<CategoryDTO> categoryDTOS) {
        this.categoryDTOS = categoryDTOS;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category, parent, false);
        return new ViewHolder(inView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.categoryName.setText(categoryDTOS.get(position).getTitle());
        String picUrl ="";
        switch (position){
            case 0:{
                picUrl ="cat_1";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_background1));
                break;
            }
            case 1:{
                picUrl ="cat_2";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_background2));
                break;
            }
            case 2:{
                picUrl ="cat_3";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_background3));
                break;
            }
            case 3:{
                picUrl ="cat_4";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_background4));
                break;
            }
            case 4:{
                picUrl ="cat_5";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_background5));
                break;
            }
        }
        int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(picUrl,"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.categoryPic);

    }

    @Override
    public int getItemCount() {
        return categoryDTOS.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        ImageView categoryPic;
        ConstraintLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName= itemView.findViewById(R.id.categoryName);
            categoryPic= itemView.findViewById(R.id.categoryPic);
            mainLayout= itemView.findViewById(R.id.mainLayout);

        }
    }
}
