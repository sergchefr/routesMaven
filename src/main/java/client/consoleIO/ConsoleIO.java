
package client.consoleIO;
import client.clientmanager.ClientManager;
import client.clientmanager.ScriptReader;
import client.coms.AbstractCommand;
import client.coms.IllegalParamException;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class ConsoleIO {
    Scanner console = new Scanner(System.in);
    ClientManager clientManager;

    public ConsoleIO(ClientManager clientManager) {
        this.clientManager = clientManager;
    }

    public void start(){
        System.out.println("program started. Type commands or \"help\" for help");
        while(true){
            String resp = clientManager.getResponse().getMessage();
            if(!resp.isEmpty()) System.out.println(resp);

            System.out.print(">>> ");
            String command = console.nextLine();
            command = deleteExtraSpace(command);
            String com = null;
            String[] param=null;
            String[] splitted = command.strip().split(" ");
            if(splitted.length ==1){
                com = splitted[0];
                param =null;
            }else{
                com=splitted[0];
                param = Arrays.copyOfRange(splitted, 1, splitted.length);
            }
            AbstractCommand q;

            switch (com){
                case("help"):
                    String[] comNames= clientManager.getCommandNames();
                    for (String comName : comNames) {
                        param = new String[1];
                        param[0] = "%description%";
                        try {
                            q=clientManager.getCommand(comName, param);
                            System.out.println(comName+": "+q.description());
                        } catch (IllegalParamException e) {
                            System.out.println("Error. Cant get the "+comName+" description");
                        }
                    }
                    break;
                case "info","show","exit","clear","history","average_of_distance","print_ascending","print_field_ascending_distance":
                    try {
                        q = clientManager.getCommand(com, null);
                        clientManager.execCommand(q);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case("add"):
                    //String c = rangeConstructor();
                    param = rangeConstructor().split(" ");
                    try {
                        q = clientManager.getCommand(com, param);
                        clientManager.execCommand(q);
                    } catch (IllegalParamException e) {
                        System.out.println("Illegal parameters");
                    }
                    break;
                case("update"):
                    System.out.print("type id: ");
                    String a = console.nextLine().strip()+" "+rangeConstructor();
                    param = a.split(" ");
                    try {
                        q = clientManager.getCommand(com, param);
                        clientManager.execCommand(q);
                    } catch (IllegalParamException e) {
                        System.out.println("Illegal parameters");
                    }
                    break;
                case("remove_by_id"):
                    if(param==null){
                        System.out.print("type id: ");
                        param = new String[1];
                        param[0]=console.nextLine().strip();
                        try {
                            q = clientManager.getCommand(com, param);
                            clientManager.execCommand(q);
                        } catch (IllegalParamException e) {
                            System.out.println("Illegal parameters");
                        }
                    }else if(param.length ==1){
                        try {
                            q = clientManager.getCommand(com, param);
                            clientManager.execCommand(q);
                        } catch (IllegalParamException e) {
                            System.out.println("Illegal parameters");
                        }
                    }
                    break;
                case("execute_script"):
                    try{
                        clientManager.execScript(param[0]);
                    }catch (IOException|NullPointerException e){
                        System.out.println("error while opening the file");
                    }
                    break;
                case("add_if_max"):
                    param = rangeConstructor().split(" ");
                    try {
                        q = clientManager.getCommand(com, param);
                        clientManager.execCommand(q);
                    } catch (IllegalParamException e) {
                        System.out.println("Illegal parameters");
                    }
                    break;
                case("add_if_min"):
                    param = rangeConstructor().split(" ");
                    try {
                        q = clientManager.getCommand(com, param);
                        clientManager.execCommand(q);
                    } catch (IllegalParamException e) {
                        System.out.println("Illegal parameters");
                    }
                    break;
                case"load","save" :
                    try {
                        q = clientManager.getCommand(com, param);
                        clientManager.execCommand(q);
                    } catch (IllegalParamException e) {
                        System.out.println("Illegal parameters");
                    }
                    break;
                default:
                    System.out.println("unidentified command");

            }
        }
    }

    private String rangeConstructor(){
        String c="";
        System.out.print("type the route name: ");
        String name = console.nextLine();

        System.out.println("type coordinates of from location. use \",\" as separator");
        System.out.print("x:int, y:int z:float: ");
        String[] fromCoordinates = console.nextLine().strip().split(",");

        for (int i = 0; i < fromCoordinates.length; i++) {
            c=c+fromCoordinates[i].strip()+" ";
        }

        System.out.print("type the location name: ");
        c=c+ console.nextLine().strip()+" ";

        System.out.println("type coordinates of the destination location. use \",\" as separator");
        System.out.print("x:int, y:int z:float: ");
        String[] toCoordinates = console.nextLine().strip().split(",");
        for (int i = 0; i < fromCoordinates.length; i++) {
            c=c+fromCoordinates[i].strip()+" ";
        }

        System.out.print("type the location name: ");
        c=c+ console.nextLine().strip()+" ";

        System.out.println("type the distance");
        System.out.print("dist:float: ");
        c=c+ console.nextLine().strip()+" ";

        c=c+name;

        //System.out.println("--c "+c);
        return c;
    }

    private String deleteExtraSpace(String a){
        char[] b = a.toCharArray();
        String s = "";
        boolean wasSpace = false;
        for (int i = 0; i < a.length(); i++) {
            if(!wasSpace){
                if(b[i]==' ') wasSpace = true;
                else wasSpace = false;

                s=s+b[i];
            }else{
                if(b[i]!=' ') s=s+b[i];
            }
        }
        return s;
    }

}
