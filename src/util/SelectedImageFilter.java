package util;

import java.awt.image.RGBImageFilter;

public class SelectedImageFilter extends RGBImageFilter {
  private final float rf;
  private final float gf;
  private final float bf;

  public SelectedImageFilter(float rf, float gf, float bf) {
    super();
    this.rf = Math.min(1f, rf);
    this.gf = Math.min(1f, gf);
    this.bf = Math.min(1f, bf);
    canFilterIndexColorModel = false;
  }
  // @Override public int filterRGB(int x, int y, int argb) {
  //   Color color = new Color(argb, true);
  //   float[] array = new float[4];
  //   color.getComponents(array);
  //   return new Color(array[0] * filter[0], array[1] * filter[1], array[2] * filter[2], array[3]).getRGB();
  // }

  @Override public int filterRGB(int x, int y, int argb) {
    int r = (int) (((argb >> 16) & 0xFF) * rf);
    int g = (int) (((argb >> 8) & 0xFF) * gf);
    int b = (int) ((argb & 0xFF) * bf);
    return (argb & 0xFF_00_00_00) | (r << 16) | (g << 8) | b;
  }
}
