package business.com.businessapp.news.model;

import business.com.businessapp.base.BaseModel;
import business.com.businessapp.news.beans.NewsRequestModel;
import rx.Observable;

/**ydc 获取数据的逻辑模块协议，也可以是接口，提供给P调用，在callback中更新V
 * Created by Administrator on 2017/7/6.
 */

public  abstract class Model extends BaseModel {
   public abstract Observable<NewsRequestModel> loadNews(String url, int type);
}
