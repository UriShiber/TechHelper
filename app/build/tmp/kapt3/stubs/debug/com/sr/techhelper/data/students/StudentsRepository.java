package com.sr.techhelper.data.students;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J%\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\t2\u0010\u0010\u000e\u001a\f\u0012\u0004\u0012\u00020\f0\u000fj\u0002`\u0010\u00a2\u0006\u0002\u0010\u0011J%\u0010\u0012\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\t2\u0010\u0010\u000e\u001a\f\u0012\u0004\u0012\u00020\f0\u000fj\u0002`\u0010\u00a2\u0006\u0002\u0010\u0011J%\u0010\u0013\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\t2\u0010\u0010\u000e\u001a\f\u0012\u0004\u0012\u00020\f0\u000fj\u0002`\u0010\u00a2\u0006\u0002\u0010\u0011J$\u0010\u0014\u001a\u00020\f2\u001c\u0010\u000e\u001a\u0018\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u0016\u0012\u0004\u0012\u00020\f0\u0015j\u0002`\u0017R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\n\u00a8\u0006\u0019"}, d2 = {"Lcom/sr/techhelper/data/students/StudentsRepository;", "", "()V", "executor", "Ljava/util/concurrent/ExecutorService;", "kotlin.jvm.PlatformType", "mainHandler", "Landroid/os/Handler;", "studentDao", "error/NonExistentClass", "Lerror/NonExistentClass;", "add", "", "student", "callback", "Lkotlin/Function0;", "Lcom/sr/techhelper/data/students/EmptyCallback;", "(Lerror/NonExistentClass;Lkotlin/jvm/functions/Function0;)V", "delete", "edit", "getAllStudents", "Lkotlin/Function1;", "", "Lcom/sr/techhelper/data/students/StudentsCallback;", "Companion", "app_debug"})
public final class StudentsRepository {
    @org.jetbrains.annotations.NotNull()
    private final error.NonExistentClass studentDao = null;
    private final java.util.concurrent.ExecutorService executor = null;
    @org.jetbrains.annotations.NotNull()
    private final android.os.Handler mainHandler = null;
    @org.jetbrains.annotations.NotNull()
    private static final com.sr.techhelper.data.students.StudentsRepository shared = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.sr.techhelper.data.students.StudentsRepository.Companion Companion = null;
    
    private StudentsRepository() {
        super();
    }
    
    public final void getAllStudents(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.util.List<error.NonExistentClass>, kotlin.Unit> callback) {
    }
    
    public final void add(@org.jetbrains.annotations.NotNull()
    error.NonExistentClass student, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> callback) {
    }
    
    public final void edit(@org.jetbrains.annotations.NotNull()
    error.NonExistentClass student, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> callback) {
    }
    
    public final void delete(@org.jetbrains.annotations.NotNull()
    error.NonExistentClass student, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> callback) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/sr/techhelper/data/students/StudentsRepository$Companion;", "", "()V", "shared", "Lcom/sr/techhelper/data/students/StudentsRepository;", "getShared", "()Lcom/sr/techhelper/data/students/StudentsRepository;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.sr.techhelper.data.students.StudentsRepository getShared() {
            return null;
        }
    }
}