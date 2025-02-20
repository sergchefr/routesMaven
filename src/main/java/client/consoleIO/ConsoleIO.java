
package client.consoleIO;
import client.clientmanager.ClientManager;
import client.clientmanager.ScriptReader;
import client.coms.AbstractCommand;

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
                    q= clientManager.getCommand("help", param);
                    clientManager.execCommand(q);
                    break;
                case "info","show","exit","clear","history","average_of_distance","print_ascending","print_field_ascending_distance":
                    q = clientManager.getCommand(com, null);
                    clientManager.execCommand(q);
                    break;
                case("add"):
                    //String c = rangeConstructor();
                    param = rangeConstructor().split(" ");
                    q = clientManager.getCommand(com, param);
                    clientManager.execCommand(q);
                    break;
                case("update"):
                    System.out.print("type id: ");
                    String a = console.nextLine().strip()+" "+rangeConstructor();
                    //a=a+rangeConstructor();
                    param = a.split(" ");
                    q = clientManager.getCommand(com, param);
                    clientManager.execCommand(q);
                    break;
                case("remove_by_id"):
                    if(param==null){
                        System.out.print("type id: ");
                        param = new String[1];
                        param[0]=console.nextLine().strip();
                        q = clientManager.getCommand(com, param);
                        clientManager.execCommand(q);
                    }else if(param.length ==1){
                        q = clientManager.getCommand(com, param);
                        clientManager.execCommand(q);
                    }
                    break;
                case("execute_script"):
                    ScriptReader scriptReader = new ScriptReader(clientManager);
                    try{
                        scriptReader.execute(param[1]);
                    }catch (IOException e){
                        System.out.println("error while opening the file");
                    }

                    break;
                case("add_if_max"):
                    param = rangeConstructor().split(" ");
                    q = clientManager.getCommand(com, param);
                    clientManager.execCommand(q);
                    break;
                case("add_if_min"):
                    param = rangeConstructor().split(" ");
                    q = clientManager.getCommand(com, param);
                    clientManager.execCommand(q);
                    break;
                case"load","save" :
                    q = clientManager.getCommand(com, param);
                    clientManager.execCommand(q);
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
        for (int i = 0; i < 3; i++) {
            c=c+fromCoordinates[i].strip()+" ";
        }

        System.out.print("type the location name: ");
        c=c+ console.nextLine().strip()+" ";

        System.out.println("type coordinates of the destination location. use \",\" as separator");
        System.out.print("x:int, y:int z:float: ");
        String[] toCoordinates = console.nextLine().strip().split(",");
        for (int i = 0; i < 3; i++) {
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
