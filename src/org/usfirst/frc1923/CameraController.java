package org.usfirst.frc1923;

import java.io.BufferedReader;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import java.io.File;
import java.util.ArrayList;
import java.lang.Integer;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class CameraController {

    AxisCamera camera = AxisCamera.getInstance();

    static Location getCenter() {
        BufferedImage bimage = loadFile("image.JPG");
        return calcCenter(getCorners(bimage, toPixels(bimage)));
    }

    static int[][] toPixels(BufferedImage bimage) {
        int h = bimage.getHeight();
        int w = bimage.getWidth();
        int[][] imagearray = new int[w][h];
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                imagearray[x][y] = bimage.getRGB(x, y);
            }
        }
        return imagearray;
    }

    static Location[] getCorners(BufferedImage bimage, int[][] imagearray) {
        Location[] corners = new Location[4];
        int[] x1 = {99999, 99999, 999999, 99999};
        int[] y1 = {99999, 999999, 999999, 99999};
        imagearray = getArea(imagearray);
        for (int x = 0; x < imagearray.length; x++) {
            for (int y = 0; y < imagearray[0].length; y++) {
                if (imagearray[x][y] == 1) {
                    if (getDistance(imagearray.length - 1, 0, x, y) < getDistance(imagearray.length - 1, 0, x1[0], y1[0])) {
                        x1[0] = x;
                        y1[0] = y;
                    }
                    if (getDistance(0, 0, x, y) < getDistance(0, 0, x1[1], y1[1])) {
                        x1[1] = x;
                        y1[1] = y;
                    }
                    if (getDistance(0, imagearray[0].length - 1, x, y) < getDistance(0, imagearray[0].length - 1, x1[2], y1[2])) {
                        x1[2] = x;
                        y1[2] = y;
                    }
                    if (getDistance(imagearray.length - 1, imagearray[0].length - 1, x, y) < getDistance(imagearray.length - 1, imagearray[0].length - 1, x1[3], y1[3])) {
                        x1[3] = x;
                        y1[3] = y;
                    }
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            corners[i] = new Location(x1[i], y1[i]);
            System.out.println("Corners" + i + ": " + x1[i] + "," + y1[i]);
        }
        return corners;
    }

    static double getDistance(int x, int y, int x1, int y1) {
        return Math.sqrt((x - x1) * (x - x1) + (y - y1) * (y - y1));
    }

    static BufferedImage loadFile(String file) {
        BufferedImage bimage;
        bimage = null;
        try {
            bimage = ImageIO.read(new File("image.JPG"));
        } catch (IOException e) {
        }
        return bimage;
    }

    static Location calcCenter(Location[] a) {
        double m1 = (a[1].getY() - a[3].getY()) / (a[1].getX() - a[3].getX() + .000001);
        double m2 = (a[0].getY() - a[2].getY()) / (a[0].getX() - a[2].getX() + .000001);
        int x = (int) ((a[0].getY() - a[1].getY() + m1 * a[1].getX() - m2 * a[0].getX()) / (m1 - m2));
        int y = (int) (m1 * x + a[1].getY() - m1 * a[1].getX());
        Location b = new Location(x, y);
        return b;
    }

    static int[][] getArea(int imagearray[][]) {
        int r, g, b;
        for (int x = 0; x < imagearray.length; x++) {
            for (int y = 0; y < imagearray[0].length; y++) {
                int RGB = imagearray[x][y];
                r = (RGB >> 16) & 255;
                g = (RGB >> 8) & 255;
                b = (RGB) & 255;
                if (r < 20 && g < 20 && b < 20 && x > 0 && y > 0 && x < imagearray.length - 1 && y < imagearray[0].length - 1) {
                    imagearray[x][y] = 999;
                } else {
                    imagearray[x][y] = 0;
                }
            }
        }

        int count = 0;
        for (int x = 0; x < imagearray.length; x++) {
            for (int y = 0; y < imagearray[0].length; y++) {
                if (imagearray[x][y] == 999) {
                    count++;
                    imagearray = scanArea(imagearray, count, x, y);
                }
            }
        }

        int[] counter = new int[count + 1];
        for (int[] row : imagearray) {
            for (int col : row) {
                counter[col]++;
            }
        }

        counter[0] = 0;
        for (int i = 0; i < counter.length; i++) {
            if (counter[i] > counter[0]) {
                counter[0] = counter[i];
                count = i;
            }
        }
        for (int x = 0; x < imagearray.length; x++) {
            for (int y = 0; y < imagearray[0].length; y++) {
                imagearray[x][y] = imagearray[x][y] == count ? 1 : 0;
            }
        }

        return imagearray;
    }

    static int[][] scanArea(int[][] imagearray, int count, int x, int y) {
        int tempx, tempy;
        boolean check;
        ArrayList memx = new ArrayList(); /* saves previous x vals */
        ArrayList memy = new ArrayList();
        memx.add(new Integer(x));
        memy.add(new Integer(y));
        ArrayList checkCounterx = new ArrayList(); /* acts as a counter; ask ivan for details */
        ArrayList checkCountery = new ArrayList();
        checkCounterx.add(new Integer(-1));
        checkCountery.add(new Integer(-1));

        while (memx.size() > 0) {
            check = false;
            tempx = ((Integer) memx.get(memx.size() - 1)).intValue();
            tempy = ((Integer) memy.get(memy.size() - 1)).intValue();
            imagearray[tempx][tempy] = count;
            for (int j = tempx + ((Integer) checkCounterx.get(checkCounterx.size() - 1)).intValue(); j < tempx + 2 && j < imagearray.length; j++) {
                for (int k = tempy + ((Integer) checkCountery.get(checkCountery.size() - 1)).intValue(); k < tempy + 2 && k < imagearray[0].length; k++) {
                    if (imagearray[j][k] == 999) {
                        memx.add(new Integer(j));
                        memy.add(new Integer(k));
                        checkCounterx.set(checkCounterx.size() - 1, new Integer(j - tempx));
                        checkCountery.set(checkCountery.size() - 1, new Integer(k - tempy));
                        checkCounterx.add(new Integer(-1));
                        checkCountery.add(new Integer(-1));
                        check = true;
                        break;
                    } else if (j == tempx + 1 && k == tempy + 1) {
                        memx.remove(memx.size() - 1);
                        memy.remove(memy.size() - 1);
                        checkCounterx.remove(checkCounterx.size() - 1);
                        checkCountery.remove(checkCountery.size() - 1);
                    }
                }
                if (check) {
                    break;
                }
            }
        }

        return imagearray;
    }
}