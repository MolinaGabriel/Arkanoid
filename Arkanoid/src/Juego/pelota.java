package Juego;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class pelota {
    public int x, y;
    private int dx = 5; // Cambio en la posición en el eje X
    private int dy = 5; // Cambio en la posición en el eje Y
    private int radio = 15; // Radio de la pelota
    private Color color = Color.WHITE; // Color de la pelota
    public int nivel = 0;
    public int Verificacion = 0;
    private Random rand = new Random();

    public pelota() {
        this.x = 320 - radio; // Mitad del ancho del frame
        this.y = 205 - radio; // Mitad del alto del frame

        // Genera direcciones aleatorias en los ejes X e Y
        randomizarDireccion();

        if (Eleccion.nivel == 1) {
            dx = 5;
            dy = 5;
            nivel = 1;
        } else if (Eleccion.nivel == 2) {
            dx = 8;
            dy = 8;
            nivel = 2;
        } else if (Eleccion.nivel == 3) {
            dx = 10;
            dy = 10;
            nivel = 3;
        } else {
            dx = 5;
            dy = 5;
            nivel = 0;
        }
    }

    public void randomizarDireccion() {
        // Genera un número aleatorio entre -5 y 5 para dx
        dx = rand.nextInt(11) - 5;
        // Genera un número aleatorio entre -5 y 5 para dy
        dy = rand.nextInt(11) - 5;

        // Asegura que la pelota no quede quieta en ambos ejes
        if (dx == 0) {
            dx = 1;
        }
        if (dy == 0) {
            dy = 1;
        }
    }

    public void mover() {
        x += dx;
        y += dy;

        // Verifica colisión con los bordes del frame
        if (x < 0 || x > 670 - 2 * radio) {
            dx = -dx;
        }
        if (y < 50 || y > 670 - 2 * radio) {
            dy *= -1; // Cambia la dirección en el eje Y
        }
        if (y >= 670 - 2 * radio && x >= 0) {
            Verificacion = 1;
        }
    }

    public void cambiarDireccionY() {
        dy *= -1 - 0.05; // Cambia la dirección en el eje Y
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        // Establece el grosor de la línea en 3 píxeles (ajusta este valor según tus preferencias)
        g2d.setStroke(new BasicStroke(1));

        // Establece el color del contorno en negro
        g2d.setColor(Color.BLACK);
        g2d.drawOval(x, y, radio * 2, radio * 2); // Dibuja el contorno de la pelota

        // Establece el color del relleno en blanco
        g2d.setColor(color);
        g2d.fillOval(x + 1, y + 1, (radio - 1) * 2, (radio - 1) * 2); // Rellena la pelota, restando 1 a las coordenadas y reduciendo el radio en 1 para que el contorno sea visible
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, radio * 2, radio * 2);
    }

    public void incrementarPuntaje() {
        // Implementa la lógica para incrementar el puntaje aquí
    }
}

