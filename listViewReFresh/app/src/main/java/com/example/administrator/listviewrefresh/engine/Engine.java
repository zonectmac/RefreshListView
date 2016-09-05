package com.example.administrator.listviewrefresh.engine;

import com.example.administrator.listviewrefresh.model.BannerModel;
import com.example.administrator.listviewrefresh.model.LoginModel;
import com.example.administrator.listviewrefresh.model.RefreshModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/8/23.
 */
public interface Engine {

    //请求头
//    @Headers({
//            "Accept: application/vnd.github.v3.full+json",
//            "User-Agent: RetrofitBean-Sample-App",
//            "name:ljd"
//    })
    @GET("refreshlayout/api/defaultdata6.json")
    Call<List<RefreshModel>> loadInitDatas();

    @GET("refreshlayout/api/newdata{pageNumber}.json")
    Call<List<RefreshModel>> loadNewData(@Path("pageNumber") int pageNumber);

    @GET("refreshlayout/api/moredata{pageNumber}.json")
    Call<List<RefreshModel>> loadMoreData(@Path("pageNumber") int pageNumber);

    @GET("banner/api/5item.json")
    Call<BannerModel> getBannerModel();

    @POST("default/svc/author/params")
    Call<LoginModel> getData(@Query("params") String route);
}
