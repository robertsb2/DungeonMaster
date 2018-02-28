package Model;

public enum Rooms {
    HALLWAY("src/main/resources/Images/hallway.jpg"),
    TREASURY("src/main/resources/Images/treasure.jpg"),
    CAVERN("src/main/resources/Images/cavern.jpg"),
    LIBRARY("src/main/resources/Images/library.jpg"),
    STAIRS("src/main/resources/Images/stairs.jpg"),
    SHRINE("src/main/resources/Images/altar.jpg");


    public final String imgPath;

    Rooms(String imgPath) {
        this.imgPath = imgPath;
    }

}
