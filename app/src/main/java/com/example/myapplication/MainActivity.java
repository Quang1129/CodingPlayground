package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


//    private TextView textViewCount;
//    private FloatingActionButton addingButton;
//    private FloatingActionButton removingButton;

    private MyViewModel model;
    private ArrayList<Integer> arrayList;
    private ArrayAdapter<Integer> arrayAdapter;
    private  ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Instead of rendering from activity_main.xml
//        setContentView(R.layout.activity_main);
        // Users can do this

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        model = new ViewModelProvider(this).get(MyViewModel.class);

//        not necessary anymore
//        textViewCount = findViewById(R.id.text_view_count);
//        addingButton = findViewById(R.id.adding_button);
//        removingButton = findViewById(R.id.removing_button);
//        listViewCount = findViewById(R.id.list_view_count);

        arrayList = new ArrayList<Integer>();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);

        binding.listViewCount.setAdapter(arrayAdapter);


        model.getNumber().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.textViewCount.setText("" + integer);
                arrayList.add(integer);
                arrayAdapter.notifyDataSetChanged();
            }
        });

        binding.addingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.increaseNumber();
            }
        });


        binding.removingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.decreaseNumber();
            }
        });

        binding.listViewCount.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            // When HOLDING LONG CLICK, the item gets removed!
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                arrayList.remove(i);
                arrayAdapter.notifyDataSetChanged();

                return false;
            }
        });

        binding.listViewCount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            // GO TO DetailsActivity Screen!
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("number",  arrayList.get(i).toString());
                startActivity(intent);
            }
        });

    }
}