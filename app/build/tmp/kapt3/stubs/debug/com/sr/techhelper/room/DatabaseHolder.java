package com.sr.techhelper.room;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0004J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/sr/techhelper/room/DatabaseHolder;", "", "()V", "appDatabase", "Lcom/sr/techhelper/room/AppDatabase;", "getDatabase", "initDatabase", "", "context", "Landroid/content/Context;", "app_debug"})
public final class DatabaseHolder {
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.sr.techhelper.room.AppDatabase appDatabase;
    @org.jetbrains.annotations.NotNull()
    public static final com.sr.techhelper.room.DatabaseHolder INSTANCE = null;
    
    private DatabaseHolder() {
        super();
    }
    
    public final void initDatabase(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.sr.techhelper.room.AppDatabase getDatabase() {
        return null;
    }
}