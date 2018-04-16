package com.example.inder.newsapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.inder.newsapplication.DetailArticle;
import com.example.inder.newsapplication.R;
import com.example.inder.newsapplication.model.Article;
import com.example.inder.newsapplication.Interface.ItemClickListener;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

class ListNewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    ItemClickListener itemClickListener;

    TextView article_title;
    TextView article_time;
    CircleImageView article_image;

    public ListNewsViewHolder(View itemView) {
        super(itemView);
        article_image = (CircleImageView)itemView.findViewById(R.id.article_image);
        article_title = (TextView)itemView.findViewById(R.id.article_title);
        article_time = (TextView) itemView.findViewById(R.id.article_time);

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

public class ListNewsAdapter extends RecyclerView.Adapter<ListNewsViewHolder> {
    private List<Article> articleList;
    private Context context;

    public ListNewsAdapter(List<Article> articleList, Context context) {
        this.articleList = articleList;
        this.context = context;
    }

    @Override
    public ListNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.news_layout,parent,false);
        return new ListNewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListNewsViewHolder holder, int position) {

        Glide.with(context)
                .load(articleList.get(position).getUrlToImage())
                .into(holder.article_image);

        if(articleList.get(position).getTitle().length() > 65)
            holder.article_title.setText(articleList.get(position).getTitle().substring(0,65)+"...");
        else
            holder.article_title.setText(articleList.get(position).getTitle());

        if(articleList.get(position).getPublishedAt() != null) {
            String date = null;
                date = articleList.get(position).getPublishedAt();
            holder.article_time.setText(date);
        }

        //Set Event Click
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent detail = new Intent(context,DetailArticle.class);
                detail.putExtra("webURL",articleList.get(position).getUrl());
                context.startActivity(detail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }
}