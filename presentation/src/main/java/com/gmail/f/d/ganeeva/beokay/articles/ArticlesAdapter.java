package com.gmail.f.d.ganeeva.beokay.articles;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gmail.f.d.ganeeva.beokay.R;
import com.gmail.f.d.ganeeva.domain.entity.ArticleDomainModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diana on 19.11.2017 at 15:17.
 */

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder>{
    private List<ArticleDomainModel> articles = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_article, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ArticleDomainModel article = articles.get(position);
        holder.setIsRecyclable(false);
        holder.titleTV.setText(article.getTitle());
        holder.textTV.setText(article.getText());
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public void setArticles(List<ArticleDomainModel> articles) {
        this.articles.addAll(articles);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTV;
        public TextView textTV;
        private boolean visible;

        public ViewHolder(View v) {
            super(v);
            titleTV = (TextView) v.findViewById(R.id.title);
            textTV = (TextView) v.findViewById(R.id.text);
            visible = false;
            textTV.setVisibility(View.GONE);
            titleTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (visible) {
                        textTV.setVisibility(View.GONE);
                        visible = false;
                    } else {
                        textTV.setVisibility(View.VISIBLE);
                        visible = true;
                    }
                }
            });

        }
    }
}
