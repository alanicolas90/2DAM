package dao.impl;

import config.Configuration;
import config.common.Constants;
import dao.MenuItemsDao;
import io.vavr.control.Either;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.log4j.Log4j2;
import model.ErrorC;
import model.xml.MenuItemXml;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Log4j2
public class MenuItemsImpl implements MenuItemsDao {
    Path xmlFIle = Paths.get(Configuration.getInstance().getPropertyTxt("xmlItemsPath"));


    @Override
    public Either<ErrorC, List<String>> getAll() {
        MenuItemXml result = null;
        try {
            JAXBContext context = JAXBContext.newInstance(MenuItemXml.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            Unmarshaller unmarshaller = context.createUnmarshaller();

            result = (MenuItemXml) unmarshaller.unmarshal(xmlFIle.toFile());

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        if(result == null){
            return Either.left(new ErrorC(Constants.ERROR_READING_FILE));
        }

        return Either.right(result.getItems());
    }

}
