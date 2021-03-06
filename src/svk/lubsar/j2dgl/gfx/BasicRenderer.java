package svk.lubsar.j2dgl.gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import svk.lubsar.j2dgl.gfx.event.GFXCallback;
import svk.lubsar.j2dgl.gfx.event.GFXEvent;
import svk.lubsar.j2dgl.gfx.sprite.AbstractSpriteRenderer;
import svk.lubsar.j2dgl.gfx.sprite.Sprite;
import svk.lubsar.j2dgl.utils.debug.DebugStringBuilder;
import svk.lubsar.j2dgl.utils.debug.MessageHandler;

public class BasicRenderer implements AbstractSpriteRenderer, AbstractPrimitiveRenderer {
	protected RenderBuffer buffer = null;
	protected int[] bufferPixels = null;
	protected boolean optimizedPipeline = false;
	
	protected int bufferWidth;
	protected int bufferHeight;
	
	protected int xOffset = 0;
	protected int yOffset = 0;
	protected boolean ignoreOffset;
	
	protected float[] transformMatrix;
	
	protected Color clearColor;
	protected int fontSize = -1;
	
	protected Graphics2D renderGraphics;
	
	private GFXCallback bufferCallback = (event) -> {
		if(event == GFXEvent.BUFF_PIPE_LOCKED) {
			this.bufferPixels = null;
			this.optimizedPipeline = true;
		} else {
			this.bufferPixels = buffer.pixels;
			this.optimizedPipeline = false;
		}
	};
	
	public BasicRenderer() {
		clearColor = Color.BLACK;
	}
	
	public BasicRenderer(RenderBuffer buffer) {
		this();
		setBuffer(buffer);
	}
	
	@Override
	public RenderBuffer getBuffer() {
		return buffer;
	}
	
	@Override
	public void setBuffer(RenderBuffer buffer) {
		if(this.buffer != null) {
			buffer.removeEventCallback(bufferCallback);
		}
		
		this.buffer = buffer;
		this.bufferHeight = buffer.height;
		this.bufferWidth = buffer.width;
		this.bufferPixels = buffer.pixels;
		this.renderGraphics = buffer.g;
		
		this.buffer.addEventCallback(bufferCallback);
		this.optimizedPipeline = buffer.optimizedPipeline;
	}
	
	@Override
	public int getBufferWidth() {
		return bufferWidth;
	}
	
	@Override
	public int getBufferHeight() {
		return bufferHeight;
	}
	
	public void renderFilledRectangle(int x, int y, int width, int height, Color color) {
		setColor(color);

		if (!ignoreOffset) {
			x -= xOffset;
			y -= yOffset;
		}

		renderGraphics.fillRect(x, y, width, height);
	}

	public void renderFilledRectangle(int x, int y, int width, int height) {
		if (!ignoreOffset) {
			x -= xOffset;
			y -= yOffset;
		}

		renderGraphics.fillRect(x, y, width, height);
	}

	public void renderRectangle(int x, int y, int width, int height, Color color) {
		setColor(color);

		if (!ignoreOffset) {
			x -= xOffset;
			y -= yOffset;
		}

		renderGraphics.drawRect(x, y, width, height);
	}

	public void renderRectangle(int x, int y, int width, int height) {
		if (!ignoreOffset) {
			x -= xOffset;
			y -= yOffset;
		}

		renderGraphics.drawRect(x, y, width, height);
	}

	public void renderImage(BufferedImage img, int x, int y, int width, int height) {
		if (!ignoreOffset) {
			x -= xOffset;
			y -= yOffset;
		}

		renderGraphics.drawImage(img, x, y, width, height, null);
	}

	public void renderImage(BufferedImage img, int x, int y) {
		if (!ignoreOffset) {
			x -= xOffset;
			y -= yOffset;
		}

		renderGraphics.drawImage(img, x, y, null);
	}

	public void renderString(String text, int x, int y, Font font, Color color) {
		setFont(font);
		setColor(color);

		y += fontSize;

		if (!ignoreOffset) {
			x -= xOffset;
			y -= yOffset;
		}

		renderGraphics.drawString(text, x, y);
	}

	public void renderString(String text, int x, int y, Font font) {
		setFont(font);
		y += fontSize;

		if (!ignoreOffset) {
			x -= xOffset;
			y -= yOffset;
		}

		renderGraphics.drawString(text, x, y);
	}

