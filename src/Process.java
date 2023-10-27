import java.util.ArrayList;
import java.util.List;

public class Process {
    private PCB pcb; // Reference to the process control block

    public Process(PCB pcb) {
        this.pcb = pcb;
    }

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

    public List<Process> getChildren() {
        List<PCB> pcbChildren = pcb.getChildren();
        List<Process> processChildren = new ArrayList<>();

        for (PCB childPCB : pcbChildren) {
            processChildren.add(new Process(childPCB));
        }

        return processChildren;
    }

    public void addChild(Process child) {
        pcb.addChild(child.getPcb());
    }

    public Process getParent() {
        PCB parentPcb = pcb.getParent();
        return new Process(parentPcb);
    }

    public void setParent(Process parent) {
        pcb.setParent(parent.getPcb());
    }

    public PCB.ProcessState getState() {
        return pcb.getState();
    }

    public void setState(PCB.ProcessState state) {
        pcb.setState(state);
    }

    public int getN() {
        return pcb.getN();
    }

    public void childCompleted() {
        pcb.childCompleted(this);
    }
}
