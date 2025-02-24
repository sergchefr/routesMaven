package ru.ifmo.client.coms;

import ru.ifmo.server.servermanager.ServerManager;
/**
 * Команда для вывода элементов коллекции
 */
public class ShowCommand extends AbstractCommand{
    /**
     * Команда для вывода элементов коллекции
     * @param target класс-адресат
     * @param param не учитывается
     */
    public ShowCommand(ServerManager target, String[] param) {
        super(target, param);
    }

    @Override
    public String execute() {
        getTarget().addCommandToHistiry("show");
        return getTarget().show();
    }

    @Override
    public String description() {
        return "shows the collection`s elements";
    }
}
