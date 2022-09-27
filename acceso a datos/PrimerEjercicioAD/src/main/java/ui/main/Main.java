package ui.main;

import config.ConfiguracionProperties;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ConfiguracionProperties configuracionProperties = new ConfiguracionProperties();
        Path path = Paths.get(configuracionProperties.getNewspaperData());

        //Escribir en el archivo
//        try {
//            String str = "Nuevo user 2";
//            byte[] bs = str.getBytes();
//            Path writtenFilePath = Files.write(path, bs);
//
//            System.out.println("Written content in file:\n"+ new String(Files.readAllBytes(writtenFilePath)));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        try {
            byte[] bs = Files.readAllBytes(path);
            List<String> strings = Files.readAllLines(path);

            System.out.println("Read bytes: \n"+new String(bs));
            System.out.println("Read lines: \n"+strings);
        } catch (Exception e) {
            e.printStackTrace();
        }




    }
}
