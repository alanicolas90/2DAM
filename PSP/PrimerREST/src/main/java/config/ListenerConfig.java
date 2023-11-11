package config;

import dao.DBConnection;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener()
public class ListenerConfig implements ServletContextListener {
    Configuration config;

    DBConnection pool;

    @Inject
    public ListenerConfig(Configuration config,DBConnection pool) {
        this.config = config;
        this.pool = pool;

    }

    // Public constructor is required by servlet spec
    public ListenerConfig() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    @Override
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed).
         You can initialize servlet context related data here.
      */
        config.load(sce.getServletContext().getResourceAsStream(ConstantesConfig.RUTA_YAML));
        pool.loadPool();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context
         (the Web application) is undeployed or
         Application Server shuts down.
      */
       pool.closePool();
    }

}
