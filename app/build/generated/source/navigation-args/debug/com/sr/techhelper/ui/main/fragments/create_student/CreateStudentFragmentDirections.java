package com.sr.techhelper.ui.main.fragments.create_student;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.sr.techhelper.R;

public class CreateStudentFragmentDirections {
  private CreateStudentFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionCreateStudentFragmentToStudentsListFragment() {
    return new ActionOnlyNavDirections(R.id.action_createStudentFragment_to_studentsListFragment);
  }
}
