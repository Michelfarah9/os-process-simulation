import java.util.*;

public class ShortTermSchedular {

    // Data structure to hold processes (use appropriate type based on scheduling algorithm)
    private List<Process> processList;
    private List<Process> processListPriority;
    private Queue<Process> queueQuantum;
    private static int quantum;
    private PIDManager pidMans;

    public int getTotalBurstTime() {
        return totalBurstTime;
    }

    public void setTotalBurstTime(int totalBurstTime) {
        this.totalBurstTime = totalBurstTime;
    }

    private int totalBurstTime;  // variable to hold total burst time


    public int getQuantum() {
        return quantum;
    }

    //Getter for ProcessList
    public List<Process> getProcessList() {
        return processList;
    }


    public List<Process> getProcessListPriority() {
        return processListPriority;
    }

    public void setProcessListPriority(List<Process> processListPriority) {
        this.processListPriority = processListPriority;
    }

    // Constructor to initialize the scheduler with processes
    public ShortTermSchedular(List<Process> processList) {
        quantum = 0;
        this.processList = new ArrayList<>();
        this.processListPriority = new ArrayList<>();
        this.queueQuantum = new LinkedList<>();
        this.totalBurstTime = 0;
        for (Process process : processList) {
            addProcessAndChildren(process);
            addProcessAndChildrenPriority(process);
        }


        this.processListPriority.sort(Comparator.comparingInt(Process::getPriority));

        this.processList.sort(Comparator.comparingInt(Process::getArrivalTime));
        for (Process process : this.processList) {
            totalBurstTime += process.getCpuBurst();  // Accumulate burst times
        }
    }

    private void addProcessAndChildren(Process process) {
        this.processList.add(process);
        for (Process child : process.getChildren()) {
            addProcessAndChildren(child);
        }
    }

    private void addProcessAndChildrenPriority(Process process) {
        this.processListPriority.add(process);
        for (Process child : process.getChildren()) {
            addProcessAndChildrenPriority(child);
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
            if (TimeManager.getCurrentTime() >= process.getArrivalTime() && (process.getState() == PCB.ProcessState.READY)) {
                // Check if the process is already in the queue
                boolean alreadyInQueue = queueQuantum.contains(process);

                if (!alreadyInQueue) {
                    EnqueueQueueQuantum(process);
                }
            }
        }
    }

    public void addArrivingProcessesToQueuePriority() {
        int currentPriority = -1; // Initialize with a value that is not a valid priority
        List<Process> processesToRemove = new ArrayList<>();
        for (Process process : processListPriority) {
            if (TimeManager.getCurrentTime() >= process.getArrivalTime() && (process.getState() == PCB.ProcessState.READY)) {
                if (currentPriority == -1) {
                    currentPriority = process.getPriority(); // Initialize currentPriority with the first process's priority
                }

                // Check if the process is already in the queue
                boolean alreadyInQueue = queueQuantum.contains(process);

                if (process.getPriority() == currentPriority && !alreadyInQueue) {
                    EnqueueQueueQuantum(process);
                    processesToRemove.add(process);
                }


            }
        }
        // Remove the processes with the same priority from the list
        processListPriority.removeAll(processesToRemove);
    }



    //     RR Scheduling Algorithm Quantum
    public Process scheduleQuantum() {
        return popQueueQuantum();
    }


    public Process scheduleRR() {
        return popQueueQuantum();
    }


}