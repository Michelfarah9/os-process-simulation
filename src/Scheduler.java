import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    // Short-term scheduler instance
    private ShortTermSchedular shortTermScheduler;

    // Constructor to initialize the scheduler with a short-term scheduler
    public Scheduler(ShortTermSchedular shortTermScheduler) {
        this.shortTermScheduler = shortTermScheduler;
    }

    // Method to select a process based on the scheduling algorithm number
    public Process selectProcess(int number) {
        switch (number) {
            case 0:
                return shortTermScheduler.scheduleFCFS();
            case 1:
                return shortTermScheduler.scheduleQuantum();
            case 2:
                return shortTermScheduler.scheduleRR();
            default:
                throw new IllegalArgumentException("Invalid scheduling algorithm.");
        }
    }

    // Method to get a list of processes that are ready to be scheduled
    public List<Process> getProcessList() {
        List<Process> accurateList = new ArrayList<>();
        for (Process process : shortTermScheduler.getProcessList()) {
            if (TimeManager.getCurrentTime() >= process.getArrivalTime()) {
                accurateList.add(process);
            }
        }
        return accurateList;
    }

    // Method to calculate the total burst time of all processes
    public int getTotalBurstTime() {
        int total = 0;
        for (Process process : shortTermScheduler.getProcessList()) {
            total += shortTermScheduler.getTotalBurstTime();
        }
        return total;
    }

    // Method to check if there is a process currently in the running state
    public boolean isThereProcessRunning() {
        for (Process process : shortTermScheduler.getProcessList()) {
            if (process.getPcb().getState() == PCB.ProcessState.RUNNING) {
                return true;
            }
        }
        return false;
    }

    // Method to add arriving processes to the scheduling queue
    public void addArrivingProcessesToQueue() {
        shortTermScheduler.addArrivingProcessesToQueue();
    }

    // Method to add arriving processes to the priority queue
    public void addArrivingProcessesToQueuePriority() {
        shortTermScheduler.addArrivingProcessesToQueuePriority();
    }

    // Method to get the quantum value used in scheduling
    public int getQuantum() {
        return shortTermScheduler.getQuantum();
    }

    // Method to enqueue a process into the quantum queue
    public void EnqueueQueueQuantum(Process process) {
        shortTermScheduler.EnqueueQueueQuantum(process);
    }

    // Method to calculate the total burst time for FCFS scheduling
    public int getTotalBurstTimeFCFS() {
        int total = 0;
        for (Process process : shortTermScheduler.getProcessList()) {
            total += process.getCpuBurst();
        }
        return total;
    }

    // Method to peek at the first process in the quantum queue
    public Process peekQueueQuantum() {
        return shortTermScheduler.peekQueueQuantum();
    }
}
