
package vista;

import javax.swing.*;

import dao.PublicacionDAO;

import java.awt.*;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {
        setTitle("Mi Blog Personal");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));

        JLabel titulo = new JLabel("MI BLOG PERSONAL", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));

        JTextField campoTitulo = new JTextField();
        campoTitulo.setBorder(
            BorderFactory.createTitledBorder("Título de la publicación")
        );

        JTextArea campoContenido = new JTextArea(8, 30);
        campoContenido.setLineWrap(true);
        campoContenido.setWrapStyleWord(true);

        JScrollPane scroll = new JScrollPane(campoContenido);
        scroll.setBorder(
            BorderFactory.createTitledBorder("Contenido")
        );

        JButton botonPublicar = new JButton("Publicar");
        JButton botonVer = new JButton("Ver publivaciones");
        JButton botonActualizar = new JButton("Actualizar");
JButton botonEliminar = new JButton("Eliminar");



botonPublicar.addActionListener(e -> {
String tituloTexto = campoTitulo.getText().trim();
String contenidoTexto = campoContenido.getText().trim();

    if (tituloTexto.isEmpty() || contenidoTexto.isEmpty()) {
        JOptionPane.showMessageDialog(this,
            "Completa el título y el contenido");
        return;
    }

    PublicacionDAO dao = new PublicacionDAO();
boolean resultado = dao.insertar(tituloTexto, contenidoTexto, "publicado");

    if (resultado) {
        JOptionPane.showMessageDialog(this,
            "¡Publicación guardada correctamente!");
    } else {
        JOptionPane.showMessageDialog(this,
            "Error al guardar la publicación");
    }
});


        JPanel formulario = new JPanel(new BorderLayout(10, 10));
                botonVer.addActionListener(e -> {
            PublicacionDAO dao = new PublicacionDAO();

            JTextArea area = new JTextArea(dao.consultar(), 15, 40);
            area.setEditable(false);

            JOptionPane.showMessageDialog(
                this,
                new JScrollPane(area),
                "Publicaciones guardadas",
                JOptionPane.INFORMATION_MESSAGE
            );
       
        });
        formulario.add(campoTitulo, BorderLayout.NORTH);
        formulario.add(scroll, BorderLayout.CENTER);
        JPanel botones = new JPanel();
botonActualizar.addActionListener(e -> {
    String idTexto = JOptionPane.showInputDialog(this, "ID de la publicación que deseas actualizar:");

    if (idTexto == null || idTexto.trim().isEmpty()) {
        return;
    }

    try {
        int id = Integer.parseInt(idTexto.trim());

        String nuevoTitulo = JOptionPane.showInputDialog(this, "Nuevo título:");
        if (nuevoTitulo == null || nuevoTitulo.trim().isEmpty()) return;

        String nuevoContenido = JOptionPane.showInputDialog(this, "Nuevo contenido:");
        if (nuevoContenido == null || nuevoContenido.trim().isEmpty()) return;

        PublicacionDAO dao = new PublicacionDAO();
        boolean resultado = dao.actualizar(id, nuevoTitulo, nuevoContenido, "Publicado");

        if (resultado) {
            JOptionPane.showMessageDialog(this, "Publicación actualizada.");
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo actualizar.");
        }

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "El ID debe ser un número.");
    }
});
botonEliminar.addActionListener(e -> {
    String idTexto = JOptionPane.showInputDialog(this,
        "ID de la publicación que deseas eliminar:");

    if (idTexto == null || idTexto.trim().isEmpty()) {
        return;
    }

    try {
        int id = Integer.parseInt(idTexto.trim());

        int confirmacion = JOptionPane.showConfirmDialog(
            this,
            "¿Seguro que deseas eliminar esta publicación?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            PublicacionDAO dao = new PublicacionDAO();
            boolean resultado = dao.eliminar(id);

            if (resultado) {
                JOptionPane.showMessageDialog(this, "Publicación eliminada.");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar.");
            }
        }

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "El ID debe ser un número.");
    }
});
botones.add(botonPublicar);
botones.add(botonVer);
botones.add(botonActualizar);
botones.add(botonEliminar);
formulario.add(botones, BorderLayout.SOUTH);

        panel.add(titulo, BorderLayout.NORTH);
        panel.add(formulario, BorderLayout.CENTER);

        add(panel);
    }
}
