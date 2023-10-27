import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {


    PIDManager pidMan = new PIDManager();
        pidMan.allocate_map();

        PCB.setPidManager(pidMan);

        List<Process> processes = InputParser.parseInputFile("C:\\Users\\miche\\IdeaProjects\\os-process-simulation\\src\\input.txt");

        for (Process process : processes) {
            System.out.println("Process ID: " + process.getPid());
            System.out.println("Name: " + process.getName());
            System.out.println("Priority: " + process.getPriority());
            System.out.println("CPU Burst: " + process.getCpuBurst());
            System.out.println("Arrival Time: " + process.getArrivalTime());
            System.out.println("Number of Children: " + process.getN());

            System.out.println("-----------------------");

            // Print children
            List<Process> children = process.getChildren();
            for (Process child : children) {
                System.out.println("Child ID: " + child.getPid());
                System.out.println("Child Name: " + child.getName());
                System.out.println("Child Priority: " + child.getPriority());
                System.out.println("Child CPU Burst: " + child.getCpuBurst());
                System.out.println("Child Arrival Time: " + child.getArrivalTime());
                System.out.println("Number of Children: " + child.getN());
                System.out.println("-----------------------");
            }

            System.out.println("-----------------------");
        }





    }
}