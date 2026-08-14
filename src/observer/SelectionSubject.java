package observer;

import java.util.ArrayList;
import java.util.List;

public class SelectionSubject {

	private List<SelectionObserver> observers =
			new ArrayList<SelectionObserver>();

	private int selectedCount;

	public void addObserver(SelectionObserver observer) {
		observers.add(observer);
	}

	public void removeObserver(SelectionObserver observer) {
		observers.remove(observer);
	}

	public void setSelectedCount(int selectedCount) {
		this.selectedCount = selectedCount;
		notifyObservers();
	}

	public void notifyObservers() {

		for (SelectionObserver observer : observers) {
			observer.update(selectedCount);
		}
	}
}