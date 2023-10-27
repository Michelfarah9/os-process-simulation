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
    }

