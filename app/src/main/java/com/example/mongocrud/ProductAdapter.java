package com.example.mongocrud;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Product> productList;


    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_view, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.nametv.setText(product.getName());
        holder.categorytv.setText(product.getCategory()); // Example of using another field
        holder.pricetv.setText(String.valueOf(product.getPrice()));
       // String.valueOf(price)
        holder.mannufacturertv.setText(product.getManufacturer());
        holder.releasetv.setText(product.getReleaseDate());


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nametv;
        TextView categorytv;
        TextView mannufacturertv;
        TextView pricetv;
        TextView releasetv;

        public ViewHolder(View itemView) {
            super(itemView);
            nametv = itemView.findViewById(R.id.tvName);
            categorytv = itemView.findViewById(R.id.tvCategory);
            mannufacturertv= itemView.findViewById(R.id.tvManufacturer);
            pricetv = itemView.findViewById(R.id.tvPrice);
            releasetv = itemView.findViewById(R.id.tvReleaseDate);


        }
    }
}

