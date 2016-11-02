/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package hexer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**

 @author Mark Ferguson
 */
public class HexPanel extends JPanel {

  public HexPanel() {
    setBackground(Color.white);
  }

  private static void addPoly() {
    ////System.out.print("addPoly()");
    AHexer.poly = new Polygon();
    AHexer.poly.addPoint((int) (AHexer.L + AHexer.L3), (int) AHexer.T);
    AHexer.poly.addPoint((int) (AHexer.L + AHexer.L3 + AHexer.L5),
      (int) AHexer.T);
    AHexer.poly.addPoint((int) (AHexer.L + AHexer.L5 + (AHexer.L3 * 2)),
      (int) (AHexer.T + AHexer.L4));
    AHexer.poly.addPoint((int) (AHexer.L + AHexer.L3 + (int) AHexer.L5),
      (int) (AHexer.T + (AHexer.L4 * 2)));
    AHexer.poly.addPoint((int) (AHexer.L + AHexer.L3),
      (int) (AHexer.T + (AHexer.L4 * 2)));
    AHexer.poly.addPoint((int) (AHexer.L), (int) (AHexer.T + AHexer.L4));
    AHexer.poly.addPoint((int) (AHexer.L + AHexer.L3), (int) AHexer.T);
  }

  private void drawPoly(Graphics g) {
    g.setColor(Color.white);
    g.fillPolygon(AHexer.poly);
    g.setColor(Color.black);
    Graphics2D painted = (Graphics2D) g;
    BasicStroke pen = new BasicStroke((int) AHexer.jsl.getValue());
    painted.setStroke(pen);
    painted.draw(AHexer.poly);
    repaint();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    //boolean square = true;
    AHexer.L = AHexer.G * AHexer.M;
    BufferedImage image = null;
    for (AHexer.col = 1; AHexer.col <= AHexer.Cols;
      AHexer.col++) {
      if (AHexer.col % 2 == 1) {
        AHexer.T = AHexer.G * AHexer.M;
      } else {
        AHexer.T = ((AHexer.H / 2) + (AHexer.G * 3 / 2)) * AHexer.M;
      }
      if ((AHexer.col % 2 == 1) && (AHexer.square.isSelected())) {
        //          T = 0;
        AHexer.T = ((AHexer.H / 2) + (AHexer.G * 3 / 2)) * AHexer.M;
      }
      //ACROSS
      for (AHexer.row = 1; AHexer.row <= AHexer.Rows;
        AHexer.row++) {
        //DOWN
        if (!AHexer.square.isSelected()) {
          AHexer.jss.setEnabled(false);
          addPoly();
          drawPoly(g);
        } else {
          AHexer.jss.setEnabled(true);
          AHexer.jss.setValue(AHexer.jsh.getValue());
          addSquare();
          drawSquare(g);
        }
        //---------------------------
        //<editor-fold defaultstate="collapsed" desc="Extras">
        //Start
        //<editor-fold defaultstate="collapsed" desc="LEFT">
        if (AHexer.LEFT) {
          AHexer.x1 = 0;
          AHexer.y1 = 0;
          AHexer.x2 = 0;
          AHexer.y2 = AHexer.height;
          g.setColor(Color.black);
          g.drawLine(AHexer.x1, AHexer.y1, AHexer.x2, AHexer.y2);
        }
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="RIGHT">
        //          if (RIGHT) {
        if (AHexer.square.isSelected()) {
          AHexer.width
            = ((((int) AHexer.jsh.getValue() + (int) AHexer.jsg.getValue())
            * (int) AHexer.jsx.getValue() * (int) AHexer.jsm.getValue()));
          int acr = (int) AHexer.jsx.getValue();
          int gap = (int) AHexer.jsg.getValue();
          int hgt = (int) AHexer.jsh.getValue();
          //width = ((acr*hgt)+((acr+1)*gap))*(int)jsm.getValue();
        }
        AHexer.x1 = AHexer.width;
        AHexer.y1 = 0;
        AHexer.x2 = AHexer.width;
        AHexer.y2 = AHexer.height;
        g.setColor(Color.black);
        g.drawLine(AHexer.x1, AHexer.y1, AHexer.x2, AHexer.y2);
        //          }
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="TOP">
        if (AHexer.TOP) {
          AHexer.x1 = 0;
          AHexer.y1 = 0;
          AHexer.x2 = AHexer.width;
          AHexer.y2 = 0;
          g.setColor(Color.black);
          g.drawLine(AHexer.x1, AHexer.y1, AHexer.x2, AHexer.y2);
        }
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="BOTTOM">
        if (AHexer.BOTTOM) {
          AHexer.x1 = 0;
          AHexer.y1 = AHexer.height;
          AHexer.x2 = AHexer.width;
          AHexer.y2 = AHexer.height;
          g.setColor(Color.black);
          g.drawLine(AHexer.x1, AHexer.y1, AHexer.x2, AHexer.y2);
        }
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="CENTRES">
        if (AHexer.CENTRES) {
          //TAS
          g.setColor(Color.red);
          int w = 12 * (int) AHexer.M;
          int h = 12 * (int) AHexer.M;
          g.fillOval((int) (AHexer.L + (AHexer.W / 2)) - (w / 2),
            (int) (AHexer.T + (AHexer.M * (AHexer.H / 2))) - (h / 2), w, h);
          //Allegiance
          g.setColor(Color.cyan);
          w = 10 * (int) AHexer.M;
          h = 10 * (int) AHexer.M;
          g.fillOval((int) (AHexer.L + (AHexer.W / 2)) - (w / 2),
            (int) (AHexer.T + (AHexer.M * (AHexer.H / 2))) - (h / 2), w, h);
          //Atmosphere
          g.setColor(Color.white);
          w = 8 * (int) AHexer.M;
          h = 8 * (int) AHexer.M;
          g.fillOval((int) (AHexer.L + (AHexer.W / 2)) - (w / 2),
            (int) (AHexer.T + (AHexer.M * (AHexer.H / 2))) - (h / 2), w, h);
          //Mainworld
          g.setColor(Color.black);
          w = 5 * (int) AHexer.M;
          h = 5 * (int) AHexer.M;
          g.fillOval((int) (AHexer.L + (AHexer.W / 2)) - (w / 2),
            (int) (AHexer.T + (AHexer.M * (AHexer.H / 2))) - (h / 2), w, h);
          //Water
          g.setColor(Color.cyan);
          w = 4 * (int) AHexer.M;
          h = 4 * (int) AHexer.M;
          g.fillOval((int) (AHexer.L + (AHexer.W / 2)) - (w / 2),
            (int) (AHexer.T + (AHexer.M * (AHexer.H / 2))) - (h / 2), w, h);
        }
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="STAR3">
        if (AHexer.STAR3) {
          g.setColor(Color.red);
          AHexer.poly = new Polygon();
          AHexer.poly.addPoint((int) (AHexer.L + AHexer.L3 + (AHexer.L5 / 2)),
            (int) AHexer.T);
          AHexer.poly.addPoint((int) (AHexer.L + AHexer.L3),
            (int) AHexer.T + (int) (AHexer.H * AHexer.M));
          AHexer.poly.addPoint((int) (AHexer.L + AHexer.L3 + AHexer.L5),
            (int) AHexer.T + (int) (AHexer.H * AHexer.M));
          //4      poly.addPoint((int) (L + L3 + (int) L5), (int) (T + (L4 * 2)));
          //5      poly.addPoint((int) (L + L3), (int) (T + (L4 * 2)));
          //6      poly.addPoint((int) (L), (int) (T + L4));
          //1      poly.addPoint((int) (L + L3), (int) T);
          g.fillPolygon(AHexer.poly);
        }
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="STAR4">
        if (AHexer.STAR4) {
          g.setColor(Color.orange);
          AHexer.poly = new Polygon();
          AHexer.poly.addPoint((int) (AHexer.L + AHexer.L3),
            (int) (AHexer.T + (AHexer.L4 / 2)));
          AHexer.poly.addPoint((int) (AHexer.L + AHexer.L3 + AHexer.L5),
            (int) (AHexer.T + (AHexer.L4 / 2)));
          AHexer.poly.addPoint((int) (AHexer.L + AHexer.L3 + AHexer.L5),
            (int) (AHexer.T + AHexer.L4 + (AHexer.L4 / 2)));
          AHexer.poly.addPoint((int) (AHexer.L + AHexer.L3),
            (int) (AHexer.T + AHexer.L4 + (AHexer.L4 / 2)));
          g.fillPolygon(AHexer.poly);
        }
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="STAR5">
        if (AHexer.STAR5) {
          g.setColor(Color.blue);
          AHexer.poly = new Polygon();
          //1      poly.addPoint((int) (L + L3), (int) T);
          //2      poly.addPoint((int) (L + L3 + L5), (int) T);
          //3      poly.addPoint((int) (L + L5 + (L3 * 2)), (int) (T + L4));
          //4      poly.addPoint((int) (L + L3 + (int) L5), (int) (T + (L4 * 2)));
          //5      poly.addPoint((int) (L + L3), (int) (T + (L4 * 2)));
          //6      poly.addPoint((int) (L), (int) (T + L4));
          //1      poly.addPoint((int) (L + L3), (int) T);
          AHexer.poly.addPoint((int) (AHexer.L + AHexer.L3), (int) AHexer.T); //1
          AHexer.poly.addPoint((int) (AHexer.L + AHexer.L5 + (AHexer.L3 * 2)),
            (int) (AHexer.T + AHexer.L4)); //3
          AHexer.poly.addPoint((int) (AHexer.L), (int) (AHexer.T + AHexer.L4)); //6
          AHexer.poly.addPoint((int) (AHexer.L + AHexer.L3 + AHexer.L5),
            (int) AHexer.T); //2
          AHexer.poly.addPoint(
            (int) (((AHexer.L + AHexer.L3) + (AHexer.L5 / 2))),
            (int) (AHexer.T + (AHexer.L4 * 2)));
          AHexer.poly.addPoint((int) (AHexer.L + AHexer.L3), (int) AHexer.T);
          g.fillPolygon(AHexer.poly);
        }
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="STAR6">
        if (AHexer.STAR6) {
          g.setColor(Color.red);
          AHexer.poly = new Polygon();
          AHexer.poly.addPoint((int) (AHexer.L + AHexer.L3), (int) AHexer.T);
          AHexer.poly.addPoint((int) (AHexer.L + AHexer.L5 + (AHexer.L3 * 2)),
            (int) (AHexer.T + AHexer.L4));
          AHexer.poly.addPoint((int) (AHexer.L + AHexer.L3),
            (int) (AHexer.T + (AHexer.L4 * 2)));
          g.fillPolygon(AHexer.poly);
          AHexer.poly = new Polygon();
          AHexer.poly.addPoint((int) (AHexer.L), (int) (AHexer.T + AHexer.L4));
          AHexer.poly.addPoint((int) (AHexer.L + AHexer.L3 + AHexer.L5),
            (int) AHexer.T);
          AHexer.poly.addPoint((int) (AHexer.L + AHexer.L3 + (int) AHexer.L5),
            (int) (AHexer.T + (AHexer.L4 * 2)));
          g.fillPolygon(AHexer.poly);
        }
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="DIAG2">
        if (AHexer.DIAG2) {
          g.setColor(Color.red);
          AHexer.x1 = (int) AHexer.L + 0;
          AHexer.y1 = (int) AHexer.T + 0;
          AHexer.x2 = (int) AHexer.L + (int) AHexer.W;
          AHexer.y2 = (int) AHexer.T + (int) AHexer.H * (int) AHexer.M;
          g.drawLine(AHexer.x1, AHexer.y1, AHexer.x2, AHexer.y2);
          AHexer.x1 = (int) AHexer.L + (int) AHexer.W;
          AHexer.y1 = (int) AHexer.T + 0;
          AHexer.x2 = (int) AHexer.L + 0;
          AHexer.y2 = (int) AHexer.T + (int) AHexer.H * (int) AHexer.M;
          g.drawLine(AHexer.x1, AHexer.y1, AHexer.x2, AHexer.y2);
        }
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="DIAG3">
        if (AHexer.DIAG3) {
          g.setColor(Color.black);
          //1,4
          g.drawLine((int) (AHexer.L + AHexer.L3), (int) AHexer.T,
            (int) (AHexer.L + AHexer.L3 + (int) AHexer.L5),
            (int) (AHexer.T + (AHexer.L4 * 2)));
          //2,5
          g.drawLine((int) (AHexer.L + AHexer.L3 + AHexer.L5), (int) AHexer.T,
            (int) (AHexer.L + AHexer.L3), (int) (AHexer.T + (AHexer.L4 * 2)));
          //3,6
          g.drawLine((int) (AHexer.L + AHexer.L5 + (AHexer.L3 * 2)),
            (int) (AHexer.T + AHexer.L4), (int) (AHexer.L),
            (int) (AHexer.T + AHexer.L4));
          g.drawLine((int) (AHexer.L + AHexer.L3), (int) AHexer.T,
            (int) (AHexer.L + AHexer.L5 + (AHexer.L3 * 2)),
            (int) (AHexer.T + AHexer.L4));
          g.drawLine((int) (AHexer.L + AHexer.L5 + (AHexer.L3 * 2)),
            (int) (AHexer.T + AHexer.L4), (int) (AHexer.L + AHexer.L3),
            (int) (AHexer.T + (AHexer.L4 * 2)));
          g.drawLine((int) (AHexer.L + AHexer.L3),
            (int) (AHexer.T + (AHexer.L4 * 2)), (int) (AHexer.L + AHexer.L3),
            (int) AHexer.T);
          g.drawLine((int) (AHexer.L), (int) (AHexer.T + AHexer.L4),
            (int) (AHexer.L + AHexer.L3 + AHexer.L5), (int) AHexer.T);
          g.drawLine((int) (AHexer.L + AHexer.L3 + AHexer.L5), (int) AHexer.T,
            (int) (AHexer.L + AHexer.L3 + (int) AHexer.L5),
            (int) (AHexer.T + (AHexer.L4 * 2)));
          g.drawLine((int) (AHexer.L + AHexer.L3 + (int) AHexer.L5),
            (int) (AHexer.T + (AHexer.L4 * 2)), (int) (AHexer.L),
            (int) (AHexer.T + AHexer.L4));
        }
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="FILE">
        if (AHexer.FILE) {
          File file
            = new File("D:/CYBERBOARD/BMP/Har/Cra/CM=50-ModularCutter-10.bmp");
          try {
            image = ImageIO.read(file);
          } catch (IOException ex) {
          }
          g.drawImage(image,
            (int) (AHexer.L + AHexer.L3 + (AHexer.L5 / 2)) - (image.getWidth()
            / 2),
            (int) (AHexer.T + AHexer.L4) - (image.getHeight() / 2), null);
        }
        //</editor-fold>
        //---------------------------
        //<editor-fold defaultstate="collapsed" desc="NOPRESET">
        AHexer.JUMP = false;
        AHexer.SECTOR = false;
        AHexer.SUBSECTOR = false;
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="JUMP">
        if (AHexer.JUMP) {
          //g.fillPolygon((int)CX,(int)CY);
        }
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="SECTOR">
        if (AHexer.SECTOR) {
        }
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="SUBSECTOR">
        if (AHexer.SUBSECTOR) {
        }
        //</editor-fold>
        //---------------------------
        //<editor-fold defaultstate="collapsed" desc="NOUWP">
        if (AHexer.NOUWP) {
        }
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="HEX">
        if (AHexer.HEX) {
        }
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="NAME">
        if (AHexer.NAME) {
        }
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="UWP">
        if (AHexer.UWP) {
        }
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="PAG">
        if (AHexer.PAG) {
        }
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="POP">
        if (AHexer.POP) {
        }
        //</editor-fold>
        //---------------------------
        //End Extras
        //</editor-fold>
        //---------------------------
        if (!AHexer.square.isSelected()) {
          AHexer.T += (AHexer.M * AHexer.H) + (AHexer.M * AHexer.G);
        } else {
          AHexer.T += (AHexer.M * AHexer.H) + AHexer.G;
        }
        repaint();
      }
      if (!AHexer.square.isSelected()) {
        AHexer.L += ((AHexer.M * AHexer.G) + (AHexer.W - AHexer.L3));
      } else {
        AHexer.L += AHexer.S + AHexer.G;
      }
    }
    //Graphics2D painter2D = (Graphics2D) painter;
  }

