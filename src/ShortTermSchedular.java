import java.util.Comparator;
import java.util.List;
import java.util.Queue;

public class ShortTermSchedular {

    // Data structure to hold processes (use appropriate type based on scheduling algorithm)
    private List<Process> processList;
    private Queue<Process> processQueue;

    // Constructor to initialize the scheduler with processes
    public ShortTermSchedular(List<Process> processList) {
        this.processList = processList;
        this.processList.sort(Comparator.comparingInt(Process::getArrivalTime));
    }

    // FCFS Scheduling Algorithm
    public Process scheduleFCFS() {
        // Implement FCFS logic
        // Return the selected process

        if (processList.isEmpty()) {
            return null;
        }

        Process processToExecute = processList.get(0);

        if (processToExecute.getState() == PCB.ProcessState.READY) {
            processToExecute.setState(PCB.ProcessState.RUNNING);

            // Check if this process is a parent and if it has created all its children
            if (processToExecute.getN() > 0 && processToExecute.getChildren().size() == processToExecute.getN()) {
                processToExecute.setState(PCB.ProcessState.WAITING);
            }

            return processToExecute;
        }

        return null;

    }

//    // SJF Scheduling Algorithm
//    public Process scheduleSJF() {
//        // Implement SJF logic (sorting processes based on burst time)
//        // Return the selected process
//    }
//
//    // Round Robin Scheduling Algorithm
//    public Process scheduleRoundRobin(int quantum) {
//        // Implement Round Robin logic (using a queue and time quantum)
//        // Return the selected process
//    }
}



