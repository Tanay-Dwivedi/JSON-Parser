package com.example.jsonview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    EmpAdapter empAdapter;
    List<EmpModal> emAl = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize RecyclerView
        rv = (RecyclerView) findViewById(R.id.emp_list);
        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
        rv.setItemAnimator(new DefaultItemAnimator());

        // Initialize Employee Adapter and set it to RecyclerView
        empAdapter = new EmpAdapter(MainActivity.this, emAl);
        rv.setAdapter(empAdapter);

        // Load and parse JSON data locally
        getJSONLocally();
    }

    // Function to load JSON data from a local file
    public String loadJSONLocally() {
        String json = null;
        try {
            InputStream is = MainActivity.this.getAssets().open("employee.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }

    // Function to parse JSON data
    private void getJSONLocally() {

        try {
            // Load JSON data as a JSONObject
            JSONObject jsonObject = new JSONObject(loadJSONLocally());
            String rc = jsonObject.getString("responseCode");
            String rm = jsonObject.getString("responseMessage");
            String rt = jsonObject.getString("responseTime");

            // Check if the response code is 100 (Success)
            if (!rc.equals("100")) {
                Toast.makeText(MainActivity.this, "No Record Found", Toast.LENGTH_SHORT).show();
            }

            // Get the array of employees
            JSONArray jsonArray = jsonObject.getJSONArray("employeesList");

            // Iterate through the employee JSON objects
            for (int i = 0; i < jsonArray.length(); i++) {

                EmpModal empModal = new EmpModal();
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                // Extract employee data
                String empName = jsonObject1.getString("name");
                String empAge = jsonObject1.getString("age");
                String empCity = jsonObject1.getString("city");

                // Set employee data in EmpModal
                empModal.setName("" + empName);
                empModal.setAge("" + empAge);
                empModal.setCity("" + empCity);

                // Add EmpModal to the list
                emAl.add(empModal);
            }

            // Notify the adapter of data changes
            if (emAl != null) {
                empAdapter.DataChanged(emAl);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
