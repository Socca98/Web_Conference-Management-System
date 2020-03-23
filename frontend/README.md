==================== Steps to setup Frontend ==========================
setup = when you want to run for the first time

-Open project with IDE (WebStorm, VS Code)
-Go inside folder 'frontend' with the terminal (the IDE may have 'open folder in terminal' on right click).
-Run the command 'npm install' to install folder 'node_modules'.
-Start app with WebStorm, VS Code or with command 'ng serve' in terminal.
-The app will run on the address http://localhost:4200/  -> the default port (4200) for angular projects






=================== Steps to setup Backend ======================
-Open project with IDE (IntelliJ, Eclipse)



#Connect Java code with SQL Server

-From the Start menu, open SQL Server 2014 Configuration Manager.
-Click Protocol for SQLEXPRESS under SQL Server Network Configuration on the left pane. On the right pane, right- click TCP/IP, and select Properties.
-On the TCP/IP Properties dialog box that appears, click the IP Addresses tab.
-Scroll down to locate the IPALL node. Remove any value, if present for TCP Dynamic Ports and specify 1433 for TCP Port.

-Click OK.
-Again right-click TCP/IP on the right pane, and select Enable.
-On the SQL Server Services node, right-click SQL Server (SQLEXPRESS), and select Restart.

This sets up SQL Server to be reached from JDBC code.