package edu.ap.spring.view;

import edu.ap.spring.model.EightBall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class EightballFrame extends JFrame {

    private JTextField vraagTextField;
    private JLabel antwoordLabel;
    @Autowired
    EightBall eightball;

    public EightballFrame(){
        init();
    }

    private void init() {
        this.setDefaultLookAndFeelDecorated(true);
        this.setResizable(false);
        this.setTitle("Eightball");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridLayout gridLayout = new GridLayout(2,2);
        this.setLayout(gridLayout);

        gridLayout.setHgap(25);
        gridLayout.setVgap(10);

        vraagTextField = new JTextField();
        vraagTextField.setSize(25,50);
        JLabel vraagLabel= new JLabel("Vraag:");
        vraagLabel.setSize(25,50);

        antwoordLabel= new JLabel("Antwoord");
        JButton button = new JButton("Answer!");
        button.addActionListener(e -> {
            antwoordLabel.setText(eightball.getRandomAnswer(vraagTextField.getText()));
        });

        this.add(vraagLabel);
        this.add(vraagTextField);
        this.add(antwoordLabel);
        this.add(button);
        this.setSize(300,150);
        this.setVisible(true);
    }
}
