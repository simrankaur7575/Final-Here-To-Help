package com.example.heretohelp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rey.material.widget.CheckBox;

import Common.Common;
import Model.User;
import io.paperdb.Paper;

public class SignIn extends AppCompatActivity {

    EditText edtPhone, edtPassword;
    Button btnSignIn;
    TextView titleText;
    CheckBox ckbRemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtPassword =(MaterialEditText) findViewById(R.id.edtPassword);
        edtPhone =(MaterialEditText) findViewById(R.id.edtPhone);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        ckbRemember = (CheckBox)  findViewById(R.id.ckbRemember);

        //Init Paper
        Paper.init(this);

        titleText = (TextView) findViewById(R.id.titleText);
        Typeface f = Typeface.createFromAsset(getAssets(),"fonts/Pacifico-Regular.ttf");
        titleText.setTypeface(f);

        // Init firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User") ;

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Save user and Password
                if(ckbRemember.isChecked())
                {
                    Paper.book().write(Common.USER_KEY,edtPhone.getText().toString());
                    Paper.book().write(Common.PWD_KEY,edtPassword.getText().toString());
                }

                final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
                mDialog.setMessage("Please Wait ...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        mDialog.dismiss();

                        // Check if user not exist in database
                        if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {

                            //Get user information

                            User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);
                             user.setPhone(edtPhone.getText().toString()); //set Phone
                            if (user.getPassword().equals(edtPassword.getText().toString())) {
                              // Toast.makeText(SignIn.this, "Sign In Sucessfully !", Toast.LENGTH_SHORT).show();
                                Intent homeIntent = new Intent(SignIn.this,Home.class);
                                Common.currentUser = user;
                                startActivity(homeIntent);
                                finish();
                            } else {
                                Toast.makeText(SignIn.this, "Wrong Password !", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            mDialog.dismiss();
                            Toast.makeText(SignIn.this, "User does not exist !", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }

}
