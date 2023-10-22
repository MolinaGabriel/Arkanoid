package Juego;


import java.awt.*;

public class Cuadrado {
    private int x, y; // Posición del cuadrado
    private int width, height; // Dimensiones del cuadrado
    private boolean visible; // Estado del cuadrado

    public Cuadrado(int x, int y, int width, int height) {
        this.x = x;
        this.y = y+50;
        this.width = width;
        this.height = height;
        this.visible = true; // El cuadrado es visible inicialmente
    }

    public void paint(Graphics g) {
        if (visible) {
            g.setColor(Color.WHITE); // Establece el color de fondo a blanco
            g.fillRect(x, y, width, height);

            g.setColor(Color.BLACK); // Establece el color de contorno a negro
            g.drawRect(x, y, width, height);
        }
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
