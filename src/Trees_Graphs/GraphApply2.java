package Trees_Graphs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Graph 응용 : 회사에 여러개의 프로젝트가 있는데, 어떤 프로젝트들은 특정 프로젝트가 완료 되어야만 진행할 수 있는 프로젝트가 있다.
 *              프로젝트의 목록과, 각 프로젝트간 의존여부를 입력하면, 의존도에 입각한 프로젝트 진행 순서를 반환하는 함수를 구현
 *              => 의존도이므로 Graph로 구현하고, 공간복접도가 적은 Linked List를 이용하여 구현
 */
class Project{
    private String name;
    private LinkedList<Project> dependencies;
    private boolean marked;
    public Project(String name){
        this.name = name;
        this.dependencies = new LinkedList<>();
        this.marked = false;
    }

    public void addDependency(Project project){
        if (!dependencies.contains(project)){
            dependencies.add(project);
        }
    }

    public LinkedList<Project> getDependencies(){
        return this.dependencies;
    }

    public String getName(){
        return this.name;
    }

    public void setMarked(boolean marked){
        this.marked = marked;
    }

    public boolean getMarked(){
        return this.marked;
    }
}

class ProjectManager{
    private HashMap<String , Project> projects;
    public ProjectManager(String [] names, String[][] dependencies){
        buildProjects(names);
        addDependencies(dependencies);
    }

    public void buildProjects(String [] names){
        projects = new HashMap<>();
        for (int i = 0; i< names.length; i++){
            projects.put(names[i], new Project(names[i]));
        }
    }

    public void addDependencies(String [][] dependencies){
        for (String [] dependency : dependencies){
            Project before = findProject(dependency[0]);
            Project after = findProject(dependency[1]);
            after.addDependency(before);
        }
    }

    private int index;
    public Project[] buildOrder(){
        initMarkingFlags();
        Project[] ordered = new Project[this.projects.size()];
        index = 0;
        for (Project project : this.projects.values()){
            buildOrder(project,ordered);
        }
        return ordered;
    }

    public void buildOrder(Project project, Project[] ordered){
        if(!project.getDependencies().isEmpty()){
            for(Project p : project.getDependencies()){
                buildOrder(p,ordered);
            }
        }
        if (project.getMarked() == false){
            project.setMarked(true);
            ordered[index] = project;
            index++;
        }
    }

    private void initMarkingFlags(){
        for(Project project : this.projects.values()){
            project.setMarked(false);
        }
    }

    private Project findProject(String name){
        return projects.get(name);
    }
}

public class GraphApply2 {
    public static void main(String [] args){
        String[] projects ={"a","b","c","d","e","f","g"};
        String [][] dependencies = {{"f","a"},{"f","b"},{"f","c"},{"b","a"},{"c","a"},{"a","e"},{"b","e"},{"d","g"}};
        ProjectManager pm = new ProjectManager(projects,dependencies);
        Project[] ps = pm.buildOrder();
        for (Project p : ps){
            if (p != null){
                System.out.print(p.getName()+"  ");
            }
        }
    }
}
