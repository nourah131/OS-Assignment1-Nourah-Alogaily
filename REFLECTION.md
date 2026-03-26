# Reflection Questions

## Instructions
Answer the following questions about your learning experience. Each answer should be **at least 5-7 sentences** and show your understanding.

---

## Question 1: What did you learn about multithreading?

**Your Answer: This assignment taught me that multithreading enables the execution of several tasks within a single process while sharing resources and memory. I was better able to comprehend how threads progress through several phases, including New, Runnable, Running, Waiting, and Terminated, because every process in this simulation was represented as a thread. I also discovered that while creating threads in Java is easy with the help of the Thread class, properly controlling thread behavior necessitates an understanding of scheduling logic. One key idea I grasped is that threads don't always finish right away since the scheduler may interrupt them based on the time quantum.Additionally, I saw that join() is crucial since it makes the main scheduler wait until the current thread has completed its allotted quantum before proceeding.
---

## Question 2: What was the most challenging part of this assignment?

**Your Answer:**
Implementing the extra features without altering the original Round-Robin scheduler logic was the most difficult aspect of this job. Because it required tracking timestamps each time a process entered and exited the ready queue, the waiting time functionality was particularly challenging. Due of Java's extreme sensitivity to method structure and class placement, inserting methods in the incorrect location initially resulted in several compilation problems. Determining the precise location to update waiting time in order to maintain accuracy presented another difficulty. Although conceptually simpler, the context switch counter still needed to be positioned correctly prior to thread execution. Overall, the most challenging aspect was gradually adding new capabilities while preserving the original behavior.
[Describe the specific challenge. Was it understanding the code? Implementing a feature? Using Git? Explain what made it difficult and how it relates to the course concepts.]

---

## Question 3: How did you overcome the challenges you faced?

**Your Answer:**
Instead of making all the changes at once, I overcame the difficulties by gradually debugging the code and testing after each feature. Before making any changes, I thoroughly examined the original code to comprehend the scheduler loop. Next, I added each feature one at a time: waiting time came last, context switch counter came second, and priority came first. I carefully examined variable names, method placement, and repeated declarations when compilation errors surfaced. To ensure that methods were outside of main() and inside the appropriate class, I also verified my code structure with Java syntax guidelines. I was able to pinpoint the exact locations of issues by repeatedly running the code. This methodical approach decreased errors and simplified the debugging process.

[Describe your problem-solving approach. Did you read documentation? Ask for help? Debug systematically? What resources did you use? What strategies worked?]

---

## Question 4: How can you apply multithreading concepts in real-world applications?

**Your Answer:**
Because several tasks frequently occur simultaneously, multithreading is commonly employed in many real-world applications. In a web browser, for instance, one thread manages page rendering while another thread works in the background to download content. Mobile applications are another example; in order to keep the application responsive, the user interface runs on one thread while network requests run on another. CPU scheduling in operating systems makes use of thread principles to equitably manage numerous workloads. Threads can be used to divide physics calculations, sound processing, and graphics rendering in games. My understanding of how scheduling choices impact responsiveness and fairness in real-world systems has improved thanks to this project.

[Give specific examples from real applications you use (web browsers, games, mobile apps, etc.). Explain why threads are useful in those scenarios. Connect to what you learned in this assignment.]

---

## Additional Reflections (Optional)

### What would you like to learn more about?
Since synchronization techniques like mutexes, semaphores, and thread safety become crucial when several threads access shared resources, I'd like to learn more about them. Additionally, I'm curious about how operating systems avoid deadlocks and how they occur in actual systems.
[Any topics related to threading, concurrency, or operating systems that you're curious about?]

---

### How confident do you feel about multithreading concepts now?
in the middle.

I now have a far greater understanding of thread lifetime, scheduling fundamentals, and thread generation than I had before beginning this project. I can explain Round-Robin scheduling behavior and read thread-based programming with confidence. I still need more experience with more complex concurrency issues like race conditions and synchronization, though.
[Rate yourself and explain: Beginner / Intermediate / Confident]

[Explain your rating - what do you understand well? What needs more practice?]

---

### Feedback on the assignment
Because it connected theoretical scheduling concepts with real-world Java implementation, this assignment was really helpful. Because each feature represented a true operating-system notion, adding features steadily deepened the learning process. Although difficult, the assignment was beneficial for comprehending the internal workings of schedulers.
[Any comments about the assignment? Was it helpful? Too easy/hard? Suggestions for improvement?]
