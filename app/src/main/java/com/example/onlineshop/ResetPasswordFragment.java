package com.example.onlineshop;


import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResetPasswordFragment extends Fragment {

    private EditText registered_email;
    private Button resetPassword_btn;
    private TextView goBack;

    private FrameLayout parentFrameLayout;

    private ViewGroup emailIconContainer;
    private ImageView email_icon;
    private TextView email_text;
    private ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;

    public ResetPasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reset_password, container, false);

        registered_email = view.findViewById(R.id.forgot_password_email);
        resetPassword_btn = view.findViewById(R.id.forgot_password_reset_btn);
        goBack = view.findViewById(R.id.forgot_password_goBack);

        parentFrameLayout = getActivity().findViewById(R.id.register_frameLayout);

        emailIconContainer = view.findViewById(R.id.forgot_password_email_container);
        email_icon = view.findViewById(R.id.forgot_password_email_icon);
        email_text = view.findViewById(R.id.forgot_password_email_text);
        progressBar = view.findViewById(R.id.forgot_password_progressBar);

        firebaseAuth = FirebaseAuth.getInstance();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        registered_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        resetPassword_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TransitionManager.beginDelayedTransition(emailIconContainer);
                email_text.setVisibility(View.GONE);

                TransitionManager.beginDelayedTransition(emailIconContainer);
                email_icon.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);

                resetPassword_btn.setEnabled(false);
                resetPassword_btn.setTextColor(Color.argb(50,255,255,255));

                firebaseAuth.sendPasswordResetEmail(registered_email.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    //email_icon.setImageIcon(R.drawable.email_green);
                                    email_text.setText("Recovery email sent successfully! Check your inbox.");
                                    email_text.setTextColor(getResources().getColor(R.color.SuccessGreen));
                                    TransitionManager.beginDelayedTransition(emailIconContainer);
                                    email_text.setVisibility(View.VISIBLE);

                                    Toast.makeText(getActivity(), "email sent successfully", Toast.LENGTH_LONG).show();
                                }else{
                                    String error = task.getException().getMessage();

                                    resetPassword_btn.setEnabled(true);
                                    resetPassword_btn.setTextColor(Color.rgb(255,255,255));

                                    //Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                                    email_text.setText(error);
                                    email_text.setTextColor(getResources().getColor(R.color.colorPrimary));
                                    TransitionManager.beginDelayedTransition(emailIconContainer);
                                    email_text.setVisibility(View.VISIBLE);
                                }
                                progressBar.setVisibility(View.GONE);

                            }
                        });
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SignInFragment());
            }
        });
    }

    private void checkInputs() {
        if (TextUtils.isEmpty(registered_email.getText())){
            resetPassword_btn.setEnabled(false);
            resetPassword_btn.setTextColor(Color.argb(50,255,255,255));
        }else{
            resetPassword_btn.setEnabled(true);
            resetPassword_btn.setTextColor(Color.rgb(255,255,255));
        }
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_left, R.anim.slideout_from_right);
        fragmentTransaction.replace(parentFrameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }
}
