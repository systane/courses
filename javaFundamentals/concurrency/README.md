## Basic about concurrency
The jvm can allocate lines of execution (lines of java code) in different processors
(cpus).

**Process:** from the operational system view, a process is the activation of one program;
One program can originate multiple processes, for example a browser can have multiple 
activations and those processes are executed in parallel.
If a computer has multiple processors (cpu), those processors can be executed by
different cpus.
    
Types of process:

    - parallel processes: are processes that are executed independently (they do not share
    commum resources.
    - concurrency processes: are processes that share some kind of resources.

## How create parallel processes in java?

- Thread class: this class has two method start and run. The "start" method activate a Thread object and the "run" method 
defines the behavior of this thread through its lifecycle. You can use extension to create Thread classes.

- Runnable interface: If you need for freedom to create a parallel process in java, you
can use the runnable interface, so in this way you don't need to extend Thread class.
With the runnable interface you can override the run method to define the behavior of
your thread object.

## Threads and the jvm memory
Each thread has its own stack area, so each variable and method created by a Thread
are only visible by its own Thread.
The heap area where the objects are allocated is shared between all Threads, so each
Thread can access a same object (allocated in heap area) from a local reference 
(stack area).

Each Thread has a priority that can be defined from 1 to 10 
(more priority). If all Threads have the same priority, the
CPU will execute every Thread in a round-robin system.

## Synchronizing Threads
We can use a lot of tools from java api to synchronize thread, and one way of doing it is with the reserved word 
"synchronized" to sync the execution of our code. The disadvantage of using this approach is that our code is not going
to be executed in parallel.

## Optimist and pessimist lock
pessimist lock - In each beginning of a transaction you can select and block a record from database until the transaction
is done, so in this way, the record is not going to be read or updated by other process. The trade of is that with this solution
you are going to block other processes.

optimist lock - this strategy doesn't block the record from database, so it's not really a lock. It's more like a way
to identify if the target record was updated by another process while your program is trying update the same record.
In order to identify record update, the optimist lock creates a new column 'version' in the schema of the table, so
each time a record is updated, the version column is incremented. For example, imagine that the program A select the 
version from a record (for example version = 1), then the program A tries to update the columns of this record where 
the version = 1. Just a few seconds before the update command started, the program B had successfully updated the same
record (which was before in version = 1) to version 2. The transaction started from program B had been committed and
after a few seconds the database receives the program A command trying to update the record with version 1. At this 
moment the database is not going to find the record with version 1, forcing you to restart your process. In other words,
now program A is going to select the record again with version updated, execute the command to update the record and try
to commit your transaction again updating the record to version 3 now.

When use optimist or pessimist lock? usually in sql database you can use pessimist lock. Optimist lock are usually used
when you do not have a lot of concurrency or when your database doesn't have support to pessimist lock. Another important
thing to think is how long is your transactions? do you have to execute a lot of processes? if so, then better go to
pessimist lock.


## Deadlock

## Livelock

## Starvation


# Design Patterns:
## CQRS (Command Query Responsibility Segregation)
Is a pattern that allows you work with 2 different databases


## Thread exercises: https://math.hws.edu/javanotes/c12/exercises.html


-----------------------------------




