import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputParser {

    // Method that reads a text file and creates processes from it
    public static List<Process> parseInputFile(String filePath) {
        List<Process> processes = new ArrayList<>();

        // To read processes and its attributes
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                String name = parts[0];
                int priority = Integer.parseInt(parts[1]);
                int cpuBurst = Integer.parseInt(parts[2]);
                int arrivalTime = Integer.parseInt(parts[3]);
                int numChildren = Integer.parseInt(parts[4]);
                PCB pcb = new PCB(name, priority, cpuBurst, arrivalTime, numChildren);
                Process process = new Process(pcb);

                // To create a children of the parent processes
                for (int i = 0; i < numChildren; i++) {
                    String childLine = br.readLine();
                    String[] childParts = childLine.split(", ");
                    String childName = childParts[0];
                    int childPriority = Integer.parseInt(childParts[1]);
                    int childCpuBurst = Integer.parseInt(childParts[2]);
                    int childArrivalTime = Integer.parseInt(childParts[3]);
                    int childNumChildren = Integer.parseInt(childParts[4]);
                    PCB pcbChild = new PCB(childName, childPriority, childCpuBurst, childArrivalTime, childNumChildren);
                    Process childProcess = new Process(pcbChild);
                    process.addChild(childProcess);
                }

                processes.add(process);
            }
        }
        // Handle exceptions
        catch (IOException e) {
            e.printStackTrace();
        }

        return processes;
    }
}
