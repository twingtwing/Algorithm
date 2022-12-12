package practice.graph;

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
    
    public String getName() {return this.name;}

    public boolean isMarked() {return this.marked;}

    public void setMarked(boolean marked) {this.marked = marked;}

    public LinkedList<Project> getDependencies() {return this.dependencies;}
    
    public void addDependencies(Project project){if (!this.dependencies.contains(project)) this.dependencies.add(project);}

}



class ProjectManager{
    private HashMap<String, Project> projects;
    
    public ProjectManager(String [] names, String [][] dependencies){
        buildProjects(names);
        addDependencies(dependencies);
    }

    private void buildProjects(String[] names) {
        this.projects = new HashMap<>();
        for (String name : names){
            this.projects.put(name,new Project(name));
        }
    }

    private void addDependencies(String[][] dependencies) { // name 배열 ( 자식 project, 부모 project)
        for (String [] dependency : dependencies){
            Project root = findProject(dependency[0]);
            Project child = findProject(dependency[1]);
            child.addDependencies(root);
        }
    }

    private Project findProject(String name) {return this.projects.get(name);}
    
    Integer index;
    public Project[] buildOrder(){
        setInitMarked();
        this.index = 0;
        Project [] path = new Project[this.projects.size()];
        for (Project project : this.projects.values()) buildOrder(project,path);
        return path;
    }

    private void buildOrder(Project project, Project[] path) {
        if (project.isMarked()) return;
        if (!project.getDependencies().isEmpty())for (Project root : project.getDependencies()) buildOrder(root,path);
        project.setMarked(true);
        path[index++] = project;
    }

    private void setInitMarked() {for (Project project : this.projects.values())project.setMarked(false);}
    
    public void printPath(Project [] path){
        if (path.length == 0) return;
        for (Project project : path) if (project != null) System.out.print(project.getName() + " ");
    }
}


public class Graph_Apply02 {
    public static void main(String[] args) {
        String [] projects ={"a","b","c","d","e","f","g"};
        String [][] dependencies = {{"f","a"},{"f","b"},{"f","c"},{"b","a"},{"c","a"},{"a","e"},{"b","e"},{"d","g"}};
        ProjectManager pm = new ProjectManager(projects,dependencies);
        pm.printPath(pm.buildOrder());
    }

}
