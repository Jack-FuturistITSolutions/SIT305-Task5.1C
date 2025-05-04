package com.example.task51c;

import android.os.Bundle;
import android.os.Handler;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView topStoriesRecyclerView, mainArticlesRecyclerView1, mainArticlesRecyclerView2;
    private SmallNewsArticleAdapter topStoriesAdapter;
    private NewsArticleAdapter  mainArticlesAdapter1, mainArticlesAdapter2;
    private List<SmallNewsArticle> topStories;
    private List<NewsArticle> mainArticles1, mainArticles2;
    private Handler handler;
    private int currentItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Create a list of smaller news articles
        topStories = new ArrayList<>();
        topStories.add(new SmallNewsArticle(1, "First look at a Blackhole Singularity", R.drawable.connect_image, "1hr ago", "Frank Goblin", "This is the body."));
        topStories.add(new SmallNewsArticle(2, "New technology that will extend life indefinitely", R.drawable.connect_image, "6hrs ago", "Frank Goblin", "This is the body."));
        topStories.add(new SmallNewsArticle(3, "Green energy and climate change a hoax?", R.drawable.connect_image, "Yesterday", "Frank Goblin", "This is the body."));

        // Set up the RecyclerView with horizontal layout
        topStoriesRecyclerView = findViewById(R.id.topRecyclerView);
        topStoriesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // Set the adapter
        topStoriesAdapter = new SmallNewsArticleAdapter(this, topStories);
        topStoriesRecyclerView.setAdapter(topStoriesAdapter);

        // Create a list of smaller news articles for the main recycler view
        mainArticles1 = new ArrayList<>();
        mainArticles1.add(new NewsArticle(1, "Trump tariffs on Australia dropped - 'It was a blatant insult to our Free Trade Agreement'", R.drawable.connect_image, "Apr 20, 2025", "Frank Goblin", "This is the body."));
        mainArticles1.add(new NewsArticle(2, "'China will be seeking an opportunity with the US to drop tariffs, including our reciprocal tariffs'", R.drawable.connect_image, "Yesterday", "Frank Goblin", "This is the body."));
        mainArticles1.add(new NewsArticle(3, "OpenAI drops their first, open-source AGI model for at home robotics applications", R.drawable.connect_image, "Apr 23, 2025", "Frank Goblin", "This is the body."));
        mainArticles1.add(new NewsArticle(4, "Katy Perry is being sued 50 million USD in response to her actions during her spaceflight", R.drawable.connect_image, "Apr 17, 2025", "Frank Goblin", "This is the body."));
        mainArticles1.add(new NewsArticle(5, "Klaus Schwab resigns - WEF seeking new 'Shadow Emperor' to fill his position", R.drawable.connect_image, "Apr 22, 2025", "Frank Goblin", "This is the body."));

        // Create a list of smaller news articles for the second main recycler view
        mainArticles2 = new ArrayList<>();
        mainArticles2.add(new NewsArticle(1, "Australia discovers new gold deposit - Another gold rush?", R.drawable.connect_image, "35mins ago", "Frank Goblin", "This is the body."));
        mainArticles2.add(new NewsArticle(2, "Israel condemns International Court for pursuing litigation", R.drawable.connect_image, "Apr 21, 2025", "Frank Goblin", "This is the body."));
        mainArticles2.add(new NewsArticle(3, "Homeless man discovers winning Tatt's Lotto Ticket - Rags to Riches", R.drawable.connect_image, "Apr 23, 2025", "Frank Goblin", "This is the body."));
        mainArticles2.add(new NewsArticle(4, "Elon Musk confirms he'd like to be the 'Emperor of Mars' someday", R.drawable.connect_image, "Apr 22, 2025", "Frank Goblin", "This is the body."));
        mainArticles2.add(new NewsArticle(5, "Greens decide to dissolve party - moved directly into Labor", R.drawable.connect_image, "Yesterday", "Frank Goblin", "This is the body."));

        // Set up the main RecyclerView with grid layout (4 items per row)
        mainArticlesRecyclerView1 = findViewById(R.id.mainRecyclerView1);
        mainArticlesRecyclerView1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // Set the adapter for main RecyclerView
        mainArticlesAdapter1 = new NewsArticleAdapter(this, mainArticles1);
        mainArticlesRecyclerView1.setAdapter(mainArticlesAdapter1);

        // Set up the main RecyclerView with grid layout (4 items per row)
        mainArticlesRecyclerView2 = findViewById(R.id.mainRecyclerView2);
        mainArticlesRecyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // Set the adapter for main RecyclerView
        mainArticlesAdapter2 = new NewsArticleAdapter(this, mainArticles2);
        mainArticlesRecyclerView2.setAdapter(mainArticlesAdapter2);

        // Handle system window insets for padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize the handler and set the timer to scroll every 3 seconds
        handler = new Handler();
        startAutoScroll();
    }

    // Add an auto scrolling animation to the top horizontal recycler view for smallNewsArticles
    private void startAutoScroll() {
        // Run the scroll update every 3 seconds
        Runnable autoScrollRunnable = new Runnable() {
            @Override
            public void run() {
                // Scroll to the next item in the list
                if (currentItem == topStories.size()) {
                    currentItem = 0;
                }

                // Smoothly scroll to the next item
                topStoriesRecyclerView.smoothScrollToPosition(currentItem);

                // Increment the item to show next
                currentItem++;

                // Re-run this task after 3 seconds
                handler.postDelayed(this, 3000);
            }
        };

        // Start the auto-scrolling loop
        handler.postDelayed(autoScrollRunnable, 3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Remove any pending posts of the runnable
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }
}
