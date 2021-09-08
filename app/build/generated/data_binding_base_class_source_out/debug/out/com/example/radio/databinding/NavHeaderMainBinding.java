// Generated by view binder compiler. Do not edit!
package com.example.radio.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.example.radio.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class NavHeaderMainBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final CircleImageView imageViewProfile;

  @NonNull
  public final TextView textViewEmail;

  @NonNull
  public final TextView textViewName;

  private NavHeaderMainBinding(@NonNull ConstraintLayout rootView,
      @NonNull CircleImageView imageViewProfile, @NonNull TextView textViewEmail,
      @NonNull TextView textViewName) {
    this.rootView = rootView;
    this.imageViewProfile = imageViewProfile;
    this.textViewEmail = textViewEmail;
    this.textViewName = textViewName;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static NavHeaderMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static NavHeaderMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.nav_header_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static NavHeaderMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.imageView_profile;
      CircleImageView imageViewProfile = rootView.findViewById(id);
      if (imageViewProfile == null) {
        break missingId;
      }

      id = R.id.textView_email;
      TextView textViewEmail = rootView.findViewById(id);
      if (textViewEmail == null) {
        break missingId;
      }

      id = R.id.textView_name;
      TextView textViewName = rootView.findViewById(id);
      if (textViewName == null) {
        break missingId;
      }

      return new NavHeaderMainBinding((ConstraintLayout) rootView, imageViewProfile, textViewEmail,
          textViewName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}