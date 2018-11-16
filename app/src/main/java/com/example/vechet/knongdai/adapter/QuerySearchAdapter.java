package com.example.vechet.knongdai.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vechet.knongdai.R;
import com.example.vechet.knongdai.callback.OnQuerySearchClickItemListener;
import com.example.vechet.knongdai.entity.QuerySearchResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class QuerySearchAdapter extends RecyclerView.Adapter<QuerySearchAdapter.QuerySearchViewHolder> {

    private List<QuerySearchResponse.DataEntity> querySearchList;
    private OnQuerySearchClickItemListener listener;

    public void onQuerySearchClickListener(OnQuerySearchClickItemListener listener){
        this.listener = listener;
    }

    public QuerySearchResponse.DataEntity getQuerySearchById(int position){
        return querySearchList.get(position);
    }

    public QuerySearchAdapter() {
        this.querySearchList = new ArrayList<>();
    }

    public void getQuerySearch(List<QuerySearchResponse.DataEntity> querySearch){
        querySearchList.addAll(querySearch);
        notifyDataSetChanged();
    }

    public void clearSearchData(){
        this.querySearchList.clear();
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public QuerySearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.querysearch_listitem,
                parent, false);
        return new QuerySearchViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull QuerySearchViewHolder holder, int position) {
        QuerySearchResponse.DataEntity querySearch = this.querySearchList.get(position);
        holder.tvSearchTitle.setText(querySearch.getTitle());
        if(querySearch.getPhone() != null){
            holder.ivSearchPhoneLogo.setVisibility(View.VISIBLE);
            holder.tvSearchPhone.setVisibility(View.VISIBLE);
            holder.tvSearchPhone.setText(querySearch.getPhone());

        }
        holder.tvSearchWebsite.setText(querySearch.getLink());
        if (querySearch.getEmail() != null){
            holder.ivSearchEmailLogo.setVisibility(View.VISIBLE);
            holder.tvSearchEmail.setVisibility(View.VISIBLE);
            holder.tvSearchEmail.setText(querySearch.getEmail());
        }
        if(querySearch.getAddress() != null){
            holder.ivSearchAddressLogo.setVisibility(View.VISIBLE);
            holder.tvSearchAddress.setVisibility(View.VISIBLE);
            holder.tvSearchAddress.setText(querySearch.getAddress());
        }
        Picasso.get()
                .load(querySearch.getPicUrl())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.ivSearchLogo);
    }

    @Override
    public int getItemCount() {
        return this.querySearchList.size();
    }

    class QuerySearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvSearchAddress, tvSearchWebsite, tvSearchEmail, tvSearchPhone, tvSearchTitle;
        ImageView ivSearchLogo, ivSearchWebsiteLogo, ivSearchAddressLogo, ivSearchEmailLogo,
        ivSearchPhoneLogo;
        private OnQuerySearchClickItemListener listener;

        public QuerySearchViewHolder(View itemView, OnQuerySearchClickItemListener listener){
            super(itemView);
            tvSearchAddress = itemView.findViewById(R.id.tvSearchAddress);
            tvSearchEmail = itemView.findViewById(R.id.tvSearchEmail);
            tvSearchPhone = itemView.findViewById(R.id.tvSearchPhone);
            tvSearchTitle = itemView.findViewById(R.id.tvSearchTitle);
            tvSearchWebsite = itemView.findViewById(R.id.tvSearchWebsite);
            ivSearchLogo = itemView.findViewById(R.id.ivSearchLogo);
            ivSearchAddressLogo = itemView.findViewById(R.id.ivSearchAddressLogo);
            ivSearchEmailLogo = itemView.findViewById(R.id.ivSearchEmailLogo);
            ivSearchWebsiteLogo = itemView.findViewById(R.id.ivSearchWebsiteLogo);
            ivSearchPhoneLogo = itemView.findViewById(R.id.ivSearchPhoneLogo);
            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onQuerySearchClickItem(getAdapterPosition());
        }
    }
}
