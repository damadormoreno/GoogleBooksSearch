package com.amador.david.googlebookssearch.presentation.model;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Created by david on 26/11/17.
 */

public class Book implements Serializable{
    private String title;
    private JSONArray authors;
    private String longDescription;
    private String smallThumbnail;
    private String thumbnail;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public JSONArray getAuthors() {
        return authors;
    }

    public void setAuthors(JSONArray authors) {
        this.authors = authors;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getSmallThumbnail() {
        return smallThumbnail;
    }

    public void setSmallThumbnail(String smallThumbnail) {
        this.smallThumbnail = smallThumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

}
