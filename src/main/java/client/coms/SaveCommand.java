package client.coms;

import server.servermanager.ServerManager;

public class SaveCommand extends AbstractCommand{

    private final String filename;
    public SaveCommand(ServerManager target, String[] param) {
        super(target, param);
        filename= param[0];
    }

    @Override
    public String execute() {
        getTarget().addCommandToHistiry("save");
        return getTarget().save(filename);
    }

    @Override
    public String description() {
        return "saves the collection into the XML file";
    }
}
