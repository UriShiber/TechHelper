package com.sr.techhelper.data.users;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\"\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007H\u00a7@\u00a2\u0006\u0002\u0010\tJ\u0018\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\fJ\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000bH\'J!\u0010\u000f\u001a\u00020\u00032\u0012\u0010\u0010\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\u0011\"\u00020\u000bH\'\u00a2\u0006\u0002\u0010\u0012\u00a8\u0006\u0013"}, d2 = {"Lcom/sr/techhelper/data/users/UsersDao;", "", "deleteByUid", "", "id", "", "getExistingUserIds", "", "ids", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUserByUid", "Lcom/sr/techhelper/data/users/UserModel;", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateUserData", "user", "upsertAll", "users", "", "([Lcom/sr/techhelper/data/users/UserModel;)V", "app_debug"})
@androidx.room.Dao()
public abstract interface UsersDao {
    
    @androidx.room.Query(value = "SELECT * FROM users WHERE id LIKE :id LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUserByUid(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.sr.techhelper.data.users.UserModel> $completion);
    
    @androidx.room.Query(value = "SELECT id FROM users WHERE id IN (:ids)")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getExistingUserIds(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> ids, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.String>> $completion);
    
    @androidx.room.Upsert()
    public abstract void upsertAll(@org.jetbrains.annotations.NotNull()
    com.sr.techhelper.data.users.UserModel... users);
    
    @androidx.room.Query(value = "DELETE FROM users WHERE id = :id")
    public abstract void deleteByUid(@org.jetbrains.annotations.NotNull()
    java.lang.String id);
    
    @androidx.room.Update()
    public abstract void updateUserData(@org.jetbrains.annotations.NotNull()
    com.sr.techhelper.data.users.UserModel user);
}