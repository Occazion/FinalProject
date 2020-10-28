package com.epam.project.web.command.action;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;

public class ActionContainer {

    private static final Logger log = Logger.getLogger(ActionContainer.class);

    private static final Map<String, Action> actions = new TreeMap<>();

    static {

        actions.put("fire", new FireAction());
        actions.put("status", new StatusAction());
        actions.put("discount", new DiscountAction());
        actions.put("add", new AddAction());
        actions.put("delete", new DeleteAction());
        actions.put("change", new ChangeAction());
        actions.put("noAction", new NoAction());

        log.debug("Command container was successfully initialized");
        log.trace("Number of commands --> " + actions.size());
    }

    /**
     * Returns command object with the given name.
     *
     * @param actionName
     *            Name of the command.
     * @return Command object.
     */
    public static Action get(String actionName) {
        if (actionName == null || !actions.containsKey(actionName)) {
            log.trace("Command not found, name --> " + actionName);
            return actions.get("noCommand");
        }

        return actions.get(actionName);
    }
}
