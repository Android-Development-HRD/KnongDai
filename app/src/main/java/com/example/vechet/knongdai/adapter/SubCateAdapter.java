package com.example.vechet.knongdai.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vechet.knongdai.R;
import com.example.vechet.knongdai.callback.OnSubCateItemClickListener;
import com.example.vechet.knongdai.entity.SubByMainIdResponse;

import java.util.ArrayList;
import java.util.List;

public class SubCateAdapter extends RecyclerView.Adapter<SubCateAdapter.SubCateViewHolder> {

    private List<SubByMainIdResponse.DataEntity> subCateList;
    private OnSubCateItemClickListener listener;

    public void OnSubCateClickListener(OnSubCateItemClickListener listener){
        this.listener = listener;
    }

    public SubByMainIdResponse.DataEntity getSubCateByMainCateId(int positon){
        return subCateList.get(positon);
    }

    public SubCateAdapter() {
        this.subCateList = new ArrayList<>();
    }

    public void getSubCate(List<SubByMainIdResponse.DataEntity> subCates){
        subCateList.addAll(subCates);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SubCateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.subcate_listitem,
                parent, false);
        return new SubCateViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull SubCateViewHolder holder, int position) {
        SubByMainIdResponse.DataEntity subCate = this.subCateList.get(position);
        holder.tvSubCateTitle.setText(subCate.getCateName());
    }

    @Override
    public int getItemCount() {
        return this.subCateList.size();
    }

    class SubCateViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private OnSubCateItemClickListener listener;
        TextView tvSubCateTitle;
        public SubCateViewHolder(View itemView, OnSubCateItemClickListener listener) {
            super(itemView);
            tvSubCateTitle = itemView.findViewById(R.id.tvSubCategoriesTitle);
            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onSubCateItemClick(getAdapterPosition());
        }
    }
}
