package minhnqph38692.fpoly.assignment1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;

import minhnqph38692.fpoly.assignment1.Activity.BillDetailsActivity;
import minhnqph38692.fpoly.assignment1.Activity.ShowDetailActivity;
import minhnqph38692.fpoly.assignment1.DTO.BillDetailsDTO;
import minhnqph38692.fpoly.assignment1.DTO.ProductDTOAPI;
import minhnqph38692.fpoly.assignment1.Helper.ManagementCart;
import minhnqph38692.fpoly.assignment1.Interface.ChangeNumberItemsListener;
import minhnqph38692.fpoly.assignment1.Interface.Item_Bill_Handle;
import minhnqph38692.fpoly.assignment1.Interface.Item_ProductCart_Handle;
import minhnqph38692.fpoly.assignment1.R;

public class BillListAdapter extends RecyclerView.Adapter<BillListAdapter.ViewHolder> {
    Context context;
   ArrayList<BillDetailsDTO> list;
    Item_Bill_Handle handle;

    public BillListAdapter(Context context, ArrayList<BillDetailsDTO> list, Item_Bill_Handle handle) {
        this.context = context;
        this.list = list;
        this.handle = handle;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_bill, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (list != null && position < list.size()) {
            // Tiếp tục với việc gán dữ liệu vào ViewHolder
            BillDetailsDTO billDetailsDTO = list.get(position);

            holder.billTitle.setText(list.get(position).getTitle());
            holder.billprice.setText("Giá: " + String.valueOf(billDetailsDTO.getPrice()));
            holder.billStock.setText(" Số lượng: x" + billDetailsDTO.getStock());

            Glide.with(holder.itemView.getContext())
                    .load(billDetailsDTO.getImage().get(0))
                    .thumbnail(Glide.with(holder.itemView.getContext()).load(billDetailsDTO.getImage().get(0)))
                    .into(holder.anh);
            holder.ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(holder.itemView.getContext(), BillDetailsActivity.class);
                    Log.d("biiada", list.get(position).get_id());
                    Gson gson = new Gson();
                    String billDetailsJson = gson.toJson(list.get(position));

                    // Thêm dữ liệu vào Intent
                    intent.putExtra("billDetails", billDetailsJson);

                    holder.itemView.getContext().startActivity(intent);
                }
            });
            Log.d("list", "onBindViewHolder: " + list);
        } else {
            Toast.makeText(context, " ko ", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView billTitle, billprice;
        ImageView anh;
        TextView billStock;
        LinearLayout ok;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            billTitle = itemView.findViewById(R.id.billTitle);
            billprice = itemView.findViewById(R.id.billprice);
            anh = itemView.findViewById(R.id.anh);
            billStock = itemView.findViewById(R.id.billStock);
            ok = itemView.findViewById(R.id.ok);


        }
    }
}
