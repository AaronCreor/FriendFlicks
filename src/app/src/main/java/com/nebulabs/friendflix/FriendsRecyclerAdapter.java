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
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import es.dmoral.toasty.Toasty;

import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

/**
 * Recycler view adapter for each friend entity in friend list of user
 */
public class FriendsRecyclerAdapter extends RecyclerView.Adapter<FriendsRecyclerAdapter.ViewHolder> {

    private static final String TAG = "RecyclerAdapter";
    List<String[]> friendsList;
    List<String[]> unfilteredFriendsList;

    public FriendsRecyclerAdapter(List<String[]> friendsList) {
        this.friendsList = friendsList;
        this.unfilteredFriendsList = new ArrayList<String[]>(friendsList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.friends_recycler_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.recyclerItemTextView.setText(friendsList.get(position)[0]);
        holder.rowCountTextView.setText(friendsList.get(position)[1]);
        Picasso.get().load(friendsList.get(position)[2]).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return friendsList.size();
    }

    public Filter getFilter() {
        return myFilter;
    }

    Filter myFilter = new Filter() {

        // run on background thread
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<String[]> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0) {
                filteredList.addAll(unfilteredFriendsList);
            }
            else {
                for(String[] friend : unfilteredFriendsList) {
                    if (friend[0].toLowerCase().contains(constraint.toString().toLowerCase())) {
                        filteredList.add(friend);
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
            friendsList.clear();
            friendsList.addAll((Collection<? extends String[]>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView recyclerItemTextView, rowCountTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.recyclerItemUserImage);
            recyclerItemTextView = itemView.findViewById(R.id.recyclerItemTextView);
            rowCountTextView = itemView.findViewById(R.id.rowCountTextView);

            itemView.setOnClickListener(this);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    friendsList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    return true;
                }
            });

        }

        @Override
        public void onClick(View view) {
            String name = friendsList.get(getAdapterPosition())[0];
            String email = friendsList.get(getAdapterPosition())[1];
            String image = friendsList.get(getAdapterPosition())[2];
            Fragment selectedFrag = new FriendFragment(name, email);
            ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_view, selectedFrag)
                    .commit();
        }
    }
}
