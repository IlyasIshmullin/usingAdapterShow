package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static class Student {
        String fio;
        Double mark_math;
        Double mark_infor;
        Double mark_physics;
        Double mark_average;

        public Student(String fio, Double mark_math, Double mark_infor, Double mark_physics) {
            this.fio = fio;
            this.mark_math = mark_math;
            this.mark_infor = mark_infor;
            this.mark_physics = mark_physics;

            this.mark_average = (mark_math + mark_infor + mark_physics) / 3;
        }

        public String getFio() {
            return fio;
        }

        public Double getMark_math() {
            return mark_math;
        }

        public Double getMark_infor() {
            return mark_infor;
        }

        public Double getMark_physics() {
            return mark_physics;
        }
        public Double getMark_average() {
            return mark_average;
        }
    }

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
        //transition = findViewById(R.id.button2);

//        LinkedList list = new LinkedList();
        //ArrayList list = new ArrayList();

        ListView listView = findViewById(R.id.listView);
        ArrayList<Student> students = new ArrayList<>();
        ArrayAdapter<Student> studentArrayAdapter =
                new ArrayAdapter<Student>(this, R.layout.adapter_show, students) {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public View getView(int position,
                                        View convertView,
                                        ViewGroup parent) {
                        Student currentStudent = students.get(position);

                        if(convertView == null) {
                            convertView = getLayoutInflater().inflate(R.layout.adapter_show, null, false);
                        }

                        TextView FIO = (TextView) convertView.findViewById(R.id.tv_fio);
                        TextView mark_math = (TextView) convertView.findViewById(R.id.tv_mark_math);
                        TextView mark_informatic = (TextView) convertView.findViewById(R.id.tv_mark_informatic);
                        TextView mark_physics = (TextView) convertView.findViewById(R.id.tv_mark_physics);
                        TextView mark_average = (TextView) convertView.findViewById(R.id.tv_average_mark);

                        FIO.setText(currentStudent.getFio());
                        mark_math.setText(currentStudent.getMark_math().toString());
                        mark_informatic.setText(currentStudent.getMark_infor().toString());
                        mark_physics.setText(currentStudent.getMark_physics().toString());

                        String averageFormatted = String.format("%2.1f", currentStudent.getMark_average()).replace(',', '.');;
                        mark_average.setText(averageFormatted);

                        return convertView;
                    }
                };
        listView.setAdapter(studentArrayAdapter);


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

                    students.add(new Student(fioStr, mathDouble, infoDouble, physDouble));

                    /*list.add(fioStr);
                    list.add(mathDouble);
                    list.add(infoDouble);
                    list.add(physDouble);

                     */

                    fio.setText("");
                    math.setText("");
                    info.setText("");
                    physics.setText("");
                    Toast.makeText(getApplicationContext(),"Добавлено",Toast.LENGTH_SHORT).show();

                    studentArrayAdapter.notifyDataSetChanged();
                }

            }
        });

        /*
        transition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //переход на другое активити с передачей списка.
                Toast.makeText(getApplicationContext(), "System", Toast.LENGTH_SHORT).show();
                /*Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                intent.putExtra("listAll",list);
                startActivity(intent);
            }
        });
         */



    }


}