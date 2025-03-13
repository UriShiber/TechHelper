package com.sr.techhelper.ui.main.fragments.edit_student;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.sr.techhelper.R;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class EditStudentFragmentDirections {
  private EditStudentFragmentDirections() {
  }

  @NonNull
  public static ActionEditStudentFragmentToStudentDetailsFragment actionEditStudentFragmentToStudentDetailsFragment(
      @NonNull String studentId) {
    return new ActionEditStudentFragmentToStudentDetailsFragment(studentId);
  }

  @NonNull
  public static NavDirections actionEditStudentFragmentToStudentsListFragment() {
    return new ActionOnlyNavDirections(R.id.action_editStudentFragment_to_studentsListFragment);
  }

  public static class ActionEditStudentFragmentToStudentDetailsFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private ActionEditStudentFragmentToStudentDetailsFragment(@NonNull String studentId) {
      if (studentId == null) {
        throw new IllegalArgumentException("Argument \"student_id\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("student_id", studentId);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionEditStudentFragmentToStudentDetailsFragment setStudentId(
        @NonNull String studentId) {
      if (studentId == null) {
        throw new IllegalArgumentException("Argument \"student_id\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("student_id", studentId);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("student_id")) {
        String studentId = (String) arguments.get("student_id");
        __result.putString("student_id", studentId);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_editStudentFragment_to_studentDetailsFragment;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getStudentId() {
      return (String) arguments.get("student_id");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionEditStudentFragmentToStudentDetailsFragment that = (ActionEditStudentFragmentToStudentDetailsFragment) object;
      if (arguments.containsKey("student_id") != that.arguments.containsKey("student_id")) {
        return false;
      }
      if (getStudentId() != null ? !getStudentId().equals(that.getStudentId()) : that.getStudentId() != null) {
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
      result = 31 * result + (getStudentId() != null ? getStudentId().hashCode() : 0);
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionEditStudentFragmentToStudentDetailsFragment(actionId=" + getActionId() + "){"
          + "studentId=" + getStudentId()
          + "}";
    }
  }
}
