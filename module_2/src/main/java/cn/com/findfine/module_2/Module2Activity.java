package cn.com.findfine.module_2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.com.findfine.simple_annotation.annotation.SimpleRoute;

@SimpleRoute(path = "Module2Activity", type = "activity")
public class Module2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module2);
    }
}
