// Generated by view binder compiler. Do not edit!
package com.example.radio.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import com.example.radio.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class CustomAlertDialogeBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final EditText editreasoneTextView;

  @NonNull
  public final Button submitButton;

  private CustomAlertDialogeBinding(@NonNull CardView rootView,
      @NonNull EditText editreasoneTextView, @NonNull Button submitButton) {
    this.rootView = rootView;
    this.editreasoneTextView = editreasoneTextView;
    this.submitButton = submitButton;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static CustomAlertDialogeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static CustomAlertDialogeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.custom_alert_dialoge, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static CustomAlertDialogeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.editreasoneTextView;
      EditText editreasoneTextView = rootView.findViewById(id);
      if (editreasoneTextView == null) {
        break missingId;
      }

      id = R.id.submitButton;
      Button submitButton = rootView.findViewById(id);
      if (submitButton == null) {
        break missingId;
      }

      return new CustomAlertDialogeBinding((CardView) rootView, editreasoneTextView, submitButton);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}