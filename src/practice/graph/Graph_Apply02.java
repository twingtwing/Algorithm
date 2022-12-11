package practice.graph;

import com.sun.org.apache.xpath.internal.operations.String;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * 응용. 여러개 프로젝트가 존재한다. 특정 프로젝트는 사전에 어떠한 프로젝트가 완료되어야하만 진행할 수 있다.
 *      프로젝트의 목록과, 각 프로젝트간 의존여부를 입력하면, 프로젝트 진행 순서를 반환하는 알고리즘 구현
 *      !! keypoint !!
 *      의존도 이므로 graph로 구현하도, 공간복잡도가 적은??? LinkedList를 이용하여 구현 => Adjacent List
 */
class Project{
    private String name;
    private boolean marked;
    private LinkedList<Project> dependencies;
    public Project(String name){
        this.name = name;
        this.marked = false;
        this.dependencies = new LinkedList<>();
    }
    
    public String getName() {return name;}

    public boolean isMarked() {return marked;}

    public void setMarked(boolean marked) {this.marked = marked;}

    public LinkedList<Project> getDependencies() {return dependencies;}
    
    public void addDependencies(Project project){if (this.dependencies.contains(project)) this.dependencies.add(project);}

}

class ProjectManager{
    private HashMap<String, Project> projects;
    
    public ProjectManager(String [] names, String [][] dependencies){
        buildProjects(names);
        addDependencies(dependencies);
    }

    private void addDependencies(String[][] dependencies) {
    }

    private void buildProjects(String[] names) {
    }
}


public class Graph_Apply02 {
    public static void main(String[] args) {

    }
}
