package project.activity;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.permissions.R;
import java.io.File;
import project.helper.RequestHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestForWriteSDCardPermission();
        requestForReceiveSMSPermission();
    }

    private void requestForWriteSDCardPermission() {
        RequestHelper requestHelper = new RequestHelper(this);
        requestHelper.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, new RequestHelper.OnGrantedListener() {
            @Override
            public void onGranted() {
                Toast.makeText(MainActivity.this, "Granted for write external storage", Toast.LENGTH_SHORT).show();
            }
        }, new RequestHelper.OnDeniedListener() {
            @Override
            public void onDenied() {
                Toast.makeText(MainActivity.this, "Denied for write external storage", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void requestForReceiveSMSPermission() {
        RequestHelper requestHelper = new RequestHelper(this);
        requestHelper.request(Manifest.permission.RECEIVE_SMS, new RequestHelper.OnGrantedListener() {
            @Override
            public void onGranted() {
                Toast.makeText(MainActivity.this, "Granted for receive sms", Toast.LENGTH_SHORT).show();
            }
        }, new RequestHelper.OnDeniedListener() {
            @Override
            public void onDenied() {
                Toast.makeText(MainActivity.this, "Denied for receive sms", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        RequestHelper.onRequestPermissionResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void createAppDir() {
        String dirName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/uncox";
        File file = new File(dirName);
        boolean wasCreated = file.mkdirs();
        if (wasCreated) {
            Log.i("LOG", "Yes");
        } else {
            Log.i("LOG", "No");
        }
    }
}
