public class TimeManager {

    private static int currentTime = 0; // Static variable to represent current time

    public static int getCurrentTime() {
        return currentTime; // Getter to access current time
    }

    public static void setCurrentTime(int time) {
        currentTime = time; // Setter to update current time
    }

    public static void updateTime(int time) {
        currentTime += time; // Method to update current time
    }
}

