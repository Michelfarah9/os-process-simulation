import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {


    PIDManager pidMan = new PIDManager();
        pidMan.allocate_map();

        PCB.setPidManager(pidMan);

        List<Process> processList  = InputParser.parseInputFile("C:\\Users\\miche\\IdeaProjects\\os-process-simulation\\src\\input.txt");

        ShortTermSchedular shortTermSchedular = new ShortTermSchedular(processList);
        Scheduler scheduler = new Scheduler(shortTermSchedular);

        int currentTime = 0;
        int quantum = 10; // Example value, set as per your requirements

        while (true) {
            boolean allProcessesCompleted = true;

            // Check if any processes are still in the ready state
            for (Process process : processList) {
                if (process.getState() != PCB.ProcessState.COMPLETED) {
                    allProcessesCompleted = false;
                    break;
                }
            }

            if (allProcessesCompleted) {
                break;
            }

            Process selectedProcess = scheduler.selectProcess(0, quantum); // Example: FCFS

            if (selectedProcess != null) {
                System.out.println("Time " + currentTime + ": Process " + selectedProcess.getName() + " is running.");
                // Update process states, remaining burst time, etc.
                // Handle any state transitions (e.g., from RUNNING to WAITING)
                // Print any changes (e.g., process created, process goes to waiting)

                // Print processes in ready state
                System.out.print("Ready state: ");
                for (Process process : processList) {
                    if (process.getState() == PCB.ProcessState.READY) {
                        System.out.print(process.getName() + " ");
                    }
                }
                System.out.println();

                // Print processes in waiting state
                System.out.print("Waiting state: ");
                for (Process process : processList) {
                    if (process.getState() == PCB.ProcessState.WAITING) {
                        System.out.print(process.getName() + " ");
                    }
                }
                System.out.println();

                // Increment time by the burst time of the selected process
                currentTime += selectedProcess.getCpuBurst();
            } else {
                // No process was selected for execution, increment time by 1 unit
                currentTime++;
            }
        }}}




//        for (Process process : processes) {
//            System.out.println("Process ID: " + process.getPid());
//            System.out.println("Name: " + process.getName());
//            System.out.println("Priority: " + process.getPriority());
//            System.out.println("CPU Burst: " + process.getCpuBurst());
//            System.out.println("Arrival Time: " + process.getArrivalTime());
//            System.out.println("Number of Children: " + process.getN());
//
//            System.out.println("-----------------------");
//
//            // Print children
//            List<Process> children = process.getChildren();
//            for (Process child : children) {
//                System.out.println("Child ID: " + child.getPid());
//                System.out.println("Child Name: " + child.getName());
//                System.out.println("Child Priority: " + child.getPriority());
//                System.out.println("Child CPU Burst: " + child.getCpuBurst());
//                System.out.println("Child Arrival Time: " + child.getArrivalTime());
//                System.out.println("Number of Children: " + child.getN());
//                System.out.println("-----------------------");
//            }
//
//            System.out.println("-----------------------");
//        }


