package com.amador.david.googlebookssearch.presentation.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amador.david.googlebookssearch.R;
import com.amador.david.googlebookssearch.presentation.model.Book;
import com.bumptech.glide.Glide;

import org.json.JSONException;


public class DetailBookFragment extends Fragment implements SearchBook.DetailBook{

    private static final String ARG_PARAM1 = "book";

    private Book book;

    private ImageView imgBook;
    private TextView title, description, authors;

    public DetailBookFragment() {
        // Required empty public constructor
    }

    public static DetailBookFragment newInstance(Book book) {
        DetailBookFragment fragment = new DetailBookFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, book);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            book = (Book) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_detail_book, container, false);
        imgBook = view.findViewById(R.id.img_book);
        title = view.findViewById(R.id.title);
        description = view.findViewById(R.id.description);
        authors = view.findViewById(R.id.authors);

        /*Mostramos el libro que nos han pasado en el constructor*/
        showBook();

        return view;
    }

    @Override
    public void showBook() {
        if (book.getThumbnail()!=null) {
            Glide.with(getContext()).load(book.getThumbnail()).into(imgBook);
        }else {
            Glide.with(getContext()).load(R.drawable.ic_launcher_background).into(imgBook);
        }

        title.setText(book.getTitle());
        if (book.getAuthors()!=null && book.getAuthors().length()>0){
            String authors = "";
            for (int i = 0; i <book.getAuthors().length() ; i++) {
                try {
                    authors = authors + book.getAuthors().getString(i) + " ";
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

           this.authors.setText(authors);
        }
        if (book.getLongDescription() != null){
            description.setText(book.getLongDescription());
        }

    }
}
