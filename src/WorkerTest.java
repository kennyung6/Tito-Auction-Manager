import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * @see "http://stackoverflow.com/questions/4530659"
 */
public final class WorkerTest extends JFrame {

    private final JLabel label = new JLabel("Loading...");

    public WorkerTest() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        this.add(label);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    private void start() {
        new ImageWorker().execute();
    }

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                WorkerTest wt = new WorkerTest();
                wt.setVisible(true);
                wt.start();
            }
        });
    }

    class ImageWorker extends SwingWorker<Image, Void> {

        private static final String TEST
                = "http://cdn.sstatic.net/stackexchange/img/logos/so/so-logo.png";

        @Override
        protected Image doInBackground() throws IOException {
            Image image = ImageIO.read(new URL(TEST));
            return image.getScaledInstance(640, -1, Image.SCALE_SMOOTH);
        }

        @Override
        protected void done() {
            try {
                ImageIcon icon = new ImageIcon(get());
                label.setIcon(icon);
                label.setText("Done");
                WorkerTest.this.pack();
                WorkerTest.this.setLocationRelativeTo(null);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
