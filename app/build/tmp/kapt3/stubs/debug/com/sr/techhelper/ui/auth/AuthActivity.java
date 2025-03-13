package com.sr.techhelper.ui.auth;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\u0010\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u0014H\u0002R)\u0010\u0003\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0012\u0010\n\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006\u001b"}, d2 = {"Lcom/sr/techhelper/ui/auth/AuthActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "signInLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "getSignInLauncher", "()Landroidx/activity/result/ActivityResultLauncher;", "signInLauncher$delegate", "Lkotlin/Lazy;", "supportedAuth", "Ljava/util/ArrayList;", "Lcom/firebase/ui/auth/AuthUI$IdpConfig;", "viewModel", "Lcom/sr/techhelper/ui/auth/AuthViewModel;", "getViewModel", "()Lcom/sr/techhelper/ui/auth/AuthViewModel;", "viewModel$delegate", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onSignInResult", "result", "Lcom/firebase/ui/auth/data/model/FirebaseAuthUIAuthenticationResult;", "toApp", "app_debug"})
public final class AuthActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy signInLauncher$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.ArrayList<com.firebase.ui.auth.AuthUI.IdpConfig> supportedAuth = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    
    public AuthActivity() {
        super();
    }
    
    private final androidx.activity.result.ActivityResultLauncher<android.content.Intent> getSignInLauncher() {
        return null;
    }
    
    private final com.sr.techhelper.ui.auth.AuthViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void onSignInResult(com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult result) {
    }
    
    private final void toApp() {
    }
}