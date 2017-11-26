package com.amador.david.googlebookssearch.presentation.presenters;


import com.amador.david.googlebookssearch.presentation.view.activities.MainView;


/**
 * Created by david on 26/11/17.
 */

public class MainPresenterImpl implements MainPresenter {
    private MainView view;


    public MainPresenterImpl(MainView view) {
        this.view = view;
    }

    @Override
    public void onUserClickBook() {
        view.goToDetail();
    }
}
