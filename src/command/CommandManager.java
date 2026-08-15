package command;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {

	private List<Command> undoCommands = new ArrayList<Command>();
	private List<Command> redoCommands = new ArrayList<Command>();

	public void executeCommand(Command command) {

		command.execute();

		undoCommands.add(command);

		redoCommands.clear();
	}

	public void undo() {

		if (undoCommands.isEmpty()) {
			return;
		}

		Command command =
				undoCommands.remove(undoCommands.size() - 1);

		command.unexecute();

		redoCommands.add(command);
	}

	public void redo() {

		if (redoCommands.isEmpty()) {
			return;
		}

		Command command =
				redoCommands.remove(redoCommands.size() - 1);

		command.execute();

		undoCommands.add(command);
	}

	public boolean canUndo() {
		return !undoCommands.isEmpty();
	}

	public boolean canRedo() {
		return !redoCommands.isEmpty();
	}

	public Command getLastUndoCommand() {

		if (undoCommands.isEmpty()) {
			return null;
		}

		return undoCommands.get(undoCommands.size() - 1);
	}

	public Command getLastRedoCommand() {

		if (redoCommands.isEmpty()) {
			return null;
		}

		return redoCommands.get(redoCommands.size() - 1);
	}
}