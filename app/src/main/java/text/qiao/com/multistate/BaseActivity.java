package text.qiao.com.multistate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import text.qiao.com.library.loadlayout.LoadLayout;

/**
 * @project：Multistate
 * @fileName BaseActivity
 * @author：乔少聪
 * @date：2019/4/4 18:12
 * @describe：
 */
public abstract class BaseActivity extends AppCompatActivity {
    /**
     * 根布局
     */
    private LoadLayout mLoadLayout;

    /**
     * 上层设置的布局
     */
    private View mView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baselayout);
        initView();
    }
    private void  initView(){
        mLoadLayout=findViewById(R.id.load_layout);
        mView = getLayoutInflater().inflate(getLayout(), null);
        mLoadLayout.removeAllViews();
        mLoadLayout.addSuccessView(mView);
        obtainData();
    }
    /**
     * @return 返回基础布局
     */
    public LoadLayout getLoadLayout() {
        return mLoadLayout;
    }
    protected abstract int getLayout();

    /**
     * 实现obtainData来做数据的初始化
     */
    protected abstract void obtainData();


}
