package com.example.inder.newsapplication.Adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.inder.newsapplication.Common;
import com.example.inder.newsapplication.ListNews;
import com.example.inder.newsapplication.R;
import com.example.inder.newsapplication.Interface.IconBetterIdeaService;
import com.example.inder.newsapplication.Interface.ItemClickListener;
import com.example.inder.newsapplication.model.Website;

import de.hdodenhof.circleimageview.CircleImageView;

class ListSourceViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener
{
    ItemClickListener itemClickListener;

    TextView source_title;
    CircleImageView source_image;

    public ListSourceViewHolder(View itemView) {
        super(itemView);

        source_image = (CircleImageView) itemView.findViewById(R.id.source_image);
        source_title = (TextView)itemView.findViewById(R.id.source_name);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}

public class ListSourceAdapter extends RecyclerView.Adapter<ListSourceViewHolder>{
    private Context context;
    private Website website;

    private IconBetterIdeaService mService;

    public ListSourceAdapter(Context context, Website webSite) {
        this.context = context;
        this.website = webSite;

        mService = Common.getIconService();
    }

    @Override
    public ListSourceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.source_layout,parent,false);
        return new ListSourceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ListSourceViewHolder holder, int position) {



        holder.source_title.setText(website.getSources().get(position).getName());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent intent = new Intent(context, ListNews.class);
                intent.putExtra("source",website.getSources().get(position).getId());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return website.getSources().size();
    }
}