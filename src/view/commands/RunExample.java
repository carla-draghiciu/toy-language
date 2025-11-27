package view.commands;

import controller.Controller;

public class RunExample extends Command {
    private Controller controller;
    public RunExample(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute() {
        try {
            controller.displayCurrentState(0);
//            controller.displayCurrentState(Integer.parseInt(this.getKey()) - 1);
            controller.executeAll(0);
//            controller.executeAll(Integer.parseInt(this.getKey()) - 1);
        } catch (Exception e) {
            IO.println(e.getMessage());
        }
    }
}
