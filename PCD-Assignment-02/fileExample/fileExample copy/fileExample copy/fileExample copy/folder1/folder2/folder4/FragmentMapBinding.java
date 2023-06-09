// Generated by view binder compiler. Do not edit!
package com.example.progettomobile_07_05.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.progettomobile_07_05.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentMapBinding implements ViewBinding {
  @NonNull
  private final ScrollView rootView;

  @NonNull
  public final Button btnsalvamap;

  @NonNull
  public final TextView distancetext;

  @NonNull
  public final SeekBar seekbar;

  private FragmentMapBinding(@NonNull ScrollView rootView, @NonNull Button btnsalvamap,
      @NonNull TextView distancetext, @NonNull SeekBar seekbar) {
    this.rootView = rootView;
    this.btnsalvamap = btnsalvamap;
    this.distancetext = distancetext;
    this.seekbar = seekbar;
  }

  @Override
  @NonNull
  public ScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentMapBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentMapBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_map, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentMapBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnsalvamap;
      Button btnsalvamap = ViewBindings.findChildViewById(rootView, id);
      if (btnsalvamap == null) {
        break missingId;
      }

      id = R.id.distancetext;
      TextView distancetext = ViewBindings.findChildViewById(rootView, id);
      if (distancetext == null) {
        break missingId;
      }

      id = R.id.seekbar;
      SeekBar seekbar = ViewBindings.findChildViewById(rootView, id);
      if (seekbar == null) {
        break missingId;
      }

      return new FragmentMapBinding((ScrollView) rootView, btnsalvamap, distancetext, seekbar);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
