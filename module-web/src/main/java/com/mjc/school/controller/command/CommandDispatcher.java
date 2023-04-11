package com.mjc.school.controller.command;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotations.CommandHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

@Component("commandDispatcher")
public class CommandDispatcher {
    private final Map<String, String> commandMap = new HashMap<>();

    public CommandDispatcher() {
        commandMap.put("1", "readAll");
        commandMap.put("2", "readById");
        commandMap.put("3", "create");
        commandMap.put("4", "update");
        commandMap.put("5", "deleteById");
        commandMap.put("6", "readAll");
        commandMap.put("7", "readById");
        commandMap.put("8", "create");
        commandMap.put("9", "update");
        commandMap.put("10", "deleteById");
    }
    public Object dispatch(BaseController<?, ?, ?> controller, String commandName, List<Object> commandParams) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = BaseController.class.getDeclaredMethods();
        Method method = null;
        for (Method m : methods) {
            if (m.isAnnotationPresent(CommandHandler.class)) {
                if (m.getName().equals(commandMap.get(commandName))){
                    method = m;
                    break;
                }
            }
        }

        if (method == null) {
            throw new IllegalArgumentException("No method found for command: " + commandName);
        }

        // invoke the method on the controller with the specified parameters
        Object[] params = commandParams.toArray();
        return method.invoke(controller, params);
    }
}