  private void addSquare() {
    //setBackground(Color.black);
    AHexer.S = (int) AHexer.jss.getValue() * AHexer.M;
    //T = S;
    //W = S;
    AHexer.sq = new Polygon();
    AHexer.sq.addPoint((int) (AHexer.L), (int) AHexer.T);
    AHexer.sq.addPoint((int) (AHexer.L + AHexer.S), (int) AHexer.T);
    AHexer.sq.addPoint((int) (AHexer.L + AHexer.S), (int) (AHexer.T + AHexer.S));
    AHexer.sq.addPoint((int) (AHexer.L), (int) (AHexer.T + AHexer.S));
  }

  private void drawSquare(Graphics g) {
    g.setColor(Color.white);
    g.fillPolygon(AHexer.sq);
    g.setColor(Color.black);
    Graphics2D painted = (Graphics2D) g;
    BasicStroke pen = new BasicStroke((int) AHexer.jsl.getValue());
    painted.setStroke(pen);
    painted.draw(AHexer.sq);
    repaint();
  }

  public Graphics gc;

  private void addOverlay() {
    int width = HexPanel.WIDTH;
    int height = HexPanel.HEIGHT;

//      gc.setLineWidth(0.25);
//      gc.setStroke(javafx.scene.paint.Color.BLACK);
//      gc.strokeLine(A, h * 2.5, B, h * 0);
//      gc.strokeLine(A, h * 7.5, D, h * 0);
//      gc.strokeLine(A, h * 12.5, D, h * 5);
//      gc.strokeLine(A, h * 17.5, D, h * 10);
//      gc.strokeLine(A, h * 22.5, D, h * 15);
//      gc.strokeLine(B, h * 25, D, h * 20);
//
//      gc.strokeLine(B, h * 0, D, h * 5);
//      gc.strokeLine(A, h * 2.5, D, h * 10);
//      gc.strokeLine(A, h * 7.5, D, h * 15);
//      gc.strokeLine(A, h * 12.5, D, h * 20);
//      gc.strokeLine(A, h * 17.5, D, h * 25);
//      gc.strokeLine(A, h * 22.5, B, h * 25);
//
//      gc.strokeLine(B, h * 0, D, h * 0);
//      gc.strokeLine(B, h * 25, D, h * 25);
//
//      gc.strokeLine(B, h * 0, B, h * 25);
//      gc.strokeLine(C, h * 0, C, h * 25);
  }

}
//---------------------------
