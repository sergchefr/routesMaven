package ru.ifmo.server.coll;

import java.io.IOException;
import java.util.*;

/**
 * Класс, управляющий коллекцией
 */
public class TreeSetHandler {
    private final Collection<Route> coll;
    private final Date initDate;
    public TreeSetHandler() {
        coll = new TreeSet<Route>();
        this.initDate = new Date();
    }

    public String add(Route obj){
        Route route;
        try{
            route = obj;
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
        StringBuilder s = new StringBuilder();
        if(coll.isEmpty()) return "collection is empty\n";

        for (Object o : coll) {
            s.append(o.toString()).append("\n");
        }
        return s.toString();
    }
    public String update(Long id, Route route){
//        Route route;
//        try{
//            route = (Route) obj;
//        }catch(ClassCastException e){
//            return "collection handles only routes\n";
//        }
        for (Route o : coll) {
            if(o.getId().equals(id)) {
                coll.remove(o);
                try{
                    if(coll.add(new Route(id, route.getName(),route.getCreationDate(), route.getFromLocation(),route.getToLocation(), route.getDistance()))){
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
        for (Route o : coll) {
            if(o.getId()==id) {
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
    public String addIfMax(Route route){
        //        try{
//            route = (Route) obj;
//        }catch(ClassCastException e){
//            return "collection handles only routes";
//        }

        float maxd=0;
        for (Route o : coll) {
            if(o.getDistance()>maxd) maxd= o.getDistance();
        }
        if(route.getDistance()>maxd) {
            coll.add(route);
            return "element was added\n";
        }
        return "element is not max\n";
    }
    public String addIfMin(Route route){
        //        try{
//            route = (Route) obj;
//        }catch(ClassCastException e){
//            return "collection handles only routes";
//        }

        float mind=Float.POSITIVE_INFINITY;
        for (Route o : coll) {
            if(o.getDistance()<mind) mind= o.getDistance();
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
        for (Route o : coll) {
            s=s+ o.getDistance();
        }
        return s/coll.size()+"\n";
    }
    public String printAsc(){
        ArrayList<Route> routes = new ArrayList<>(Arrays.asList(getAllRoutes()));
        routes.sort(Comparator.comparing(Route::getDistance));

        StringBuilder s = new StringBuilder();
        if(routes.isEmpty()) return "collection is empty\n";

        for (Object o : routes) {
            s.append(o.toString()).append("\n");
        }
        return s+"\n";

    }
    public String printAscDist(){
        float[] dist=new float[coll.size()];
        int i =0;
        for (Route o : coll) {
            dist[i++]= o.getDistance();
        }
        Arrays.sort(dist);
        StringBuilder s= new StringBuilder();
        for (float v : dist) {
            s.append(v).append(", ");
        }
        return s+"\n";
    }

    public Route[] getAllRoutes(){
       return coll.toArray(new Route[0]);
    }
}
