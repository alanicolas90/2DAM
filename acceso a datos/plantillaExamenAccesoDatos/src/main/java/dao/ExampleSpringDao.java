package dao;

import dao.rowmappers.XRowMapper;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.X;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Log4j2
public class ExampleSpringDao {

    private final DBConnection dbConnection;

    @Inject
    public ExampleSpringDao(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }


    public Either<String, List<X>> getAll() {
        try{
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnection.getDataSource());
            List<X> xs = jdbcTemplate.query("SELECT * FROM X", new XRowMapper());
            if(xs.isEmpty()){
                return Either.left("No Xs found");
            }else{
                return Either.right(xs);
            }
        }catch(Exception e){
            log.error(e.getMessage());
            return Either.left("Error in get All Xs");
        }
    }
}
