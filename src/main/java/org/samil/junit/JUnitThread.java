package org.samil.junit;

import org.dojo.calculator.StringCalculatorTest;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.mdkt.compiler.CustomInMemoryJavaCompiler;
import org.samil.Katas;

import java.security.AccessControlException;

public class JUnitThread implements Runnable {

    private final Katas kata;
    private final String sutCode;
    final CustomInMemoryJavaCompiler compiler = new CustomInMemoryJavaCompiler();

    private String message;

    public String getMessage() {
        return message;
    }

    public JUnitThread(Katas className, String code) {
        this.kata = className;
        this.sutCode = code;
    }

    @Override
    public void run() {
        try {
            Thread.currentThread().setContextClassLoader(compiler.getCl());

            Class<?> sutClass = compiler.compile(this.kata.getClassName(), this.sutCode);

            Class<JUnitCore> clz = (Class<JUnitCore>) compiler.getCl().loadClass("org.junit.runner.JUnitCore");
            System.out.println("Loaded class : " + clz.getName());

            JUnitCore junit = clz.newInstance();
            Result result = junit.run(kata.getTestClass());
            boolean triedToHack = false;
            if(result.wasSuccessful()){
                message = "All tests pass!";
            }else {
                for (Failure failure : result.getFailures()) {
                    if(failure.getException() instanceof AccessControlException){
                       triedToHack = true;
                        continue;
                    }
                    System.out.println(failure.toString());
                }

                message = triedToHack ? "Don't try to hack!" : "There are some errors";
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

}