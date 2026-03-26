# Development Log

## Instructions
Document your development process as you work on the assignment. Add entries showing:
- What you worked on
- Problems you encountered
- How you solved them
- Time spent

**Requirements**: Minimum 5 entries showing progression over time.

---

## Example Entry Format:


---

## Your Development Log:

### Entry 1 - [March 25, 2026, 3:30 PM]
**What I did**: Started reading the assignment requirements and examined the starter Java code.

**Details**: 
Read the assignment instructions carefully.
Identified the original Round-Robin scheduler structure.
Ran the original code to observe the default output.

**Challenges**: The first challenge was understanding how threads and processes were linked through the queue and map.

**Solution**: I traced the scheduler loop step by step and matched each output line to the code

**Time spent**: 45 minutes

---

### Entry 2 - [March 26,2026,5:00PM]
**What I did**: Implemented Feature 1 (Process Priority).

**Details**: Added priority field inside Process class.
Updated constructor and getter.
Generated random priority values.

**Challenges**: 
Needed to ensure the original constructor still worked correctly after modification.
**Solution**: 
Updated all process creation lines consistently.
**Time spent**: 
50 minutes
---

### Entry 3 - [March 27,2026,2:15PM]
**What I did**: 
 Implemented Feature 2 (Context Switch Counter).
**Details**: 
Added static counter in SchedulerSimulation.
Incremented counter before each thread execution.
Printed final total at the end.
**Challenges**: 
Choosing the correct location to count context switches.
**Solution**: 
Placed the counter immediately before currentThread.start().
**Time spent**: 
 35 minutes
---

### Entry 4 - [March 28, 2026, 6:40 PM]
**What I did**: Worked on Feature 3 (Waiting Time Tracking).

**Details**: 
Added waiting-time fields.
Created methods for queue entry and waiting calculation.

**Challenges**: 

**Solution**: 
Reorganized methods carefully inside the Process class and corrected variable names.
**Time spent**: 
1 HOUER 10 MINUTES
---

### Entry 5 - [March 29, 2026, 4:20 PM]
**What I did**: 
Tested the full program and reviewed final output.
**Details**: 
Verified priority display.
Verified context switch total.
Verified waiting time summary table.
**Challenges**: 
Needed to ensure all features worked together without affecting Round-Robin behavior
**Solution**: 
Repeated execution several times and checked output consistency.
**Time spent**: 
40 minutes
---

### Entry 6 - [Optional - Date and Time]
**What I did**: 

**Details**: 

**Challenges**: 

**Solution**: 

**Time spent**: 

---

## Summary

**Total time spent on assignment**:  4 houer [X hours]

**Most challenging part**:  waiting tnie implementation

**Most interesting learning**: Understanding how scheduling decisions affect process execution order

**What I would do differently next time**: Test every small modification immediately before adding the next feature
