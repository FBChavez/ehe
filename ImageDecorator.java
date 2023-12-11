// Decorator for wrapping an image
public class ImageDecorator implements decorateRoom.Image {
    private decorateRoom.Image imageToDecorate;
    private String decoratorName;

    public ImageDecorator(decorateRoom.Image imageToDecorate, String decoratorName) {
        this.imageToDecorate = imageToDecorate;
        this.decoratorName = decoratorName;
    }

    @Override
    public void display() {
        System.out.println("Wrapping image with: " + decoratorName);
        imageToDecorate.display();
    }
}
