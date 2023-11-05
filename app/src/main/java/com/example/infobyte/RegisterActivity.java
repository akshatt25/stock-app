package com.example.infobyte;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    private EditText nameBox, mailBox, pwBox, confirmpwBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameBox = findViewById(R.id.nameBox);
        mailBox = findViewById(R.id.mailBox);
        pwBox = findViewById(R.id.pwBox);
        confirmpwBox = findViewById(R.id.confirmpwBox);

        TextView registerButton = findViewById(R.id.login2);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameBox.getText().toString();
                String email = mailBox.getText().toString();
                String password = pwBox.getText().toString();
                String confirmPassword = confirmpwBox.getText().toString();

                if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    // Display a message if any field is empty
                    Toast.makeText(RegisterActivity.this, "Please fill in all details", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)) {
                    // Display a message if passwords don't match
                    Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                } else {
                    // All input is valid, start the OtpActivity and pass the data
                    Intent intent = new Intent(RegisterActivity.this, OtpActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("email", email);
                    intent.putExtra("password", password);
                    startActivity(intent);
                }
            }
        });
    }
}
