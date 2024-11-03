import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class VisualizadorArbolAVL extends JFrame {
    private ArbolAVL<Integer> arbol;
    private int radio = 25; // Radio de los nodos
    private int espacioVertical = 70; // Espacio vertical entre niveles
    private int anchoPantalla = 1920;
    private int altoPantalla = 1080;

    /*Resoluciones:
    * 1200 x 800
    * FULLHD : 1920 x 1080
    * 2k : 2560 x 1440
    * */

    public VisualizadorArbolAVL(ArbolAVL<Integer> arbol) {
        this.arbol = arbol;
        this.setTitle("Visualizador Árbol AVL");
        this.setSize(anchoPantalla, altoPantalla);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.add(new ArbolPanel());
        this.setLocationRelativeTo(null);
    }

    class ArbolPanel extends JPanel {
        public ArbolPanel() {
            setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (arbol != null && arbol.getRaiz() != null) {
                dibujarArbol(g2d, arbol.getRaiz(), anchoPantalla / 2, 50, anchoPantalla / 4);
            }
        }

        private void dibujarArbol(Graphics2D g2d, Nodo<Integer> nodo, int x, int y, int offset) {
            if (nodo == null) return;

            // Dibujar las líneas a los hijos primero (para que estén detrás de los nodos)
            if (nodo.getIzquierda() != null) {
                g2d.setColor(Color.GRAY);
                g2d.draw(new Line2D.Double(x, y, x - offset, y + espacioVertical));
            }
            if (nodo.getDerecha() != null) {
                g2d.setColor(Color.GRAY);
                g2d.draw(new Line2D.Double(x, y, x + offset, y + espacioVertical));
            }

            // Dibujar el nodo
            g2d.setColor(new Color(173, 216, 230)); // Color celeste claro
            g2d.fill(new Ellipse2D.Double(x - radio, y - radio, 2 * radio, 2 * radio));
            g2d.setColor(Color.BLACK);
            g2d.draw(new Ellipse2D.Double(x - radio, y - radio, 2 * radio, 2 * radio));

            // Dibujar el valor y el FE
            g2d.setFont(new Font("Arial", Font.BOLD, 12));
            String valorTexto = nodo.getElemento().toString();
            FontMetrics fm = g2d.getFontMetrics();
            int textWidth = fm.stringWidth(valorTexto);
            g2d.drawString(valorTexto, x - textWidth/2, y);

            String feTexto = "FE:" + nodo.getFe();
            textWidth = fm.stringWidth(feTexto);
            g2d.setFont(new Font("Arial", Font.PLAIN, 10));
            g2d.drawString(feTexto, x - textWidth/2, y + 15);

            // Recursivamente dibujar los subárboles
            if (nodo.getIzquierda() != null) {
                dibujarArbol(g2d, nodo.getIzquierda(), x - offset, y + espacioVertical, offset / 2);
            }
            if (nodo.getDerecha() != null) {
                dibujarArbol(g2d, nodo.getDerecha(), x + offset, y + espacioVertical, offset / 2);
            }
        }
    }
}