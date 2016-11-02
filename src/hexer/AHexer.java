/*

 Copyright (C) 2014 Mark Ferguson



 This program is free software: you can redistribute it and/or modify

 it under the terms of the GNU General Public License as published by

 the Free Software Foundation, either version 3 of the License, or

 (at your option) any later version.



 This program is distributed in the hope that it will be useful,

 but WITHOUT ANY WARRANTY; without even the implied warranty of

 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the

 GNU General Public License for more details.



 You should have received a copy of the GNU General Public License

 along with this program.  If not, see <http://www.gnu.org/licenses/>.

 */
package hexer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javafx.event.EventHandler;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Scale;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 <p>
 @author maggot.iiss
 */
@SuppressWarnings("serial")
public class AHexer extends javax.swing.JPanel {

//---------------------------
  //<editor-fold defaultstate="collapsed" desc="Variables">
  public static float H = 0; //Height
  public static float G = 0; //Gap
  public static float M = 0; //Multiplier
  public static float I = 0; //Line Multiplier;
  public static float S = 0; //Square size
//---------------------------
  public static float Cols = 0;  //X
  public static float Rows = 0;  //Y
//---------------------------
  public static float col = 0;  //X
  public static float row = 0;  //Y
//---------------------------
  public static float T = 0; //Top
  public static float L = 0; //Left
  public static float W = 0; //Width
  public static float L3 = 0;  //Line3
  public static float L4 = 0;  //Line4
  public static float L5 = 0;  //Line5
  public static float CX = 0;  //CenterX
  public static float CY = 0;  //CenterY
//---------------------------
  public static Polygon poly = null;
  public static Polygon sq = null;
//---------------------------
  public static JSpinner jsx = new JSpinner(
    new SpinnerNumberModel(5, 1, 100, 1));
  public static JSpinner jsy = new JSpinner(
    new SpinnerNumberModel(5, 1, 100, 1));
  public static JSpinner jsm = new JSpinner(
    new SpinnerNumberModel(1, 1, 100, 1));
  public static JSpinner jsg = new JSpinner(
    new SpinnerNumberModel(0, 0, 100, 1));
  public static JSpinner jsh = new JSpinner(
    new SpinnerNumberModel(20, 1, 1000, 1));
  public static JButton printButton = new JButton("Print");
  public static JSpinner jsl = new JSpinner(
    new SpinnerNumberModel(1, 1, 100, 1));
  public static JSpinner jsize = new JSpinner(
    new SpinnerNumberModel(1, 1, 100, 1));
  public static JCheckBox square = new JCheckBox();
  public static JSpinner jss = new JSpinner(new SpinnerNumberModel(10, 1, 100,
    1));
//---------------------------
  /**
   JFrame
   */
  public static JFrame jf;
//---------------------------
  public static int jump = 13;
  public static int down = jump;
  public static int across = jump;
  public static int width = 0;
  public static int height = 0;
  public static int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
//---------------------------
  public static String string = "string";
  public static boolean NOLINE = false;
  public static boolean LEFT = true;
  public static boolean RIGHT = true;
  public static boolean TOP = true;
  public static boolean BOTTOM = true;
  public static boolean CENTRES = false;
  public static boolean STAR3 = false;
  public static boolean STAR4 = false;
  public static boolean STAR5 = false;
  public static boolean STAR6 = false;
  public static boolean DIAG2 = false;
  public static boolean DIAG3 = false;
  public static boolean FILE = false;
//---------------------------
  public static boolean NOPRESET = false;
  public static boolean JUMP = true;
  public static boolean SUBSECTOR = false;
  public static boolean SECTOR = false;
  public static boolean WORLDMAP = false;
//---------------------------
  public static boolean NOUWP = false;
  public static boolean HEX = false;
  public static boolean NAME = false;
  public static boolean UWP = false;
  public static boolean PAG = false;
  public static boolean POP = false;
//---------------------------
  public static String[] commands = new String[]{
    "Left", "Right", "Top", "Bottom", "Centres", "Star3", "Star4", "Star5",
    "Star6", "Diag2", "Diag3", "File"
  };
  public static int commandsLen = commands.length;
//---------------------------
  public static String[] presets = new String[]{
    "NONE", "Sector", "SubSector", "Jump", "WorldMap"
  };
  public static int presetsLen = presets.length;
//---------------------------
  public static String[] uwp = new String[]{
    "Hex", "Name", "UWP", "PAG", "Pop"
  };
  public static int uwpLen = uwp.length;
//---------------------------
  public static Color[] cols = {
    //Zone
    Color.red, Color.orange, Color.green,
    //Allegiance
    Color.orange, Color.white, Color.cyan, Color.magenta, Color.yellow,
    //Atmosphere
    Color.black, Color.gray, Color.white
//---------------------------
  };
  //</editor-fold>
  static JScrollPane jsp = new JScrollPane();
  static ConPanel cp;
  static HexPanel hp;
  static SouthPanel sp;
//---------------------------

