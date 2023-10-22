package Juego;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;


public class Arkanoid extends JPanel implements KeyListener {
    private pelota pel;
    private rectangulo rect;
    private Image fondo;
    private int tiempoTranscurrido;
    private int vidas = 3;
    private int numRows = 5;
    private int numCols = 8;
    private Cuadrado[][] cuadrados;
    private boolean juegoTerminado = false;
    private int veri = 0;
    private int aux = 0;
    private JFrame miVentana;

    private Timer timerContador;
    private Timer timerPelota;
 
    int puntaje = 0;
    int nivel=0;
    int limite=0;
    int toque=0;
    int espera=0;
    int verificacion=1;
    public Arkanoid() {
   
    
        aux = vidas;
        pel = new pelota();
        rect = new rectangulo(300, 600);
        cuadrados = new Cuadrado[numRows][numCols];
        inicializarCuadrados();

        fondo = new ImageIcon(getClass().getResource("/imagenes/80.jpg")).getImage();
        tiempoTranscurrido = 0;

        timerContador = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tiempoTranscurrido++;
                espera = 2;
            }
        });

    

        timerPelota = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (juegoTerminado) {
                    return;
                }
                pel.mover();

                if (pel.getBounds().intersects(rect.getBounds())) {
                    pel.cambiarDireccionY();
                }

                for (int row = 0; row < numRows; row++) {
                    for (int col = 0; col < numCols; col++) {
                     //   if(){
                        if (cuadrados[row][col] != null && pel.getBounds().intersects(cuadrados[row][col].getBounds())) {
                            pel.cambiarDireccionY();
                            eliminarCuadrado(row, col);
                            puntaje+=5;
                            toque++;
                        }
                    }
                //}
                }
               if(vidas >=1 ){
                if (pel.Verificacion == 1 ) {
                    JOptionPane.showMessageDialog(Arkanoid.this, "Perdiste una vida\nTiempo transcurrido: " + tiempoTranscurrido + " segundos" + "\nPuntaje:" + puntaje);
                    veri = 1;
                    reiniciarJuego();
                }
               }
                if (veri == 1) {
                    vidas = vidas - 1;
                    veri = 0;
                
                }
          
                repaint();
            }
        });

        timerContador.start();
        timerPelota.start();
        setFocusable(true);
        addKeyListener(this);
        
        
    }

    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0,getWidth(), getHeight(), this);
        Font fuente = new Font("Unispace", Font.BOLD, 24);
        g.setFont(fuente);
        String mensajePuntaje = "Puntaje: " +puntaje;
        g.setColor(Color.WHITE);
        g.drawString(mensajePuntaje, 270, 25);
        nivel=Eleccion.nivel;
        if(nivel==1 || nivel==2||nivel==3){
        String mensajeNivel = ("Nivel: "+nivel);  // Cambia el número de nivel según tu lógica
        g.setColor(Color.WHITE);
        g.drawString(mensajeNivel, 10, 25);
        }
        if(nivel==0){
        String MensajeNivel = ("Sin limite");
        g.setColor(Color.WHITE);
        g.drawString(MensajeNivel, 10, 25);
    
        }
        
        String mensajeVidas = "Vidas: " + vidas;
        g.setColor(Color.WHITE);
        g.drawString(mensajeVidas, 550, 25);

        pel.paint(g);
        rect.paint(g);

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (cuadrados[row][col] != null) {
                    cuadrados[row][col].paint(g);
                }
            }
        }
        
         if (vidas == 0) {
                 timerPelota.stop();
        timerContador.stop();

        String mensajePerdiste = "¡Perdiste!";
        g.setColor(Color.WHITE);
        g.setFont(new Font("Unispace", Font.BOLD, 32));
        int mensajeAncho = g.getFontMetrics().stringWidth(mensajePerdiste);
        int mensajeX = (getWidth() - mensajeAncho) / 2;
        g.drawString(mensajePerdiste, mensajeX, getHeight() / 2);
           
        String Informacion ="Puntaje: " + puntaje+"  \nTiempo transcurrido: " + tiempoTranscurrido + " segundos";
        
    g.setFont(new Font("Unispace", Font.PLAIN, 20));
    int infoAncho = g.getFontMetrics().stringWidth(Informacion);
    int infoX = (getWidth() - infoAncho) / 2;
    g.drawString(Informacion, infoX, getHeight() / 2 + 40);

        JButton volverAlMenu = new JButton("Volver al Menú");
        volverAlMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Inicio inicioFrame = new Inicio();
                inicioFrame.setVisible(true);
                miVentana.dispose();
            }
        });

        volverAlMenu.setBounds(getWidth() / 2 - 75, getHeight() / 2 + 80, 150, 30);
        add(volverAlMenu);
        }
         
        if (toque == limite && verificacion==1) {
             timerPelota.stop();
        timerContador.stop();

    String mensajeGanaste = "¡Ganaste!";
    String mensajeInfo = "Puntaje: " + puntaje + " Tiempo transcurrido: " + tiempoTranscurrido + " segundos";

    g.setColor(Color.WHITE);
    g.setFont(new Font("Unispace", Font.BOLD, 32));
    int mensajeAncho = g.getFontMetrics().stringWidth(mensajeGanaste);
    int mensajeX = (getWidth() - mensajeAncho) / 2;
    g.drawString(mensajeGanaste, mensajeX, getHeight() / 2);

    g.setFont(new Font("Unispace", Font.PLAIN, 20));
    int infoAncho = g.getFontMetrics().stringWidth(mensajeInfo);
    int infoX = (getWidth() - infoAncho) / 2;
    g.drawString(mensajeInfo, infoX, getHeight() / 2 + 40);

    JButton volverAlMenu = new JButton("Volver al Menú");
   volverAlMenu.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      
    Inicio inicioFrame = new Inicio(); // Crear una instancia de la clase "Eleccion"
    inicioFrame.setVisible(true); // Mostrar la instancia de "Eleccion"
  
    miVentana.dispose(); // Cerrar la ventana actual ("Inicio")
    }
});

    volverAlMenu.setBounds(getWidth() / 2 - 75, getHeight() / 2 + 80, 150, 30);
    add(volverAlMenu);
    
    if (juegoTerminado) {
        miVentana.dispose();
    }
}
    }

    private void eliminarCuadrado(int row, int col) {
        cuadrados[row][col] = null;
    }

 
    private void inicializarCuadrados() {
        int paddingX = 5;
        int paddingY = 5;
        int squareWidth = 81;
        int squareHeight = 20;
    nivel = Eleccion.nivel; // Obtén el nivel desde la clase pelota
    
    // Ajusta el número de filas de cuadrados según el nivel seleccionado
    if (nivel == 1) {
        numRows = 2;
       limite=16;
    } else if (nivel == 2) {
        numRows = 3;
        limite=24;
    } else if (nivel == 3) {
        numRows = 4;
        limite=32;
    }
    else if(nivel==0){
        numRows = 1;
        limite=40;
    }
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                int x = col * (squareWidth + paddingX);
                int y = row * (squareHeight + paddingY);
                cuadrados[row][col] = new Cuadrado(x, y, squareWidth, squareHeight);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Arkanoid game = new Arkanoid();

            String input = JOptionPane.showInputDialog("Ingrese el número de vidas:");
            try {
                game.vidas = Integer.parseInt(input);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Entrada no válida. Se usará el valor predeterminado (3) para las vidas.");
            }

            JFrame miVentana = new JFrame("Juego");
            miVentana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            miVentana.add(game);
            miVentana.setSize(700, 700);
            miVentana.setVisible(true);
            miVentana.setResizable(false);
            miVentana.setLocationRelativeTo(null);
        });
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) {
            rect.moverIzquierda();
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            rect.moverDerecha();
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void reiniciarJuego() {
        pel = new pelota();
        tiempoTranscurrido = 0;

        timerPelota.stop();
        timerContador.restart();

        timerPelota.start();
    }

 
}
