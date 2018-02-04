package com.example.nancy.higherorlower;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    public Integer randomNo;


    public void randomNo(){
        Random random=new Random();
        randomNo=random.nextInt(20)+1;
    }

    public void makeToast(String text){
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }

    public void showInvalidNoAlert(){
        new AlertDialog.Builder(this)
                .setTitle("Wrong input")
                .setMessage("Please enter a whole number between 1 to 20")
                .setPositiveButton("OK", null).show();
    }

    public void showPlayAgainAlert(){
        new AlertDialog.Builder(this)
                .setTitle("You are smart!, The correct No. is "+randomNo)
                .setMessage("Do you want to play again?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        randomNo();
                    }
                }).setNegativeButton("No", null).show();
    }

    public void compareNo(View view){

        EditText inputNo = (EditText) findViewById(R.id.numberInput);
        String inputNumber = inputNo.getText().toString();

        if(isInputNumberValidInt(inputNumber)){
            int intNumber = Integer.parseInt(inputNumber);
            compareLogic(intNumber);
        }
    }

    private void compareLogic(int number) {

        if(number>randomNo){
            makeToast("Guess lower");
        }else if(number==randomNo){
            showPlayAgainAlert();
        }else if(number<randomNo){
            makeToast("Guess higher");
        }
    }

    //get number from input field and apply basic validation
    private boolean isInputNumberValidInt(String input){
        int number;
        try {
            number=Integer.parseInt(input);
        } catch (Exception e) {
            showInvalidNoAlert();
            return false;
        }

        if (number < 1 || number > 20) {
            showInvalidNoAlert();
            return false;
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        randomNo();
    }
}
