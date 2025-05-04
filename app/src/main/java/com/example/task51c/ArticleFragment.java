package com.example.task51c;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ArticleFragment extends Fragment {

    private static final String ARG_TITLE = "title";
    private static final String ARG_DATE = "date";
    private static final String ARG_IMAGE = "image";
    private static final String ARG_AUTHOR = "author";
    private static final String ARG_BODY = "body";

    // Constructor
    public static ArticleFragment newInstance(String title, String date, int imageRes, String author, String body) {
        ArticleFragment fragment = new ArticleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_DATE, date);
        args.putInt(ARG_IMAGE, imageRes);
        args.putString(ARG_AUTHOR, author);
        args.putString(ARG_BODY, body);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article_detail, container, false);

        // Bind views
        TextView titleView = view.findViewById(R.id.textTitle);
        TextView dateView = view.findViewById(R.id.textDate);
        TextView authorView = view.findViewById(R.id.textAuthor);
        TextView bodyView = view.findViewById(R.id.textBody);
        ImageView imageView = view.findViewById(R.id.imageArticle);

        // Set content
        Bundle args = getArguments();
        if (args != null) {
            titleView.setText(args.getString(ARG_TITLE));
            dateView.setText(args.getString(ARG_DATE));
            authorView.setText("By " + args.getString(ARG_AUTHOR));
            bodyView.setText(args.getString(ARG_BODY));
            imageView.setImageResource(args.getInt(ARG_IMAGE));
        }

        // Get the root layout
        View rootLayout = view.findViewById(R.id.root_layout);

        // Clicking the background dismisses the fragment
        rootLayout.setOnClickListener(v -> {
            requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .remove(ArticleFragment.this)
                    .commit();

            requireActivity()
                    .getSupportFragmentManager()
                    .popBackStack(); // Remove from back stack as well

            // Reset the dim background
            View fragContainer = requireActivity().findViewById(R.id.fragment_container);
            if (fragContainer != null) {
                fragContainer.setBackgroundColor(android.graphics.Color.TRANSPARENT);
            }
        });

        // Set up the related stories recycler
        RecyclerView relatedRecycler = view.findViewById(R.id.relatedStoriesRecyclerView);
        relatedRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        // Show placeholder related stories
        List<RelatedArticle> relatedArticles = new ArrayList<>();
        relatedArticles.add(new RelatedArticle(1, "AI in Education: Future or Fad?", R.drawable.connect_image, "Apr 28, 2025", "Frank Goblin", "This is the body."));
        relatedArticles.add(new RelatedArticle(2, "Breakthrough in Climate Research", R.drawable.connect_image, "Apr 28, 2025", "Frank Goblin", "This is the body."));
        relatedArticles.add(new RelatedArticle(3, "SpaceX Announces Mars Colonization Date", R.drawable.connect_image, "Apr 28, 2025", "Frank Goblin", "This is the body."));

        RelatedArticleAdapter adapter = new RelatedArticleAdapter(requireContext(), relatedArticles);
        relatedRecycler.setAdapter(adapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // Hide the background overlay
        View container = requireActivity().findViewById(R.id.fragment_container);
        if (container != null) {
            container.setVisibility(View.GONE);
            container.setBackgroundColor(android.graphics.Color.TRANSPARENT);
        }
    }
}