package com.example.vechet.knongdai.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.vechet.knongdai.R;
import com.example.vechet.knongdai.callback.OnMainCateClickItemListener;
import com.example.vechet.knongdai.entity.MainCategoriesResponse;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainCategoryAdapter extends RecyclerView.Adapter<MainCategoryAdapter.CategoryViewHolder> {

    List<MainCategoriesResponse.DataEntity> mainCateList;
    public static final String BASE_URL = "http://110.74.194.125:15000";
    private Context context;
    private OnMainCateClickItemListener listener;

    public void onClickListener(OnMainCateClickItemListener listener){
        this.listener = listener;
    }

    public MainCategoriesResponse.DataEntity getMainCateById(int position){
        return mainCateList.get(position);
    }

    public MainCategoryAdapter(Context context) {
        this.mainCateList = new ArrayList<>();
        this.context = context;
    }

    public void getMainCate(List<MainCategoriesResponse.DataEntity> mainCates){
        mainCateList.addAll(mainCates);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.maincate_listitem,
                parent, false);
        return new CategoryViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        MainCategoriesResponse.DataEntity mainCate = this.mainCateList.get(position);
        holder.tvMainCategoriesTitle.setText(mainCate.getCateName());
        /*Picasso.get()
                .load(mainCate.getIconName())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.ivLogo);*/
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground);
        Glide.with(context)
                .load(mainCate.getIconName().contains("http") ?
                        mainCate.getIconName() : BASE_URL+"/"+mainCate.getIconName())
                .apply(options)
                .into(holder.ivLogo);
    }

    @Override
    public int getItemCount() {
        return this.mainCateList.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CircleImageView ivLogo;
        TextView tvMainCategoriesTitle;
        private OnMainCateClickItemListener listener;

        public CategoryViewHolder(View itemView, OnMainCateClickItemListener listener) {
            super(itemView);
            tvMainCategoriesTitle = itemView.findViewById(R.id.tvCategorieeTitle);
            ivLogo = itemView.findViewById(R.id.ivLogo);
            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(getAdapterPosition());
        }
    }
}
