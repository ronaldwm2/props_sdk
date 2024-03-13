package com.props.adsmanager.connection;

import com.props.adsmanager.Models.PropsAdsManagementModels;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface API {

    String BASE_URL = "https://propssdk.props.id";

    String CACHE = "Cache-Control: max-age=0";

    @Headers({CACHE})
    @GET("getAdsPosition")
    Call<List<PropsAdsManagementModels>> getAdsPosition(@Query("packageName") String packageName);

}
