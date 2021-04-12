package com.example.googlemapsdonor.firebasehandler;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.googlemapsdonor.models.DataStatus;
import com.example.googlemapsdonor.models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FBUserAuthHandler {
    private FirebaseAuth  firebaeAuth;
    private String errorMessage;

    public FBUserAuthHandler(){
        firebaeAuth = FirebaseAuth.getInstance();
    }

    public void isSignedIn(){
        FirebaseUser currentUser = firebaeAuth.getCurrentUser();
        if(currentUser!=null){
            //redirect to main page based on the user role

        }
        else{
            //redirect to register page
        }
    }

    public void createNewUser(String username,String password, final DataStatus dataStatus){
        firebaeAuth.createUserWithEmailAndPassword(username,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //account creation successfully
                            //String successMessage = "success account creation";
                            Log.d("USER AUTH","Inside user auth created account!");
                            FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful())
                                            {

                                                       String currentUserId = firebaeAuth.getCurrentUser().getUid();
                                                    String message = "Email verfication link send.Please verify.";
                                                Log.d("User AUTH",""+message);
                                                    dataStatus.dataLoaded(message);


                                            }
                                            else{

                                                errorMessage = "Error Ocurred! Please check your credentials";
                                                dataStatus.errorOccured(task.getException().getMessage());
                                            }
                                        }
                                    });

                            String currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            Log.d("USER AUTH","Inside user auth created account!"+ currentUserId);
                            //dataStatus.dataCreated(successMessage);
                            dataStatus.dataCreated(currentUserId);
                        }
                        else {
                            //if sign in fails
                            String message = "onFailure: Some error creating account";
                            Log.d("Auth Handler", message);
                            dataStatus.errorOccured(message);
                        }
                    }
                });
    }

    public void signInUser(String userName,String password, final DataStatus dataStatus){
        firebaeAuth.signInWithEmailAndPassword(userName,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //creds match
                            if(firebaeAuth.getCurrentUser().isEmailVerified()) {
                                String currentUserId = firebaeAuth.getCurrentUser().getUid();
                                dataStatus.dataLoaded(currentUserId);
                            }
                            else{
                                errorMessage = "Error Ocurred! Please verify your email first";
                                dataStatus.errorOccured(errorMessage);
                            }


                        }
                        else{
                            errorMessage = " Error Occurred! Please check your credentials ";
                            dataStatus.errorOccured(task.getException().getMessage());
                        }
                    }
                });
    }
    public void signOutUser(){
        Log.d("logged out"+firebaeAuth.getCurrentUser(),"logged out user");
        firebaeAuth.signOut();

    }
}