package com.hollysmart.smartsensor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.hollysmart.sensordata.Sensorapi;
import com.hollysmart.smartsensor.adapter.FruitAdapter;
import com.hollysmart.smartsensor.bean.Fruit;

import java.util.ArrayList;
import java.util.List;

public class TestRecycleViewActivity extends AppCompatActivity {

    private List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_recycle_view);
        Sensorapi.getInstance().setModuleId("1577579264862720001");
        initFruitList();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        FruitAdapter adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);
    }

    private void initFruitList() {
        for (int i = 0; i < 10; i++) {
            Fruit apple = new Fruit("apple", R.drawable.ic_launcher_background);
            fruitList.add(apple);
            Fruit caomei = new Fruit("caomei", R.drawable.ic_launcher_background);
            fruitList.add(caomei);
            Fruit chengzi = new Fruit("chengzi", R.drawable.ic_launcher_background);
            fruitList.add(chengzi);
            Fruit lizi = new Fruit("lizi", R.drawable.ic_launcher_background);
            fruitList.add(lizi);
            Fruit lanmei = new Fruit("lanmei", R.drawable.ic_launcher_background);
            fruitList.add(lanmei);
            Fruit mangguo = new Fruit("mangguo", R.drawable.ic_launcher_background);
            fruitList.add(mangguo);
            Fruit putao = new Fruit("putao", R.drawable.ic_launcher_background);
            fruitList.add(putao);
            Fruit xiangjiao = new Fruit("xiangjiao", R.drawable.ic_launcher_background);
            fruitList.add(xiangjiao);
            Fruit xigua = new Fruit("xigua", R.drawable.ic_launcher_background);
            fruitList.add(xigua);
            Fruit yingtao = new Fruit("yingtao", R.drawable.ic_launcher_background);
            fruitList.add(yingtao);
        }
    }
}