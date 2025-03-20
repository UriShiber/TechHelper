package com.sr.techhelper.ui.main.fragments.posts_list;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import com.sr.techhelper.R;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class PostsListFragmentDirections {
  private PostsListFragmentDirections() {
  }

  @NonNull
  public static ActionPostsListFragmentToPostDetailsFragment actionPostsListFragmentToPostDetailsFragment(
      @NonNull String postId) {
    return new ActionPostsListFragmentToPostDetailsFragment(postId);
  }

  public static class ActionPostsListFragmentToPostDetailsFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private ActionPostsListFragmentToPostDetailsFragment(@NonNull String postId) {
      if (postId == null) {
        throw new IllegalArgumentException("Argument \"post_id\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("post_id", postId);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionPostsListFragmentToPostDetailsFragment setPostId(@NonNull String postId) {
      if (postId == null) {
        throw new IllegalArgumentException("Argument \"post_id\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("post_id", postId);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("post_id")) {
        String postId = (String) arguments.get("post_id");
        __result.putString("post_id", postId);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_postsListFragment_to_postDetailsFragment;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getPostId() {
      return (String) arguments.get("post_id");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionPostsListFragmentToPostDetailsFragment that = (ActionPostsListFragmentToPostDetailsFragment) object;
      if (arguments.containsKey("post_id") != that.arguments.containsKey("post_id")) {
        return false;
      }
      if (getPostId() != null ? !getPostId().equals(that.getPostId()) : that.getPostId() != null) {
        return false;
      }
      if (getActionId() != that.getActionId()) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + (getPostId() != null ? getPostId().hashCode() : 0);
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionPostsListFragmentToPostDetailsFragment(actionId=" + getActionId() + "){"
          + "postId=" + getPostId()
          + "}";
    }
  }
}
