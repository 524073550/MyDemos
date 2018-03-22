package com.zhuke.zxingcode;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity  {

    private int REQUEST_CODE = 1;
    private ImageView mViewById;
    private EditText et;
    private int REQUEST_IMAGE = 2;
    private ListView mViewById1;
    private List<String> mStrings = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mStrings.add("1");
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            private int mLastVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                    //这里进行加载数据操作
                    mStrings.add("2");
                    mAdapter.notifyDataSetChanged();
                    recyclerView.scrollToPosition(mStrings.size());
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mLastVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
            }
        });
        mAdapter = new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                if (viewType == 6) {
                    View inflate = LayoutInflater.from(MainActivity.this).inflate(R.layout.iteam_test, parent, false);
                    return new MyHolder1(inflate);
                } else if (viewType == 7){
                    View inflate = LayoutInflater.from(MainActivity.this).inflate(R.layout.iteam_test1, parent, false);
                    return new MyHolder2(inflate);
                }
                return null;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return mStrings.size();
            }

            @Override
            public int getItemViewType(int position) {
                if (position == 0) {
                    return 6;
                } else if (position == 1) {
                    return 7;
                }
                return super.getItemViewType(position);
            }
        };
        recyclerView.setAdapter(mAdapter);
    }


    public class MyHolder1 extends RecyclerView.ViewHolder {
        public MyHolder1(View itemView) {
            super(itemView);
        }
    }
    public class MyHolder2 extends RecyclerView.ViewHolder {
        public MyHolder2(View itemView) {
            super(itemView);
        }
    }
}
    /*@Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.code:
                Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                startActivityForResult(intent,REQUEST_CODE);
                break;
            case R.id.scan_pic:
                Intent intent1 = new Intent(Intent.ACTION_GET_CONTENT);
                intent1.addCategory(Intent.CATEGORY_OPENABLE);
                intent1.setType("image*//*");
                startActivityForResult(intent1, REQUEST_IMAGE);
                break;
            case R.id.code_pic:
                String trim = et.getText().toString().trim();
                if (TextUtils.isEmpty(trim)){
                    return;
                }
                Bitmap bitmap = CodeUtils.createImage(trim,200,200,null);
                mViewById.setImageBitmap(bitmap);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE){
            if(null != data){
                Bundle bundle = data.getExtras();
                if(bundle == null){
                    return;
                }

                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(MainActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }else if (requestCode == REQUEST_IMAGE) {
            if (data != null) {
                Uri uri = data.getData();
                ContentResolver cr = getContentResolver();
                try {
                    Bitmap mBitmap = MediaStore.Images.Media.getBitmap(cr, uri);//显得到bitmap图片

                    CodeUtils.analyzeBitmap(String.valueOf(mBitmap), new CodeUtils.AnalyzeCallback() {
                        @Override
                        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                            Toast.makeText(MainActivity.this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onAnalyzeFailed() {
                            Toast.makeText(MainActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                        }
                    });

                    if (mBitmap != null) {
                        mBitmap.recycle();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }*/
//}
