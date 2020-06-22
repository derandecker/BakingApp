package com.derandecker.bakingapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.derandecker.bakingapp.R;
import com.derandecker.bakingapp.model.Recipe;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.TextViewHolder> {
        private LayoutInflater inflater;
        private Context context;
        private List<Recipe> mRecipes;
        final private RecipeClickListener mOnClickListener;
        private Recipe recipe;

        public interface RecipeClickListener {
            void onListItemClick(List<Recipe> mRecipes, int clickedItemIndex);
        }

        public RecipeAdapter(Context context, RecipeClickListener listener) {
            inflater = LayoutInflater.from(context);
            mOnClickListener = listener;
            this.context = context;
        }

        @Override
        public TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.item_list_content, parent, false);
            TextViewHolder holder = new TextViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(TextViewHolder holder, int position) {
            displayRecipes(holder, position);
        }

        private void displayRecipes(TextViewHolder holder, int position) {
            recipe = mRecipes.get(position);
            holder.recipeName.setText(recipe.name);
            holder.recipeServings.setText(recipe.servings);
        }

        @Override
        public int getItemCount() {
            if (mRecipes == null) {
                return 0;
            } else {
                return mRecipes.size();
            }
        }


        public void setRecipes(List<Recipe> recipes) {
            mRecipes = recipes;
            notifyDataSetChanged();
        }


        class TextViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView recipeName;
            TextView recipeServings;

            public TextViewHolder(View itemView) {
                super(itemView);
                recipeName = (TextView) itemView.findViewById(R.id.recipeNameTv);
                recipeServings = (TextView) itemView.findViewById(R.id.recipeServingsTv);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                int clickedPosition = getAdapterPosition();
                mOnClickListener.onListItemClick(mRecipes, clickedPosition);
            }
        }

    }
