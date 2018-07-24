package business.com.businessapp.account.presenter;

import business.com.businessapp.account.view.ILoginView;
import business.com.businessapp.base.mvp.BasePresenter;

/**ydc 新闻类的协议也可以是接口
 * Created by Administrator on 2017/7/6.
 */
abstract class Presenter extends BasePresenter<ILoginView> {
    public abstract void Login(String username,String password);
}
