package edu.io.player;

import java.util.ArrayList;
import java.util.List;

public class Shed {

    private final List<Tool> tools = new ArrayList<>();

    public boolean isEmpty() {
        return tools.isEmpty();
    }

    public void add(Tool tool) {
        if (tool == null) throw new IllegalArgumentException();
        tools.add(tool);
    }

    public Tool getTool() {
        if (tools.isEmpty()) return new NoTool();
        return tools.get(tools.size() - 1);
    }

    public void dropTool() {
        if (!tools.isEmpty()) {
            tools.remove(tools.size() - 1);
        }
    }
}

