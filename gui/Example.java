import javax.swing.*;
import java.awt.*;

public class Example {

    /* Test sample
    // Create a JPanel to hold the images
    private JPanel panel;

    // Import Background Scene
    public void showMenuState() {
        ImageIcon menuBackground = new ImageIcon("res/wallpaper.jpg");
        JLabel backgroundLabel = new JLabel(menuBackground);
        panel.add(backgroundLabel);
    }
     
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
    */



    // Create a main method to display the panel in a JFrame
    public static void main(String[] args) {
        
        JFrame windowFrame = new JFrame();
        Container con = new Container();
        JPanel picturPanel = new JPanel();

        ImageIcon background = new ImageIcon("res/wallpaper.jpg");
        ImageIcon title = new ImageIcon("res/pvz_logo.png");
        ImageIcon playbutton = new ImageIcon("res/Button/PlayDemo.png");

        //Set Window Frame
        windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windowFrame.setSize(1600,750);
        windowFrame.setVisible(true);
        windowFrame.setLayout(null);
        windowFrame.add(picturPanel);

        //Scaling image to fit in the JPael
        Image backgroundscalImage = background.getImage().getScaledInstance(windowFrame.getWidth(), windowFrame.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaled_backgrounIcon = new ImageIcon(backgroundscalImage);

        Image titlImage = title.getImage().getScaledInstance(windowFrame.getWidth(), windowFrame.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaled_titleIcon = new ImageIcon(titlImage);

        Image butImage = playbutton.getImage().getScaledInstance(windowFrame.getWidth(), windowFrame.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaled_butIcon = new ImageIcon(butImage);

        //Label 1
        JLabel label1 = new JLabel();
        label1.setIcon(scaled_backgrounIcon);
        JLabel label2 = new JLabel();
        label2.setIcon(scaled_titleIcon);
        JLabel label3 = new JLabel();
        label3.setIcon(scaled_butIcon);
        
        //Set Picture Panel
        picturPanel.setBounds(0,0,1600,750);
        picturPanel.setBackground(Color.blue);
        picturPanel.add(label1);
        picturPanel.add(label2);
        picturPanel.add(label3);
        
        System.out.println(playbutton.getIconWidth());
        System.out.println(playbutton.getIconHeight());
    }
}