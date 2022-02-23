package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    EditText fio,math,info,physics;
    Button button,transition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fio = findViewById(R.id.editTextTextPersonName);
        math = findViewById(R.id.editTextPhone);
        info = findViewById(R.id.editTextPhone2);
        physics = findViewById(R.id.editTextPhone3);
        button = findViewById(R.id.button);
        transition = findViewById(R.id.button2);

//        LinkedList list = new LinkedList();
        ArrayList list = new ArrayList();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fioStr = fio.getText().toString();
                //проверка на заполненность всех полей
                if(fioStr.isEmpty() || math.getText().toString().isEmpty() || info.getText().toString().isEmpty()
                        || physics.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Не все поля заполнены",Toast.LENGTH_SHORT).show();
                }
                else{
                    double mathDouble = Double.parseDouble(math.getText().toString());
                    double infoDouble = Double.parseDouble(info.getText().toString());
                    double physDouble = Double.parseDouble(physics.getText().toString());

                    list.add(fioStr);
                    list.add(mathDouble);
                    list.add(infoDouble);
                    list.add(physDouble);

                    fio.setText("");
                    math.setText("");
                    info.setText("");
                    physics.setText("");
                    Toast.makeText(getApplicationContext(),"Добавлено",Toast.LENGTH_SHORT).show();
                }
            }
        });

        transition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //переход на другое активити с передачей списка.
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                intent.putExtra("listAll",list);
                startActivity(intent);
            }
        });

    }


}