package com.amador.david.googlebookssearch.presentation.view.activities;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.amador.david.googlebookssearch.R;
import com.amador.david.googlebookssearch.presentation.model.Book;
import com.amador.david.googlebookssearch.presentation.presenters.MainPresenter;
import com.amador.david.googlebookssearch.presentation.presenters.MainPresenterImpl;
import com.amador.david.googlebookssearch.presentation.view.fragments.DetailBookFragment;
import com.amador.david.googlebookssearch.presentation.view.fragments.SearchBookFragment;

public class MainActivity extends AppCompatActivity implements MainView,
        SearchBookFragment.OnBookClickListener{

    private MainPresenter presenter;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private Book currentBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Inicializamos el presentador*/
        presenter = new MainPresenterImpl(this);

        /*Insertamos nuestra vista*/
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.container, new SearchBookFragment(),"search").commit();
    }

    /*Navegamos a la vista de detalle*/
    @Override
    public void goToDetail() {
        ft = fm.beginTransaction();
        ft.replace(R.id.container,DetailBookFragment.newInstance(currentBook),"detailBook").addToBackStack("detail").commit();
    }

    /*Cuando el usuario pulsa eestablece el libro actual en la Actividad y
    * pregunta al presenter que hacer*/
    @Override
    public void onBookClickListener(Book book) {
        currentBook = book;
        presenter.onUserClickBook();
    }
}
