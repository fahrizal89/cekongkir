/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fahrizal.cekongkir.data.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.fahrizal.cekongkir.data.entity.BaseResponse;
import com.fahrizal.cekongkir.data.entity.CostEntity;
import com.fahrizal.cekongkir.data.entity.CostResponse;
import com.fahrizal.cekongkir.data.entity.ProvinceEntity;
import com.fahrizal.cekongkir.data.entity.mapper.ProvinceEntityJsonMapper;
import com.fahrizal.cekongkir.data.exception.NetworkConnectionException;
import com.fahrizal.cekongkir.data.model.CostRequest;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

import java.net.MalformedURLException;
import java.util.List;

/**
 * {@link RestApi} implementation for retrieving data from the network.
 */
public class RestApiImpl implements RestApi {

  private final Context context;
  private final ProvinceEntityJsonMapper provinceEntityJsonMapper;
  ApiService apiService;
  CompositeDisposable compositeDisposable = new CompositeDisposable();

  /**
   * Constructor of the class
   *
   * @param context {@link android.content.Context}.
   * @param provinceEntityJsonMapper {@link ProvinceEntityJsonMapper}.
   */
  public RestApiImpl(Context context, ProvinceEntityJsonMapper provinceEntityJsonMapper,ApiService apiService) {
    if (context == null || provinceEntityJsonMapper == null) {
      throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
    }
    this.context = context.getApplicationContext();
    this.provinceEntityJsonMapper = provinceEntityJsonMapper;
    this.apiService=apiService;
  }



  @Override public Observable<List<ProvinceEntity>> provinceEntityList() {
    return Observable.create(emitter -> {
      if (isThereInternetConnection()) {
        try {
          BaseResponse response=apiService.getProvincesAll(RestApi.API_KEY).execute().body();
          if (response != null) {
            emitter.onNext(response.getRajaOngkir().getResults());
            emitter.onComplete();
          } else {
            emitter.onError(new NetworkConnectionException());
          }
        } catch (Exception e) {
          emitter.onError(new NetworkConnectionException(e.getCause()));
        }
      } else {
        emitter.onError(new NetworkConnectionException());
      }
    });
  }

  @Override public Observable<ProvinceEntity> userEntityById(final int userId) {
    return Observable.create(emitter -> {
      if (isThereInternetConnection()) {
        try {
          String responseUserDetails = getUserDetailsFromApi(userId);
          if (responseUserDetails != null) {
            emitter.onNext(provinceEntityJsonMapper.transformUserEntity(responseUserDetails));
            emitter.onComplete();
          } else {
            emitter.onError(new NetworkConnectionException());
          }
        } catch (Exception e) {
          emitter.onError(new NetworkConnectionException(e.getCause()));
        }
      } else {
        emitter.onError(new NetworkConnectionException());
      }
    });
  }

  private String getUserEntitiesFromApi() throws MalformedURLException {
    return ApiConnection.createGET(API_URL_GET_PROVINCE_LIST).requestSyncCall();
  }

  private String getUserDetailsFromApi(int userId) throws MalformedURLException {
    String apiUrl = API_URL_GET_CITY_LIST + userId + ".json";
    return ApiConnection.createGET(apiUrl).requestSyncCall();
  }

  /**
   * Checks if the device has any active internet connection.
   *
   * @return true device with internet connection, otherwise false.
   */
  private boolean isThereInternetConnection() {
    boolean isConnected;

    ConnectivityManager connectivityManager =
        (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
    isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

    return isConnected;
  }

  @Override
  public Observable<List<CostEntity.CostServiceEntity>> getCostDetailList(CostRequest costRequest) {
    return Observable.create(emitter -> {
      if (isThereInternetConnection()) {
        try {
          CostResponse response=apiService.getCost(RestApi.API_KEY,costRequest).execute().body();
          if (response != null) {
            List<CostEntity.CostServiceEntity> costs= response.getRajaOngkir().getResults().get(0).getCosts();
            emitter.onNext(costs);
            emitter.onComplete();
          } else {
            emitter.onError(new NetworkConnectionException());
          }
        } catch (Exception e) {
          emitter.onError(new NetworkConnectionException(e.getCause()));
        }
      } else {
        emitter.onError(new NetworkConnectionException());
      }
    });
  }

}
