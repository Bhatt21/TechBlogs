package com.example.android.techblogs;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BHATT on 4/1/2018.
 */

public final class fetchData {

    public static final String LOG_TAG = fetchData.class.getSimpleName();


    public static List<blog> fetchblogData(String requestUrl) {

        URL url = createUrl(requestUrl);


        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }


        List <blog> tech = extractFeatureFromJson(jsonResponse);


        return tech;
    }


    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 );
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the Techblogs JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static List<blog> extractFeatureFromJson(String blogJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(blogJSON)) {
            return null;
        }

        List<blog> blogs = new ArrayList<>();


        try {
            JSONObject baseJsonResponse = new JSONObject(blogJSON);
            JSONArray articleArray = baseJsonResponse.getJSONArray("articles");
            for (int i=0;i<articleArray.length();i++)

            {
                JSONObject JBLOG= articleArray.getJSONObject(i);
                String title=JBLOG.getString("title");
                String des=JBLOG.getString("description");
                String urltoarticle =JBLOG.getString("url");


                String urli=JBLOG.getString("urlToImage");
                String published=JBLOG.getString("publishedAt");
                String [] realpublished= published.split("T");
                published=realpublished[0];

                blog Blog=new blog(title,des,urli, published, urltoarticle);
                blogs.add(Blog);

            }

                return blogs;
            } catch (JSONException e1) {
            e1.printStackTrace();
        }

        return null;
    }







}
