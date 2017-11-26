package com.amador.david.googlebookssearch.presentation.view.adapters;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amador.david.googlebookssearch.R;
import com.amador.david.googlebookssearch.presentation.model.Book;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by david on 26/11/17.
 */

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder>{
    private ArrayList<Book> books;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public BooksAdapter(ArrayList<Book> books) {
        this.books = books;
    }

    public interface OnItemClickListener {
        void onBookItemClicked(Book book);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        context = parent.getContext();
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Book currentBook = books.get(position);
        holder.title.setText(currentBook.getTitle());
        if (currentBook.getLongDescription()!=null){
            holder.description.setText(currentBook.getLongDescription());
        }

        if (currentBook.getSmallThumbnail()!=null){
            Glide.with(context).load(currentBook.getSmallThumbnail()).into(holder.imageBook);
        }else {
            Glide.with(context).load(R.drawable.ic_launcher_background).into(holder.imageBook);
        }

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (BooksAdapter.this.onItemClickListener!=null){
                    BooksAdapter.this.onItemClickListener.onBookItemClicked(currentBook);
                }
            }
        });
    }

    public void setOnItemClickListener (OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return books.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageBook;
        TextView title, description;
        ConstraintLayout layout;

        public ViewHolder(View itemView) {
            super(itemView);
            imageBook = itemView.findViewById(R.id.img_book);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            layout = itemView.findViewById(R.id.layout);
        }
    }

    public void updateList(ArrayList<Book> list){
        books = list;
        notifyDataSetChanged();
    }
}
