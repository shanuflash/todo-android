package com.example.todo;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemLongClickListener {
    private EditText ed;
    private Button adding_items;
    private ListView item_list;
    private ArrayList<String> values=new ArrayList<String>(); //ArrayList takes the user input
    private ArrayAdapter<String> adapter; // ArrayAdapter is used to insert a value in ListView
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed = findViewById(R.id.text_edit);
        adding_items = findViewById(R.id.button);
        item_list = findViewById(R.id.listview);

        adding_items.setOnClickListener(this);
        item_list.setOnItemLongClickListener(this); // Giving the reference of the onclick function
    }

    @Override
    public void onClick(View view) {
        String add_item=ed.getText().toString();
        if(values.contains(add_item))// Checks the condition if item exists return the message displayed on Toast
        {
            Toast.makeText(getBaseContext(),"Item Already Exist", Toast.LENGTH_LONG).show();
        } // Enter the element if it does not exist
        else
        {
            values.add(add_item);
            adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,values);
            item_list.setAdapter(adapter);
            ed.setText("");
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
        final int removing_item=position;
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this); // Ask the user to get the confirmation before deleting an item from the listView
        builder.setMessage("Do you want to delete").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                values.remove(removing_item);
                adapter.notifyDataSetChanged();
                Toast.makeText(getBaseContext(), "Item Deleted", Toast.LENGTH_LONG).show();
            }
        }).setNegativeButton("Cancel", null).show();



        return true;
    }
}