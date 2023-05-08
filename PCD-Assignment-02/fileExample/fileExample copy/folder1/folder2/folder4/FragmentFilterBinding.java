// Generated by view binder compiler. Do not edit!
package com.example.progettomobile_07_05.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.progettomobile_07_05.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentFilterBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final LinearLayoutCompat lineardistanza;

  @NonNull
  public final LinearLayoutCompat linearprice;

  @NonNull
  public final TextView msgprice;

  @NonNull
  public final TextView msgtitle;

  @NonNull
  public final Button openmapbtn;

  @NonNull
  public final NumberPicker pricemaxpicker;

  @NonNull
  public final NumberPicker priceminpicker;

  @NonNull
  public final TextView rangefilter;

  @NonNull
  public final Button savefilter;

  @NonNull
  public final TextView textpricemax;

  @NonNull
  public final TextView textpricemin;

  private FragmentFilterBinding(@NonNull RelativeLayout rootView,
      @NonNull LinearLayoutCompat lineardistanza, @NonNull LinearLayoutCompat linearprice,
      @NonNull TextView msgprice, @NonNull TextView msgtitle, @NonNull Button openmapbtn,
      @NonNull NumberPicker pricemaxpicker, @NonNull NumberPicker priceminpicker,
      @NonNull TextView rangefilter, @NonNull Button savefilter, @NonNull TextView textpricemax,
      @NonNull TextView textpricemin) {
    this.rootView = rootView;
    this.lineardistanza = lineardistanza;
    this.linearprice = linearprice;
    this.msgprice = msgprice;
    this.msgtitle = msgtitle;
    this.openmapbtn = openmapbtn;
    this.pricemaxpicker = pricemaxpicker;
    this.priceminpicker = priceminpicker;
    this.rangefilter = rangefilter;
    this.savefilter = savefilter;
    this.textpricemax = textpricemax;
    this.textpricemin = textpricemin;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentFilterBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentFilterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_filter, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentFilterBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.lineardistanza;
      LinearLayoutCompat lineardistanza = ViewBindings.findChildViewById(rootView, id);
      if (lineardistanza == null) {
        break missingId;
      }

      id = R.id.linearprice;
      LinearLayoutCompat linearprice = ViewBindings.findChildViewById(rootView, id);
      if (linearprice == null) {
        break missingId;
      }

      id = R.id.msgprice;
      TextView msgprice = ViewBindings.findChildViewById(rootView, id);
      if (msgprice == null) {
        break missingId;
      }

      id = R.id.msgtitle;
      TextView msgtitle = ViewBindings.findChildViewById(rootView, id);
      if (msgtitle == null) {
        break missingId;
      }

      id = R.id.openmapbtn;
      Button openmapbtn = ViewBindings.findChildViewById(rootView, id);
      if (openmapbtn == null) {
        break missingId;
      }

      id = R.id.pricemaxpicker;
      NumberPicker pricemaxpicker = ViewBindings.findChildViewById(rootView, id);
      if (pricemaxpicker == null) {
        break missingId;
      }

      id = R.id.priceminpicker;
      NumberPicker priceminpicker = ViewBindings.findChildViewById(rootView, id);
      if (priceminpicker == null) {
        break missingId;
      }

      id = R.id.rangefilter;
      TextView rangefilter = ViewBindings.findChildViewById(rootView, id);
      if (rangefilter == null) {
        break missingId;
      }

      id = R.id.savefilter;
      Button savefilter = ViewBindings.findChildViewById(rootView, id);
      if (savefilter == null) {
        break missingId;
      }

      id = R.id.textpricemax;
      TextView textpricemax = ViewBindings.findChildViewById(rootView, id);
      if (textpricemax == null) {
        break missingId;
      }

      id = R.id.textpricemin;
      TextView textpricemin = ViewBindings.findChildViewById(rootView, id);
      if (textpricemin == null) {
        break missingId;
      }

      return new FragmentFilterBinding((RelativeLayout) rootView, lineardistanza, linearprice,
          msgprice, msgtitle, openmapbtn, pricemaxpicker, priceminpicker, rangefilter, savefilter,
          textpricemax, textpricemin);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
