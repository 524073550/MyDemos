package business.com.businessapp.base.mvp.factory;
import business.com.businessapp.base.mvp.BasePresenter;
import business.com.businessapp.base.mvp.IBaseView;

/**
 * @author ydc
 * @date 2017/11/17
 * @description Presenter工厂实现类
 */
public class PresenterMvpFactoryImpl<V extends IBaseView, P extends BasePresenter<V>> implements PresenterMvpFactory<V, P> {

    /**
     * 需要创建的Presenter的类型
     */
    private final Class<P> mPresenterClass;


    /**
     * 根据注解创建Presenter的工厂实现类
     * @param viewClazz 需要创建Presenter的V层实现类
     * @param <V> 当前View实现的接口类型
     * @param <P> 当前要创建的Presenter类型
     * @return 工厂类
     */
    public static <V extends IBaseView, P extends BasePresenter<V>> PresenterMvpFactoryImpl<V,P> createFactory(Class<?> viewClazz){
       CreatePresenter annotation = viewClazz.getAnnotation(CreatePresenter.class);
        Class<P> aClass = null;
        if(annotation != null){
            aClass = (Class<P>) annotation.value();
        }
        return aClass == null ? null : new PresenterMvpFactoryImpl<V, P>(aClass);
    }


    private PresenterMvpFactoryImpl(Class<P> presenterClass) {
        this.mPresenterClass = presenterClass;
    }

    @Override
    public P createMvpPresenter() {
        try {
            return mPresenterClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Presenter创建失败!，检查是否声明了@CreatePresenter(xx.class)注解");
        }
    }
}
