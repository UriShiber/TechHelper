package com.sr.techhelper.data.users;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0011\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u000bJ\"\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\rH\u0086@\u00a2\u0006\u0002\u0010\u0010J\u0016\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u000bJ\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\t\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u000bJ\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\t\u001a\u00020\nH\u0082@\u00a2\u0006\u0002\u0010\u000bJ\"\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\rH\u0086@\u00a2\u0006\u0002\u0010\u0010J\"\u0010\u0016\u001a\u00020\b2\u0012\u0010\u0017\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000e0\u0018\"\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u0019J\u0016\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u001cR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/sr/techhelper/data/users/UsersRepository;", "", "()V", "firestoreHandle", "Lcom/google/firebase/firestore/CollectionReference;", "usersDao", "Lcom/sr/techhelper/data/users/UsersDao;", "cacheUserIfNotExisting", "", "uid", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cacheUsersIfNotExisting", "", "Lcom/sr/techhelper/data/users/UserModel;", "uids", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteById", "id", "getUserByUid", "getUserFromRemoteSource", "getUsersFromRemoteSource", "insertUsers", "users", "", "([Lcom/sr/techhelper/data/users/UserModel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "upsertUser", "user", "(Lcom/sr/techhelper/data/users/UserModel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class UsersRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.sr.techhelper.data.users.UsersDao usersDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.CollectionReference firestoreHandle = null;
    
    public UsersRepository() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertUsers(@org.jetbrains.annotations.NotNull()
    com.sr.techhelper.data.users.UserModel[] users, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object upsertUser(@org.jetbrains.annotations.NotNull()
    com.sr.techhelper.data.users.UserModel user, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteById(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object cacheUserIfNotExisting(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object cacheUsersIfNotExisting(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> uids, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.sr.techhelper.data.users.UserModel>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getUserByUid(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.sr.techhelper.data.users.UserModel> $completion) {
        return null;
    }
    
    private final java.lang.Object getUserFromRemoteSource(java.lang.String uid, kotlin.coroutines.Continuation<? super com.sr.techhelper.data.users.UserModel> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getUsersFromRemoteSource(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> uids, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.sr.techhelper.data.users.UserModel>> $completion) {
        return null;
    }
}