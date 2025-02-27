JAVAC = javac
JAVA = java
SRC_DIR = src
CLASS_DIR = class
FILE_PATH = "class/Main/italian_dist_graph.csv"

all: 
	$(JAVAC) -cp "lib/*" -d $(CLASS_DIR) $(SRC_DIR)/Graph/*.java
	$(JAVAC) -cp "lib/*" -d $(CLASS_DIR) $(SRC_DIR)/PriorityQueue/*.java
	$(JAVAC) -cp "lib/*:$(CLASS_DIR)" -d $(CLASS_DIR) $(SRC_DIR)/Main/*.java

run:
	$(JAVA) -cp "lib/*:$(CLASS_DIR)" Main.Prim $(FILE_PATH)

clean:
	rm -rf $(CLASS_DIR)/*