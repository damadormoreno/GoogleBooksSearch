package com.amador.david.googlebookssearch.presentation.view.fragments;

import com.amador.david.googlebookssearch.presentation.model.Book;

import java.util.ArrayList;

/**
 * Created by david on 26/11/17.
 */

public interface SearchBook {

    public interface Search{
        /*Mostramos la lista de libros*/
        void showList(ArrayList<Book> books);
        /*Muestra error*/
        void showError();
    }

    public interface DetailBook{
        void showBook();
    }

}
