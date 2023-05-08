// Generated by view binder compiler. Do not edit!
package com.example.progettomobile_07_05.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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

public final class DetailsBinding implements ViewBinding {
  @NonNull
  private final LinearLayoutCompat rootView;

  @NonNull
  public final ImageView deleteitem;

  @NonNull
  public final TextView productDescription;

  @NonNull
  public final ImageView productImage;

  @NonNull
  public final TextView productName;

  @NonNull
  public final TextView productPosition;

  @NonNull
  public final TextView productPrice;

  @NonNull
  public final Button shareButton;

  @NonNull
  public final TextView txtdescrizione;

  @NonNull
  public final TextView txtposizione;

  @NonNull
  public final TextView txtprezzo;

  private DetailsBinding(@NonNull LinearLayoutCompat rootView, @NonNull ImageView deleteitem,
      @NonNull TextView productDescription, @NonNull ImageView productImage,
      @NonNull TextView productName, @NonNull TextView productPosition,
      @NonNull TextView productPrice, @NonNull Button shareButton, @NonNull TextView txtdescrizione,
      @NonNull TextView txtposizione, @NonNull TextView txtprezzo) {
    this.rootView = rootView;
    this.deleteitem = deleteitem;
    this.productDescription = productDescription;
    this.productImage = productImage;
    this.productName = productName;
    this.productPosition = productPosition;
    this.productPrice = productPrice;
    this.shareButton = shareButton;
    this.txtdescrizione = txtdescrizione;
    this.txtposizione = txtposizione;
    this.txtprezzo = txtprezzo;
  }

  @Override
  @NonNull
  public LinearLayoutCompat getRoot() {
    return rootView;
  }

  @NonNull
  public static DetailsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DetailsBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent,
      boolean attachToParent) {
    View root = inflater.inflate(R.layout.details, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DetailsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.deleteitem;
      ImageView deleteitem = ViewBindings.findChildViewById(rootView, id);
      if (deleteitem == null) {
        break missingId;
      }

      id = R.id.product_description;
      TextView productDescription = ViewBindings.findChildViewById(rootView, id);
      if (productDescription == null) {
        break missingId;
      }

      id = R.id.product_image;
      ImageView productImage = ViewBindings.findChildViewById(rootView, id);
      if (productImage == null) {
        break missingId;
      }

      id = R.id.product_name;
      TextView productName = ViewBindings.findChildViewById(rootView, id);
      if (productName == null) {
        break missingId;
      }

      id = R.id.product_position;
      TextView productPosition = ViewBindings.findChildViewById(rootView, id);
      if (productPosition == null) {
        break missingId;
      }

      id = R.id.product_price;
      TextView productPrice = ViewBindings.findChildViewById(rootView, id);
      if (productPrice == null) {
        break missingId;
      }

      id = R.id.share_button;
      Button shareButton = ViewBindings.findChildViewById(rootView, id);
      if (shareButton == null) {
        break missingId;
      }

      id = R.id.txtdescrizione;
      TextView txtdescrizione = ViewBindings.findChildViewById(rootView, id);
      if (txtdescrizione == null) {
        break missingId;
      }

      id = R.id.txtposizione;
      TextView txtposizione = ViewBindings.findChildViewById(rootView, id);
      if (txtposizione == null) {
        break missingId;
      }

      id = R.id.txtprezzo;
      TextView txtprezzo = ViewBindings.findChildViewById(rootView, id);
      if (txtprezzo == null) {
        break missingId;
      }

      return new DetailsBinding((LinearLayoutCompat) rootView, deleteitem, productDescription,
          productImage, productName, productPosition, productPrice, shareButton, txtdescrizione,
          txtposizione, txtprezzo);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}