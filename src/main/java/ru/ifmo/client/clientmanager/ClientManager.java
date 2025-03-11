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

/**
 * Класс, управляющий всеми функциями клиентской части и осуществляющий взаимодействие с управляющим классом сервера
 */
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

    /**
     * Метод, сопоставляющий названию и параметрам команды ее экземпляр
     * @param comName название команды
     * @param param параметры команды
     * @return экземпляр команды
     * @throws IllegalParamException если неверное имя или параметры, не позволяющие создать класс
     */
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

    /**
     * Метод для добавления нового типа команды в класс
     * @param name текстовое имя команды
     * @param cl класс команды
     */
    public void addnewCommand(String name, Class cl){
        coms.put(name, cl);

    }

    /**
     * Метод для получения ответа от сервера или чего-либо еще. Добавляет в очередь ответов внутри класса
     * @param response сам ответ
     */
    public void giveResponse(Response response){
        responses.addLast(response);
        //System.out.println("response: \n"+ response);
    }

    /**
     * Метод, для получения ответов, содержащихся в очереди внутри класса.
     * @return первый ответ в очереди
     */
    public Response getResponse(){
        if(responses.isEmpty()) return new Response("");
        return responses.pollFirst();
    }

    /**
     * Метод, отправляющий серверу переданную команду
     * @param com команда
     */
    public void execCommand(AbstractCommand com){
        commandQueue.addLast(com);

        serverManager.addCommand(commandQueue.pollFirst());
    }

    /**
     * Метод для получения списка названий команд, которые могут быть выполнены
     * @return массив строк названий команд
     */
    public String[] getCommandNames(){
        return coms.keySet().toArray(new String[0]);
    }

    /**
     * Метод, выполняющий скрипт из файла
     * @param filepath путь к файлу
     * @throws IOException если не удается открыть файл
     */
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
        if (pr[0]==null){
            giveResponse(new Response("no such environment variable"));
        }else{
            serverManager.addCommand(new LoadCommand(serverManager, pr));
        }
    }
}
