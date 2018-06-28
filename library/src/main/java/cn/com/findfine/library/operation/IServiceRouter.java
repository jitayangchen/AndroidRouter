package cn.com.findfine.library.operation;

import java.util.Map;

import cn.com.findfine.simple_annotation.model.RouteMeta;

public interface IServiceRouter {

    void loadInto(Map<String, RouteMeta> atlas);
}
