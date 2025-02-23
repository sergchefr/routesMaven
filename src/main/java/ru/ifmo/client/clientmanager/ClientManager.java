package ru.ifmo.client.clientmanager;

import ru.ifmo.client.coms.AbstractCommand;
import ru.ifmo.client.coms.IllegalParamException;
import ru.ifmo.client.coms.LoadCommand;
import ru.ifmo.client.coms.Response;
import ru.ifmo.server.servermanager.ServerManager;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayDeque;
import java.util.HashMap;

public class ClientManager {
    private ServerManager serverManager;
    private ArrayDeque<Response> responses = new ArrayDeque<>();
    private HashMap<String, Class> coms = new HashMap<>();
    private final ScriptReader reader;
    private ArrayDeque<AbstractCommand> commandQueue= new ArrayDeque<>();

    public ClientManager(ServerManager serverManager) {
        this.serverManager = serverManager;
        reader = new ScriptReader(this);

        //ru.ifmo.client.coms.put("add",new AddCommand(new TreeSetHandler(), new String()));
    }

    public AbstractCommand getCommand(String comName, String[] param) throws IllegalParamException {
        if(!coms.containsKey(comName)) throw new IllegalParamException("Wrong name");
        Constructor<?> constructor = coms.get(comName).getConstructors()[0];
        try {
            return (AbstractCommand) constructor.newInstance(serverManager, param);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new IllegalParamException("Wrong parameters");
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

    public String[] getCommandNames(){
        return coms.keySet().toArray(new String[0]);
    }

    public void execScript(String filepath) throws IOException{
        try {
            reader.execute(filepath);
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    public void init(String rt){
        String[] pr = new String[1];
        pr[0] = System.getenv(rt);
        serverManager.addCommand(new LoadCommand(serverManager, pr));
    }
}
