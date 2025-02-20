package client.coms;

import server.servermanager.ServerManager;

import java.io.IOException;

public class ShowCommand extends AbstractCommand{

    public ShowCommand(ServerManager target, String[] param) throws IOException {
        super(target, param);
        //throw new IOException();
    }

    @Override
    public String execute() {
        getTarget().addCommandToHistory("show");
        return getTarget().show();
    }

    @Override
    public String description() {
        return "shows the collection`s elements";
    }
}
