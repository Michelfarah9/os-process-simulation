public class PIDManager {
    private static final int Min_PID = 300;
    private static final int Max_PID = 5000;

    private static int[] bitMap;

    public int allocate_map() {
        bitMap = new int[Max_PID - Min_PID + 1];
        if (bitMap != null) {
            for (int i = 0; i < bitMap.length; i++) {
                bitMap[i] = 0;
            }
            return 1;
        }
        return -1;
    }

    public int allocate_pid() {
        for (int i = 0; i < bitMap.length; i++) {
            if (bitMap[i] == 0) {

                bitMap[i] = 1;
                int pid;
                return pid = i + Min_PID;
            }
        }
        return -1;
    }

    public void release_pid(int pid) {
        if (pid < Min_PID || pid > Max_PID) {
            return;
        }
        int arrayIndex = pid - Min_PID;
        bitMap[arrayIndex] = 0;

    }






}
