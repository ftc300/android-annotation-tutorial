package com.mindorks.binder;

import android.app.Activity;

import com.ftc300.lib.annotations.FTC300_RUNTIME;
import com.ftc300.lib.annotations.internal.BindingSuffix;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Binding {

    private Binding() {
        // not to be instantiated in public
    }

    private static <T extends Activity> void instantiateBinder(T target, String suffix) {
        Class<?> targetClass = target.getClass();
        String className = targetClass.getName();
        try {

            Class<?> bindingClass = targetClass
                    .getClassLoader()
                    .loadClass(className + suffix);
            Constructor<?> classConstructor = bindingClass.getConstructor(targetClass);
            try {
                classConstructor.newInstance(target);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Unable to invoke " + classConstructor, e);
            } catch (InstantiationException e) {
                throw new RuntimeException("Unable to invoke " + classConstructor, e);
            } catch (InvocationTargetException e) {
                Throwable cause = e.getCause();
                if (cause instanceof RuntimeException) {
                    throw (RuntimeException) cause;
                }
                if (cause instanceof Error) {
                    throw (Error) cause;
                }
                throw new RuntimeException("Unable to create instance.", cause);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to find Class for " + className + suffix, e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Unable to find constructor for " + className + suffix, e);
        }
    }

    private static <T extends Activity> void bindRuntimeField(T activity){
        Field fields[] = activity.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(FTC300_RUNTIME.class)) {
                FTC300_RUNTIME ftc300_runtime = field.getAnnotation(FTC300_RUNTIME.class);
                try {
                    field.setAccessible(true);
                    field.set(activity,ftc300_runtime.value());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static <T extends Activity> void bind(T activity) {
        instantiateBinder(activity, BindingSuffix.GENERATED_CLASS_SUFFIX);
        bindRuntimeField(activity);
    }



}
