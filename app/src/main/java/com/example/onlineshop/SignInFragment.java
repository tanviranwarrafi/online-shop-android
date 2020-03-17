package com.example.onlineshop;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.onlineshop.RegisterActivity.onResetPasswordFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment {


    public SignInFragment() {
        // Required empty public constructor
    }


    private FrameLayout parentFrameLayout;
    private TextView dontHaveAnAccount;

    private EditText email, password;
    private Button signIn_btn;
    private TextView forgot_password;

    private ImageButton close_btn;

    private ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;

    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        dontHaveAnAccount = view.findViewById(R.id.tv_dont_have_an_account);
        parentFrameLayout = getActivity().findViewById(R.id.register_frameLayout);

        email = view.findViewById(R.id.sign_in_email);
        password = view.findViewById(R.id.sign_in_password);
        signIn_btn = view.findViewById(R.id.sign_in_btn);
        forgot_password = view.findViewById(R.id.sign_in_forgot_password);

        close_btn = view.findViewById(R.id.sign_in_close_btn);
        progressBar = view.findViewById(R.id.sign_in_progressBar);

        firebaseAuth = FirebaseAuth.getInstance();
        return view;
        }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dontHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SignUpFragment());
            }
        });

        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onResetPasswordFragment = true;
                setFragment(new ResetPasswordFragment());
            }
        });

        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(getActivity(), MainActivity.class);
                startActivity(mainIntent);
                getActivity().finish();
            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInput();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInput();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        signIn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEmailAndPassword();
            }
        });
    }



    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_right, R.anim.slideout_from_left);
        fragmentTransaction.replace(parentFrameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }

    private void checkInput() {
        if (!TextUtils.isEmpty(email.getText())){
            if(!TextUtils.isEmpty(password.getText())){
                signIn_btn.setEnabled(true);
                signIn_btn.setTextColor(Color.rgb(255,255,255));
            }else{
                signIn_btn.setEnabled(false);
                signIn_btn.setTextColor(Color.argb(50,255,255,255));
            }
        }else{
            signIn_btn.setEnabled(false);
            signIn_btn.setTextColor(Color.argb(50,255,255,255));
        }
    }

    private void checkEmailAndPassword() {
        if (email.getText().toString().matches(emailPattern)){
            if (password.length()>=8){

                progressBar.setVisibility(View.VISIBLE);

                signIn_btn.setEnabled(false);
                signIn_btn.setTextColor(Color.argb(50,255,255,255));

                firebaseAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){

                                    Intent mainIntent = new Intent(getActivity(), MainActivity.class);
                                    startActivity(mainIntent);
                                    getActivity().finish();
                                }else{
                                    progressBar.setVisibility(View.INVISIBLE);
                                    signIn_btn.setEnabled(true);
                                    signIn_btn.setTextColor(Color.rgb(255,255,255));
                                    String error = task.getException().getMessage();
                                    Toast.makeText(getActivity(), "Incorrect email or password!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }else{
                Toast.makeText(getActivity(), "Incorrect email or password!", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getActivity(), "Incorrect email or password!", Toast.LENGTH_SHORT).show();
        }
    }
}



