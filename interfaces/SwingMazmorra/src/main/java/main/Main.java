package main;

import common.Constantes;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.log4j.Log4j2;
import main.modelo.Door;
import main.modelo.Mazmorra;
import main.modelo.Room;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.io.File;
import java.util.List;

@Log4j2
public class Main {
    private static Room habitacionActual;

    public static void main(String[] args) {
        //TODO BOTONES
        JButton bttnNorte = new JButton(Constantes.NORTE);
        JButton bttnSur = new JButton(Constantes.SUR);
        JButton bttnOeste = new JButton(Constantes.OESTE);
        JButton bttnEste = new JButton(Constantes.ESTE);

        //TODO PANE QUE VA DEBAJO DE LOS BOTONES
        JLabel log = new JLabel(Constantes.COMIENZA_LA_AVENTURA);
        log.setVerticalAlignment(SwingConstants.TOP);
        JScrollPane scrollPaneLog = new JScrollPane();

        scrollPaneLog.createVerticalScrollBar();
        scrollPaneLog.setViewportView(log);

        //TODO la parte en medio entre los botones
        JLabel textoEntreLosBotones = new JLabel(Constantes.TEXTO_MEDIO);
        textoEntreLosBotones.setHorizontalAlignment(SwingConstants.CENTER);

        //TODO PANE CON LOS BOTONES
        JPanel panelBotones = new JPanel(new BorderLayout());
        panelBotones.add(bttnNorte, BorderLayout.NORTH);
        panelBotones.add(bttnSur, BorderLayout.SOUTH);
        panelBotones.add(bttnOeste, BorderLayout.WEST);
        panelBotones.add(bttnEste, BorderLayout.EAST);
        panelBotones.add(textoEntreLosBotones, BorderLayout.CENTER);

        //TODO PANTALLA PRINCIPAL
        JPanel mainPanel = new JPanel(new BorderLayout());

        //TODO esto es para inciializar la pantalla
        // el frame es la pantalla
        // el main panel es el panel principal
        JFrame frame = new JFrame(Constantes.GAME_TITLE);
        ImageIcon imageIcon = new ImageIcon("src/main/resources/th.jpg");
        frame.setIconImage(imageIcon.getImage());

        frame.add(mainPanel);
        frame.setVisible(true);
        frame.setSize(800, 600);
        frame.setResizable(false);

        //TODO esto es la barra de arriba con sus elementos
        JMenuBar menuBar = new JMenuBar();
        JMenu menuOpciones = new JMenu(Constantes.OPCIONES);
        JMenuItem menuItemCargarTree = new JMenuItem(Constantes.CARGAR_MAPA);
        JMenuItem menuItemSalir = new JMenuItem(Constantes.SALIR);
        menuOpciones.add(menuItemCargarTree);
        menuOpciones.add(menuItemSalir);

        menuBar.add(menuOpciones);


        //TODO esto es para separama la parte derecha de la pantalla en dos (los botones y el log)
        JSplitPane splitPaneVertical = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPaneVertical.setTopComponent(panelBotones);
        splitPaneVertical.setBottomComponent(scrollPaneLog);
        splitPaneVertical.setDividerLocation(200);

        //TODO esto es la parte izquierda de la pantalla (el tree)
        JTree tree = new JTree();
        tree.setRootVisible(true);
        tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode(Constantes.MAZMORRA)));

        //TODO esto es para separar la pantalla en izquierda y derecha
        JSplitPane splitPaneHorizontal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPaneHorizontal.setLeftComponent(tree);
        splitPaneHorizontal.setRightComponent(splitPaneVertical);
        splitPaneHorizontal.setDividerLocation(250);

        //TODO aqui meto toda la pantalla
        mainPanel.add(splitPaneHorizontal, BorderLayout.CENTER);
        mainPanel.add(menuBar, BorderLayout.NORTH);


        //TODO le damos acciones(funcionalidades) a los menuitems de la barra de arriba
        menuItemSalir.addActionListener(e -> System.exit(0));
        mainPanel.updateUI();

        //TODO necesito añadir un filechooser si el profe quiere que lo hagamos asi
        menuItemCargarTree.addActionListener(e -> {
            File file = fileChooserSelectedFile(frame);
            try {
                JAXBContext context = JAXBContext.newInstance(Mazmorra.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                Mazmorra mazmorra = (Mazmorra) unmarshaller.unmarshal(file);
                habitacionActual = mazmorra.getRooms().get(0);
                cargarMapaEnElTree(tree, mazmorra);

                //TODO Iniciamos la mazmorra, en la primera sala
                if (mazmorra != null) {
                    habitacionActual = mazmorra.getRooms().get(0);
                    log.setText(log.getText().replace(Constantes.BODY_HTML_FINAL_REPLACE, Constantes.BR_TE_ENCUENTRAS_EN_LA_HABITACION + habitacionActual.getId() + Constantes.BODY_HTML_FINAL_REPLACE));
                    textoEntreLosBotones.setText(habitacionActual.getDescription());

                    //TODO USABILIDAD DE LOS BOTONES
                    loadBotones(bttnNorte, Constantes.NORTE, textoEntreLosBotones, log, mazmorra, bttnNorte, bttnSur, bttnEste, bttnOeste);
                    loadBotones(bttnSur, Constantes.SUR, textoEntreLosBotones, log, mazmorra, bttnNorte, bttnSur, bttnEste, bttnOeste);
                    loadBotones(bttnOeste, Constantes.OESTE, textoEntreLosBotones, log, mazmorra, bttnNorte, bttnSur, bttnEste, bttnOeste);
                    loadBotones(bttnEste, Constantes.ESTE, textoEntreLosBotones, log, mazmorra, bttnNorte, bttnSur, bttnEste, bttnOeste);
                } else {
                    textoEntreLosBotones.setText(Constantes.NO_SE_HA_PODIDO_CARGAR_EL_MAPA);
                }

                //TODO para que se actualice la pantalla (para el tree, si no se queda la pantalla en blanco)
                mainPanel.updateUI();
            } catch (JAXBException exception) {
                exception.printStackTrace();
            }
        });


    }


    private static void loadBotones(JButton boton, String direccion, JLabel textoEntreLosBotones, JLabel log, Mazmorra mazmorra, JButton bttnNorte, JButton bttnSur, JButton bttnEste, JButton bttnOeste) {
        //TODO LISTA DE PUERTAS
        List<Door> puertasDisponibles = habitacionActual.getDoors();

        //TODO HABILITAR O DESHABILITAR BOTON
        boton.setEnabled(puertasDisponibles.stream().anyMatch(puerta -> puerta.getName().equalsIgnoreCase(direccion)));

        //TODO Action Listener
        boton.addActionListener(e -> {

            //TODO PUERTA SELECCIONADA
            Door selectedDoor = null;
            for (Door puerta : habitacionActual.getDoors()) {
                if (puerta.getName().equalsIgnoreCase(direccion)) {
                    selectedDoor = puerta;
                    break;
                }
            }

            //TODO ID DE LA PUERTA SELECCIONADA
            String nuevaHabitacionId = selectedDoor.getDestination();

            //TODO BUSCAR LA HABITACION EN LA MAZMORRA
            Room nuevaHabitacion = null;
            for (Room nextRoom : mazmorra.getRooms()) {
                if (nextRoom.getId().equals(nuevaHabitacionId)) {
                    nuevaHabitacion = nextRoom;
                    break;
                }
            }

            //TODO ACTUALIZAR HABITACION ACTUAL
            habitacionActual = nuevaHabitacion;
            textoEntreLosBotones.setText(nuevaHabitacion.getDescription());

            log.setText(log.getText().replace(Constantes.BODY_HTML_FINAL_REPLACE, Constantes.BR_TE_HAS_DIRIGIDO_HACIA_EL + nuevaHabitacionId + Constantes.BODY_HTML_FINAL_REPLACE));
            boton.setEnabled(false);

            //TODO ACTUALIZAR HABITACION ACTUAL
            List<Door> nuevasPuertasDisponibles = habitacionActual.getDoors();
            bttnNorte.setEnabled(nuevasPuertasDisponibles.stream().anyMatch(puerta -> puerta.getName().equalsIgnoreCase(Constantes.NORTE)));
            bttnSur.setEnabled(nuevasPuertasDisponibles.stream().anyMatch(puerta -> puerta.getName().equalsIgnoreCase(Constantes.SUR)));
            bttnOeste.setEnabled(nuevasPuertasDisponibles.stream().anyMatch(puerta -> puerta.getName().equalsIgnoreCase(Constantes.OESTE)));
            bttnEste.setEnabled(nuevasPuertasDisponibles.stream().anyMatch(puerta -> puerta.getName().equalsIgnoreCase(Constantes.ESTE)));
        });


    }

    private static File fileChooserSelectedFile(JFrame frame) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".xml") || f.isDirectory();
            }

            @Override
            public String getDescription() {
                return "XML files";
            }
        });
        fileChooser.showOpenDialog(frame);
        return fileChooser.getSelectedFile();
    }

    private static Mazmorra cargarMapaEnElTree(JTree tree, Mazmorra mazmorra) {
        try {
            //primer nodo
            DefaultMutableTreeNode root = new DefaultMutableTreeNode(Constantes.MAZMORRA);
            tree.setModel(new DefaultTreeModel(root));
            //este bucle es para generar las habitaciones
            for (int i = 0; i < mazmorra.getRooms().size(); i++) {
                DefaultMutableTreeNode room = new DefaultMutableTreeNode(Constantes.ROOM + mazmorra.getRooms().get(i).getId());
                root.add(room);
                room.add(new DefaultMutableTreeNode(mazmorra.getRooms().get(i).getDescription()));
                //este bucle es para las puertas disponibles en cada habitacion
                for (int j = 0; j < mazmorra.getRooms().get(i).getDoors().size(); j++) {
                    room.add(new DefaultMutableTreeNode(mazmorra.getRooms().get(i).getDoors().get(j).getName() + Constantes.FLECHA + mazmorra.getRooms().get(i).getDoors().get(j).getDestination()));
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return mazmorra;
    }
}