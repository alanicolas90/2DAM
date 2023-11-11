package dao.utils;

public class DaoConstants {

    public static final String DATABASE_ERROR = "Database error";
    public static final String CLOSING_CONNECTION_POOL = "Closing connection pool";
    public static final int MAX_POOL_SIZE = 5;
    public static final int IDLE_TIMEOUT_MS = 3000;
    public static final String CACHE_PREP_STMTS = "cachePrepStmts";
    public static final String PREP_STMT_CACHE_SIZE = "prepStmtCacheSize";
    public static final String PREP_STMT_CACHE_SQL_LIMIT = "prepStmtCacheSqlLimit";
    public static final int CACHE_SIZE = 250;
    public static final int CACHE_SQLLIMIT = 2048;


    private DaoConstants() {
    }


}
