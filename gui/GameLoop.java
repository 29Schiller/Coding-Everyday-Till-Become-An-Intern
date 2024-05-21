public class GameLoop implements Runnable {
    private Boolean isRunning;
    private final long FPS = 60;
    private final long PERIOD = 1000 * 1000000 / FPS; //unique nano second

    public GameLoop() {
        isRunning = true;
    }

    public void stop() {
        isRunning = false;
    }

    @Override
    public void run() {
        long beginTime;
        long sleepTime;
        beginTime = System.nanoTime();
        while (isRunning) {
            long deltaTime = System.nanoTime() - beginTime;
            sleepTime = PERIOD - deltaTime;
            try {
                if (sleepTime > 0) {
                    Thread.sleep(sleepTime / 1000000);
                } else {
                    Thread.sleep(PERIOD / 2000000);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            beginTime = System.nanoTime();
        }
    }
}