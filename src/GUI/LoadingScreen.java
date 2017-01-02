package GUI;

import kotlin.reflect.jvm.internal.impl.descriptors.EffectiveVisibility;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.time.LocalDate;

/**
 * Created by mian on 2017-01-02.
 */
public class LoadingScreen extends JPanel{

    private JLabel imageLabel;
    private ImageIcon image;

    public LoadingScreen(){
        super();
        this.setPreferredSize(new Dimension(600, 600));

        this.imageLabel = new JLabel();
        this.image = new ImageIcon("images/loading.gif");

        imageLabel.setIcon(image);
        this.add(imageLabel, BorderLayout.CENTER);

    }
}
