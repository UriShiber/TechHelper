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
    if (bundle.containsKey("student_id")) {
      String studentId;
      studentId = bundle.getString("student_id");
      if (studentId == null) {
        throw new IllegalArgumentException("Argument \"student_id\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("student_id", studentId);
    } else {
      throw new IllegalArgumentException("Required argument \"student_id\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static EditPostFragmentArgs fromSavedStateHandle(
      @NonNull SavedStateHandle savedStateHandle) {
    EditPostFragmentArgs __result = new EditPostFragmentArgs();
    if (savedStateHandle.contains("student_id")) {
      String studentId;
      studentId = savedStateHandle.get("student_id");
      if (studentId == null) {
        throw new IllegalArgumentException("Argument \"student_id\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("student_id", studentId);
    } else {
      throw new IllegalArgumentException("Required argument \"student_id\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public String getStudentId() {
    return (String) arguments.get("student_id");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
    Bundle __result = new Bundle();
    if (arguments.containsKey("student_id")) {
      String studentId = (String) arguments.get("student_id");
      __result.putString("student_id", studentId);
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public SavedStateHandle toSavedStateHandle() {
    SavedStateHandle __result = new SavedStateHandle();
    if (arguments.containsKey("student_id")) {
      String studentId = (String) arguments.get("student_id");
      __result.set("student_id", studentId);
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
    if (arguments.containsKey("student_id") != that.arguments.containsKey("student_id")) {
      return false;
    }
    if (getStudentId() != null ? !getStudentId().equals(that.getStudentId()) : that.getStudentId() != null) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + (getStudentId() != null ? getStudentId().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "EditPostFragmentArgs{"
        + "studentId=" + getStudentId()
        + "}";
  }

  public static final class Builder {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    public Builder(@NonNull EditPostFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    @SuppressWarnings("unchecked")
    public Builder(@NonNull String studentId) {
      if (studentId == null) {
        throw new IllegalArgumentException("Argument \"student_id\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("student_id", studentId);
    }

    @NonNull
    public EditPostFragmentArgs build() {
      EditPostFragmentArgs result = new EditPostFragmentArgs(arguments);
      return result;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setStudentId(@NonNull String studentId) {
      if (studentId == null) {
        throw new IllegalArgumentException("Argument \"student_id\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("student_id", studentId);
      return this;
    }

    @SuppressWarnings({"unchecked","GetterOnBuilder"})
    @NonNull
    public String getStudentId() {
      return (String) arguments.get("student_id");
    }
  }
}
