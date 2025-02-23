package ru.ifmo.server.xmlmanager;

import ru.ifmo.server.coll.Route;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class XMLwriter {
    public void writeRoute(Route[] routes, String filename)throws IOException{

        File savefolder = new File(System.getProperty("java.class.path")+"\\..\\"+"\\resources"+"\\saves");
        System.out.println(savefolder.getPath());
        File file;
        System.out.println(savefolder.mkdirs());
        if(filename.contains(".xml")){
            file = new File(savefolder.getPath()+"\\"+filename);
        }else{
            file = new File(savefolder.getPath()+"\\"+filename+".xml");
        }

        try{
            file.createNewFile();
        } catch (IOException e) {
            throw new IOException("error while creating file");
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
            writer.println("<data>");
            for (Route route : routes) {
                writer.println("\t<route>");
                writer.println("\t\t<id>" + route.getId() + "</id>");
                writer.println("\t\t<name>" + route.getName() + "</name>");
                writer.println("\t\t<creationDate>" + route.getCreationDate() + "</creationDate>");
                writer.println("\t\t<locationFrom>");
                writer.println("\t\t\t<name>" + route.getFromLocation().getName() + "</name>");
                writer.println("\t\t\t<x>" + route.getFromLocation().getX() + "</x>");
                writer.println("\t\t\t<y>" + route.getFromLocation().getY() + "</y>");
                writer.println("\t\t\t<z>" + route.getFromLocation().getZ() + "</z>");
                writer.println("\t\t</locationFrom>");
                writer.println("\t\t<locationTo>");
                writer.println("\t\t\t<name>" + route.getToLocation().getName() + "</name>");
                writer.println("\t\t\t<x>" + route.getToLocation().getX() + "</x>");
                writer.println("\t\t\t<y>" + route.getToLocation().getY() + "</y>");
                writer.println("\t\t\t<z>" + route.getToLocation().getZ() + "</z>");
                writer.println("\t\t</locationTo>");
                writer.println("\t\t<distance>" + route.getDistance() + "</distance>");
                writer.println("\t</route>");
            }
            writer.println("</data>");
        } catch (IOException e) {
            throw new IOException("error while writing XML");
        }
    }
}
