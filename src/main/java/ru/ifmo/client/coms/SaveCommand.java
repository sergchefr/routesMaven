package ru.ifmo.client.coms;

import ru.ifmo.server.servermanager.ServerManager;
/**
 * Команда для сохранения элементов коллекции в XML файл
 */
public class SaveCommand extends AbstractCommand{
    private final String filename;
    /**
     * Команда для сохранения элементов коллекции в XML файл
     * @param target класс-адресат
     * @param param путь к XML
     */
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
