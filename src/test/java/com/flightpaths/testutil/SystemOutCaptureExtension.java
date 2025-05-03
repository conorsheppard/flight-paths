package com.flightpaths.testutil;

import org.junit.jupiter.api.extension.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class SystemOutCaptureExtension implements BeforeEachCallback, AfterEachCallback, ParameterResolver {
    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;

    @Override
    public void beforeEach(ExtensionContext context) {
        originalOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Override
    public void afterEach(ExtensionContext context) {
        System.setOut(originalOut);
    }

    public String getOutput() {
        return outContent.toString().trim();
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return parameterContext.getParameter().getType() == SystemOutCaptureExtension.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return this;
    }
}
