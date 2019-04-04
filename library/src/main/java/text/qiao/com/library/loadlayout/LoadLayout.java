package text.qiao.com.library.loadlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import text.qiao.com.library.R;

/**
 * @project：Multistate
 * @fileName LoadLayout
 * @author：乔少聪
 * @date：2019/4/4 17:43
 * @describe：
 */
public class LoadLayout extends BaseLoadLayout {

    private int mLoadingViewId = R.layout.layout_load_loading_view;
    private int mFailedViewId = R.layout.layout_load_failed_view;
    private int mNoDataViewId = R.layout.layout_load_null_data_view;
    private int mOtherId = R.layout.layout_load_other_data_view;
    private View mLoadingView;
    private View mFailedView;
    private View mNoDataView;
    private View mOtherView;

    private RelativeLayout rl_load_failed;
    private RelativeLayout rl_load_paymentfailed;
    private RelativeLayout rl_load_nolove;
    private RelativeLayout rl_load_nonews;
    private RelativeLayout rl_load_paysuccess;
    private RelativeLayout rl_loactionfail;

    public LoadLayout(Context context) {
        super(context);
    }

    public LoadLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected View createLoadingView() {
        mLoadingView = LayoutInflater.from(getContext()).inflate(mLoadingViewId, null);
        mLoadingView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        return mLoadingView;
    }

    @Override
    protected View createLoadFailedView() {
        mFailedView = LayoutInflater.from(getContext()).inflate(mFailedViewId, null);
        mFailedView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                setLayoutState(State.LOADING);
                return false;
            }
        });

        return mFailedView;
    }

    @Override
    protected View createNoDataView() {
        mNoDataView = LayoutInflater.from(getContext()).inflate(mNoDataViewId, null);

        mNoDataView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                setLayoutState(State.LOADING);
                return false;
            }
        });
        return mNoDataView;
    }

    @Override
    protected View createOtherDataView() {
        mOtherView = LayoutInflater.from(getContext()).inflate(mOtherId, null);
        mOtherView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (getOnLoadListener() != null) {
                    getOnLoadListener().onOther();
                }
                return false;
            }
        });

        return mOtherView;
    }

    public int getmLoadingViewId() {
        return mLoadingViewId;
    }

    public void setmLoadingViewId(int mLoadingViewId) {
        this.mLoadingViewId = mLoadingViewId;
    }

    public int getmFailedViewId() {
        return mFailedViewId;
    }

    public void setmFailedViewId(int mFailedViewId) {
        this.mFailedViewId = mFailedViewId;
    }

    public int getmNoDataViewId() {
        return mNoDataViewId;
    }

    public void setmNoDataViewId(int mNoDataViewId) {
        this.mNoDataViewId = mNoDataViewId;
    }

    public int getmOtherId() {
        return mOtherId;
    }

    public void setmOtherId(int mOtherId) {
        this.mOtherId = mOtherId;
    }
}