	public void renderString(String text, int x, int y) {
		if(fontSize == -1) {
			fontSize = renderGraphics.getFontMetrics().getHeight();
		}
		
		y += fontSize;

		if (!ignoreOffset) {
			x -= xOffset;
			y -= yOffset;
		}

		renderGraphics.drawString(text, x, y);
	}

	public void renderFilledOval(int x, int y, int width, int height, Color color) {
		setColor(color);

		if (!ignoreOffset) {
			x -= xOffset;
			y -= yOffset;
		}

		renderGraphics.fillOval(x, y, width, height);
	}

	public void renderFilledOval(int x, int y, int width, int height) {
		if (!ignoreOffset) {
			x -= xOffset;
			y -= yOffset;
		}

		renderGraphics.fillOval(x, y, width, height);
	}

	public void renderOval(int x, int y, int width, int height, Color color) {
		setColor(color);

		if (!ignoreOffset) {
			x -= xOffset;
			y -= yOffset;
		}

		renderGraphics.drawOval(x, y, width, height);
	}

	public void renderOval(int x, int y, int width, int height) {
		if (!ignoreOffset) {
			x -= xOffset;
			y -= yOffset;
		}

		renderGraphics.drawOval(x, y, width, height);
	}

	public void renderLine(int x, int y, int xa, int ya, Color color) {
		setColor(color);

		if (!ignoreOffset) {
			x -= xOffset;
			y -= yOffset;
			xa -= xOffset;
			ya -= yOffset;
		}

		renderGraphics.drawLine(x, y, xa, ya);
	}

	public void renderLine(int x, int y, int xa, int ya) {
		if (!ignoreOffset) {
			x -= xOffset;
			y -= yOffset;
			xa -= xOffset;
			ya -= yOffset;
		}

		renderGraphics.drawLine(x, y, xa, ya);
	}

	public void renderFilledArc(int x, int y, int width, int height, int startAngle, int arcAngle, Color color) {
		setColor(color);

		if (!ignoreOffset) {
			x -= xOffset;
			y -= yOffset;
		}

		renderGraphics.fillArc(x, y, width, height, startAngle, arcAngle);
	}

	public void renderFilledArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
		if (!ignoreOffset) {
			x -= xOffset;
			y -= yOffset;
		}

