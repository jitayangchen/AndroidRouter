package cn.com.findfine.androidrouter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import cn.com.findfine.library.RouterEngine;
import cn.com.findfine.library.iservice.ITestService;
import cn.com.findfine.simple_annotation.annotation.SimpleRoute;

@SimpleRoute(path = "MainActivity", type = "activity")
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View btnStartModule1 = findViewById(R.id.btn_start_module_1);
        View btnStartModule2 = findViewById(R.id.btn_start_module_2);
        View btnGetData = findViewById(R.id.btn_get_data);

        btnStartModule1.setOnClickListener(this);
        btnStartModule2.setOnClickListener(this);
        btnGetData.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_module_1:
                RouterEngine.startActivity(this, "Module1Activity");
                break;
            case R.id.btn_start_module_2:
                RouterEngine.startActivity(this, "Module2Activity");
                break;
            case R.id.btn_get_data:
                ITestService iTestService = (ITestService) RouterEngine.getData("TestService");
                if (iTestService != null) {
                    Toast.makeText(this, iTestService.getData(), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
