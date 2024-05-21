import java.awt.*;
public interface  SceneManager {
    public void render(Graphics g,Image img);
    void mouseClicked(int x, int y);
    void mousePressed(int x, int y);
    void mouseReleased(int x, int y);
} 