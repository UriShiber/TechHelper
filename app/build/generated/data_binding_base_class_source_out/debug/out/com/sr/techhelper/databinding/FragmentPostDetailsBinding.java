// Generated by view binder compiler. Do not edit!
package com.sr.techhelper.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.sr.techhelper.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentPostDetailsBinding implements ViewBinding {
  @NonNull
  private final ScrollView rootView;

  @NonNull
  public final AppCompatImageButton backButton;

  @NonNull
  public final Button editButton;

  @NonNull
  public final TextView postDescriptionTextView;

  @NonNull
  public final ImageView postImageView;

  @NonNull
  public final TextView postLocationLatTextView;

  @NonNull
  public final TextView postLocationLngTextView;

  @NonNull
  public final TextView postTitleTextView;

  @NonNull
  public final TextView postUserIdTextView;

  private FragmentPostDetailsBinding(@NonNull ScrollView rootView,
      @NonNull AppCompatImageButton backButton, @NonNull Button editButton,
      @NonNull TextView postDescriptionTextView, @NonNull ImageView postImageView,
      @NonNull TextView postLocationLatTextView, @NonNull TextView postLocationLngTextView,
      @NonNull TextView postTitleTextView, @NonNull TextView postUserIdTextView) {
    this.rootView = rootView;
    this.backButton = backButton;
    this.editButton = editButton;
    this.postDescriptionTextView = postDescriptionTextView;
    this.postImageView = postImageView;
    this.postLocationLatTextView = postLocationLatTextView;
    this.postLocationLngTextView = postLocationLngTextView;
    this.postTitleTextView = postTitleTextView;
    this.postUserIdTextView = postUserIdTextView;
  }

  @Override
  @NonNull
  public ScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentPostDetailsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentPostDetailsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_post_details, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentPostDetailsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.back_button;
      AppCompatImageButton backButton = ViewBindings.findChildViewById(rootView, id);
      if (backButton == null) {
        break missingId;
      }

      id = R.id.edit_button;
      Button editButton = ViewBindings.findChildViewById(rootView, id);
      if (editButton == null) {
        break missingId;
      }

      id = R.id.post_description_text_view;
      TextView postDescriptionTextView = ViewBindings.findChildViewById(rootView, id);
      if (postDescriptionTextView == null) {
        break missingId;
      }

      id = R.id.post_image_view;
      ImageView postImageView = ViewBindings.findChildViewById(rootView, id);
      if (postImageView == null) {
        break missingId;
      }

      id = R.id.post_location_lat_text_view;
      TextView postLocationLatTextView = ViewBindings.findChildViewById(rootView, id);
      if (postLocationLatTextView == null) {
        break missingId;
      }

      id = R.id.post_location_lng_text_view;
      TextView postLocationLngTextView = ViewBindings.findChildViewById(rootView, id);
      if (postLocationLngTextView == null) {
        break missingId;
      }

      id = R.id.post_title_text_view;
      TextView postTitleTextView = ViewBindings.findChildViewById(rootView, id);
      if (postTitleTextView == null) {
        break missingId;
      }

      id = R.id.post_user_id_text_view;
      TextView postUserIdTextView = ViewBindings.findChildViewById(rootView, id);
      if (postUserIdTextView == null) {
        break missingId;
      }

      return new FragmentPostDetailsBinding((ScrollView) rootView, backButton, editButton,
          postDescriptionTextView, postImageView, postLocationLatTextView, postLocationLngTextView,
          postTitleTextView, postUserIdTextView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
