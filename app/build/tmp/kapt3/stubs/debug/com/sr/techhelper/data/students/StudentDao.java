package com.sr.techhelper.data.students;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J!\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005\"\u00020\u0006H\'\u00a2\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0006H\'J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\'J\u0014\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u000e0\rH\'J\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\r2\u0006\u0010\n\u001a\u00020\u000bH\'J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0006H\'J!\u0010\u0011\u001a\u00020\u00032\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005\"\u00020\u0006H\'\u00a2\u0006\u0002\u0010\u0007\u00a8\u0006\u0012"}, d2 = {"Lcom/sr/techhelper/data/students/StudentDao;", "", "add", "", "student", "", "Lcom/sr/techhelper/data/students/StudentModel;", "([Lcom/sr/techhelper/data/students/StudentModel;)V", "delete", "deleteById", "id", "", "getAllStudents", "Landroidx/lifecycle/LiveData;", "", "getById", "update", "upsertAll", "app_debug"})
@androidx.room.Dao()
public abstract interface StudentDao {
    
    @androidx.room.Query(value = "SELECT * FROM students")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.sr.techhelper.data.students.StudentModel>> getAllStudents();
    
    @androidx.room.Query(value = "SELECT * FROM students WHERE id = :id")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<com.sr.techhelper.data.students.StudentModel> getById(@org.jetbrains.annotations.NotNull()
    java.lang.String id);
    
    @androidx.room.Insert(onConflict = 1)
    public abstract void add(@org.jetbrains.annotations.NotNull()
    com.sr.techhelper.data.students.StudentModel... student);
    
    @androidx.room.Update()
    public abstract void update(@org.jetbrains.annotations.NotNull()
    com.sr.techhelper.data.students.StudentModel student);
    
    @androidx.room.Delete()
    public abstract void delete(@org.jetbrains.annotations.NotNull()
    com.sr.techhelper.data.students.StudentModel student);
    
    @androidx.room.Query(value = "DELETE FROM students WHERE id = :id")
    public abstract void deleteById(@org.jetbrains.annotations.NotNull()
    java.lang.String id);
    
    @androidx.room.Upsert()
    public abstract void upsertAll(@org.jetbrains.annotations.NotNull()
    com.sr.techhelper.data.students.StudentModel... student);
}