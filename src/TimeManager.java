public class TimeManager {

    // Static variable to represent current time
    private static int currentTime = 0;

    // Getter to access current time
    public static int getCurrentTime() {
        return currentTime;
    }

    // Setter to update current time
    public static void setCurrentTime(int time) {
        currentTime = time;
    }

    // Method to update current time
    public static void updateTime(int time) {
        currentTime += time;
    }
}

