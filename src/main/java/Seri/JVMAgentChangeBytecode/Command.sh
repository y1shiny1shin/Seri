# 打jar包命令(依赖包也需要打一块，将依赖替换为本地路径)
javac -cp ".:/Users/y1shin/.m2/repository/org/javassist/javassist/3.21.0-GA/javassist-3.21.0-GA.jar:$JAVA_HOME/lib/tools.jar" -d . Hello_Transform.java
javac -cp ".:/Users/y1shin/.m2/repository/org/javassist/javassist/3.21.0-GA/javassist-3.21.0-GA.jar:$JAVA_HOME/lib/tools.jar" -d . AgentMain.java
jar cvfm AgentMain.jar META-INF/MANIFEST.MF -C . Seri/JVMAgentChangeBytecode/*
