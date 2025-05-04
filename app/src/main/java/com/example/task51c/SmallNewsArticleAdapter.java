package com.example.task51c;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SmallNewsArticleAdapter extends RecyclerView.Adapter<SmallNewsArticleAdapter.SmallNewsViewHolder> {

    private Context context;
    private List<SmallNewsArticle> articles;

    // Constructor
    public SmallNewsArticleAdapter(Context context, List<SmallNewsArticle> articles) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public SmallNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_small_news_article, parent, false);
        return new SmallNewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SmallNewsViewHolder holder, int position) {
        SmallNewsArticle article = articles.get(position);
        holder.title.setText(article.getTitle());
        holder.date.setText(article.getDate());
        holder.imageView.setImageResource(article.getImageResource());

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
    public static class SmallNewsViewHolder extends RecyclerView.ViewHolder {

        TextView title, date;
        ImageView imageView;

        // Set the fields
        public SmallNewsViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textHook);
            date = itemView.findViewById(R.id.textDate);
            imageView = itemView.findViewById(R.id.imageThumbnail);
        }
    }
}
