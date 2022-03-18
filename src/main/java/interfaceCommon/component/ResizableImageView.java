package interfaceCommon.component;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Une ImageView qui change de taille dynamiquement
 * @author Samuel Demont
 *
 */
public class ResizableImageView extends ImageView
{
	double minsize=40, maxsize=16384;
	
	public ResizableImageView()
    {
		super();
        setPreserveRatio(true);
    }
	public ResizableImageView(Image image){
		super(image);
		setPreserveRatio(true);
	}
	
	public void setMinSize(double size) {
		minsize=size;
	}
	
	public void setMaxSize(double size) {
		maxsize=size;
	}
	
    @Override
    public double minWidth(double height)
    {
        return minsize;
    }

    @Override
    public double prefWidth(double height)
    {
        Image I=getImage();
        if (I==null) return minWidth(height);
        return I.getWidth();
    }

    @Override
    public double maxWidth(double height)
    {
        return maxsize;
    }

    @Override
    public double minHeight(double width)
    {
        return 40;
    }

    @Override
    public double prefHeight(double width)
    {
        Image I=getImage();
        if (I==null) return minHeight(width);
        return I.getHeight();
    }

    @Override
    public double maxHeight(double width)
    {
        return 16384;
    }

    @Override
    public boolean isResizable()
    {
        return true;
    }

    @Override
    public void resize(double width, double height)
    {
        setFitWidth(width);
        setFitHeight(height);
    }
}
