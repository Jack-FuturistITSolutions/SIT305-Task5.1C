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

public class RelatedArticleAdapter extends RecyclerView.Adapter<RelatedArticleAdapter.ViewHolder> {

    private Context context;
    private List<RelatedArticle> articles;

    // Constructor
    public RelatedArticleAdapter(Context context, List<RelatedArticle> articles) {
        this.context = context;
        this.articles = articles;
    }

    @Override
    public RelatedArticleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_related_article, parent, false);
        return new ViewHolder(view);
    }

    // Set the field views and open a fragment when pressed
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RelatedArticle article = articles.get(position);

        // Bind data to views
        holder.imageView.setImageResource(article.getImageResId());
        holder.titleView.setText(article.getTitle());
        holder.bodyView.setText(article.getBody());

        // Listen for whether an article is pressed, open a fragment
        holder.itemView.setOnClickListener(v -> {
            if (context instanceof AppCompatActivity) {
                AppCompatActivity activity = (AppCompatActivity) context;

                ArticleFragment detailFragment = ArticleFragment.newInstance(
                        article.getTitle(),
                        article.getDate(),
                        article.getImageResId(),
                        article.getAuthor(),
                        article.getBody()
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
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleView, bodyView;

        // Set the fields
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageThumbnail);
            titleView = itemView.findViewById(R.id.textTitle);
            bodyView = itemView.findViewById(R.id.textBody);
        }
    }
}

