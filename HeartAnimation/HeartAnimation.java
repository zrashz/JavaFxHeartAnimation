import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class HeartAnimation extends JPanel {

    // Size variables for scaling the heart
    private double scale = 1.0;
    private boolean growing = true;

    // Variables for the animated text
    private int textY = 400;
    private boolean movingUp = true;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Set red color for the heart
        g2d.setColor(Color.RED);

        // Draw heart using lines
        drawHeart(g2d);

        // Draw the animated text "I Love You Mom" below the heart
        drawText(g2d);
    }

    private void drawHeart(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Heart equation points
        for (double t = 0; t < Math.PI * 2; t += 0.01) {
            double x1 = 16 * Math.sin(t) * Math.sin(t) * Math.sin(t);
            double y1 = 13 * Math.cos(t) - 5 * Math.cos(2 * t) - 2 * Math.cos(3 * t) - Math.cos(4 * t);

            double x2 = 16 * Math.sin(t + 0.01) * Math.sin(t + 0.01) * Math.sin(t + 0.01);
            double y2 = 13 * Math.cos(t + 0.01) - 5 * Math.cos(2 * (t + 0.01)) - 2 * Math.cos(3 * (t + 0.01))
                    - Math.cos(4 * (t + 0.01));

            // Scaling the heart
            x1 *= scale;
            y1 *= scale;
            x2 *= scale;
            y2 *= scale;

            // Drawing lines to represent the heart
            g2d.draw(new Line2D.Double(300 + x1 * 10, 300 - y1 * 10, 300 + x2 * 10, 300 - y2 * 10));
        }
    }

    // Method to draw the animated text
    private void drawText(Graphics2D g2d) {
        g2d.setFont(new Font("Serif", Font.BOLD, 30));
        g2d.setColor(Color.BLACK);
        g2d.drawString("I Love You Mom", 200, textY);
    }

    // Animation control for heart beating effect and text animation
    public void animateHeartAndText() {
        Timer timer = new Timer(50, e -> {
            // Heart scaling (beating)
            if (growing) {
                scale += 0.01;
                if (scale >= 1.2) {
                    growing = false;
                }
            } else {
                scale -= 0.01;
                if (scale <= 1.0) {
                    growing = true;
                }
            }

            // Text animation (moving up and down)
            if (movingUp) {
                textY -= 1;
                if (textY <= 380) {
                    movingUp = false;
                }
            } else {
                textY += 1;
                if (textY >= 420) {
                    movingUp = true;
                }
            }

            repaint(); // Redraw the heart and text
        });
        timer.start();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Heart Animation with Text");
        HeartAnimation heartAnimation = new HeartAnimation();
        frame.add(heartAnimation);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        heartAnimation.animateHeartAndText(); // Start the animation
    }
}