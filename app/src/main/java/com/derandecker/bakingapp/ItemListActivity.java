package com.derandecker.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.derandecker.bakingapp.database.AppDatabase;
import com.derandecker.bakingapp.model.Ingredients;
import com.derandecker.bakingapp.model.Recipe;
import com.derandecker.bakingapp.model.RecipeWithIngredients;
import com.derandecker.bakingapp.model.RecipeWithSteps;
import com.derandecker.bakingapp.model.Steps;
import com.derandecker.bakingapp.utils.AppExecutors;
import com.derandecker.bakingapp.utils.JSONUtils;
import com.derandecker.bakingapp.utils.NetworkUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.derandecker.bakingapp.dummy.DummyContent;

import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private URL recipesUrl;
    AppDatabase database;

    {
        try {
            recipesUrl = new URL("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());


        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        database = AppDatabase.getInstance(getApplicationContext());

        downloadRecipes();


        View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);


    }

    private void downloadRecipes() {
        AppExecutors.getInstance().networkIO().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String recipeString;
                    if (isOnline()) {
                        recipeString = NetworkUtils.getResponseFromHttpUrl(recipesUrl);
                    } else {
                        return;
                    }
                    List<Recipe> recipes = JSONUtils.parseRecipesJson(recipeString);
                    database.RecipeDao().insertRecipeNamesAndServings(recipes);
                    List<Ingredients> ingredients = JSONUtils.parseIngredientsJson(recipeString, 3);
                    database.RecipeDao().insertIngredients(ingredients);

                    RecipeWithIngredients ingredientsFromDb = database.RecipeDao().loadRecipeIngredients(3);
                    Log.d("RECIPE NAME", ingredientsFromDb.recipe.getName());
                    Log.d("RECIPE INGRED", ingredientsFromDb.ingredients.get(2).ingredient);
                    Log.d("RECIPE quantity", ingredientsFromDb.ingredients.get(2).quantity);
                    Log.d("RECIPE measure", ingredientsFromDb.ingredients.get(2).measure);

                    List<Steps> steps = JSONUtils.parseStepsJson(recipeString, 3);
                    database.RecipeDao().insertSteps(steps);
                    RecipeWithSteps stepsFromDb = database.RecipeDao().loadRecipeSteps(3);
                    Log.d("RECIPE step id", String.valueOf(stepsFromDb.steps.get(2).stepNumber));
                    Log.d("RECIPE step shortD", stepsFromDb.steps.get(2).shortDescription);
                    Log.d("RECIPE step descr", stepsFromDb.steps.get(2).description);
                    Log.d("RECIPE step URL", stepsFromDb.steps.get(2).videoUrl);

//                    Log.d("RECIPE ingred size", String.valueOf(ingredientsFromDb.ingredients.size()));
                    /* TODO

                    6. Save recipes JSON string to local storage in some way so the server
                        isn't queried every time we need to reference the json string
                     */


                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }

        });

    }

    private boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, mTwoPane));
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final ItemListActivity mParentActivity;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DummyContent.DummyItem item = (DummyContent.DummyItem) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(ItemDetailFragment.ARG_ITEM_ID, item.id);
                    ItemDetailFragment fragment = new ItemDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.item_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ItemDetailActivity.class);
                    intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, item.id);

                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(ItemListActivity parent,
                                      boolean twoPane) {
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
//            holder.mContentView.setText(mValues.get(position).name);

//            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return 0;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mContentView;

            ViewHolder(View view) {
                super(view);
                mContentView = (TextView) view.findViewById(R.id.content);
            }
        }
    }
}
