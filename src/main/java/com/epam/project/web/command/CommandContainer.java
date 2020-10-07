package com.epam.project.web.command;

import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;


public class CommandContainer {

    private static final Logger log = Logger.getLogger(CommandContainer.class);

    private static Map<String, Command> commands = new TreeMap<>();

    static {
        // common commands
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("makeOrder", new MakeOrderCommand());
        commands.put("noCommand", new NoCommand());

        // client commands
        commands.put("tourMenu", new TourMenuCommand());

        // admin commands
        //commands.put("listOrders", new ListOrdersCommand());

        log.debug("Command container was successfully initialized");
        log.trace("Number of commands --> " + commands.size());
    }

    /**
     * Returns command object with the given name.
     *
     * @param commandName
     *            Name of the command.
     * @return Command object.
     */
    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            log.trace("Command not found, name --> " + commandName);
            return commands.get("noCommand");
        }

        return commands.get(commandName);
    }

}