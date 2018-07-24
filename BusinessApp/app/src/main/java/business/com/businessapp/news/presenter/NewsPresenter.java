package business.com.businessapp.news.presenter;

import business.com.businessapp.base.mvp.ApiCallBack;
import business.com.businessapp.news.beans.NewsRequestModel;
import business.com.businessapp.news.model.Model;
import business.com.businessapp.news.model.NewsModel;

/**
 * Created by Administrator on 2017/7/6.
 */

public class NewsPresenter extends Presenter {

    private Model mModel;
    public  NewsPresenter(){
        mModel=new NewsModel();
    }

    @Override
    public void loadNews(int type, int page) {
        addSubscription(mModel.loadNews("nc/article/headline/T1348647909107/0-20.html",0), new ApiCallBack<NewsRequestModel>() {

            @Override
            public void onStart() {
                getMvpView().showLoading();
            }

            @Override
            public void onSuccess(NewsRequestModel modelBean) {
                if(modelBean!=null){
                    getMvpView().addNews(modelBean.getT1348647909107());
                }

            }

            @Override
            public void onFailure(String errorMsg) {
               getMvpView().showErrorMsg("","");
            }

            @Override
            public void onFinished() {
                getMvpView().hideLoading();
            }
        });
    }

    @Override
    public void subscribe() {

    }
}
