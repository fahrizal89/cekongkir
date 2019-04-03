package com.fahrizal.cekongkir.sample.test.presenter;

import android.content.Context;
import com.fahrizal.cekongkir.domain.interactor.GetUserDetails;
import com.fahrizal.cekongkir.domain.interactor.GetUserDetails.Params;
import com.fahrizal.cekongkir.presentation.mapper.ProvinceModelDataMapper;

import io.reactivex.observers.DisposableObserver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProvinceDetailsPresenterTest {

  private static final int USER_ID = 1;
/*
  private UserDetailsPresenter userDetailsPresenter;

  @Mock private Context mockContext;
  @Mock private UserDetailsView mockUserDetailsView;
  @Mock private GetUserDetails mockGetUserDetails;
  @Mock private ProvinceModelDataMapper mockProvinceModelDataMapper;

  @Before
  public void setUp() {
    userDetailsPresenter = new UserDetailsPresenter(mockGetUserDetails, mockProvinceModelDataMapper);
    userDetailsPresenter.setView(mockUserDetailsView);
  }

  @Test
  @SuppressWarnings("unchecked")
  public void testUserDetailsPresenterInitialize() {
    given(mockUserDetailsView.context()).willReturn(mockContext);

    userDetailsPresenter.initialize(USER_ID);

    verify(mockUserDetailsView).hideRetry();
    verify(mockUserDetailsView).showLoading();
    verify(mockGetUserDetails).execute(any(DisposableObserver.class), any(Params.class));
  }*/
}
