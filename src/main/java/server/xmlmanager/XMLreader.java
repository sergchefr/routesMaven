package server.xmlmanager;
import server.coll.Route;
import server.coll.Location;

import java.io.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class XMLreader {
    private String read(String filepath) throws IOException {
        if(!(filepath.contains("\\")|filepath.contains("/"))){
            System.out.println(filepath.contains("\\"));
            //если это сокращенное название
            File savefolder = new File(System.getProperty("java.class.path")+"\\resources"+"\\saves");
            File file;
            savefolder.mkdirs();
            if(filepath.contains(".xml")){
                file = new File(savefolder.getPath()+"\\"+filepath);
            }else{
                file = new File(savefolder.getPath()+"\\"+filepath+".xml");
            }
            filepath = file.getPath();
        }
        String str ="";
        try (BufferedReader bfr = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)))) {
            String nl;
            String version = bfr.readLine();
            while (true) {
                nl = bfr.readLine();
                if (nl != null) str = str+nl.strip();
                else break;
            }
        } catch (IOException e) {
            throw new IOException(e);
        }
        str=str.replace("<","#<").replace(">",">#").replace("##","#").substring(1);
        return str;
    }

    public ArrayList<Route> getRoutes(String filename)throws IOException{
        String[] coms;
        try {
            String str1 = read(filename);
            coms = str1.split("#");
        }catch (IOException e){
            throw new IOException(e);
        }
        //System.out.println(Arrays.toString(client.coms));
        Stack<String> cond = new Stack<>();
        HashMap<String, String> constr = new HashMap();
        ArrayList<Route> routes= new ArrayList<>();
        for (String s : coms) {
            if(s.contains("/")) {
                String a = cond.pop();
                //System.out.println(a);
                if (a.equals("<route>")){
                    //System.out.println("svo");
                        routes.add(new Route(
                                Long.parseLong(constr.get("[<data>, <route>, <id>]")),
                                constr.get("[<data>, <route>, <name>]"),
                                dateParse(constr.get("[<data>, <route>, <creationDate>]")),
                                new Location(Integer.parseInt(constr.get("[<data>, <route>, <locationFrom>, <x>]")),
                                        Integer.parseInt(constr.get("[<data>, <route>, <locationFrom>, <y>]")),
                                        Float.parseFloat(constr.get("[<data>, <route>, <locationFrom>, <z>]")),
                                        constr.get("[<data>, <route>, <locationFrom>, <name>]")),
                                new Location(Integer.parseInt(constr.get("[<data>, <route>, <locationTo>, <x>]")),
                                        Integer.parseInt(constr.get("[<data>, <route>, <locationTo>, <y>]")),
                                        Float.parseFloat(constr.get("[<data>, <route>, <locationTo>, <z>]")),
                                        constr.get("[<data>, <route>, <locationTo>, <name>]")),
                                Float.parseFloat(constr.get("[<data>, <route>, <distance>]"))
                        ));
                }

            }
            else if(s.contains("<")) cond.add(s);
            else constr.put(cond.toString(),s);
        }
        //System.out.println(constr);
        return routes;
    }

    private Date dateParse(String str){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        ZonedDateTime zdt = ZonedDateTime.parse(str, formatter);
        return Date.from(zdt.toInstant());
    }


}
