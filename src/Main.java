import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        // Scanner instance for user inputs
        Scanner myObj = new Scanner(System.in);

        // Initialize PIDManager and allocate PID (bitmap)
        PIDManager pidMan = new PIDManager();
        pidMan.allocate_map();

        PCB.setPidManager(pidMan);

        // Parse the input text file and save it as a process list
        List<Process> processList = InputParser.parseInputFile("C:\\Users\\miche\\IdeaProjects\\os-process-simulation\\src\\input.txt");

        // Initialize ShortTermSchedular and scheduler instances
        ShortTermSchedular shortTermSchedular = new ShortTermSchedular(processList);
        Scheduler scheduler = new Scheduler(shortTermSchedular);

        // Initializing Quantum value that will later be changed by user
        int QuantumValue = 0;

        // Print to the user the input options
        System.out.println("Which Algorithm would you want to use (Provide also Quantum in the case of Value 1 or 2, or by default enter 0)\nFirst input is algorithm value\nSecond input is Quantum value: \n0: First-come, first-served (FCFS) \n1 :Round Robin with time quantum \n2: Round Robin Priority based");

        // Read user inputs
        int algorithmValue = Integer.parseInt(myObj.nextLine());
        QuantumValue = Integer.parseInt(myObj.nextLine());


        // First Algorithm First-Come, First-Served (FCFS)
        if (algorithmValue == 0) {

            // To keep going until current time is equal or greater than total burst time of all processes
            while (TimeManager.getCurrentTime() < scheduler.getTotalBurstTimeFCFS()) {

                // To make sure the process arrived at the time
                if (TimeManager.getCurrentTime() < scheduler.getTotalBurstTimeFCFS()) {

                // Save process returned by algorithm implemented
                Process selectedProcess = scheduler.selectProcess(0);



                    // To check if process is arrived
                    if (TimeManager.getCurrentTime() >= selectedProcess.getArrivalTime()) {
                        System.out.println("\nTime " + TimeManager.getCurrentTime() + ": Process " + selectedProcess.getName() + " is running.");
                        selectedProcess.setState(PCB.ProcessState.RUNNING);
                        printProcessStates(scheduler.getProcessList());


                        // Process completes and time is updated
                        selectedProcess.setState(PCB.ProcessState.COMPLETED);
                        pidMan.release_pid(selectedProcess.getPid());
                        TimeManager.updateTime(selectedProcess.getCpuBurst());
                    }
                }
            }
            // To check if all the processes are done
            if (TimeManager.getCurrentTime() == scheduler.getTotalBurstTimeFCFS()) {
                System.out.println("\nTime " + scheduler.getTotalBurstTimeFCFS()+ ":");
                printProcessStates(scheduler.getProcessList());
            }
        }


/////////////////////////////////////////////////////////////////////////////////


        // Second algorithm Round Robin with quantum
        else if(algorithmValue == 1){

            // To keep going until current time is equal or greater than total burst time of all processes
            while (TimeManager.getCurrentTime() < scheduler.getTotalBurstTime()) {

                // Add processes that already arrived at a specific time
                scheduler.addArrivingProcessesToQueue();

                // Save process returned by algorithm implemented
                Process selectedProcess = scheduler.selectProcess(1); // Example: RR Quantum

                // Keep going as long is there is a process being returned
                if (selectedProcess != null) {
                    System.out.println("\nTime " + TimeManager.getCurrentTime() + ": Process " + selectedProcess.getName() + " is running.");
                    selectedProcess.setState(PCB.ProcessState.RUNNING);
                    printProcessStates(scheduler.getProcessList());


                    // Calculate remaining burst time for that process
                    int remainingBurstTime = selectedProcess.getCpuBurst() - QuantumValue;

                    // Check if process is completed or not
                    if (remainingBurstTime <= 0) {
                        selectedProcess.setState(PCB.ProcessState.COMPLETED);
                        pidMan.release_pid(selectedProcess.getPid());
                        TimeManager.updateTime(selectedProcess.getCpuBurst());
                        selectedProcess.setCpuBurst(remainingBurstTime);
                        System.out.println("\nTime " + TimeManager.getCurrentTime() + ": Process " + selectedProcess.getName() + " completed.");
                        printProcessStates(scheduler.getProcessList());
                    }
                    else {
                        TimeManager.updateTime(QuantumValue);
                        scheduler.addArrivingProcessesToQueue();
                        selectedProcess.setCpuBurst(remainingBurstTime);
                        scheduler.EnqueueQueueQuantum(selectedProcess);
                        selectedProcess.setState(PCB.ProcessState.READY);
                    }
                } else {

                    // Update time if no process is in queue
                    TimeManager.updateTime(1);
                }

            }

        }


/////////////////////////////////////////////////////////////////////////////////////


        // Third algorithm Round Robin with priority
        else if(algorithmValue == 2){

            // To keep going until current time is equal or greater than total burst time of all processes
            while (TimeManager.getCurrentTime() < scheduler.getTotalBurstTime()) {

                // Add processes that arrived at the time
                scheduler.addArrivingProcessesToQueuePriority();

                    // Keep going as long as there is a process available in queue
                    while(scheduler.peekQueueQuantum() != null) {

                    // Save process returned by algorithm implemented
                    Process selectedProcess = scheduler.selectProcess(2); // Example: RR Priority

                    // Check if process is available
                    if (selectedProcess != null) {
                        System.out.println("\nTime " + TimeManager.getCurrentTime() + ": Process " + selectedProcess.getName() + " is running.");
                        selectedProcess.setState(PCB.ProcessState.RUNNING);
                        printProcessStates(scheduler.getProcessList());


                        // Calculate remaining burst time for that process
                        int remainingBurstTime = selectedProcess.getCpuBurst() - QuantumValue;

                        // Check if process is completed or not
                        if (remainingBurstTime <= 0) {
                            selectedProcess.setState(PCB.ProcessState.COMPLETED);
                            pidMan.release_pid(selectedProcess.getPid());
                            TimeManager.updateTime(selectedProcess.getCpuBurst());
                            selectedProcess.setCpuBurst(remainingBurstTime);
                            System.out.println("\nTime " + TimeManager.getCurrentTime() + ": Process " + selectedProcess.getName() + " completed.");
                            printProcessStates(scheduler.getProcessList());
                        }
                        else {
                            TimeManager.updateTime(QuantumValue);
                            selectedProcess.setCpuBurst(remainingBurstTime);
                            scheduler.EnqueueQueueQuantum(selectedProcess);
                            selectedProcess.setState(PCB.ProcessState.READY);
                        }
                    }

                    // Update time by 1 if no more processes
                        else {
                            TimeManager.updateTime(1);
                    }
                }
            }

        }
        // Exception handling
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