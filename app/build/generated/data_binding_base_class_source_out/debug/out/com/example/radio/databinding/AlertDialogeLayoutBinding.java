// Generated by view binder compiler. Do not edit!
package com.example.radio.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import com.example.radio.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class AlertDialogeLayoutBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final Button button3;

  @NonNull
  public final Button button4;

  @NonNull
  public final TextView textviewSure;

  @NonNull
  public final View view;

  @NonNull
  public final View viewHoriztontal;

  private AlertDialogeLayoutBinding(@NonNull CardView rootView, @NonNull Button button3,
      @NonNull Button button4, @NonNull TextView textviewSure, @NonNull View view,
      @NonNull View viewHoriztontal) {
    this.rootView = rootView;
    this.button3 = button3;
    this.button4 = button4;
    this.textviewSure = textviewSure;
    this.view = view;
    this.viewHoriztontal = viewHoriztontal;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static AlertDialogeLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static AlertDialogeLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.alert_dialoge_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static AlertDialogeLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.button3;
      Button button3 = rootView.findViewById(id);
      if (button3 == null) {
        break missingId;
      }

      id = R.id.button4;
      Button button4 = rootView.findViewById(id);
      if (button4 == null) {
        break missingId;
      }

      id = R.id.textview_sure;
      TextView textviewSure = rootView.findViewById(id);
      if (textviewSure == null) {
        break missingId;
      }

      id = R.id.view_;
      View view = rootView.findViewById(id);
      if (view == null) {
        break missingId;
      }

      id = R.id.view_horiztontal;
      View viewHoriztontal = rootView.findViewById(id);
      if (viewHoriztontal == null) {
        break missingId;
      }

      return new AlertDialogeLayoutBinding((CardView) rootView, button3, button4, textviewSure,
          view, viewHoriztontal);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}