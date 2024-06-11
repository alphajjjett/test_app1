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

public class Comment extends AppCompatActivity {

    DBHelper DB;

    EditText edittxt1, edittxt4, edittxt5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_comment);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main3), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button button1 = findViewById(R.id.back3);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Comment.this,MainActivity.class);
                startActivity(intent);
            }
        });

        DB = new DBHelper(this);
        Button button2 = findViewById(R.id.back3);

        edittxt1 = findViewById(R.id.edittxt1);
        edittxt4 = findViewById(R.id.edittxt4);
        edittxt5 = findViewById(R.id.edittxt5);


        Button next2 = findViewById(R.id.next2);

        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = edittxt1.getText().toString();
                String informationTXT = edittxt4.getText().toString();
                String commentTXT = edittxt5.getText().toString();


                if (nameTXT.isEmpty() || informationTXT.isEmpty() || commentTXT.isEmpty()) {
                    Toast.makeText(Comment.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                Boolean checkinsertData = DB.insertuserdata(nameTXT, informationTXT, commentTXT,null,null,

                        null,null,null,null);
                if (checkinsertData) {
                    Toast.makeText(Comment.this, "ส่งข้อมูลเรียบร้อย", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Comment.this, "ข้อมูลไม่สมบูรณ์", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}