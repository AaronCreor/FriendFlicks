package com.nebulabs.friendflix;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.dmoral.toasty.Toasty;

import androidx.fragment.app.Fragment;

public class GroupsRecyclerAdapter extends RecyclerView.Adapter<GroupsRecyclerAdapter.ViewHolder> {

    private static final String TAG = "RecyclerAdapter";
    List<String> groupsList;

    public GroupsRecyclerAdapter(List<String> moviesList) {
        this.groupsList = moviesList;
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
        holder.rowCountTextView.setText(String.valueOf(position));
        holder.textView.setText(groupsList.get(position));
    }

    @Override
    public int getItemCount() {
        return groupsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView textView, rowCountTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            rowCountTextView = itemView.findViewById(R.id.rowCountTextView);

            itemView.setOnClickListener(this);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    groupsList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    return true;
                }
            });

        }

        @Override
        public void onClick(View view) {
            Toasty.info(view.getContext(), groupsList.get(getAdapterPosition()), Toast.LENGTH_SHORT).show();
            String name = groupsList.get(getAdapterPosition());
            Fragment selectedFrag = new MovieFragment(name);
            ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_view, selectedFrag)
                    .commit();
        }
    }
}
