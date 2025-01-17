package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    EditText cityInput;
    Button addCityButton, deleteCityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cityList = findViewById(R.id.city_list);
        cityInput = findViewById(R.id.city_input);
        addCityButton = findViewById(R.id.add_city_button);
        deleteCityButton = findViewById(R.id.delete_city_button);

        String[] cities = {"Edmonton", "Paris", "London", "Beijing"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        addCityButton.setOnClickListener(v -> {
            String city = cityInput.getText().toString().trim();
            if (!city.isEmpty() && !dataList.contains(city)) {
                dataList.add(city);
                cityAdapter.notifyDataSetChanged();
                cityInput.setText("");
                Toast.makeText(this, city + " added!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Invalid or duplicate city!", Toast.LENGTH_SHORT).show();
            }
        });

        deleteCityButton.setOnClickListener(v -> {
            String city = cityInput.getText().toString().trim();
            if (dataList.remove(city)) {
                cityAdapter.notifyDataSetChanged();
                cityInput.setText("");
                Toast.makeText(this, city + " deleted!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "City not found!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
