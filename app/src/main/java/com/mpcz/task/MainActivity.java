package com.mpcz.task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Data> data = new ArrayList<>();
    TextInputEditText reponame,repo_link,repo_description;
    Button add_repo;
    RecyclerView repolist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repo_link = findViewById(R.id.edt_repo_link);
        reponame = findViewById(R.id.edt_reposetry_name);
        repo_description = findViewById(R.id.repo_decription);
        add_repo = findViewById(R.id.add_repo);
        repolist= findViewById(R.id.repolist);
        loaddata();

        add_repo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.add(new Data(reponame.getText().toString(),repo_link.getText().toString(),repo_description.getText().toString()));
                saveData();
                loadview();
            }
        });
    }
    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(data);
        editor.putString("courses", json);
        editor.apply();

    }
    private void loaddata() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("courses", null);
        Type type = new TypeToken<ArrayList<Data>>() {}.getType();

        data = gson.fromJson(json, type);
        if (data == null) {
            data = new ArrayList<>();
        }else{
            loadview();
        }
    }
    private void loadview(){
        if(0<data.size()){
            Adapter adapter = new Adapter(MainActivity.this,data);
            repolist.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }else{
            Toast.makeText(MainActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
        }
    }
}