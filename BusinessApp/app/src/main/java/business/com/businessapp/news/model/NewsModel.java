package business.com.businessapp.news.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import business.com.businessapp.interfaces.INewService;
import business.com.businessapp.news.beans.NewsRequestModel;
import rx.Observable;

/**ydc 新闻数据处理协议
 * Created by Administrator on 2017/7/6.
 */

public class NewsModel extends Model {
    private INewService service=createService(INewService.class);

    @Override
    public Observable<NewsRequestModel> loadNews(String url, int type) {
        Map<String, String> map = new HashMap<>();
        //map.put("type", type+"");
        return service.getNewList(url,map);
    }
}
