package com.zhuke.rxutils;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vondear.rxtools.view.ticker.RxTickerUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Integer> mIntegers = new ArrayList<>();
    private SwipeRefreshLayout mRefreshLayout;
    private MyAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView viewById = (RecyclerView) findViewById(R.id.rv);
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.srl);

        viewById.setLayoutManager(new LinearLayoutManager(this));
        getData();
        mAdapter = new MyAdapter(this, mIntegers);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
                mAdapter.refreshData(mIntegers);
                mRefreshLayout.setRefreshing(false);
            }
        });
        viewById.setAdapter(mAdapter);
    }

    private void getData() {
        for (int i = 1; i < 20; i++) {
            mIntegers.add(100000*i);
        }
    }
}
