import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    //3 Algorithms


    private ShortTermSchedular shortTermScheduler;

    public Scheduler(ShortTermSchedular shortTermScheduler) {
        this.shortTermScheduler = shortTermScheduler;
    }

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



    public List<Process> getProcessList(){
        List<Process> AccurateList = new ArrayList<>();
        for (Process process: shortTermScheduler.getProcessList()){
            if(TimeManager.getCurrentTime()>= process.getArrivalTime()){
                AccurateList.add(process);
            }
        }
        return AccurateList;

    }

    public int getTotalBurstTime(){
        int total = 0;
        for (Process process : shortTermScheduler.getProcessList()){
            total += shortTermScheduler.getTotalBurstTime();

        }
        return total;
    }

    public boolean isThereProcessRunning(){
        for (Process process : shortTermScheduler.getProcessList()){
            if(process.getPcb().getState() == PCB.ProcessState.RUNNING){
                return true;
            }
        }
        return false;
    }

    public void addArrivingProcessesToQueue(){
        shortTermScheduler.addArrivingProcessesToQueue();
    }

    public void addArrivingProcessesToQueuePriority(){
        shortTermScheduler.addArrivingProcessesToQueuePriority();
    }

    public int getQuantum(){
        return shortTermScheduler.getQuantum();
    }

    public void EnqueueQueueQuantum(Process process){
        shortTermScheduler.EnqueueQueueQuantum(process);
    }


    public int getTotalBurstTimeFCFS() {
        int total = 0;
        for (Process process : shortTermScheduler.getProcessList()){
            total += process.getCpuBurst();

        }
        return total;
    }

    public Process peekQueueQuantum() {
        return shortTermScheduler.peekQueueQuantum();
    }
    }
