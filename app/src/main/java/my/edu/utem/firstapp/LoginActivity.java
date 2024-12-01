package my.edu.utem.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

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

import my.edu.utem.firstapp.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;

    Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityLoginBinding.inflate(getLayoutInflater()); // setContentView(R.layout.activity_login);
        setContentView(binding.getRoot()); // setContentView(R.layout.activity_login);

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

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        //? Binding method without Binding class (Refer RegisterActivity)
        // loginBtn = (Button) findViewById(R.id.login_btn);
        // loginBtn.setOnClickListener(this::fnLogin);

        //? Using binding class
        binding.loginBtn.setOnClickListener(this::fnLogin);
    }

    @Override
    protected void onStart() {
        super.onStart();
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

    public void fnLogin(View view) {
        System.out.println("Login");

        String username = binding.edtUsername.getText().toString();
        String password = binding.edtPassword.getText().toString();

        Intent intent = new Intent(this, RegisterActivity.class);
        intent.putExtra("username", username);
        intent.putExtra("password", password);
        startActivity(intent);
    }
}