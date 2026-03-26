import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

// ANSI Color Codes for enhanced terminal output
class Colors {
    public static final String RESET = "\u001B[0m";
    public static final String BOLD = "\u001B[1m";
    public static final String CYAN = "\u001B[36m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String BLUE = "\u001B[34m";
    public static final String RED = "\u001B[31m";
    public static final String BG_BLUE = "\u001B[44m";
    public static final String BG_GREEN = "\u001B[42m";
    public static final String WHITE = "\u001B[37m";
    public static final String BRIGHT_WHITE = "\u001B[97m";
    public static final String BRIGHT_CYAN = "\u001B[96m";
    public static final String BRIGHT_YELLOW = "\u001B[93m";
    public static final String BRIGHT_GREEN = "\u001B[92m";
}

// Class representing a process that implements Runnable to be run by a thread
class Process implements Runnable {
    private String name;
    private int burstTime;
    private int timeQuantum;
    private int remainingTime;

    // 1 Feature
    // Added process priority
    private int priority;

    // 3 Feature
    // Added waiting time tracking fields
    private long creationTime;
    private long lastQueueEnterTime;
    private long totalWaitingTime;

    public Process(String name, int burstTime, int timeQuantum, int priority) {
        this.name = name;
        this.burstTime = burstTime;
        this.timeQuantum = timeQuantum;
        this.remainingTime = burstTime;
        this.priority = priority;
        this.creationTime = System.currentTimeMillis();
        this.lastQueueEnterTime = this.creationTime;
        this.totalWaitingTime = 0;
    }

    @Override
    public void run() {
        int runTime = Math.min(timeQuantum, remainingTime);

        String quantumBar = createProgressBar(0, 15);
        System.out.println(Colors.BRIGHT_GREEN + "  ▶ " + Colors.BOLD + Colors.CYAN + name +
                Colors.RESET + Colors.GREEN + " executing quantum" + Colors.RESET +
                " [" + runTime + "ms] ");

        try {
            int steps = 5;
            int stepTime = runTime / steps;

            for (int i = 1; i <= steps; i++) {
                Thread.sleep(stepTime);
                int quantumProgress = (i * 100) / steps;
                quantumBar = createProgressBar(quantumProgress, 15);

                System.out.print("\r  " + Colors.YELLOW + "⚡" + Colors.RESET +
                        " Quantum progress: " + quantumBar);
            }
            System.out.println();

        } catch (InterruptedException e) {
            System.out.println(Colors.RED + "\n  ✗ " + name + " was interrupted." + Colors.RESET);
        }

        remainingTime -= runTime;
        int overallProgress = (int) (((double) (burstTime - remainingTime) / burstTime) * 100);
        String overallProgressBar = createProgressBar(overallProgress, 20);

        System.out.println(Colors.YELLOW + "  ⏸ " + Colors.CYAN + name + Colors.RESET +
                " completed quantum " + Colors.BRIGHT_YELLOW + runTime + "ms" + Colors.RESET +
                " │ Overall progress: " + overallProgressBar);
        System.out.println(Colors.MAGENTA + "     Remaining time: " + remainingTime + "ms" + Colors.RESET);

        if (remainingTime > 0) {
            System.out.println(Colors.BLUE + "  ↻ " + Colors.CYAN + name + Colors.RESET +
                    " yields CPU for context switch" + Colors.RESET);
        } else {
            System.out.println(Colors.BRIGHT_GREEN + "  ✓ " + Colors.BOLD + Colors.CYAN + name +
                    Colors.RESET + Colors.BRIGHT_GREEN + " finished execution!" +
                    Colors.RESET);
        }
        System.out.println();
    }

    private String createProgressBar(int progress, int width) {
        int filled = (progress * width) / 100;
        StringBuilder bar = new StringBuilder("[");
        for (int i = 0; i < width; i++) {
            if (i < filled) {
                bar.append(Colors.GREEN + "█" + Colors.RESET);
            } else {
                bar.append(Colors.WHITE + "░" + Colors.RESET);
            }
        }
        bar.append("] ").append(progress).append("%");
        return bar.toString();
    }

    public void runToCompletion() {
        try {
            System.out.println(Colors.BRIGHT_CYAN + "  ⚡ " + Colors.BOLD + Colors.CYAN + name +
                    Colors.RESET + Colors.BRIGHT_CYAN + " is the last process, running to completion" +
                    Colors.RESET + " [" + remainingTime + "ms]");
            Thread.sleep(remainingTime);
            remainingTime = 0;
            System.out.println(Colors.BRIGHT_GREEN + "  ✓ " + Colors.BOLD + Colors.CYAN + name +
                    Colors.RESET + Colors.BRIGHT_GREEN + " finished execution!" + Colors.RESET);
            System.out.println();
        } catch (InterruptedException e) {
            System.out.println(Colors.RED + "  ✗ " + name + " was interrupted." + Colors.RESET);
        }
    }

