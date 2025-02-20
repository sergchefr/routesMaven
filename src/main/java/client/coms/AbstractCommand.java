package client.coms;

import server.servermanager.ServerManager;

public  abstract class AbstractCommand{
    private final ServerManager target;
    private final String[] param;

    public AbstractCommand(ServerManager target, String[] param) {
        this.target = target;
        this.param= param;
    }

    public abstract String execute();
    public abstract String description();
    protected ServerManager getTarget(){
        return target;
    }
    protected String[] getParam(){
        return param;
    }
}
