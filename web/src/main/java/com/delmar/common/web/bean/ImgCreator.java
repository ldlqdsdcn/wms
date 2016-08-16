package com.delmar.common.web.bean;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class ImgCreator
{
  private Image img;
  public Graphics g;

  public Image getImage(int i, int j)
  {
    int k = (j <= i) ? i : j;
    this.img = new BufferedImage(k, k, 13);
    this.g = ((BufferedImage)this.img).createGraphics();
    return this.img;
  }

  public Graphics getGraphics()
  {
    return this.g;
  }
}