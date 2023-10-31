import java.util.*;

public class ShortTermSchedular {

    // Data structure to hold processes (use appropriate type based on scheduling algorithm)
    private List<Process> processList;
    private Queue<Process> queueQuantum;
    private static int quantum;
    private PIDManager pidMans;


    public int getQuantum() {
        return quantum;
    }

    //Getter for ProcessList
    public List<Process> getProcessList() {
        return processList;
    }


    // Constructor to initialize the scheduler with processes
    public ShortTermSchedular(List<Process> processList) {
        quantum = 0;
        this.processList = new ArrayList<>();
        this.queueQuantum = new LinkedList<>();

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
        for (Process process : processList) {
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

    // Peek at the front of queueQuantum
    public Process peekQueueQuantum() {
        return queueQuantum.peek();
    }

    // Pop the front of queueQuantum
    public Process popQueueQuantum() {
        return queueQuantum.poll();
    }

    public void EnqueueQueueQuantum(Process process) {
        queueQuantum.add(process);
    }

    public void addArrivingProcessesToQueue() {
        for (Process process : processList) {
            if (TimeManager.getCurrentTime() == process.getArrivalTime() && (process.getState() == PCB.ProcessState.READY)) {
                EnqueueQueueQuantum(process);
            }


        }


    }


    //     RR Scheduling Algorithm
    public Process scheduleQuantum(int quantumParam) {
        quantum = quantumParam;
       return popQueueQuantum();
    }
}




//
//    // Round Robin Scheduling Algorithm
//    public Process scheduleRoundRobin(int quantum) {
//        // Implement Round Robin logic (using a queue and time quantum)
//        // Return the selected process
//    }




