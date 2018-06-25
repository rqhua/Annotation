package com.rqhua.demo.javatest;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Completion;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * Created by Administrator on 2018/6/25.
 */
@SupportedSourceVersion(SourceVersion.RELEASE_7)
@SupportedAnnotationTypes("com.rqhua.demo.javatest.ReflectParam")
public class Processor extends AbstractProcessor {

    private Elements mElementUtils;
    private Types mTypeUtils;
    private Filer mFiler;
    private Messager mMessages;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        //Element代表程序的元素，例如包、类、方法。
        mElementUtils = processingEnvironment.getElementUtils();

        //处理TypeMirror的工具类，用于取类信息
        mTypeUtils = processingEnvironment.getTypeUtils();

        //Filer可以创建文件
        mFiler = processingEnvironment.getFiler();

        //错误处理工具
        mMessages = processingEnvironment.getMessager();
    }

    //指定注解处理器是注册给哪些注解的，返回指定支持的注解类型集合。
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        for (Class<? extends Annotation> annotation : getSupportedAnnotations()) {
            types.add(annotation.getCanonicalName());
        }
        return types;
    }

    //支持的注解集合
    private Set<Class<? extends Annotation>> getSupportedAnnotations() {
        Set<Class<? extends Annotation>> annotations = new LinkedHashSet<>();
        annotations.add(ReflectParam.class);
        return annotations;
    }

    //指定Java版本，一般返回最新版本即可
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        System.out.println("-------------------in");
        Set<Element> elements = (Set<Element>) roundEnvironment.getElementsAnnotatedWith(ReflectParam.class);
        for (Element element : elements) {
            System.out.println(element.getSimpleName());
//            element.
            System.out.println(element.getAnnotation(ReflectParam.class).value());
        }
        System.out.println("-------------------");
        return false;
    }


}
