package server.coll;

import java.io.IOException;
import java.util.*;
import server.xmlmanager.*;
public class TreeSetHandler {
    private Collection coll;
    private Date initDate;
    public TreeSetHandler() {
        coll = new TreeSet<Route>();
        this.initDate = new Date();
    }

    public String add(Route obj){
        Route route;
        try{
            route = (Route) obj;
        }catch(ClassCastException e){
            return "collection handles only routes\n";
        }
        if(coll.add(route)) return "element was added\n";
        return "element is already in collection\n";
    }
    public String info(){
        return "initialisation date: "+initDate+"\n"+"size: "+ coll.size()+"\n"+ "collection class: " + coll.getClass()+"\n";
    }
    public String show(){
        String s ="";
        if(coll.size()==0) return "collection is empty\n";

        for (Object o : coll) {
            s=s+o.toString()+"\n";
        }
        return s;
    }
    public String update(Long id, Route obj){
        Route route;
        try{
            route = (Route) obj;
        }catch(ClassCastException e){
            return "collection handles only routes\n";
        }
        for (Object o : coll) {
            if(((Route)o).getId().equals(id)) {
                coll.remove(o);
                try{
                    if(coll.add(new Route((long)id, route.getName(),route.getCreationDate(), route.getFromLocation(),route.getToLocation(), route.getDistance()))){
                        return "element updated by id\n";
                    }else{
                        return "element wasn`t updated\n";
                    }
                }catch (IOException e){
                    return "error\n";
                }

            }
        }
        return "element with this id doesn`t exist\n";
    }
    public String removeById(long id){
        for (Object o : coll) {
            if(((Route)o).getId()==id) {
                coll.remove(o);
                return "element deleted\n";
            }
        }
        return "element with this id doesn`t exist\n";
    }
    public String clear(){
        coll.clear();
        return "collection cleared\n";
    }
    public String exit(){
        System.exit(0);
        return "";
    }
    public String addIfMax(Route obj){
        Route route = obj;
//        try{
//            route = (Route) obj;
//        }catch(ClassCastException e){
//            return "collection handles only routes";
//        }

        float maxd=0;
        for (Object o : coll) {
            if(((Route)o).getDistance()>maxd) maxd=((Route)o).getDistance();
        }
        if(route.getDistance()>maxd) {
            coll.add(route);
            return "element was added\n";
        }
        return "element is not max\n";
    }
    public String addIfMin(Route obj){
        Route route = obj;
//        try{
//            route = (Route) obj;
//        }catch(ClassCastException e){
//            return "collection handles only routes";
//        }

        float mind=Float.POSITIVE_INFINITY;
        for (Object o : coll) {
            if(((Route)o).getDistance()<mind) mind=((Route)o).getDistance();
        }
        if(route.getDistance()<mind) {
            coll.add(route);
            return "element was added\n";
        }
        return "element is not min\n";
    }
    public String avgdistance(){
        double s=0;
        if(coll.isEmpty()) return "collection is empty\n";
        for (Object o : coll) {
            s=s+((Route)o).getDistance();
        }
        return Double.toString(s/coll.size())+"\n";
    }
    public String printAsc(){
        //return "in development\n"+this.show();
        ArrayList<Route> routes = new ArrayList<>(Arrays.asList(getAllRoutes()));
        routes.sort(Comparator.comparing(Route::getDistance));

        String s ="";
        if(routes.size()==0) return "collection is empty\n";

        for (Object o : routes) {
            s=s+o.toString()+"\n";
        }
        return s+"\n";

    }
    public String printAscDist(){
        float[] dist=new float[coll.size()];
        int i =0;
        for (Object o : coll) {
            dist[i++]=((Route)o).getDistance();
        }
        Arrays.sort(dist);
        String s="";
        for (float v : dist) {
            s=s+v+", ";
        }
        return s+"\n";
    }

    public Route[] getAllRoutes(){
       return (Route[]) coll.toArray(new Route[0]);
    }
}
