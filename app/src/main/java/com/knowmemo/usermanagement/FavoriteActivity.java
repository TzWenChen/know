package com.knowmemo.usermanagement;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import sqlite.tableDao;

/**
 * Created by TingEn on 2016/11/17.
 */
public class FavoriteActivity extends Activity {
    ListView lv;
    tableDao tabledao;
    ArrayList<HashMap<String, Object>> favorReturnList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myfavorite);
        this.loadInit();

    }
    private void loadInit() {
        tabledao = new tableDao(getApplicationContext());
        lv = (ListView) findViewById(R.id.favorInfo);
        favorReturnList = tabledao.getFavoritesWords();

        FavorviewAdapter Btnadapter = new FavorviewAdapter(
                this,
                favorReturnList,
                R.layout.favoritem_layout,
                new String[] {"單字","解釋", "按鈕"},
                new int[] {R.id.worditem,R.id.meanitem,R.id.btndelete}
        );
        lv.setAdapter(Btnadapter);
    }
    //        SimpleAdapter adapter = new SimpleAdapter(this, favorReturnList, R.layout.favoritem_layout, new String[]{"單字", "解釋"}, new int[]{R.id.worditem, R.id.meanitem});
    ////        lv.setAdapter(new CustomAdapterArrayAdapter(this,favorReturnList));
    //        lv.setAdapter(adapter);

    public class FavorviewAdapter extends BaseAdapter {

        private ArrayList<HashMap<String, Object>> mAppList;
        private LayoutInflater mInflater;
        private Context mContext;
        private String[] keyString;
        private int[] valueViewID;

        private ItemView itemView;

        private class ItemView {
            TextView ItemName;
            TextView ItemInfo;
            ImageButton ItemButton;
        }

        public FavorviewAdapter(Context c, ArrayList<HashMap<String, Object>> appList, int resource, String[] from, int[] to) {
            mAppList = appList;
            mContext = c;
            mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            keyString = new String[from.length];
            valueViewID = new int[to.length];
            System.arraycopy(from, 0, keyString, 0, from.length);
            System.arraycopy(to, 0, valueViewID, 0, to.length);

        }
        @Override
        public int getCount() {

            //return 0;
            return mAppList.size();
        }

        @Override
        public Object getItem(int position) {

            //return null;
            return mAppList.get(position);
        }

        @Override
        public long getItemId(int position) {

            //return 0;
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            //return null;

            if (convertView != null) {
                itemView = (ItemView) convertView.getTag();
            } else {
                convertView = mInflater.inflate(R.layout.favoritem_layout, null);
                itemView = new ItemView();

                itemView.ItemName = (TextView)convertView.findViewById(valueViewID[0]);
                itemView.ItemInfo = (TextView)convertView.findViewById(valueViewID[1]);
                itemView.ItemButton = (ImageButton)convertView.findViewById(valueViewID[2]);
                convertView.setTag(itemView);
            }

            HashMap<String, Object> appInfo = mAppList.get(position);
            if (appInfo != null) {
                String name = (String) appInfo.get(keyString[0]);
                String info = (String) appInfo.get(keyString[1]);
                int bid = (Integer)appInfo.get(keyString[2]);
                itemView.ItemName.setText(name);
                itemView.ItemInfo.setText(info);
                itemView.ItemButton.setOnClickListener(new ItemButton_Click(position));
            }

            return convertView;
        }

        class ItemButton_Click implements View.OnClickListener  {
            private int position;
            tableDao tabledao;

            ItemButton_Click(int pos) {
                position = pos;

            }

            @Override
            public void onClick(View v) {
                tabledao = new tableDao(getApplicationContext());
                HashMap<String, Object> appInfo = mAppList.get(position);
                String name = (String) appInfo.get(keyString[0]);
                System.out.println(name);
                tabledao.deleteFavorItem(name);
                mAppList.remove(position);
                notifyDataSetChanged();




            }

        }

    }





}
