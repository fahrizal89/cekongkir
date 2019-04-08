package com.fahrizal.cekongkir.data.net;

import com.fahrizal.cekongkir.data.entity.BaseResponse;
import com.fahrizal.cekongkir.data.entity.CityResponse;
import com.fahrizal.cekongkir.data.entity.CostResponse;
import com.fahrizal.cekongkir.data.entity.ProvinceEntity;
import com.fahrizal.cekongkir.data.model.CityRequest;
import com.fahrizal.cekongkir.data.model.CostRequest;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("province")
    Call<BaseResponse> getProvincesAll(@Header("key") String key);

    @POST("cost")
    Call<CostResponse> getCost(@Header("key") String key,@Body CostRequest costRequest);

    @GET("city")
    Call<CityResponse> getCity(@Header("key") String key, @Query("province") String provinceId);
}
