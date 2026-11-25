package sistemavendas;

import sistemavendas.view.Estilo;
import sistemavendas.view.TelaPrincipal;

public class Main {

    public static void main(String[] args) {
        Estilo.configurarTema();
        java.awt.EventQueue.invokeLater(() -> new TelaPrincipal().setVisible(true));
    }
}
