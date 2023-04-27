package com.myproject.bimbelapp.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.myproject.bimbelapp.MainActivity;
import com.myproject.bimbelapp.R;
import com.myproject.bimbelapp.ui.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private TextView edEmail, edPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnLogin = findViewById(R.id.btn_login);
        TextView btnRegister = findViewById(R.id.btn_register);
        edEmail = findViewById(R.id.ed_email);
        edPassword = findViewById(R.id.ed_password);

        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(view -> loginUser());

        btnRegister.setOnClickListener(view1 -> startActivity(new Intent(this, RegisterActivity.class)));
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    private void loginUser() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_loading);
        dialog.setCancelable(false);

        if (dialog.getWindow()!=null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        String email = edEmail.getText().toString();
        String password = edPassword.getText().toString();

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() && !email.contains(".com")) {
            edEmail.setError("Invalid Email");
            edEmail.requestFocus();
        } else if (password.isEmpty()) {
            edPassword.setError("password must fill");
            edPassword.requestFocus();
        } else {
            dialog.show();
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        dialog.dismiss();
                        if (task.isSuccessful()) {
                            Log.d("LoginActivity", "signInWithEmail:success");
                            Toast.makeText(LoginActivity.this, "Login successful.", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            Log.w("LoginActivity", "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Invalid email or password.", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    });
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }
}