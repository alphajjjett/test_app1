package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    EditText edittxt1, edittxt2, edittxt3, edittxt4, edittxt5, edittxt6, edittxt7, edittxt8, edittxt9;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main2), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        DB = new DBHelper(this);

        Button button = findViewById(R.id.back1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });

        edittxt1 = findViewById(R.id.edittxt1);
        edittxt2 = findViewById(R.id.edittxt2);
        edittxt3 = findViewById(R.id.edittxt3);
        edittxt4 = findViewById(R.id.edittxt4);
        edittxt5 = findViewById(R.id.edittxt5);
        edittxt6 = findViewById(R.id.edittxt6);
        edittxt7 = findViewById(R.id.edittxt7);
        edittxt8 = findViewById(R.id.edittxt8);
        edittxt9 = findViewById(R.id.edittxt9);
        Button next1 = findViewById(R.id.next1);

        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String locationTXT = edittxt1.getText().toString();
                String dateTXT = edittxt2.getText().toString();
                String fromloTXT = edittxt3.getText().toString();
                String nameTXT = edittxt4.getText().toString();
                String addressTXT = edittxt5.getText().toString();
                String numberTXT = edittxt6.getText().toString();
                String informationTXT = edittxt7.getText().toString();
                String moreinfoTXT = edittxt8.getText().toString();
                String requestTXT = edittxt9.getText().toString();

                if (locationTXT.isEmpty() || dateTXT.isEmpty() || fromloTXT.isEmpty() ||
                        nameTXT.isEmpty() || addressTXT.isEmpty() || numberTXT.isEmpty() ||
                        informationTXT.isEmpty() || moreinfoTXT.isEmpty() || requestTXT.isEmpty()) {
                    Toast.makeText(MainActivity2.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                Boolean checkinsertData = DB.insertuserdata(locationTXT, dateTXT, fromloTXT, nameTXT, addressTXT,
                        numberTXT, informationTXT, moreinfoTXT, requestTXT);
                if (checkinsertData) {
                    Toast.makeText(MainActivity2.this, "ส่งข้อมูลเรียบร้อย", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity2.this, "ข้อมูลไม่สมบูรณ์", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
