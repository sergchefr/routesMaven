package ru.ifmo.client.coms;

import ru.ifmo.server.coll.Location;
import ru.ifmo.server.coll.Route;
import ru.ifmo.server.servermanager.ServerManager;

import java.io.IOException;

public class AddCommand extends AbstractCommand {

private Route route;

    public AddCommand(ServerManager target, String[] param) throws IOException {
        super(target, param);
        if(!param[0].equals("%description%")) {
            try {
                this.route = routeParse(param);
            } catch (Exception e) {
                throw new IOException(e);
            }
        }
    }

    //public AddCommand(String a) {}

    public String execute(){
        getTarget().addCommandToHistiry("add");
        return getTarget().add(route);
    }

    public String description(){
        return "adds a new route to the collection";
    }

    private Route routeParse(String[] parm)throws IllegalArgumentException{
        Location from;
        Location to;
        float dist;
        String name;
        try {
            int fromx = Integer.parseInt(parm[0]);
            int fromy = Integer.parseInt(parm[1]);
            float fromz = Float.parseFloat(parm[2]);
            from = new Location(fromx, fromy, fromz, parm[3]);

            int tox = Integer.parseInt(parm[4]);
            int toy = Integer.parseInt(parm[5]);
            float toz = Float.parseFloat(parm[6]);
            to = new Location(tox, toy, toz, parm[7]);

            dist = Float.parseFloat(parm[8]);

            name = parm[9];
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
        try {
            return new Route(name, from, to, dist);
        }catch (IOException e){
            throw new IllegalArgumentException(e);
        }
    }
}
