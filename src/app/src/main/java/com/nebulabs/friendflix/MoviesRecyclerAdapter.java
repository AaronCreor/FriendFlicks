package com.nebulabs.friendflix;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import es.dmoral.toasty.Toasty;

import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

/**
 * Recycler view adapter for each Movie entity in movie list
 */
public class MoviesRecyclerAdapter extends RecyclerView.Adapter<MoviesRecyclerAdapter.ViewHolder> implements Filterable {

    private static final String TAG = "RecyclerAdapter";
    List<String[]> moviesList;
    List<String[]> unfilteredMoviesList;

    public MoviesRecyclerAdapter(List<String[]> moviesList) {
        this.moviesList = moviesList;
        this.unfilteredMoviesList = new ArrayList<String[]>(moviesList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.movieIDView.setText(moviesList.get(position)[0]);
        holder.textView.setText(moviesList.get(position)[1]);
        holder.rowCountTextView.setText(moviesList.get(position)[2]);
        Picasso.get().load(moviesList.get(position)[3]).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    @Override
    public Filter getFilter() {
        return myFilter;
    }

    Filter myFilter = new Filter() {

        // run on background thread
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<String[]> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0) {
                filteredList.addAll(unfilteredMoviesList);
            }
            else {
                for(String[] movie : unfilteredMoviesList) {
                    if (movie[1].toLowerCase().contains(constraint.toString().toLowerCase())) {
                        filteredList.add(movie);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;

            return filterResults;
        }

        // runs on a ui thread
        @Override
        protected void publishResults(CharSequence constraint, FilterResults filterResults) {
            moviesList.clear();
            moviesList.addAll((Collection<? extends String[]>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView textView, rowCountTextView, movieIDView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.recyclerItemImageView);
            textView = itemView.findViewById(R.id.recyclerItemTextView);
            rowCountTextView = itemView.findViewById(R.id.rowCountTextView);
            movieIDView = itemView.findViewById(R.id.movieIDview);

            itemView.setOnClickListener(this);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    moviesList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    return true;
                }
            });

        }

        @Override
        public void onClick(View view) {
            Toasty.info(view.getContext(), moviesList.get(getAdapterPosition())[0], Toast.LENGTH_SHORT).show();
            String movieID = moviesList.get(getAdapterPosition())[0];
            Fragment selectedFrag = new MovieFragment(movieID);
            ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_view, selectedFrag)
                    .commit();
        }
    }
}
