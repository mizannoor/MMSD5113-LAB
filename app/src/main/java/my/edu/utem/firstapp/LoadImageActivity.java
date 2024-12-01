package my.edu.utem.firstapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import my.edu.utem.firstapp.databinding.ActivityLoadImageBinding;
import my.edu.utem.firstapp.databinding.ActivityLoginBinding;

public class LoadImageActivity extends AppCompatActivity {
    Executor executor;
    Handler handler;
    Bitmap bitmap = null;
    ActivityLoadImageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityLoadImageBinding.inflate(getLayoutInflater()); // setContentView(R.layout.activity_login);
        setContentView(binding.getRoot()); // setContentView(R.layout.activity_login);

//        setContentView(R.layout.activity_load_image);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        executor = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());

        ConnectivityManager manager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        if(networkInfo != null  && networkInfo.isConnected()) {
            // the background task executor and handler is done within this checking
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        URL ImageURL = new URL("https://ftmk.utem.edu.my/web/wp-content/uploads/2020/02/cropped-Logo-FTMK.png");
                        HttpURLConnection connection = (HttpURLConnection) ImageURL.openConnection();
                        connection.setDoInput(true);
                        connection.connect();
                        InputStream inputStream = connection.getInputStream();
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inPreferredConfig = Bitmap.Config.RGB_565;
                        bitmap = BitmapFactory.decodeStream(inputStream,null,options);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    handler.post(new Runnable() {  // this is to update main thread -- UI
                        @Override
                        public void run() {
                            binding.imgVwSelfie.setImageBitmap(bitmap);
                            onShowToast();
                        }
                    });
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "No Network!! Please add data plan or connect to wifi network!", Toast.LENGTH_SHORT).show();
        }
    }

    protected void onShowToast() {
        Toast.makeText(getApplicationContext(), "Image loaded!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
//        Toast.makeText(getApplicationContext(), "Started", Toast.LENGTH_SHORT).show();
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
}