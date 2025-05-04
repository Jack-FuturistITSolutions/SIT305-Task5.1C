package com.example.task51c;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NewsArticleAdapter extends RecyclerView.Adapter<NewsArticleAdapter.NewsViewHolder> {

    private Context context;
    private List<NewsArticle> articles;

    // Constructor
    public NewsArticleAdapter(Context context, List<NewsArticle> articles) {
        this.context = context;
        this.articles = articles;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_news_article, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        NewsArticle article = articles.get(position);

        // Bind data to views
        holder.textHook.setText(article.getTitle());
        holder.textDate.setText(article.getDate());
        holder.imageThumbnail.setImageResource(article.getImageResource());

        // Listen for whether an article is pressed, open a fragment
        holder.itemView.setOnClickListener(v -> {
            if (context instanceof AppCompatActivity) {
                AppCompatActivity activity = (AppCompatActivity) context;

                ArticleFragment detailFragment = ArticleFragment.newInstance(
                        article.getTitle(),
                        article.getDate(),
                        article.getImageResource(),
                        article.getAuthor(),
                        article.getArticleBody()
                );

                FragmentTransaction transaction = activity
                        .getSupportFragmentManager()
                        .beginTransaction();

                transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                transaction.add(R.id.fragment_container, detailFragment);
                transaction.addToBackStack(null);
                transaction.commit();

                // Ensure fragment container is visible
                View container = activity.findViewById(R.id.fragment_container);
                if (container != null) {
                    container.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    // Set the view holder
    public static class NewsViewHolder extends RecyclerView.ViewHolder {

        TextView textHook, textDate;
        ImageView imageThumbnail;

        // Set the fields
        public NewsViewHolder(View itemView) {
            super(itemView);
            textHook = itemView.findViewById(R.id.textHook);
            textDate = itemView.findViewById(R.id.textDate);
            imageThumbnail = itemView.findViewById(R.id.imageThumbnail);
        }
    }
}
