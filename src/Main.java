import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        List<Process> processes = InputParser.parseInputFile("C:\\Users\\miche\\IdeaProjects\\os-process-simulation\\src\\input.txt");

        for (Process process : processes) {
            System.out.println("Process ID: " + process.getPid());
            System.out.println("Process children: " + process.getChildren());
            System.out.println("Name: " + process.getName());
            System.out.println("Priority: " + process.getPriority());
            System.out.println("CPU Burst: " + process.getCpuBurst());
            System.out.println("Arrival Time: " + process.getArrivalTime());
            System.out.println("Children: " + process.getChildren().size());
            System.out.println("-----------------------");
        }
    }
}