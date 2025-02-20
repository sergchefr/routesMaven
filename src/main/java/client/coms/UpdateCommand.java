package client.coms;

import server.coll.Location;
import server.coll.Route;
import server.servermanager.ServerManager;

import java.io.IOException;
import java.util.Arrays;

public class UpdateCommand extends AbstractCommand{

    private final Route route;
    private final long id;

    public UpdateCommand(ServerManager target, String[] param) throws IOException {
        super(target,param);
        this.id = Long.parseLong(param[0]);
        try {
            this.route = routeParse(Arrays.copyOfRange(param, 1, 11));
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    //public AddCommand(String a) {}

    public String execute(){
        getTarget().addCommandToHistiry("update");
        return getTarget().update(id,route);
    }

    public String description(){
        return "update id adds an element with the id instead of the element with the same id in the collection";
    }

    private Route routeParse(String[] parm)throws IOException{
        //String[] parm = param.split(" ");

        //public Route(String name,Location from, Location to, Float distance)
        // public Location(Integer x, Integer y, Float z, String name)
        int fromx = Integer.parseInt(parm[0]);
        int fromy = Integer.parseInt(parm[1]);
        float fromz = Float.parseFloat(parm[2]);
        Location from = new Location(fromx, fromy, fromz, parm[3]);

        int tox = Integer.parseInt(parm[4]);
        int toy = Integer.parseInt(parm[5]);
        float toz = Float.parseFloat(parm[6]);
        Location to = new Location(tox, toy, toz, parm[7]);

        float dist = Float.parseFloat(parm[8]);

        String name = parm[9];
        try {
            return new Route(name, from, to, dist);
        }catch (IOException e){
            throw new IOException(e);
        }
    }
}
