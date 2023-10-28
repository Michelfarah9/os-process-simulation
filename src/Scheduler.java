import java.util.List;

public class Scheduler {
    //3 Algorithms


        private ShortTermSchedular shortTermScheduler;

        public Scheduler(ShortTermSchedular shortTermScheduler) {
            this.shortTermScheduler = shortTermScheduler;
        }

        public Process selectProcess(int number, int quantum) {
            switch (number) {
                case 0:
                    return shortTermScheduler.scheduleFCFS();
//                case 1:
//                    return shortTermScheduler.scheduleSJF();
//                case 2:
//                    return shortTermScheduler.scheduleRoundRobin(quantum);
                default:
                    throw new IllegalArgumentException("Invalid scheduling algorithm.");
            }
        }

    // Iterate over the processes in the scheduler
    public boolean areAllProcessesCompleted() {
        for (Process process : shortTermScheduler.getProcessList()) {
            if (process.getState() != PCB.ProcessState.COMPLETED) {
                return false;
            }
        }
        return true;
    }

    public boolean isTimeStamp(){
        for (Process process : shortTermScheduler.getProcessList()){
            if(process.getArrivalTime() == TimeManager.getCurrentTime()){
                return true;
            }
        }
        return false;
    }

    public List<Process> getProcessList(){
            return shortTermScheduler.getProcessList();
    }

    public int getTotalBurstTime(){
            int total = 0;
            for (Process process : shortTermScheduler.getProcessList()){
                total += process.getCpuBurst();

            }
            return total;
    }



    }

