package dao;

import config.Configuration;
import io.vavr.control.Either;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import model.xml.XXml;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ExampleXmlDao {

    Path file = Paths.get(Configuration.getInstance().getProperty("txtOrdersFile"));

    public Either<String, XXml> getAll() {
        XXml xXml;
        try{
            JAXBContext context = JAXBContext.newInstance(XXml.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            Unmarshaller unmarshaller = context.createUnmarshaller();

            //read the file
            xXml = (XXml) unmarshaller.unmarshal(file.toFile());

        }catch (Exception e){
            e.printStackTrace();
            return Either.left("Error reading file");
        }
        return Either.right(xXml);
    }

    public Either<String, Integer> loadDataToDBFromXML(XXml xXml){
        //read the xml file and parse it to the database from before
//        try {
//            // Establish a connection to your database
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "username", "password");
//
//            // Create a PreparedStatement
//            String sql = "INSERT INTO your_table (column1, column2, column3) VALUES (?, ?, ?)";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//
//            // Set the parameters of the PreparedStatement with the data from your parsed XML
//            preparedStatement.setString(1, xXml.getField1());
//            preparedStatement.setString(2, xXml.getField2());
//            preparedStatement.setString(3, xXml.getField3());
//
//            // Execute the PreparedStatement to insert the data into the database
//            int rowsAffected = preparedStatement.executeUpdate();
//
//            return Either.right(rowsAffected);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return Either.left("Error uploading data to the database");
//        }
        return Either.right(1);
    }

}