		renderGraphics.fillArc(x, y, width, height, startAngle, arcAngle);
	}

	public void renderArc(int x, int y, int width, int height, int startAngle, int arcAngle, Color color) {
		setColor(color);

		if (!ignoreOffset) {
			x -= xOffset;
			y -= yOffset;
		}

		renderGraphics.drawArc(x, y, width, height, startAngle, arcAngle);
	}

	public void renderArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
		if (!ignoreOffset) {
			x -= xOffset;
			y -= yOffset;
		}

		renderGraphics.drawArc(x, y, width, height, startAngle, arcAngle);
	}

	public void render(Sprite sprite, int xCoord, int yCoord) {
		int[] spritePixels = sprite.getPixels();

		if (!ignoreOffset) {
			xCoord -= xOffset;
			yCoord -= yOffset;
		}

		xCoord--;
		yCoord--;

		int spriteWidth = sprite.getWidth();
		int spriteHeight = sprite.getHeight();

		int pixelX = 0;
		int pixelY = 0;

		for (int y = 0; y < spriteHeight; y++) {
			pixelY = y + yCoord;
			for (int x = 0; x < spriteWidth; x++) {
				pixelX = x + xCoord;

				if (spritePixels[x + y * spriteWidth] >> 24 == 0) {
					continue;
				}

				if (pixelY >= 0 && pixelY < this.bufferHeight && pixelX >= 0 && pixelX < this.bufferWidth) {
					this.bufferPixels[pixelX + pixelY * this.bufferWidth] = spritePixels[x + y * spriteWidth];
				}
			}
		}
	}

	public void render(Sprite sprite, int xCoord, int yCoord, int scale) {
		int[] spritePixels = sprite.getPixels();

		if (!ignoreOffset) {
			xCoord -= xOffset;
			yCoord -= yOffset;
		}

		int spriteWidth = sprite.getWidth();
		int spriteHeight = sprite.getHeight();

		int pixelX = 0;
		int pixelY = 0;

		int scaledPixelX;
		int scaledPixelY;

		for (int y = 0; y < spriteHeight; y++) {
			pixelY = y * scale + yCoord;
			for (int x = 0; x < spriteWidth; x++) {
				pixelX = x * scale + xCoord;

				if (spritePixels[x + y * spriteWidth] >> 24 == 0) {
					continue;
				}

				if (scale > 1) {
					for (int yScaler = 0; yScaler < scale; yScaler++) {
						for (int xScaler = 0; xScaler < scale; xScaler++) {
							scaledPixelY = pixelY + yScaler;
							scaledPixelX = pixelX + xScaler;

							if (scaledPixelY >= 0 && scaledPixelY < this.bufferHeight && scaledPixelX >= 0
									&& scaledPixelX < this.bufferWidth) {
								this.bufferPixels[scaledPixelX + scaledPixelY * this.bufferWidth] = spritePixels[x
										+ y * spriteWidth];
							}
						}
					}
				} else {
					if (pixelY > 0 && pixelY < this.bufferHeight && pixelX > 0 && pixelX < this.bufferWidth) {
						this.bufferPixels[pixelX + pixelY * this.bufferWidth] = spritePixels[x + y * spriteWidth];
					}
				}

			}
		}
	}

	public void clear() {
		if (!optimizedPipeline) {
			int colorValue = clearColor.getRGB();
			for (int i = 0; i < bufferPixels.length; i++) {
				bufferPixels[i] = colorValue;
			}
		} else {
			Color temp = renderGraphics.getColor();
			renderGraphics.setColor(clearColor);
			renderGraphics.fillRect(0, 0, bufferWidth, bufferHeight);
			renderGraphics.setColor(temp);
		}
	}

	public void clear(Color color) {
		if (!optimizedPipeline) {
			int colorValue = color.getRGB();
			for (int i = 0; i < bufferPixels.length; i++) {
				bufferPixels[i] = colorValue;
			}
		} else {
			Color temp = renderGraphics.getColor();
			renderGraphics.setColor(color);
			renderGraphics.fillRect(0, 0, bufferWidth, bufferHeight);
			renderGraphics.setColor(temp);
		}
	}

	public void setColor(Color color) {
		if (color == null) {
			MessageHandler.print(MessageHandler.ERROR, "Screen color cannot be set to null");
			return;
		}

		renderGraphics.setColor(color);
	}

	public void setFont(Font font) {
		if (font == null) {
			MessageHandler.print(MessageHandler.ERROR,
					"Screen font cannot be set to null, font stays set to current font");
			return;
		}

		renderGraphics.setFont(font);
		fontSize = font.getSize();
	}

	public void setFontSize(float size) {
		Font old = renderGraphics.getFont();
		Font newFont = old.deriveFont(size);
		this.fontSize = newFont.getSize();
		renderGraphics.setFont(newFont);
	}

	public void setFontStyle(int style) {
		Font old = renderGraphics.getFont();
		renderGraphics.setFont(old.deriveFont(style));
	}

	public FontMetrics getFontMetrics() {
		return renderGraphics.getFontMetrics();
	}

	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public void setIngoreOffset(boolean ignore) {
		this.ignoreOffset = ignore;
	}

	public void disposeGraphics() {
		renderGraphics.dispose();
	}

	public int getXOffset() {
		return xOffset;
	}

	public int getYOffset() {
		return yOffset;
	}

	public String toString() {
		DebugStringBuilder ret = new DebugStringBuilder();

		ret.appendInstanceInfo(getClass(), hashCode());
		ret.increaseOffset();
		ret.appendPrimitive("bufferWidth", bufferWidth);
		ret.appendPrimitive("bufferHeight", bufferHeight);
		ret.appendPrimitive("ignoreOffset", ignoreOffset);
		ret.appendPrimitive("xOffset", xOffset);
		ret.appendPrimitive("yOffset", yOffset);

		ret.append(clearColor, "clearColor");
		ret.append(renderGraphics, "renderGraphics");
		ret.append(buffer, "renderBuffer");
		ret.append(bufferPixels, "bufferPixels");
		ret.decreaseOffset();
		ret.appendCloseBracket();

		return ret.getString();
	}
	
	@Override
	public void setTransform(float[] matrix, boolean affine) {
		AffineTransform transformObject = null;
		if(affine && matrix.length < 6) {
			throw new RuntimeException("Matrix is too short to be used as an affine transform");
		}
		
		if(affine) {
			 transformObject = new AffineTransform(matrix[0], matrix[3], matrix[1], matrix[4], matrix[2], matrix[5]);
		} else {
			transformObject = new AffineTransform(matrix);
		}
		renderGraphics.setTransform(transformObject);
		
		this.transformMatrix = matrix;
	}
	
	@Override
	public float[] getTransform() {
		return transformMatrix;
	}
	
	public Graphics2D getRenderGraphics() {
		return renderGraphics;
	}
}
