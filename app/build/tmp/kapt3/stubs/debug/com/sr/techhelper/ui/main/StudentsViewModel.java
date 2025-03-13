package com.sr.techhelper.ui.main;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0012\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00110\u0010J\u0016\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u00102\u0006\u0010\u0014\u001a\u00020\rJ\u0006\u0010\u0015\u001a\u00020\bJ\u000e\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u0013R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/sr/techhelper/ui/main/StudentsViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "repository", "Lcom/sr/techhelper/data/students/StudentsRepository;", "usersRepository", "Lcom/sr/techhelper/data/users/UsersRepository;", "addStudent", "", "student", "Lcom/sr/techhelper/data/students/StudentModel;", "deleteStudentById", "studentId", "", "editStudent", "getAllStudents", "Landroidx/lifecycle/LiveData;", "", "getUserById", "Lcom/sr/techhelper/data/users/UserModel;", "id", "invalidateStudents", "updateUser", "user", "app_debug"})
public final class StudentsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.sr.techhelper.data.students.StudentsRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.sr.techhelper.data.users.UsersRepository usersRepository = null;
    
    public StudentsViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.sr.techhelper.data.students.StudentModel>> getAllStudents() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.sr.techhelper.data.users.UserModel> getUserById(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
        return null;
    }
    
    public final void updateUser(@org.jetbrains.annotations.NotNull()
    com.sr.techhelper.data.users.UserModel user) {
    }
    
    public final void addStudent(@org.jetbrains.annotations.NotNull()
    com.sr.techhelper.data.students.StudentModel student) {
    }
    
    public final void editStudent(@org.jetbrains.annotations.NotNull()
    com.sr.techhelper.data.students.StudentModel student) {
    }
    
    public final void invalidateStudents() {
    }
    
    public final void deleteStudentById(@org.jetbrains.annotations.NotNull()
    java.lang.String studentId) {
    }
}