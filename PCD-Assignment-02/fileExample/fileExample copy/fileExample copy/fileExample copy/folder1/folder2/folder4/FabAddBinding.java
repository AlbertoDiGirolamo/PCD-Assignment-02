// Generated by view binder compiler. Do not edit!
package com.example.progettomobile_07_05.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.example.progettomobile_07_05.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.NullPointerException;
import java.lang.Override;

public final class FabAddBinding implements ViewBinding {
  @NonNull
  private final FloatingActionButton rootView;

  @NonNull
  public final FloatingActionButton fabAdd;

  private FabAddBinding(@NonNull FloatingActionButton rootView,
      @NonNull FloatingActionButton fabAdd) {
    this.rootView = rootView;
    this.fabAdd = fabAdd;
  }

  @Override
  @NonNull
  public FloatingActionButton getRoot() {
    return rootView;
  }

  @NonNull
  public static FabAddBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FabAddBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent,
      boolean attachToParent) {
    View root = inflater.inflate(R.layout.fab_add, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FabAddBinding bind(@NonNull View rootView) {
    if (rootView == null) {
      throw new NullPointerException("rootView");
    }

    FloatingActionButton fabAdd = (FloatingActionButton) rootView;

    return new FabAddBinding((FloatingActionButton) rootView, fabAdd);
  }
}