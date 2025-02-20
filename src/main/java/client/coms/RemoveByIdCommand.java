package client.coms;

import server.servermanager.ServerManager;

import java.io.IOException;

public class RemoveByIdCommand extends AbstractCommand{
    private final long id;

    public RemoveByIdCommand(ServerManager target, String[] param) throws IOException {
        super(target,param);
        this.id = Long.parseLong(param[0]);
    }

    @Override
    public String execute() {
        getTarget().addCommandToHistiry("remove_by_id");
        return getTarget().removeById(id);
    }

    @Override
    public String description() {
        return "remove_by_id id removes an element with that id from the collection";
    }
}
