package com.devilsoftware.fgallery;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.devilsoftware.fgallery.API.Models.Hit;

import java.util.List;

public class PhotoItemRecyclerViewAdapter extends RecyclerView.Adapter<PhotoItemRecyclerViewAdapter.ViewHolder> {


    PhotoItemRecyclerViewAdapter(Context context, List<String> urlsPrw, List<Hit> hits) {
        this.mContext = context;
        this.urls = urlsPrw;
        this.hits = hits;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View photoView = inflater.inflate(R.layout.photo_item, parent, false);
        return new ViewHolder(photoView);
    }

    private List<String> urls;
    private Context mContext;
    private List<Hit> hits;

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        ImageView imageView = holder.mPhotoImageView;

        Glide.with(mContext)
                .load(urls.get(position))
                .asBitmap()
                .into(imageView);

    }

    @Override
    public int getItemCount() {
        return urls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView mPhotoImageView;

        ViewHolder(View itemView) {

            super(itemView);
            mPhotoImageView = itemView.findViewById(R.id.iv_photo);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            int position = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION) {

                Intent intent = new Intent(mContext, PhotoActivity.class);
                intent.putExtra("url",hits.get(position).getLargeImageURL());
                mContext.startActivity(intent);
            }
        }
    }
}
