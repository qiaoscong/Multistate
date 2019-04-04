package text.qiao.com.multistate;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import text.qiao.com.library.loadlayout.OnLoadListener;
import text.qiao.com.library.loadlayout.State;

public class MainActivity extends BaseActivity {

    private Button mButton1, mButton2, mButton3;
    private int style = -1;


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void obtainData() {
        Log.e("qiao","obtainData");
        mButton1 = findViewById(R.id.button);
        mButton2 = findViewById(R.id.button2);
        mButton3 = findViewById(R.id.button3);
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                style = 1;
                getLoadLayout().setLayoutState(State.LOADING);
            }
        });
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                style = 2;
                getLoadLayout().setLayoutState(State.LOADING);
            }
        });
        mButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                style = 3;
                getLoadLayout().setLayoutState(State.LOADING);
            }
        });

        getLoadLayout().setOnLoadListener(new OnLoadListener() {
            @Override
            public void onLoad() {
                Log.e("qiao","onLoad");
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {

                        switch (style) {
                            case -1:
                                Log.e("qiao","SUCCESS");
                                getLoadLayout().setLayoutState(State.SUCCESS);
                                break;
                            case 1:
                                Log.e("qiao","NO_DATA");
                                getLoadLayout().setLayoutState(State.NO_DATA);
                                style = -1;
                                break;
                            case 2:
                                Log.e("qiao","FAILED");
                                getLoadLayout().setLayoutState(State.FAILED);
                                style = -1;
                                break;
                            case 3:
                                Log.e("qiao","OYHER");
                                getLoadLayout().setLayoutState(State.OYHER);
                                style = -1;
                                break;
                            default:
                                break;
                        }

                    }

                }, 5000);
            }

            @Override
            public void onOther() {

                Toast.makeText(MainActivity.this, "自定义点击", Toast.LENGTH_SHORT).show();
                style = -1;
                getLoadLayout().setLayoutState(State.LOADING);
            }
        });
        getLoadLayout().setLayoutState(State.LOADING);
    }
}
