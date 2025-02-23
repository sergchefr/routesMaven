package client.clientmanager;

import client.coms.AbstractCommand;
import client.coms.IllegalParamException;
import client.coms.Response;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class ScriptReader {
    private ArrayList<String> commands = new ArrayList<>();
    private final ClientManager clientManager;

    public ScriptReader(ClientManager clientManager){
        this.clientManager = clientManager;
    }

    public void execute(String filepath) throws IOException{
        try(BufferedReader bfr = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)))){
            String nl;
            while(true){
                nl = bfr.readLine();
                if (nl!= null) commands.add(nl);
                else break;
            }
        }catch(IOException e){
            throw new IOException(e);
        }

        String s;
        String[] f;
        for (String command : commands) {
            s= command.strip().split(" ")[0];
            f= Arrays.copyOfRange(command.strip().split(" "),1,command.strip().split(" ").length);
            AbstractCommand q = null;
            try {
                q = clientManager.getCommand(s,f);
                clientManager.execCommand(q);
            } catch (IllegalParamException e) {
                clientManager.giveResponse(new Response("Cant execute "+s+". Bad parameters"));
            }

        }
    }
}
