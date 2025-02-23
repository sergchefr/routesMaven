package server.coll;
//TODO добавить addhistory
/** Интерфейс с командами управления коллекцией*/
public interface Commands {
    public String save(String filename);
    public String load(String filename);
    public String add(Route route);
    public String info();
    public String show();
    public String update(Long id, Route obj);
    public String removeById(long id);
    public String clear();
    public String executeScript(String fileName);
    public String exit();
    public String addIfMax(Route route);
    public String addIfMin(Route route);
    public String avgdistance();
    public String printAsc();
    public String printAscDist();
    public String showHistory();
}