  /**
   <p>
   @param args strings to start
   */
  public static void main(String[] args) {
    jf = new JFrame();
    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jf.setBounds(0, 0, 600, 800);
    cp = new ConPanel();
    jf.getContentPane().add(cp, "North");
    hp = new HexPanel();
    jsp = new JScrollPane(hp);
    jf.getContentPane().add(jsp, "Center");
    sp = new SouthPanel();
    jf.getContentPane().add(sp, "South");
    jf.setVisible(true);
//    gc = hp.getGraphics();
  }
//---------------------------

  /**
   Creates new form Hexer
   */
  public AHexer() {
    initComponents();
  }
//---------------------------

  /**
   @return name of class
   */
  @Override
  public String toString() {
    return "" + getClass().getName() + "[]";
  }

  /**
   This method is called from within the constructor to initialize the form.
   WARNING: Do NOT modify this code. The content of this method is always
   regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 400, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 300, Short.MAX_VALUE)
    );
  }// </editor-fold>//GEN-END:initComponents
  // Variables declaration - do not modify//GEN-BEGIN:variables
  // End of variables declaration//GEN-END:variables
//---------------------------

  public static class HexPrinter implements ActionListener, Printable {

    public HexPrinter() {
      //System.out.print("HexPrinter");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
      //System.out.print("actionPerformed(ActionEvent ae) = " + ae);
      PrinterJob job = PrinterJob.getPrinterJob();
      job.setPrintable(this);
      boolean ok = job.printDialog();
      if (ok) {
        try {
          job.print();
        } catch (PrinterException ex) {
          /*
           The job did not successfully complete
           */
          //System.out.print("OOPS!");
        }
      }
    }

    @Override
    public int print(Graphics grphcs, PageFormat pf, int i) throws
      PrinterException {
      System.out.print("print(Graphics grphcs, PageFormat pf, int i) = "
        + grphcs + " " + pf + " " + i);
      if (i > 0) {
        /*
         We have only one page, and 'page' is zero-based
         */

        return NO_SUCH_PAGE;
      }

      /*
       User (0,0) is typically outside the imageable area, so we must
       translate by the X and Y values in the PageFormat to avoid clipping
       */
      Graphics2D g2d = (Graphics2D) grphcs;
      g2d.translate(pf.getImageableX(), pf.getImageableY());

//      /* Now we perform our rendering */
//      g2d.rotate(50);
//      //grphcs.drawString("Hello world!", 100, 100);
//      g2d.drawString("AHexer", 100, 300);
//      jf.printAll(grphcs);
      jf.printAll(jsp.getGraphics());

      /*
       tell the caller that this page is part of the printed document
       */
      return PAGE_EXISTS;
    }
  }
