package org.mdkt.compiler;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;

/**
 * User: Samil
 * Date: 12/01/2016
 */
public class CompilationError extends Throwable {
    private DiagnosticCollector<JavaFileObject> diagnostics;

    public CompilationError(DiagnosticCollector<JavaFileObject> diagnostics) {
        this.diagnostics = diagnostics;
    }

    public DiagnosticCollector<JavaFileObject> getDiagnostics() {
        return diagnostics;
    }
}
