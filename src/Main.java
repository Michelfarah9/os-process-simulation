import java.sql.Time;
import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Scanner myObj = new Scanner(System.in);

        PIDManager pidMan = new PIDManager();
        pidMan.allocate_map();

        PCB.setPidManager(pidMan);

        List<Process> processList = InputParser.parseInputFile("C:\\Users\\miche\\IdeaProjects\\os-process-simulation\\src\\input.txt");

        ShortTermSchedular shortTermSchedular = new ShortTermSchedular(processList);
        Scheduler scheduler = new Scheduler(shortTermSchedular);

        int QuantumValue = 0;
        System.out.println("Which Algorithm would you want to use (Provide also Quantum in the case of Value 1, or by default enter 0): \n0: First-come, first-served (FCFS) \n1 :Round Robin with time quantum \n2: Round Robin Priority based");


        int algorithmValue = Integer.parseInt(myObj.nextLine());// Read user input
        QuantumValue = Integer.parseInt(myObj.nextLine());// Read user input


        if (algorithmValue == 0) {


            while (TimeManager.getCurrentTime() < scheduler.getTotalBurstTime()) {

                Process selectedProcess = scheduler.selectProcess(0, QuantumValue); // Example: FCFS


                if (TimeManager.getCurrentTime() < scheduler.getTotalBurstTime()) {
                    if (TimeManager.getCurrentTime() >= selectedProcess.getArrivalTime()) {
                        System.out.println("Time " + TimeManager.getCurrentTime() + ": Process " + selectedProcess.getName() + " is running.");
                        selectedProcess.setState(PCB.ProcessState.RUNNING);
                        // Update process states, remaining burst time, etc.
                        // Handle any state transitions (e.g., from RUNNING to WAITING)
                        // Print any changes (e.g., process created, process goes to waiting)

                        printProcessStates(scheduler.getProcessList());


                        // Increment time by the burst time of the selected process
                        selectedProcess.setState(PCB.ProcessState.COMPLETED);
                        pidMan.release_pid(selectedProcess.getPid());
                        TimeManager.updateTime(selectedProcess.getCpuBurst());
                    }
                } else {
                    // No process was selected for execution, increment time by 1 unit
                    TimeManager.updateTime(1);
                }
            }
            if (TimeManager.getCurrentTime() == scheduler.getTotalBurstTime()) {
                System.out.println("Time " + scheduler.getTotalBurstTime()+ ":");
                printProcessStates(scheduler.getProcessList());
            }
        }

        //Round Robin without priority
        else if(algorithmValue == 1){

            while (TimeManager.getCurrentTime() <= scheduler.getTotalBurstTime()) {

                Process selectedProcess = scheduler.selectProcess(1, QuantumValue); // Example: RR Quantum


                if (TimeManager.getCurrentTime() < scheduler.getTotalBurstTime()) {
                    if (TimeManager.getCurrentTime() >= selectedProcess.getArrivalTime()) {
                        System.out.println("Time " + TimeManager.getCurrentTime() + ": Process " + selectedProcess.getName() + " is running.");
                        selectedProcess.setState(PCB.ProcessState.RUNNING);
                        // Update process states, remaining burst time, etc.
                        // Handle any state transitions (e.g., from RUNNING to WAITING)
                        // Print any changes (e.g., process created, process goes to waiting)

                        printProcessStates(scheduler.getProcessList());


                        // Increment time by the burst time of the selected process
                        selectedProcess.setState(PCB.ProcessState.COMPLETED);
                        pidMan.release_pid(selectedProcess.getPid());
                        TimeManager.updateTime(selectedProcess.getCpuBurst());
                    }
                } else {
                    // No process was selected for execution, increment time by 1 unit
                    TimeManager.updateTime(1);
                }
            }
            if (TimeManager.getCurrentTime() == scheduler.getTotalBurstTime()) {
                System.out.println("Time " + scheduler.getTotalBurstTime()+ ":");
                printProcessStates(scheduler.getProcessList());
            }
        }




        //Round Robin with Priority
        else if(algorithmValue == 2){



        }
    else {
            System.out.println("Value must be 0,1, or 2");
        }


    }























    //Printing
    public static void printProcessStates(List<Process> processList) {
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

        // Print processes in completed state
        System.out.print("Completed state: ");
        for (Process process : processList) {
            if (process.getState() == PCB.ProcessState.COMPLETED) {
                System.out.print(process.getName() + " ");
            }
        }
        System.out.println();
    }

}