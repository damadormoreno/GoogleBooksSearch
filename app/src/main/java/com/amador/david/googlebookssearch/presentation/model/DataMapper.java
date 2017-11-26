package com.amador.david.googlebookssearch.presentation.model;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by david on 26/11/17.
 */

public class DataMapper {
    JSONObject object;

    public DataMapper(JSONObject object) {
        this.object = object;
    }

    /*Transforma el JSONObject recibido en el modelo de la vista.*/
    public ArrayList<Book> JSONtoArrayBooks() throws JSONException {
        ArrayList<Book> books = new ArrayList<>();
        JSONArray booksJSON = object.getJSONArray("items");

        for (int i = 0; i <booksJSON.length() ; i++) {
            Book book = new Book();
            JSONObject bookJSON = booksJSON.getJSONObject(i);
            JSONObject volumeInfo = null;
            JSONObject imageLinks = null;

            if (bookJSON.getJSONObject("volumeInfo")!=null)
                volumeInfo = bookJSON.getJSONObject("volumeInfo");

            if (volumeInfo!=null && volumeInfo.has("imageLinks")){
                imageLinks = volumeInfo.getJSONObject("imageLinks");
                if (imageLinks.has("smallThumbnail"))
                    book.setSmallThumbnail(imageLinks.getString("smallThumbnail"));
                if (imageLinks.has("thumbnail"))
                    book.setThumbnail(imageLinks.getString("thumbnail"));
            }
            if (volumeInfo!=null){
                book.setTitle(volumeInfo.getString("title"));
                if (volumeInfo.has("authors"))
                    book.setAuthors(volumeInfo.getJSONArray("authors"));
                if (volumeInfo.has("description"))
                    book.setLongDescription(volumeInfo.getString("description"));
            }
            books.add(book);
        }
        return books;
    }
}
