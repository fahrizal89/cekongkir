package com.fahrizal.cekongkir.data.net;

import com.fahrizal.cekongkir.data.entity.ProvinceResponse;
import com.fahrizal.cekongkir.data.entity.CityResponse;
import com.fahrizal.cekongkir.data.entity.CostResponse;
import com.fahrizal.cekongkir.data.entity.CostRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @GET("province")
    Call<ProvinceResponse> getProvincesAll(@Header("key") String key);

    @POST("cost")
    Call<CostResponse> getCost(@Header("key") String key,@Body CostRequest costRequest);

    @GET("city")
    Call<CityResponse> getCities(@Header("key") String key, @Query("province") int provinceId);
}
