package Model.rooms;

public enum Rooms {
    HALLWAY("/Images/hallway.jpg"),
    TREASURY("/Images/treasure.jpg"),
    CAVERN("/Images/cavern.jpg"),
    LIBRARY("/Images/library.jpg"),
    STAIRS("/Images/stairs.jpg"),
    SHRINE("/Images/altar.jpg"),
    BOSS("/Images/BossRoom.jpg");


    public final String imgPath;

    Rooms(String imgPath) {
        this.imgPath = imgPath;
    }

}
