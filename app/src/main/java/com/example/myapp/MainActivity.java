package com.example.myapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize the DBHelper
        DB = new DBHelper(this);

        // Handle edge-to-edge insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set up button1 click listener
        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        });

        // Set up button2 click listener
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Comment.class);
            startActivity(intent);
        });

        // Set up button4 click listener
        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(v -> {
            Cursor res = DB.getdata();
            if (res.getCount() == 0) {
                Toast.makeText(MainActivity.this, "ไม่มีข้อมูล", Toast.LENGTH_SHORT).show();
                return;
            }

            // Use StringBuilder instead of StringBuffer for better performance
            StringBuilder buffer = new StringBuilder();
            while (res.moveToNext()) {
                buffer.append("เขียนที่ :").append(res.getString(0)).append("\n")
                        .append("วันที่ :").append(res.getString(1)).append("\n")
                        .append("เรียน :").append(res.getString(2)).append("\n")
                        .append("ชื่อ :").append(res.getString(3)).append("\n")
                        .append("ที่อยู่ :").append(res.getString(4)).append("\n")
                        .append("เบอร์โทรศัพท์ :").append(res.getString(5)).append("\n")
                        .append("คำร้อง :").append(res.getString(6)).append("\n")
                        .append("เพิ่มเติม :").append(res.getString(7)).append("\n")
                        .append("การดำเนินการ :").append(res.getString(8)).append("\n\n");
            }

            // Close the cursor to avoid memory leaks
            res.close();

            // Display the data in an AlertDialog
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("ข้อมูล")
                    .setMessage(buffer.toString())
                    .setPositiveButton("OK", null)
                    .show();
        });
    }
}
