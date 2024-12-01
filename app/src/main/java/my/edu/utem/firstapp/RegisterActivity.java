package my.edu.utem.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import my.edu.utem.firstapp.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot()); // setContentView(R.layout.activity_register);

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

        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/

        //? Binding method using Binding class
        binding.signupBtn.setOnClickListener(this::fnRegister);
        binding.signupBtn.setText("Register now!");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");

        binding.edtFullname.setText(username);
        binding.edtDOB.setText(password);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void fnRegister(View view) {
        System.out.println("Login");

        String fullname = binding.edtFullname.getText().toString();
        String email = binding.edtEmail.getText().toString();

        Intent intent = new Intent(this, RegisterActivity.class);
        intent.putExtra("username", fullname);
        intent.putExtra("password", email);
        startActivity(intent);
    }
}