//---------------------------

  public static class ConPanel extends JPanel {

    public ConPanel() {
      setBackground(Color.gray);

      add(jsh);
      jsh.setValue(40);
      jsh.setToolTipText("jsh=Height");
      jsh.addChangeListener(new ChangeListenerImpl());

      add(jsx);
      jsx.setValue(13);
      jsx.setToolTipText("jsx=Across");
      jsx.addChangeListener(new ChangeListenerImpl());

      add(jsy);
      jsy.setValue(13);
      jsy.setToolTipText("jsy=Down");
      jsy.addChangeListener(new ChangeListenerImpl());

      add(printButton);
      printButton.setToolTipText("printButton=PRINT TO PRINTER");
      printButton.addActionListener(new HexPrinter());

      add(jsm);
      jsm.setValue(1);
      jsm.setToolTipText("jsm=Mult");
      jsm.addChangeListener(new ChangeListenerImpl());

      add(jsg);
      jsg.setValue(0);
      jsg.setToolTipText("jsg=Gap");
      jsg.addChangeListener(new ChangeListenerImpl());

      add(jsl);
      jsl.setValue(1);
      jsl.setToolTipText("jsl=Line");
      jsl.addChangeListener(new ChangeListenerImpl());

      add(square);
      square.setToolTipText("square=Square");
      square.addChangeListener(new ChangeListenerImpl());

      add(jsize);
      jsize.setToolTipText("World Size");
      jsize.setValue(5);

    }

    public class ChangeListenerImpl implements ChangeListener {

      public ChangeListenerImpl() {
      }

      @Override
      public void stateChanged(ChangeEvent ce) {
        ////System.out.print("stateChanged(ChangeEvent ce) = " + ce);
        setupNumbers();
      }
    }

    public static void setupNumbers() {
      //System.out.print("setupNumbers()");
      M = (int) jsm.getValue();
      H = (int) jsh.getValue();
      G = (int) jsg.getValue();
      T = G;
      L = G;
      W = M * (H * 11 / 8);
      L3 = M * (H * 3 / 8);
      L4 = M * (H * 4 / 8);
      L5 = M * (H * 5 / 8);
      CX = M * (H / 2);
      CY = M * (W / 2);
      Cols = (int) jsx.getValue();
      Rows = (int) jsy.getValue();
      width = (int) (((Cols * ((G * M) + L3 + L5)) + L3) + (G * M));
      height = (int) (((Rows + 0.5) * ((G + H) * M) + (G * M)));
    }
  }
