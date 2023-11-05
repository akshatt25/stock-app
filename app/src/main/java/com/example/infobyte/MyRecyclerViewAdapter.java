package com.example.infobyte;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    private List<StockItem> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;

    public MyRecyclerViewAdapter(Context context, List<StockItem> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.custom_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        StockItem item = mData.get(position);


        holder.symboleTextView.setText(item.getSYMBOLE());
        holder.nameTextView.setText(item.getNAME());
        holder.nkTextView.setText(String.valueOf(item.getP_D_CLOSE()));
        holder.nameTextView.setSelected(true);

        double changePrice = item.getChangeInPRICE();
        if (changePrice < 0) {
            holder.changePriceTextView.setTextColor(Color.parseColor("#E25B3E")); // Red for negative values
            holder.changePriceTextView.setText(String.format("-%s", Math.abs(changePrice)));
        } else {
            holder.changePriceTextView.setTextColor(Color.parseColor("#7EC761")); // Green for positive values
            holder.changePriceTextView.setText(String.format("+%s", changePrice));
        }

        // Check if the percentage change is negative and set text color accordingly
        double changePerc = item.getPerc_change();
        if (changePerc < 0) {
            holder.changePercTextView.setTextColor(Color.parseColor("#E25B3E")); // Red for negative values
            holder.changePercTextView.setText(String.format("-%s%%", Math.abs(changePerc)));
        } else {
            holder.changePercTextView.setTextColor(Color.parseColor("#7EC761")); // Green for positive values
            holder.changePercTextView.setText(String.format("+%s%%", changePerc));
        }


        // Load an image using Glide for the CircleImageView
//        Glide.with(context)
//                .load(R.drawable.login_art) // Replace with the actual image URL or resource
//                .transition(DrawableTransitionOptions.withCrossFade())
//                .into(holder.loginArtImageView);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView symboleTextView;
        TextView nameTextView;
        TextView nkTextView;
        TextView changePriceTextView;
        TextView changePercTextView;
        CircleImageView loginArtImageView;

        ViewHolder(View itemView) {
            super(itemView);
            symboleTextView = itemView.findViewById(R.id.symbole);
            nameTextView = itemView.findViewById(R.id.name);
            nkTextView = itemView.findViewById(R.id.nk);
            changePriceTextView = itemView.findViewById(R.id.changePrice);
            changePercTextView = itemView.findViewById(R.id.changePerc);
            loginArtImageView = itemView.findViewById(R.id.loginArt);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public StockItem getItem(int id) {
        return mData.get(id);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setData(List<StockItem> data) {
        mData = data;
        notifyDataSetChanged();
    }
}
