package com.delmar.common.web.bean;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class ImgCreator
{
  public Graphics g;

  public Image getImage(int i, int j)
  {
    int k = (j <= i) ? i : j;
    Image img = new BufferedImage(k, k, 13);
    this.g = ((BufferedImage) img).createGraphics();
    return img;
  }

  public Graphics getGraphics()
  {
    return this.g;
  }
}