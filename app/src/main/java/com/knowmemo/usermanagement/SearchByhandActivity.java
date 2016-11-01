package com.knowmemo.usermanagement;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;




/**
 * Created by TingEn on 2016/10/11.
 */
public class SearchByhandActivity extends Activity {

    Button buttonSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchbyhand);

        Button btn_Search = (Button) findViewById(R.id.buttonSearch);

        btn_Search.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {



                new Thread(new Runnable()
                {
                    EditText et = (EditText) findViewById(R.id.editText);
                    TextView output = (TextView) findViewById(R.id.enginfo);

                    public void run()
                    {
                        try
                        {
                            String input = et.getText().toString();
                            String inputfind = input.toString().toLowerCase();
                            String url = "https://glosbe.com/gapi/translate?from=eng&dest=zh&format=json&phrase="+inputfind+"&pretty=true" ;
                            HttpClient mHttpClient = new DefaultHttpClient();
                            HttpGet mHttpGet = new HttpGet(url);
                            HttpResponse mHttpResponse = mHttpClient.execute(mHttpGet);

                            if(mHttpResponse.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_OK )
                            {
//                                output.setText("OK");
                                String mJsonText = EntityUtils.toString(mHttpResponse.getEntity());

                                String ID =new JSONObject( new JSONArray(new JSONObject(mJsonText).getString("tuc")).getJSONObject(0).getString("phrase")).getString("text");

//
                                output.setText("解釋:"+ID);
                            }
                            else
                                output.setText("wrong wrong wrong");
                        }
                        catch(Exception e)
                        {
                        }
                    }
                }).start();
            }});



    }

}




