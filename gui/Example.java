import javax.swing.*;
import java.awt.*;

public class Example {
    // Create a JPanel to hold the images
    private JPanel panel;

    // Import Background Scene
    public void showMenuState() {
        ImageIcon menuBackground = new ImageIcon("res/wallpaper.jpg");
        JLabel backgroundLabel = new JLabel(menuBackground);
        panel.add(backgroundLabel);
    }
    /* 
    public void showTitle() {
        ImageIcon title = new ImageIcon("res/pvz_logo.png");
        float rate = (float) 0.7;
        float width = title.getIconWidth() * 1600 * rate;
        float height = title.getIconHeight() * 900 * rate;
        float posX = 1300 / 2 - width / 2;
        float posY = 750 * (float) (0.2) - height / 2;

        // Create a JLabel with the title image and set its size and position
        JLabel titleLabel = new JLabel(title);
        titleLabel.setBounds((int) posX, (int) posY, (int) width, (int) height);
        panel.add(titleLabel);
    }
    */
    /*
    // Import Button Start
    public void showButton() {
        ImageIcon start = new ImageIcon("res/PlayDemo.png");
        float rate = 0.9f;
        float width = start.getIconWidth() * 1600 * rate;
        float height = start.getIconHeight() * 900 * rate;

        float posX = 1300 / 2 - width / 2;
        float posY = 750 * (float) (0.7) - height / 2;

        // Create a JLabel with the start button image and set its size and position
        JLabel startLabel = new JLabel(start);
        startLabel.setBounds((int) posX, (int) posY, (int) width, (int) height);
        panel.add(startLabel);
    }
     */
    // Import Theme Sound

    // Create a constructor to initialize the panel
    public Example() {
        panel = new JPanel();
        System.out.println("Panel: " + panel);
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(1600, 900));
        showMenuState();
        // showTitle();
        //showButton();
    }


    // Create a main method to display the panel in a JFrame
    public static void main(String[] args) {
        Example exam = new Example();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(exam.panel);
        frame.setVisible(true);
    }
}