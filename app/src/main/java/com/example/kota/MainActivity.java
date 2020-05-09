package com.example.kota;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Context context;
    KotaAdapter kotaAdapter;
    ArrayList<Kota> kotaList;
    Kota kotaModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);

        kotaList = new ArrayList<>();

        kotaPopulate();

        kotaAdapter = new KotaAdapter(this, kotaList);
        listView.setAdapter(kotaAdapter);
    }

    private void kotaPopulate () {
        kotaModel = new Kota();
        kotaModel.setKota("Depok");
        kotaModel.setLatIng(-6.385589);
        kotaModel.setLongIng(106.830711);
        kotaList.add(kotaModel);

        kotaModel = new Kota();
        kotaModel.setKota("Papua");
        kotaModel.setLatIng(-0.861453);
        kotaModel.setLongIng(134.062042);
        kotaList.add(kotaModel);

        kotaModel = new Kota();
        kotaModel.setKota("Bali");
        kotaModel.setLatIng( -8.739184);
        kotaModel.setLongIng(115.171127);
        kotaList.add(kotaModel);
    }

    public class KotaAdapter extends BaseAdapter {

        Context context; //context
        ArrayList<Kota> kotaList;
        Kota kotaModel;
        LayoutInflater layoutInflater;


        public KotaAdapter(Context context, ArrayList<Kota> kotaList) {
            this.context = context;
            this.kotaList = kotaList;
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return kotaList.size();
        }

        @Override
        public Object getItem(int i) {
            return kotaList.get(i);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View rowView = view;
            if(rowView == null) {
                rowView = layoutInflater.inflate(R.layout.kota_list, null, true);
            }

            TextView tvName = rowView.findViewById(R.id.tvName);

            kotaModel = kotaList.get(i);

            tvName.setText(kotaModel.getKota());

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView parent, View v, int position, long id) {
                    try {
                        Kota kota_list = kotaList.get(position);
                        Intent kotaIntent = new Intent(getApplicationContext(), MapsActivity.class);
                        kotaIntent.putExtra("lat",kota_list.latIng);
                        kotaIntent.putExtra("long", kota_list.longIng);
                        startActivity(kotaIntent);
                    } catch (Exception e) {
                        System.out.println(e + "eror yah");
                    }
                }

            });

            return rowView;
        }
    }
}
