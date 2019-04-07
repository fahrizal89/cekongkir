package com.fahrizal.cekongkir.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.fahrizal.cekongkir.presentation.R;
import com.fahrizal.cekongkir.presentation.model.ProvinceModel;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

/**
 * Adaptar that manages a collection of {@link ProvinceModel}.
 */
public class ProvincesAdapter extends RecyclerView.Adapter<ProvincesAdapter.UserViewHolder> {

  public interface OnItemClickListener {
    void onUserItemClicked(ProvinceModel provinceModel);
  }

  private List<ProvinceModel> provincesCollection;
  private final LayoutInflater layoutInflater;

  private OnItemClickListener onItemClickListener;

  @Inject
  ProvincesAdapter(Context context) {
    this.layoutInflater =
        (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    this.provincesCollection = Collections.emptyList();
  }

  @Override public int getItemCount() {
    return (this.provincesCollection != null) ? this.provincesCollection.size() : 0;
  }

  @Override public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    final View view = this.layoutInflater.inflate(R.layout.row_provice, parent, false);
    return new UserViewHolder(view);
  }

  @Override public void onBindViewHolder(UserViewHolder holder, final int position) {
    final ProvinceModel userModel = this.provincesCollection.get(position);
    holder.textViewTitle.setText(userModel.getName());
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (ProvincesAdapter.this.onItemClickListener != null) {
          ProvincesAdapter.this.onItemClickListener.onUserItemClicked(userModel);
        }
      }
    });
  }

  @Override public long getItemId(int position) {
    return position;
  }

  public void setProvincesCollection(Collection<ProvinceModel> provincesCollection) {
    this.validateUsersCollection(provincesCollection);
    this.provincesCollection = (List<ProvinceModel>) provincesCollection;
    this.notifyDataSetChanged();
  }

  public void setOnItemClickListener (OnItemClickListener onItemClickListener) {
    this.onItemClickListener = onItemClickListener;
  }

  private void validateUsersCollection(Collection<ProvinceModel> provincesCollection) {
    if (provincesCollection == null) {
      throw new IllegalArgumentException("The list cannot be null");
    }
  }

  static class UserViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.title) TextView textViewTitle;

    UserViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
