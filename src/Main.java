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


            while (TimeManager.getCurrentTime() < scheduler.getTotalBurstTimeFCFS()) {

                if (TimeManager.getCurrentTime() < scheduler.getTotalBurstTimeFCFS()) {
                Process selectedProcess = scheduler.selectProcess(0); // Example: FCFS




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
                }
            }
            if (TimeManager.getCurrentTime() == scheduler.getTotalBurstTimeFCFS()) {
                System.out.println("Time " + scheduler.getTotalBurstTimeFCFS()+ ":");
                printProcessStates(scheduler.getProcessList());
            }
        }





/////////////////////////////////////////////////////////////////////////////////




        //Round Robin with quantum
        else if(algorithmValue == 1){

            while (TimeManager.getCurrentTime() < scheduler.getTotalBurstTime()) {

                scheduler.addArrivingProcessesToQueue();


                Process selectedProcess = scheduler.selectProcess(1); // Example: RR Quantum

                if (selectedProcess != null) {
                    System.out.println("\nTime " + TimeManager.getCurrentTime() + ": Process " + selectedProcess.getName() + " is running.");
                    selectedProcess.setState(PCB.ProcessState.RUNNING);
                    printProcessStates(scheduler.getProcessList());


                    // Adjust burst time and state
                    int remainingBurstTime = selectedProcess.getCpuBurst() - QuantumValue;

                    if (remainingBurstTime <= 0) {
                        selectedProcess.setState(PCB.ProcessState.COMPLETED);
                        TimeManager.updateTime(selectedProcess.getCpuBurst());
                        selectedProcess.setCpuBurst(remainingBurstTime);
                        System.out.println("\nTime " + TimeManager.getCurrentTime() + ": Process " + selectedProcess.getName() + " completed.");
                        printProcessStates(scheduler.getProcessList());
                    } else {
                        TimeManager.updateTime(QuantumValue);
                        scheduler.addArrivingProcessesToQueue();
                        selectedProcess.setCpuBurst(remainingBurstTime);
                        scheduler.EnqueueQueueQuantum(selectedProcess);
                        selectedProcess.setState(PCB.ProcessState.READY);
                    }
                } else {
                    TimeManager.updateTime(1);
                }

            }

        }




/////////////////////////////////////////////////////////////////////////////////////










        //Round Robin with Priority
        else if(algorithmValue == 2){

            while (TimeManager.getCurrentTime() < scheduler.getTotalBurstTime()) {

                scheduler.addArrivingProcessesToQueuePriority();

                    while(scheduler.peekQueueQuantum() != null) {
                    Process selectedProcess = scheduler.selectProcess(2); // Example: RR Priority

                    if (selectedProcess != null) {
                        System.out.println("\nTime " + TimeManager.getCurrentTime() + ": Process " + selectedProcess.getName() + " is running.");
                        selectedProcess.setState(PCB.ProcessState.RUNNING);
                        printProcessStates(scheduler.getProcessList());


                        // Adjust burst time and state
                        int remainingBurstTime = selectedProcess.getCpuBurst() - QuantumValue;

                        if (remainingBurstTime <= 0) {
                            selectedProcess.setState(PCB.ProcessState.COMPLETED);
                            TimeManager.updateTime(selectedProcess.getCpuBurst());
                            selectedProcess.setCpuBurst(remainingBurstTime);
                            System.out.println("\nTime " + TimeManager.getCurrentTime() + ": Process " + selectedProcess.getName() + " completed.");
                            printProcessStates(scheduler.getProcessList());
                        } else {
                            TimeManager.updateTime(QuantumValue);
                            selectedProcess.setCpuBurst(remainingBurstTime);
                            scheduler.EnqueueQueueQuantum(selectedProcess);
                            selectedProcess.setState(PCB.ProcessState.READY);
                        }
                    } else {
                        TimeManager.updateTime(1);
                    }
                }
            }

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