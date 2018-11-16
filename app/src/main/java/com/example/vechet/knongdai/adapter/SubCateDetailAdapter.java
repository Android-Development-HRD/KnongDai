package com.example.vechet.knongdai.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vechet.knongdai.R;
import com.example.vechet.knongdai.callback.OnSubCateDetailItemClickListener;
import com.example.vechet.knongdai.entity.SubCateDetialResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SubCateDetailAdapter extends RecyclerView.Adapter<SubCateDetailAdapter.SubCateDetailViewHolder> {

    private List<SubCateDetialResponse.DataEntity> subCateDetailList;
    private OnSubCateDetailItemClickListener listener;

    public void onSubCateDetailClickListener(OnSubCateDetailItemClickListener listener){
        this.listener = listener;
    }

    public SubCateDetialResponse.DataEntity getSubCateDetailById(int position){
        return subCateDetailList.get(position);
    }

    public SubCateDetailAdapter() {
        this.subCateDetailList = new ArrayList<>();
    }

    public void getSubCateDetail(List<SubCateDetialResponse.DataEntity> subCateDetail){
        subCateDetailList.addAll(subCateDetail);
        notifyDataSetChanged();
    }

    //clear adapter
    public void clearData(){
        this.subCateDetailList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SubCateDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.subcatedetail_listitem,
                parent, false);
        return new SubCateDetailViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull SubCateDetailViewHolder holder, int position) {
        SubCateDetialResponse.DataEntity subCateDetail = this.subCateDetailList.get(position);
        holder.tvSubCateDetailTitle.setText(subCateDetail.getTitle());
        if(subCateDetail.getPhone() != null){
            holder.ivSubCateDetailPhoneLogo.setVisibility(View.VISIBLE);
            holder.tvSubCateDetailPhone.setVisibility(View.VISIBLE);
            holder.tvSubCateDetailPhone.setText(subCateDetail.getPhone());

        }
        holder.tvSubCateDetailWebsite.setText(subCateDetail.getLink());
        if (subCateDetail.getEmail() != null){
            holder.ivSubCateDetailEmailLogo.setVisibility(View.VISIBLE);
            holder.tvSubCateDetailEmail.setVisibility(View.VISIBLE);
            holder.tvSubCateDetailEmail.setText(subCateDetail.getEmail());
        }
        if(subCateDetail.getAddress() != null){
            holder.ivSubCateDetailAddressLogo.setVisibility(View.VISIBLE);
            holder.tvSubCateDetailAddress.setVisibility(View.VISIBLE);
            holder.tvSubCateDetailAddress.setText(subCateDetail.getAddress());
        }
        Picasso.get()
                .load(subCateDetail.getPicUrl())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.ivSubCateDetailLogo);
    }

    @Override
    public int getItemCount() {
        return this.subCateDetailList.size();
    }

    class SubCateDetailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView ivSubCateDetailLogo, ivSubCateDetailEmailLogo,
                ivSubCateDetailAddressLogo, ivSubCateDetailPhoneLogo;
        TextView tvSubCateDetailTitle, tvSubCateDetailPhone, tvSubCateDetailEmail,
                tvSubCateDetailAddress, tvSubCateDetailWebsite;
        private OnSubCateDetailItemClickListener listener;

        public SubCateDetailViewHolder(View itemView, OnSubCateDetailItemClickListener listener) {
            super(itemView);
            ivSubCateDetailLogo = itemView.findViewById(R.id.ivSubCateDetailLogo);
            tvSubCateDetailAddress = itemView.findViewById(R.id.tvSubcateDetailAddress);
            ivSubCateDetailAddressLogo = itemView.findViewById(R.id.ivSubCateDetailAddressLogo);
            tvSubCateDetailEmail = itemView.findViewById(R.id.tvSubcateDetailEmail);
            ivSubCateDetailEmailLogo = itemView.findViewById(R.id.ivSubCateDetailEmailLogo);
            tvSubCateDetailPhone = itemView.findViewById(R.id.tvSubcateDetailPhone);
            ivSubCateDetailPhoneLogo = itemView.findViewById(R.id.ivSubCateDetailPhoneLogo);
            tvSubCateDetailWebsite = itemView.findViewById(R.id.tvSubcateDetailWebsite);
            tvSubCateDetailTitle = itemView.findViewById(R.id.tvSubcateDetailTitle);
            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onSubCateDetailItemClick(getAdapterPosition());
        }
    }
}
