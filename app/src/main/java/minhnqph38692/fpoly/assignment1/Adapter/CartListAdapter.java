package minhnqph38692.fpoly.assignment1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import minhnqph38692.fpoly.assignment1.DTO.FoodDTO;
import minhnqph38692.fpoly.assignment1.DTO.ProductDTOAPI;
import minhnqph38692.fpoly.assignment1.Helper.ManagementCart;
import minhnqph38692.fpoly.assignment1.Interface.ChangeNumberItemsListener;
import minhnqph38692.fpoly.assignment1.Interface.Item_ProductCart_Handle;
import minhnqph38692.fpoly.assignment1.R;

public class CartListAdapter  extends  RecyclerView.Adapter<CartListAdapter.ViewHolder>{
private ArrayList<ProductDTOAPI> foodDTOS;
ManagementCart managementCart;
Item_ProductCart_Handle handle;
ChangeNumberItemsListener changeNumberItemsListener;

    public CartListAdapter(ArrayList<ProductDTOAPI> foodDTOS, Context context, Item_ProductCart_Handle handle, ChangeNumberItemsListener changeNumberItemsListener) {
        this.foodDTOS = foodDTOS;
        this.managementCart = new ManagementCart(context);
        this.handle = handle;
        this.changeNumberItemsListener = changeNumberItemsListener;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductDTOAPI foodDTO =foodDTOS.get(position);

        holder.title.setText(foodDTOS.get(position).getTitle());
        holder.feeEachItem.setText(String.valueOf(foodDTOS.get(position).getPrice()));
        holder.totalEachItem.setText(String.valueOf(Math.round((foodDTO.getStock() * foodDTO.getPrice())*100) /100));
        holder.num.setText(String.valueOf(foodDTOS.get(position).getStock()));

        Glide.with(holder.itemView.getContext())
                .load(foodDTO.getImage().get(0))
                .thumbnail(Glide.with(holder.itemView.getContext()).load(foodDTO.getImage().get(0)))
                .into(holder.pic);

//        int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(foodDTOS.get(position).getImage(),"drawable",holder.itemView.getContext().getPackageName());
//
//        Glide.with(holder.itemView.getContext())
//                .load(drawableResourceId)
//                .into(holder.pic);

        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
                handle.UpdateT(foodDTOS.get(position).get_id(),foodDTO);
            }
        });
        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                managementCart.minusNumberFood(foodDTOS, position, new ChangeNumberItemsListener() {
//                    @Override
//                    public void changed() {
//                        notifyDataSetChanged();
//                        changeNumberItemsListener.changed();
//                    }
//                });
                handle.UpdateG(foodDTOS.get(position).get_id(),foodDTO);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodDTOS.size();
    }


    public ArrayList<ProductDTOAPI> getList() {
        return foodDTOS;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,feeEachItem;
        ImageView pic,plusItem,minusItem;
        TextView totalEachItem,num;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTxt1);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
            pic = itemView.findViewById(R.id.picCart);
            plusItem = itemView.findViewById(R.id.plusCartBtn);
            minusItem = itemView.findViewById(R.id.minusCartBtn);
            totalEachItem = itemView.findViewById(R.id.totalEachItem);
            num = itemView.findViewById(R.id.numberItemTxt);

        }
    }
}
