/* From http://java.sun.com/docs/books/tutorial/index.html */


import java.awt.Color;
import java.awt.Graphics;

/**
 * A simple applet class to demonstrate a sort algorithm. You can specify a
 * sorting algorithm using the "alg" attribyte. When you click on the applet, a
 * thread is forked which animates the sorting algorithm.
 * 
 * @author James Gosling
 * @version 1.17f, 10 Apr 1995
 */
public class SortItem extends java.applet.Applet implements Runnable {
  /**
   * The thread that is sorting (or null).
   */
  private Thread kicker;

  /**
   * The array that is being sorted.
   */
  int[] arr;

  /**
   * The high water mark.
   */
  int h1 = -1;

  /**
   * The low water mark.
   */
  int h2 = -1;

  /**
   * The name of the algorithm.
   */
  String algName;

  /**
   * The sorting algorithm (or null).
   */
  SortAlgorithm algorithm;

  /**
   * Fill the array with random numbers from 0..n-1.
   */
  void scramble() {
    int[] a = new int[size().height / 2];
    double f = size().width / (double) a.length;
    for (int i = a.length; --i >= 0;) {
      a[i] = (int) (i * f);
    }
    for (int i = a.length; --i >= 0;) {
      int j = (int) (i * Math.random());
      int t = a[i];
      a[i] = a[j];
      a[j] = t;
    }
    arr = a;
  }

  /**
   * Pause a while.
   * 
   * @see SortAlgorithm
   */
  void pause() {
    pause(-1, -1);
  }

  /**
   * Pause a while, and draw the high water mark.
   * 
   * @see SortAlgorithm
   */
  void pause(int H1) {
    pause(H1, -1);
  }

  /**
   * Pause a while, and draw the low&high water marks.
   * 
   * @see SortAlgorithm
   */
  void pause(int H1, int H2) {
    h1 = H1;
    h2 = H2;
    if (kicker != null) {
      repaint();
    }
    try {
      Thread.sleep(20);
    } catch (InterruptedException e) {
    }
  }

  /**
   * Initialize the applet.
   */
  public void init() {
    String at = getParameter("alg");
    if (at == null) {
      at = "BubbleSort";
    }

    algName = at + "Algorithm";
    scramble();

    setBackground(Color.white);

    resize(100, 100);
  }

  /**
   * Paint the array of numbers as a list of horizontal lines of varying
   * lenghts.
   */
  public void paint(Graphics g) {
    int[] a = arr;
    int y = size().height - 1;

    // Erase old lines
    g.setColor(Color.lightGray);
    for (int i = a.length; --i >= 0; y -= 2) {
      g.drawLine(arr[i], y, size().width, y);
    }

    // Draw new lines
    g.setColor(Color.black);
    y = size().height - 1;
    for (int i = a.length; --i >= 0; y -= 2) {
      g.drawLine(0, y, arr[i], y);
    }

    if (h1 >= 0) {
      g.setColor(Color.red);
      y = h1 * 2 + 1;
      g.drawLine(0, y, size().width, y);
    }
    if (h2 >= 0) {
      g.setColor(Color.blue);
      y = h2 * 2 + 1;
      g.drawLine(0, y, size().width, y);
    }
  }

  /**
   * Update without erasing the background.
   */
  public void update(Graphics g) {
    paint(g);
  }

  /**
   * Run the sorting algorithm. This method is called by class Thread once the
   * sorting algorithm is started.
   * 
   * @see java.lang.Thread#run
   * @see SortItem#mouseUp
   */
  public void run() {
    try {
      if (algorithm == null) {
        algorithm = (SortAlgorithm) Class.forName(algName)
            .newInstance();
        algorithm.setParent(this);
      }
      algorithm.init();
      algorithm.sort(arr);
    } catch (Exception e) {
    }
  }

  /**
   * Stop the applet. Kill any sorting algorithm that is still sorting.
   */
  public synchronized void stop() {
    if (kicker != null) {
      try {
        kicker.stop();
      } catch (IllegalThreadStateException e) {
        // ignore this exception
      }
      kicker = null;
    }
    if (algorithm != null) {
      try {
        algorithm.stop();
      } catch (IllegalThreadStateException e) {
        // ignore this exception
      }
    }
  }

  /**
   * For a Thread to actually do the sorting. This routine makes sure we do
   * not simultaneously start several sorts if the user repeatedly clicks on
   * the sort item. It needs to be synchronoized with the stop() method
   * because they both manipulate the common kicker variable.
   */
  private synchronized void startSort() {
    if (kicker == null || !kicker.isAlive()) {
      scramble();
      repaint();
      kicker = new Thread(this);
      kicker.start();
    }
  }

  /**
   * The user clicked in the applet. Start the clock!
   */
  public boolean mouseUp(java.awt.Event evt, int x, int y) {
    startSort();
    return true;
  }
}


/**
 * A generic sort demonstration algorithm SortAlgorithm.java, Thu Oct 27
 * 10:32:35 1994
 * 
 * @author James Gosling
 * @version 1.6f, 31 Jan 1995
 */

class SortAlgorithm {
  /**
   * The sort item.
   */
  private SortItem parent;

  /**
   * When true stop sorting.
   */
  protected boolean stopRequested = false;

  /**
   * Set the parent.
   */
  public void setParent(SortItem p) {
    parent = p;
  }

  /**
   * Pause for a while.
   */
  protected void pause() throws Exception {
    if (stopRequested) {
      throw new Exception("Sort Algorithm");
    }
    parent.pause(parent.h1, parent.h2);
  }

  /**
   * Pause for a while and mark item 1.
   */
  protected void pause(int H1) throws Exception {
    if (stopRequested) {
      throw new Exception("Sort Algorithm");
    }
    parent.pause(H1, parent.h2);
  }

  /**
   * Pause for a while and mark item 1 & 2.
   */
  protected void pause(int H1, int H2) throws Exception {
    if (stopRequested) {
      throw new Exception("Sort Algorithm");
    }
    parent.pause(H1, H2);
  }

  /**
   * Stop sorting.
   */
  public void stop() {
    stopRequested = true;
  }

  /**
   * Initialize
   */
  public void init() {
    stopRequested = false;
  }

  /**
   * This method will be called to sort an array of integers.
   */
  void sort(int[] a) throws Exception {
  }
}


/**
 * A bubble sort demonstration algorithm
 * SortAlgorithm.java, Thu Oct 27 10:32:35 1994
 *
 * @author James Gosling
 * @version   1.6f, 31 Jan 1995
 */
class BubbleSortAlgorithm extends SortAlgorithm {
    void sort(int[] a) throws Exception {
  for (int i = a.length; --i>=0; )
      for (int j = 0; j<i; j++) {
    if (stopRequested) {
        return;
    }
    if (a[j] > a[j+1]) {
        int T = a[j];
        a[j] = a[j+1];
        a[j+1] = T;
    }
    pause(i,j);
      }
  
    }
}
