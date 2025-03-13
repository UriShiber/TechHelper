package com.sr.techhelper.ui.main.fragments.students_list;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u0015B\u0019\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004\u00a2\u0006\u0002\u0010\u0007J\b\u0010\n\u001a\u00020\u000bH\u0016J\u001c\u0010\f\u001a\u00020\u00062\n\u0010\r\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000bH\u0016J\u001c\u0010\u000f\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000bH\u0016J\u0014\u0010\u0013\u001a\u00020\u00062\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\tR\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/sr/techhelper/ui/main/fragments/students_list/StudentsAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/sr/techhelper/ui/main/fragments/students_list/StudentsAdapter$StudentViewHolder;", "onStudentClick", "Lkotlin/Function1;", "Lcom/sr/techhelper/data/students/StudentModel;", "", "(Lkotlin/jvm/functions/Function1;)V", "students", "", "getItemCount", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "updateStudents", "newStudents", "StudentViewHolder", "app_debug"})
public final class StudentsAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.sr.techhelper.ui.main.fragments.students_list.StudentsAdapter.StudentViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.sr.techhelper.data.students.StudentModel, kotlin.Unit> onStudentClick = null;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.sr.techhelper.data.students.StudentModel> students;
    
    public StudentsAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.sr.techhelper.data.students.StudentModel, kotlin.Unit> onStudentClick) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.sr.techhelper.ui.main.fragments.students_list.StudentsAdapter.StudentViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.sr.techhelper.ui.main.fragments.students_list.StudentsAdapter.StudentViewHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    public final void updateStudents(@org.jetbrains.annotations.NotNull()
    java.util.List<com.sr.techhelper.data.students.StudentModel> newStudents) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/sr/techhelper/ui/main/fragments/students_list/StudentsAdapter$StudentViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/sr/techhelper/ui/main/fragments/students_list/StudentsAdapter;Landroid/view/View;)V", "checkBox", "Landroid/widget/CheckBox;", "studentId", "Landroid/widget/TextView;", "studentName", "bind", "", "student", "Lcom/sr/techhelper/data/students/StudentModel;", "app_debug"})
    public final class StudentViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView studentName = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView studentId = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.CheckBox checkBox = null;
        
        public StudentViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View itemView) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.sr.techhelper.data.students.StudentModel student) {
        }
    }
}