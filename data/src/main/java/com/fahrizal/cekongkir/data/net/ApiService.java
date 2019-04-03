package com.fahrizal.cekongkir.data.net;

import com.fahrizal.cekongkir.data.entity.BaseResponse;
import com.fahrizal.cekongkir.data.entity.ProvinceEntity;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ApiService {

    @GET("province")
    Call<BaseResponse> getProvincesAll(@Header("key") String key);
}
