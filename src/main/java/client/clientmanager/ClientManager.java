package client.clientmanager;

import client.coms.AbstractCommand;
import client.coms.Response;
import server.servermanager.ServerManager;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayDeque;
import java.util.HashMap;

public class ClientManager {
    private ServerManager serverManager;
    private ArrayDeque<Response> responses = new ArrayDeque<>();
    private HashMap<String, Class> coms = new HashMap<>();
    private ArrayDeque<AbstractCommand> commandQueue= new ArrayDeque<>();

    public ClientManager(ServerManager serverManager) {
        this.serverManager = serverManager;
        //client.coms.put("add",new AddCommand(new TreeSetHandler(), new String()));
    }

    public AbstractCommand getCommand(String comName, String[] param){
        Constructor<?> constructor = coms.get(comName).getConstructors()[0];
        try {
            return (AbstractCommand) constructor.newInstance(serverManager, param);
        } catch (/*IOException |*/ InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
    }

    public void setServerManager(ServerManager serverManager){
        this.serverManager = serverManager;
    }

    public void addnewCommand(String name, Class cl){
        coms.put(name, cl);

    }

    public void giveResponse(Response response){
        responses.addLast(response);
        //System.out.println("response: \n"+ response);
    }

    public Response getResponse(){
        if(responses.isEmpty()) return new Response("");
        return responses.pollFirst();
    }

    public void execCommand(AbstractCommand com){
        commandQueue.addLast(com);

        serverManager.addCommand(commandQueue.pollFirst());
    }





}
