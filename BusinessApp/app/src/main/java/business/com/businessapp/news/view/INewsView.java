package business.com.businessapp.news.view;

import business.com.businessapp.base.mvp.IBaseView;
import business.com.businessapp.news.beans.NewsBean;

import java.util.List;

/**ydc 新闻列表所特有的方法定义
 * Created by Administrator on 2017/7/6.
 */

public interface INewsView extends IBaseView {
    void addNews(List<NewsBean> newsList);
}
