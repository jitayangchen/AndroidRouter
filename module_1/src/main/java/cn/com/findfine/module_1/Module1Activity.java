package cn.com.findfine.module_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.com.findfine.library.RouterEngine;
import cn.com.findfine.simple_annotation.annotation.SimpleRoute;

@SimpleRoute(path = "Module1Activity", type = "activity")
public class Module1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module1);

        findViewById(R.id.btn_start_main_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RouterEngine.startActivity(Module1Activity.this, "MainActivity");
            }
        });
    }
}
