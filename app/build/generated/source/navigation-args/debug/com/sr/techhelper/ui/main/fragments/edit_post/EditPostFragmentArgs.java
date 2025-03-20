package com.sr.techhelper.ui.main.fragments.edit_post;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.lifecycle.SavedStateHandle;
import androidx.navigation.NavArgs;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class EditPostFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private EditPostFragmentArgs() {
  }

  @SuppressWarnings("unchecked")
  private EditPostFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static EditPostFragmentArgs fromBundle(@NonNull Bundle bundle) {
    EditPostFragmentArgs __result = new EditPostFragmentArgs();
    bundle.setClassLoader(EditPostFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("post_id")) {
      String postId;
      postId = bundle.getString("post_id");
      if (postId == null) {
        throw new IllegalArgumentException("Argument \"post_id\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("post_id", postId);
    } else {
      throw new IllegalArgumentException("Required argument \"post_id\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static EditPostFragmentArgs fromSavedStateHandle(
      @NonNull SavedStateHandle savedStateHandle) {
    EditPostFragmentArgs __result = new EditPostFragmentArgs();
    if (savedStateHandle.contains("post_id")) {
      String postId;
      postId = savedStateHandle.get("post_id");
      if (postId == null) {
        throw new IllegalArgumentException("Argument \"post_id\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("post_id", postId);
    } else {
      throw new IllegalArgumentException("Required argument \"post_id\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public String getPostId() {
    return (String) arguments.get("post_id");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
    Bundle __result = new Bundle();
    if (arguments.containsKey("post_id")) {
      String postId = (String) arguments.get("post_id");
      __result.putString("post_id", postId);
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public SavedStateHandle toSavedStateHandle() {
    SavedStateHandle __result = new SavedStateHandle();
    if (arguments.containsKey("post_id")) {
      String postId = (String) arguments.get("post_id");
      __result.set("post_id", postId);
    }
    return __result;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
        return true;
    }
    if (object == null || getClass() != object.getClass()) {
        return false;
    }
    EditPostFragmentArgs that = (EditPostFragmentArgs) object;
    if (arguments.containsKey("post_id") != that.arguments.containsKey("post_id")) {
      return false;
    }
    if (getPostId() != null ? !getPostId().equals(that.getPostId()) : that.getPostId() != null) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + (getPostId() != null ? getPostId().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "EditPostFragmentArgs{"
        + "postId=" + getPostId()
        + "}";
  }

  public static final class Builder {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    public Builder(@NonNull EditPostFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    @SuppressWarnings("unchecked")
    public Builder(@NonNull String postId) {
      if (postId == null) {
        throw new IllegalArgumentException("Argument \"post_id\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("post_id", postId);
    }

    @NonNull
    public EditPostFragmentArgs build() {
      EditPostFragmentArgs result = new EditPostFragmentArgs(arguments);
      return result;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setPostId(@NonNull String postId) {
      if (postId == null) {
        throw new IllegalArgumentException("Argument \"post_id\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("post_id", postId);
      return this;
    }

    @SuppressWarnings({"unchecked","GetterOnBuilder"})
    @NonNull
    public String getPostId() {
      return (String) arguments.get("post_id");
    }
  }
}
