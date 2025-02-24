package ru.ifmo.client.coms;

import ru.ifmo.server.servermanager.ServerManager;
/**
 * Команда для удаления всех элементов из коллекции
 */
public class ClearCommand extends AbstractCommand {
    /**
     * Команда для удаления всех элементов из коллекции
     * @param target класс-адресат
     * @param param не учитывается
     */
    public ClearCommand(ServerManager target, String[] param) {
        super(target,param);
    }

    @Override
    public String execute() {
        getTarget().addCommandToHistiry("clear");
        return getTarget().clear();
    }

    @Override
    public String description() {
        return "deletes all elements from the collection";
    }
}
