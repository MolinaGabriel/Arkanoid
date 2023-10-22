package Juego;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;


public class rectangulo {
    private int x, y;
    private int velocidadX = 15; // Velocidad de movimiento en el eje X
    private int ancho = 100; // Ancho del cuadrado
    private int alto = 25; // Alto del cuadrado
    private Color color = Color.WHITE; // Color del cuadrado

    public rectangulo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moverIzquierda() {
        if (x > 0) {
            x -= velocidadX;
        }
    }

    public void moverDerecha() {
        if (x < 685 - ancho) {
            x += velocidadX;
        }
    }

  public void paint(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    // Establece el grosor de la línea en 3 píxeles (puedes ajustar este valor según tus preferencias)
    g2d.setStroke(new BasicStroke(2));
    
    // Establece el color del contorno en negro
    g2d.setColor(Color.BLACK);
    g2d.drawRect(x, y, ancho, alto); // Dibuja el contorno del rectángulo
    
    // Establece el color del relleno en blanco
    g2d.setColor(color);
    g2d.fillRect(x + 1, y + 1, ancho - 1, alto - 1); // Rellena el rectángulo, restando 1 a las coordenadas y restando 1 al ancho y alto para que el contorno sea visible
}


    public Rectangle getBounds() {
        return new Rectangle(x, y, ancho, alto);
    }
}
