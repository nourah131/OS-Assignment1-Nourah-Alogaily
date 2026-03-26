# Assignment Questions

## Instructions
Answer all 4 questions with detailed explanations. Each answer should be **3-5 sentences minimum** and demonstrate your understanding of the concepts.

---

## Question 1: Thread vs Process

**Question**: Explain the difference between a **thread** and a **process**. Why did we use threads in this assignment instead of creating separate processes?

**Your Answer:**
A process is a stand-alone program that runs independently and has its own resources, memory, and system state. Within a process, a thread is a smaller execution unit that shares memory and resources with other threads. Because they require less overhead to create and switch between than individual processes, threads are lighter than processes. Because the scheduler required multiple execution units that could effectively share the same queue, map, and simulation environment, threads were used in this assignment. Communication would become more complicated and demand more operating system resources if distinct processes were used. Since the objective was to mimic CPU scheduling behavior rather than isolate each task as a distinct operating-system process, threads were more appropriate.

[Write your answer here. Consider: What is a process? What is a thread? How do they differ in terms of memory, resources, creation overhead? Why are threads more suitable for this simulation?]

---

## Question 2: Ready Queue Behavior

**Question**: In Round-Robin scheduling, what happens when a process doesn't finish within its time quantum? Explain using an example from your program output.

**Your Answer:**
In Round-Robin scheduling, if a process does not complete within its allocated time quantum, it is temporarily taken off the CPU and placed at the end of the ready queue. This guarantees equity as every process gets CPU time in rotation. The process will pause until all preceding processes in the queue have their opportunity before it executes once more. In this simulation, the scheduler evaluates the remaining time at each quantum and re-inserts incomplete processes with addProcessToQueue(). This behavior is clearly evident in the output when a process has remaining burst time.

Sure! Please provide the text you'd like me to paraphrase, and I'll assist you with that.

P3 finished quantum 4000ms │ Total progress: 49%

Time left: 4087ms

P3 releases CPU for context switching.

P3 included in the ready queue (Priority: 5)

Clarification of illustration:

In this instance, process P3 possessed a burst time exceeding the 4000ms time quantum, preventing it from completing in a single turn. Once one quantum was finished, the time left was 4087ms. Due to its incomplete status, the scheduler placed it at the rear of the ready queue

[Write your answer here. Describe the specific behavior - where does the process go? When does it run again? Give an example from your actual program output showing a process that was re-queued.]

Example from my output:
```
[Paste a relevant snippet from your program output here showing a process being re-queued]
```

**Explanation of example:**
[Explain what's happening in the output snippet you pasted]

---

## Question 3: Thread States

**Question**: A thread can be in different states: **New**, **Runnable**, **Running**, **Waiting**, **Terminated**. Walk through these states for one process (P1) from your simulation.

**Your Answer:**
The thread for P1 progresses through the typical Java thread lifecycle throughout the simulation. Every state manifests at a varying time based on how the scheduler formulates and runs the thread. The scheduler initially creates the thread, subsequently adds it to the queue, then begins execution, awaits completion with join(), and ultimately, the thread ends when the process concludes.




[Write your answer here. For each state, explain when P1 enters that state during the simulation. Use your understanding of the code to trace through the lifecycle.]

1. **New**: [When is P1 in New state?]
P1 enters the New state right after new Thread(process) is instantiated within addProcessToQueue() prior to invoking start().
2. **Runnable**: [When does P1 become Runnable?]
Runnable: P1 transitions to Runnable when it enters the ready queue and is prepared for scheduling.
3. **Running**: [When is P1 Running?]
Running: P1 enters the Running state when the scheduler invokes currentThread.start() and the run() method starts executing
4. **Waiting**: [When/why would P1 be Waiting?]
Waiting: The primary scheduler thread temporarily enters a waiting state during currentThread.join() while it waits for P1 to complete its quantum.
5. **Terminated**: [When is P1 Terminated?]
Terminated: P1 transitions to Terminated once its burst time reaches zero and the thread has fully completed execution
---

## Question 4: Real-World Applications

**Question**: Give **TWO** real-world examples where Round-Robin scheduling with threads would be useful. Explain why this scheduling algorithm works well for those scenarios.

**Your Answer:**
Example 1: Handling Requests on a Web Server

I'm sorry, but it appears that there is no text provided for me to paraphrase. Please provide the text you'd like me to rephrase.

A web server can handle numerous client requests simultaneously, including page loads, images, and user information.

Reasons why Round-Robin is effective in this situation:

Round-Robin scheduling allocates CPU time equitably among active request threads, ensuring that no request experiences excessive waiting times. This enhances responsiveness and stops a single heavy request from hindering others.

Example 2: Tasks for an Interactive Operating System

Description:

An operating system frequently handles numerous background and foreground processes, including file management, browser tabs, and user applications.

Reasons Round-Robin is effective in this context:

Round-Robin works well since every active task receives a brief CPU quantum consistently

### Example 1: [Name of application/scenario]

**Description**: 
[Describe the real-world scenario or application]

**Why Round-Robin works well here**: 
[Explain why Round-Robin scheduling is suitable. Consider fairness, responsiveness, predictability, etc.]

### Example 2: [Name of application/scenario]

**Description**: 
[Describe the real-world scenario or application]

**Why Round-Robin works well here**: 
[Explain why Round-Robin scheduling is suitable. Consider fairness, responsiveness, predictability, etc.]

---

## Summary
Important ideas I grasped from these questions:

.
**Key concepts I understood through these questions:**
Within a process, threads serve as lightweight execution units.
By re-queuing incomplete work, round-robin scheduling ensures fairness.
Java scheduling's execution lifecycle is explained by thread states.


**Concepts I need to study more:**
1. synchronization of several threads.
2.sophisticated thread communication techniques.
  
