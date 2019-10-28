/**
 * Class Panel Creates a Java Frame with three buttons
 * @Author Shivi, Jack, Rahul, Arjun
 */
//Libraries imported
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class Panel
{
    /**
     * Constructor for Panel
     */
    Panel()
    {
        //Creates a JFrame object titled TrackIt
        JFrame frame= new JFrame("TrackIt");
        //Creates a JPanel object which allows us to manipulate a panel
        JPanel panel=new JPanel();
        //Panel is bounded to width and height units starting from (0,0)
        panel.setBounds(0,0,800,800);
        //Panel is given a background color of pink
        panel.setBackground(Color.PINK);

        //TextField instantiation
        JLabel label1 = new JLabel();
        label1.setBounds(117,100,555,40);
        label1.setFont(new Font("Serif", Font.BOLD, 30));
        label1.setText("Welcome to TrackIt");

        //Button One instantiated
        JButton b1= new JButton(new AbstractAction("Time Clock")
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ClientDriver.main("no");
            }
        });
        //Button one is given a location on the panel with height and width
        b1.setBounds(120,200, 200,200);

        //Button Two instantiated
        JButton b2=new JButton(new AbstractAction("Training")
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ClientDriver.main("train");
            }
        });
        //Button two is given a location on the panel with height and width
        b2.setBounds(460,200,200,200);

        new AdminPanel();

        //buttons b1,b2,b3 are added to the JFrame and can now be interacted with
        frame.add(b1);
        frame.add(b2);
        //frame.add(b3);
        frame.add(label1);
        //Panel is added to the JFrame
        frame.add(panel);
        //JFrame, or the window, is given a width and height
        frame.setSize(800,800);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
