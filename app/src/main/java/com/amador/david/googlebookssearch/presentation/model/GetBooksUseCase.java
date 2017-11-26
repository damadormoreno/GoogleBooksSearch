package com.amador.david.googlebookssearch.presentation.model;

import com.loopj.android.http.JsonHttpResponseHandler;

/**
 * Created by david on 26/11/17.
 */

public interface GetBooksUseCase {
    void getBooks(String query , JsonHttpResponseHandler handler);
}
