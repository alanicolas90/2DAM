package dao;

import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.X;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class ExamplesJDBCDao {

    private final DBConnection dbConnection;

    @Inject
    public ExamplesJDBCDao(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public Either<String, List<X>> getWithPreparedStatement() {
        List<X> ejemplo;

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM X where id = ?")) {

            preparedStatement.setString(1, "el dato que sea");

            ResultSet resultSet = preparedStatement.executeQuery();
            ejemplo = readRS(resultSet);

            if (ejemplo.isEmpty()) {
                return Either.left("No hay datos");
            } else {
                return Either.right(ejemplo);
            }

        } catch (SQLException e) {
            log.error(e.getMessage());
            return Either.left("Error SQL");
        } catch (Exception e) {
            log.error(e.getMessage());
            return Either.left("Error different from SQL, check code");
        }

    }

    public Either<String,List<X>> getAllWithStatement(){
        List<X> ejemplo;
        try(Connection connection = dbConnection.getConnection();
            Statement statement = connection.createStatement();
        ) {
            statement.executeQuery("SELECT * FROM X");
            ResultSet resultSet = statement.getResultSet();
            ejemplo = readRS(resultSet);

            if (ejemplo.isEmpty()) {
                return Either.left("No hay datos");
            } else {
                return Either.right(ejemplo);
            }

        } catch (SQLException e) {
            log.error(e.getMessage());
            return Either.left("Error SQL");
        } catch (Exception e) {
            log.error(e.getMessage());
            return Either.left("Error different from SQL, check code");
        }
    }

    private List<X> readRS(ResultSet resultSet) {
        List<X> ejemplo = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                ejemplo.add(new X(id, name));
            }

        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return ejemplo;
    }


}
