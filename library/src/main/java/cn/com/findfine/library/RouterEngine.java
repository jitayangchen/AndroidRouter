package cn.com.findfine.library;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.findfine.library.exception.PathNotFoundException;
import cn.com.findfine.library.operation.IActivityRouter;
import cn.com.findfine.simple_annotation.model.RouteMeta;

public class RouterEngine {

    private final static List<String> classNameArray = new ArrayList<>();
    private final static Map<String, RouteMeta> routeMetaMap = new HashMap<>();

    public static void init() {

        if (!classNameArray.isEmpty()) {
            classNameArray.clear();
        }

        classNameArray.add("cn.com.findfine.androidrouter.RouterLoad_example");
        classNameArray.add("cn.com.findfine.androidrouter.RouterLoad_module_1");
        classNameArray.add("cn.com.findfine.androidrouter.RouterLoad_module_2");


        for (String className : classNameArray) {
            try {
                IActivityRouter activityRouter = (IActivityRouter) Class.forName(className).getConstructor().newInstance();
                activityRouter.loadInto(routeMetaMap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void startActivity(Context context, String path) {
        RouteMeta routeMeta = routeMetaMap.get(path);
        if (routeMeta == null) {
            throw new PathNotFoundException("Path not found!");
        }

        Intent intent = new Intent(context, routeMeta.getaClass());
        context.startActivity(intent);
    }
}
