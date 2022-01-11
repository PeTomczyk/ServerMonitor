# Server Monitor - Magnificent!

Simple HTTP service monitor built as a coding challenge.
Program should be run as a deamon or service. Once started it runs forever until it is killed manually (Ctrl+C on Windows).
Program logs server availability every second like this:

> 2022-01-11 20:20:58 [INFO ] [main] com.company.Main: Result: 75% of success.

Logs are displayed on Console/Terminal window and are also created in LogFile.log inside logs directory.
When connection to server is not possible, program log error message like this:

> 2022-01-11 20:20:59 [ERROR] [main] com.company.URLClient: Connection unreachable.


## How to execute

Build program in Maven and run in IDE.   
After building you can also run it from local folder 'target' via java -jar command.

For Windows:
> java.exe -jar .\ServerMonitor-1.0-SNAPSHOT-jar-with-dependencies.jar

For Linux:
> java -jar ServerMonitor-1.0-SNAPSHOT-jar-with-dependencies.jar

## Configuration
You can change monitored server by creating and editing "url.txt" file in main folder.
First line in this file should be an address to monitored server.
When this file is unavailable, program fallback to http://localhost:12345.

Inside the code, there are some hard-coded values, like:

- POOLING_RATE_MS = 1000 (check server every 1 second)
- RESULT_SET_SIZE = 300 (collect results from last 5 minutes)