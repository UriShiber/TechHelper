package com.sr.techhelper.ui.main.fragments.create_post;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.sr.techhelper.R;

public class CreatePostFragmentDirections {
  private CreatePostFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionCreatePostFragmentToPostListFragment() {
    return new ActionOnlyNavDirections(R.id.action_createPostFragment_to_postListFragment);
  }
}
