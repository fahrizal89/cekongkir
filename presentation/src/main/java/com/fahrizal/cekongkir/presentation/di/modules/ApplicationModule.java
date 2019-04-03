/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fahrizal.cekongkir.presentation.di.modules;

import android.content.Context;

import com.fahrizal.cekongkir.data.cache.ProvinceCache;
import com.fahrizal.cekongkir.data.cache.ProvinceCacheImpl;
import com.fahrizal.cekongkir.data.datasource.RetrofitDataStore;
import com.fahrizal.cekongkir.data.executor.JobExecutor;
import com.fahrizal.cekongkir.data.ProvinceDataRepository;
import com.fahrizal.cekongkir.data.net.ApiService;
import com.fahrizal.cekongkir.domain.executor.PostExecutionThread;
import com.fahrizal.cekongkir.domain.executor.ThreadExecutor;
import com.fahrizal.cekongkir.domain.repository.ProvinceRepository;
import com.fahrizal.cekongkir.presentation.AndroidApplication;
import com.fahrizal.cekongkir.presentation.UIThread;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {
  private final AndroidApplication application;

  public ApplicationModule(AndroidApplication application) {
    this.application = application;
  }

  @Provides @Singleton Context provideApplicationContext() {
    return this.application;
  }

  @Provides @Singleton ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
    return jobExecutor;
  }

  @Provides @Singleton PostExecutionThread providePostExecutionThread(UIThread uiThread) {
    return uiThread;
  }

  @Provides @Singleton
  ProvinceCache provideUserCache(ProvinceCacheImpl userCache) {
    return userCache;
  }

  @Provides @Singleton
  ProvinceRepository provideUserRepository(ProvinceDataRepository userDataRepository) {
    return userDataRepository;
  }
  @Provides @Singleton
  ApiService provideApiService(){return new RetrofitDataStore().getApiService();}
}
