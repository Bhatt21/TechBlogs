package com.example.android.techblogs;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private blogAdapter mAdapter;

    private static final String TECHBLOGS ="https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=df42c040b80741faa100ae8646c4a32f";
   // private blogAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) findViewById(R.id.list);
        mAdapter = new blogAdapter(this, new ArrayList<blog>());
        listView.setAdapter(mAdapter);

       getAsync MyAsynctask=new getAsync();
        MyAsynctask.execute(TECHBLOGS);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                blog currblog=mAdapter.getItem(i);
String str=currblog.getDescription();
                String u=currblog.getUrlimage();

                Intent intent = new Intent(MainActivity.this, onClickActivity.class);
                intent.putExtra("Description",str);
                intent.putExtra("Image",u);
                String urlarticle=currblog.getUrltoarticle();
                intent.putExtra("urltoarticle",urlarticle);


                 startActivity(intent);
                //startActivity(intent);
            }
        });


    }


private class getAsync extends AsyncTask<String,Void,List<blog>>
{

    @Override
    protected List<blog> doInBackground(String... urls) {




if(urls.length<1||urls==null)
        return null;

List<blog> getblog=fetchData.fetchblogData(urls[0]);

return getblog;
    }
    @Override
    protected void onPostExecute(List<blog> data) {
        // Clear the adapter of previous earthquake data
        mAdapter.clear();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (data != null && !data.isEmpty()) {
            mAdapter.addAll(data);
        }
    }

}
}
