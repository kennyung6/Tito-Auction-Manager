package helpers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;
import java.util.List;

public class LevelBar extends JPanel implements MouseListener, MouseMotionListener {
  private final int gap;
  protected final List<ImageIcon> iconList;
  protected final List<JLabel> labelList = Arrays.asList(
      new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel()
  );
  protected final ImageIcon defaultIcon;
  private int clicked = -1;

  public LevelBar(ImageIcon defaultIcon, List<ImageIcon> list, int gap) {
    super(new GridLayout(1, 5, gap * 2, gap * 2));
    this.defaultIcon = defaultIcon;
    this.iconList = list;
    this.gap = gap;
    for (JLabel l: labelList) {
      l.setIcon(defaultIcon);
      add(l);
    }
    addMouseListener(this);
    addMouseMotionListener(this);
  }

  public void clear() {
    clicked = -1;
    repaintIcon(clicked);
  }

  public int getLevel() {
    return clicked;
  }

  public void setLevel(int l) {
    clicked = l;
    repaintIcon(clicked);
  }

  private int getSelectedIconIndex(Point p) {
    for (int i = 0; i < labelList.size(); i++) {
      Rectangle r = labelList.get(i).getBounds();
      r.grow(gap, gap);
      if (r.contains(p)) {
        return i;
      }
    }
    return -1;
  }

  protected void repaintIcon(int index) {
    for (int i = 0; i < labelList.size(); i++) {
      labelList.get(i).setIcon(i <= index ? iconList.get(i) : defaultIcon);
    }
    repaint();
  }

  @Override public void mouseMoved(MouseEvent e) {
    repaintIcon(getSelectedIconIndex(e.getPoint()));
  }

  @Override public void mouseEntered(MouseEvent e) {
    repaintIcon(getSelectedIconIndex(e.getPoint()));
  }

  @Override public void mouseClicked(MouseEvent e) {
    clicked = getSelectedIconIndex(e.getPoint());
  }

  @Override public void mouseExited(MouseEvent e) {
    repaintIcon(clicked);
  }

  @Override public void mouseDragged(MouseEvent e) { /* not needed */ }

  @Override public void mousePressed(MouseEvent e) { /* not needed */ }

  @Override public void mouseReleased(MouseEvent e) { /* not needed */ }
}