//---------------------------

  public static class SouthPanel extends JPanel {

    public static String CRLF = System.lineSeparator();

    public static int h;
    public static int w;
    public static int pagewidth;
    public static int worldsize;
    public static int pageheight;
    public static int A;
    public static int B;
    public static int C;
    public static int D;

    public void drawBorder(GraphicsContext gc) {
      gc.setStroke(javafx.scene.paint.Color.BLACK);
      gc.strokeLine(0, 0, pagewidth * 3 / 4, 0);
      gc.strokeLine(0, 0, 0, pageheight);
      gc.strokeLine(D, 0, D, pageheight);
      gc.strokeLine(0, pageheight, D, pageheight);
    }

    public void drawTriangles(GraphicsContext gc) {
//      gc = HexPanel.getGraphics();

      gc.setLineWidth(0.25);
      gc.setStroke(javafx.scene.paint.Color.BLACK);
      gc.strokeLine(A, h * 2.5, B, h * 0);
      gc.strokeLine(A, h * 7.5, D, h * 0);
      gc.strokeLine(A, h * 12.5, D, h * 5);
      gc.strokeLine(A, h * 17.5, D, h * 10);
      gc.strokeLine(A, h * 22.5, D, h * 15);
      gc.strokeLine(B, h * 25, D, h * 20);

      gc.strokeLine(B, h * 0, D, h * 5);
      gc.strokeLine(A, h * 2.5, D, h * 10);
      gc.strokeLine(A, h * 7.5, D, h * 15);
      gc.strokeLine(A, h * 12.5, D, h * 20);
      gc.strokeLine(A, h * 17.5, D, h * 25);
      gc.strokeLine(A, h * 22.5, B, h * 25);

      gc.strokeLine(B, h * 0, D, h * 0);
      gc.strokeLine(B, h * 25, D, h * 25);

      gc.strokeLine(B, h * 0, B, h * 25);
      gc.strokeLine(C, h * 0, C, h * 25);

    }

    public void drawHexes() {
    }
    public int height;
    public int l3;
    public int l4;
    public int l5;

    public class EventHandlerImpl implements
      EventHandler<javafx.event.ActionEvent> {

      public EventHandlerImpl() {
      }

      @Override
      public void handle(javafx.event.ActionEvent event) {
        Display();
        System.out.println(event.toString());
        if (event.toString().contains("ClickCount=2")) {
          clear();
        }
      }

      public void Display() {
        drawHexes();
      }

      public void clear() {
        System.out.println("Clear");
      }

    }

    public void print(final Node node) {
      Printer printer = Printer.getDefaultPrinter();
      PageLayout pageLayout = printer.createPageLayout(Paper.A4,
        PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);
      double scaleX = pageLayout.getPrintableWidth() / node.getBoundsInParent()
        .getWidth();
      double scaleY = pageLayout.getPrintableHeight() / node.getBoundsInParent()
        .getHeight();
      node.getTransforms().add(new Scale(scaleX, scaleY));

      javafx.print.PrinterJob job = javafx.print.PrinterJob.createPrinterJob();
      if (job != null) {
        boolean success = job.printPage(node);
        if (success) {
          job.endJob();
        }
      }
    }

    public SouthPanel() {
      setBackground(Color.gray);
      //----------------------------------
      final JComboBox<String> jl1 = new JComboBox<>(commands);
      jl1.setToolTipText("jl1=Commands");
      jl1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
          //System.out.print("jl1 Change to " + jl1.getModel().getSelectedItem().toString());
          Lines(jl1.getModel().getSelectedItem().toString());
        }
      });
      add(jl1);
      //----------------------------------
      JButton jb1 = new JButton("Show");
      jb1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
          showBools();
        }
      });
      add(jb1);
      //----------------------------------
      final JComboBox<String> jl2 = new JComboBox<>(presets);
      jl2.setToolTipText("jl2=Presets");
      jl2.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
          //System.out.print("jl2 Change to " + jl2.getModel().getSelectedItem().toString());
          Presets(jl2.getModel().getSelectedItem().toString());
        }
      });
      jl2.setSelectedIndex(0);
      add(jl2);
      //----------------------------------
      final JComboBox<String> jl3 = new JComboBox<>(uwp);
      jl3.setToolTipText("jl3=UWP");
      jl3.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
          //System.out.print("jl3 Change to " + jl3.getModel().getSelectedItem().toString());
          UWP(jl3.getModel().getSelectedItem().toString());
        }
      });
      add(jl3);
      //----------------------------------
    }

    public static void Lines(String thestring) {
      ////System.out.print("Lines = " + string);
      string = thestring;
      switch (thestring) {
        case "NONE":
          NOLINE = !NOLINE;
          break;
        case "Left":
          LEFT = !LEFT;
          break;
        case "Right":
          RIGHT = !RIGHT;
          break;
        case "Top":
          TOP = !TOP;
          break;
        case "Bottom":
          BOTTOM = !BOTTOM;
          break;
        case "Centres":
          CENTRES = !CENTRES;
          break;
        case "Star3":
          STAR3 = !STAR3;
          break;
        case "Star4":
          STAR4 = !STAR4;
          break;
        case "Star5":
          STAR5 = !STAR5;
          break;
        case "Star6":
          STAR6 = !STAR6;
          break;
        case "Diag2":
          DIAG2 = !DIAG2;
          break;
        case "Diag3":
          DIAG3 = !DIAG3;
          break;
        case "File":
          FILE = !FILE;
          break;
        default: //<editor-fold defaultstate="collapsed" desc="JOP">
        {
          String s = "";
          s += "" + CRLF;
          s += "" + CRLF;
          //        JTextArea jta = new JTextArea(s, 50, 80);
          //        JScrollPane jsp = new JScrollPane(jta);
          //        JOptionPane.showMessageDialog(null, jsp);
        }
        //</editor-fold>
        break;
      }
      //showBools();
    }

    public void Presets(String thestring) {
      ////System.out.print("Presets = " + string);
      string = thestring;
      switch (thestring) {
        case "NONE":
          NOPRESET = !NOPRESET;
          JUMP = false;
          SECTOR = false;
          SUBSECTOR = false;
          jsh.setValue(20);
          jsx.setValue(10);
          jsy.setValue(10);
          jsm.setValue(1);
          jsg.setValue(0);
          NOPRESET = false;
          break;
        case "Jump":
          JUMP = !JUMP;
          jsh.setValue(40);
          jsx.setValue(13);
          jsy.setValue(13);
          jsm.setValue(1);
          jsg.setValue(0);
          JUMP = false;
          break;
        case "Sector":
          SECTOR = !SECTOR;
          jsh.setValue(13);
          jsx.setValue(32);
          jsy.setValue(40);
          jsm.setValue(1);
          jsg.setValue(0);
          SECTOR = false;
          break;
        case "SubSector":
          SUBSECTOR = !SUBSECTOR;
          jsh.setValue(50);
          jsx.setValue(8);
          jsy.setValue(10);
          jsm.setValue(1);
          jsg.setValue(0);
          SUBSECTOR = false;
          break;
        case "WorldMap":
          WORLDMAP = !WORLDMAP;
          jsh.setValue(20);
          jsx.setValue(16);
          jsy.setValue(26);
          jsm.setValue(1);
          jsg.setValue(0);

          addWorldOverlay();
          WORLDMAP = false;
          break;
        default: //System.out.print("Incorrect reply = " + thestring);
        //System.out.print("Left = " + LEFT);
        //<editor-fold defaultstate="collapsed" desc="JOP">
        {
          String s = "";
          s += "" + CRLF;
          s += "" + CRLF;
//        JTextArea jta = new JTextArea(s, 50, 80);
//        JScrollPane jsp = new JScrollPane(jta);
//        JOptionPane.showMessageDialog(null, jsp);
        }
        //</editor-fold>
        break;
      }
    }

    public static void UWP(String thestring) {
      ////System.out.print("UWP = " + string);
      string = thestring;
      switch (thestring) {
        case "NONE":
          NOUWP = !NOUWP;
          break;
        case "Hex":
          HEX = !HEX;
          break;
        case "Name":
          NAME = !NAME;
          break;
        case "UWP":
          UWP = !UWP;
          break;
        case "PAG":
          PAG = !PAG;
          break;
        case "Pop":
          POP = !POP;
          break;
        default: //System.out.print("Incorrect reply = " + thestring);
        //<editor-fold defaultstate="collapsed" desc="JOP">
        {
          String s = "";
          s += "" + CRLF;
          s += "" + CRLF;
//        JTextArea jta = new JTextArea(s, 50, 80);
//        JScrollPane jsp = new JScrollPane(jta);
//        JOptionPane.showMessageDialog(null, jsp);
        }
        //</editor-fold>
        break;
      }
    }

    public static void showBools() {
      //System.out.print("showBools()");
      int lot = commands.length + presets.length + uwp.length;
      String vName = "";
      for (int i = 0; i < lot; i++) {
        if (i < commands.length) {
          vName = commands[i];
        } else if (i >= commands.length && i < commands.length + presets.length) {
          vName = presets[i - commands.length];
        } else {
          vName = uwp[i - (commands.length + presets.length)];
        }
      }
      //System.out.print("vName = " + vName);
      //System.out.print("string = " + string);
//      H = (int) jsh.getValue();
      //System.out.print("H = " + (int) jsh.getValue());
//      M = (int) jsmult.getValue();
      //System.out.print("M = " + (int) jsm.getValue());
//      G = M * (int) jsgap.getValue();
      //System.out.print("G = " + (int) jsg.getValue());
//      //T = M * G;
      //System.out.print("T = " + T);
//      //L = M * G;
      //System.out.print("L = " + L);
//      W = M * (H * 11 / 8);
      //System.out.print("W = " + W);
//      L3 = M * (H * 3 / 8);
      //System.out.print("L3 = " + L3);
//      L4 = M * (H * 4 / 8);
      //System.out.print("L4 = " + L4);
//      L5 = M * (H * 5 / 8);
      //System.out.print("L5 = " + L5);
//      CX = M * (H / 2);
      //System.out.print("CX = " + CX);
//      CY = M * (W / 2);
      //System.out.print("CY = " + CY);
//      Cols = (int) jsx.getValue();
      //System.out.print("Cols = " + Cols);
//      Rows = (int) jsy.getValue();
      //System.out.print("Rows = " + Rows);
//      width = (int) (((Cols * (G + L3 + L5 + L3)) * M)+(2*G*M)) ;
      //System.out.print("Width = " + width);
//      height = (int) (((Rows + 0.5)*(G+H)* M + (2*G*M)));
      //System.out.print("Height = " + height);

    }

    private void addWorldOverlay() {
      //<editor-fold defaultstate="collapsed" desc="IFD">
      {
        String s = "";
        boolean DEBUG = true;  // true or false;
        if (DEBUG) { // true or false
          s += "//////////////////////////////////////////////" + CRLF;
          s += "\t" + Thread.currentThread().getStackTrace()[1]
            .getMethodName() + CRLF;
          System.out.print(s);
        }
      }
      //</editor-fold>
      drawTriangles();
    }

