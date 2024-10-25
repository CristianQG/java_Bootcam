import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import javax.swing.*;

public class Interfaz extends JFrame {
    private JLabel[][] tablero;
    private boolean jugadorActual;
    private boolean ganador = false;
    private Scanner sc = new Scanner(System.in);

    public Interfaz() {
        jugadorActual = true;
        tablero = new JLabel[3][3];
        setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = new JLabel("", JLabel.CENTER);
                tablero[i][j].setFont( new Font("Time black", Font.PLAIN,80));
                tablero[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                tablero[i][j].setPreferredSize(new Dimension(100, 100));
                int fila = i;
                int columna = j;
                tablero[i][j].addMouseListener(
                        new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                seleccionarPosicion(fila, columna);
                            }
                        });
                add(tablero[i][j]);
            }

        }
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void seleccionarPosicion(int fila, int columna) {
        if (tablero[fila][columna].getText().equals("")) {
            tablero[fila][columna].setText((jugadorActual) ? "X" : "O");
            jugadorActual = !jugadorActual;
            evaluarGanador();
            cambiarTurnos();

        } else {
            JOptionPane.showMessageDialog(null, "La posicion ya esta ocupada");
        }

    }

    private void evaluarGanador() {
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0].getText().equals(tablero[i][1].getText())
                    && tablero[i][1].getText().equals(tablero[i][2].getText())
                    && !tablero[i][0].getText().equals("") && !tablero[i][1].getText().equals("")
                    && !tablero[i][2].getText().equals("")) {
                ganador = true;
                return;
            }
            if (tablero[0][i].getText().equals(tablero[1][i].getText())
                    && tablero[1][i].getText().equals(tablero[2][i].getText())
                    && !tablero[0][i].getText().equals("") && !tablero[1][i].getText().equals("")
                    && !tablero[2][i].getText().equals("")) {
                ganador = true;
                return;
            }
            if (i == 0) {
                if (tablero[i][i].getText().equals(tablero[i + 1][i + 1].getText())
                        && tablero[i + 1][i + 1].getText().equals(tablero[i + 2][i + 2].getText())
                        && !tablero[i][i].getText().equals("") && !tablero[i + 1][i + 1].getText().equals("")
                        && !tablero[i + 2][i + 2].getText().equals("")) {
                    ganador = true;
                    return;
                }
            }
            if (i == 2) {
                if (tablero[i][i - 2].getText().equals(tablero[i - 1][i - 1].getText())
                        && tablero[i - 1][i - 1].getText().equals(tablero[i - 2][i].getText())
                        && !tablero[i][i - 2].getText().equals("") && !tablero[i - 1][i - 1].getText().equals("")
                        && !tablero[i - 2][i].getText().equals("")) {
                    ganador = true;
                    return;
                }
            }
        }

    }

    public boolean evaluarEmpate() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    public void reiniciarJuego() {
        int opcion = JOptionPane.showConfirmDialog(null, "Desea jugar de nuevo?", "Reiniciar", 0);
        if (opcion == 0) {
            JOptionPane.showMessageDialog(null, "Juegue de nuevo.");
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    tablero[i][j].setText("");
                }
            }
            ganador = false;
        } else if (opcion == 1) {
            JOptionPane.showMessageDialog(null, "Muchas gracias por jugar.");
            System.exit(0);

        }
    }

    public void cambiarTurnos() {
        if (ganador) {
            String mensajeGanador = (jugadorActual) ? "El ganador es el jugador dos." : "El ganador es el jugador uno.";
            JOptionPane.showMessageDialog(null, mensajeGanador);
            // Para que cierre directamente//System.exit(0);
        } else if (evaluarEmpate()) {
            JOptionPane.showMessageDialog(null, "Ha habido un empate.");

        }
        if (ganador || evaluarEmpate()) {
            reiniciarJuego();

        }
    }
}
