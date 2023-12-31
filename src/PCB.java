import java.util.ArrayList;
import java.util.List;

public class PCB {

    // Member variables to store process information
    private int pid;
    private final String name;
    private int priority;
    private int cpuBurst;
    private final int arrivalTime;
    private ProcessState state;
    private List<PCB> children;
    private PCB parent;
    private int n;
    private static PIDManager pidManager;

    // Enum representing the possible states of a process
    public enum ProcessState {
        READY, RUNNING, WAITING, COMPLETED
    }

    // Constructor
    public PCB(String name, int priority, int cpuBurst, int arrivalTime, int n) {
        this.name = name;
        this.priority = priority;
        this.cpuBurst = cpuBurst;
        this.arrivalTime = arrivalTime;
        this.state = ProcessState.READY;
        this.children = new ArrayList<>();
        this.parent = null;
        this.n = n;
        this.pid = pidManager.allocate_pid();
    }


    // Getters and setters
    public static void setPidManager(PIDManager manager) {
        pidManager = manager;
    }

    public void setCpuBurst(int cpuBurst) {
        this.cpuBurst = cpuBurst;
    }


    public int getPid() {
        return pid;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public int getCpuBurst() {
        return cpuBurst;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public List<PCB> getChildren() {
        return children;
    }

    public void addChild(PCB child) {
        children.add(child);
        child.setParent(this);

    }

    public PCB getParent() {
        return parent;
    }

    public void setParent(PCB parent) {
        this.parent = parent;
    }

    public ProcessState getState() {
        return state;
    }

    public void setState(ProcessState state) {
        this.state = state;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    // Method to decrement remaining children count
    public void childCompleted(Process completedChild) {
        n--;
        children.remove(completedChild.getPcb()); // Remove the completed child

        if (n == 0) {
            this.setState(ProcessState.READY);
        }
    }
}