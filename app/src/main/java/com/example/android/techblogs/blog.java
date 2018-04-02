package com.example.android.techblogs;

/**
 * Created by BHATT on 4/1/2018.
 */

public class blog {

    public final String Title;

    public final String description;


    public final String urlimage;

    public final String published;
    public final String urltoarticle;


    public blog(String eventTitle, String DESCRIPTION, String URLIMAGES, String published, String urltoarticle) {
        Title = eventTitle;
        description = DESCRIPTION;
        urlimage = URLIMAGES;
        this.published = published;
        this.urltoarticle = urltoarticle;
    }


    public  String getTitle()
    {
        return Title;
    }
    public  String getDescription()
    {
        return description;
    }
    public  String getUrlimage() {
        return urlimage;
    }
    public String getUrltoarticle()
    {return urltoarticle;}

    public String getPublished() {
        return published;
    }
}
