module domain {
    exports domain.model;
    exports domain.common;
    requires lombok;
    requires org.apache.logging.log4j;

    opens domain.model to com.google.gson;
    opens domain.common to com.google.gson;

}