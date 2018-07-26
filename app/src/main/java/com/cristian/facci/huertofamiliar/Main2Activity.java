package com.cristian.facci.huertofamiliar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    Toolbar mToolbar;
    RecyclerView mRecyclerView;
    List<FlowerData> mFlowerList;
    FlowerData mFlowerData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle(getResources().getString(R.string.app_name));
        mRecyclerView = findViewById(R.id.recyclerview);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(Main2Activity.this, 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);

        mFlowerList = new ArrayList<>();
        mFlowerData = new FlowerData("Tomate", getString(R.string.description_flower_rose),
                R.drawable.tomate);
        mFlowerList.add(mFlowerData);
        mFlowerData = new FlowerData("Pepino", getString(R.string.description_flower_carnation),
                R.drawable.pepino);
        mFlowerList.add(mFlowerData);
        mFlowerData = new FlowerData("Lechuga", getString(R.string.description_flower_tulip),
                R.drawable.lechuga);
        mFlowerList.add(mFlowerData);
        mFlowerData = new FlowerData("Rabano", getString(R.string.description_flower_daisy),
                R.drawable.rabano);
        mFlowerList.add(mFlowerData);
        mFlowerData = new FlowerData("Papa", getString(R.string.description_flower_sunflower),
                R.drawable.pap);
        mFlowerList.add(mFlowerData);
        mFlowerData = new FlowerData("Cebolla", getString(R.string.description_flower_daffodil),
                R.drawable.cebolla);
        mFlowerList.add(mFlowerData);
        mFlowerData = new FlowerData("Zanahoria", getString(R.string.description_flower_gerbera),
                R.drawable.zanahoria);
        mFlowerList.add(mFlowerData);
        mFlowerData = new FlowerData("Col", getString(R.string.description_flower_orchid),
                R.drawable.col);
        mFlowerList.add(mFlowerData);
        mFlowerData = new FlowerData("Pimiento", getString(R.string.description_flower_iris),
                R.drawable.pimiento);
        mFlowerList.add(mFlowerData);
        mFlowerData = new FlowerData("Hierbita", getString(R.string.description_flower_lilac),
                R.drawable.hierbita);
        mFlowerList.add(mFlowerData);

        MyAdapter myAdapter = new MyAdapter(Main2Activity.this, mFlowerList);
        mRecyclerView.setAdapter(myAdapter);
    }

}
