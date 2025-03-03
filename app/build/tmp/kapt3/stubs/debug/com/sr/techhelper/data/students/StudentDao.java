package com.sr.techhelper.data.students;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007H\'J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\'J!\u0010\u000b\u001a\u00020\u00032\u0012\u0010\f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\r\"\u00020\u0005H\'\u00a2\u0006\u0002\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'\u00a8\u0006\u0010"}, d2 = {"Lcom/sr/techhelper/data/students/StudentDao;", "", "deleteStudent", "", "student", "Lcom/sr/techhelper/data/students/StudentModel;", "getAllStudents", "", "getStudentById", "id", "", "insertStudents", "students", "", "([Lcom/sr/techhelper/data/students/StudentModel;)V", "updateStudent", "app_debug"})
@androidx.room.Dao()
public abstract interface StudentDao {
    
    @androidx.room.Query(value = "SELECT * FROM student")
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.List<com.sr.techhelper.data.students.StudentModel> getAllStudents();
    
    @androidx.room.Query(value = "SELECT * FROM student WHERE id = :id")
    @org.jetbrains.annotations.NotNull()
    public abstract com.sr.techhelper.data.students.StudentModel getStudentById(@org.jetbrains.annotations.NotNull()
    java.lang.String id);
    
    @androidx.room.Insert(onConflict = 1)
    public abstract void insertStudents(@org.jetbrains.annotations.NotNull()
    com.sr.techhelper.data.students.StudentModel... students);
    
    @androidx.room.Update()
    public abstract void updateStudent(@org.jetbrains.annotations.NotNull()
    com.sr.techhelper.data.students.StudentModel student);
    
    @androidx.room.Delete()
    public abstract void deleteStudent(@org.jetbrains.annotations.NotNull()
    com.sr.techhelper.data.students.StudentModel student);
}