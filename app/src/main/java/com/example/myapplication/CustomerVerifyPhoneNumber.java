package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.TaskExecutor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class CustomerVerifyPhoneNumber extends AppCompatActivity {
    String verficationCodeBySystem;
    Button verify_btn;
    EditText phoneNoEnteredbyTheUser;
    ProgressBar progressBar;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_verify_phone_number);
        verify_btn=findViewById(R.id.buttonphone);
        phoneNoEnteredbyTheUser=findViewById(R.id.verfication_code);
        progressBar=findViewById(R.id.progress_circular_bar);

        String phoneno=getIntent().getStringExtra("phoneNo");
        mAuth = FirebaseAuth.getInstance();



        sendVerficationCodeToUser(phoneno);

    }

    private void sendVerficationCodeToUser(String phoneno) {

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+919763254022")       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)// OnVerificationStateChangedCallbacks

                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verficationCodeBySystem=s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                String code=phoneAuthCredential.getSmsCode();
                if(code!=null){
                    progressBar.setVisibility(View.VISIBLE);
                    verifyCode(code);
                }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(CustomerVerifyPhoneNumber.this,e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    };

    private void verifyCode(String CodeByUser){
        PhoneAuthCredential credential= PhoneAuthProvider.getCredential(verficationCodeBySystem,CodeByUser);
        signInTheUserByCredentials(credential);
    }

    private void signInTheUserByCredentials(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(CustomerVerifyPhoneNumber.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(CustomerVerifyPhoneNumber.this,"LOGGED IN THANK You",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(CustomerVerifyPhoneNumber.this,AfterLoginActivity.class);
                    startActivity(intent);

                }
                else {
                    Toast.makeText(CustomerVerifyPhoneNumber.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}