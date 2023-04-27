package com.myproject.bimbelapp.ui.register;

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
import com.myproject.bimbelapp.R;
import com.myproject.bimbelapp.ui.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private TextView edId, edName, edEmail, edPassword, edConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button btnRegister = findViewById(R.id.btn_register);
        TextView btnLogin = findViewById(R.id.btn_login);
        edId = findViewById(R.id.ed_id);
        edName = findViewById(R.id.ed_name);
        edEmail = findViewById(R.id.ed_email);
        edPassword = findViewById(R.id.ed_password);
        edConfirmPassword = findViewById(R.id.ed_confirmPassword);

        mAuth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(view1 -> registerUser());

        btnLogin.setOnClickListener(view1 -> startActivity(new Intent(this, LoginActivity.class)));
    }

    private void registerUser() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_loading);
        dialog.setCancelable(false);

        if (dialog.getWindow()!=null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        
        String id = edId.getText().toString();
        String name = edName.getText().toString();
        String email = edEmail.getText().toString().trim();
        String password = edPassword.getText().toString().trim();
        String confirmPassword = edConfirmPassword.getText().toString().trim();

        if (id.isEmpty()) {
            edId.setError("ID bimbel must fill");
            edId.requestFocus();
        } else if (name.length() < 6) {
            edName.setError("minimum 5 char");
            edName.requestFocus();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() && !email.contains(".com")) {
            edEmail.setError("Invalid Email");
            edEmail.requestFocus();
        } else if (password.isEmpty()) {
            edPassword.setError("password must fill");
            edPassword.requestFocus();
        } else if (!password.equals(confirmPassword)) {
            edConfirmPassword.setError("invalid password");
            edConfirmPassword.requestFocus();
        } else {
            dialog.show();
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        dialog.dismiss();
                        if (task.isSuccessful()) {
                            Log.d("RegisterActivity", "createUserWithEmail:success");
                            Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            Log.w("RegisterActivity", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(this, "There's something wrong. Try again later.", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    });
        }
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null){
            mAuth.signOut();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }
}