package cn.com.findfine.simple_processors.utils;

import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;

public class Logger {

    private Messager messager;

    public Logger(Messager messager) {
        this.messager = messager;
    }

    public void i(CharSequence msg) {
        messager.printMessage(Diagnostic.Kind.NOTE, msg);
    }
}
