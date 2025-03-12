package ru.ifmo.server.servermanager;

import ru.ifmo.client.clientmanager.ClientManager;
import ru.ifmo.server.coll.Commands;
import ru.ifmo.server.coll.Route;
import ru.ifmo.server.coll.TreeSetHandler;
import ru.ifmo.client.coms.*;
import ru.ifmo.server.history.History;
import ru.ifmo.server.xmlmanager.*;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Класс, управляющий функциями сервера и взаимодействующий с управляющим классом клиентской части
 */
public class ServerManager implements Commands{

    private ClientManager clientManager;
    private ArrayDeque<AbstractCommand> coms= new ArrayDeque<>();
    private TreeSetHandler collhandler;
    private History hst = new History();



    public ServerManager(ClientManager clientManager, TreeSetHandler collhandler) {
        this.clientManager = clientManager;
        this.collhandler = collhandler;
    }

    public void execute(){
        Response response;
        if(!coms.isEmpty()) response = new Response(coms.pollFirst().execute());
        else response = new Response("no commands to execute");

        clientManager.giveResponse(response);
    }

    public void addCommand(AbstractCommand com){
        coms.addLast(com);
        execute();
    }

    @Override
    public String save(String filename) {
        XMLwriter writer=new XMLwriter();
        try {
            writer.writeRoute(collhandler.getAllRoutes(), filename);
        }catch (IOException e){
            return "can`t create the file";
        }
        return "collection saved";
    }

    @Override
    public String load(String filename) {
        int errorCounter=0;
        XMLreader reader = new XMLreader();
        try{
            ArrayList<Route> routes=reader.getRoutes(filename);
            for (Route route : routes) {
                collhandler.add(route);
            }

//            for (Route route : routes) {
//                while((add(route)).equals("element is already in the collection")){
//                    route = new Route(route.getId()+1, route.getName(), route.getCreationDate(),route.getFromLocation(),route.getToLocation(), route.getDistance());
//                }
//            }
        }catch (IOException e){
            return "error while opening file: "+ filename;
        }catch (IllegalParamException e){
            errorCounter+=1;
        }
        return "file loaded, "+errorCounter+" errors";
    }

    @Override
    public String add(Route route) {
        return collhandler.add(route);
    }

    @Override
    public String info() {
        return collhandler.info();
    }

    @Override
    public String show() {
        return collhandler.show();
    }

    @Override
    public String update(Long id, Route route) {
        return collhandler.update(id,route);
    }

    @Override
    public String removeById(long id) {
        return collhandler.removeById(id);
    }

    @Override
    public String clear() {
        return collhandler.clear();
    }

    @Override
    public String exit() {
        System.exit(0);
        return "";
    }

    @Override
    public String addIfMax(Route route) {
        return collhandler.addIfMax(route);
    }

    @Override
    public String addIfMin(Route route) {
        return collhandler.addIfMin(route);
    }

    @Override
    public String avgdistance() {
        return collhandler.avgdistance();
    }

    @Override
    public String printAsc() {
        return collhandler.printAsc();
    }

    @Override
    public String printAscDist() {
        return collhandler.printAscDist();
    }

    public void setClientManager(ClientManager clientManager) {
        this.clientManager = clientManager;
    }

    @Override
    public String showHistory() {
        return hst.showHistory();
    }

    public void addCommandToHistiry(String com){
        hst.add(com);
    }
}
