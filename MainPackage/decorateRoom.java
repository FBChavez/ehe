package MainPackage;

public class decorateRoom {
    public static void main(String[] args) {
        // Creating a simple image
        Image simpleImage = new SimpleImage("Nature.jpg");

        // Wrapping the simple image with a decorator
        Image wrappedImage = new ImageDecorator(simpleImage, "Border.jpg");

        // Displaying the wrapped image
        wrappedImage.display();
    }
    public interface Image {
        void display();
    }
}

class SimpleImage implements decorateRoom.Image {
    private String imageName;

    public SimpleImage(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public void display() {
        System.out.println("Displaying image: " + imageName);
    }
}



