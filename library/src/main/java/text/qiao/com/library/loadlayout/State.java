package text.qiao.com.library.loadlayout;

/**
 * @author 乔少聪
 * @time 2019/4/4 16:56
 * @describe 各种状态
 */
public interface State {
    /**
     * 加载中
     */
    int LOADING = 1;

    /**
     * 加载成功
     */
    int SUCCESS = 2;

    /**
     * 加载失败
     */
    int FAILED = 3;

    /**
     * 加载成功且返回无数据
     */
    int NO_DATA = 4;
    /**
     * 其它状态
     */
    int OYHER = 5;
}
