// HW 6
// Burgard Lu (jl4nq)
// Source: Professor Basit's OH
// Piazza
public class Elevator extends Provided.AbstractElevator {

	public Elevator(Provided.AbstractElevatorController control) {
		super(control);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void hail(int floor, Provided.Person p) throws Provided.OccupiedException { // pass in the person who hailed the elevator and the target floor
		try {
			lock.lock();
			if (!this.isAvailable()) throw new Provided.OccupiedException(""+id);  // If this elevator is not available to be hailed, throw an OccupiedException and print out the id of the unavailable elevator
			passenger = p;
			targetFloor = floor;
		}
		finally {
			lock.unlock();
		}

	}

	@Override
	public void start() { // start the thread running the elevator
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public boolean isAvailable() { // The elevator is available when it has not picked up anyone and there is no passenger 
		return (!carrying && passenger == null);
	}

	@Override
	protected void pickUp() { // The elevator picks up the passenger, and elevator starts moving towards the destination
		passenger.board(this);
		carrying = true;
		targetFloor = passenger.getDestination();
	}

	@Override
	protected boolean shouldPickUp() { // To check if the elevator has reached the floor the passenger is at, when there is some passenger who wants an elevator and the elevator has not picked up the passenger 
		return (this.getFloor() == targetFloor && !carrying && passenger != null);
	}

	@Override
	protected void offload() { // The elevator offloads the passenger, and sends the signal that it has finished its mission and becomes once again available
		passenger.exit();
		carrying = false;
		passenger = null;
		this.control.lock.lock();
		this.control.elevatorFinished.signalAll();
		this.control.lock.unlock();

	}

	@Override
	protected boolean shouldOffload() { // To check if the elevator reaches the target floor when it is still carrying its passenger
		return (this.getFloor() == targetFloor && carrying);
	}

}
