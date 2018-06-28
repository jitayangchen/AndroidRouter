package cn.com.findfine.module_1;

import android.content.Context;

import cn.com.findfine.library.iservice.ITestService;
import cn.com.findfine.simple_annotation.annotation.ServiceRoute;

@ServiceRoute(path = "TestService", type = "service")
public class TestService implements ITestService {


    @Override
    public void init(Context context) {

    }

    @Override
    public String getData() {
        return "This is Module1 Str.";
    }
}
