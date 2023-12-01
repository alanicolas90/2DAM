package service;

import dao.ExampleSpringDao;
import dao.ExampleXmlDao;
import dao.ExamplesJDBCDao;
import jakarta.inject.Inject;

public class XUseCase {

    private final ExampleXmlDao exampleXmlDao;
    private final ExamplesJDBCDao examplesJDBCDao;
    private final ExampleSpringDao exampleSpringDao;

    @Inject
    public XUseCase(ExampleXmlDao exampleXmlDao, ExamplesJDBCDao examplesJDBCDao, ExampleSpringDao exampleSpringDao) {
        this.exampleXmlDao = exampleXmlDao;
        this.examplesJDBCDao = examplesJDBCDao;
        this.exampleSpringDao = exampleSpringDao;
    }




}