    public String getName() {
        return name;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public int getPriority() {
        return priority;
    }

    // 3 Feature
    // Recorded when the process enters the ready queue
    public void markEnteredReadyQueue() {
        lastQueueEnterTime = System.currentTimeMillis();
    }

    // 3 Feature
    // Updated total waiting time before execution
    public void updateWaitingTime() {
        totalWaitingTime += System.currentTimeMillis() - lastQueueEnterTime;
    }

    public long getTotalWaitingTime() {
        return totalWaitingTime;
    }

    public boolean isFinished() {
        return remainingTime <= 0;
    }
}

public class SchedulerSimulation {

    // 2 Feature
    // Counted total context switches during process execution
    private static int contextSwitchCount = 0;

    public static void main(String[] args) {
        int studentID = 445052170;
        Random random = new Random(studentID);

        // 3 Feature
        // Stored all processes for final waiting time summary
        List<Process> allProcesses = new ArrayList<>();

        int timeQuantum = 2000 + random.nextInt(4) * 1000;
        int numProcesses = 10 + random.nextInt(11);

        Queue<Thread> processQueue = new LinkedList<>();
        Map<Thread, Process> processMap = new HashMap<>();

        System.out.println("\n" + Colors.BOLD + Colors.BRIGHT_CYAN +
                "╔═══════════════════════════════════════════════════════════════════════════════════════╗" +
                Colors.RESET);
        System.out.println(Colors.BOLD + Colors.BRIGHT_CYAN + "║" + Colors.RESET +
                Colors.BG_BLUE + Colors.BRIGHT_WHITE + Colors.BOLD +
                "                          CPU SCHEDULER SIMULATION                                " +
                Colors.RESET + Colors.BOLD + Colors.BRIGHT_CYAN + "║" + Colors.RESET);
        System.out.println(Colors.BOLD + Colors.BRIGHT_CYAN +
                "╠═══════════════════════════════════════════════════════════════════════════════════════╣" +
                Colors.RESET);
        System.out.println(Colors.BOLD + Colors.BRIGHT_CYAN + "║" + Colors.RESET +
                Colors.YELLOW + "  ⚙ Processes:     " + Colors.RESET + Colors.BRIGHT_YELLOW +
                String.format("%-65s", numProcesses) +
                Colors.BOLD + Colors.BRIGHT_CYAN + "║" + Colors.RESET);
        System.out.println(Colors.BOLD + Colors.BRIGHT_CYAN + "║" + Colors.RESET +
                Colors.YELLOW + "  ⏱ Time Quantum:  " + Colors.RESET + Colors.BRIGHT_YELLOW +
                String.format("%-65s", timeQuantum + "ms") +
                Colors.BOLD + Colors.BRIGHT_CYAN + "║" + Colors.RESET);
        System.out.println(Colors.BOLD + Colors.BRIGHT_CYAN + "║" + Colors.RESET +
                Colors.YELLOW + "  🔑 Student ID:    " + Colors.RESET + Colors.BRIGHT_YELLOW +
                String.format("%-65s", studentID) +
                Colors.BOLD + Colors.BRIGHT_CYAN + "║" + Colors.RESET);
        System.out.println(Colors.BOLD + Colors.BRIGHT_CYAN +
                "╚═══════════════════════════════════════════════════════════════════════════════════════╝" +
                Colors.RESET + "\n");

        for (int i = 1; i <= numProcesses; i++) {
            int burstTime = timeQuantum / 2 + random.nextInt(2 * timeQuantum + 1);

            // 1 Feature
            // Generated random priority for each process
            int priority = random.nextInt(5) + 1;

            Process process = new Process("P" + i, burstTime, timeQuantum, priority);

            // 3 Feature
            // Added process to summary list
            allProcesses.add(process);

            addProcessToQueue(process, processQueue, processMap);
        }

        System.out.println(Colors.BOLD + Colors.GREEN +
                "╔════════════════════════════════════════════════════════════════════════════════╗" +
                Colors.RESET);
        System.out.println(Colors.BOLD + Colors.GREEN + "║" + Colors.RESET +
                Colors.BG_GREEN + Colors.WHITE + Colors.BOLD +
                "                        ▶  SCHEDULER STARTING  ◀                               " +
                Colors.RESET + Colors.BOLD + Colors.GREEN + "║" + Colors.RESET);
        System.out.println(Colors.BOLD + Colors.GREEN +
                "╚════════════════════════════════════════════════════════════════════════════════╝" +
                Colors.RESET + "\n");

        while (!processQueue.isEmpty()) {
            Thread currentThread = processQueue.poll();
            Process process = processMap.get(currentThread);

            System.out.println(Colors.BOLD + Colors.MAGENTA + "┌─ Ready Queue " + "─".repeat(65) + Colors.RESET);
            System.out.print(Colors.MAGENTA + "│ " + Colors.RESET + Colors.BRIGHT_WHITE + "[" + Colors.RESET);

            int queueCount = 0;
            for (Thread thread : processQueue) {
                Process queuedProcess = processMap.get(thread);
                if (queueCount > 0) System.out.print(Colors.WHITE + " → " + Colors.RESET);
                System.out.print(Colors.BRIGHT_CYAN + queuedProcess.getName() + Colors.RESET);
                queueCount++;
            }

            if (queueCount == 0) {
                System.out.print(Colors.YELLOW + "empty" + Colors.RESET);
            }

            System.out.println(Colors.BRIGHT_WHITE + "]" + Colors.RESET);
            System.out.println(Colors.BOLD + Colors.MAGENTA + "└" + "─".repeat(79) + Colors.RESET + "\n");

            // 3 Feature
            // Updated waiting time before the process starts running
            process.updateWaitingTime();

            // 2 Feature
            // Incremented context switch counter before each process starts
            contextSwitchCount++;

            currentThread.start();

            try {
                currentThread.join();
            } catch (InterruptedException e) {
                System.out.println("Main thread interrupted.");
            }

            if (!process.isFinished()) {
                if (!processQueue.isEmpty()) {
                    addProcessToQueue(process, processQueue, processMap);
                } else {
                    System.out.println(Colors.BRIGHT_YELLOW + "  ⚠ " + Colors.CYAN + process.getName() +
                            Colors.RESET + Colors.YELLOW + " is the last process → running to completion" +
                            Colors.RESET);
                    process.runToCompletion();
                }
            }
        }

        System.out.println(Colors.BOLD + Colors.BRIGHT_GREEN +
                "╔════════════════════════════════════════════════════════════════════════════════╗" +
                Colors.RESET);
        System.out.println(Colors.BOLD + Colors.BRIGHT_GREEN + "║" + Colors.RESET +
                Colors.BG_GREEN + Colors.WHITE + Colors.BOLD +
                "                     ✓  ALL PROCESSES COMPLETED  ✓                            " +
                Colors.RESET + Colors.BOLD + Colors.BRIGHT_GREEN + "║" + Colors.RESET);
        System.out.println(Colors.BOLD + Colors.BRIGHT_GREEN +
                "╚════════════════════════════════════════════════════════════════════════════════╝" +
                Colors.RESET + "\n");

        System.out.println(Colors.BRIGHT_YELLOW + "Total context switches: " + contextSwitchCount + Colors.RESET);
        System.out.println();

        // 3 Feature
        // Printed waiting time summary for all processes
        System.out.println(Colors.BOLD + Colors.BRIGHT_CYAN + "Waiting Time Summary" + Colors.RESET);
        System.out.println("--------------------------------------------------");
        System.out.printf("%-12s %-15s %-15s%n", "Process", "Burst Time", "Waiting Time");
        System.out.println("--------------------------------------------------");

        for (Process process : allProcesses) {
            System.out.printf("%-12s %-15d %-15d%n",
                    process.getName(),
                    process.getBurstTime(),
                    process.getTotalWaitingTime());
        }
    }

    public static void addProcessToQueue(Process process, Queue<Thread> processQueue,
                                         Map<Thread, Process> processMap) {

        // 3 Feature
        // Recorded when the process enters the ready queue
        process.markEnteredReadyQueue();

        Thread thread = new Thread(process);
        processQueue.add(thread);
        processMap.put(thread, process);

        System.out.println(Colors.BLUE + "  ➕ " + Colors.BOLD + Colors.CYAN + process.getName() +
                Colors.RESET + Colors.BLUE + " added to ready queue" + Colors.RESET +
                " (Priority: " + Colors.BRIGHT_YELLOW + process.getPriority() + Colors.RESET + ")" +
                " │ Burst time: " + Colors.YELLOW + process.getBurstTime() + "ms" +
                Colors.RESET);
    }
}