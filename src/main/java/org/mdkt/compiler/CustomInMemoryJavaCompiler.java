package org.mdkt.compiler;

import javax.tools.DiagnosticListener;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * User: Samil
 * Date: 06/01/2016
 */
public class CustomInMemoryJavaCompiler {
    static JavaCompiler javac = ToolProvider.getSystemJavaCompiler();

    private final DynamicClassLoader cl = new DynamicClassLoader(ClassLoader.getSystemClassLoader());

    public CustomInMemoryJavaCompiler() {
    }

    public DynamicClassLoader getCl() {
        return cl;
    }

    public Class<?> compile(String className, String sourceCodeInText) throws Exception {
        SourceCode sourceCode = new SourceCode(className, sourceCodeInText);
        CompiledCode compiledCode = new CompiledCode(className);
        List compilationUnits = Arrays.asList(new SourceCode[]{sourceCode});
        ExtendedStandardJavaFileManager fileManager = new ExtendedStandardJavaFileManager(javac.getStandardFileManager((DiagnosticListener)null, (Locale)null, (Charset)null), compiledCode, cl);
        JavaCompiler.CompilationTask task = javac.getTask((Writer)null, fileManager, (DiagnosticListener)null, (Iterable)null, (Iterable)null, compilationUnits);
        boolean result = task.call().booleanValue();
        return cl.loadClass(className);
    }


}
