/**
 * Visualization and HCI
 * Exercise 3: Color Mapping
 * Volker Ahlers, HS Hannover (volker.ahlers@hs-hannover.de)
 */

package vish;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class ColorMapper extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private enum DatasetType {
		MATH_FUNC,
		HAWAII
	}

	private enum ColorTableType {
		DUMMY,
		GRAYSCALE,
		RAINBOW,
		BLUE
	}
		
	private Dataset	dataset;
	private ColorTable colorTable;
	private BufferedImage image;
	
	public static void main(String[] args) {				
		ColorMapper mathFunc;
		try {
			// mathematical function
			mathFunc = new ColorMapper("Math Function", 800, 800,
					DatasetType.MATH_FUNC, ColorTableType.DUMMY, 20);
			mathFunc.setVisible(true);
			// Hawaii elevation data
			mathFunc = new ColorMapper("Hawaii", 800, 800,
					DatasetType.HAWAII, ColorTableType.GRAYSCALE, 50);
			mathFunc.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ColorMapper(String title, int width, int height,
			DatasetType dsType, ColorTableType ctType, int ctSize) throws Exception {
		super(title);
		setSize(width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		switch (dsType) {
		case MATH_FUNC:
			dataset = new MathFuncDataset(50, 50, -1.0, 1.0, -1.0, 1.0);
			break;
		case HAWAII:
			dataset = new HawaiiDataset();
			break;
		default:
			throw new Exception("Unknown DatasetType in ColorMapper().");				
		}
		switch (ctType) {
		case DUMMY:
			colorTable = new DummyTable(ctSize, dataset.getFMin(), dataset.getFMax());
			break;
		// To do: implement color tables

		case GRAYSCALE:
			colorTable = new GrayscaleTable(ctSize, dataset.getFMin(), dataset.getFMax());
			break;

			/*
		case RAINBOW:
			colorTable = new RainbowTable(ctSize, dataset.getFMin(), dataset.getFMax());
			break;
		case BLUE:
			colorTable = new BlueTable(ctSize, dataset.getFMin(), dataset.getFMax());
			break;
		 */			
		default:
			throw new Exception("Unknown ColorTableType in ColorMapper().");				
		}
	}
					
	/**
	 * Create visualization image and show it in frame 
	 */
	@Override
	public void paint(Graphics graphics) {
		int width = getWidth();
		int height = getHeight();
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);				
		int xSize = dataset.getXSize();
		int ySize = dataset.getYSize();
		double xMin = dataset.getXMin();
		double yMin = dataset.getYMin();
		double dx = dataset.getDx();
		double dy = dataset.getDy();
		double dxImage = dx * (xSize - 1) / (double) width;
		double dyImage = dy * (ySize - 1) / (double) height;
		for (int i = 0; i < width; i++) {
			double x = xMin + i * dxImage;
			for (int j = 0; j < height; j++) {
				// mirror data in y direction (image coordinates, origin in upper left corner)
				double y = yMin + (height - 1 - j) * dyImage;
				Color color = colorTable.getColor(dataset.getInterpolatedData(x, y)); 
				image.setRGB(i, j, color.getRGB());
			}
		}
		graphics.drawImage(image, 0, 0, null);
	}

}