//      private int h;
//  private int w;
//  private int pagewidth;
//  private int worldsize;
//  private int pageheight;
    private GraphicsContext gc;
//  private int A;
//  private int B;
//  private int C;
//  private int D;
//
//  private void drawBorder(GraphicsContext gc) {
//    gc.setStroke(javafx.scene.paint.Color.BLACK);
//    gc.strokeLine(0, 0, pagewidth * 3 / 4, 0);
//    gc.strokeLine(0, 0, 0, pageheight);
//    gc.strokeLine(D, 0, D, pageheight);
//    gc.strokeLine(0, pageheight, D, pageheight);
//  }
//

    private void drawTriangles() {
//      gc = HexPanel.getGraphics();
      gc.fillText("Hello", 10, 10);
      gc.setLineWidth(0.25);
      gc.fillText("HELLO", 10, 10);
      float hx = (2 * l3) + l5 / 2;
      float hy = l4;
      int p = pageheight;
      gc.setStroke(javafx.scene.paint.Color.BLACK);
      gc.strokeLine(A, (p / 10), B, h * 0);//10
      gc.strokeLine(A, (p / 10) * 3, D, h * 0);//30
      gc.strokeLine(A, (p / 10) * 5, D, h * 5);//50
      gc.strokeLine(A, (p / 10) * 7, D, h * 10);//70
      gc.strokeLine(A, (p / 10) * 9, D, h * 15);//90
      gc.strokeLine(B, h, D, (p / 10) * 2);//100

      gc.strokeLine(B, h * 0, D, h * 5);
      gc.strokeLine(A, h * 2.5, D, h * 10);
      gc.strokeLine(A, h * 7.5, D, h * 15);
      gc.strokeLine(A, h * 12.5, D, h * 20);
      gc.strokeLine(A, h * 17.5, D, h * 25);
      gc.strokeLine(A, h * 22.5, B, h * 25);

      gc.strokeLine(B, h * 0, D, h * 0);
      gc.strokeLine(B, h * 25, D, h * 25);

      gc.strokeLine(B, h * 0, B, h * 25);
      gc.strokeLine(C, h * 0, C, h * 25);

    }

//  private void drawHexes() {
//
//  }
//  private int height;
//  private int l3;
//  private int l4;
//  private int l5;
  }
//---------------------------
}
