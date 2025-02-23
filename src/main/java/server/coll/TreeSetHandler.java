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
            return "collection handles only routes";
        }
        if(coll.add(route)) return "element was added";
        return "element is already in collection";
    }
    public String info(){
        return "initialisation date: "+initDate+"\n"+"size: "+ coll.size()+"\n"+ "collection class: " + coll.getClass();
    }
    public String show(){
        String s ="";
        if(coll.size()==0) return "collection is empty";

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
            return "collection handles only routes";
        }
        for (Object o : coll) {
            if(((Route)o).getId().equals(id)) {
                coll.remove(o);
                try{
                    if(coll.add(new Route((long)id, route.getName(),route.getCreationDate(), route.getFromLocation(),route.getToLocation(), route.getDistance()))){
                        return "element updated by id";
                    }else{
                        return "element wasn`t updated";
                    }
                }catch (IOException e){
                    return e.getMessage();
                }

            }
        }
        return "element with this id doesn`t exist";
    }
    public String removeById(long id){
        for (Object o : coll) {
            if(((Route)o).getId()==id) {
                coll.remove(o);
                return "element deleted";
            }
        }
        return "element with this id doesn`t exist";
    }
    public String clear(){
        coll.clear();
        return "collection cleared";
    }
    public String executeScript(String fileName){
        return "in development";
    }
    public String exit(){
        System.exit(0);
        return "";
    }
    public String addIfMax(Route obj){
        Route route;
        try{
            route = (Route) obj;
        }catch(ClassCastException e){
            return "collection handles only routes";
        }

        float maxd=0;
        for (Object o : coll) {
            if(((Route)o).getDistance()>maxd) maxd=((Route)o).getDistance();
        }
        if(route.getDistance()>maxd) {
            coll.add(route);
            return "element was added";
        }
        return "element is not max";
    }
    public String addIfMin(Route obj){
        Route route;
        try{
            route = (Route) obj;
        }catch(ClassCastException e){
            return "collection handles only routes";
        }

        float mind=Float.POSITIVE_INFINITY;
        for (Object o : coll) {
            if(((Route)o).getDistance()<mind) mind=((Route)o).getDistance();
        }
        if(route.getDistance()<mind) {
            coll.add(route);
            return "element was added";
        }
        return "element is not min";
    }
    public String avgdistance(){
        double s=0;
        for (Object o : coll) {
            s=s+((Route)o).getDistance();
        }
        return Double.toString(s/coll.size());
    }
    public String printAsc(){
        return "in development\n"+this.show();
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
        return s;
    }

    public Route[] getAllRoutes(){
       return (Route[]) coll.toArray(new Route[0]);
    }
}
