import java.util.*;

public class ShortTermSchedular {

    // Data structure to hold processes (use appropriate type based on scheduling algorithm)
    private List<Process> processList;
    private Queue<Process> queueQuantum;
    private int quantum;

    //Getter for ProcessList
    public List<Process> getProcessList() {
        return processList;
    }


    // Constructor to initialize the scheduler with processes
    public ShortTermSchedular(List<Process> processList) {
        this.quantum = 0;
        this.processList = new ArrayList<>();

        for (Process process : processList) {
            addProcessAndChildren(process);
        }

        this.processList.sort(Comparator.comparingInt(Process::getArrivalTime));
    }

    private void addProcessAndChildren(Process process) {
        this.processList.add(process);
        for (Process child : process.getChildren()) {
            addProcessAndChildren(child);
        }
    }

    // FCFS Scheduling Algorithm
    public Process scheduleFCFS() {
        // Implement FCFS logic
        // Return the selected process
        for (Process process : processList){
            if (process.getState() == PCB.ProcessState.READY) {
                return process;
            }

        }
        return null;
        }


    // Getter for queueQuantum
    public Queue<Process> getQueueQuantum() {
        return queueQuantum;
    }

    // Setter for queueQuantum
    public void setQueueQuantum(Queue<Process> queueQuantum) {
        this.queueQuantum = queueQuantum;
    }




//    // RR Scheduling Algorithm
//    public Process scheduleQuantum(int quantum) {
//        this.quantum = quantum;
//        queueQuantum = new LinkedList<>(processList);
//
//    }
//
//    // Round Robin Scheduling Algorithm
//    public Process scheduleRoundRobin(int quantum) {
//        // Implement Round Robin logic (using a queue and time quantum)
//        // Return the selected process
//    }
}



