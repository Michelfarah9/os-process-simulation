import java.util.ArrayList;
import java.util.List;

public class Process {
    private PCB pcb; // Reference to the process control block

    // Constructor to initialize the process with a PCB
    public Process(PCB pcb) {
        this.pcb = pcb;
    }

    // Getter for the associated PCB
    public PCB getPcb() {
        return pcb;
    }

    // Methods to interact with the PCB
    public int getPid() {
        return pcb.getPid();
    }

    public String getName() {
        return pcb.getName();
    }

    public int getPriority() {
        return pcb.getPriority();
    }

    public int getCpuBurst() {
        return pcb.getCpuBurst();
    }

    public int getArrivalTime() {
        return pcb.getArrivalTime();
    }

    // Method to set the CPU burst time in the PCB
    public void setCpuBurst(int cpuBurst) {
        this.pcb.setCpuBurst(cpuBurst);
    }

    // Method to get a list of child processes
    public List<Process> getChildren() {
        List<PCB> pcbChildren = pcb.getChildren();
        List<Process> processChildren = new ArrayList<>();

        // Create new Process objects for each child PCB
        for (PCB childPCB : pcbChildren) {
            processChildren.add(new Process(childPCB));
        }

        return processChildren;
    }

    // Method to add a child process to the PCB
    public void addChild(Process child) {
        pcb.addChild(child.getPcb());
    }

    // Method to get the parent process
    public Process getParent() {
        PCB parentPcb = pcb.getParent();
        return new Process(parentPcb);
    }

    // Method to set the parent process in the PCB
    public void setParent(Process parent) {
        pcb.setParent(parent.getPcb());
    }

    // Method to get the state of the process
    public PCB.ProcessState getState() {
        return pcb.getState();
    }

    // Method to set the state of the process in the PCB
    public void setState(PCB.ProcessState state) {
        pcb.setState(state);
    }

    // Method to get the maximum number of children allowed
    public int getN() {
        return pcb.getN();
    }

    // Method to signal that a child process has completed
    public void childCompleted() {
        pcb.childCompleted(this);
    }
}
