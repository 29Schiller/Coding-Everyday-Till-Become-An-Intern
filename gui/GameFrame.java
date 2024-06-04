import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame {
    public static final int SCREEN_WIDTH = 1300;
    public static final int SCREEN_HEIGHT = 750;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private MenuGame menuGame;
    private GamePanel gamePanel;

    public GameFrame() {
        Toolkit toolkit = this.getToolkit();
        Dimension dimension = toolkit.getScreenSize();
        this.setBounds((dimension.width - SCREEN_WIDTH) / 2, (dimension.height - SCREEN_HEIGHT) / 2, SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        menuGame = new MenuGame();
        gamePanel = new GamePanel();

        JPanel menuPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                menuGame.render((Graphics2D) g);
            }
        };
        menuPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();
                menuGame.handleMouseClick(mouseX, mouseY);
                if (menuGame.isCheckPlay()) {
                    showGamePanel();
                }
            }
        });

        mainPanel.add(menuPanel, "Menu");
        mainPanel.add(gamePanel, "Game");

        add(mainPanel);
    }

    public void showGamePanel() {
        cardLayout.show(mainPanel, "Game");
        gamePanel.startGame();
    }

    public static int getScreenWidth() {
        return SCREEN_WIDTH;
    }

    public static int getScreenHeight() {
        return SCREEN_HEIGHT;
    }

    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame();
        gameFrame.setVisible(true);
    }
}