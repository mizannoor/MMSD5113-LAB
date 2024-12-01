package my.edu.utem.firstapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.navigation.NavigationView;

import java.util.Calendar;
import java.util.Vector;

import my.edu.utem.firstapp.adapter.ExpenseAdapter;
import my.edu.utem.firstapp.databinding.ActivityExpensesBinding;
import my.edu.utem.firstapp.databinding.ActivityLoginBinding;
import my.edu.utem.firstapp.model.Expense;

public class ExpensesActivity extends AppCompatActivity {

    ActivityExpensesBinding binding;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    Expense expense;
    private Vector<Expense> expenses;
    private ExpenseAdapter expensesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityExpensesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Add side menu
        drawerLayout = binding.myDrawerLayout;
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        actionBarDrawerToggle.syncState();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        drawerLayout.openDrawer(GravityCompat.START);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = binding.navMenu; //findViewById(R.id.nav_menu);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                if(item.getItemId() == R.id.nav_login){
                    intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    return true;
                }
                else if(item.getItemId() == R.id.nav_register){
                    intent = new Intent(getApplicationContext(), RegisterActivity.class);
                    startActivity(intent);
                    return true;
                }
                else if(item.getItemId() == R.id.nav_expenses_form){
                    intent = new Intent(getApplicationContext(), ExpensesActivity.class);
                    startActivity(intent);
                    return true;
                }
                else{
                    return false;
                }

            }
        });

//        setContentView(R.layout.activity_expenses);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });


        Integer[] numbers = new Integer[15];
        for(int i = 0; i<15; i++){
            numbers[i] = i + 1;
        }

        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, numbers);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spnQty.setAdapter(adapter);

        binding.btnSave.setOnClickListener(this::fnSave);
        binding.imgExp.setOnClickListener(this::fnTakePic);
        binding.edtExpDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fnInvokeDatePicker();
            }
        });

        expenses = new Vector<>();
        expensesAdapter = new ExpenseAdapter(getLayoutInflater(), expenses);
        binding.recyclerView.setAdapter(expensesAdapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    DatePickerDialog pickerDialog;

    private void fnInvokeDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        pickerDialog = new DatePickerDialog(ExpensesActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                binding.edtExpDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
            }
        }, year, month, day);

        pickerDialog.show();
    }


    private void fnTakePic(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        binding.imgExp.setImageBitmap(bitmap);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void fnSave(View view) {
        int qtyItem = (int) binding.spnQty.getSelectedItem();

        binding.txtViewTotalPrice.setText(""+ qtyItem * Float.parseFloat(binding.edtExpValue.getText().toString()));

        expense = new Expense(binding.edtExpDate.getText().toString(), binding.edtExpFor.getText().toString(), Integer.parseInt(binding.spnQty.getSelectedItem().toString()), Float.parseFloat(binding.edtExpValue.getText().toString()));

        expenses.add(expense);
        expensesAdapter.notifyDataSetChanged();
    }
}