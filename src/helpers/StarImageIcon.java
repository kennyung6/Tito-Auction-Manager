package helpers;

import javax.swing.*;
import java.awt.*;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageProducer;

public class StarImageIcon {

    public ImageIcon makeStarImageIcon(ImageProducer ip, float rf, float gf, float bf) {
        return new ImageIcon(Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(ip, new SelectedImageFilter(rf, gf, bf))));
    }
}
