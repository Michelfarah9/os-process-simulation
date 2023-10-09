import java.util.ArrayList;
import java.util.List;

public class Process {
    private static int nextPID = 1;
    private int pid;
    private String name;
    private int priority;
    private int cpuBurst;
    private int arrivalTime;
    private List<Process> children;
    private Process parent;
    private ProcessState state;

    public enum ProcessState {
        READY, RUNNING, WAITING, COMPLETED
    }

    public Process(String name, int priority, int cpuBurst, int arrivalTime) {
        this.name = name;
        this.priority = priority;
        this.cpuBurst = cpuBurst;
        this.arrivalTime = arrivalTime;
        this.children = new ArrayList<>();
        this.state = ProcessState.READY;
        this.pid = allocatePID();
    }

    private int allocatePID() {
        return nextPID++;
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

    public List<Process> getChildren() {
        return children;
    }

    public void addChild(Process child) {
        children.add(child);
        child.setParent(this);
    }

    public Process getParent() {
        return parent;
    }

    public void setParent(Process parent) {
        this.parent = parent;
    }

    public ProcessState getState() {
        return state;
    }

    public void setState(ProcessState state) {
        this.state = state;
    }


}
