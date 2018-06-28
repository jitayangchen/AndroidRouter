package cn.com.findfine.simple_annotation.model;

public class RouteMeta {
    private String path;
    private Class<?> aClass;

    public RouteMeta() {
    }

    public RouteMeta(String path, Class<?> aClass) {
        this.path = path;
        this.aClass = aClass;
    }

    public static RouteMeta build(String path, Class<?> aClass) {
        return new RouteMeta(path, aClass);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Class<?> getaClass() {
        return aClass;
    }

    public void setaClass(Class<?> aClass) {
        this.aClass = aClass;
    }
}
