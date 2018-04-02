package com.example.thando.qvaya.Admin;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thando.qvaya.R;

public class AdminDeleteDriver extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_delete_driver);
        FloatingActionButton floatingActionButton=(FloatingActionButton)findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                startActivity(new Intent(view.getContext(), AdminHome.class));
            }
        });
    }
    public void DeleteDriverAdminBTN(View view) {



        // get prompts.xml view
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.dialog_admin_delete_confirm, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView.findViewById(R.id.editTextDialogUserInput);

        // set dialog message
        alertDialogBuilder
                .setCancelable(true)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input and set it to result
                                // edit text

                                boolean error = false;
                                if(userInput.getText().toString().length() < 5)
                                {
                                    error = true;
                                    userInput.setError("Enter password");
                                }
                                if(!error)
                                {
                                    Toast.makeText(AdminDeleteDriver.this, "Driver is Deleted", Toast.LENGTH_SHORT).show();
                                    userInput.getText().clear();

                                } else {

                                    if(userInput.getText().toString().length() < 5||userInput.getText().toString().isEmpty())
                                    {
                                        userInput.setError("Invalid Must Have 5 digits");
                                        userInput.requestFocus();
                                        if(userInput.requestFocus()) {
                                            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                                            userInput.getText().clear();
                                            Toast.makeText(AdminDeleteDriver.this, "Invalid Password Must Have 5 digits", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
    }
    public void openDialog() {
    }
    public void AgreeDeleteAdmin(View view) {
    }
    public void canceladminbutton(View view) {
    }
}