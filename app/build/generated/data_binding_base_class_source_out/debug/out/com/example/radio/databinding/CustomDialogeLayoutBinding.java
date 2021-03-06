// Generated by view binder compiler. Do not edit!
package com.example.radio.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import com.example.radio.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class CustomDialogeLayoutBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final TextView canceltextview;

  @NonNull
  public final TextInputLayout emailtext;

  @NonNull
  public final TextView emailtextView22;

  @NonNull
  public final TextInputEditText forgotEditTextName;

  @NonNull
  public final TextView forgotloginButton;

  private CustomDialogeLayoutBinding(@NonNull CardView rootView, @NonNull TextView canceltextview,
      @NonNull TextInputLayout emailtext, @NonNull TextView emailtextView22,
      @NonNull TextInputEditText forgotEditTextName, @NonNull TextView forgotloginButton) {
    this.rootView = rootView;
    this.canceltextview = canceltextview;
    this.emailtext = emailtext;
    this.emailtextView22 = emailtextView22;
    this.forgotEditTextName = forgotEditTextName;
    this.forgotloginButton = forgotloginButton;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static CustomDialogeLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static CustomDialogeLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.custom_dialoge_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static CustomDialogeLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.canceltextview;
      TextView canceltextview = rootView.findViewById(id);
      if (canceltextview == null) {
        break missingId;
      }

      id = R.id.emailtext;
      TextInputLayout emailtext = rootView.findViewById(id);
      if (emailtext == null) {
        break missingId;
      }

      id = R.id.emailtextView22;
      TextView emailtextView22 = rootView.findViewById(id);
      if (emailtextView22 == null) {
        break missingId;
      }

      id = R.id.forgotEditTextName;
      TextInputEditText forgotEditTextName = rootView.findViewById(id);
      if (forgotEditTextName == null) {
        break missingId;
      }

      id = R.id.forgotloginButton;
      TextView forgotloginButton = rootView.findViewById(id);
      if (forgotloginButton == null) {
        break missingId;
      }

      return new CustomDialogeLayoutBinding((CardView) rootView, canceltextview, emailtext,
          emailtextView22, forgotEditTextName, forgotloginButton);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
