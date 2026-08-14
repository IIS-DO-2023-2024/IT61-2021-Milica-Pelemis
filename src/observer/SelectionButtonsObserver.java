package observer;

import javax.swing.JButton;

public class SelectionButtonsObserver implements SelectionObserver {

	private JButton modifyButton;
	private JButton deleteButton;

	public SelectionButtonsObserver(
			JButton modifyButton,
			JButton deleteButton) {

		this.modifyButton = modifyButton;
		this.deleteButton = deleteButton;
	}

	@Override
	public void update(int selectedCount) {

		deleteButton.setEnabled(selectedCount > 0);

		modifyButton.setEnabled(selectedCount == 1);
	}
}