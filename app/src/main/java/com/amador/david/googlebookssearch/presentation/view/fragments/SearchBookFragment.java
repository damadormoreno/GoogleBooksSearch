package com.amador.david.googlebookssearch.presentation.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.amador.david.googlebookssearch.R;
import com.amador.david.googlebookssearch.presentation.model.Book;
import com.amador.david.googlebookssearch.presentation.presenters.SearchBookImpl;
import com.amador.david.googlebookssearch.presentation.presenters.SearchBookPresenter;
import com.amador.david.googlebookssearch.presentation.view.adapters.BooksAdapter;

import java.util.ArrayList;


public class SearchBookFragment extends Fragment implements SearchBook.Search{

    private SearchBookPresenter presenter;

    private SearchView searchView;
    private RecyclerView bookList;

    private OnBookClickListener listener;
    private BooksAdapter booksAdapter;

    public SearchBookFragment() {
    }

    public static SearchBookFragment newInstance(ArrayList<Book> books, String param2) {
        SearchBookFragment fragment = new SearchBookFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_search_book, container, false);
        searchView = view.findViewById(R.id.search);
        bookList = view.findViewById(R.id.recycler);

        /*Establecemos la barra de búsqueda expandida y sin el foco*/
        searchView.onActionViewExpanded();
        searchView.setFocusable(false);
        searchView.clearFocus();

        presenter = new SearchBookImpl(this);

        /*Preparamos el recyclerView*/
        setupRecyclerView();

        /*Método que escucha la introducción de caracteres en el buscador*/
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                /*Por cada letra introducida hacemos la petición a nuestro
                * presenter*/
                presenter.getBooks(newText);
                return true;
            }
        });

        return view;
    }
    /*Preparamos el listado vacío y establecemos su listener que va a escuchar los clicks
    * en cada libro.*/
    private void setupRecyclerView() {
        booksAdapter = new BooksAdapter(new ArrayList<Book>());
        bookList.setLayoutManager(new LinearLayoutManager(getActivity()));
        bookList.setAdapter(booksAdapter);
        ((BooksAdapter)bookList.getAdapter()).setOnItemClickListener(onItemClickListener);
    }

    /*El usuario hace clik en el libro para ir al detalle*/
    private BooksAdapter.OnItemClickListener onItemClickListener =
            new BooksAdapter.OnItemClickListener() {
                @Override
                public void onBookItemClicked(Book book) {
                    onBookClick(book);
                }
            };

    public void onBookClick(Book book) {
        if (listener != null) {
            listener.onBookClickListener(book);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnBookClickListener) {
            listener = (OnBookClickListener) context;
        }else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void showList(ArrayList<Book> books) {
        ((BooksAdapter)bookList.getAdapter()).updateList(books);
    }
    /*Si se produce cualquier error mostramos un mensaje informando.*/
    @Override
    public void showError() {
        Toast.makeText(getActivity(), getString(R.string.error), Toast.LENGTH_SHORT).show();
    }

    public interface OnBookClickListener {
        void onBookClickListener(Book book);
    }
}
