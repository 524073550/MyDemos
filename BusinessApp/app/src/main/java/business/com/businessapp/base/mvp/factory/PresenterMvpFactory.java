package business.com.businessapp.base.mvp.factory;

import business.com.businessapp.base.mvp.BasePresenter;
import business.com.businessapp.base.mvp.IBaseView;

/**
 * @author ydc
 * @date 2017/11/17
 * @description Presenter工厂接口
 */
public interface PresenterMvpFactory<V extends IBaseView,P extends BasePresenter<V>> {

    /**
     * 创建Presenter的接口方法
     * @return 需要创建的Presenter
     */
    P createMvpPresenter();
}
