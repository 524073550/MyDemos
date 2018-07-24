package business.com.businessapp.news.presenter;

import business.com.businessapp.base.mvp.BasePresenter;
import business.com.businessapp.news.view.INewsView;

/**ydc 新闻类的协议也可以是接口
 * Created by Administrator on 2017/7/6.
 */
abstract class Presenter extends BasePresenter<INewsView> {
    public abstract void loadNews(int type, int page);
}
