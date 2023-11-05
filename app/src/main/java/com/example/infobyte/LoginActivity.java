package com.example.infobyte;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    private EditText mobNoBox, pwBox;
    private TextView registerLink;
    private TextView loginButton;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users");

        mobNoBox = findViewById(R.id.mobNoBox); // Assume this is the mobile number field
        pwBox = findViewById(R.id.mpwBoxx); // This is the password field
        loginButton = findViewById(R.id.login2);
        registerLink = findViewById(R.id.registerLink);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve mobile number from the mobNoBox
                String mobNo = mobNoBox.getText().toString();

                // Create an email by appending "@gmail.com" to the mobile number
                final String email = mobNo + "@gmail.com";

                // Retrieve the password from the password field
                String password = pwBox.getText().toString();

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign-in successful
                                    Toast.makeText(LoginActivity.this, "User Signed In", Toast.LENGTH_SHORT).show();

                                    // You can navigate to the main activity or perform other actions here.
                                    Intent intent = new Intent(LoginActivity.this, ViewActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                } else {
                                    // Sign-in failed
                                    Toast.makeText(LoginActivity.this, "Sign-in failed, try again", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // You can add code to navigate to the registration activity here
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
