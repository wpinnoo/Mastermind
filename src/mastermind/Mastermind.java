package mastermind;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JFrame;
import view.panels.MainPanel;

/**
 *
 * @author Wouter Pinnoo
 */
public class Mastermind {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new Mastermind().createAndShowGUI();
            }
        });
    }

    private void createAndShowGUI() {
        JFrame window = new JFrame("Mastermind \u00a9 Wouter Pinnoo");
        window.setContentPane(new MainPanel());
        window.setIconImage(Toolkit.getDefaultToolkit().getImage(Mastermind.class.getResource("mastermind.png")));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setPreferredSize(new Dimension(400, 600));
        window.setResizable(false);
        window.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation((dim.width - (window.getSize().width)) / 2, (dim.height - (window.getSize().height)) / 2);
        window.setVisible(true);
    }
}
