package com.amador.david.googlebookssearch.presentation.model;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

/**
 * Created by david on 26/11/17.
 */

public class GetBooksUseCaseImpl implements GetBooksUseCase {
    private static final String BASEURL = "https://www.googleapis.com/books/v1/volumes?q=";

    @Override
    public void getBooks(String query, JsonHttpResponseHandler handler) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(BASEURL + query, handler);
    }
}
