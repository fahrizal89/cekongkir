package com.fahrizal.cekongkir.data.cache;

import android.content.Context;

import com.fahrizal.cekongkir.data.cache.serializer.Serializer;
import com.fahrizal.cekongkir.data.entity.CityEntity;
import com.fahrizal.cekongkir.data.entity.ProvinceEntity;
import com.fahrizal.cekongkir.data.exception.UserNotFoundException;
import com.fahrizal.cekongkir.domain.executor.ThreadExecutor;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * {@link ProvinceCache} implementation.
 */
@Singleton
public class CityCacheImpl implements CityCache {

  private static final String SETTINGS_FILE_NAME = "com.fernandocejas.cekongkir.SETTINGS";
  private static final String SETTINGS_KEY_LAST_CACHE_UPDATE = "last_cache_update";

  private static final String DEFAULT_FILE_NAME = "user_";
  private static final long EXPIRATION_TIME = 60 * 10 * 1000;

  private final Context context;
  private final File cacheDir;
  private final Serializer serializer;
  private final FileManager fileManager;
  private final ThreadExecutor threadExecutor;

  /**
   * Constructor of the class {@link CityCacheImpl}.
   *
   * @param context A
   * @param serializer {@link Serializer} for object serialization.
   * @param fileManager {@link FileManager} for saving serialized objects to the file system.
   */
  @Inject
  CityCacheImpl(Context context, Serializer serializer,
                FileManager fileManager, ThreadExecutor executor) {
    if (context == null || serializer == null || fileManager == null || executor == null) {
      throw new IllegalArgumentException("Invalid null parameter");
    }
    this.context = context.getApplicationContext();
    this.cacheDir = this.context.getCacheDir();
    this.serializer = serializer;
    this.fileManager = fileManager;
    this.threadExecutor = executor;
  }

  @Override public Observable<CityEntity> get(final String cityId) {
    return Observable.create(emitter -> {
      final File userEntityFile = CityCacheImpl.this.buildFile(cityId);
      final String fileContent = CityCacheImpl.this.fileManager.readFileContent(userEntityFile);
      final CityEntity provinceEntity =
          CityCacheImpl.this.serializer.deserialize(fileContent, CityEntity.class);

      if (provinceEntity != null) {
        emitter.onNext(provinceEntity);
        emitter.onComplete();
      } else {
        emitter.onError(new UserNotFoundException());
      }
    });
  }

  @Override
  public void put(CityEntity cityEntity) {
    if (cityEntity != null) {
      final File userEntityFile = this.buildFile(cityEntity.getId());
      if (!isCached(cityEntity.getId())) {
        final String jsonString = this.serializer.serialize(cityEntity, ProvinceEntity.class);
        this.executeAsynchronously(new CacheWriter(this.fileManager, userEntityFile, jsonString));
        setLastCacheUpdateTimeMillis();
      }
    }
  }

  @Override public boolean isCached(String provinceId) {
    final File userEntityFile = this.buildFile(provinceId);
    return this.fileManager.exists(userEntityFile);
  }

  @Override public boolean isExpired() {
    long currentTime = System.currentTimeMillis();
    long lastUpdateTime = this.getLastCacheUpdateTimeMillis();

    boolean expired = ((currentTime - lastUpdateTime) > EXPIRATION_TIME);

    if (expired) {
      this.evictAll();
    }

    return expired;
  }

  @Override public void evictAll() {
    this.executeAsynchronously(new CacheEvictor(this.fileManager, this.cacheDir));
  }

  /**
   * Build a file, used to be inserted in the disk cache.
   *
   * @param provinceId The id user to build the file.
   * @return A valid file.
   */
  private File buildFile(String provinceId) {
    final StringBuilder fileNameBuilder = new StringBuilder();
    fileNameBuilder.append(this.cacheDir.getPath());
    fileNameBuilder.append(File.separator);
    fileNameBuilder.append(DEFAULT_FILE_NAME);
    fileNameBuilder.append(provinceId);

    return new File(fileNameBuilder.toString());
  }

  /**
   * Set in millis, the last time the cache was accessed.
   */
  private void setLastCacheUpdateTimeMillis() {
    final long currentMillis = System.currentTimeMillis();
    this.fileManager.writeToPreferences(this.context, SETTINGS_FILE_NAME,
        SETTINGS_KEY_LAST_CACHE_UPDATE, currentMillis);
  }

  /**
   * Get in millis, the last time the cache was accessed.
   */
  private long getLastCacheUpdateTimeMillis() {
    return this.fileManager.getFromPreferences(this.context, SETTINGS_FILE_NAME,
        SETTINGS_KEY_LAST_CACHE_UPDATE);
  }

  /**
   * Executes a {@link Runnable} in another Thread.
   *
   * @param runnable {@link Runnable} to execute
   */
  private void executeAsynchronously(Runnable runnable) {
    this.threadExecutor.execute(runnable);
  }

  /**
   * {@link Runnable} class for writing to disk.
   */
  private static class CacheWriter implements Runnable {
    private final FileManager fileManager;
    private final File fileToWrite;
    private final String fileContent;

    CacheWriter(FileManager fileManager, File fileToWrite, String fileContent) {
      this.fileManager = fileManager;
      this.fileToWrite = fileToWrite;
      this.fileContent = fileContent;
    }

    @Override public void run() {
      this.fileManager.writeToFile(fileToWrite, fileContent);
    }
  }

  /**
   * {@link Runnable} class for evicting all the cached files
   */
  private static class CacheEvictor implements Runnable {
    private final FileManager fileManager;
    private final File cacheDir;

    CacheEvictor(FileManager fileManager, File cacheDir) {
      this.fileManager = fileManager;
      this.cacheDir = cacheDir;
    }

    @Override public void run() {
      this.fileManager.clearDirectory(this.cacheDir);
    }
  }
}
