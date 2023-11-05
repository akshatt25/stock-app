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
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OtpActivity extends AppCompatActivity {

    private EditText mobNoBox, otpBox;
    private TextView sendOtpButton;
    private boolean isVerificationInProgress = false;

    private FirebaseAuth firebaseAuth;
    private String verificationId;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        mobNoBox = findViewById(R.id.mobNoBox);
        otpBox = findViewById(R.id.otpBox);
        sendOtpButton = findViewById(R.id.login2);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users"); // Initialize Firebase Database reference

        sendOtpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isVerificationInProgress) {
                    // Step 1: Send OTP
                    String phoneNumber = mobNoBox.getText().toString();
                    sendOtp(phoneNumber);
                } else {
                    // Step 3: Verify OTP
                    String otp = otpBox.getText().toString();
                    verifyOtp(otp);
                }
            }
        });
    }

    private void sendOtp(String phoneNumber) {
        phoneNumber = "+91" + phoneNumber;

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                60,
                java.util.concurrent.TimeUnit.SECONDS,
                this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        // Auto-retrieval or instant verification
                        signInWithPhoneAuthCredential(phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        // Handle the error
                    }

                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        // Step 2: Code sent successfully
                        verificationId = s;
                        isVerificationInProgress = true;
                        otpBox.setVisibility(View.VISIBLE);
                        sendOtpButton.setText("Verify");
                    }
                }
        );
    }

    private void verifyOtp(String otp) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, otp);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Otp verification successful
                        // You can navigate to the next screen or perform the required action here

                        // Retrieve user registration data
                        String name = getIntent().getStringExtra("name");
                        String email = getIntent().getStringExtra("email");
                        String password = getIntent().getStringExtra("password");

                        String phone = mobNoBox.getText().toString();
                        String emailPhone = phone + "@gmail.com"; // You can customize the email domain

                        // Sign in using email and password
                        firebaseAuth.createUserWithEmailAndPassword(emailPhone, password)
                                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Sign-in successful
                                            Toast.makeText(OtpActivity.this, "User Signed In", Toast.LENGTH_SHORT).show();
                                            User user = new User(name, email, password);

                                            // Store user data in Firebase Realtime Database
                                            String userId = firebaseAuth.getCurrentUser().getUid();
                                            databaseReference.child(userId).setValue(user);
                                            Intent intent = new Intent(OtpActivity.this, LoginActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(intent);

                                        } else {
                                            // Sign-in failed
                                            Toast.makeText(OtpActivity.this, "Sign-in failed, try again", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                } );





                    } else {
                        // Otp verification failed
                        Toast.makeText(this, "Failed, Try Again", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
