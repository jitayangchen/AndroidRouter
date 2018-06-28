package cn.com.findfine.simple_processors.processor;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

import cn.com.findfine.simple_annotation.annotation.ServiceRoute;
import cn.com.findfine.simple_annotation.model.RouteMeta;
import cn.com.findfine.simple_processors.utils.Logger;

@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_7)
@SupportedOptions("moduleName")
public class ServiceRouteProcessor extends AbstractProcessor {

    private final static String PACKAGE_NAME = "cn.com.findfine.androidrouter";
    private final static String IACTIVITYROUTER = "cn.com.findfine.library.operation.IActivityRouter";

    private Filer mFiler;
    private Logger logger;
    private String moduleName;
    private Elements elements;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        logger = new Logger(processingEnv.getMessager());
        mFiler = processingEnv.getFiler();

        elements = processingEnv.getElementUtils();

        Map<String, String> options = processingEnvironment.getOptions();
        moduleName = options.get("moduleName");
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotataions = new LinkedHashSet<>();
        annotataions.add(ServiceRoute.class.getCanonicalName());
        return annotataions;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        if (set == null || set.isEmpty())
            return false;

        ClassName routeMetaCn = ClassName.get(RouteMeta.class);

        ParameterizedTypeName inputMapTypeOfGroup = ParameterizedTypeName.get(
                ClassName.get(Map.class),
                ClassName.get(String.class),
                ClassName.get(RouteMeta.class)
        );

        ParameterSpec groupParamSpec = ParameterSpec.builder(inputMapTypeOfGroup, "atlas").build();

        MethodSpec.Builder mainBuilder = MethodSpec.methodBuilder("loadInto")
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(groupParamSpec);

        for (Element element : roundEnvironment.getElementsAnnotatedWith(ServiceRoute.class)) {
            if (element.getKind() == ElementKind.CLASS) {
                TypeElement typeElement = (TypeElement) element;
                logger.i(typeElement.getSimpleName());
                String path = typeElement.getAnnotation(ServiceRoute.class).path();
                logger.i(moduleName + " : " + path);

                mainBuilder.addStatement(
                        "atlas.put($S, $T.build($S, $T.class))",
                        path, routeMetaCn, path, ClassName.get(typeElement));
            }
        }

        TypeSpec helloWorld = TypeSpec.classBuilder("ServiceRouterLoad_" + moduleName)
                .addJavadoc("Auto make class file.")
                .addSuperinterface(ClassName.get(elements.getTypeElement(IACTIVITYROUTER)))
                .addModifiers(Modifier.PUBLIC)
                .addMethod(mainBuilder.build())
                .build();

        JavaFile javaFile = JavaFile.builder(PACKAGE_NAME, helloWorld)
                .build();

        try {
            javaFile.writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

}
