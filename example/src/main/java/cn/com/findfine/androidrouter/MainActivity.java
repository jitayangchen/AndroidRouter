package cn.com.findfine.androidrouter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.com.findfine.library.RouterEngine;
import cn.com.findfine.simple_annotation.annotation.SimpleRoute;

@SimpleRoute(path = "MainActivity")
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View btnStartModule1 = findViewById(R.id.btn_start_module_1);
        View btnStartModule2 = findViewById(R.id.btn_start_module_2);

        btnStartModule1.setOnClickListener(this);
        btnStartModule2.setOnClickListener(this);
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
        }
    }
}
