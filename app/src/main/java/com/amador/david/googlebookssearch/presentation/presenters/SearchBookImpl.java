package com.amador.david.googlebookssearch.presentation.presenters;

import com.amador.david.googlebookssearch.presentation.model.Book;
import com.amador.david.googlebookssearch.presentation.model.DataMapper;
import com.amador.david.googlebookssearch.presentation.model.GetBooksUseCase;
import com.amador.david.googlebookssearch.presentation.model.GetBooksUseCaseImpl;
import com.amador.david.googlebookssearch.presentation.view.fragments.SearchBook;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by david on 26/11/17.
 */

public class SearchBookImpl extends JsonHttpResponseHandler implements SearchBookPresenter {

    private SearchBook.Search view;
    private GetBooksUseCase getBooksUseCase;

    public SearchBookImpl(SearchBook.Search view) {
        this.view = view;
        this.getBooksUseCase = new GetBooksUseCaseImpl();
    }

    @Override
    public void getBooks(String query) {
        if (query.length()>0) {
            getBooksUseCase.getBooks(query, this);
        }else {
            view.showList(new ArrayList<Book>());
        }
    }

    /*Callbacks de la llamada al servicio*/
    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        super.onSuccess(statusCode, headers, response);
        DataMapper dataMapper = new DataMapper(response);
        try {
            view.showList(dataMapper.JSONtoArrayBooks());
        } catch (JSONException e) {
            view.showError();
        }
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
        super.onFailure(statusCode, headers, throwable, errorResponse);
        view.showError();
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
        super.onFailure(statusCode, headers, responseString, throwable);
        view.showError();
    }
}
