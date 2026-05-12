package com.example.assignment6;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView ListView;
    FloatingActionButton fab;
    ArrayList<String> arrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            ListView=findViewById(R.id.ListView);
            fab=findViewById(R.id.fab);
            ArrayAdapter<String> adapter=new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,arrayList);
            ListView.setAdapter(adapter);
            ListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                    AlertDialog.Builder alterDialog=new AlertDialog.Builder(MainActivity.this);
                    alterDialog.setTitle("Delete")
                            .setMessage("Do you want to delete?")
                            .setNegativeButton("Cancel",null)
                            .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    arrayList.remove(position);
                                    adapter.notifyDataSetChanged();
                                }
                            });
                    alterDialog.show();
                    return true;
                }
            });
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alterDialog=new AlertDialog.Builder(MainActivity.this);
                    EditText input=new EditText(MainActivity.this);
                    alterDialog.setView(input);
                    alterDialog.setTitle("Add Task")
                    .setMessage("Do you want to add task")
                    .setNegativeButton("Cancel",null)
                            .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int i) {
                                    String task=input.getText().toString();
                                    if(!task.isEmpty()){
                                        arrayList.add(task);
                                        adapter.notifyDataSetChanged();
                                    }
                                }
                            });
                    alterDialog.show();
                }
            });
            return insets;
        });
    }
}