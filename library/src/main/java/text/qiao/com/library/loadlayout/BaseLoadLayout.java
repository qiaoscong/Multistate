package text.qiao.com.library.loadlayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * @project：Multistate
 * @fileName BaseLoadLayout
 * @author：乔少聪
 * @date：2019/4/4 17:00
 * @describe：
 */
public abstract class BaseLoadLayout extends FrameLayout implements State {

    private View mSuccessView;
    private View mLoadingView;
    private View mFailedView;
    private View mNullDataView;
    private View mOtherDataView;
    private int mState = State.SUCCESS;
    private OnLoadListener mLoadListener;
    public BaseLoadLayout(Context context) {
        this(context, null);
    }

    public BaseLoadLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseLoadLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    /**
     * 初始化
     */
    private void init() {
        if (isInEditMode()) {
            return;
        }

        if (getChildCount() > 1) {
            throw new RuntimeException("只允许有一个子视图!");
        }
    }

    private void addViewInternal(@NonNull View child) {
        super.addView(child);
    }

    public void addSuccessView(View view) {
        if (getChildCount() >= 1) {
            throw new RuntimeException("只允许有一个子视图!");
        }
        mSuccessView = view;
        addViewInternal(view);
    }

    protected abstract View createLoadingView();

    protected abstract View createLoadFailedView();

    protected abstract View createNoDataView();

    protected abstract View createOtherDataView();


    /**
     * 隐藏全部状态
     */
    private void onHide() {

        if (mSuccessView != null) {
            mSuccessView.setVisibility(GONE);
        }

        if (mFailedView != null) {
            mFailedView.setVisibility(GONE);
        } else {
            mFailedView = createLoadFailedView();
            addViewInternal(mFailedView);
            mSuccessView.setVisibility(GONE);
        }
        if (mNullDataView != null) {
            mNullDataView.setVisibility(GONE);
        } else {
            mNullDataView = createNoDataView();
            addViewInternal(mNullDataView);
            mSuccessView.setVisibility(GONE);
        }
        if (mOtherDataView != null) {
            mOtherDataView.setVisibility(GONE);
        } else {
            mOtherDataView = createOtherDataView();
            addViewInternal(mOtherDataView);
            mSuccessView.setVisibility(GONE);
        }
        if (mLoadingView != null) {
            mLoadingView.setVisibility(GONE);
        } else {
            mLoadingView = createLoadingView();
            addViewInternal(mLoadingView);
            mSuccessView.setVisibility(GONE);
        }
    }

    /**
     * 加载中
     */
    private void onLoading() {
        if (mLoadingView != null) {
            Log.e("qiao","加载中显示");
            mLoadingView.setVisibility(VISIBLE);
        }
        if (mLoadListener != null) {
            mLoadListener.onLoad();
        }
    }

    /**
     * 加载失败
     */
    private void onLoadFailed() {
        if (mFailedView != null) {
            mFailedView.setVisibility(VISIBLE);
        }
    }

    /**
     * 加载无数据
     */
    private void onLoadNoData() {
        if (mNullDataView != null) {
            mNullDataView.setVisibility(VISIBLE);
        }
    }

    /**
     * 加载自定义状态
     */
    private void onLoadOtherData() {
        if (mOtherDataView != null) {
            mOtherDataView.setVisibility(VISIBLE);
        }
    }

    /**
     * 加载自定义状态
     */
    private void onLoadSuccess() {
        if (mSuccessView != null) {
            mSuccessView.setVisibility(VISIBLE);
        }
    }

    public void setLayoutState(final int state) {
        this.mState = state;

        onHide();
        switch (this.mState) {
            case LOADING:
                onLoading();
                break;
            case FAILED:
                onLoadFailed();
                break;
            case SUCCESS:
                onLoadSuccess();
                break;
            case NO_DATA:
                onLoadNoData();
                break;
            case OYHER:
                onLoadOtherData();
                break;

            default:
                break;
        }
    }

    public int getLayoutState() {
        return mState;
    }

    public OnLoadListener getOnLoadListener() {
        return mLoadListener;
    }

    public void setOnLoadListener(OnLoadListener listener) {
        this.mLoadListener = listener;
    }
}
