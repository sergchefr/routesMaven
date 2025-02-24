package ru.ifmo.client.coms;

import ru.ifmo.server.servermanager.ServerManager;
/**
 * Команда для выхода из программы
 */
public class ExitCommand extends AbstractCommand {
    /**
     * Команда для выхода из программы
     * @param target класс-адресат
     * @param param не учитывается
     */
    public ExitCommand(ServerManager target, String[] param) {
        super(target, param);
    }

    @Override
    public String execute() {
        getTarget().addCommandToHistiry("exit");
        return getTarget().exit();
    }

    @Override
    public String description() {
        return "use it to exit the program";
    }
}
