package com.knowmemo.usermanagement;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;

import sqlite.tableDao;

/**
 * Created by TingEn on 2016/11/17.
 */
public class FavoriteActivity extends Activity {
    ListView lv;
    tableDao tabledao;
    List<Map<String, Object>> favorReturnList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myfavorite);
        this.loadInit();

    }

    private void loadInit() {
        tabledao = new tableDao(getApplicationContext());
//        System.out.println(tabledao.getFavorsCount());

        lv = (ListView) findViewById(R.id.favorInfo);
        favorReturnList = tabledao.getFavoritesWords();
        SimpleAdapter adapter = new SimpleAdapter(this, favorReturnList, R.layout.favoritem_layout, new String[]{"單字", "解釋"}, new int[]{R.id.worditem, R.id.meanitem});
//        lv.setAdapter(new CustomAdapterArrayAdapter(this,favorReturnList));
        lv.setAdapter(adapter);

    }
}
