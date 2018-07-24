package business.com.businessapp.base.mvp.proxy;
import business.com.businessapp.base.mvp.BasePresenter;
import business.com.businessapp.base.mvp.IBaseView;
import business.com.businessapp.base.mvp.factory.PresenterMvpFactory;

/**
 * @author ydc
 * @date 2017/11/20
 * @description 代理接口
 */
public interface PresenterProxyInterface<V extends IBaseView,P extends BasePresenter<V>> {


    /**
     * 设置创建Presenter的工厂
     * @param presenterFactory PresenterFactory类型
     */
    void setPresenterFactory(PresenterMvpFactory<V, P> presenterFactory);

    /**
     * 获取Presenter的工厂类
     * @return 返回PresenterMvpFactory类型
     */
    PresenterMvpFactory<V,P> getPresenterFactory();


    /**
     * 获取创建的Presenter
     * @return 指定类型的Presenter
     */
    P getMvpPresenter();


}
